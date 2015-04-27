package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.omg.CORBA.RepositoryIdHelper;

import features.DBMS_Settings;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 27/apr/2015 15:34:32
 */
public class DBMS_File {
	private static final String table = 
			"CREATE TABLE IF NOT EXISTS file(" +
					"idCounting BIGINT," +
					"absolutePath VARCHAR(250)," +
					"absolutePathBackup VARCHAR(250)," +
					"countingRows BIGINT DEFAULT 0," +
					"FOREIGN KEY (idCounting) REFERENCES counting(id) ON DELETE CASCADE" +
			")";
	
	private Connection conn;
	private ResultSet res;
	private Statement sta;
	private DBMS_Settings settings;
	
	/**
	 * 
	 */
	public DBMS_File() {
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

	/**
	 * @param source
	 */
	public boolean inserisci(FileSource source) {
		checkConnessione();
		try{
			sta = conn.createStatement();
			sta.execute("INSERT INTO file (idCounting, absolutePath, countingRows)VALUES("
					+ source.getIdCounting() + ","
					+ "'" + source.getAbsolutePath() + "',"
					+ source.getRowsCounting() 
					+ ")");
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
}
