package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Board;

public class BoardDao {

	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
		public boolean insert(Board card) {	//処理の結果をtrue,falseで返す
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "");
				ResultSet = rs;
				//SQL文を準備する	検閲機能
				String sql = "SELECT * FROM(SELECT word FROM search) WHERE word like '%?%' or word like '%?%'";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				//SQL文を完成させる
				if (card.getBoard_topic() != null && !card.getBoard_topic().equals("")) {
					pStmt.setString(1, card.getBoard_topic());
				}
				else {
					pStmt.setString(1, "null");
				}
				if (card.getBoard_main() != null && !card.getBoard_main().equals("")) {
					pStmt.setString(2, card.getBoard_main());
				}
				else {
					pStmt.setString(2, "null");
				}

				// SQL文を実行する	何件処理したかを返してくれる
				rs = pStmt.executeQuery();
				int x=rs.getRow();

				if (x = 0) {
				//insertの部分を移す(45行目から)
				// SQL文を準備する	true
				String sql2 = "insert into board values (null,?,?,0,0,0,curent_date,?)";
				PreparedStatement pStmt2 = conn.prepareStatement(sql2);

				// SQL文を完成させる		idは自動採番(元がnull)なので記述不要	？の位置に実際に挿入するための記述
				if (card.getBoard_topic() != null && !card.getBoard_topic().equals("")) {
					pStmt.setString(1, card.getBoard_topic());
				}
				else {
					pStmt.setString(1, "null");
				}
				if (card.getBoard_main() != null && !card.getBoard_main().equals("")) {
					pStmt.setString(2, card.getBoard_main());
				}
				else {
					pStmt.setString(2, "null");
				}
				//userテーブルからuser_idを参照？	セッションスコープから？
				if (card.getUser_id() != null) {
					pStmt.setString(3, card.getUser_id());
				}
				else {
					pStmt.setString(3, "null");
				}

				// SQL文を実行する	何件処理したかを返してくれる
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			}	else {
					result = false;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}


/*				}else {
					result = false;
				}
*/
/*				// SQL文を準備する	true
				String sql = "insert into board values (null,?,?,0,0,0,curent_date,?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる		idは自動採番(元がnull)なので記述不要	？の位置に実際に挿入するための記述
				if (card.getBoard_topic() != null && !card.getBoard_topic().equals("")) {
					pStmt.setString(1, card.getBoard_topic());
				}
				else {
					pStmt.setString(1, "null");
				}
				if (card.getBoard_main() != null && !card.getBoard_main().equals("")) {
					pStmt.setString(2, card.getBoard_main());
				}
				else {
					pStmt.setString(2, "null");
				}
//userテーブルからuser_idを参照？	セッションスコープから？
				if (card.getUser_id() != null) {
					pStmt.setString(3, card.getUser_id());
				}
				else {
					pStmt.setString(3, "null");
				}

				// SQL文を実行する	何件処理したかを返してくれる
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
*/

			// 結果を返す
			return result;
		}


}
