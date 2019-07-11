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
import application.kh.bms.model.vo.Rental;

public class RentalDao {
	Properties prop = new Properties();

	public RentalDao() {
		try {
			prop.load(new FileReader("resources/rentalQuery.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Rental> selectAll(Connection conn, String id) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAll");
		ResultSet rs = null;
		ArrayList<Rental> temp = new ArrayList<Rental>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Rental r = new Rental();
				r.setId(rs.getString("id"));
				r.setCode(rs.getString("code"));
				r.setReturnDate(rs.getDate("return_date"));

				temp.add(r);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return temp;
	}
}
