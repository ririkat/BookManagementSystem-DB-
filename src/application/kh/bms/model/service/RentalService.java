package application.kh.bms.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import application.kh.bms.common.JDBCTemplate;
import application.kh.bms.model.dao.RentalDao;
import application.kh.bms.model.vo.Rental;

public class RentalService {
	private RentalDao dao = new RentalDao();
	
	public ArrayList<Rental> selectAll(String id){
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Rental> temp = dao.selectAll(conn,id);
		JDBCTemplate.close(conn);
		return temp;
	}
	
	public int bookRentalUpdate(String ch, String code) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.bookRentalUpdate(conn, ch, code);
		if(result==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	
	public void rentalInsert(String id, String code) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.rentalInsert(conn, id,code);
		if(result==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
	}
}
