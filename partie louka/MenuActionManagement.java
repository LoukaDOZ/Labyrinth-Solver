import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuActionManagement implements MouseListener{

	private JLabel currentLabel;
	private boolean mapCreatingWindowIsInitialized;
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
	private Window mainWindow;


	public MenuActionManagement(Window welcomeWindow, Window createWindow, Window mapCreatingWindow, Window mainWindow){

		this.mapCreatingWindowIsInitialized = false;
		this.voidLabel = false;
		this.fillLabel = false;
		this.removeLabel = false;
		this.wallLabel = false;
		this.startLabel = false;
		this.exitLabel = false;
		this.size = 20;

		this.welcomeWindow = welcomeWindow;
		this.createWindow = createWindow;
		this.mapCreatingWindow = mapCreatingWindow;
		this.mainWindow = mainWindow;
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

	if(this.currentLabel == this.createWindow.getJLabelByText("Void") && this.voidLabel == true){

	    this.currentLabel.setBackground(new Color(100,100,100));
	}

	if(this.currentLabel == this.createWindow.getJLabelByText("Random fill") && this.fillLabel == true){

	    this.currentLabel.setBackground(new Color(100,100,100));
	}

	if(this.mapCreatingWindowIsInitialized == true){

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Rubber") && this.removeLabel == true){

		    this.currentLabel.setBackground(new Color(100,100,100));
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put a wall") && this.wallLabel == true){

		    this.currentLabel.setBackground(new Color(100,100,100));
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put the start") && this.startLabel == true){

		    this.currentLabel.setBackground(new Color(100,100,100));
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put the exit") && this.exitLabel == true){

		    this.currentLabel.setBackground(new Color(100,100,100));
		}
	}
}

  //mouse clicked
  public void mouseClicked(MouseEvent e){

  	this.currentLabel = (JLabel)e.getSource();

	if(this.currentLabel == this.welcomeWindow.getJLabelByText("Open a grid")){

	  	this.welcomeWindow.setVisible(false);
	}

	if(this.currentLabel == this.welcomeWindow.getJLabelByText("Create a grid")){

	  	this.welcomeWindow.setVisible(false);
	  	this.createWindow.setVisible(true);
	}

	if(this.currentLabel == this.createWindow.getJLabelByText("Bigger")){

	  	if(this.size < 40){

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

		if(this.fillLabel == true || this.voidLabel == true){

			this.mapCreatingWindowIsInitialized = true;

			Panel panel1 = new Panel();
			panel1.setLayout(new GridLayout(9,1));
			panel1.add(this.mapCreatingWindow.getNewJLabel("  Change the map :  ",1));
			panel1.add(this.mapCreatingWindow.getNewJLabel("Reset","MenuActionManagement",1));
			panel1.add(this.mapCreatingWindow.getNewJLabel("Filled with walls","MenuActionManagement",1));
			panel1.add(this.mapCreatingWindow.getNewJLabel("Rubber","MenuActionManagement",1));
			panel1.add(this.mapCreatingWindow.getNewJLabel("Put a wall","MenuActionManagement",1));
			panel1.add(this.mapCreatingWindow.getNewJLabel("Put the start","MenuActionManagement",1));
			panel1.add(this.mapCreatingWindow.getNewJLabel("Put the exit","MenuActionManagement",1));
			panel1.add(this.mapCreatingWindow.getNewJLabel("Save the map","MenuActionManagement",1));
			panel1.add(this.mapCreatingWindow.getNewJLabel("Done","MenuActionManagement",1));

			Panel panel2 = new Panel(this.mapCreatingWindow.getGridActionManagement());
			panel2.setCreatingGrid(this.mapCreatingWindow,this.size,this.fillLabel);

			this.mapCreatingWindow.add(panel1,BorderLayout.WEST);
			this.mapCreatingWindow.add(panel2,BorderLayout.CENTER);

			this.createWindow.setVisible(false);
			this.mapCreatingWindow.setVisible(true);
		}else{

			Window chooseFillWindow = new Window("Please choose a way to fill",this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 2,this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 4,true);
			chooseFillWindow.setGridLayoutBigger(3,1);
			chooseFillWindow.add(chooseFillWindow.getNewJLabel("",2),BorderLayout.CENTER);
			chooseFillWindow.add(chooseFillWindow.getNewJLabel("You have to choose a way to fill the grid",2),BorderLayout.CENTER);
			chooseFillWindow.add(chooseFillWindow.getNewJLabel("",2),BorderLayout.CENTER);
			chooseFillWindow.setVisible(true);

			TimerPopupWindow timer = new TimerPopupWindow(chooseFillWindow);
		}
	}

	if(this.mapCreatingWindowIsInitialized == true){

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Reset")){

			for(int i = 0; i < this.mapCreatingWindow.getTotalPanel(); i++){

				this.mapCreatingWindow.getPanelByID(i).setType(0);
			}
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Filled with walls")){

			for(int i = 0; i < this.mapCreatingWindow.getTotalPanel(); i++){

				this.mapCreatingWindow.getPanelByID(i).setType(1);
			}
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Rubber")){

			this.currentLabel.setBackground(new Color(100,100,100));
			this.mapCreatingWindow.getJLabelByText("Put a wall").setBackground(new Color(0,0,0));
			this.mapCreatingWindow.getJLabelByText("Put the start").setBackground(new Color(0,0,0));
			this.mapCreatingWindow.getJLabelByText("Put the exit").setBackground(new Color(0,0,0));
			this.removeLabel = true;
			this.wallLabel = false;
			this.startLabel = false;
			this.exitLabel = false;
			this.mapCreatingWindow.getGridActionManagement().setAction(this.currentLabel.getText());
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put a wall")){

			this.currentLabel.setBackground(new Color(100,100,100));
			this.mapCreatingWindow.getJLabelByText("Rubber").setBackground(new Color(0,0,0));
			this.mapCreatingWindow.getJLabelByText("Put the start").setBackground(new Color(0,0,0));
			this.mapCreatingWindow.getJLabelByText("Put the exit").setBackground(new Color(0,0,0));
			this.wallLabel = true;
			this.removeLabel = false;
			this.startLabel = false;
			this.exitLabel = false;
			this.mapCreatingWindow.getGridActionManagement().setAction(this.currentLabel.getText());
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put the start")){

			this.currentLabel.setBackground(new Color(100,100,100));
			this.mapCreatingWindow.getJLabelByText("Rubber").setBackground(new Color(0,0,0));
			this.mapCreatingWindow.getJLabelByText("Put a wall").setBackground(new Color(0,0,0));
			this.mapCreatingWindow.getJLabelByText("Put the exit").setBackground(new Color(0,0,0));
			this.startLabel = true;
			this.removeLabel = false;
			this.wallLabel = false;
			this.exitLabel = false;
			this.mapCreatingWindow.getGridActionManagement().setAction(this.currentLabel.getText());
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put the exit")){

			this.currentLabel.setBackground(new Color(100,100,100));
			this.mapCreatingWindow.getJLabelByText("Rubber").setBackground(new Color(0,0,0));
			this.mapCreatingWindow.getJLabelByText("Put a wall").setBackground(new Color(0,0,0));
			this.mapCreatingWindow.getJLabelByText("Put the start").setBackground(new Color(0,0,0));
			this.exitLabel = true;
			this.removeLabel = false;
			this.wallLabel = false;
			this.startLabel = false;
			this.mapCreatingWindow.getGridActionManagement().setAction(this.currentLabel.getText());
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Save the map")){

			if(this.mapCreatingWindow.getPanelByType(2) == null || this.mapCreatingWindow.getPanelByType(3) == null){

				Window setStartExitWindow = new Window("Please set the start and the exit position",this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 2,this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 4,true);
				setStartExitWindow.setGridLayoutBigger(4,1);
				setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);

				if(this.mapCreatingWindow.getPanelByType(2) == null){

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("You have to choose the starting position",2),BorderLayout.CENTER);
				}else{

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);
				}

				if(this.mapCreatingWindow.getPanelByType(3) == null){

						setStartExitWindow.add(setStartExitWindow.getNewJLabel("You have to choose the exit position",2),BorderLayout.CENTER);
					}else{

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);
				}

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);
				setStartExitWindow.setVisible(true);

				TimerPopupWindow timer = new TimerPopupWindow(setStartExitWindow);
				}else{

				Window saveWindow = new Window("Enter a file name",this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 2,this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 4,true);
				SaveActionManagement saveActionManagement = new SaveActionManagement(saveWindow);
				saveWindow.setManagement(saveActionManagement);

				saveWindow.setGridLayoutBigger(4,1);
				saveWindow.add(saveWindow.getNewJLabel("Please enter the file name :",2),BorderLayout.CENTER);
				saveWindow.add(saveWindow.getNewJTextArea("Enter file name"),BorderLayout.CENTER);
				saveWindow.add(saveWindow.getNewJLabel("Cancel","SaveActionManagement",2),BorderLayout.CENTER);
				saveWindow.add(saveWindow.getNewJLabel("Done","SaveActionManagement",2),BorderLayout.CENTER);
				saveWindow.setVisible(true);
				}		
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Done")){

			if(this.mapCreatingWindow.getPanelByType(2) == null || this.mapCreatingWindow.getPanelByType(3) == null){

				Window setStartExitWindow = new Window("Please set the start and the exit position",this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 2,this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 4,true);
				setStartExitWindow.setGridLayoutBigger(4,1);
				setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);

				if(this.mapCreatingWindow.getPanelByType(2) == null){

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("You have to choose the starting position",2),BorderLayout.CENTER);
				}else{

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);
				}

				if(this.mapCreatingWindow.getPanelByType(3) == null){

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("You have to choose the exit position",2),BorderLayout.CENTER);
				}else{

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);
				}

				setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);
				setStartExitWindow.setVisible(true);

				TimerPopupWindow timer = new TimerPopupWindow(setStartExitWindow);
			}else{

				this.mapCreatingWindow.setVisible(false);
				this.mainWindow.setVisible(true);
			}
		}
	}
}

  //mouse pressed
  public void mousePressed(MouseEvent e){}

  //mouse released
  public void mouseReleased(MouseEvent e){}
}