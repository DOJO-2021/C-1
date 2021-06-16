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
			public List<Board> topList () {
				Connection conn = null;
				List<Board> topListMain = new ArrayList<>();

				try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");

				// SQL文1を準備する Boardテーブル用
				String sql = "SELECT "
						+ "(BOARD_SMILE +  BOARD_SHOCK  + BOARD_TEAR ) AS REACTION , "
						+ "BOARD_UPDATE ,BOARD_TOPIC  "
						+ "FROM BOARD "
						+ "GROUP BY BOARD_TOPIC "
						+ "ORDER BY "
						+ "REACTION DESC";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文2を準備する Replyテーブル用
				String sql_reply ="SELECT REPLY_DATE FROM REPLY ORDER BY REPLY_DATE DESC";
				PreparedStatement pStmt_reply = conn.prepareStatement(sql_reply);

				//SQL文1を実行し結果表を取得
				ResultSet rs = pStmt.executeQuery();

				//select文の結果をArrayListに格納
					while (rs.next()) {
						Board page1 = new Board();
						//page1.setBoard_smile(rs.getInt("BOARD_SMILE"));
						//page1.setBoard_shock(rs.getInt("board_shock"));
						//page1.setBoard_tear(rs.getInt("board_tear"));
						page1.setBoard_smileTotal(rs.getInt("reaction"));
						page1.setBoard_update(rs.getString("board_update"));
						page1.setBoard_topic(rs.getString("board_topic"));

						//SQL文2を実行し結果表を取得
						ResultSet rsr = pStmt_reply.executeQuery();

						while(rsr.next()) {
							Reply page2 = new Reply();
							page2.setReply_date(rsr.getString("reply_date"));

							page1.getReply().add(page2);
						}
						topListMain.add(page1);
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
					topListMain = null;
				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
					topListMain = null;
				}
				finally {
					// データベースを切断
					if (conn != null) {
						try {
							conn.close();
						}
						catch (SQLException e) {
							e.printStackTrace();
							topListMain = null;
						}
					}
				}

				return topListMain;
			}

}