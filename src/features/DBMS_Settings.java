package features;

import java.io.File;

import model.DBMS_Counting;
import model.DBMS_ExtensionToCounting;
import model.DBMS_File;
import model.DBMS_ListExtensions;
import model.DBMS_Project;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 27/apr/2015 13:11:46
 */
public class DBMS_Settings {
	/*private static final String HOME_DIR ="C:\\Users\\giovybus\\Desktop\\rc";*/
	private static final String HOME_DIR = "./database";
	private String url = "jdbc:h2:" + HOME_DIR + "\\rowsCounter;AUTO_SERVER=TRUE;Mode=Mysql;";
	
	private String user = "root";
	private String password = "";
	
	/**
	 * 
	 */
	public DBMS_Settings() {
		
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
	
	public static void installDB(){
		if(!new File(HOME_DIR).exists()){
			DBMS_Project db_prject = new DBMS_Project();
			boolean contr_proj = db_prject.createTable();
			
			DBMS_Counting db_count = new DBMS_Counting();
			boolean contr_count = db_count.createTable();
			
			DBMS_File db_file = new DBMS_File();
			boolean contr_file = db_file.createTable();
			
			DBMS_ListExtensions db_listExt = new DBMS_ListExtensions();
			boolean contr_listExt = db_listExt.createTable();
			
			DBMS_ExtensionToCounting db_ext = new DBMS_ExtensionToCounting();
			boolean contr_ext = db_ext.createTable();
			
			if(contr_proj){
				System.out.println("project ok");
			}else{
				System.out.println("project no");
			}
			
			if(contr_count){
				System.out.println("counting ok");
			}else{
				System.out.println("counting no");
			}
			
			if(contr_file){
				System.out.println("file ok");
			}else{
				System.out.println("file no");
			}
			
			if(contr_listExt){
				System.out.println("listExt ok");
			}else{
				System.out.println("listExt no");
			}
			
			if(contr_ext){
				System.out.println("ext ok");
			}else{
				System.out.println("ext no");
			}
		}
	}
}
