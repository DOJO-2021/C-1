package Fukada;

import java.util.List;

import model.Board;
import model.Reply;

public class BoardDAOTest {
	public static void main(String[] args) {
		BoardfDAO dao = new BoardfDAO();

		//topList()のテスト
		System.out.println("--topList()のテスト--");
		List<Board> topListMain = dao.topList();
		for (Board board : topListMain) {
			System.out.println(board.getBoard_smileTotal());
			//System.out.println(board.getBoard_shock());
			//System.out.println(board.getBoard_tear());
			System.out.println(board.getBoard_update());
			System.out.println(board.getBoard_topic());
			System.out.println();
			for (Reply reply : board.getReply()) {
				System.out.println(reply.getReply_date());
				System.out.println();
			}
			System.out.println();
		}
	}
}
