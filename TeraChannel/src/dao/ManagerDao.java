package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDao {

		// ログインできるならtrueを返す
		public boolean isLoginOK(String id, String pw) {
			Connection conn = null;
			boolean loginResult = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1", "sa", "");

				// SELECT文を準備する
				String sql = "select count(*) from IDPW where ID = ? and PW = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, id);
				pStmt.setString(2, pw);

				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// ユーザーIDとパスワードが一致するユーザーがいたかどうかをチェックする
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

}


//引数paramで検索項目を指定し、検索結果のリストを返す
	public List<Tc> select(Tc param) {
		Connection conn = null;
		List<Tc> user = new ArrayList<Tc>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1", "sa", "");

			// SQL文を準備する
			String sql = "select user_id, user_name, user_pw, user_type, user_mail, user_count, user_nameCount, from user";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (param.getUser_id() != null) {
				pStmt.setString(1, "%" + param.getUser_id() + "%");
			}
			else {
				pStmt.setString(1, "%");
			}
			if (param.getUser_name() != null) {
				pStmt.setString(2, "%" + param.getUser_name() + "%");
			}
			else {
				pStmt.setString(2, "%");
			}
			if (param.getUser_pw() != null) {
				pStmt.setString(3, "%" + param.getUser_pw() + "%");
			}
			else {
				pStmt.setString(3, "%");
			}
                    if (param.getUser_type() != null) {
				pStmt.setString(4, "%" + param.getUser_type() + "%");
			}
			else {
				pStmt.setString(4, "%");
			}
                    if (param.getUser_mail() != null) {
				pStmt.setString(5, "%" + param.getUser_mail() + "%");
			}
			else {
				pStmt.setString(5, "%");
			}
                    if (param.getUser_count() != null) {
				pStmt.setString(6, "%" + param.getUser_count() + "%");
			}
			else {
				pStmt.setString(6, "%");
			}
                    if (param.getUser_nameCount() != null) {
				pStmt.setString(7, "%" + param.getUser_nameCount() + "%");
			}
			else {
				pStmt.setString(7, "%");
			}

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Tc user = new Tc(
				rs.getInt("user_id"),
				rs.getString("user_name"),
				rs.getString("user_pw"),
				rs.getInt("user_type"),
				rs.getString("user_mail"),
				rs.getInt("user_count"),
				rs.getInt("user_nameCount"),
				);
				userList.add(user);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			userList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			userList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					userList = null;
				}
			}
		}

		// 結果を返す
		return userList;
	}



	// 引数userで指定されたレコードを更新し、成功したらtrueを返す
		public boolean update(Tc user) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1", "sa", "");

				// SQL文を準備する
				String sql = "update TC set user_count=? where id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (user.getUser_count() != null) {
					pStmt.setString(1, user.getUser_count());
				}
				else {
					pStmt.setString(1, "null");
				}
				pStmt.setInt(2, user.getId());

				// SQL文を実行する
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

			// 結果を返す
			return result;
		}
