package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

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
	private ExtensionToCounting extensions;
	
	/**
	 * a list of files, relative
	 * this counting
	 */
	private List<File>files;
	
	/**
	 * constructor
	 */
	public Counting(int idProject, Date dateCounting, long numberOfPack, 
			long numberOfFiles, long numberOfRows, ExtensionToCounting extensions) {
		this.idProject = idProject;
		this.dateCounting = dateCounting;
		this.numberOfPack = numberOfPack;
		this.numberOfRows = numberOfRows;
		this.extensions = extensions;
	}
	
	/**
	 * @param idProject the idProject to set
	 */
	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}
	
	/**
	 * @param dateCounting the dateCounting to set
	 */
	public void setDateCounting(Date dateCounting) {
		this.dateCounting = dateCounting;
	}
	
	/**
	 * @param numberOfPack the numberOfPack to set
	 */
	public void setNumberOfPack(long numberOfPack) {
		this.numberOfPack = numberOfPack;
	}
	
	/**
	 * @param numberOfRows the numberOfRows to set
	 */
	public void setNumberOfRows(long numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	
	/**
	 * @param extensions the extensions to set
	 */
	public void setExtensions(ExtensionToCounting extensions) {
		this.extensions = extensions;
	}
	
	public String getDateCountingIT(){
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return s.format(this.dateCounting);
	}
	
	/**
	 * @return the numberOfRows
	 */
	public long getNumberOfRows() {
		return numberOfRows;
	}
	


	/**
	 * @return
	 */
	public Object[] getObject() {
		return new Object[]{
				getDateCountingIT(),
				this.numberOfPack,
				this.numberOfFiles,
				this.numberOfRows,
				"ext"
		};
	}
}
