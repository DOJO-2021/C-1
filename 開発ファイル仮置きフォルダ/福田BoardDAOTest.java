package test;

public class BoardDAOTest {
	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();

		//insert()	���e�̃e�X�g	�����ɂȂ��OK
		System.out.println("----insert()�̃e�X�g----");
		Board insRec = new Board(0,"test","test",0,0,0,"current_timestamp",0);
		if (dao.insert(insRec)) {
			System.out.println("���e����");
		}
		else {
			System.out.println("���e���s");
		}

		//insert()	���e�Etopic�̌��{�̃e�X�g	���s�ɂȂ��OK
		System.out.println("----insert()���e�Etopic�̌��{�̃e�X�g----");
		Board insRec2 = new Board(0,"�o�J","test",0,0,0,"current_timestamp",0);
		if (dao.insert(insRec2)) {
			System.out.println("���e����");
		}
		else {
			System.out.println("���e���s");
		}

		//insert()	���e�Emain�̌��{�̃e�X�g	���s�ɂȂ��OK
		System.out.println("----insert()���e�Emain�̌��{�̃e�X�g----");
		Board insRec3 = new Board(0,"test","����",0,0,0,"current_timestamp",0);
		if (dao.insert(insRec3)) {
			System.out.println("���e����");
		}
		else {
			System.out.println("���e���s");
		}
	}
}
