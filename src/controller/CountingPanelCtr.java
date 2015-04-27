package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainGui;
import view.panels.CountingsPanel;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 27/apr/2015 19:39:18
 */
public class CountingPanelCtr {
	private MainGui mainGui;
	
	private CountingsPanel countingPanel;
	
	/**
	 * 
	 */
	public CountingPanelCtr(MainGui mainGui, CountingsPanel countingPanel) {
		this.mainGui = mainGui;
		this.countingPanel = countingPanel;
		
		this.countingPanel.getButtonCounting().setEnabled(false);
		this.countingPanel.getButtonDelete().setEnabled(false);
		this.countingPanel.getButtonExport().setEnabled(false);
		this.countingPanel.getButtonOpen().setEnabled(false);
		
		this.countingPanel.getButtonReturn().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CountingPanelCtr.this.mainGui.removeCountingPanel();
				CountingPanelCtr.this.mainGui.addMainPanel();
				
			}
		});
	}
	
}
