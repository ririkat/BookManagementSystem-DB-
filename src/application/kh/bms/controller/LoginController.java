package application.kh.bms.controller;

import java.util.ArrayList;

import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.User;

public class LoginController {
	private LoadSave dao = LoadSave.getDao();
	
	public int checkLogin(String id, String pw) {
		int check = -1;
		ArrayList<User> temp = dao.loadUser();
		for(int i=0; i<temp.size(); i++) {
			if(temp.get(i).getId().equals(id)) {
				if(temp.get(i).getPw().equals(pw)){
					check = i;
					break;
				}
			}
		}
		return check;
	}
}
