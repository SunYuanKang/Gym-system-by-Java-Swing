import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class InfoDialog extends JDialog {

	private static final int WIDTH = 900;
	private static final int HEIGHT = 600;

	private final JPanel contentPanel = new JPanel();

	private JLabel membershipNumLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel dateOfBirthLabel;
	private JLabel genderLabel;
	private JLabel postAddressLabel;
	private JLabel telNumberLabel;
	private JLabel ageLabel;
	private JLabel healthConditionLabel;
	private JLabel allergyInfoLabel;
	private JLabel startingDateLabel;
	private JLabel endingDateLabel;
	private JLabel memberTypeLabel;
	private JLabel feeAmountLabel;

	private JTextField membershipNumField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField dateOfBirthField;
	private JTextField genderField;
	private JTextField postAddressField;
	private JTextField telNumberField;
	private JTextField ageField;
	private JTextArea healthConditionArea;
	private JTextArea allergyInfoArea;
	private JTextField startingDateField;
	private JTextField endingDateField;
	private JTextField memberTypeField;
	private JTextField feeAmountField;

	/**
	 * Create the dialog.
	 */
	public InfoDialog() {
		setTitle("Customer Information Details");

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		setBounds((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2, WIDTH, HEIGHT);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);

		// get customer information
		int row = ClubMembership.getDataTable().getSelectedRow();
		Vector<String> singleRow = ((Vector<String>) ClubMembership.getAllData().get(row));
		String[] customerInfo= new String[DataTable.headSize];;
		for(int i=0;i<ClubMembership.getMembershipList().size();i++){
			String customNum = ClubMembership.getMembershipList().get(i).getMembershipNumber() + "";	
			if(customNum.equals(singleRow.get(0))){
				MemberShip memberShip = ClubMembership.getMembershipList().get(i);
				String[] customerInfoSplit = memberShip.toString().split(OperationCSV.csvSplitBy);
				System.arraycopy(customerInfoSplit, 0, customerInfo, 0, customerInfoSplit.length);
				break;
			}
		}
		

		// set labels
		Font labelFont = new Font("Times New Roman", Font.PLAIN, 20);
		membershipNumLabel = new JLabel("Membership Number");
		membershipNumLabel.setFont(labelFont);
		firstNameLabel = new JLabel("First Name");
		firstNameLabel.setFont(labelFont);
		lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setFont(labelFont);
		dateOfBirthLabel = new JLabel("Birthday");
		dateOfBirthLabel.setFont(labelFont);
		genderLabel = new JLabel("Gender");
		genderLabel.setFont(labelFont);
		postAddressLabel = new JLabel("Post Address");
		postAddressLabel.setFont(labelFont);
		telNumberLabel = new JLabel("Telephone");
		telNumberLabel.setFont(labelFont);
		ageLabel = new JLabel("Age");
		ageLabel.setFont(labelFont);
		healthConditionLabel = new JLabel("Health Condition");
		healthConditionLabel.setFont(labelFont);
		allergyInfoLabel = new JLabel("Allergy Information");
		allergyInfoLabel.setFont(labelFont);
		startingDateLabel = new JLabel("Start Date");
		startingDateLabel.setFont(labelFont);
		endingDateLabel = new JLabel("End date");
		endingDateLabel.setFont(labelFont);
		memberTypeLabel = new JLabel("Membership Type");
		memberTypeLabel.setFont(labelFont);
		feeAmountLabel = new JLabel("Fee");
		feeAmountLabel.setFont(labelFont);

		// set textfields
		membershipNumField = new JTextField();
		membershipNumField.setSize(100, 10);
		membershipNumField.setEditable(false);
		membershipNumField.setText(customerInfo[0]);
		firstNameField = new JTextField();
		firstNameField.setSize(100, 10);
		firstNameField.setEditable(false);
		firstNameField.setText(customerInfo[1]);
		lastNameField = new JTextField();
		lastNameField.setSize(100, 10);
		lastNameField.setEditable(false);
		lastNameField.setText(customerInfo[2]);
		dateOfBirthField = new JTextField();
		dateOfBirthField.setSize(100, 10);
		dateOfBirthField.setEditable(false);
		dateOfBirthField.setText(customerInfo[3]);
		genderField = new JTextField();
		genderField.setSize(100, 10);
		genderField.setEditable(false);
		genderField.setText(customerInfo[4]);
		postAddressField = new JTextField();
		postAddressField.setSize(100, 10);
		postAddressField.setEditable(false);
		postAddressField.setText(customerInfo[5]);
		telNumberField = new JTextField();
		telNumberField.setSize(100, 10);
		telNumberField.setEditable(false);
		telNumberField.setText(customerInfo[6]);
		ageField = new JTextField();
		ageField.setSize(100, 10);
		ageField.setEditable(false);
		ageField.setText(customerInfo[7]);
		startingDateField = new JTextField();
		startingDateField.setSize(100, 10);
		startingDateField.setEditable(false);
		startingDateField.setText(customerInfo[10]);
		endingDateField = new JTextField();
		endingDateField.setSize(100, 10);
		endingDateField.setEditable(false);
		endingDateField.setText(customerInfo[11]);
		memberTypeField = new JTextField();
		memberTypeField.setSize(100, 10);
		memberTypeField.setEditable(false);
		memberTypeField.setText(customerInfo[12]);
		feeAmountField = new JTextField();
		feeAmountField.setSize(100, 10);
		feeAmountField.setEditable(false);
		feeAmountField.setText(customerInfo[13]);

		// set textareas
		healthConditionArea = new JTextArea();
		healthConditionArea.setRows(6);
		healthConditionArea.setSize(100, 30);
		healthConditionArea.setEditable(false);
		healthConditionArea.setText(customerInfo[8]);
		allergyInfoArea = new JTextArea();
		allergyInfoArea.setRows(6);
		allergyInfoArea.setSize(100, 30);
		allergyInfoArea.setEditable(false);
		allergyInfoArea.setText(customerInfo[9]);

		// set group layout
		GroupLayout groupLayout = new GroupLayout(contentPanel);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);

		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(firstNameLabel)
						.addComponent(membershipNumLabel).addComponent(genderLabel).addComponent(telNumberLabel)
						.addComponent(healthConditionLabel).addComponent(startingDateLabel)
						.addComponent(endingDateLabel))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(firstNameField)
						.addComponent(membershipNumField).addComponent(genderField).addComponent(telNumberField)
						.addComponent(healthConditionArea).addComponent(startingDateField)
						.addComponent(endingDateField))
				.addGap(60)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lastNameLabel)
						.addComponent(dateOfBirthLabel).addComponent(ageLabel).addComponent(postAddressLabel)
						.addComponent(allergyInfoLabel).addComponent(memberTypeLabel).addComponent(feeAmountLabel))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lastNameField)
						.addComponent(dateOfBirthField).addComponent(ageField).addComponent(postAddressField)
						.addComponent(allergyInfoArea).addComponent(memberTypeField).addComponent(feeAmountField))
				.addGap(40));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(firstNameLabel)
						.addComponent(firstNameField).addComponent(lastNameLabel).addComponent(lastNameField))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(membershipNumLabel)
						.addComponent(membershipNumField).addComponent(dateOfBirthLabel).addComponent(dateOfBirthField))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(genderLabel)
						.addComponent(genderField).addComponent(ageLabel).addComponent(ageField))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(telNumberLabel)
						.addComponent(telNumberField).addComponent(postAddressLabel).addComponent(postAddressField))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(healthConditionLabel)
						.addComponent(healthConditionArea).addComponent(allergyInfoLabel).addComponent(allergyInfoArea))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(startingDateLabel)
						.addComponent(startingDateField).addComponent(memberTypeLabel).addComponent(memberTypeField))
				.addGap(30).addGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(endingDateLabel)
						.addComponent(endingDateField).addComponent(feeAmountLabel).addComponent(feeAmountField)));

		contentPanel.setLayout(groupLayout);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okButton.setPreferredSize(new Dimension(90, 40));
		okButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

	}
}
