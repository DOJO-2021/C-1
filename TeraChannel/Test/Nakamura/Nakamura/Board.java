package Nakamura;
import java.io.Serializable;
import java.util.ArrayList;

import model.Reply;

public class Board implements Serializable {
	private int board_id;
	private String board_topic;
	private String board_main;
	private int board_smile;
	private int board_shock;
	private int board_tear;
	private String board_update;
	private int user_id;
	private ArrayList<Reply> reply;
	private int board_smileTotal;

	//コンストラクタ
	public Board(int board_id, String board_topic, String board_main, int board_smile, int board_shock, int board_tear,
			String board_update, int user_id) {
		super();
		this.board_id = board_id;
		this.board_topic = board_topic;
		this.board_main = board_main;
		this.board_smile = board_smile;
		this.board_shock = board_shock;
		this.board_tear = board_tear;
		this.board_update = board_update;
		this.user_id = user_id;
	}

	//引数なしコンストラクタ
	public Board() {
		super();
		this.board_id = 0;
		this.board_topic = "";
		this.board_main = "";
		this.board_smile = 0;
		this.board_shock = 0;
		this.board_tear = 0;
		this.board_update = "";
		this.user_id = 0;
		this.reply = new ArrayList<Reply>();
		this.board_smileTotal = 0;
	}

	//ゲッターとセッター
	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getBoard_topic() {
		return board_topic;
	}

	public void setBoard_topic(String board_topic) {
		this.board_topic = board_topic;
	}

	public String getBoard_main() {
		return board_main;
	}

	public void setBoard_main(String board_main) {
		this.board_main = board_main;
	}

	public int getBoard_smile() {
		return board_smile;
	}

	public void setBoard_smile(int board_smile) {
		this.board_smile = board_smile;
	}

	public int getBoard_shock() {
		return board_shock;
	}

	public void setBoard_shock(int board_shock) {
		this.board_shock = board_shock;
	}

	public int getBoard_tear() {
		return board_tear;
	}

	public void setBoard_tear(int board_tear) {
		this.board_tear = board_tear;
	}

	public String getBoard_update() {
		return board_update;
	}

	public void setBoard_update(String board_update) {
		this.board_update = board_update;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public ArrayList<Reply> getReply() {
		return reply;
	}

	public void setReply(ArrayList<Reply> reply) {
		this.reply = reply;
	}

	public int getBoard_smileTotal() {
		return board_smileTotal;
	}

	public void setBoard_smileTotal(int board_smileTotal) {
		this.board_smileTotal = board_smileTotal;
	}
}
