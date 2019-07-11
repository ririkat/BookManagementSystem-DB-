package application.kh.bms.controller;

<<<<<<< HEAD
import java.util.ArrayList;

//import application.kh.bms.model.dao.LoadSave;
=======
import application.kh.bms.model.service.RentalService;
>>>>>>> 5c29d16638f518a66b8a4e1f7cc3f0d6f63d5241
import application.kh.bms.model.service.UserService;
import application.kh.bms.model.vo.User;

public class UserDeleteController {
	private UserService service = new UserService();
//	private RentalService rentalService = new 
	//ÇØ¾ßÇÔ
	public int checkUser(String id, String pw) {
		int check = -1;
//		User temp = service.oneUserSelect(id);
//		
//		if (temp.getPw().equals(pw)) {
//			if (users.get(i).getRetalList().isEmpty() == true) {
//				check = i;
//				break;
//			}
//		}

		return check;
	}

	public void deleteUser(String id) {
		service.deleteUser(id);
	}

}
