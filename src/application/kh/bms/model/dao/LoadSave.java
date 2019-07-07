package application.kh.bms.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import application.kh.bms.model.vo.BookModel;
import application.kh.bms.model.vo.User;

public class LoadSave {

	private String userFileName = "User.txt";
	private String BookFileName = "BookModel.txt";
	private static LoadSave dao = new LoadSave();
	private String nowUser = "";
	private String nowBook = "";

	private LoadSave() {
	}

	public String getNowUser() {
		return nowUser;
	}

	public void setNowUser(String nowUser) {
		this.nowUser = nowUser;
	}
	
	public String getNowBook() {
		return nowBook;
	}

	public void setNowBookModel(String nowBook) {
		this.nowBook = nowBook;
	}

	public static LoadSave getDao() {
		return dao;
	}

	public ArrayList<User> loadUser() {
		ObjectInputStream ois = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			File user = new File(userFileName);
			ois = new ObjectInputStream(new FileInputStream(user));
			users = (ArrayList<User>) ois.readObject();
		} catch (NullPointerException e) {
		}catch (Exception e) {
		} finally {
			try {
				ois.close();
			}  catch(NullPointerException e4) {

			}catch (IOException e) {
				e.printStackTrace();
			}

		}
		return users;
	}
	
	public ArrayList<BookModel> loadBook() {
		ObjectInputStream ois = null;
		ArrayList<BookModel> BookModels = new ArrayList<>();
		try {
				File book = new File(BookFileName);
				ois = new ObjectInputStream(new FileInputStream(book));
				BookModels = (ArrayList<BookModel>) ois.readObject();
			} catch (NullPointerException e) {
			}catch (Exception e) {
			} finally {
				try {
					ois.close();
				}  catch(NullPointerException e4) {

				}catch (IOException e) {
					e.printStackTrace();
				}

			}
		return BookModels;
	}

	public void saveUser(ArrayList<User> users) {
		File file = new File(userFileName);
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveBook(ArrayList<BookModel> BookModels) {
		ObjectInputStream ois = null;
		File file = new File(BookFileName);
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(BookModels);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
