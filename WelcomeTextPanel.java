import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class WelcomeTextPanel extends JPanel{
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		
		// render text
		g2.setPaint(Color.black);
		
		// set font
		g2.setFont(new Font("papyrus", Font.PLAIN, 32));
		
		// make text and shapes appear
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		// prepare graphics object to render
		String text = "Welcome to Sport Club";
		g2.drawString(text, 70, 150);
	}

}
