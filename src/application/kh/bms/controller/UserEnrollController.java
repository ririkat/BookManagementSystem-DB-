package application.kh.bms.controller;

import application.kh.bms.model.service.UserService;
import application.kh.bms.model.vo.User;

public class UserEnrollController {

	private UserService service = new UserService();

	// 아이디 체크
	public boolean checkedId(String id) {
		boolean check = true;
		User temp = service.oneUserSelect(id);
		if (temp != null) {
			check = false;
		}
		return check;
	}

	// 비번 체크
	public boolean checkPw(String pw) {
		if (pw.length() >= 8 && pw.length() <= 12) {
			return true;
		} else {
			return false;
		}
	}

	// 빈칸체크
	public boolean checkNull(String id, String pw, String name, String gender, String phone, String addr) {
		boolean bool = true;
		// ------------------------------------------
		if ((id.isEmpty()) || (pw.isEmpty()) || (name.isEmpty()) || (gender.equals("성별")) || (addr.isEmpty())
				|| (phone.isEmpty())) {
			// ------------------------------------------
			bool = false;
		}
		return bool;
	}

	// 관리자체크
	public boolean adminCheck(String adminNum) {
		if (adminNum.equals("김류서서송오정")) {
			return true;
		} else {
			return false;
		}
	}

	// 유저추가
	public void addUser(String regId, String regPw, String regName, String regGender, String regAddr, String regphone,
			boolean adChk) {
		User temp = new User(regId, regPw, regName, regGender, regAddr, regphone, adChk);
		service.addUser(temp);

	}

}
