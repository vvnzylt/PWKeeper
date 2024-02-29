package backend;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ItemDetails {
	private String itemName;
	private String username;
	private String password;
	private String url;
	private String notes;
	private Date createdTime;
	private Date modifiedTime;
	private int modifiedCounter = 0;
	
	ItemDetails(String itemNameValue, String usernameValue, String passwordValue, String urlValue, String notesValue) {
		this.itemName = itemNameValue;
		this.username = usernameValue;
		this.password = passwordValue;
		this.url = urlValue;
		this.notes = notesValue;
		this.createdTime = new Date();
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
	
	public String getCreatedTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy, h:mm:ss a", Locale.ENGLISH);
		String formattedCreatedTime = sdf.format(createdTime);
		return formattedCreatedTime;
	}
	
	public String getModifiedTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy, h:mm:ss a", Locale.ENGLISH);
		String formattedModifiedTime = sdf.format(modifiedTime);
		return formattedModifiedTime;
	}
	
	public int getModifiedCounter() {
		return modifiedCounter;
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
	
	public void setModifiedTime() {
		this.modifiedTime = new Date();
		this.modifiedCounter++;
	}
}
