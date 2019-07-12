package application.kh.bms.controller;

import application.kh.bms.model.dao.InformationManager;
import application.kh.bms.model.service.UserService;
import application.kh.bms.model.vo.User;

public class LoginController {
	private UserService service = new UserService();

	public int checkLogin(String id, String pw) {
		int check = 1;
		User temp = service.oneUserSelect(id);
		if (temp != null) {
			if (temp.getPw().equals(pw)) {
				check = 3;
				InformationManager.getInformationManager().setNowUser(temp);
			} else {
				check = 2;
			}
		}
		return check;
	}
}
