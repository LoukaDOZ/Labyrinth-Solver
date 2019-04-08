import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuActionManagement implements MouseListener{

	private JLabel currentLabel;
	private boolean voidLabel;
	private boolean fillLabel;
	private boolean removeLabel;
	private boolean wallLabel;
	private boolean startLabel;
	private boolean exitLabel;
	private int size;
	private Window welcomeWindow;
	private Window createWindow;
	private Window mapCreatingWindow;
	private Window mapCreatingOptionsWindow;
	private Window mainWindow;


	public MenuActionManagement(){

		this.voidLabel = false;
		this.fillLabel = false;
		this.removeLabel = false;
		this.wallLabel = false;
		this.startLabel = false;
		this.exitLabel = false;
		this.size = 15;
	}

	public void addWindows(Window welcomeWindow, Window createWindow, Window mapCreatingWindow, Window mapCreatingOptionsWindow, Window mainWindow){

		this.welcomeWindow = welcomeWindow;
		this.createWindow = createWindow;
		this.mapCreatingWindow = mapCreatingWindow;
		this.mapCreatingOptionsWindow = mapCreatingOptionsWindow;
		this.mainWindow = mainWindow;
	}

	public int getGridSize(){

		return this.size;
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

  	if(this.createWindow != null){

	    if(this.currentLabel == this.createWindow.getJLabelByText("Void") && this.voidLabel == true){

	    	this.currentLabel.setBackground(new Color(100,100,100));
	    }

	    if(this.currentLabel == this.createWindow.getJLabelByText("Random fill") && this.fillLabel == true){

	    	this.currentLabel.setBackground(new Color(100,100,100));
	    }
	}

    if(this.mapCreatingOptionsWindow != null){

	    if(this.currentLabel == this.mapCreatingOptionsWindow.getJLabelByText("Remove a wall") && this.removeLabel == true){

	    	this.currentLabel.setBackground(new Color(100,100,100));
	    }

	    if(this.currentLabel == this.mapCreatingOptionsWindow.getJLabelByText("Put a wall") && this.wallLabel == true){

	    	this.currentLabel.setBackground(new Color(100,100,100));
	    }

	    if(this.currentLabel == this.mapCreatingOptionsWindow.getJLabelByText("Put starting position") && this.startLabel == true){

	    	this.currentLabel.setBackground(new Color(100,100,100));
	    }

	    if(this.currentLabel == this.mapCreatingOptionsWindow.getJLabelByText("Put the exit") && this.exitLabel == true){

	    	this.currentLabel.setBackground(new Color(100,100,100));
	    }
	}
}

  //mouse clicked
  public void mouseClicked(MouseEvent e){

  	this.currentLabel = (JLabel)e.getSource();

  	if(this.welcomeWindow != null){

	  	if(this.currentLabel == this.welcomeWindow.getJLabelByText("Open a grid")){

	  		this.welcomeWindow.setVisible(false);
	  	}

	  	if(this.currentLabel == this.welcomeWindow.getJLabelByText("Create a grid")){

	  		this.welcomeWindow.setVisible(false);
	  		this.createWindow.setVisible(true);
	  	}
  	}

  	if(this.createWindow != null){

	  	if(this.currentLabel == this.createWindow.getJLabelByText("Bigger")){

	  		if(this.size < 30){

	  			this.size++;
	  			this.createWindow.getJLabelByText("Choose the size of the grid ["+(this.size - 1)+"x"+(this.size - 1)+"] :").setText("Choose the size of the grid ["+this.size+"x"+this.size+"] :");
	  		}
	  	}

	  	if(this.currentLabel == this.createWindow.getJLabelByText("Smaller")){

	  		if(this.size > 5){

	  			this.size--;
	  			this.createWindow.getJLabelByText("Choose the size of the grid ["+(this.size + 1)+"x"+(this.size + 1)+"] :").setText("Choose the size of the grid ["+this.size+"x"+this.size+"] :");
	  		}
	  	}

	  	if(this.currentLabel == this.createWindow.getJLabelByText("Void")){

	  		this.currentLabel.setBackground(new Color(100,100,100));
	  		this.createWindow.getJLabelByText("Random fill").setBackground(new Color(0,0,0));
	  		this.voidLabel = true;
	  		this.fillLabel = false;
	  	}

	  	if(this.currentLabel == this.createWindow.getJLabelByText("Random fill")){

	  		this.currentLabel.setBackground(new Color(100,100,100));
	  		this.createWindow.getJLabelByText("Void").setBackground(new Color(0,0,0));
	  		this.voidLabel = false;
	  		this.fillLabel = true;
	  	}

	  	if(this.currentLabel == this.createWindow.getJLabelByText("Done")){

	  		this.createWindow.setVisible(false);
	  		this.mapCreatingWindow.setVisible(true);
	  		this.mapCreatingOptionsWindow.setVisible(true);
	  	}
	  }

  	if(this.mapCreatingOptionsWindow != null){

	  	if(this.currentLabel == this.mapCreatingOptionsWindow.getJLabelByText("Remove a wall")){

	  		this.currentLabel.setBackground(new Color(100,100,100));
	  		this.mapCreatingOptionsWindow.getJLabelByText("Put a wall").setBackground(new Color(0,0,0));
	  		this.mapCreatingOptionsWindow.getJLabelByText("Put starting position").setBackground(new Color(0,0,0));
	  		this.mapCreatingOptionsWindow.getJLabelByText("Put the exit").setBackground(new Color(0,0,0));
	  		this.removeLabel = true;
	  		this.wallLabel = false;
	  		this.startLabel = false;
	  		this.exitLabel = false;
	  	}

	  	if(this.currentLabel == this.mapCreatingOptionsWindow.getJLabelByText("Put a wall")){

	  		this.currentLabel.setBackground(new Color(100,100,100));
	  		this.mapCreatingOptionsWindow.getJLabelByText("Remove a wall").setBackground(new Color(0,0,0));
	  		this.mapCreatingOptionsWindow.getJLabelByText("Put starting position").setBackground(new Color(0,0,0));
	  		this.mapCreatingOptionsWindow.getJLabelByText("Put the exit").setBackground(new Color(0,0,0));
	  		this.wallLabel = true;
	  		this.removeLabel = false;
	  		this.startLabel = false;
	  		this.exitLabel = false;
	  	}

	  	if(this.currentLabel == this.mapCreatingOptionsWindow.getJLabelByText("Put starting position")){

	  		this.currentLabel.setBackground(new Color(100,100,100));
	  		this.mapCreatingOptionsWindow.getJLabelByText("Remove a wall").setBackground(new Color(0,0,0));
	  		this.mapCreatingOptionsWindow.getJLabelByText("Put a wall").setBackground(new Color(0,0,0));
	  		this.mapCreatingOptionsWindow.getJLabelByText("Put the exit").setBackground(new Color(0,0,0));
	  		this.startLabel = true;
	  		this.removeLabel = false;
	  		this.wallLabel = false;
	  		this.exitLabel = false;
	  	}

	  	if(this.currentLabel == this.mapCreatingOptionsWindow.getJLabelByText("Put the exit")){

	  		this.currentLabel.setBackground(new Color(100,100,100));
	  		this.mapCreatingOptionsWindow.getJLabelByText("Remove a wall").setBackground(new Color(0,0,0));
	  		this.mapCreatingOptionsWindow.getJLabelByText("Put a wall").setBackground(new Color(0,0,0));
	  		this.mapCreatingOptionsWindow.getJLabelByText("Put starting position").setBackground(new Color(0,0,0));
	  		this.exitLabel = true;
	  		this.removeLabel = false;
	  		this.wallLabel = false;
	  		this.startLabel = false;
	  	}

	  	if(this.currentLabel == this.mapCreatingOptionsWindow.getJLabelByText("Done")){

	  		this.mapCreatingWindow.setVisible(false);
	  		this.mapCreatingOptionsWindow.setVisible(false);
	  		this.mainWindow.setVisible(true);
	  	}
	  }
}

  //mouse pressed
  public void mousePressed(MouseEvent e){}

  //mouse released
  public void mouseReleased(MouseEvent e){}
}