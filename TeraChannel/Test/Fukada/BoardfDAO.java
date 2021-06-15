package Fukada;
import model.Board;
import model.Reply;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BoardfDao {

	public class BoardDao {
		// 引数searchで検索項目を指定し、投稿、返信の検索結果のリストを返す
		public List<Board> findCardList (String search) {
			Connection conn = null;
			List<Board> findList = new ArrayList<Boaed>():

			try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C-1", "sa", "");

			// SQL文を準備する
			String sql = "";
			PreparedStatement pStmt = conn.prepareStatement(sql);


			//SQL文を実行し、結果表を表示する
			ResultSet rs =pStmt.executeQuery();

			//結果表をコレクションにコピーする
				while (rs.next()) {
					Board list = new Board(
					rs.getString("board_topic"),
					rs.getString("board_main"),
					rs.getString("reply_main")
					);
					findList.add(list);
				}
			}
			catch (SQLException e) {
					e.printStackTrace();
					findList = null;
			}
			catch (ClassNotFoundException e) {
					e.printStackTrace();
					findList = null;
			}
			finally {
			// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						findList = null;
					}
				}
			}
			// 結果を返す
			return findList;
		}


			// reaction合計の数を格納し降順で返す
			// update all投稿、返信合わせての更新日を格納し降順で返す
			// 上記を含めてた上でtopic見出しを表示する
			public List<Board> topList (String main) {
				Connection conn = null;
				List<Board> topListMain = new ArrayList<>():

				try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C-1", "sa", "");

				// SQL文を準備する
					//reaction合計
				String sql1 = "SELECT SUM(SMILE + SHOCK  + TEAR )  AS REACTION FROM BOARD GROUP BY ID ORDER BY SUM(SMILE + SHOCK  + TEAR ) DESC;";
				PreparedStatement pStmt1 = conn.prepareStatement(sql1);
					//投稿、返信合わせての更新日
				String sql2 = "SELECT B.UPDATE  ,R.DATE FROM BOARD  AS B RIGHT JOIN REPLY AS R ON B.ID = R.ID GROUP BYB.ID,R.ID ORDER BY B.UPDATE  ,R.DATE DESC";
				PreparedStatement pStmt2 = conn.prepareStatement(sql2);
					//topic見出し
				String sql3 = "SELECT TOPIC FROM BOARD GROUP BY ID ORDER BY TOPIC DESC;";
				PreparedStatement pStmt3 = conn.prepareStatement(sql3);

				//serect文の結果を実行
				ResultSet rs1 = pStmt1.executeQuery();
				ResultSet rs2 = pStmt2.executeQuery();
				ResultSet rs3 = pStmt3.executeQuery();

				//select文の結果をArrayListに格納
					while (rs1,rs2,rs3.next()) {

					}
				}

			}

}