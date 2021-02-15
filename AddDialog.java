import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class AddDialog extends JDialog {

	private static final int WIDTH = 900;
	private static final int HEIGHT = 500;

	private final JPanel contentPanel = new JPanel();

	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel dateOfBirthLabel;
	private JLabel genderLabel;
	private JLabel postAddressLabel;
	private JLabel telNumberLabel;
	private JLabel healthConditionLabel;
	private JLabel allergyInfoLabel;
	private JLabel startingDateLabel;
	private JLabel memberTypeLabel;

	private HintJTextField firstNameField;
	private HintJTextField lastNameField;
	private HintJTextField dateOfBirthField;
	private HintJTextField postAddressField;
	private HintJTextField telNumberField;
	private JTextArea healthConditionArea;
	private JTextArea allergyInfoArea;
	private HintJTextField startingDateField;

	private JComboBox genderBox;
	private JComboBox memberTypeBox;

	/**
	 * Create the dialog.
	 */
	public AddDialog() {
		setTitle("Add New Member");
		setModal(true);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		setBounds((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2, WIDTH, HEIGHT);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// set labels
		Font labelFont = new Font("Times New Roman", Font.PLAIN, 20);
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
		healthConditionLabel = new JLabel("Health Condition");
		healthConditionLabel.setFont(labelFont);
		allergyInfoLabel = new JLabel("Allergy Information");
		allergyInfoLabel.setFont(labelFont);
		startingDateLabel = new JLabel("Start Date");
		startingDateLabel.setFont(labelFont);
		memberTypeLabel = new JLabel("Membership Type");
		memberTypeLabel.setFont(labelFont);

		// set textfields
		firstNameField = new HintJTextField("First name");
		lastNameField = new HintJTextField("Last name");
		dateOfBirthField = new HintJTextField("08/03/1998");
		postAddressField = new HintJTextField("Post address");
		telNumberField = new HintJTextField("(+44)(0)7987654321");
		startingDateField = new HintJTextField("10/10/2020");

		// set textarea
		healthConditionArea = new JTextArea();
		healthConditionArea.setRows(6);
		healthConditionArea.setSize(100, 30);
		allergyInfoArea = new JTextArea();
		allergyInfoArea.setRows(6);
		allergyInfoArea.setSize(100, 30);

		// set combobox
		String[] genderBoxItem = { "male", "female", "others", "perfer not to disclose" };
		genderBox = new JComboBox(genderBoxItem);
		String[] memberTypeBoxItem = { "Monthly_Family", "Quarterly_Family", "Yearly_Family", "Monthly_Individual",
				"Quarterly_Individual", "Yearly_Individual", "Visitor" };
		memberTypeBox = new JComboBox(memberTypeBoxItem);

		// set group layout
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GroupLayout groupLayout = new GroupLayout(contentPanel);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);

		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(firstNameLabel)
						.addComponent(dateOfBirthLabel).addComponent(postAddressLabel)
						.addComponent(healthConditionLabel).addComponent(startingDateLabel))
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(firstNameField)
						.addComponent(dateOfBirthField).addComponent(postAddressField).addComponent(healthConditionArea)
						.addComponent(startingDateField))
				.addGap(40)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lastNameLabel)
						.addComponent(genderLabel).addComponent(telNumberLabel).addComponent(allergyInfoLabel)
						.addComponent(memberTypeLabel))
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lastNameField)
						.addComponent(genderBox).addComponent(telNumberField).addComponent(allergyInfoArea)
						.addComponent(memberTypeBox)));
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(firstNameLabel)
						.addComponent(firstNameField).addComponent(lastNameLabel).addComponent(lastNameField))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(dateOfBirthLabel)
						.addComponent(dateOfBirthField).addComponent(genderLabel).addComponent(genderBox))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(postAddressLabel)
						.addComponent(postAddressField).addComponent(telNumberLabel).addComponent(telNumberField))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(healthConditionLabel)
						.addComponent(healthConditionArea).addComponent(allergyInfoLabel).addComponent(allergyInfoArea))
				.addGap(30).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(startingDateLabel)
						.addComponent(startingDateField).addComponent(memberTypeLabel).addComponent(memberTypeBox)));

		contentPanel.setLayout(groupLayout);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		// set save button
		JButton okButton = new JButton("Save");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (memberTypeBox.getSelectedItem().equals("Visitor")) {   // MemberType is visitor
					if (dateOfBirthField.getText().equals("")) {
						JOptionPane.showMessageDialog(getContentPane(), "Please enter birthday to determine age !",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						try {
							if (verifyAge() < 12) {
								JOptionPane.showMessageDialog(getContentPane(), "Visitor are at least 12 years old !",
										"Error", JOptionPane.ERROR_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(getContentPane(), "Add visitor success!");
								MainFrame.visitorNum++;
								MainFrame.setVisitorNumLabel();
								dispose();
							}
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}
				} else {    // MemberType is others
					try {
						switch (verification()) {  // verify the information
						case 0:
							try {
								addNewMenber();
								JOptionPane.showMessageDialog(getContentPane(), "Add member success!");
								dispose();
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
							break;
						case 1:
							JOptionPane.showMessageDialog(getContentPane(), "Please enter first name!", "Error",
									JOptionPane.ERROR_MESSAGE);
							break;
						case 2:
							JOptionPane.showMessageDialog(getContentPane(), "Please enter last name!", "Error",
									JOptionPane.ERROR_MESSAGE);
							break;
						case 3:
							JOptionPane.showMessageDialog(getContentPane(),
									"Please enter right birthday format: 08/03/1998 !", "Error",
									JOptionPane.ERROR_MESSAGE);
							break;
						case 4:
							JOptionPane.showMessageDialog(getContentPane(),
									"Please enter right telephone number: (+44)(0)7987654321 !", "Error",
									JOptionPane.ERROR_MESSAGE);
							break;
						case 5:
							JOptionPane.showMessageDialog(getContentPane(),
									"Please enter right start date format: 10/10/2020 !", "Error",
									JOptionPane.ERROR_MESSAGE);
							break;
						case 6:
							JOptionPane.showMessageDialog(getContentPane(),
									"Individual membership are at least 12 years old !", "Error",
									JOptionPane.ERROR_MESSAGE);
							break;
						case 7:
							JOptionPane.showMessageDialog(getContentPane(),
									"Family membership are at least 18 years old !", "Error",
									JOptionPane.ERROR_MESSAGE);
							break;
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
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
		if (dateOfBirthField.getText().equals("")) {
			return 3;
		}
		if (!Pattern.matches(dateReg, dateOfBirthField.getText())) {
			return 3;
		}
		int age = verifyAge();

		String[] memberTypeSplit = memberTypeBox.getSelectedItem().toString().split("_");

		if (firstNameField.getText().equals("")) {
			return 1;
		}
		if (lastNameField.getText().equals("")) {
			return 2;
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

	public int verifyAge() throws ParseException {
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

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}

		return age;
	}

	private void addNewMenber() throws ParseException {
		String[] colName = DataTable.colName;

		Map<String, String> map = new HashMap<String, String>();
		map.put(colName[0], "");
		map.put(colName[1], firstNameField.getText());
		map.put(colName[2], lastNameField.getText());
		map.put(colName[3], dateOfBirthField.getText());
		map.put(colName[4], genderBox.getSelectedItem().toString());
		map.put(colName[5], postAddressField.getText());
		map.put(colName[6], telNumberField.getText());
		map.put(colName[7], "");
		map.put(colName[8], healthConditionArea.getText());
		map.put(colName[9], allergyInfoArea.getText());
		map.put(colName[10], startingDateField.getText());
		map.put(colName[11], "");
		map.put(colName[12], memberTypeBox.getSelectedItem().toString());
		map.put(colName[13], "");

		MemberShip memberShip = new MemberShip(map, ClubMembership.getMembershipList());
		ClubMembership.getMembershipList().add(memberShip);
	}

}
