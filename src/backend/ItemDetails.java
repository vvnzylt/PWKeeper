package backend;

public class ItemDetails {
	private String itemName;
	private String username;
	private String password;
	private String url;
	private String notes;
	
	ItemDetails(String itemNameValue, String usernameValue, String passwordValue, String urlValue, String notesValue) {
		this.itemName = itemNameValue;
		this.username = usernameValue;
		this.password = passwordValue;
		this.url = urlValue;
		this.notes = notesValue;
	}
	
	public void getFullDetails() {
		System.out.println("Item name: " + itemName);
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		System.out.println("URL: " + url);
		System.out.println("Notes: " + notes);
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getURL() {
		return url;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setItemName(String itemNameValue) {
		this.itemName = itemNameValue;
	}
	
	public void setUsername(String usernameValue) {
		this.username = usernameValue;
	}
	
	public void setPassword(String passwordValue) {
		this.password = passwordValue;
	}
	
	public void setURL(String urlValue) {
		this.url = urlValue;
	}
	
	public void setNotes(String notesValue) {
		this.notes = notesValue;
	}
}
