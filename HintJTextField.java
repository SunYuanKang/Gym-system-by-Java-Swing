import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class HintJTextField extends JTextField implements FocusListener{
	
	private String hintText;
	private boolean isShow;
	
	// create a new JTextField to show some hint text about it
	public HintJTextField(String hintText) {
		super(hintText);
		setSize(100, 10);
		setForeground(Color.GRAY);
		this.hintText = hintText;
		this.isShow = true;
		this.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(getText().equals("")){
			setForeground(Color.BLACK);
			super.setText("");
			this.isShow = false;
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(getText().equals("")){
			setForeground(Color.GRAY);
			super.setText(this.hintText);
			this.isShow = true;
		}		
	}
	
	@Override
	public String getText() {
		if(isShow){
			return "";
		}
		else{
			return super.getText();
		}
	}
	
	@Override
	public void setText(String t) {
		super.setText(t);
		this.hintText = t;
		this.isShow = false;
		setForeground(Color.BLACK);
		removeFocusListener(this);
	}

}
