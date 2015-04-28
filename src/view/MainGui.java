package view;

import javax.swing.JFrame;

import controller.CountingPanelCtr;
import controller.FilesPanelCtr;
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
	
	/*private MainPanelCtr mainPanelCtr;
	private CountingPanelCtr countingPanelCtr;
	private FilesPanelCtr filesPanelCtr;*/
	
	/**
	 * constructor 
	 */
	public MainGui() {
		panMain = new MainPanel();
		panCount = new CountingsPanel();
		panFiles = new FilesPanel();
		
		//mainGuiCtr = new MainGuiCtr(this, panMain, panCount, panFiles);
		/*mainPanelCtr = */new MainPanelCtr(this, panMain, panCount, panFiles);
		/*countingPanelCtr = */new CountingPanelCtr(this, panCount, panFiles);
		/*filesPanelCtr = */new FilesPanelCtr(this, panFiles);
		
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

	public void removeCountingPanel() {
		frm.remove(panCount);
		frm.repaint();
	}
	
	public void addMainPanel(){
		frm.add(panMain);
		frm.revalidate();
	}
	
	public void addFilesPanel(){
		frm.add(panFiles);
		frm.revalidate();
	}
	
	public void removeFilesPanel(){
		frm.remove(panFiles);
		frm.repaint();
	}
}
