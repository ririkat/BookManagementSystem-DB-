package application.kh.bms.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import application.kh.bms.common.JDBCTemplate;
import application.kh.bms.model.vo.BookModel;

public class BookModelDao {
	Properties prop = new Properties();

	public BookModelDao() {
		try {
			prop.load(new FileReader("resources/bookQuery.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BookModel oneBookSelect(Connection conn, String code) {
		PreparedStatement pstmt = null;
		BookModel b = null;
		ResultSet rs = null;
		String sql = prop.getProperty("oneBookSelect");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				b=new BookModel();
				b.setCode(rs.getString("code"));
				b.setBookName(rs.getString("book_name"));
				b.setAuthor(rs.getString("author"));
				b.setCategory(rs.getString("category"));
				b.setPublisgingHouse(rs.getString("publishinghouse"));
				b.setRental(Boolean.valueOf(rs.getString("rental")));
				b.setContent(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			JDBCTemplate.close(rs);
//			JDBCTemplate.close(pstmt);
		}
		return b;
	}
	
	public List<BookModel> selectAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookModel> list = new ArrayList<BookModel>();
		String sql = prop.getProperty("selectAll");
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookModel b = new BookModel();
				
				b.setCode(rs.getString("code"));
				b.setBookName(rs.getString("book_name"));
				b.setAuthor(rs.getString("author"));
				b.setCategory(rs.getString("category"));
				b.setPublisgingHouse(rs.getString("publishinghouse"));
				b.setRental(Boolean.valueOf(rs.getString("rental")));
				b.setContent(rs.getString("content"));

				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			JDBCTemplate.close(rs);
//			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int addBook(Connection conn, BookModel b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("addBook");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getCode());
			pstmt.setString(2, b.getBookName());
			pstmt.setString(3, b.getAuthor());
			pstmt.setString(4, b.getPublishingHouse());
			pstmt.setString(5, b.getCategory());
			pstmt.setString(6, b.getContent());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int deleteBook(Connection conn, String code) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteBook");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			JDBCTemplate.close(pstmt);
		}
		return result;
	}


	public int updateBook(Connection conn, BookModel b) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateBook");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getBookName());
			pstmt.setString(2, b.getAuthor());
			pstmt.setString(3, b.getPublishingHouse());
			pstmt.setString(4, b.getCategory());
			pstmt.setString(5, b.getContent());
			pstmt.setString(6, b.getCode());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public List<BookModel> conditionSelect(Connection conn, String col, String inputStmt) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookModel> list = new ArrayList<BookModel>();
		String sql = null;
		if(col.equals("code")) {
			sql = prop.getProperty("selectEquals");
		}else {
			sql = prop.getProperty("selectLike");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, col);
			pstmt.setString(2, inputStmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BookModel b = new BookModel();
				
				b.setCode(rs.getString("code"));
				b.setBookName(rs.getString("book_name"));
				b.setAuthor(rs.getString("author"));
				b.setCategory(rs.getString("category"));
				b.setPublisgingHouse(rs.getString("publishinghouse"));
				b.setRental(Boolean.valueOf(rs.getString("rental")));
				b.setContent(rs.getString("content"));

				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			JDBCTemplate.close(rs);
//			JDBCTemplate.close(pstmt);
		}
		return list;
	}
}
