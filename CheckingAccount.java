/**
 * This class implementes CheckingAccount as a concrete instance of the Abstract account class
 *
 * @author 
 * 
 * @version 1.4
 */

public final class CheckingAccount extends Account{
	/** Number of free transactions before fees start */
	private int freeTransactions;

	/** Transaction fee */
	private double transactionFee;

	/** Count of number of transactions since the last transaction count */
	private int transactionCount;

	/**
	 * The following is a constructor that sets the amount to the attribute amount
	 * of the class Account.
	 * 
	 * @param amount
	 */
	public CheckingAccount(double amount) {
		super(amount);
	}
	
	/**
	 * The following constructor sets the attributes of the CheckingAccount
	 * to the values that are sent as parameters.
	 * @param accountNumber sets the accountNumber attribute based on accountNumber.
	 * @param accountHolderName sets the accountHolderName attribute based on accountHolderName.
	 * @param dateOfBirth sets the date of birth of the account holder based on the dateOfBirth parameter.
	 * @param email sets the email attribute
	 * @param phoneNumber sets the phoneNumber attribute
	 * @param amount the initial amount to be set based on the parameter.
	 */
	public CheckingAccount(String accountNumber, String accountHolderName,
			String dateOfBirth, String contactAddress,
			String email, String phoneNumber, double amount) {
		super(accountNumber, accountHolderName, dateOfBirth, contactAddress, email, phoneNumber, amount);
		this.transactionCount = 0;
		this.transactionFee = 100.0;
		this.freeTransactions = 3;
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

		// Increment the transaction count
		this.transactionCount++;

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

		// Increment the transaction count
		this.transactionCount++;

		return true;
	}

	/**
	 * Compute the fees based on the number of transactions; deduct it from the account
	 * and reset the transaction count
	 *
	 * @return true if the fees is successfully deducted from the account
	 *
	 * @exception throw an exception InvalidAmountException if the amount is less than 0
	 * with a message "Amount can't be negative or 0."
	 */
	public boolean deductFees() throws InsufficientFundsException, InvalidAmountException {
		double fees;
		if (this.transactionCount > this.freeTransactions){
			fees = this.transactionFee * (this.transactionCount - this.freeTransactions);
		}
		else{
			fees = 0.0;
		}
		boolean flag = debit(fees);
		this.transactionCount = 0;
		
		return flag;
	}

	/**
	 * @return int The number of free transactions allowed for this account
	 */
	public int getFreeTransactions(){
		return this.freeTransactions;
	}
	
	/**
	 * @param freeTransactions The number of free transactions to set
	 */
	public void setFreeTransactions(int freeTransactions){
		this.freeTransactions = freeTransactions;
	}

	/**
	 * @return double The fee for each transaction
	 */
	public double getTransactionFee(){
		return this.transactionFee;
	}

	/**
	 * @param transactionFee The transaction fee to set
	 */
	public void setTransactionFee(double transactionFee){
		this.transactionFee = transactionFee;
	}
}
