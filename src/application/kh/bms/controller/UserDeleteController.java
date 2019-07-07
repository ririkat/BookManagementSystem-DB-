package application.kh.bms.controller;

import java.util.ArrayList;

import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.User;

public class UserDeleteController {

	private LoadSave dao = LoadSave.getDao();
	private ArrayList<User> users=dao.loadUser();

	//	//아이디 체크
	//	public boolean checkedId(String id) {
	//		boolean check=true;
	//		for(int i=0; i<dao.loadUser().size(); i++) {
	//			if(dao.loadUser().get(i).getId().equals(id)) {
	//				check=false;
	//				break;
	//			}
	//		}
	//		return check;
	//	}
	//
	//	// 비번 체크
	//	public boolean checkPw(String pw) {
	//		if (pw.length() >= 8 && pw.length() <= 12) {
	//			return true;
	//		} else {
	//			return false;
	//		}
	//	}
	//체크하는 매소드 1개
	//아이디, 패스워드



	public int checkUser(String id, String pw) {
		int check = -1;
		//		
		//		int index=-1;
		//		for (int i=0;i<users.size();i++) {
		//			if(users.get(i).getId().equals(id)) {
		//				index = i;
		//			}
		//		}
		String nowUser = dao.getNowUser();


		if(nowUser.equals(id)) {
			for(int i=0;i<users.size();i++) {
				if(users.get(i).getId().equals(id)) {
					if(users.get(i).getPw().equals(pw)) {
						if(users.get(i).getRetalList().isEmpty()==true) {
							check = i;
							break;
						}
					}


				}
			}
		}

		return check;

	}

	public void deleteUser(String id) {
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getId().equals(id)) {
				users.remove(i);
				dao.saveUser(users);

			}
		}
	}

}
