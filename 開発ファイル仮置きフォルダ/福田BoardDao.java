package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Board;

public class BoardDao {

	// ����card�Ŏw�肳�ꂽ���R�[�h��o�^���A����������true��Ԃ�
		public boolean insert(Board card) {	//�����̌��ʂ�true,false�ŕԂ�
			Connection conn = null;
			boolean result = false;

			try {
				// JDBC�h���C�o��ǂݍ���
				Class.forName("org.h2.Driver");

				// �f�[�^�x�[�X�ɐڑ�����
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/C-1/database", "sa", "");
				ResultSet = rs;
				//SQL������������	���{�@�\
				String sql = "SELECT * FROM(SELECT word FROM search) WHERE word like '%?%' or word like '%?%'";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				//SQL��������������
				if (card.getBoard_topic() != null && !card.getBoard_topic().equals("")) {
					pStmt.setString(1, card.getBoard_topic());
				}
				else {
					pStmt.setString(1, "null");
				}
				if (card.getBoard_main() != null && !card.getBoard_main().equals("")) {
					pStmt.setString(2, card.getBoard_main());
				}
				else {
					pStmt.setString(2, "null");
				}

				// SQL�������s����	����������������Ԃ��Ă����
				rs = pStmt.executeQuery();
				int x=rs.getRow();

				if (x = 0) {
				//insert�̕������ڂ�(45�s�ڂ���)
				// SQL������������	true
				String sql2 = "insert into board values (null,?,?,0,0,0,curent_date,?)";
				PreparedStatement pStmt2 = conn.prepareStatement(sql2);

				// SQL��������������		id�͎����̔�(����null)�Ȃ̂ŋL�q�s�v	�H�̈ʒu�Ɏ��ۂɑ}�����邽�߂̋L�q
				if (card.getBoard_topic() != null && !card.getBoard_topic().equals("")) {
					pStmt.setString(1, card.getBoard_topic());
				}
				else {
					pStmt.setString(1, "null");
				}
				if (card.getBoard_main() != null && !card.getBoard_main().equals("")) {
					pStmt.setString(2, card.getBoard_main());
				}
				else {
					pStmt.setString(2, "null");
				}
				//user�e�[�u������user_id���Q�ƁH	�Z�b�V�����X�R�[�v����H
				if (card.getUser_id() != null) {
					pStmt.setString(3, card.getUser_id());
				}
				else {
					pStmt.setString(3, "null");
				}

				// SQL�������s����	����������������Ԃ��Ă����
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			}	else {
					result = false;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				// �f�[�^�x�[�X��ؒf
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}


/*				}else {
					result = false;
				}
*/
/*				// SQL������������	true
				String sql = "insert into board values (null,?,?,0,0,0,curent_date,?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL��������������		id�͎����̔�(����null)�Ȃ̂ŋL�q�s�v	�H�̈ʒu�Ɏ��ۂɑ}�����邽�߂̋L�q
				if (card.getBoard_topic() != null && !card.getBoard_topic().equals("")) {
					pStmt.setString(1, card.getBoard_topic());
				}
				else {
					pStmt.setString(1, "null");
				}
				if (card.getBoard_main() != null && !card.getBoard_main().equals("")) {
					pStmt.setString(2, card.getBoard_main());
				}
				else {
					pStmt.setString(2, "null");
				}
//user�e�[�u������user_id���Q�ƁH	�Z�b�V�����X�R�[�v����H
				if (card.getUser_id() != null) {
					pStmt.setString(3, card.getUser_id());
				}
				else {
					pStmt.setString(3, "null");
				}

				// SQL�������s����	����������������Ԃ��Ă����
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				// �f�[�^�x�[�X��ؒf
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
*/

			// ���ʂ�Ԃ�
			return result;
		}


}
