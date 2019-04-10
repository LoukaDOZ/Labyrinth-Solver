import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GridActionManagement implements MouseListener{

	private Panel currentPanel;
	private String action;

	private Window mapCreatingWindow;

	public GridActionManagement(Window mapCreatingWindow){

		this.mapCreatingWindow = mapCreatingWindow;
		this.action = "none";
	}

	public void setAction(String action){

		this.action = action;
	}

  //mouse clicked
  public void mouseClicked(MouseEvent e){

  	this.currentPanel = (Panel)e.getSource();
  	
  	if(this.action.equals("Rubber")){

  		this.currentPanel.setType(0);
  	}

  	if(this.action.equals("Put a wall")){

  		this.currentPanel.setType(1);
  	}

  	if(this.action.equals("Put the start")){

  		if(this.mapCreatingWindow.getPanelByType(2) != null){

  			this.mapCreatingWindow.getPanelByType(2).setType(0);
  		}

  		this.currentPanel.setType(2);
  	}

  	if(this.action.equals("Put the exit")){

  		if(this.mapCreatingWindow.getPanelByType(3) != null){

  			this.mapCreatingWindow.getPanelByType(3).setType(0);
  		}

  		this.currentPanel.setType(3);
  	}
}

	//mouse is hoveing
  public void mouseEntered(MouseEvent e) {}

  //mouse stop hovering
  public void mouseExited(MouseEvent e) {}

  //mouse pressed
  public void mousePressed(MouseEvent e){}

  //mouse released
  public void mouseReleased(MouseEvent e){}
}