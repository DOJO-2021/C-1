package Mita;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import model.Manager;
import java.util.ArrayList;
import java.util.List;

import model.LoginUser;
import model.Manager;

public class ManagerDao {
	public boolean isLoginOK(String manager_mail, String manager_pw) {
		Connection conn = null;
		boolean loginResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");

			// SELECT文を準備する
			String sql = "select count(*) from manager where manager_mail = ? and manager_pw = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, manager_mail);
			pStmt.setString(2, manager_pw);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// メールアドレスとパスワードが一致するユーザーがいたかどうかをチェックする
			rs.next();
			if (rs.getInt("count(*)") == 1) {
				loginResult = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			loginResult = false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			loginResult = false;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					loginResult = false;
				}
			}
		}

		// 結果を返す
		return loginResult;
	}
	//スコープにid,mail,pwを保存
		public List<Manager> User(String manager_mail, String manager_pw) {
				Connection conn = null;
				List<Manager> ManagerList = new ArrayList<Manager>();

				try {
					// JDBCドライバを読み込む
					Class.forName("org.h2.Driver");

					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");

					ResultSet rs;

					//SQL文を準備する
					String sql = "select manager_id,manager_mail,manager_pw from manager where manager_mail = ? and manager_pw = ?";
					PreparedStatement pStmt = conn.prepareStatement(sql);
					pStmt.setString(1, manager_mail);
					pStmt.setString(2, manager_pw);
					//SQL文の実行
					rs = pStmt.executeQuery();

					while (rs.next()) {
						Manager mg = new Manager(
								rs.getInt("manager_id"),
								rs.getString("manager_mail"),
								rs.getString("manager_pw")
								);
						ManagerList.add(mg);
					}
				}catch (SQLException e) {
					e.printStackTrace();
					ManagerList = null;
				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
					ManagerList = null;
				}
				finally {
					// データベースを切断
					if (conn != null) {
						try {
							conn.close();
						}
						catch (SQLException e) {
							e.printStackTrace();
							ManagerList = null;
						}
					}
				}

				// 結果を返す
				return ManagerList;
			}
}
