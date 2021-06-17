package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class PasswordDao {

	// 引数paramで検索項目を指定し、検索結果のリストを返す
	public List<User> select(User param) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "");

			// SQL文を準備する
			String sql = "select user_pw "
					+ "from User "
					+ "WHERE user_pw like ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (param.getUser_pw() != null) {
				pStmt.setString(1, "%" + param.getUser_pw() + "%");
			} else {
				pStmt.setString(1, "%");
			}

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				//User user = new User( rs.getString("user_pw") );
				User user = new User();
				user.setUser_pw(rs.getString("user_pw"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			userList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			userList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					userList = null;
				}
			}
		}

		// 結果を返す
		return userList;
	}

	// 引数userで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(User user) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "");

			// SQL文を準備する
			String sql = "insert into User values (?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (user.getUser_pw() != null) {
				pStmt.setString(1, user.getUser_pw());
			} else {
				pStmt.setString(1, "null");
			}

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
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

	// 引数userで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(User user) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "");
			// SQL文を準備する
			String sql = "update User set user_pw = ? where user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を完成させる
			if (user.getUser_pw() != null) {
				pStmt.setString(1, user.getUser_pw());
			} else {
				pStmt.setString(1, "null");
			}

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
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
}
