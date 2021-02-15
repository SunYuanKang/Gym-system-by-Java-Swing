import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class EditDialog extends JDialog {

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
	private JTextField postAddressField;
	private JTextField telNumberField;
	private JTextField ageField;
	private JTextArea healthConditionArea;
	private JTextArea allergyInfoArea;
	private JTextField startingDateField;
	private JTextField endingDateField;
	private JTextField feeAmountField;

	private JComboBox genderBox;
	private JComboBox memberTypeBox;
	
	private int index = 0;

	/**
	 * Create the dialog.
	 */
	public EditDialog() {
		setTitle("Edit selected Member");
		setModal(true);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		setBounds((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2, WIDTH, HEIGHT);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		// get customer information
		int row = ClubMembership.getDataTable().getSelectedRow();
		Vector<String> singleRow = ((Vector<String>) ClubMembership.getAllData().get(row));
		String[] customerInfo = new String[DataTable.headSize];
		for (int i = 0; i < ClubMembership.getMembershipList().size(); i++) {
			String customNum = ClubMembership.getMembershipList().get(i).getMembershipNumber() + "";
			if (customNum.equals(singleRow.get(0))) {
				index = i;
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
		firstNameField.setText(customerInfo[1]);
		lastNameField = new JTextField();
		lastNameField.setText(customerInfo[2]);
		dateOfBirthField = new JTextField();
		dateOfBirthField.setText(customerInfo[3]);
		postAddressField = new JTextField();
		postAddressField.setText(customerInfo[5]);
		telNumberField = new JTextField();
		telNumberField.setText(customerInfo[6]);
		ageField = new JTextField();
		ageField.setSize(100, 10);
		ageField.setEditable(false);
		ageField.setText(customerInfo[7]);
		startingDateField = new JTextField();
		startingDateField.setText(customerInfo[10]);
		endingDateField = new JTextField();
		endingDateField.setSize(100, 10);
		endingDateField.setEditable(false);
		endingDateField.setText(customerInfo[11]);
		feeAmountField = new JTextField();
		feeAmountField.setSize(100, 10);
		feeAmountField.setEditable(false);
		feeAmountField.setText(customerInfo[13]);

		// set textareas
		healthConditionArea = new JTextArea();
		healthConditionArea.setRows(6);
		healthConditionArea.setSize(100, 30);
		healthConditionArea.setText(customerInfo[8]);
		allergyInfoArea = new JTextArea();
		allergyInfoArea.setRows(6);
		allergyInfoArea.setSize(100, 30);
		allergyInfoArea.setText(customerInfo[9]);

		// set combobox
		String[] genderBoxItem = { "male", "female", "others", "perfer not to disclose" };
		genderBox = new JComboBox(genderBoxItem);
		genderBox.setSelectedItem(customerInfo[4]);
		String[] memberTypeBoxItem = { "Monthly_Family", "Quarterly_Family", "Yearly_Family", "Monthly_Individual",
				"Quarterly_Individual", "Yearly_Individual" };
		memberTypeBox = new JComboBox(memberTypeBoxItem);
		memberTypeBox.setSelectedItem(customerInfo[12]);

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
						.addComponent(membershipNumField).addComponent(genderBox).addComponent(telNumberField)
						.addComponent(healthConditionArea).addComponent(startingDateField)
						.addComponent(endingDateField))
				.addGap(60)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lastNameLabel)
						.addComponent(dateOfBirthLabel).addComponent(ageLabel).addComponent(postAddressLabel)
						.addComponent(allergyInfoLabel).addComponent(memberTypeLabel).addComponent(feeAmountLabel))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lastNameField)
						.addComponent(dateOfBirthField).addComponent(ageField).addComponent(postAddressField)
						.addComponent(allergyInfoArea).addComponent(memberTypeBox).addComponent(feeAmountField))
				.addGap(40));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(firstNameLabel)
						.addComponent(firstNameField).addComponent(lastNameLabel).addComponent(lastNameField))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(membershipNumLabel)
						.addComponent(membershipNumField).addComponent(dateOfBirthLabel).addComponent(dateOfBirthField))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(genderLabel)
						.addComponent(genderBox).addComponent(ageLabel).addComponent(ageField))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(telNumberLabel)
						.addComponent(telNumberField).addComponent(postAddressLabel).addComponent(postAddressField))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(healthConditionLabel)
						.addComponent(healthConditionArea).addComponent(allergyInfoLabel).addComponent(allergyInfoArea))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(startingDateLabel)
						.addComponent(startingDateField).addComponent(memberTypeLabel).addComponent(memberTypeBox))
				.addGap(30).addGroup(groupLayout.createParallelGroup(Alignment.CENTER).addComponent(endingDateLabel)
						.addComponent(endingDateField).addComponent(feeAmountLabel).addComponent(feeAmountField)));

		contentPanel.setLayout(groupLayout);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		// set save button
		JButton okButton = new JButton("Save");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					switch (verification()) {  // verify the information
					case 0:
						try {
							editMenber(index);
							JOptionPane.showMessageDialog(getContentPane(), "Edit member information success!");
							dispose();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						break;
					case 1:
						JOptionPane.showMessageDialog(getContentPane(), "Please enter first name!", "Error", JOptionPane.ERROR_MESSAGE);
						break;
					case 2:
						JOptionPane.showMessageDialog(getContentPane(), "Please enter last name!", "Error", JOptionPane.ERROR_MESSAGE);
						break;
					case 3:
						JOptionPane.showMessageDialog(getContentPane(),
								"Please enter right birthday format: 08/03/1998 !", "Error", JOptionPane.ERROR_MESSAGE);
						break;
					case 4:
						JOptionPane.showMessageDialog(getContentPane(),
								"Please enter right telephone number: (+44)(0)7987654321 !", "Error", JOptionPane.ERROR_MESSAGE);
						break;
					case 5:
						JOptionPane.showMessageDialog(getContentPane(),
								"Please enter right start date format: 10/10/2020 !", "Error", JOptionPane.ERROR_MESSAGE);
						break;
					case 6:
						JOptionPane.showMessageDialog(getContentPane(),
								"Individual membership are at least 12 years old !", "Error", JOptionPane.ERROR_MESSAGE);
						break;
					case 7:
						JOptionPane.showMessageDialog(getContentPane(),
								"Family membership are at least 18 years old !", "Error", JOptionPane.ERROR_MESSAGE);
						break;
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		okButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		okButton.setPreferredSize(new Dimension(90, 40));
		okButton.setActionCommand("Save");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		cancelButton.setPreferredSize(new Dimension(90, 40));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

	}

	public int verification() throws ParseException {
		// verify data
		String dateReg = "(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|[1][012])\\/\\d{4}";

		// verify telephone number
		String telReg = "(\\+44|0)7[0-9]{9}";

		// verify age
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date current = simpleDateFormat.parse("01/09/" + year);
		cal.setTime(current);
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(simpleDateFormat.parse(dateOfBirthField.getText()));
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;

		String[] memberTypeSplit = memberTypeBox.getSelectedItem().toString().split("_");

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}

		if (firstNameField.getText().equals("")) {
			return 1;
		}
		if (lastNameField.getText().equals("")) {
			return 2;
		}
		if (!Pattern.matches(dateReg, dateOfBirthField.getText())) {
			return 3;
		}
		if (!Pattern.matches(telReg, telNumberField.getText())) {
			return 4;
		}
		if (!Pattern.matches(dateReg, startingDateField.getText())) {
			return 5;
		}
		if (memberTypeSplit[1].equals("Individual") && age < 12) {
			return 6;
		}
		if (memberTypeSplit[1].equals("Family") && age < 18) {
			return 7;
		}
		return 0;
	}

	private void editMenber(int index) throws ParseException {
		String[] colName = DataTable.colName;

		Map<String, String> map = new HashMap<String, String>();
		map.put(colName[0], membershipNumField.getText());
		map.put(colName[1], firstNameField.getText());
		map.put(colName[2], lastNameField.getText());
		map.put(colName[3], dateOfBirthField.getText());
		map.put(colName[4], genderBox.getSelectedItem().toString());
		map.put(colName[5], postAddressField.getText());
		map.put(colName[6], telNumberField.getText());
		map.put(colName[7], ageField.getText());
		map.put(colName[8], healthConditionArea.getText());
		map.put(colName[9], allergyInfoArea.getText());
		map.put(colName[10], startingDateField.getText());
		map.put(colName[11], endingDateField.getText());
		map.put(colName[12], memberTypeBox.getSelectedItem().toString());
		map.put(colName[13], feeAmountField.getText());

		MemberShip memberShip = new MemberShip(map, ClubMembership.getMembershipList());
		// change the membership with index
		ClubMembership.getMembershipList().set(index, memberShip);
	}

}
