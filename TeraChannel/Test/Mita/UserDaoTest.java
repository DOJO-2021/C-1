package Mita;

import java.util.List;

import Mita.UserDao;
import model.LoginUser;

public class UserDaoTest {
	public static void main(String[] args) {
		testIsLoginOK1(); // ユーザーが見つかる場合のテスト
		testIsLoginOK2(); // ユーザーが見つからない場合のテスト
		//スコープにあるかテスト

		UserDao uDao = new UserDao();

		List<LoginUser> UserList = uDao.User("hokkaidou@example.com", "hokkaidou");

		for (LoginUser us : UserList) {
			System.out.println("ID：" + us.getUser_id());
			System.out.println("タイプ：" + us.getUser_type());
			System.out.println("名前:" + us.getUser_name());
			System.out.println("メール:" + us.getUser_mail());
		}
	}

	// ユーザーが見つかる場合のテスト
	public static void testIsLoginOK1() {
		UserDao dao = new UserDao();
		if (dao.isLoginOK("hokkaidou@example.com", "hokkaidou")) {
			System.out.println("testIsLoginOK1：テストが成功しました");
		} else {
			System.out.println("testIsLoginOK1：テストが失敗しました");
		}
	}

	// ユーザーが見つからない場合のテスト
	public static void testIsLoginOK2() {
		UserDao dao = new UserDao();
		if (!dao.isLoginOK("sample@example.com", "password")) {
			System.out.println("testIsLoginOK2：テストが成功しました");
		} else {
			System.out.println("testIsLoginOK2：テストが失敗しました");
		}
	}

}
