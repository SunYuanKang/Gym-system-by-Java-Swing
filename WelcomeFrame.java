import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class WelcomeFrame extends JFrame {

	private static final int WIDTH = 500;
    private static final int HEIGHT = 400;
	
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public WelcomeFrame() {
		setTitle("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setBounds((screenWidth-WIDTH)/2, (screenHeight-HEIGHT)/2, WIDTH, HEIGHT);
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		WelcomeTextPanel textPanel = new WelcomeTextPanel();
		contentPane.add(textPanel, BorderLayout.CENTER);
		
		JPanel btnPanel = new JPanel();
		contentPane.add(btnPanel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Click to Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new MainFrame().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setEnabled(true);
		btnPanel.add(btnNewButton);
	}
}
