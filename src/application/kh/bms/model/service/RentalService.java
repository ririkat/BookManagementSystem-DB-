package application.kh.bms.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import application.kh.bms.common.JDBCTemplate;
import application.kh.bms.model.dao.RentalDao;
import application.kh.bms.model.vo.Rental;

public class RentalService {
	private RentalDao dao = new RentalDao();

	public ArrayList<Rental> selectAll(String id) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Rental> temp = dao.selectAll(conn, id);
		JDBCTemplate.close(conn);
		return temp;
	}

	public ArrayList<Rental> selectBookCode(String bookCode) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Rental> temp = dao.selectBookCode(conn, bookCode);
		JDBCTemplate.close(conn);
		return temp;
	}
}
