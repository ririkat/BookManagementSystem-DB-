package application.kh.bms.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import application.kh.bms.common.JDBCTemplate;
import application.kh.bms.model.dao.BookModelDao;
import application.kh.bms.model.vo.BookModel;

public class BookModelService {
	private BookModelDao dao = new BookModelDao();

	public BookModel oneUserSelect(String code) {
		Connection conn = JDBCTemplate.getConnection();
		BookModel b = dao.oneBookSelect(conn, code);
		JDBCTemplate.close(conn);
		return b;
	}

	public int addBook(BookModel b) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.addBook(conn, b);
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public void deleteBook(String code) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteBook(conn, code);
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}

	public List<BookModel> selectAll() {
		Connection conn = JDBCTemplate.getConnection();
		List<BookModel> list = dao.selectAll(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public void updateBook(BookModel b) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateBook(conn, b);
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}
	
	public List<BookModel> conditionSelect(String col, String inputStmt) {
		Connection conn = JDBCTemplate.getConnection();
		List<BookModel> list = dao.conditionSelect(conn, col, inputStmt);
		JDBCTemplate.close(conn);
		return list;
	}

}
