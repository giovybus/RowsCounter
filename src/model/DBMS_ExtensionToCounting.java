package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import features.DBMS_Settings;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 27/apr/2015 15:25:10
 */
public class DBMS_ExtensionToCounting {
	private static final String table = 
			"CREATE TABLE IF NOT EXISTS ExtensionToCounting(" +
					"idCounting BIGINT," +
					"extension VARCHAR(30)," +
					"FOREIGN KEY (idCounting) REFERENCES counting(id) ON DELETE CASCADE," +
					"FOREIGN KEY (extension) REFERENCES listExtensions(extension) ON DELETE CASCADE," +
					"PRIMARY KEY(idCounting, extension)" +
			")";
	
	private Connection conn;
	private ResultSet res;
	private Statement sta;
	private DBMS_Settings settings;
	
	/**
	 * 
	 */
	public DBMS_ExtensionToCounting() {
		settings = new DBMS_Settings();
		createConn();
	}

	
	/**
	 * this method create the table,
	 * and close the connection
	 * @return
	 */
	public boolean createTable(){
		try{
			sta = conn.createStatement();
			sta.execute(table);
			
			sta.close();
			conn.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	/**
	 * create the connection
	 */
	private void createConn(){
		try{
			conn = DriverManager.getConnection(settings.getUrl(), settings.getUser(), settings.getPassword());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * check the connection,  
	 */
	private void checkConnessione() {
		try{
			if(conn.isClosed()){
				createConn();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
