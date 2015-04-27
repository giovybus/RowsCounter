package model;

import java.util.Date;
import java.util.List;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 25/apr/2015 10:43:47
 */
public class Counting {
	
	/**
	 * personal id of counting
	 */
	private int id;
	
	/**
	 * the id of project, (database table)
	 * foreign key
	 */
	private int idProject;
	
	/**
	 * date counting 
	 */
	private Date dateCounting;
	
	/**
	 * the number of pakage founded 
	 * in your project
	 */
	private long numberOfPack; 
	
	/**
	 * the number of source files
	 */
	private long numberOfFiles;
	
	/**
	 * total number of rows 
	 */
	private long numberOfRows;
	
	/**
	 * list of extension
	 */
	private String extensions;
	
	/**
	 * a list of files, relative
	 * this counting
	 */
	private List<File>files;
	
	/**
	 * constructor
	 */
	public Counting(int idProject, Date dateCounting, long numberOfPack, 
			long numberOfFiles, long numberOfRows, String extensions) {
		this.idProject = idProject;
		this.dateCounting = dateCounting;
		this.numberOfPack = numberOfPack;
		this.numberOfRows = numberOfRows;
		this.extensions = extensions;
	}
}
