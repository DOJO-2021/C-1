import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

	public class BoardDao {
		// 引数paramで検索項目を指定し、投稿、返信の検索結果のリストを返す
			public List<Board> findList (Board param1,Reply param1) {
				Connection conn = null;
				List<Board> findBoardList = new ArrayList<Board>():
				//入れ込む？
				//List<Reply> findReplyList = new ArrayList<Reply>();
			
				try {
					// JDBCドライバを読み込む
					Class.forName("org.h2.Driver");

					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C-1", "sa", "");

					// SQL文を準備する
					String sql = ("select board_topic, board_main from board where board_topic like ? 
												and board_main like ?"; + "select reply_main from reply where reply_main like ?"; );
					
					PreparedStatement pStmt = conn.prepareStatement(sql);

					// SQL文を完成させる
					if (param.getBoard_topic() !=
				}
			}
		}



	// reaction合計のリアクションの数を格納し降順で返す
	



	
	
	// update all投稿、返信合わせての更新日を格納し降順で返す



	// topic見出しを表示する
	// reactionとupdate allの値を変数に入れてリターンして返す