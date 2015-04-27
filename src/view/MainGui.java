package view;

import javax.swing.JFrame;

import controller.MainGuiCtr;
import controller.MainPanelCtr;
import main.RowsCounter;
import view.panels.CountingsPanel;
import view.panels.FilesPanel;
import view.panels.MainPanel;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 26/apr/2015 17:12:19
 */
public class MainGui {
	private JFrame frm;
	
	private MainPanel panMain;
	private CountingsPanel panCount;
	private FilesPanel panFiles;
	
	/**
	 * the global controller
	 */
	private MainGuiCtr mainGuiCtr;
	private MainPanelCtr mainPanelCtr;
	
	/**
	 * constructor 
	 */
	public MainGui() {
		panMain = new MainPanel();
		panCount = new CountingsPanel();
		panFiles = new FilesPanel();
		
		//mainGuiCtr = new MainGuiCtr(this, panMain, panCount, panFiles);
		mainPanelCtr = new MainPanelCtr(this, panMain, panCount);
		
		initFrame();
	}


	/**
	 * 
	 */
	private void initFrame() {
		frm = new JFrame("Rows Counter (v. " + RowsCounter.VERSION + ")");
		frm.setSize(700, 300);
		frm.setLocationRelativeTo(null);
		frm.setResizable(false);
		
		frm.add(panMain);
		
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);
		
	}
	
	/******************************************************************
	 * 
	 * 			public methods 
	 * 
	 * *****************************************************************/
	
	public void removeMainPanel(){
		frm.remove(panMain);
		frm.repaint();
	}
	
	public void addCountingPanel(){
		frm.add(panCount);
		frm.revalidate();
	}
}
