package application.kh.bms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import application.kh.bms.model.dao.InformationManager;
import application.kh.bms.model.service.BookModelService;
import application.kh.bms.model.service.RentalService;
import application.kh.bms.model.service.UserService;
import application.kh.bms.model.vo.BookModel;
import application.kh.bms.model.vo.Rental;
import application.kh.bms.model.vo.User;

public class RentalController {

	private String userId = "";
	private User nowUser = new User();
	private BookModelService bs = new BookModelService();
	private UserService us = new UserService();
	private RentalService rs = new RentalService();
	private InformationManager is = InformationManager.getInformationManager();
	private List<User> users = new ArrayList<User>();
	private List<Rental> rList = new ArrayList<Rental>();
	private List<BookModel> bList = new ArrayList<BookModel>();

	public void addRetalBook(String bookCode) {

		userId = is.getNowUser().getId(); // ���� ����
		users = us.selectAll(); // ��ü ����

		rs.rentalInsert(userId, bookCode);

	}

	public int delRentalBook(String bookCode) {
		System.out.println("����Ʈ��Ż�� �޼ҵ� ����");

		nowUser = is.getNowUser(); // ���� ����
		long result = 0;
		rList = rs.selectAll(nowUser.getId());

		Date today = new Date();
		Date returnDate = (Date) rs.selectBookCode(nowUser.getId(), bookCode).getReturnDate(); // �ݳ�����

		long sub = returnDate.getTime() - today.getTime();
		result = sub / (24 * 60 * 60 * 1000);

		rs.returnDelete(nowUser.getId(), bookCode);

		if (result < 0) { // ��ü
			return 1;

		} else { // ����ݳ�
			return 0;
		}
	}

	public int bookRentalUpdate(String state, String code) {
		int result = rs.bookRentalUpdate(state, code);
		return result;
	}

}
