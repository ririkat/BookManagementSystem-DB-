package application.kh.bms.controller;

import java.util.ArrayList;

import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.User;
import application.kh.bms.model.vo.UserTable;

public class UserSearchController {
	private LoadSave dao = LoadSave.getDao();

	public ArrayList<UserTable> userTableLoad() {
		ArrayList<User> temp = dao.loadUser();
		ArrayList<UserTable> temp2 = new ArrayList<UserTable>();
		for (int i = 0; i < temp.size(); i++) {
			UserTable ut = new UserTable(temp.get(i).getId(), temp.get(i).getName(), temp.get(i).getAddr(),
					temp.get(i).getGender(), temp.get(i).getPhone(), temp.get(i).isAdminCheck());
			System.out.println(temp.get(i).isAdminCheck());
			temp2.add(ut);
		}
		return temp2;
	}
}
