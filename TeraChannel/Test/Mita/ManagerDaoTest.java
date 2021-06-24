package Mita;

import java.util.List;

import Mita.ManagerDao;
import model.Manager;

public class ManagerDaoTest {
	public static void main(String[] args) {
		testIsLoginOK1(); // ユーザーが見つかる場合のテスト
		testIsLoginOK2(); // ユーザーが見つからない場合のテスト

		ManagerDao mDao = new ManagerDao();

		List<Manager> ManagerList = mDao.User("akita@example.com", "akita");

		for (Manager mg : ManagerList) {
			System.out.println("ID：" + mg.getManager_id());
			System.out.println("メール：" + mg.getManager_mail());
			System.out.println("パスワード:" + mg.getManager_pw());

		}
	}

	// ユーザーが見つかる場合のテスト
	public static void testIsLoginOK1() {
		ManagerDao dao = new ManagerDao();
		if (dao.isLoginOK("akita@example.com", "akita")) {
			System.out.println("testIsLoginOK1：テストが成功しました");
		} else {
			System.out.println("testIsLoginOK1：テストが失敗しました");
		}
	}

	// ユーザーが見つからない場合のテスト
	public static void testIsLoginOK2() {
		ManagerDao dao = new ManagerDao();
		if (!dao.isLoginOK("DOJO", "pass")) {
			System.out.println("testIsLoginOK2：テストが成功しました");
		} else {
			System.out.println("testIsLoginOK2：テストが失敗しました");
		}
	}
}
