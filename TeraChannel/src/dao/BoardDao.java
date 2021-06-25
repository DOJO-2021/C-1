package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Reply;

public class BoardDao {

	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public int insert(Board card) { //処理の結果をtrue,falseで返す
		Connection conn = null;
		//boolean result = false;
		boolean result_search = false;

		//auto_incrementを入れる変数
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
						 //1列め(board_idの列)を取得するのでgetInt(1)
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
		//auto_incrementの値を返す
		return ai;
	}







	//投稿IDに基づく投稿データの取得メソッド
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
				String sql2 = "update board set board_main=?,board_update=current_timestamp where board_id=?";
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
			String sql = "update board set board_smile=?,board_update=current_timestamp,board_shock=?,board_tear=? where board_id=?";
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

	// reaction合計の数を格納し降順で返す
		// update all投稿、返信合わせての更新日を格納し降順で返す
		// 上記を含めてた上でtopic見出しを表示する
		public List<Board> topList(int push) {
			Connection conn = null;
			List<Board> topListMain = new ArrayList<Board>();

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");

				// SQL文1を準備する Boardテーブル用
				//値が0だったらリアクションの多い順で返す
				String sql = "";
				if (push == 0) {
					sql = "SELECT "
							+ "(BOARD_SMILE +  BOARD_SHOCK  + BOARD_TEAR ) AS REACTION , "
							+ "BOARD_UPDATE ,BOARD_TOPIC  ,BOARD_ID "
							+ "FROM BOARD "
							+ "GROUP BY BOARD_TOPIC "
							+ "ORDER BY "
							+ "REACTION DESC";

				} else if (push == 1) {
					sql = "SELECT "
							+ "(BOARD_SMILE +  BOARD_SHOCK  + BOARD_TEAR ) AS REACTION , "
							+ "BOARD_UPDATE ,BOARD_TOPIC  ,BOARD_ID "
							+ "FROM BOARD "
							+ "GROUP BY BOARD_TOPIC "
							+ "ORDER BY "
							+ "REACTION ASC";
				}
				PreparedStatement pStmt = conn.prepareStatement(sql);

				ResultSet rs = pStmt.executeQuery();

				//select文の結果をArrayListに格納
				while (rs.next()) {
					Board page1 = new Board();
					page1.setBoard_smileTotal(rs.getInt("reaction"));
					page1.setBoard_update(rs.getString("board_update"));
					page1.setBoard_topic(rs.getString("board_topic"));
					page1.setBoard_id(rs.getInt("board_id"));
					int board_id = rs.getInt("board_id");

					// SQL文2を準備する Replyテーブル用
					//値が0だったら新着順で返す
					String sql_reply = "";
					if (push == 0) {
						sql_reply = "SELECT REPLY_DATE FROM REPLY WHERE BOARD_ID = ? ORDER BY REPLY_DATE DESC LIMIT 1";

					} else if (push == 1) {
						sql_reply = "SELECT REPLY_DATE FROM REPLY WHERE BOARD_ID = ? ORDER BY REPLY_DATE ASC LIMIT 1";
					}
					PreparedStatement pStmt_reply = conn.prepareStatement(sql_reply);

					//SQLを完成させる
					// ?にpage1のboard_idをセット
					pStmt_reply.setInt(1, rs.getInt("board_id"));

					//SQL文2を実行し結果表を取得
					ResultSet rsr = pStmt_reply.executeQuery();

					while (rsr.next()) {
						Reply page2 = new Reply();
						page2.setReply_date(rsr.getString("reply_date"));

						page1.getReply().add(page2);
					}

					//page1.getReply()が0件だったら
					if(page1.getReply().size() == 0) {
						Reply page2 = new Reply();
						page2.setReply_date(rs.getString("board_update"));

						page1.getReply().add(page2);
					}

					topListMain.add(page1);

				}

			} catch (SQLException e) {
				e.printStackTrace();
				topListMain = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				topListMain = null;
			} finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						topListMain = null;
					}
				}
			}

			return topListMain;
		}

		//見出し、投稿内容、返信の文字検索
		public List<Board> select(String word) {
			Connection conn = null;
			List<Board> topListMain = new ArrayList<Board>();
			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");

				// SQL文1を準備する Boardテーブル用
				String sql = " SELECT "
						+ "BOARD_TOPIC , BOARD_MAIN ,(BOARD_SMILE +  BOARD_SHOCK  + BOARD_TEAR ) AS REACTION , BOARD_UPDATE ,BOARD_ID "
						+ "FROM BOARD WHERE BOARD_TOPIC LIKE ? AND BOARD_MAIN  LIKE ? GROUP BY BOARD_TOPIC "
						+ "ORDER BY REACTION DESC";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setString(1, "%" + word + "%");
				pStmt.setString(2, "%" + word + "%");

				//SQL文2を準備する Replyテーブル用
				String sql_reply = "SELECT REPLY_DATE REPLY_MAIN FROM REPLY WHERE REPLY_MAIN LIKE ? ORDER BY REPLY_DATE DESC LIMIT 1";
				PreparedStatement pStmt_reply = conn.prepareStatement(sql_reply);

				//SQL文を完成させる
				pStmt_reply.setString(1, "%" + word + "%");

				//SQL文1を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				//select文の結果をArrayListに格納
				while (rs.next()) {
					Board page1 = new Board();
					page1.setBoard_topic(rs.getString("board_topic"));
					page1.setBoard_main(rs.getString("board_main"));
					page1.setBoard_smileTotal(rs.getInt("reaction"));
					page1.setBoard_update(rs.getString("board_update"));
					page1.setBoard_topic(rs.getString("board_topic"));
					page1.setBoard_id(rs.getInt("board_id"));
					int board_id = rs.getInt("board_id");

					//SQL文2を実行し、結果表を取得する
					ResultSet rsr = pStmt_reply.executeQuery();

					while (rsr.next()) {
						Reply page2 = new Reply();
						page2.setReply_main(rsr.getString("reply_main"));
						page2.setReply_date(rsr.getString("reply_date"));

						page1.getReply().add(page2);
					}
					topListMain.add(page1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				topListMain = null;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				topListMain = null;
			} finally {
				//データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						topListMain = null;
					}
				}
			}
			return topListMain;
		}

}