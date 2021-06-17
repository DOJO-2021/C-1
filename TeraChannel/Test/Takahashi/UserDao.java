package Takahashi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDao {
//userテーブルをすべて表示
//引数paramで検索項目を指定し、検索結果のリストを返す
	public List<User> select(User param) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1", "sa", "");

			// SQL文を準備する
			String sql = "select * from user";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (param.getUser_id() != 0) {
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
                  if (param.getUser_type() != 0) {
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
                  if (param.getUser_count() != 0) {
				pStmt.setString(6, "%" + param.getUser_count() + "%");
			}
			else {
				pStmt.setString(6, "%");
			}
                  if (param.getUser_nameCount() != 0) {
				pStmt.setString(7, "%" + param.getUser_nameCount() + "%");
			}
			else {
				pStmt.setString(7, "%");
			}

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				User user = new User(
				rs.getInt("user_id"),
				rs.getString("user_name"),
				rs.getString("user_pw"),
				rs.getInt("user_type"),
				rs.getString("user_mail"),
				rs.getInt("user_count"),
				rs.getInt("user_nameCount")
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

//ID検索
	public List<User> selectById(int user_id) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1", "sa", "");

			// SQL文を準備する
			String sql = "select * from user where user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, user_id);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				User user = new User(
				rs.getInt("user_id"),
				rs.getString("user_name"),
				rs.getString("user_pw"),
				rs.getInt("user_type"),
				rs.getString("user_mail"),
				rs.getInt("user_count"),
				rs.getInt("user_nameCount")
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

// カウント検索(昇順)
	public List<User> selectByCount(int user_count) {
		Connection conn = null;
		List<User> userList = new ArrayList<User>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1", "sa", "");

			// SQL文を準備する
			String sql = "select * from user where user_count order by asc = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, user_count);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				User user = new User(
				rs.getInt("user_id"),
				rs.getString("user_name"),
				rs.getString("user_pw"),
				rs.getInt("user_type"),
				rs.getString("user_mail"),
				rs.getInt("user_count"),
				rs.getInt("user_nameCount")
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

// カウント検索(降順)
		public List<User> selectByCount2(int key, int user_count2) {
			Connection conn = null;
			List<User> userList = new ArrayList<User>();

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1", "sa", "");

				// SQL文を準備する
				String sql = "select * from user where user_count order by desc = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1, user_count2);

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					User user = new User(
					rs.getInt("user_id"),
					rs.getString("user_name"),
					rs.getString("user_pw"),
					rs.getInt("user_type"),
					rs.getString("user_mail"),
					rs.getInt("user_count"),
					rs.getInt("user_nameCount")
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

// ドクロカウントを更新する
// 引数userで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(User user) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1", "sa", "");

			// SQL文を準備する
			String sql = "update User set user_count=? where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (user.getUser_count() != 0) {
				pStmt.setInt(1, user.getUser_count());
			}
			else {
				pStmt.setString(1, "null");
			}
			pStmt.setInt(2, user.getUser_id());

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
}