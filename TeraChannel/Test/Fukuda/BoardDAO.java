package Fukuda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Board;

public class BoardDAO {
	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Board card) { //処理の結果をtrue,falseで返す
		Connection conn = null;
		boolean result = false;
		boolean result_search = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");
			ResultSet rs;

			//SQL文を準備する	検閲単語一覧
			String sql = "select search_word from search";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行する
			rs = pStmt.executeQuery();
			String topic = card.getBoard_topic();
			String main = card.getBoard_main();

			//search_wordの行がある限りwhileで検閲
			while (rs.next()) {
				//読みとった行のsearch_wordを変数にいれる
				String word = rs.getString("search_word");
				//indexOf()を使って単語が内容に入っていないか検索を行う
				//指定した文字列が見つかった場合は、その文字列が出現する先頭の位置を返す。見つからなかった場合は-1を返す
				int result_topic = topic.indexOf(word);
				int result_main = main.indexOf(word);

				//result_topicとresult_mainともに見つからなかった場合true
				if (result_topic == -1 && result_main == -1) {
					result_search = true;
				}
				//見つかった場合false	その時点でwhileから抜ける
				else {
					result_search = false;
					break;
				}
			}

			//result_searchがtrueならinsertに移る
			if (result_search) {
				// SQL文を準備する	投稿機能
				String sql2 = "insert into board values (null,?,?,0,0,0,current_timestamp,?)";
				PreparedStatement pStmt2 = conn.prepareStatement(sql2);

				// SQL文を完成させる		idは自動採番(元がnull)なので記述不要	？の位置に実際に挿入するための記述
				if (card.getBoard_topic() != null && !card.getBoard_topic().equals("")) {
					pStmt2.setString(1, card.getBoard_topic());
				} else {
					pStmt2.setString(1, "null");
				}
				if (card.getBoard_main() != null && !card.getBoard_main().equals("")) {
					pStmt2.setString(2, card.getBoard_main());
				} else {
					pStmt2.setString(2, "null");
				}
				//セッションスコープからuser_idを参照→Servletで処理
				if (card.getUser_id() != 0) {
					pStmt2.setInt(3, card.getUser_id());
				} else {
					pStmt2.setInt(3, 0);
				}

				// SQL文を実行する	何件処理したかを返してくれる
				if (pStmt2.executeUpdate() == 1) {
					result = true;
				} else {
					result = false;
				}
			} else {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// 結果を返す
		return result;
	}

	public int insert1(Board card) { //処理の結果をtrue,falseで返す
		Connection conn = null;
		//boolean result = false;
		boolean result_search = false;

		//auto_increment
		int ai = 0;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");
			ResultSet rs;

			//SQL文を準備する	検閲単語一覧
			String sql = "select search_word from search";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行する
			rs = pStmt.executeQuery();
			String topic = card.getBoard_topic();
			String main = card.getBoard_main();

			//search_wordの行がある限りwhileで検閲
			while (rs.next()) {
				//読みとった行のsearch_wordを変数にいれる
				String word = rs.getString("search_word");
				//indexOf()を使って単語が内容に入っていないか検索を行う
				//指定した文字列が見つかった場合は、その文字列が出現する先頭の位置を返す。見つからなかった場合は-1を返す
				int result_topic = topic.indexOf(word);
				int result_main = main.indexOf(word);

				//result_topicとresult_mainともに見つからなかった場合true
				if (result_topic == -1 && result_main == -1) {
					result_search = true;
				}
				//見つかった場合false	その時点でwhileから抜ける
				else {
					result_search = false;
					break;
				}
			}

			//result_searchがtrueならinsertに移る
			if (result_search) {
				// SQL文を準備する	投稿機能
				String sql2 = "insert into board values (null,?,?,0,0,0,current_timestamp,?)";
				PreparedStatement pStmt2 = conn.prepareStatement(sql2,java.sql.Statement.RETURN_GENERATED_KEYS);

				// SQL文を完成させる		idは自動採番(元がnull)なので記述不要	？の位置に実際に挿入するための記述
				if (card.getBoard_topic() != null && !card.getBoard_topic().equals("")) {
					pStmt2.setString(1, card.getBoard_topic());
				} else {
					pStmt2.setString(1, "null");
				}
				if (card.getBoard_main() != null && !card.getBoard_main().equals("")) {
					pStmt2.setString(2, card.getBoard_main());
				} else {
					pStmt2.setString(2, "null");
				}
				//セッションスコープからuser_idを参照→Servletで処理
				if (card.getUser_id() != 0) {
					pStmt2.setInt(3, card.getUser_id());
				} else {
					pStmt2.setInt(3, 0);
				}

				// SQL文を実行する	何件処理したかを返してくれる
				if (pStmt2.executeUpdate() == 1) {
					//result = true;
					//Auto_Incrementされたidを取得
					rs = pStmt2.getGeneratedKeys();
					 if(rs.next()){
			             ai = rs.getInt(1);
			         }
				} else {
					//result = false;
					ai = 0;
				}
			} else {
				//result = false;
				ai = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// 結果を返す
		//return result;
		return ai;
	}


}
