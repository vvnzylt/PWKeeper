package backend;

import java.util.ArrayList;

public class AccountDB {
	private static ArrayList<AccountDB> account = new ArrayList<AccountDB>();
	private ArrayList<ItemDetails> item = new ArrayList<ItemDetails>();
	private static int loggedInAcccountIndex;
	
	private String username;
	private String password;
	
	AccountDB(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public static ArrayList<AccountDB> getAccount() {
		return account;
		
	}
	
	public ArrayList<ItemDetails> getAccountListObj() {
		return item;
	}
	
	String getUsername() {
		return username;
	}
	
	String getPassword() {
		return password;
	}
	
	public static void setLoggedInAccountIndex(int num) {
		loggedInAcccountIndex = num;
	}
	
	public static int getLoggedInAccountIndex() {
		return loggedInAcccountIndex;
	}
	
	public void addAccountDetails(String itemNameValue, String usernameValue, String passwordValue, String urlValue, String notesValue) {
		item.add(new ItemDetails(itemNameValue, usernameValue, passwordValue, urlValue, notesValue));
	}
	
	public void removeItem(ItemDetails accountItem) {
		item.remove(accountItem);
	}
	
	public void getListOfAccountDetails() {
		for (int i = 0; i < item.size(); i++) {
			System.out.println("Account #" + (i+1));
			item.get(i).getFullDetails();
		}
	}
}
