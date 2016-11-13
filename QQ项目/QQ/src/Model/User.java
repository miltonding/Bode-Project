package Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class User {
	private String username;
	private String password;
	public User(String admin, String password) {
		super();
		this.username = admin;
		this.password = password;
	}
		public String getAdmin() {
			return username;
		}
		public void setAdmin(String admin) {
			this.username = admin;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	@Override
		public int hashCode() {
			return super.hashCode();
		}
	@Override
		public boolean equals(Object obj) {
			User u=(User)obj;
			return (this.username.equals(u.username));
		}
	@Override
		public String toString() {
			return "用户名"+this.username+"    "+"密码"+password;
		}
	
	
	//把user信息写到配置文件中去
	public void inituser(){
		Properties properties = new Properties();
		properties.setProperty("123", "123456");
		properties.setProperty("jack", "123456");
		properties.setProperty("tom", "123456");
		try {
			properties.store(new FileOutputStream("G:\\user.properties"), "");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
