package Nakamura;

public class Reply {
	private int reply_id;
	private String reply_main;
	private String reply_date;
	private int user_id;
	private int board_id;

	public Reply(int reply_id, String reply_main, String reply_date, int user_id, int board_id) {
		super();
		this.reply_id = reply_id;
		this.reply_main = reply_main;
		this.reply_date = reply_date;
		this.user_id = user_id;
		this.board_id = board_id;
	}

	public Reply() {
		super();
		this.reply_id = 0;
		this.reply_main = "";
		this.reply_date = "";
		this.user_id = 0;
		this.board_id = 0;
	}

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public String getReply_main() {
		return reply_main;
	}

	public void setReply_main(String reply_main) {
		this.reply_main = reply_main;
	}

	public String getReply_date() {
		return reply_date;
	}

	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
}
