package model;

public class LoginUser {
	private int user_id;
	private int user_type;
	private String user_name;
	private String user_mail;





	public LoginUser(int user_id, int user_type, String user_name, String user_mail) {
		super();
		this.user_id = user_id;
		this.user_type = user_type;
		this.user_name = user_name;
		this.user_mail = user_mail;
	}

	public LoginUser() {
		super();
		this.user_id = 0;
		this.user_type = 0;
		this.user_name = "";
		this.user_mail = "";
	}






	public int getUser_id() {
		return user_id;
	}
	public int getUser_type() {
		return user_type;
	}
	public String getUser_name() {
		return user_name;
	}
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}
}
