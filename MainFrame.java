import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;

public class MainFrame extends JFrame {
	
	private static final int WIDTH = 1500;
    private static final int HEIGHT = 700;
    
    // count visitors
    public static int visitorNum = 0; 

	private JPanel contentPane;
	private JTextField searchInfoField;
	private JComboBox searchComboBox;
	private static JLabel visitorNumLabel = new JLabel();
	
	public static void setVisitorNumLabel(){
		String labelText = "Number of Visitors:" + visitorNum;
		visitorNumLabel.setText(labelText);	
	}

	public MainFrame() {
		setTitle("Gym system");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// when close the main frame, save all data into csv
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				OperationCSV operationCSV = new OperationCSV("customerlist.csv");
				try {
					operationCSV.writeCSV();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setBounds((screenWidth-WIDTH)/2, (screenHeight-HEIGHT)/2, WIDTH, HEIGHT);
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// set table panel
		JScrollPane tablePanel = new JScrollPane(ClubMembership.getDataTable());
		contentPane.add(tablePanel, BorderLayout.CENTER);
		
		// set control panel
		JPanel controlPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) controlPanel.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(50);
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		// control panel has four buttons
		// set add button
		JButton btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddDialog().setVisible(true);
				// refresh the table
				ClubMembership.setAllData();
				ClubMembership.getDataTable().updateUI();
				searchInfoField.setText("");
			}
		});
		btnAdd.setPreferredSize(new Dimension(110, 40));
		controlPanel.add(btnAdd);
		
		// set delete button
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ClubMembership.getDataTable().getSelectedRow() != -1){
					String message = "Are you sure to delete the selected customer?";
					int choice = JOptionPane.showConfirmDialog(null, message, "Warning", JOptionPane.YES_NO_OPTION);
					// whether confirm to delete
					if(choice == JOptionPane.YES_OPTION){
						int[] selectedRows = ClubMembership.getDataTable().getSelectedRows();
						for(int i=0;i<selectedRows.length;i++){
							Vector<String> singleRow = ((Vector<String>) ClubMembership.getAllData().get(i));
							for(int j=0;j<ClubMembership.getMembershipList().size();j++){
								String customNum = ClubMembership.getMembershipList().get(j).getMembershipNumber() + "";
								if(customNum.equals(singleRow.get(0))){
									ClubMembership.getMembershipList().remove(j);
									((DefaultTableModel) ClubMembership.getDataTable().getModel()).removeRow(selectedRows[i]);
									break;
								}
							}
						}
					}
					// refresh the table
					ClubMembership.setAllData();
					ClubMembership.getDataTable().updateUI();
					searchInfoField.setText("");
				}
				else{
					String message = "You have not selected the customer to delete !";
					JOptionPane.showMessageDialog(getContentPane(), message, "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDelete.setPreferredSize(new Dimension(110, 40));
		controlPanel.add(btnDelete);
		
		// set edit button
		JButton btnEdit = new JButton("EDIT");
		btnEdit.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ClubMembership.getDataTable().getSelectedRow() != -1){
					new EditDialog().setVisible(true);
					// refresh the table
					ClubMembership.setAllData();
					ClubMembership.getDataTable().updateUI();
					searchInfoField.setText("");
				}
				else{
					String message = "You have not selected the customer to edit !";
					JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnEdit.setPreferredSize(new Dimension(110, 40));
		controlPanel.add(btnEdit);
		
		// set show income and fee button
		JButton btnShowIncomeAnd = new JButton("SHOW INCOME AND FEE");
		btnShowIncomeAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IncomeFeeDialog().setVisible(true);
			}
		});
		btnShowIncomeAnd.setPreferredSize(new Dimension(245, 40));
		btnShowIncomeAnd.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		controlPanel.add(btnShowIncomeAnd);
		
		MainFrame.setVisitorNumLabel();
		visitorNumLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		controlPanel.add(visitorNumLabel);
				
		// set search panel
		JPanel searchPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) searchPanel.getLayout();
		flowLayout_1.setHgap(15);
		flowLayout_1.setVgap(10);
		contentPane.add(searchPanel, BorderLayout.NORTH);
		
		searchInfoField = new JTextField();
		searchInfoField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		searchPanel.add(searchInfoField);
		searchInfoField.setColumns(50);
		
		String[] searchComboBoxItem = {"CustomerNum", "FirstName", "LastName", "TelNumber"};
		searchComboBox = new JComboBox(searchComboBoxItem);
		searchComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		searchPanel.add(searchComboBox);
		
		// set search button
		JButton searchButton = new JButton("SEARCH");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the search column
				String searchCol = searchComboBox.getSelectedItem().toString();
				String searchInfoText = searchInfoField.getText();
				if(ClubMembership.searchData(searchCol, searchInfoText).equals("ERROR")){  // cannot search
					String message = "Search information is wrong or do not exist !";
					JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.ERROR_MESSAGE);
					ClubMembership.setAllData();
				}
				else{
					// refresh the table
					ClubMembership.getDataTable().updateUI();
				}			
			}
		});
		searchButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		searchPanel.add(searchButton);
		
		// set show all members button
		JButton button = new JButton("SHOW ALL MEMBERS");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// refresh the table
				ClubMembership.setAllData();
				ClubMembership.getDataTable().updateUI();
				searchInfoField.setText("");
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		searchPanel.add(button);
	}
}
