/**
 * This class implementes CurrentAccount as a concrete instance of the Abstract account class
 *
 * @author 
 * 
 * @version 1.4
 */

public final class CurrentAccount extends Account{
	/** The maximum overdraft allowed by the bank */
	private double maxOverdraft;
	
	/** The current overdraft in the account */
	private double overdraftAmount;

	/**
	 * The following is a constructor that sets the amount to the attribute amount
	 * of the class Account.
	 * 
	 * @param amount
	 */
	public CurrentAccount(double amount) {
		super(amount);
	}
	
	/**
	 * The following constructor sets the attributes of the Account
	 * to the values that are sent as parameters.
	 * @param accountNumber sets the accountNumber attribute based on accountNumber.
	 * @param accountHolderName sets the accountHolderName attribute based on accountHolderName.
	 * @param dateOfBirth sets the date of birth of the account holder based on the dateOfBirth parameter.
	 * @param email sets the email attribute
	 * @param phoneNumber sets the phoneNumber attribute
	 * @param amount the initial amount to be set based on the parameter.
	 */
	public CurrentAccount(String accountNumber, String accountHolderName,
			String dateOfBirth, String contactAddress,
			String email, String phoneNumber, double amount) {
		super(accountNumber, accountHolderName, dateOfBirth, contactAddress, email, phoneNumber, amount);
		this.overdraftAmount = 0.0;
		this.maxOverdraft = 10000.0;
	}

	/**
	* Credit the amount to this Account.
	* 
	* @param amount to credit to this Account.
	* 
	* @return true on successfully the amount is credited to this Account.
	* 
	* @exception throw an exception InvalidAmountException if the amount 
	* is less than 0 with a message "Amount can't be negative or 0.".
	*/
	private boolean deposit(double amount) throws InvalidAmountException {
		// Get the current balance of the account
		double currentBalance = super.getAmount();

		if (amount <= 0){
			throw new InvalidAmountException("Amount can't be negative or 0.");
		}

		// Compute the new balance of the account and update it
		double newBalance = currentBalance + amount;
		super.setAmount(newBalance);

		return true;
	}
	
	/**
	 * This method performs to debit the amount on this Account.
	 * 
	 * @param amount to debit
	 * 
	 * @return true on successfully debited the amount.
	 * 
	 * @exception throw an exception called InsufficientFundsException if the
	 * amount in this Account is less than the amount to be debited with a message
	 * "Insufficient funds to withdraw the amount."
	 * 
	 * @exception throw an exception called InvalidAmountException if the amount 
	 * is less than 0 with a message "Amount can't be negative or 0.".
	 */
	private boolean remove(double amount) throws InvalidAmountException, InsufficientFundsException {
		// Get the current balance of the account
		double currentBalance = super.getAmount();

		if (amount <= 0){
			throw new InvalidAmountException("Amount can't be negative or 0.");
		}

		if (currentBalance < amount){
			throw  new InsufficientFundsException("Insufficient funds to withdraw the amount.");
		}
		
		// Compute the new balance of the account and update it
		double newBalance = currentBalance - amount;
		super.setAmount(newBalance);

		return true;
	}
	
	/**
	 * Deposit the given amount into the current account by checking for overdraftAmount
	 */
	@Override
	public boolean credit(double amount) throws InvalidAmountException{
		boolean flag;
		// If overdraft amount exists first add funds to the overdraft and then to the account
		if (overdraftAmount > 0.0) {
			if (amount < overdraftAmount) {
				overdraftAmount -= amount;
				flag = deposit(0.0);
			}
			else {
				double balance = amount - overdraftAmount;
				overdraftAmount = 0.0;
				flag = deposit(balance);
			}
		}
		else{
			flag = deposit(amount);
		}

		return flag;
	}
	
	/**
	 * Withdraw the given amount from the checking account allowing overdraft
	 */
	@Override
	public boolean debit(double amount) throws InsufficientFundsException, InvalidAmountException{
		boolean flag;
		// If account has sufficient funds; just debit it
		if (this.getAmount() >= amount){
			flag = remove(amount);
		}
		else{
			double shortfall = amount - super.getAmount();
			double availableOverdraft = this.maxOverdraft - this.overdraftAmount;

			if (shortfall > availableOverdraft){
				throw new InsufficientFundsException("Insufficient funds to withdraw the amount.");
			}
			
			overdraftAmount += shortfall;
			flag = remove(amount - shortfall);
		}

		return flag;
	}

	/**
	 * @return double The max overdraft allowed for this account
	 */
	public double getMaxOverdraft(){
		return this.maxOverdraft;
	}

	/**
	 * @param maxOverdraft The max overdraft value to set for this account
	 */
	public void setMaxOverdraft(){
		this.maxOverdraft = maxOverdraft;
	}
}
