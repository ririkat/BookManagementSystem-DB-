package application.kh.bms.controller;

import java.util.ArrayList;

import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.User;

public class UserUpdateController {
	private LoadSave dao = LoadSave.getDao();
	private ArrayList<User> users = null;
	private String targetId = "";
//	private static User nowUser = null;

	// ArrayList를 이용하여 수정된 유저의 정보를 파라미터로 받아와 해당 userNo에 Set 함
	public void updateUser(String id, String name, String addr, String gender, String phone) {
		ArrayList<User> users = dao.loadUser();
		int nowUser = -1;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId().equals(targetId)) {
				nowUser = i;
				users.get(i).setName(name);
				users.get(i).setAddr(addr);
				users.get(i).setGender(gender);
				users.get(i).setPhone(phone);
				break;
			}
		}
//		User temp = new User(targetId, users.get(nowUser).getPw(), name, addr, gender, phone,users.get(nowUser).isAdminCheck());
//		System.out.println(temp.toString());
//		users.set(nowUser, temp);
		dao.saveUser(users);
	}

	// 설정한 PW 의 길이 8~12자 체크
	public boolean pwCheck(String pw) {
		if (pw.length() >= 8 && pw.length() <= 12) {
			return true;
		} else {
			return false;
		}
	}

	// 회원정보수정 이전 씬으로부터 로그인한 유저의 userNo를 받아와 해당 값으로 유저정보를 로드시킴
	public User loadData(String id) {
		targetId = id;
		ArrayList<User> users = dao.loadUser();
		User temp = null;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId().equals(targetId)) {
				temp = users.get(i);
				break;
			}
		}
		return temp;
	}
}
