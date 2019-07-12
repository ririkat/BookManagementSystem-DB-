package application.kh.bms.controller;

import java.util.ArrayList;

//import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.service.UserService;
import application.kh.bms.model.vo.User;

public class UserUpdateController {
	private UserService service = new UserService();

	public void updateUser(String id, String name, String addr, String gender, String phone) {
		User temp = new User();
		temp.setId(id);
		temp.setName(name);
		temp.setAddr(addr);
		temp.setGender(gender);
		temp.setPhone(phone);

		service.updateUser(temp);
	}

	public boolean pwCheck(String pw) {
		if (pw.length() >= 8 && pw.length() <= 12) {
			return true;
		} else {
			return false;
		}
	}

	public User loadData(String id) {
		User temp = service.oneUserSelect(id);
		return temp;
	}
}
