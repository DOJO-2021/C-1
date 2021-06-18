package model;

public class Password {
	private String user_mail;
	private String user_pw;
    private String new_pw;

	public Password(String user_mail,String new_pw) {
		super();
		this.user_mail = user_mail;

		this.new_pw = new_pw;
	}


	public Password() {
		super();
		this.user_mail = "";
		this.user_pw = "";
		this.new_pw = "";
	}


	public String getUser_mail() {
		return user_mail;
	}


	public String getNew_pw() {
		return new_pw;
	}

	public void setUser_id(String user_mail) {
		this.user_mail = user_mail;
	}



	public void setNew_pw(String new_pw) {
		this.new_pw = new_pw;
	}
}





