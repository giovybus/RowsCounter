package main;

import javax.swing.UIManager;

import features.Backup;
import features.DBMS_Settings;
import view.MainGui;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 24/apr/2015 21:29:56
 */
public class RowsCounter {
	
	/**
	 * program version
	 */
	public static final String VERSION = "1.3.1";
	
	/**
	 * main class
	 * @param args
	 */
	public static void main(String []args){
		setLookAndFeel();
		DBMS_Settings.installDB();
		Backup.init();
		
		new MainGui();
	}

	/**
	 * set the look and feel of the panels
	 */
	private static void setLookAndFeel() {
		try{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
