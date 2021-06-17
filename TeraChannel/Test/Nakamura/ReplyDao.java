package Nakamura;

import java.sql.Connection;		//データベースに接続するメソッド
import java.sql.DriverManager; //ドライバに接続するメソッドを持つ
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReplyDao {

	//両者側の詳細ページを開いた際の返信データを返すメソッド

	public List<Reply> showReply(int board_id) {
		Connection conn = null;
		List<Reply> replyList = new ArrayList<Reply>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");

			ResultSet rs;

			//SQL文を準備する
			String sql = "select * from reply where board_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);


			//SQL文を完成させる
			if (board_id!=0) {
				pStmt.setInt(1, board_id);
			}
			else {
				pStmt.setInt(1, 0);
			}
			//SQL文の実行
			rs = pStmt.executeQuery();

			while (rs.next()) {
				Reply rep = new Reply(
						rs.getInt("REPLY_ID"),
						rs.getString("REPLY_MAIN"),
						rs.getString("REPLY_DATE"),
						rs.getInt("USER_ID"),
						rs.getInt("BOARD_ID")
						);
				replyList.add(rep);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			replyList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			replyList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					replyList = null;
				}
			}
		}

		// 結果を返す
		return replyList;
	}






	/*管理者用の詳細ページにおける検索機能を行うメソッド（仮で返信に関してのみとする）
	jspでの出力は得られた返信のみを出力する
	（検閲を目的としているので他のものは出力する必要がない）
	・ReplyDao.java
	 */
	public List<Reply> showSelectedReply(String param) {
		Connection conn = null;
		List<Reply> replyList = new ArrayList<Reply>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");

			ResultSet rs;

			//SQL文を準備する
			String sql = "select * from reply where reply_main like ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);


			//SQL文を完成させる
			if (param != null && !param.equals("")) {
				pStmt.setString(1, "%" + param + "%");
			}
			else {
				pStmt.setString(1, "");
			}
			//SQL文の実行
			rs = pStmt.executeQuery();

			while (rs.next()) {
				Reply rep = new Reply(
						rs.getInt("REPLY_ID"),
						rs.getString("REPLY_MAIN"),
						rs.getString("REPLY_DATE"),
						rs.getInt("USER_ID"),
						rs.getInt("BOARD_ID")
						);
				replyList.add(rep);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			replyList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			replyList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					replyList = null;
				}
			}
		}

		// 結果を返す
		return replyList;
	}









	/*ユーザー側の返信ボタンが押された時の登録処理
	・ReplyDao.java
	 */
	public boolean insertReply(Reply param) {	//処理の結果をtrue,falseで返す
		Connection conn = null;
		boolean result = false;
		boolean result_search=false;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");
			ResultSet rs;

			//SQL文を準備する	検閲機能
			String sql = "SELECT search_word FROM search";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行する	何件処理したかを返してくれる
			rs = pStmt.executeQuery();

			String main=param.getReply_main();

			//rs.next()の処理で受け取ったデータを次の行に移動
			while(rs.next()){
				//indexOf()を使って各単語で検閲を行っていく
				String word=rs.getString("search_word");

				int result_main=main.indexOf(word);

				if(result_main==-1){

					result_search=true;

				}else{
					result_search=false;
					break;
				}

			}
			//検閲(result_search)のtrue/falseでinsert文を実行
			if (result_search) {
				// SQL文を準備する	true
				String sql2 = "insert into reply values (null, ?,current_timestamp, ?, ?)";
				PreparedStatement pStmt2 = conn.prepareStatement(sql2);

				// SQL文を完成させる		idは自動採番(元がnull)なので記述不要	？の位置に実際に挿入するための記述
				if (param.getReply_main() != null && !param.getReply_main().equals("")) {
					pStmt2.setString(1, param.getReply_main());
				}
				else {
					pStmt2.setString(1, "null");
				}
				pStmt2.setInt(2, param.getUser_id());
				pStmt2.setInt(3, param.getBoard_id());


				// SQL文を実行する	何件処理したかを返してくれる
				if (pStmt2.executeUpdate() == 1) {
					result = true;
				}
				else {
					result = false;
				}
			}
			else {
				result=false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			result = false;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					result=false;
				}
			}
		}

		// 結果を返す
		return result;
	}




}
