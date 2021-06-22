package Fukada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Board;
import model.Reply;

public class BoardfDAO {
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
			if(push ==0) {
				sql = "SELECT "
					+ "(BOARD_SMILE +  BOARD_SHOCK  + BOARD_TEAR ) AS REACTION , "
					+ "BOARD_UPDATE ,BOARD_TOPIC  "
					+ "FROM BOARD "
					+ "GROUP BY BOARD_TOPIC "
					+ "ORDER BY "
					+ "REACTION DESC";

			} else if(push==1) {
				sql = "SELECT "
					+ "(BOARD_SMILE +  BOARD_SHOCK  + BOARD_TEAR ) AS REACTION , "
					+ "BOARD_UPDATE ,BOARD_TOPIC  "
					+ "FROM BOARD "
					+ "GROUP BY BOARD_TOPIC "
					+ "ORDER BY "
					+ "REACTION ASC";
			 }
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文2を準備する Replyテーブル用
			//値が0だったら新着順で返す
			String sql_reply ="";
			if(push==0) {
			sql_reply = "SELECT REPLY_DATE FROM REPLY ORDER BY REPLY_DATE DESC LIMIT 1";

			}else if(push==1) {
			sql_reply = "SELECT REPLY_DATE FROM REPLY ORDER BY REPLY_DATE ASC LIMIT 1";
			}
			PreparedStatement pStmt_reply= conn.prepareStatement(sql_reply);

			//SQL文1を実行し結果表を取得
			ResultSet rs = pStmt.executeQuery();

			//select文の結果をArrayListに格納
			while (rs.next()) {
				Board page1 = new Board();
				page1.setBoard_smileTotal(rs.getInt("reaction"));
				page1.setBoard_update(rs.getString("board_update"));
				page1.setBoard_topic(rs.getString("board_topic"));

				//SQL文2を実行し結果表を取得
				ResultSet rsr = pStmt_reply.executeQuery();

				while (rsr.next()) {
					Reply page2 = new Reply();
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
			String sql = "SELECT BOARD_TOPIC , BOARD_MAIN FROM BOARD WHERE BOARD_TOPIC LIKE ?  AND BOARD_MAIN  LIKE ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, "%" + word + "%");
			pStmt.setString(2, "%" + word + "%");

			//SQL文2を準備する Replyテーブル用
			String sql_reply = "SELECT REPLY_MAIN FROM REPLY WHERE REPLY_MAIN LIKE ?";
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

				//SQL文2を実行し、結果表を取得する
				ResultSet rsr = pStmt_reply.executeQuery();

				while (rsr.next()) {
					Reply page2 = new Reply();
					page2.setReply_main(rsr.getString("reply_main"));

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