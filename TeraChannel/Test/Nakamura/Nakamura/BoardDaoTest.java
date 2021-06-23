package Nakamura;

public class BoardDaoTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		BoardDao bDao=new BoardDao();
		//編集メソッドのテスト
		/*if(bDao.editBoard("編集しました（中村）",1)) {
			System.out.println("編集に成功しました");
		}else {
			System.out.println("編集に失敗しました");

		}*/

		//削除メソッドのテスト
		/*if(bDao.deleteBoard(3)) {
			System.out.println("削除に成功しました");
		}else {
			System.out.println("削除に失敗しました");

		}*/

		//リアクション登録メソッドのテスト
		//仕様として、ボタンが押された際に現在のすべてのリアクションの値をservletで
		//リクエストスコープに格納し、引数に用いる
		if(bDao.registReaction(1,1,2,3)) {
			System.out.println("リアクションの更新に成功しました");
		}else {
			System.out.println("リアクションの更新に失敗しました");

		}


		Board bd=bDao.showBoard(1);

		System.out.println("投稿ID："+bd.getBoard_id());
		System.out.println("投稿タイトル："+bd.getBoard_topic());
		System.out.println("投稿内容："+bd.getBoard_main());
		System.out.println("スマイル数："+bd.getBoard_smile());
		System.out.println("驚き数："+bd.getBoard_shock());
		System.out.println("悲しみ数："+bd.getBoard_tear());
		System.out.println("投稿日時："+bd.getBoard_update());
		System.out.println("ユーザーID："+bd.getUser_id());

	}

}
