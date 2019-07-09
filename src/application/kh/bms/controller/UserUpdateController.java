package application.kh.bms.controller;

import java.util.ArrayList;

import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.service.UserService;
import application.kh.bms.model.vo.User;

public class UserUpdateController {
	private UserService service = new UserService();

	// ArrayList를 이용하여 수정된 유저의 정보를 파라미터로 받아와 해당 userNo에 Set 함
	public void updateUser(String id, String name, String addr, String gender, String phone) {
		User temp = new User();
		temp.setId(id);
		temp.setName(name);
		temp.setAddr(addr);
		temp.setGender(gender);
		temp.setPhone(phone);

		service.updateUser(temp);
	}

	// 설정한 PW 의 길이 8~12자 체크
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
