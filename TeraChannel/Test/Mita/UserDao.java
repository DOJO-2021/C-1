package Mita;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.LoginUser;

public class UserDao {

	// ログインできるならtrueを返す
	public boolean isLoginOK(String user_mail, String user_pw) {
			Connection conn = null;
			boolean loginResult = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");

				// SELECT文を準備する
				String sql = "select count(*) from user where user_mail = ? and user_pw = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, user_mail);
				pStmt.setString(2, user_pw);

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

	//スコープにid type name mailを保存
	public List<LoginUser> User(String user_mail, String user_pw) {
			Connection conn = null;
			List<LoginUser> UserList = new ArrayList<LoginUser>();

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "123");

				ResultSet rs;

				//SQL文を準備する
				String sql = "select user_id,user_type,user_name,user_mail from user where user_mail = ? and user_pw = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, user_mail);
				pStmt.setString(2, user_pw);
				//SQL文の実行
				rs = pStmt.executeQuery();

				while (rs.next()) {
					LoginUser us = new LoginUser(
							rs.getInt("user_id"),
							rs.getInt("user_type"),
							rs.getString("user_name"),
							rs.getString("user_mail")
							);
					UserList.add(us);
				}
			}catch (SQLException e) {
				e.printStackTrace();
				UserList = null;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				UserList = null;
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						UserList = null;
					}
				}
			}

			// 結果を返す
			return UserList;
		}
}