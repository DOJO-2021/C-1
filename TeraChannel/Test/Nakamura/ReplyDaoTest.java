package Nakamura;

import java.util.List;

public class ReplyDaoTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		ReplyDao rDao=new ReplyDao();

		//返信一覧を取ってくるメソッドのテスト
		//List<Reply> replyList =rDao.showReply(1);

		//検索された文字を含む返信のみを取ってくるメソッドのテスト
		//List<Reply> replyList =rDao.showSelectedReply("テスト");

		//返信の登録ができたかを確認するメソッドのテスト
		/*if(rDao.insertReply(new Reply(0,"返信テスト３","使わないパラメータ",1,1))) {
			System.out.println("登録に成功しました");
		}else {
			System.out.println("登録に失敗しました");
		}
		*/

		/*if(rDao.editReply("返信1を編集しました",1)) {
			System.out.println("編集に成功しました");
		}else {
			System.out.println("編集に失敗しました");
		}*/

		//削除メソッドのテスト
		/*if(rDao.deleteReply(4)) {
			System.out.println("削除に成功しました");
		}else {
			System.out.println("削除に失敗しました");
		}*/



		//返信一覧を取ってくるメソッドのテスト
			List<Reply> replyList =rDao.showReply(1);

		for (Reply rep : replyList) {
			System.out.println("返信ID：" + rep.getReply_id());
			System.out.println("返信内容：" +rep.getReply_main());
			System.out.println("返信日時:"+rep.getReply_date());
			System.out.println("ユーザーID:"+rep.getUser_id());
			System.out.println("投稿ID：" +rep.getBoard_id());
			System.out.println();

		}
	}

}
