package application.kh.bms.controller;

import java.util.ArrayList;

import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.service.UserService;
import application.kh.bms.model.vo.User;

public class UserDeleteController {
	private UserService service = new UserService();

	//ÇØ¾ßÇÔ
	public int checkUser(String id, String pw) {
		int check = -1;
//		String nowUser = dao.getNowUser();
//		User temp = service.oneUserSelect(id);
//
//		if (temp.getPw().equals(pw)) {
//			if (users.get(i).getRetalList().isEmpty() == true) {
//				check = i;
//				break;
//			}
//		}
//
		return check;
	}

	public void deleteUser(String id) {
		service.deleteUser(id);
	}

}
