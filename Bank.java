import java.util.ArrayList;
/**
 * This class implements a Bank to manage the different kinds of acounts on behalf of customers
 *
 * @author 
 *
 * @version 1.4
 */

public final class Bank{
	/** The name of the bank */
	private String bankName;

	/** The name of the branch */
	private String branchName;

	/** The IFSC code of the branch */
	private String ifscCode;

	/** The accounts held by this bank */
	private ArrayList<Account> bankAccounts;

	/** The number of accounts held by this bank */
	private int numAccounts;

	/**
	 * The following is a default constructor for the Bank
	 */
	public Bank(){
		// This creates a Bank object with no attributes set
	}

	/**
	 * The following is a constructor that sets the attributes of the Bank
	 *
	 * @param bankName The name of the bank
	 * @param branchName The name of the branch
	 * @param ifscCode The IFSC code of the branch
	 */
	public Bank(String bankName, String branchName, String ifscCode){
		this.bankName = bankName;
		this.branchName = branchName;
		this.ifscCode = ifscCode;
		
		// Set the number of accounts to 0
		this.numAccounts = 0;

		// Bank can hold a maximum of 1000 accounts
		this.bankAccounts = new ArrayList<Account>();
	}

	/**
	 * This method adds an account to this bank
	 *
	 * @param account The account to add to the bank
	 */
	public void addAccount(Account account){
		this.bankAccounts.add(account);
		this.numAccounts++;
	}

	/**
	 * This method handles the deduction of fees for a checking account
	 *
	 * @param checkingAccount The checking account from which the fees has to be deducted
	 *
	 * @return true if the fees has been successfully deducted from the account
	 */
	public static boolean deductFees(CheckingAccount checkingAccount) throws InsufficientFundsException,
	       InvalidAmountException {
		boolean flag = checkingAccount.deductFees();
		
		return flag;
	}

	/**
	 * This method handles the crediting of interest to a savings account
	 *
	 * @param savingsAccount The savings account to which the interest has to be credited
	 *
	 * @return true if the interest has been successfully credited to the account
	 */
	public static boolean creditInterest(SavingsAccount savingsAccount) throws InvalidAmountException{
		boolean flag = savingsAccount.creditInterest();

		return flag;
	}

	/**
	 * Return the number of accounts in the bank
	 */
	public int getNumAccounts() {
		return this.numAccounts;
	}

	/**
	 * Return all the bank accounts in the bank
	 *
	 * @return Account[] Array containing all the accounts in the bank
	 */
	public ArrayList<Account> getAccounts() {
		return this.bankAccounts;
	}

	/**
	 * Return a particular bank account in the bank
	 *
	 * @param accountNumber The account number of the account to retrieve
	 *
	 * @return Account The account to retrieve; else null
	 */
	public Account getAccount(String accountNumber){
		for (Account a: this.bankAccounts){
			if (a.getAccountNumber().equals(accountNumber)){
				return a;
			}
		}
		return null;
	}

	/**
	 * This method returns the String version of the object
	 *
	 * @return The string version of the bank object
	 */
	public String toString(){
		return this.bankName + ", " + "Branch: " + this.branchName 
			+ ", " + "IFSC Code: " + this.ifscCode;
	}

}
