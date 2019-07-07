package application.kh.bms.model.vo;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.HashMap;

public class User implements Serializable {
   
   /**
    * 
    */
   private static final long serialVersionUID = 7242156790991014261L;
   private String id;
   private String pw;
   private String name;
   private String gender;
   private String addr;
   private String phone;
   private boolean adminCheck;
   //책코드,반납일자
   //hashmap 사용
   private HashMap<String, GregorianCalendar> retalList = new HashMap<String, GregorianCalendar>();
   
   public User(String id, String pw, String name, String gender, String addr, String phone, boolean adminCheck) {
      super();
      this.id = id;
      this.pw = pw;
      this.name = name;
      this.gender = gender;
      this.addr = addr;
      this.phone = phone;
      this.adminCheck = adminCheck;
      
   }
   
   public User() {
   }


   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getPw() {
      return pw;
   }

   public void setPw(String pw) {
      this.pw = pw;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getGender() {
      return gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public String getAddr() {
      return addr;
   }

   public void setAddr(String addr) {
      this.addr = addr;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }
   
   public boolean isAdminCheck() {
      return adminCheck;
   }

   public void setAdminCheck(boolean adminCheck) {
      this.adminCheck = adminCheck;
   }

   public HashMap<String, GregorianCalendar> getRetalList() {
      return retalList;
   }

   public void setRetalList(HashMap<String, GregorianCalendar> retalList) {
      this.retalList = retalList;
   }
   
   public void addRentalList(HashMap<String, GregorianCalendar> retalList) {
      this.retalList.putAll(retalList);
   }
   
   public void delRentalList(String key) {
      this.retalList.remove(key);
   }
}