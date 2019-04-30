import javax.swing.*;
import java.awt.*;

public class Simulation{

	private Window simulationWindow;
	private Window optionsWindow;

	private int[] typeArray;
	private String nextDirection;
	private int round;
	private int gridSize;
	private int nextPanelID;
	private int exitID;
	private boolean isRandom;

	public Simulation(){

		//get usable size of the screen
    	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int windowSizeX = (int)dimension.getWidth();
    	int windowSizeY = (int)dimension.getHeight();

		this.simulationWindow = new Window("Simulation",windowSizeX,(windowSizeY / 4) * 3,0,windowSizeY / 4,true);
		this.optionsWindow = new Window("Your options",windowSizeX,windowSizeY / 4,0,0,true);

		this.optionsWindow.add(this.optionsWindow.getNewJLabel("Starting grid :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Algorithm :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Mode :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Next direction :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Round :",1),BorderLayout.CENTER);

	    this.optionsWindow.getJLabelByText("Starting grid :").setBackground(new Color(50,50,50));
	    this.optionsWindow.getJLabelByText("Algorithm :").setBackground(new Color(50,50,50));
	    this.optionsWindow.getJLabelByText("Mode :").setBackground(new Color(50,50,50));
	    this.optionsWindow.getJLabelByText("Next direction :").setBackground(new Color(255,200,200));
	    this.optionsWindow.getJLabelByText("Round :").setBackground(new Color(50,50,50));

		this.round = 0;
		this.nextDirection = "Undefined";
		this.nextPanelID = -1;
	}
	
	public void startSimulation(int gridSize, int[] typeArray, boolean isRandom, boolean isManual){

		this.typeArray = typeArray;
		this.gridSize = gridSize;
		this.isRandom = isRandom;

		this.optionsWindow.setGridLayout(2,3);

		Panel optionsGridPanel = this.getStartingGridPanel(this.optionsWindow,this.gridSize);
	    this.optionsWindow.add(optionsGridPanel,BorderLayout.CENTER);

	    this.optionsWindow.getPanelByType(2).getJLabel().setIcon(null);
	    this.optionsWindow.getPanelByType(2).setBackground(new Color(0,0,255));
	    this.optionsWindow.getPanelByType(3).getJLabel().setIcon(null);
	    this.optionsWindow.getPanelByType(3).setBackground(new Color(0,150,0));

	    if(isRandom == true){

	    	this.optionsWindow.add(this.optionsWindow.getNewJLabel("Random",1),BorderLayout.CENTER);
	    }else{

	    	this.optionsWindow.add(this.optionsWindow.getNewJLabel("Determinist",1),BorderLayout.CENTER);
	    }

	    if(isManual == true){

	    	this.optionsWindow.add(this.optionsWindow.getNewJLabel("Manual",1),BorderLayout.CENTER);
	    }else{

	    	this.optionsWindow.add(this.optionsWindow.getNewJLabel("Automatic",1),BorderLayout.CENTER);
	    }

	    this.optionsWindow.add(this.optionsWindow.getNewJLabel(this.nextDirection,1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel(""+this.round,1),BorderLayout.CENTER);

		if(isManual == true){

			ManualManagement keyPressedManagement = new ManualManagement(this);
			this.simulationWindow.addKeyListener(keyPressedManagement);
			this.optionsWindow.addKeyListener(keyPressedManagement);

			Panel simulationGridPanel = this.getStartingGridPanel(this.simulationWindow,this.gridSize);
			this.simulationWindow.add(simulationGridPanel,BorderLayout.CENTER);
			this.simulationWindow.setVisible(true);

			this.optionsWindow.setVisible(true);

			this.exitID = this.simulationWindow.getPanelByType(3).getID();
			this.nextRound();
		}else{

			Panel simulationGridPanel = this.getStartingGridPanel(this.simulationWindow,this.gridSize);
			this.simulationWindow.add(this.simulationWindow.getNewJLabel("Processing simulation",2),BorderLayout.CENTER);
			this.simulationWindow.setVisible(true);

			this.optionsWindow.setVisible(true);

			this.exitID = this.simulationWindow.getPanelByType(3).getID();
			this.nextRound();
			AutomaticManagement automaticManagement = new AutomaticManagement(this);
		}	
	}

	public void nextRound(){

		this.round++;
		this.optionsWindow.getJLabelByText(""+(this.round - 1)).setText(""+this.round);

		this.setNextDirection();
	}

	public void setNextDirection(){

		JLabel currentDirection = this.optionsWindow.getJLabelByText(this.nextDirection);

		if(this.isRandom == true){

			this.nextDirection = this.getRandomDirection();
		}

		currentDirection.setText(this.nextDirection);
	}

	public String getRandomDirection(){

		int direction;
		int playerPosition = this.simulationWindow.getPanelByType(2).getID();

		while(true){

			direction = (int)(Math.random() * 4);

			if(direction == 0 && playerPosition > this.gridSize && this.simulationWindow.getPanelByID(playerPosition - this.gridSize).getType() != 1){

				this.nextPanelID = playerPosition - this.gridSize;
				this.simulationWindow.getPanelByID(this.nextPanelID).setBackground(new Color(255,200,200));
			
				return "North";
			}

			if(direction == 1 && ((playerPosition + 1) % this.gridSize) != 0 && this.simulationWindow.getPanelByID(playerPosition + 1).getType() != 1){

				this.nextPanelID = playerPosition + 1;
				this.simulationWindow.getPanelByID(this.nextPanelID).setBackground(new Color(255,200,200));

				return "East";
			}

			if(direction == 2 && playerPosition < ((this.gridSize * this.gridSize) - this.gridSize) && this.simulationWindow.getPanelByID(playerPosition + this.gridSize).getType() != 1){

				this.nextPanelID = playerPosition + this.gridSize;
				this.simulationWindow.getPanelByID(this.nextPanelID).setBackground(new Color(255,200,200));
			
				return "South";
			}

			if(direction == 3 && (playerPosition % this.gridSize) != 0 && this.simulationWindow.getPanelByID(playerPosition - 1).getType() != 1){

				this.nextPanelID = playerPosition - 1;
				this.simulationWindow.getPanelByID(this.nextPanelID).setBackground(new Color(255,200,200));
			
				return "West";
			}
		}
	}

	public void move(){

		this.simulationWindow.getPanelByType(2).setType(0,this.gridSize);
		this.simulationWindow.getPanelByID(this.nextPanelID).setType(2,this.gridSize);

		if(this.simulationWindow.getPanelByType(2).getID() == this.exitID){

			System.exit(1);
		}else{

			this.nextRound();
		}
	}

	public Panel getStartingGridPanel(Window window,int gridSize){

		Panel gridPanel = new Panel(window.getHeight());
		gridPanel.setCreatingGrid(window,window.getHeight(),this.gridSize,false);

		for(int i = 0; i < this.gridSize; i++){

	      for(int j = 0; j < this.gridSize; j++){

	        window.getPanelByID(j + (i * this.gridSize)).setType(this.typeArray[i + (j * this.gridSize)],this.gridSize);
	      }
	    }

	    return gridPanel;
	}
}