package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import features.TextPrompt;
import model.Project;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 26/apr/2015 17:15:59
 */
public class MainPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JPanel panNorth;
	private JTextField fieldSearch;
	private JButton buttSearch;
	
	private JPanel panCenter;
	private JTable table;
	private DefaultTableModel tableModel;
	private String []col = {"Path root project", "Last counting"};
	private JScrollPane tableScroll;
	
	private JPanel panSouth;
	
	/**
	 * this button allow you 
	 * to view details of the
	 * project selected  
	 */
	private JButton buttView;
	
	/**
	 * this button open the filechooser
	 * for a new counting
	 */
	private JButton buttCounting; 
	
	/**
	 * this button allow you to
	 * delete a row selected in the
	 * table
	 */
	private JButton buttDelete;
	
	/**
	 * with this button you can
	 * export the project report 
	 * in PDF
	 */
	private JButton buttExport;
	
	/**
	 * this button open the directory
	 * of the project
	 */
	private JButton buttOpen;
	
	/**
	 * default constructor
	 */
	public MainPanel() {
		super(new BorderLayout());
		
		initNorthPanel();
		initSouthPanel();
		initCenterPanel();
		fillPanel();
	}

	/**
	 * 
	 */
	private void initNorthPanel() {
		panNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		initFieldSearch();
		panNorth.add(fieldSearch);
		
		initButtSearch();
		panNorth.add(buttSearch);
	}

	/**
	 * 
	 */
	private void initButtSearch() {
		buttSearch = new JButton("Search");
	}

	/**
	 * 
	 */
	private void initFieldSearch() {
		fieldSearch = new JTextField(50);
		
		TextPrompt tp = new TextPrompt("insert search query for all projects", fieldSearch);
		tp.setForeground(Color.LIGHT_GRAY);
	}

	/**
	 * 
	 */
	private void initSouthPanel() {
		panSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		buttView = new JButton("View project");
		panSouth.add(buttView);
		
		buttCounting = new JButton("Counting");
		panSouth.add(buttCounting);
		
		buttExport = new JButton("Export PDF");
		panSouth.add(buttExport);
		
		buttOpen = new JButton("Open directory");
		panSouth.add(buttOpen);
		
		buttDelete = new JButton("Delete");
		panSouth.add(buttDelete);
	}

	/**
	 * 
	 */
	private void initCenterPanel() {
		panCenter = new JPanel(new GridLayout(1, 1));
		
		initTable();
		panCenter.add(tableScroll);
	}

	/**
	 * 
	 */
	private void initTable() {
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int r, int c){
				return false;
			}
		};
		
		table.getColumnModel().getColumn(1).setMaxWidth(130);
		table.getColumnModel().getColumn(1).setMinWidth(130);
	
		tableScroll = new JScrollPane(table);
		
	}

	/**
	 *  fill the panel
	 */
	private void fillPanel() {
		this.add(panNorth, BorderLayout.NORTH);
		this.add(panCenter, BorderLayout.CENTER);
		this.add(panSouth, BorderLayout.SOUTH);
		
	}
	
	
	/******************************************************************
	 * 
	 * 			public methods 
	 * 
	 * *****************************************************************/
	
	public JButton getButtonView(){
		return this.buttView;
	}
	
	public JButton getButtonCounting(){
		return this.buttCounting;
	}
	
	public JButton getButtonExport(){
		return this.buttExport;
	}
	
	public JButton getButtonOpen(){
		return this.buttOpen;
	}
	
	public JButton getButtonDelete(){
		return this.buttDelete;
	}
	
	public JButton getButtonSearch(){
		return this.buttSearch;
	}
	
	public String getQuerySearch(){
		return this.fieldSearch.getText();
	}
	
	/**
	 * @return the fieldSearch
	 */
	public JTextField getFieldSearch() {
		return fieldSearch;
	}
	
	public void addRow(Project p){
		tableModel.addRow(p.getObject());
	}
	
	public int getSelectedRow(){
		return this.table.getSelectedRow();
	}

}
