package model;

public class User {
	private int user_id;
	private String user_name;
	private String user_pw;
	private int user_type;
	private String user_mail;
	private int user_count;
	private int user_nameCount;


	public User(int user_id, String user_name, String user_pw, int user_type, String user_mail, int user_count,
			int user_nameCount) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_pw = user_pw;
		this.user_type = user_type;
		this.user_mail = user_mail;
		this.user_count = user_count;
		this.user_nameCount = user_nameCount;
	}


	public User() {
		super();
		this.user_id = 0;
		this.user_name ="";
		this.user_pw = "";
		this.user_type = 0;
		this.user_mail = "";
		this.user_count =0;
		this.user_nameCount = 0;
	}


	public int getUser_id() {
		return user_id;
	}


	public String getUser_name() {
		return user_name;
	}


	public String getUser_pw() {
		return user_pw;
	}


	public int getUser_type() {
		return user_type;
	}


	public String getUser_mail() {
		return user_mail;
	}


	public int getUser_count() {
		return user_count;
	}


	public int getUser_nameCount() {
		return user_nameCount;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}


	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}


	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}


	public void setUser_count(int user_count) {
		this.user_count = user_count;
	}


	public void setUser_nameCount(int user_nameCount) {
		this.user_nameCount = user_nameCount;
	}

}





