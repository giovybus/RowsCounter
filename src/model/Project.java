package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 25/apr/2015 10:38:20
 * 
 * this class contains the struct of your project
 */
public class Project {
	
	/**
	 * the id used in the 
	 * database, (unique and primay key)
	 */
	private int id;
	
	/**
	 * the absolute path of the project
	 */
	private String absolutePath;
	
	/**
	 * the date of the latest 
	 * counting
	 */
	private Date latestCountingDate;

	/**
	 * a list of countings, relative 
	 * this project
	 */
	private List<Counting>countings;
	
	/**
	 * constructor 
	 */
	public Project(int id, String absolutePath, String latestCountingDate) {
		this.id = id;
		this.absolutePath = absolutePath;
		setDateString(latestCountingDate);
	}
	
	/**
	 * constructor without id 
	 */
	public Project(String absolutePath, Date latestCountingDate) {
		this.absolutePath = absolutePath;
		this.latestCountingDate = latestCountingDate;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDateStringIT(){
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		//System.out.println(s.format(this.latestCountingDate) + ", " + latestCountingDate);
		return s.format(this.latestCountingDate);
	}
	
	public void setDateString(String latestCountingDate){
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			this.latestCountingDate = s.parse(latestCountingDate);
		}catch(Exception e){
			e.printStackTrace();
			this.latestCountingDate = null;
		}
	}
	
	/**
	 * @param latestCountingDate the latestCountingDate to set
	 */
	public void setLatestCountingDate(Date latestCountingDate) {
		this.latestCountingDate = latestCountingDate;
	}
	
	/**
	 * @return
	 */
	public String getDateStringUS() {
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return s.format(this.latestCountingDate);
	}

	/**
	 * @return the absolutePath
	 */
	public String getAbsolutePath() {
		return absolutePath;
	}
	
	/**
	 * @param countings the countings to set
	 */
	public void setCountings(List<Counting> countings) {
		this.countings = countings;
	}
	
	/**
	 * @return the countings
	 */
	public List<Counting> getCountings() {
		return countings;
	}
	
	public void addCounting(Counting c){
		if(countings == null) countings = new ArrayList<>();
		countings.add(c);
	}
	
	/**
	 * @return
	 */
	public Object[] getObject() {
		Object []o = {
				this.absolutePath,
				getDateStringIT()
		};
		
		return o;
	}
}
