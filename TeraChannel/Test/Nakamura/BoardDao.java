package Nakamura;

import java.sql.Connection;		//データベースに接続するメソッド
import java.sql.DriverManager; //ドライバに接続するメソッドを持つ
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDao {

	//両者側の詳細ページを開いた際の投稿データを返すメソッド


	public Board showBoard(int board_id) {
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");

			ResultSet rs;

			//SQL文を準備する
			String sql = "select * from board where board_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);


			//SQL文を完成させる
			if (board_id != 0) {
				pStmt.setInt(1, board_id);
			}
			else {
				pStmt.setInt(1, 0);
			}
			//SQL文の実行
			rs = pStmt.executeQuery();

			rs.next();
			Board bd = new Board(
					rs.getInt("BOARD_ID"),
					rs.getString("BOARD_TOPIC"),
					rs.getString("BOARD_MAIN"),
					rs.getInt("BOARD_SMILE"),
					rs.getInt("BOARD_SHOCK"),
					rs.getInt("BOARD_TEAR"),
					rs.getString("BOARD_UPDATE"),
					rs.getInt("USER_ID")
					);

			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}

			// 結果を返す
			return bd;


		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
	}









	/*ユーザー側の詳細ページで編集を反映させる(投稿/返信)メソッド
	・投稿部分:BoardDao.java
	 */
	public boolean editBoard(String board_main,int board_id) {	//処理の結果をtrue,falseで返す
		Connection conn = null;
		boolean result = false;
		boolean result_search=false;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			ResultSet rs;
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");

			//SQL文を準備する	検閲機能
			String sql = "SELECT search_word FROM search";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行する	何件処理したかを返してくれる
			rs = pStmt.executeQuery();

			//String topic=param.getBoard_topic();
			String main=board_main;

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
				String sql2 = "update board set board_main=?,board_update=current_time where board_id=?";
				PreparedStatement pStmt2 = conn.prepareStatement(sql2);

				// SQL文を完成させる		idは自動採番(元がnull)なので記述不要	？の位置に実際に挿入するための記述
				if (main != null && !main.equals("")) {
					pStmt2.setString(1, main);
				}
				else {
					pStmt2.setString(1, "null");
				}
				pStmt2.setInt(2, board_id);

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
















	 /*削除ボタンが押された時の削除メソッド
	  ・投稿部分:BoardDao.java
	 */
	public boolean deleteBoard(int board_id) {	//処理の結果をtrue,falseで返す
		Connection conn = null;
		boolean result = false;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			ResultSet rs;
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");

			// SQL文を準備する	true
			String sql = "delete board where board_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる		idは自動採番(元がnull)なので記述不要	？の位置に実際に挿入するための記述
			pStmt.setInt(1, board_id);

			// SQL文を実行する	何件処理したかを返してくれる
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
			else {
				result = false;
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










	/*リアクションの登録メソッド(リアクションごとではなくすべてを更新
	 * ※増えているのは一つだけであるから（javascriptで制御）)
	 *   この際、引数として、それぞれのリアクションの値を用いる
	 */

	public boolean registReaction(int board_id,int smile,int shock,int tear) {	//処理の結果をtrue,falseで返す
		Connection conn = null;
		boolean result = false;
		boolean result_search=false;
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			ResultSet rs;
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");

			// SQL文を準備する	true
			String sql = "update board set board_smile=?,board_update=current_times,board_shock=?,board_tear=? where board_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, smile);
			pStmt.setInt(2, shock);
			pStmt.setInt(3, tear);
			pStmt.setInt(4, board_id);
			// SQL文を実行する	何件処理したかを返してくれる
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
			else {
				result = false;
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
