import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JTextAreaManagement implements MouseListener{

	private JTextArea currentTextArea;

	public JTextAreaManagement() {}

	//mouse clicked
	public void mouseClicked(MouseEvent e){

		this.currentTextArea = (JTextArea)e.getSource();

		this.currentTextArea.setText("");
	}

	//mouse just moved
	public void mouseMoved(MouseEvent e){}

	//mouse is hoveing
	public void mouseEntered(MouseEvent e) {}

	//mouse stop hovering
	public void mouseExited(MouseEvent e) {}

	//mouse pressed
	public void mousePressed(MouseEvent e){}

	//mouse released
	public void mouseReleased(MouseEvent e){}
}