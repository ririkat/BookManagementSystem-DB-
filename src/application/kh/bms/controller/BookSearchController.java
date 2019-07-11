package application.kh.bms.controller;

import java.util.ArrayList;
import java.util.List;

import application.kh.bms.model.dao.InformationManager;
import application.kh.bms.model.service.BookModelService;
import application.kh.bms.model.service.UserService;
import application.kh.bms.model.vo.BookModel;
import application.kh.bms.model.vo.BookTable;
import application.kh.bms.model.vo.RentalTable;
import application.kh.bms.model.vo.User;

public class BookSearchController {

//	private LoadSave dao = LoadSave.getDao();
	private BookModelService bs = new BookModelService();
	private InformationManager im = InformationManager.getInformationManager();
	private UserService us = new UserService();
	private List<BookModel> temp = new ArrayList<BookModel>();
	private List<BookTable> temp2 = new ArrayList<BookTable>();

	private List<RentalTable> temp3 = new ArrayList<RentalTable>();
	private List<User> users = new ArrayList<User>();
	private User nowUser = new User();

	public List<BookTable> bookTableLoad() {
		temp2.clear();
		temp = bs.selectAll();
		for (int i = 0; i < temp.size(); i++) {
			BookTable bt = new BookTable(temp.get(i).getCode(), temp.get(i).getBookName(), temp.get(i).getAuthor(),
					temp.get(i).getPublishingHouse(), temp.get(i).getCategory(), temp.get(i).isRental(),temp.get(i).getContent());
			temp2.add(bt);
		}
		return temp2;
	}

	public List<RentalTable> rentalTableLoad() {
		System.out.println("렌탈테이블 로드 메소드 실행");
		nowUser = im.getNowUser();
		users = us.selectAll();
		temp3.clear();

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId().equals(nowUser)) {
				for (int j = 0; j < temp.size(); j++) {
					if (users.get(i).getRetalList().containsKey(temp.get(j).getCode())) {
						System.out.println("조건에 걸렸당!");
						RentalTable bt = new RentalTable(temp.get(j).getCode(), temp.get(j).getBookName(),
								temp.get(j).getAuthor(), temp.get(j).getPublishingHouse(), temp.get(j).getCategory());
						temp3.add(bt);
					}
				}
				break;
			}
		}
		return temp3;
	}

}
