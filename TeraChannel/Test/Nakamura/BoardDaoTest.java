package Nakamura;

public class BoardDaoTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		BoardDao bDao=new BoardDao();
		/*if(bDao.editBoard("編集しました（中村）",1)) {
			System.out.println("編集に成功しました");
		}else {
			System.out.println("編集に失敗しました");

		}*/
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
