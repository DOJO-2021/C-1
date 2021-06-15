package test;

public class BoardDAOTest {
	public static void main(String[] args) {
		BoardDAO dao = new BoardDAO();

		//insert()	“Še‚ÌƒeƒXƒg	¬Œ÷‚É‚È‚ê‚ÎOK
		System.out.println("----insert()‚ÌƒeƒXƒg----");
		Board insRec = new Board(0,"test","test",0,0,0,"current_timestamp",0);
		if (dao.insert(insRec)) {
			System.out.println("“Še¬Œ÷");
		}
		else {
			System.out.println("“Še¸”s");
		}

		//insert()	“ŠeEtopic‚ÌŒŸ‰{‚ÌƒeƒXƒg	¸”s‚É‚È‚ê‚ÎOK
		System.out.println("----insert()“ŠeEtopic‚ÌŒŸ‰{‚ÌƒeƒXƒg----");
		Board insRec2 = new Board(0,"ƒoƒJ","test",0,0,0,"current_timestamp",0);
		if (dao.insert(insRec2)) {
			System.out.println("“Še¬Œ÷");
		}
		else {
			System.out.println("“Še¸”s");
		}

		//insert()	“ŠeEmain‚ÌŒŸ‰{‚ÌƒeƒXƒg	¸”s‚É‚È‚ê‚ÎOK
		System.out.println("----insert()“ŠeEmain‚ÌŒŸ‰{‚ÌƒeƒXƒg----");
		Board insRec3 = new Board(0,"test","‚ ‚Ù",0,0,0,"current_timestamp",0);
		if (dao.insert(insRec3)) {
			System.out.println("“Še¬Œ÷");
		}
		else {
			System.out.println("“Še¸”s");
		}
	}
}
