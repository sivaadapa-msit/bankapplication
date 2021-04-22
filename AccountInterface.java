/**
 * This is an interface specifying the minimum operations to be performed by an account
 *
 * @author
 *
 * @version 1.4
 */

public interface AccountInterface{
	/** 
	 * Method to debit/credit the amount from/to the account
	 * All classes implementing this Interface must implement these classes 
	 */
	private boolean debit (double amount) throws InvalidAmountException, InsufficientFundsException;
	private boolean credit (double amount) throws InvalidAmountException;
}
