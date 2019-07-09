package application.kh.bms.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import application.kh.bms.common.JDBCTemplate;
import application.kh.bms.model.dao.UserDao;
import application.kh.bms.model.vo.User;

public class UserService {
	private UserDao dao = new UserDao();

	public User oneUserSelect(String id) {
		Connection conn = JDBCTemplate.getConnection();
		User temp = dao.oneUserSelect(conn, id);
		JDBCTemplate.close(conn);
		return temp;
	}

	public void addUser(User temp) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.addUser(conn, temp);
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}

	public void deleteUser(String id) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteUser(conn, id);
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}

	public ArrayList<User> selectAll() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<User> temp = dao.selectAll(conn);
		JDBCTemplate.close(conn);
		return temp;
	}

	public void updateUser(User temp) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.updateUser(conn, temp);
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
	}
}
