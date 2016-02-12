package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import features.TextPrompt;
import model.Counting;

/**
 * @author Giovanni Buscarino (giovybus) Copyright (c) 2015 <br>
 * <b>Email:</b> giovanni.buscarino[at]gmail.com<br>
 *
 * created on 26/apr/2015 17:16:25
 */
public class CountingsPanel extends JPanel{
private static final long serialVersionUID = 1L;
	
	private JPanel panNorth;
	private JTextField fieldSearch;
	private JButton buttSearch;
	
	private JPanel panCenter;
	private JTable table;
	private DefaultTableModel tableModel;
	private String []col = {"Date of counting", "N° of package", "N° of file", "N° of rows", "Extensions", "Diff"};
	private JScrollPane tableScroll;
	
	private JPanel panSouth;
	
	/**
	 * this button allow you to
	 * return in the main panel
	 * to view all projects 
	 */
	private JButton buttReturn;
	
	/**
	 * this button allow you
	 * to view the files of
	 * the selected counting
	 */
	private JButton buttView;
	
	/**
	 * this button open the filechooser
	 * for a new counting for this project
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
	
	private List<Counting> countings;
	
	/**
	 * constructor
	 */
	public CountingsPanel() {
		super(new BorderLayout());
	
		initNorthPanel();
		initCenterPanel();
		initSouthPanel();
		fillPanel();
		
	}

	/**
	 * 
	 */
	private void initNorthPanel() {
		panNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		initFieldSearch();
		panNorth.add(fieldSearch);
		
		buttSearch = new JButton("Search");
		panNorth.add(buttSearch);
	}
	
	/**
	 * 
	 */
	private void initFieldSearch() {
		fieldSearch = new JTextField(50);
		
		TextPrompt tp = new TextPrompt("insert search query for this project", fieldSearch);
		tp.setForeground(Color.LIGHT_GRAY);
		
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
		
		table.getColumnModel().getColumn(0).setMinWidth(100);
		
		tableScroll = new JScrollPane(table);
		
	}

	/**
	 * 
	 */
	private void initSouthPanel() {
		panSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		buttReturn = new JButton("Return");
		panSouth.add(buttReturn);
		
		buttView = new JButton("View files");
		panSouth.add(buttView);
		
		buttCounting = new JButton("Count");
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
	
	public JButton getButtonReturn(){
		return this.buttReturn;
	}

	public JButton getButtView() {
		return buttView;
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
	
	public String getQuerySearch(){
		return this.fieldSearch.getText();
	}
	
	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}
	
	/**
	 * @return the fieldSearch
	 */
	public JTextField getFieldSearch() {
		return fieldSearch;
	}
	
	public void addRow(Counting c, long diff){
		tableModel.addRow(c.getObject(diff));
	}
	
	public void setCountings(List<Counting>countings){
		this.countings = countings;
		
		this.tableModel.setRowCount(0);
		if(this.countings != null){
			/*for(Counting c : this.countings){
				addRow(c);
			}*/
			for(int i=0; i<this.countings.size(); i++){
				if(i==0) addRow(this.countings.get(i), 0);
				else addRow(this.countings.get(i), 
						(this.countings.get(i).getNumberOfRows() 
								- this.countings.get(i-1).getNumberOfRows()));
			}
		}
	}
	
	public int getSelectedRow(){
		return this.table.getSelectedRow();
	}
	
	/**
	 * 
	 * @return
	 * the counting selected in table,
	 * else return null
	 */
	public Counting getCountingSelected(){
		if(getSelectedRow() != -1){
			return this.countings.get(getSelectedRow());
		}else{
			return null;
		}
	}
}
