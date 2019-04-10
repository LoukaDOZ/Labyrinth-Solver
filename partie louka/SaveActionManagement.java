import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SaveActionManagement implements MouseListener{

	private JLabel currentLabel;
	private Window saveWindow;


	public SaveActionManagement(Window saveWindow){

		this.saveWindow = saveWindow;
	}

	//mouse is hoveing
  public void mouseEntered(MouseEvent e) {

    this.currentLabel = (JLabel)e.getSource();
    this.currentLabel.setBackground(new Color(100,100,100));
  }

  //mouse stop hovering
  public void mouseExited(MouseEvent e) {

  	this.currentLabel = (JLabel)e.getSource();
  	this.currentLabel.setBackground(new Color(0,0,0));
}

  //mouse clicked
  public void mouseClicked(MouseEvent e){

  	this.currentLabel = (JLabel)e.getSource();

  	if(this.saveWindow != null){
  	}
}

  //mouse pressed
  public void mousePressed(MouseEvent e){}

  //mouse released
  public void mouseReleased(MouseEvent e){}
}