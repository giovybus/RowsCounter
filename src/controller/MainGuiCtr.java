package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Project;
import view.MainGui;
import view.panels.CountingsPanel;
import view.panels.FilesPanel;
import view.panels.MainPanel;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 27/apr/2015 12:53:40
 */
public class MainGuiCtr {

	private MainGui mainGui;
	private MainPanel mainPanel;
	private CountingsPanel countingPanel;
	private FilesPanel filesPanel;
	
	/**
	 * 
	 */
	public MainGuiCtr(final MainGui mainGui, MainPanel mainPanel, CountingsPanel countingPanel, FilesPanel filesPanel) {
		this.mainGui = mainGui;
		this.mainPanel = mainPanel;
		this.countingPanel = countingPanel;
		this.filesPanel = filesPanel;
		
		this.mainPanel.getButtonView().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainGui.removeMainPanel();
				System.out.println("rimosso");
				
			}
		});
	}
}
