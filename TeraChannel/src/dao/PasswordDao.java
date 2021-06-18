package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Password;



public class PasswordDao {

	// 引数userで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(Password user) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "");
			// SQL文を準備する
			String sql = "update User set user_pw = ? where user_mail = '?' ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を完成させる
			if (user.getNew_pw() != null) {
				pStmt.setString(1, user.getNew_pw());
			} else {
				pStmt.setString(1, "null");
			}
			if (user.getUser_mail() != null) {
				pStmt.setString(2, user.getUser_mail());
			} else {
				pStmt.setString(2, "null");
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
