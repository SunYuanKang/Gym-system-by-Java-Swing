import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DataTable extends JTable {

	// the name of heads
	public static final String[] colName = { "CustomerNum", "FirstName", "LastName", "DateOfBirth", "Gender",
			"PostAddress", "TelNumber", "Age", "HealthCondition", "AllergyInfo", "StartingDate", "EndingDate",
			"MemberType", "FeeAmount" };
	
	public DefaultTableModel tableModel;
	public static final int headSize = colName.length;
	
	// set all column names into vector
	public static final Vector<String> colNameVector = new Vector<String>(Arrays.asList(colName));

	public DataTable() {	
		// set model
		tableModel = new DefaultTableModel(ClubMembership.getAllData(), colNameVector);
		setModel(tableModel);
		
		setFont(new Font("Times New Roman", Font.PLAIN, 14));
		getTableHeader().setPreferredSize(new Dimension(150, 35));
		getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 16));
        setVisible(true);	
        
        // if click the single row twice, show all details of this customers
        addMouseListener(new MouseAdapter(){
        	public void mouseClicked(MouseEvent e){
        		if(e.getClickCount()==2){
        			new InfoDialog().setVisible(true);
        		}
        	}
        });
	}
	
	// can not edit every single cell
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
