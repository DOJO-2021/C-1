import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

	public class BoardDao {
		// ����param�Ō������ڂ��w�肵�A���e�A�ԐM�̌������ʂ̃��X�g��Ԃ�
			public List<Board> findList (Board param1,Reply param1) {
				Connection conn = null;
				List<Board> findBoardList = new ArrayList<Board>():
				//���ꍞ�ށH
				//List<Reply> findReplyList = new ArrayList<Reply>();
			
				try {
					// JDBC�h���C�o��ǂݍ���
					Class.forName("org.h2.Driver");

					// �f�[�^�x�[�X�ɐڑ�����
					conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/C-1", "sa", "");

					// SQL������������
					String sql = ("select board_topic, board_main from board where board_topic like ? 
												and board_main like ?"; + "select reply_main from reply where reply_main like ?"; );
					
					PreparedStatement pStmt = conn.prepareStatement(sql);

					// SQL��������������
					if (param.getBoard_topic() !=
				}
			}
		}



	// reaction���v�̃��A�N�V�����̐����i�[���~���ŕԂ�
	



	
	
	// update all���e�A�ԐM���킹�Ă̍X�V�����i�[���~���ŕԂ�



	// topic���o����\������
	// reaction��update all�̒l��ϐ��ɓ���ă��^�[�����ĕԂ�