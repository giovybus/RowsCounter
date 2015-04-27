package model;

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
	public Project(int id, String absolutePath, Date latestCountingDate) {
		this.id = id;
		this.absolutePath = absolutePath;
		this.latestCountingDate = latestCountingDate;
	}
	
	/**
	 * constructor without id 
	 */
	public Project(String absolutePath, Date latestCountingDate) {
		this.absolutePath = absolutePath;
		this.latestCountingDate = latestCountingDate;
	}
	
}
