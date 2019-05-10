import javax.swing.*;
import java.awt.*;

public class ManualSimulation{

	private Window simulationWindow;
	private Window optionsWindow;
	private Window finalWindow;

	private Algorithm algorithm;

	private int[] typeArray;
	private int[] passedByArray;
	private int maxRounds;
	private int round;
	private int gridSize;
	private int nextPanelID;
	private int exitID;

	private String nextDirection;

	private boolean isRandom;

	public ManualSimulation(int gridSize, int[] typeArray, boolean isRandom, String maxRounds){

		this.maxRounds = Integer.parseInt(maxRounds);
		this.round = 0;
		this.typeArray = typeArray;
		this.gridSize = gridSize;
		this.isRandom = isRandom;
		this.nextDirection = "Undefined";
		this.nextPanelID = -1;

		//get usable size of the screen
    	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int windowSizeX = (int)dimension.getWidth();
    	int windowSizeY = (int)dimension.getHeight();

		this.simulationWindow = new Window("Simulation",windowSizeX,(windowSizeY / 4) * 3,0,windowSizeY / 4,true);
		this.optionsWindow = new Window("Your options",windowSizeX,windowSizeY / 4,0,0,true);
		this.finalWindow = new Window("Simulation ended",windowSizeX,windowSizeY,0,0,true);

		this.optionsWindow.setGridLayout(2,5);

		this.optionsWindow.add(this.optionsWindow.getNewJLabel("Starting grid :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Algorithm :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Mode :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Next direction :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Round :",1),BorderLayout.CENTER);

	    this.optionsWindow.getJLabelByText("Starting grid :").setBackground(new Color(50,50,50));
	    this.optionsWindow.getJLabelByText("Algorithm :").setBackground(new Color(50,50,50));
	    this.optionsWindow.getJLabelByText("Mode :").setBackground(new Color(50,50,50));
	    this.optionsWindow.getJLabelByText("Next direction :").setBackground(new Color(255,180,200));
	    this.optionsWindow.getJLabelByText("Round :").setBackground(new Color(50,50,50));

	    this.optionsWindow.add(this.getStartingGridPanel(this.optionsWindow,this.gridSize),BorderLayout.CENTER);
	    this.simulationWindow.add(this.getStartingGridPanel(this.simulationWindow,this.gridSize),BorderLayout.CENTER);

	    if(isRandom == true){

	    	this.optionsWindow.add(this.optionsWindow.getNewJLabel("Random",1),BorderLayout.CENTER);
	    }else{

	    	this.optionsWindow.add(this.optionsWindow.getNewJLabel("Determinist",1),BorderLayout.CENTER);
	    }

		this.optionsWindow.add(this.optionsWindow.getNewJLabel("Manual",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel(this.nextDirection,1),BorderLayout.CENTER);
		this.optionsWindow.add(this.optionsWindow.getNewJLabel(this.round+"/"+this.maxRounds,1),BorderLayout.CENTER);

	    this.finalWindow.add(this.finalWindow.getNewJLabel("Simulation ended",2),BorderLayout.NORTH);

	    ManualManagement keyPressedManagement = new ManualManagement(this);
		this.simulationWindow.addKeyListener(keyPressedManagement);
		this.optionsWindow.addKeyListener(keyPressedManagement);

		this.algorithm = new Algorithm(isRandom,this,simulationWindow,this.gridSize);

		this.simulationWindow.setVisible(true);
		this.optionsWindow.setVisible(true);

		this.pause("start simulation M");
	}

	public void pause(String pauseType){

		Timer timer = new Timer(10,null);
    	timer.addActionListener(new TimerManagement(this,timer,pauseType));
	}
	
	public void startSimulation(){

		this.exitID = this.simulationWindow.getPanelByType(3).getID();
		this.nextRound();	
	}

	public void nextRound(){

		this.round++;
		this.optionsWindow.getJLabelByText((this.round - 1)+"/"+this.maxRounds).setText(this.round+"/"+this.maxRounds);

		JLabel currentDirection = this.optionsWindow.getJLabelByText(this.nextDirection);

		this.nextDirection = this.algorithm.getDirection();
		currentDirection.setText(this.nextDirection);
	}

	public void move(){

		this.simulationWindow.getPanelByType(2).setType(0);
		this.simulationWindow.getPanelByID(this.nextPanelID).setType(2);

		if(this.simulationWindow.getPanelByType(2).getID() == this.exitID || this.round == this.maxRounds){

			if(this.round == this.maxRounds){

				this.endSimulation(false);
			}else{

				this.endSimulation(true);
			}
		}else{

			this.nextRound();
		}
	}

	public void endSimulation(boolean exitIsFound){

		this.optionsWindow.setVisible(false);
		this.simulationWindow.setVisible(false);

		Panel finalInformationsPanel = new Panel();
		finalInformationsPanel.setLayout(new GridLayout(2,2));

		finalInformationsPanel.add(this.finalWindow.getNewJLabel("Exit found :",2),BorderLayout.CENTER);
		finalInformationsPanel.add(this.finalWindow.getNewJLabel(""+exitIsFound,2),BorderLayout.CENTER);
		finalInformationsPanel.add(this.finalWindow.getNewJLabel("Number of rounds :",2),BorderLayout.CENTER);
		finalInformationsPanel.add(this.finalWindow.getNewJLabel(this.round+"/"+this.maxRounds,2),BorderLayout.CENTER);

		this.finalWindow.getJLabelByText("Simulation ended").setBackground(new Color(180,180,180));
		this.finalWindow.getJLabelByText("Simulation ended").setForeground(new Color(0,0,0));
		this.finalWindow.getJLabelByText("Exit found :").setBackground(new Color(50,50,50));
	    this.finalWindow.getJLabelByText("Number of rounds :").setBackground(new Color(50,50,50));

	    this.finalWindow.add(finalInformationsPanel,BorderLayout.CENTER);

		this.finalWindow.setVisible(true);
	}

	public Panel getStartingGridPanel(Window window,int gridSize){

		Panel gridPanel = new Panel();
		gridPanel.setNewGrid(window,this.gridSize,false);

		for(int i = 0; i < this.gridSize; i++){

	      for(int j = 0; j < this.gridSize; j++){

	        window.getPanelByID(j + (i * this.gridSize)).setType(this.typeArray[i + (j * this.gridSize)]);
	      }
	    }

	    return gridPanel;
	}

	public void setNextPanelID(int id){

		this.nextPanelID = id;
	}

	public int getNextPanelID(){

		return this.nextPanelID;
	}
}