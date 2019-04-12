import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuActionManagement implements MouseListener{

	private JLabel currentLabel;
	private String currentSelection;
	private String currentSelection2;
	private int size;

	private Window welcomeWindow;
	private Window createWindow;
	private Window mapCreatingWindow;
	private Window algorithmWindow;


	public MenuActionManagement(Window welcomeWindow, Window createWindow, Window mapCreatingWindow, Window algorithmWindow){

		this.currentSelection = "none";
		this.currentSelection2 = "none";
		this.size = 20;

		this.welcomeWindow = welcomeWindow;
		this.createWindow = createWindow;
		this.mapCreatingWindow = mapCreatingWindow;
		this.algorithmWindow = algorithmWindow;
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

	if(this.currentLabel == this.createWindow.getJLabelByText("Void") && this.currentSelection.equals("Void") == true){

	    this.currentLabel.setBackground(new Color(100,100,100));
	}

	if(this.currentLabel == this.createWindow.getJLabelByText("Random fill") && this.currentSelection.equals("Random fill") == true){

	    this.currentLabel.setBackground(new Color(100,100,100));
	}

	if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Rubber") && this.currentSelection.equals("Rubber") == true){

		this.currentLabel.setBackground(new Color(100,100,100));
	}

	if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put a wall") && this.currentSelection.equals("Put a wall") == true){

		this.currentLabel.setBackground(new Color(100,100,100));
	}

	if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put the start") && this.currentSelection.equals("Put the start") == true){

		this.currentLabel.setBackground(new Color(100,100,100));
	}

	if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put the exit") && this.currentSelection.equals("Put the exit") == true){

		this.currentLabel.setBackground(new Color(100,100,100));
	}

	if((this.currentLabel == this.algorithmWindow.getJLabelByText("Random") && this.currentSelection.equals("Random") == true) || (this.currentLabel == this.algorithmWindow.getJLabelByText("Determinist") && this.currentSelection.equals("Determinist") == true)
	|| (this.currentLabel == this.algorithmWindow.getJLabelByText("Manual") && this.currentSelection2.equals("Manual") == true) || (this.currentLabel == this.algorithmWindow.getJLabelByText("Automatic") && this.currentSelection2.equals("Automatic") == true)){

		this.currentLabel.setBackground(new Color(100,100,100));
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
		this.currentSelection = this.currentLabel.getText();
	}

	if(this.currentLabel == this.createWindow.getJLabelByText("Random fill")){

		this.currentLabel.setBackground(new Color(100,100,100));
		this.createWindow.getJLabelByText("Void").setBackground(new Color(0,0,0));
		this.currentSelection = this.currentLabel.getText();
	}

	if(this.currentLabel == this.createWindow.getJLabelByText("Done")){

		if(this.currentSelection.equals("none") == false){

			Panel panel = new Panel(this.mapCreatingWindow.getGridActionManagement());

			panel.setCreatingGrid(this.mapCreatingWindow,this.size,this.currentSelection.equals("Random fill"));

			this.mapCreatingWindow.add(panel,BorderLayout.CENTER);

			this.createWindow.setVisible(false);
			this.mapCreatingWindow.setVisible(true);
		}else{

			Window chooseFillWindow = new Window("Please choose a way to fill",this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 2,this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 4,true);
			chooseFillWindow.setGridLayout(3,1);
			chooseFillWindow.add(chooseFillWindow.getNewJLabel("",2),BorderLayout.CENTER);
			chooseFillWindow.add(chooseFillWindow.getNewJLabel("You have to choose a way to fill the grid",2),BorderLayout.CENTER);
			chooseFillWindow.add(chooseFillWindow.getNewJLabel("",2),BorderLayout.CENTER);
			chooseFillWindow.setVisible(true);

			TimerPopupWindow timer = new TimerPopupWindow(chooseFillWindow);
		}
	}

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
		this.currentSelection = this.currentLabel.getText();
		this.mapCreatingWindow.getGridActionManagement().setAction(this.currentLabel.getText());
	}

	if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put a wall")){

		this.currentLabel.setBackground(new Color(100,100,100));
		this.mapCreatingWindow.getJLabelByText("Rubber").setBackground(new Color(0,0,0));
		this.mapCreatingWindow.getJLabelByText("Put the start").setBackground(new Color(0,0,0));
		this.mapCreatingWindow.getJLabelByText("Put the exit").setBackground(new Color(0,0,0));
		this.currentSelection = this.currentLabel.getText();
		this.mapCreatingWindow.getGridActionManagement().setAction(this.currentLabel.getText());
	}

	if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put the start")){

		this.currentLabel.setBackground(new Color(100,100,100));
		this.mapCreatingWindow.getJLabelByText("Rubber").setBackground(new Color(0,0,0));
		this.mapCreatingWindow.getJLabelByText("Put a wall").setBackground(new Color(0,0,0));
		this.mapCreatingWindow.getJLabelByText("Put the exit").setBackground(new Color(0,0,0));
		this.currentSelection = this.currentLabel.getText();
		this.mapCreatingWindow.getGridActionManagement().setAction(this.currentLabel.getText());
	}

	if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put the exit")){

		this.currentLabel.setBackground(new Color(100,100,100));
		this.mapCreatingWindow.getJLabelByText("Rubber").setBackground(new Color(0,0,0));
		this.mapCreatingWindow.getJLabelByText("Put a wall").setBackground(new Color(0,0,0));
		this.mapCreatingWindow.getJLabelByText("Put the start").setBackground(new Color(0,0,0));
		this.currentSelection = this.currentLabel.getText();
		this.mapCreatingWindow.getGridActionManagement().setAction(this.currentLabel.getText());
	}

	if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Save the map")){

		if(this.mapCreatingWindow.getPanelByType(2) == null || this.mapCreatingWindow.getPanelByType(3) == null){

			Window setStartExitWindow = new Window("Please set the start and the exit position",this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 2,this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 4,true);
			setStartExitWindow.setGridLayout(4,1);
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

			saveWindow.setGridLayout(4,1);
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
			setStartExitWindow.setGridLayout(4,1);
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
			this.algorithmWindow.setVisible(true);
		}
	}

	if(this.currentLabel == this.algorithmWindow.getJLabelByText("Random")){

		this.currentLabel.setBackground(new Color(100,100,100));
		this.algorithmWindow.getJLabelByText("Determinist").setBackground(new Color(0,0,0));
		this.currentSelection = this.currentLabel.getText();
	}

	if(this.currentLabel == this.algorithmWindow.getJLabelByText("Determinist")){

		this.currentLabel.setBackground(new Color(100,100,100));
		this.algorithmWindow.getJLabelByText("Random").setBackground(new Color(0,0,0));
		this.currentSelection = this.currentLabel.getText();
	}

	if(this.currentLabel == this.algorithmWindow.getJLabelByText("Manual")){


		this.currentLabel.setBackground(new Color(100,100,100));
		this.algorithmWindow.getJLabelByText("Automatic").setBackground(new Color(0,0,0));
		this.currentSelection2 = this.currentLabel.getText();
	}

	if(this.currentLabel == this.algorithmWindow.getJLabelByText("Automatic")){

		this.currentLabel.setBackground(new Color(100,100,100));
		this.algorithmWindow.getJLabelByText("Manual").setBackground(new Color(0,0,0));
		this.currentSelection2 = this.currentLabel.getText();
	}

	if(this.currentLabel == this.algorithmWindow.getJLabelByText("Done")){

		if((this.currentSelection.equals("Random") == true || this.currentSelection.equals("Determinist") == true) && this.currentSelection2.equals("none") == false){

			StartSimulation startSimulation = new StartSimulation(this.size,this.mapCreatingWindow.getPanelTypeAsAnArray(this.size),this.currentSelection.equals("Random"),this.currentSelection2.equals("Manual"));
			this.algorithmWindow.setVisible(false);
		}else{

			Window chooseWindow = new Window("Please choose something",this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 2,this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 4,true);
			chooseWindow.setGridLayout(4,1);
			chooseWindow.add(chooseWindow.getNewJLabel("",2),BorderLayout.CENTER);

			if(this.currentSelection.equals("Random") == false && this.currentSelection.equals("Determinist") == false){

				chooseWindow.add(chooseWindow.getNewJLabel("You have to choose an algorithm",2),BorderLayout.CENTER);
			}else{

				chooseWindow.add(chooseWindow.getNewJLabel("",2),BorderLayout.CENTER);
			}

			if(this.currentSelection2.equals("none") == true){

				chooseWindow.add(chooseWindow.getNewJLabel("You have to choose a mode",2),BorderLayout.CENTER);
			}else{

				chooseWindow.add(chooseWindow.getNewJLabel("",2),BorderLayout.CENTER);
			}

			chooseWindow.add(chooseWindow.getNewJLabel("",2),BorderLayout.CENTER);
			chooseWindow.setVisible(true);

			TimerPopupWindow timer = new TimerPopupWindow(chooseWindow);
		}
	}
}

  //mouse pressed
  public void mousePressed(MouseEvent e){}

  //mouse released
  public void mouseReleased(MouseEvent e){}
}