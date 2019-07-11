package application.kh.bms.model.vo;

import java.sql.Date;

public class Rental {
	private String id;
	private String code;
	private Date returnDate;

	public Rental() {
	}

	public Rental(String id, String code, Date returnDate) {
		super();
		this.id = id;
		this.code = code;
		this.returnDate = returnDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "Rental [id=" + id + ", code=" + code + ", returnDate=" + returnDate + "]";
	}

}
