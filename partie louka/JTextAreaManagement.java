import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JTextAreaManagement implements MouseListener{

	private JTextArea currentTextArea;
	private Window window;

	public JTextAreaManagement(Window window){

		this.window = window;
	}

	//mouse clicked
	public void mouseClicked(MouseEvent e){

		this.currentTextArea = (JTextArea)e.getSource();

		this.currentTextArea.setText("");
	}

	//mouse stop hovering
	public void mouseExited(MouseEvent e) {

		this.currentTextArea = (JTextArea)e.getSource();

		if(this.currentTextArea == this.window.getJTextAreaByOrderOfArrival(1)){

			try{

				int test = Integer.parseInt(this.currentTextArea.getText());
			}catch(NumberFormatException ex){

				this.currentTextArea.setText("inf");
			}
		}
		
	}

	//mouse just moved
	public void mouseMoved(MouseEvent e){}

	//mouse is hoveing
	public void mouseEntered(MouseEvent e) {}

	//mouse pressed
	public void mousePressed(MouseEvent e){}

	//mouse released
	public void mouseReleased(MouseEvent e){}
}