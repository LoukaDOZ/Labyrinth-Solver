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

		if(this.currentTextArea == this.window.getJTextAreaByOrderOfArrival(1) && this.window.getTitle().equals("Choose an algorithm")){

			try{

				int test = Integer.parseInt(this.currentTextArea.getText());

				if(test < 1){

					this.currentTextArea.setText("infinite");
				}
			}catch(NumberFormatException ex){

				this.currentTextArea.setText("infinite");
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