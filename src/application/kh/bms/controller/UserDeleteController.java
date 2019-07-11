package application.kh.bms.controller;

import java.util.ArrayList;

<<<<<<< HEAD
//import application.kh.bms.model.dao.LoadSave;
=======
>>>>>>> c3ab26791ef7cec3391a26e58c3c8e08c4a3d31c
import application.kh.bms.model.service.RentalService;
import application.kh.bms.model.service.UserService;
import application.kh.bms.model.vo.Rental;
import application.kh.bms.model.vo.User;

public class UserDeleteController {
	private UserService service = new UserService();

	private RentalService rentalService = new RentalService();

	// ÇØ¾ßÇÔ
	public int checkUser(String id, String pw) {
		int check = -1;
		User temp = service.oneUserSelect(id);
		ArrayList<Rental> list = rentalService.selectAll(id);
		if (temp.getPw().equals(pw)) {
			if (list.size() > 0) {
				check = 1;
			}
		}

		return check;
	}

	public void deleteUser(String id) {
		service.deleteUser(id);
	}

}
