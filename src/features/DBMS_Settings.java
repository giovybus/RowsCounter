package features;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 27/apr/2015 13:11:46
 */
public class DBMS_Settings {
	private String url = "jdbc:h2:C:\\Users\\giovybus\\Desktop\\rc\\rowsCounter;AUTO_SERVER=TRUE;Mode=Mysql;";
	private String user = "root";
	private String password = "";
	
	/**
	 * 
	 */
	public DBMS_Settings() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public String getUser(){
		return this.user;
	}
	
	public String getPassword(){
		return this.password;
	}
}
