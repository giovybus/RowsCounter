package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import features.DBMS_Settings;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 27/apr/2015 13:10:50
 */
public class DBMS_Project {
	private final static String table = 
			"CREATE TABLE IF NOT EXISTS project (" +
				"id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL," +
				"absolutePath VARCHAR(250) UNIQUE," +
				"lastCountingDate DATETIME)";

	private Connection conn;
	private ResultSet res;
	private Statement sta;
	private DBMS_Settings settings;
	
	/**
	 * 
	 */
	public DBMS_Project() {
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
	 * @return
	 * all rows in db
	 */
	public List<Project> getAllProjects() {
		List<Project>projects = null;
		
		checkConnessione();
		try{
			sta = conn.createStatement();
			res = sta.executeQuery("SELECT * FROM project");
			while(res.next()){
				if(projects == null)projects = new ArrayList<>();
				projects.add(new Project(res.getInt("id"), res.getString("absolutePath"),
						res.getString("lastCountingDate")));
			}
			
			conn.close();
			sta.close();
			res.close();
		}catch(Exception exc){
			exc.printStackTrace();
		}
		
		return projects;
	}

	/**
	 * @param absolutePath
	 * @return
	 */
	public Project getProjectByPath(String absolutePath) {
		Project p = null;
		
		checkConnessione();
		try{
			sta = conn.createStatement();
			res = sta.executeQuery("SELECT * FROM project WHERE absolutePath='" + absolutePath + "'");
			
			if(res.next()){
				p = new Project(res.getInt("id"), res.getString("absolutePath"), 
						res.getString("lastCountingDate"));
			}
			
			sta.close();
			res.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return p;
	}

	/**
	 * @param project
	 */
	public boolean insert(Project project) {
		checkConnessione();
		try{
			sta = conn.createStatement();
			sta.execute("INSERT INTO project (absolutePath, lastCountingDate) "
					+ "VALUES ("
					+ "'" + project.getAbsolutePath() + "',"
					+ "now()" 
					+ ")", Statement.RETURN_GENERATED_KEYS);
			
			res = sta.getGeneratedKeys();
			if(res.next()){
				project.setId(res.getInt(1));
			}
			
			sta.close();
			res.close();
			conn.close();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean update(Project project){
		checkConnessione();
		try{
			sta = conn.createStatement();
			sta.execute("UPDATE project SET "
					+ "absolutePath='" + project.getAbsolutePath() + "',"
					+ "lastCountingDate='" + project.getDateStringUS() + "' "
					+ "WHERE id=" + project.getId());
			sta.close();
			conn.close();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * @param p
	 * @return
	 */
	public boolean delete(Project p) {
		checkConnessione();
		try {
			sta = conn.createStatement();
			sta.execute("DELETE FROM project where id=" + p.getId());
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
