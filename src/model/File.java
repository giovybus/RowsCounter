package model;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 25/apr/2015 10:38:40
 */
public class File {
	
	/**
	 * id of foreign key
	 */
	private int idCounting;
	
	/**
	 * the absolute path of the file
	 * memorized in the project dir
	 */
	private String absolutePath;
	
	/**
	 * the absolute path of the file
	 * but this absolute path is the 
	 * reference to the backup of this file
	 */
	private String absolutePathBackup;
	
	/**
	 * the number of rows 
	 * of the file
	 */
	private long countingRows;
	
	
}
