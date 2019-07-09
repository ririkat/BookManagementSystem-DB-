package application.kh.bms.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import application.kh.bms.common.JDBCTemplate;
import application.kh.bms.model.vo.User;

public class UserDao {
	Properties prop = new Properties();

	public UserDao() {
		try {
			prop.load(new FileReader("resources/userQuery.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public User oneUserSelect(Connection conn, String id) {
		PreparedStatement pstmt = null;
		User temp = new User();
		ResultSet rs = null;
		String sql = prop.getProperty("oneUserSelect");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				temp.setId(rs.getString("id"));
				temp.setPw(rs.getString("pw"));
				temp.setName(rs.getString("name"));
				temp.setGender(rs.getString("gender"));
				temp.setAddr(rs.getString("addr"));
				temp.setPhone(rs.getString("phone"));
				if (rs.getString("admincheck").equals("1")) {
					temp.setAdminCheck(true);
				} else {
					temp.setAdminCheck(false);
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}

	}
}
