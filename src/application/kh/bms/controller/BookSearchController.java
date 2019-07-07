package application.kh.bms.controller;

import java.util.ArrayList;

import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.BookModel;
import application.kh.bms.model.vo.BookTable;
import application.kh.bms.model.vo.RentalTable;
import application.kh.bms.model.vo.User;

public class BookSearchController {

	private LoadSave dao = LoadSave.getDao();
	private ArrayList<BookModel> temp = dao.loadBook();
	private ArrayList<BookTable> temp2 = new ArrayList<BookTable>();
	
	ArrayList<RentalTable> temp3 = new ArrayList<RentalTable>();
	private ArrayList<User> users = new ArrayList<User>();
	private String nowUser = "";

	
	public ArrayList<BookTable> bookTableLoad() {
		temp2.clear();
		temp = dao.loadBook();
		for (int i = 0; i < temp.size(); i++) {
			BookTable bt = new BookTable(temp.get(i).getCode(), temp.get(i).getBookName(), temp.get(i).getAuthor(),
					temp.get(i).getPublishingHouse(), temp.get(i).getCategory(), temp.get(i).isRental());
			temp2.add(bt);
		}
		return temp2;
	}
	
//	public ArrayList<RentalTable> rentalTableLoad() {
//		for (int i = 0; i < temp.size(); i++) {
//			RentalTable bt = new RentalTable(temp.get(i).getCode(), temp.get(i).getBookName(), temp.get(i).getAuthor(),
//					temp.get(i).getPublishingHouse(), temp.get(i).getCategory());
//			temp3.add(bt);
//		}
//		return temp3;
//	}
	
	
	public ArrayList<RentalTable> rentalTableLoad() {
		System.out.println("렌탈테이블 로드 메소드 실행");
		nowUser = dao.getNowUser();
		users = dao.loadUser();
		temp3.clear();
		
		for(int i=0; i<users.size(); i++) {
			if(users.get(i).getId().equals(nowUser)) {
				for(int j=0; j<temp.size(); j++) {
					if(users.get(i).getRetalList().containsKey(temp.get(j).getCode())) {
						System.out.println("조건에 걸렸당!");
						RentalTable bt = new RentalTable(temp.get(j).getCode(), temp.get(j).getBookName(), temp.get(j).getAuthor(),
								temp.get(j).getPublishingHouse(), temp.get(j).getCategory());
						temp3.add(bt);
					}
				}
				break;
			}
		}
		return temp3;
	}

}
