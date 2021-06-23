package Nakamura;


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
			conn = DriverManager.getConnection("jdbc:h2:file:C:\\pleiades\\workspace\\C-1\\database", "sa", "123");

			// SQL文を準備する
			String sql = "select * from user";
			PreparedStatement pStmt = conn.prepareStatement(sql);

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
				rs.getInt("user_nameCount"),
				rs.getString("user_update")
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
			conn = DriverManager.getConnection("jdbc:h2:file:C:\\pleiades\\workspace\\C-1\\database", "sa", "123");

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
				rs.getInt("user_nameCount"),
				rs.getString("user_update")
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


// カウント検索(昇順降順)
		public List<User> selectByCount(int key) {
			Connection conn = null;
			List<User> userList = new ArrayList<User>();

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:\\pleiades\\workspace\\C-1\\database", "sa", "123");

				// SQL文を準備する
				if(key == 1) {  //昇順
				    String sql = "select * from user  order by user_count asc ";
				    PreparedStatement pStmt = conn.prepareStatement(sql);
				// SQL文を実行し、結果表を取得する
					ResultSet rs = pStmt.executeQuery();
					while (rs.next()) {
						User user = new User(
						rs.getInt("user_id"),
						rs.getString("user_name"),
						rs.getString("user_pw"),
						rs.getInt("user_type"),
						rs.getString("user_mail"),
						rs.getInt("user_count"),
						rs.getInt("user_nameCount"),
						rs.getString("user_update")
						);
						userList.add(user);
					}
				}else {         //降順
					String sql = "select * from user  order by user_count desc ";
					PreparedStatement pStmt = conn.prepareStatement(sql);
				// SQL文を完成させる
				// SQL文を実行し、結果表を取得する
					ResultSet rs = pStmt.executeQuery();
					while (rs.next()) {
						User user = new User(
						rs.getInt("user_id"),
						rs.getString("user_name"),
						rs.getString("user_pw"),
						rs.getInt("user_type"),
						rs.getString("user_mail"),
						rs.getInt("user_count"),
						rs.getInt("user_nameCount"),
						rs.getString("user_update")
						);
						userList.add(user);
					}
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




		// 更新日時並び替え(昇順降順)
				public List<User> orderByDate(int key) {
					Connection conn = null;
					List<User> userList = new ArrayList<User>();
					try {
						// JDBCドライバを読み込む
						Class.forName("org.h2.Driver");
						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:h2:file:C:\\pleiades\\workspace\\C-1\\database", "sa", "123");
						// SQL文を準備する
						if(key == 1) {  //昇順
						    String sql = "select * from user order by user_update asc ";
						    PreparedStatement pStmt = conn.prepareStatement(sql);
						// SQL文を実行し、結果表を取得する
							ResultSet rs = pStmt.executeQuery();
							while (rs.next()) {
								User user = new User(
								rs.getInt("user_id"),
								rs.getString("user_name"),
								rs.getString("user_pw"),
								rs.getInt("user_type"),
								rs.getString("user_mail"),
								rs.getInt("user_count"),
								rs.getInt("user_nameCount"),
								rs.getString("user_update")
								);
								userList.add(user);
							}
						}else {         //降順
							String sql = "select * from user order by user_update desc ";
							PreparedStatement pStmt = conn.prepareStatement(sql);
						// SQL文を完成させる
						// SQL文を実行し、結果表を取得する
							ResultSet rs = pStmt.executeQuery();
							while (rs.next()) {
								User user = new User(
								rs.getInt("user_id"),
								rs.getString("user_name"),
								rs.getString("user_pw"),
								rs.getInt("user_type"),
								rs.getString("user_mail"),
								rs.getInt("user_count"),
								rs.getInt("user_nameCount"),
								rs.getString("user_update")
								);
								userList.add(user);
							}
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










				//引数userで指定されたレコードを登録し、成功したらtrueを返す(テスト用のプログラム）
			public boolean insert(User user) {
				Connection conn = null;
				boolean result = false;

				try {
					// JDBCドライバを読み込む
					Class.forName("org.h2.Driver");

					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:h2:file:C:\\pleiades\\workspace\\C-1\\database", "sa", "123");

					// SQL文を準備する
					String sql = "insert into USER values (null, ?, ?, ?, ?, ?, ?)";
					PreparedStatement pStmt = conn.prepareStatement(sql);

					// SQL文を完成させる
					pStmt.setInt(1, user.getUser_count());

					// SQL文を実行する
					if (pStmt.executeUpdate() == 3) {
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


// ドクロカウントを更新する
// 引数userで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(User user) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:\\pleiades\\workspace\\C-1\\database", "sa", "123");

			int count=user.getUser_count();

			if(count!=3) {
				// SQL文を準備する
				String sql = "update User set user_count=?, user_update=current_time where user_id=?";
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

			}else {//ドクロカウントが３だった場合にnameCountを一つ追加し、user_countを０にリセットする
				// SQL文を準備する
				String sql = "update User set user_count=0,user_nameCount=?, user_update=current_time where user_id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setInt(1, user.getUser_nameCount()+1);


				pStmt.setInt(2, user.getUser_id());

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}

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














