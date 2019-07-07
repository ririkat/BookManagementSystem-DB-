package application.kh.bms.model.vo;

import java.io.Serializable;

import javafx.beans.property.BooleanProperty;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserTable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -986398137402674102L;
	private final SimpleStringProperty id, name, addr, gender, phone;
	private final SimpleBooleanProperty adminCheck;

	public UserTable(String id, String name, String addr, String gender, String phone, boolean adminCheck) {
		super();
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.addr = new SimpleStringProperty(addr);
		this.gender = new SimpleStringProperty(gender);
		this.phone = new SimpleStringProperty(phone);
		this.adminCheck = new SimpleBooleanProperty(adminCheck);
	}

	public String getId() {
		return id.get();
	}

	public String getName() {
		return name.get();
	}

	public String getAddr() {
		return addr.get();
	}

//	public SimpleBooleanProperty getAdminCheck() {
//		return adminCheck;
//	}

	public BooleanProperty adminCheckProperty() {
		return adminCheck;
	}

	public boolean isaAminCheck() {
		return adminCheck.get();
	}

	public String getGender() {
		return gender.get();
	}

	public String getPhone() {
		return phone.get();
	}

	@Override
	public String toString() {
		return "UserTable [id=" + id + ", name=" + name + ", addr=" + addr + ", gender=" + gender + ", phone=" + phone
				+ ", adminCheck=" + adminCheck + "]";
	}

}
