package Fukuda;

import model.Board;

public class BoardDAOTest {
	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();

		//insert()	投稿のテスト	成功になればOK
		System.out.println("----insert()のテスト----");
		Board insRec = new Board(0, "ばか", "test", 0, 0, 0, "current_timestamp", 0);
		int test = dao.insert1(insRec);
		if (test != 0 ) {
			System.out.println("投稿成功");
			System.out.println(test);
		} else {
			System.out.println("投稿失敗");
			System.out.println(test);
		}

		/*
		//insert()	投稿・topicの検閲のテスト	失敗になればOK
		System.out.println("----insert()投稿・topicの検閲のテスト----");
		Board insRec2 = new Board(0, "バカやろう", "test", 0, 0, 0, "current_timestamp", 0);
		if (dao.insert(insRec2)) {
			System.out.println("投稿成功");
		} else {
			System.out.println("投稿失敗");
		}

		//insert()	投稿・mainの検閲のテスト	失敗になればOK
		System.out.println("----insert()投稿・mainの検閲のテスト----");
		Board insRec3 = new Board(0, "test", "あほ", 0, 0, 0, "current_timestamp", 0);
		if (dao.insert(insRec3)) {
			System.out.println("投稿成功");
		} else {
			System.out.println("投稿失敗");
		}

		//insert()	投稿・topic/mainの検閲のテスト	失敗になればOK
		System.out.println("----insert()投稿・topic/mainの検閲のテスト----");
		Board insRec4 = new Board(0, "バカ", "あほ", 0, 0, 0, "current_timestamp", 0);
		if (dao.insert(insRec4)) {
			System.out.println("投稿成功");
		} else {
			System.out.println("投稿失敗");
		}
		*/
	}
}
