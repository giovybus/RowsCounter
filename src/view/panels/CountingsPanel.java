package view.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Counting;
import model.Project;

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
	private String []col = {"Date of counting", "N° of package", "N° of file", "N° of rows", "Extensions"};
	private JScrollPane tableScroll;
	
	private JPanel panSouth;
	
	/**
	 * this button allow you to
	 * return in the main panel
	 * to view all projects 
	 */
	private JButton buttReturn;
	
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
		
		tableScroll = new JScrollPane(table);
		
	}

	/**
	 * 
	 */
	private void initSouthPanel() {
		panSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		buttReturn = new JButton("Return");
		panSouth.add(buttReturn);
		
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
	
	public void addRow(Counting c){
		tableModel.addRow(c.getObject());
	}
	
	public void setCountings(List<Counting>countings){
		this.countings = countings;
		if(this.countings != null){
			for(Counting c : this.countings){
				addRow(c);
			}
		}
	}
	
	public int getSelectedRow(){
		return this.table.getSelectedRow();
	}
}
