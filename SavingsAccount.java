/**
 * This class implementes SavingsAccount as a concrete instance of the Abstract account class
 *
 * @author 
 * 
 * @version 1.4
 */

public final class SavingsAccount extends Account{
	/** The interest rate (for crediting interest in the savings account) */
	private double interestRate;

	/**
	 * The following is a constructor that sets the amount to the attribute amount
	 * of the class Account.
	 * 
	 * @param amount
	 */
	public SavingsAccount(double amount) {
		super(amount);
	}
	
	/**
	 * The following constructor sets the attributes of the SavingsAccount
	 * to the values that are sent as parameters.
	 * @param accountNumber sets the accountNumber attribute based on accountNumber.
	 * @param accountHolderName sets the accountHolderName attribute based on accountHolderName.
	 * @param dateOfBirth sets the date of birth of the account holder based on the dateOfBirth parameter.
	 * @param email sets the email attribute
	 * @param phoneNumber sets the phoneNumber attribute
	 * @param amount the initial amount to be set based on the parameter.
	 * @param rate the interest rate
	 */
	public SavingsAccount(String accountNumber, String accountHolderName,
			String dateOfBirth, String contactAddress,
			String email, String phoneNumber, double amount) {
		super(accountNumber, accountHolderName, dateOfBirth, contactAddress, email, phoneNumber, amount);
		this.interestRate = 4.0;
			
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
	@Override
	public boolean debit(double amount) throws InvalidAmountException, InsufficientFundsException {
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
	* Credit the amount to this Account.
	* 
	* @param amount to credit to this Account.
	* 
	* @return true on successfully the amount is credited to this Account.
	* 
	* @exception throw an exception InvalidAmountException if the amount 
	* is less than 0 with a message "Amount can't be negative or 0.".
	*/
	@Override
	public boolean credit(double amount) throws InvalidAmountException {
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
	 * This method adds the earned interest to the balance in the account
	 *
	 * @param interest amount to credit to this Account.
	 *
	 * @return true on sucessfully crediting the interest to this Account.
	 *
	 * @exception throw an exception InvalidAmountException if the amount
	 * is less than 0 with a message  "Amount can't be  negative or 0."
	 */
	public boolean creditInterest() throws InvalidAmountException {
		double accountBalance = super.getAmount();
		double interest = accountBalance * this.interestRate / 100;
		boolean flag = credit(interest);

		return flag;
	}

	/**
	 * @return double The interest rate for the saving account
	 */
	public double getInterestRate(){
		return this.interestRate;
	}

	/**
	 * @param interestRate The interest rate to set
	 */
	public void setInterestRate(double interestRate){
		this.interestRate = interestRate;
	}
}
