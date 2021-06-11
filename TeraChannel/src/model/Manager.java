package model;

public class Manager {
    private int manager_id;
    private String manager_ps;
    private String manager_mail;

	public Manager(int manager_id, String manager_ps, String manager_mail) {
		super();
		this.manager_id = manager_id;
		this.manager_ps = manager_ps;
		this.manager_mail = manager_mail;
	}

	public Manager() {
		super();
		this.manager_id = 0;
		this.manager_ps = "";
		this.manager_mail = "";
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public String getManager_ps() {
		return manager_ps;
	}

	public void setManager_ps(String manager_ps) {
		this.manager_ps = manager_ps;
	}

	public String getManager_mail() {
		return manager_mail;
	}

	public void setManager_mail(String manager_mail) {
		this.manager_mail = manager_mail;
	}


}
