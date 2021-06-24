package model;

public class Password {
	private String mail;
    private String new_pw;

	public Password(String mail,String new_pw) {
		super();
		this.mail = mail;

		this.new_pw = new_pw;
	}


	public Password() {
		super();
		this.mail = "";
		this.new_pw = "";
	}

	public String getMail() {
		return mail;
	}
	public String getNew_pw() {
		return new_pw;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setNew_pw(String new_pw) {
		this.new_pw = new_pw;
	}
}





