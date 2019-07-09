package application.kh.bms.model.service;

import java.sql.Connection;

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
}
