import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
/**
 * This class tests the implementation of the Bank class and the associated account classes (Current/Checkings/Savings
 * accounts)
 *
 * @author
 *
 * @version 1.4
 */
 
public final class BankTest{
	/**
	 * The main method is used to test this Bank class.
	 * 
	 * @param args the command line arguments. No need to pass any command line 
	 * arguments at the time of executing this program.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Bank bank = new Bank();
		Account account;
		
		while (sc.hasNextLine()) {
			String tokens[] = sc.nextLine().split(" ");
			switch (tokens[0]) {
				case "bank":
					tokens = tokens[1].split(",");
					bank = new Bank(tokens[0], tokens[1], tokens[2]);
					System.out.println("Initialized Bank " + bank);
					break;
				case "savings":
					tokens = tokens[1].split(",");
					account = new SavingsAccount(tokens[0], tokens[1], tokens[2], tokens[3],
							tokens[5], tokens[5], Double.parseDouble(tokens[6]));
					bank.addAccount(account);
					System.out.println("Savings Account created with " + account);
					break;
				case "current":
					tokens = tokens[1].split(",");
					account = new CurrentAccount(tokens[0], tokens[1], tokens[2], tokens[3],
							tokens[5], tokens[5], Double.parseDouble(tokens[6]));
					bank.addAccount(account);
					System.out.println("Current Account created with " + account);
					break;
				case "checking":
					tokens = tokens[1].split(",");
					account = new CheckingAccount(tokens[0], tokens[1], tokens[2], tokens[3],
							tokens[5], tokens[5], Double.parseDouble(tokens[6]));
					bank.addAccount(account);
					System.out.println("Checking Account created with " + account);
					break;
				case "debit":
					try{
						tokens = tokens[1].split(",");
						String accountNumber = tokens[0];
						double amount = Double.parseDouble(tokens[1]);
						bank.getAccount(accountNumber).setTransaction("debit");
						boolean flag = bank.getAccount(accountNumber).debit(amount);
						if (flag)
							System.out.println("After debit from " + bank.getAccount(accountNumber));
						
					} catch(Exception ex){
						System.out.println(ex.getMessage());
					}
					break;
				case "credit":
					try{
						tokens = tokens[1].split(",");
						String accountNumber = tokens[0];
						double amount = Double.parseDouble(tokens[1]);
						bank.getAccount(accountNumber).setTransaction("credit");
						boolean flag = bank.getAccount(accountNumber).credit(amount);
						if (flag)
							System.out.println("After credit to " + bank.getAccount(accountNumber));
						
					} catch(Exception ex){
						System.out.println(ex.getMessage());
					}
					break;
				case "creditinterest":
					try{
						String accountNumber = tokens[1];
						Account ac = bank.getAccount(accountNumber);
						if (ac instanceof SavingsAccount){
							SavingsAccount s = (SavingsAccount) ac;
							boolean flag = bank.creditInterest(s);
							if (flag)
								System.out.println("After crediting interest to " + s);
						}
					} catch(Exception ex){
						System.out.println(ex.getMessage());
					}
					break;
				case "debitfees":
					try{
						String accountNumber = tokens[1];
						Account ac = bank.getAccount(accountNumber);
						if (ac instanceof CheckingAccount){
							CheckingAccount ca = (CheckingAccount) ac;
							boolean flag = bank.deductFees(ca);
							if (flag)
								System.out.println("After debiting transaction fees from " + ca);
						}
					} catch(Exception ex){
						System.out.println(ex.getMessage());
					}
					break;
				case "displayaccounts":
					ArrayList<Account> accounts = bank.getAccounts();
					Collections.sort(accounts, Collections.reverseOrder());
					for (Account a: accounts){
						System.out.println(a);
						ArrayList<String> transactions = a.getTransactions();
						if (transactions.isEmpty()){
							System.out.println(a.getAccountNumber() + " is a non-performing account");
						}	
					}
				default:
					break;
			}
		}
	}
}

