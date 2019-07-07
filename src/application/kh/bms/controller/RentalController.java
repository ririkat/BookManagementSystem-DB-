package application.kh.bms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;

import application.kh.bms.model.dao.LoadSave;
import application.kh.bms.model.vo.User;

public class RentalController {
   
   private String userId = "";
   private LoadSave dao = LoadSave.getDao();
   private ArrayList<User> users = new ArrayList<User>();
   
   private HashMap<String, GregorianCalendar> rentalBook = new HashMap<String, GregorianCalendar>();
   
   //대여도서 추가하기
   public void addRetalBook(String bookCode) {
      
      System.out.println("애드렌탈북 메소드 실행");
//      System.out.println("대여시 코드 넘겨받은 값 : "+bookCode);
      
      //현재날짜 받아와 7일 더해 반납날짜 만들기
      GregorianCalendar cal = new GregorianCalendar(Locale.KOREA);
       //cal.setTime(new Date());
       cal.add(cal.DATE, 7);
       Date tD = new Date(cal.getTimeInMillis());
       System.out.println("입력된 반납 날짜 : " + tD);
       
       //확인용
       //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       //String strDate = df.format(cal.getTime());

      rentalBook.put(bookCode, cal);   //책코드,반납날짜 묶기
      userId = dao.getNowUser();   //현재 유저
      users = dao.loadUser();      //전체 유저
      
      //전체 유저에서 현재 유저 찾아 대여도서(해쉬맵) 추가해주기
      for(int i=0; i<users.size(); i++) {
         System.out.println("포문 도는중~ " + i);
         if(users.get(i).getId().equals(userId)) {
            users.get(i).addRentalList(rentalBook);
            System.out.println(userId + " // " + users.get(i).getRetalList());
            dao.saveUser(users);
            break;
         }
      }
      
   }
   
   
   //대여도서 반납하기
   public int delRentalBook(String bookCode) {
      System.out.println("딜리트렌탈북 메소드 실행");
      
      userId = dao.getNowUser();   //현재 유저
      users = dao.loadUser();      //전체 유저
      long result = 0;
      
      for(int i=0; i<users.size(); i++) {
         if(users.get(i).getId().equals(userId)) {   //현재 유저 찾음
            GregorianCalendar today = new GregorianCalendar(Locale.KOREA); //오늘날짜
            GregorianCalendar returnDate = (GregorianCalendar) users.get(i).getRetalList().get(bookCode); //반납일자
            
             Date tD = new Date(today.getTimeInMillis());
             Date rD = new Date(returnDate.getTimeInMillis());
             
             System.out.println("현재날짜 : " + tD);
             System.out.println("반납일자 : " + rD);
                 
             // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
             long sub = rD.getTime() - tD.getTime();   //반납날짜에서 오늘날짜 빼기
             result = sub / (24 * 60 * 60 * 1000);
             System.out.println("날짜차이=" + result);
             
            users.get(i).getRetalList().remove(bookCode);
            dao.saveUser(users);
             
            if(result < 0) {   //연체
               return 1;
            }
            else {   //정상반납
               return 0;
            }
         }
      }
      return -999;
   }
   
}
