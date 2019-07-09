package application.kh.bms.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		User temp = null;
		ResultSet rs = null;
		String sql = prop.getProperty("oneUserSelect");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				temp = new User();
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
		return temp;
	}

	public int addUser(Connection conn, User temp) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("addUser");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, temp.getId());
			pstmt.setString(2, temp.getPw());
			pstmt.setString(3, temp.getName());
			pstmt.setString(4, temp.getGender());
			pstmt.setString(5, temp.getAddr());
			pstmt.setString(6, temp.getPhone());
			if (temp.isAdminCheck()) {
				pstmt.setString(7, "1");
			} else {
				pstmt.setString(7, "0");
			}
			result = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteUser(Connection conn, String id) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteUser");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<User> selectAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> temp = new ArrayList<User>();
		String sql = prop.getProperty("selectAll");
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User tempUser = new User();
				tempUser.setId(rs.getString("id"));
				tempUser.setPw(rs.getString("pw"));
				tempUser.setName(rs.getString("name"));
				tempUser.setGender(rs.getString("gender"));
				tempUser.setAddr(rs.getString("addr"));
				tempUser.setPhone(rs.getString("phone"));
				if (rs.getString("admincheck").equals("1")) {
					tempUser.setAdminCheck(true);
				} else {
					tempUser.setAdminCheck(false);
				}

				temp.add(tempUser);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return temp;
	}

	public int updateUser(Connection conn, User temp) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateUser");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, temp.getName());
			pstmt.setString(2, temp.getAddr());
			pstmt.setString(3, temp.getGender());
			pstmt.setString(4, temp.getPhone());
			pstmt.setString(5, temp.getId());

			result = pstmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
