import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IncomeFeeDialog extends JDialog {

	private static final int WIDTH = 950;
	private static final int HEIGHT = 650;

	private final JPanel contentPanel = new JPanel();
	
	private JLabel visitorLabel;
	private JLabel visitorFeeLabel;
	private JLabel visitorSwimLabel;
	private JLabel visitorSwimFeeLabel;
	private JLabel visitorLessonLabel;
	private JLabel visitorLessonFeeLabel;
	private JLabel visitorAllLabel;
	private JLabel visitorAllFeeLabel;
	private JLabel monthIndividualLabel;
	private JLabel monthIndividualFeeLabel;
	private JLabel quarterIndividualLabel;
	private JLabel quarterIndividualFeeLabel;
	private JLabel yearIndividualLabel;
	private JLabel yearIndividualFeeLabel;
	private JLabel monthFamilyLabel;
	private JLabel monthFamilyFeeLabel;
	private JLabel quarterFamilyLabel;
	private JLabel quarterFamilyFeeLabel;
	private JLabel yearFamilyLabel;
	private JLabel yearFamilyFeeLabel;
	private JLabel incomeLabel;
	
	private JComboBox incomeYear;
	

	/**
	 * Create the dialog.
	 */
	public IncomeFeeDialog() {
		setTitle("Fee and Income");

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		setBounds((screenWidth - WIDTH) / 2, (screenHeight - HEIGHT) / 2, WIDTH, HEIGHT);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		// set labels
		Font labelFont = new Font("Times New Roman", Font.PLAIN, 20);
		visitorLabel = new JLabel("Visitor:");
		visitorLabel.setFont(labelFont);
		visitorFeeLabel = new JLabel("3 Pounds each time ");
		visitorFeeLabel.setFont(labelFont);
		visitorSwimLabel = new JLabel("Visitor with use of swimming pool:");
		visitorSwimLabel.setFont(labelFont);
		visitorSwimFeeLabel = new JLabel("4 Pounds each time ");
		visitorSwimFeeLabel.setFont(labelFont);
		visitorLessonLabel = new JLabel("Visitor with uses of lessons:");
		visitorLessonLabel.setFont(labelFont);
		visitorLessonFeeLabel = new JLabel("5 Pounds each time ");
		visitorLessonFeeLabel.setFont(labelFont);
		visitorAllLabel = new JLabel("Visitor with use of swimming pool and lessons:");
		visitorAllLabel.setFont(labelFont);
		visitorAllFeeLabel = new JLabel("6 Pounds each time ");
		visitorAllFeeLabel.setFont(labelFont);
		monthIndividualLabel = new JLabel("Monthly_Individual:");
		monthIndividualLabel.setFont(labelFont);
		monthIndividualFeeLabel = new JLabel("36 Pounds each month");
		monthIndividualFeeLabel.setFont(labelFont);
		quarterIndividualLabel = new JLabel("Quarterly_Individual:");
		quarterIndividualLabel.setFont(labelFont);
		quarterIndividualFeeLabel = new JLabel("36 * 3 * 0.95 = 102  Pounds each quarter");
		quarterIndividualFeeLabel.setFont(labelFont);
		yearIndividualLabel = new JLabel("Yearly_Individual:");
		yearIndividualLabel.setFont(labelFont);
		yearIndividualFeeLabel = new JLabel("36 * 12 * 0.85 = 367  Pounds each year");
		yearIndividualFeeLabel.setFont(labelFont);
		monthFamilyLabel = new JLabel("Monthly_Family:");
		monthFamilyLabel.setFont(labelFont);
		monthFamilyFeeLabel = new JLabel("60 Pounds each month");
		monthFamilyFeeLabel.setFont(labelFont);
		quarterFamilyLabel = new JLabel("Quarterly_Family:");
		quarterFamilyLabel.setFont(labelFont);
		quarterFamilyFeeLabel = new JLabel("60 * 3 * 0.95 = 171 Pounds each quarter");
		quarterFamilyFeeLabel.setFont(labelFont);
		yearFamilyLabel = new JLabel("Yearly_Family:");
		yearFamilyLabel.setFont(labelFont);
		yearFamilyFeeLabel = new JLabel("60 * 12 * 0.85 = 684 Pounds each year");
		yearFamilyFeeLabel.setFont(labelFont);
		incomeLabel = new JLabel();
		incomeLabel.setFont(labelFont);
		
		// set combobox
		ArrayList<String> yearList = new ArrayList<String>();
		ArrayList<Integer> incomeList = new ArrayList<Integer>();
		for(int i=0;i<ClubMembership.getMembershipList().size();i++){
			String startDate = ClubMembership.getMembershipList().get(i).getStartingDate();
			if(!startDate.equals("")){
				String[] dateSplit = startDate.split("/");
				if(!yearList.contains(dateSplit[2])){
					yearList.add(dateSplit[2]);
				}
			}		
		}
		String[] yearBoxItem = new String[yearList.size()];
		yearList.toArray(yearBoxItem);
		incomeYear = new JComboBox(yearBoxItem);
		incomeYear.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		incomeYear.setPreferredSize(new Dimension(90, 40));
		
		// get income and set combo box and label
		int[] income = new int[yearBoxItem.length];
		for(int i=0;i<ClubMembership.getMembershipList().size();i++){
			String startDate = ClubMembership.getMembershipList().get(i).getStartingDate();
			if(!startDate.equals("")){
				String[] dateSplit = startDate.split("/");
				for(int j=0;j<yearBoxItem.length;j++){
					if(dateSplit[2].equals(yearBoxItem[j])){
						income[j] += ClubMembership.getMembershipList().get(i).getFeeAmount();
					}
				}
			}
		}
		String incomeText = "Year " + yearBoxItem[0] + " earned " + income[0] + " pounds !";
		incomeLabel.setText(incomeText);
		incomeYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<yearBoxItem.length;i++){
					if(incomeYear.getSelectedItem().equals(yearBoxItem[i])){
						String incomeText = "Year " + yearBoxItem[i] + " earned " + income[i] + " pounds !";
						incomeLabel.setText(incomeText);
					}
				}
			}
		});
		
		// set group layout
		GroupLayout groupLayout = new GroupLayout(contentPanel);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(visitorLabel)
						.addComponent(visitorSwimLabel)
						.addComponent(visitorLessonLabel)
						.addComponent(visitorAllLabel)
						.addComponent(monthIndividualLabel)
						.addComponent(quarterIndividualLabel)
						.addComponent(yearIndividualLabel)
						.addComponent(monthFamilyLabel)
						.addComponent(quarterFamilyLabel)
						.addComponent(yearFamilyLabel)
						.addComponent(incomeYear, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
					.addGap(100)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(visitorFeeLabel)
						.addComponent(visitorSwimFeeLabel)
						.addComponent(visitorLessonFeeLabel)
						.addComponent(visitorAllFeeLabel)
						.addComponent(monthIndividualFeeLabel)
						.addComponent(quarterIndividualFeeLabel)
						.addComponent(yearIndividualFeeLabel)
						.addComponent(monthFamilyFeeLabel)
						.addComponent(quarterFamilyFeeLabel)
						.addComponent(yearFamilyFeeLabel)
						.addComponent(incomeLabel))
					.addGap(40))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(visitorLabel)
						.addComponent(visitorFeeLabel))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(visitorSwimLabel)
						.addComponent(visitorSwimFeeLabel))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(visitorLessonLabel)
						.addComponent(visitorLessonFeeLabel))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(visitorAllLabel)
						.addComponent(visitorAllFeeLabel))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(monthIndividualLabel)
						.addComponent(monthIndividualFeeLabel))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(quarterIndividualLabel)
						.addComponent(quarterIndividualFeeLabel))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(yearIndividualLabel)
						.addComponent(yearIndividualFeeLabel))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(monthFamilyLabel)
						.addComponent(monthFamilyFeeLabel))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(quarterFamilyLabel)
						.addComponent(quarterFamilyFeeLabel))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(yearFamilyLabel)
						.addComponent(yearFamilyFeeLabel))
					.addGap(60)
					.addGroup(groupLayout.createParallelGroup(Alignment.CENTER)
						.addComponent(incomeYear, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(incomeLabel)))
		);
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
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
		okButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		okButton.setPreferredSize(new Dimension(90, 40));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

	}
}
