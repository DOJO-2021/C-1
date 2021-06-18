package Takahashi;
import java.util.List;

import model.User;

public class UserDaoTest {
    public static void main(String[] args) {
    	UserDao2 dao = new UserDao2();

    	// select()のテスト
    	System.out.println("---------- select()のテスト ----------");
        List<User> userList = dao.select(new User( 0, "", "", 0, "", 0, 0));
    	for (User user : userList) {
			System.out.println(user.getUser_id());
			System.out.println(user.getUser_name());
			System.out.println(user.getUser_pw());
			System.out.println(user.getUser_type());
			System.out.println(user.getUser_mail());
			System.out.println(user.getUser_count());
			System.out.println(user.getUser_nameCount());
			System.out.println();
			System.out.println();



		// update()のテスト
		System.out.println("---------- update()のテスト ----------");
		User upRec = new User(0,"TEST", "TEST", 0, "TEST", 2, 0);
			if (dao.update(upRec)) {
				System.out.println("更新成功！");
			}
			else {
				System.out.println("更新失敗！");
			}












    	}






    }
}
