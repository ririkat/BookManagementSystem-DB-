package application.kh.bms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.User;

public class RegisterController {
	private LoadSave dao = LoadSave.getDao();
	private ArrayList<User> temp = dao.loadUser();

	public boolean checkedId(String id) {
		boolean check = true;
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getId().equals(id)) {
				check = false;
				break;
			}
		}
		return check;
	}

	public boolean checkedPw(String pw, String pw2) {
		if (pw.isEmpty() || pw2.isEmpty()) {
			return false;
		} else {
			if ((pw.equals(pw2)) && (pw.length() >= 8 && pw.length() <= 12)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean nullCheck(String id, String pw, String pw2, String name, String gender, String phone, String addr) {
		boolean bool = true;
		// --------------------------------------------------------
		if ((id.isEmpty()) || (pw.isEmpty()) || (pw2.isEmpty()) || (name.isEmpty()) || (gender.equals("성별"))
				|| (addr.isEmpty()) || (phone.isEmpty()))
		// --------------------------------------------------------gender.equals 성별로 바꿈
		{
			bool = false;
		}
		return bool;

	}

	public boolean adminCheck(String adminNum) {
		if (adminNum.equals("김류서서송오정")) {
			return true;
		} else {
			return false;
		}
	}

	public void addUser(String regId, String regPw, String regName, String regGender, String regAddr, String regphone,
			boolean adChk) {
		temp.add(new User(regId, regPw, regName, regGender, regAddr, regphone, adChk));
		dao.saveUser(temp);
	}

	// ------------------------------------------------------------
	public boolean idEnglishCheck(String inputID) {
		boolean isEnglish = true;
		for (int i = 0; i < inputID.length(); i++) {
			if (!((inputID.charAt(i) >= 'a' && inputID.charAt(i) <= 'z')
					|| (inputID.charAt(i) >= 'A' && inputID.charAt(i) <= 'Z'))) {
				isEnglish = false;
				break;
			}
		}
		return isEnglish;
	}
	// ------------------------------------------------------------
}
