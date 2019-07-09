package application.kh.bms.controller;

import application.kh.bms.model.service.UserService;
import application.kh.bms.model.vo.User;

public class LoginController {
	private UserService service = new UserService();
//	public int checkLogin(String id, String pw) {
//		int check = -1;
//		ArrayList<User> temp = dao.loadUser();
//		for(int i=0; i<temp.size(); i++) {
//			if(temp.get(i).getId().equals(id)) {
//				if(temp.get(i).getPw().equals(pw)){
//					check = i;
//					break;
//				}
//			}
//		}
//		return check;
//	}

	public int checkLogin(String id, String pw) {
		int check = 1;
		User temp = service.oneUserSelect(id);
		if (temp != null) {
			if (temp.getPw().equals(pw)) {
				check = 3;
			} else {
				check = 2;
			}
		}
		return check;
	}
}
