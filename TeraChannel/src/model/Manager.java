package model;

public class Manager {
    private int manager_id;
    private String manager_pw;
    private String manager_mail;

	public Manager(int manager_id, String manager_pw, String manager_mail) {
		super();
		this.manager_id = manager_id;
		this.manager_pw = manager_pw;
		this.manager_mail = manager_mail;
	}

	public Manager() {
		super();
		this.manager_id = 0;
		this.manager_pw = "";
		this.manager_mail = "";
	}

	public int getManager_id() {
		return manager_id;
	}

	public String getManager_pw() {
		return manager_pw;
	}

	public String getManager_mail() {
		return manager_mail;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public void setManager_pw(String manager_pw) {
		this.manager_pw = manager_pw;
	}

	public void setManager_mail(String manager_mail) {
		this.manager_mail = manager_mail;
	}
}
