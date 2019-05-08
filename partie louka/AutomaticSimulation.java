import javax.swing.*;
import java.awt.*;

public class AutomaticSimulation{

	private Window simulationWindow;
	private Window optionsWindow;
	private Window finalWindow;

	private Algorithm algorithm;

	private int[] typeArray;
	private int[] passedByArray;
	private int maxRounds;
	private int round;
	private int gridSize;
	private int maxSimulationNumber;
	private int simulationNumber;
	private int nextPanelID;
	private int exitID;
	private int roundsSum;
	private int numberOfExitFound;

	private String nextDirection;
	private String maxRoundsString;

	private boolean isRandom;


	public AutomaticSimulation(int gridSize, int[] typeArray, boolean isRandom, String maxRounds){

		if(maxRounds.equals("infinite")){

			this.maxRounds = -1;
		}else{

			this.maxRounds = Integer.parseInt(maxRounds);
		}

		this.maxRoundsString = maxRounds;
		this.typeArray = typeArray;
		this.gridSize = gridSize;
		this.isRandom = isRandom;
		this.simulationNumber = 1;
		this.roundsSum = 0;
		this.numberOfExitFound = 0;

		//get usable size of the screen
    	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int windowSizeX = (int)dimension.getWidth();
    	int windowSizeY = (int)dimension.getHeight();

		this.simulationWindow = new Window("Simulation",windowSizeX,(windowSizeY / 4) * 3,0,windowSizeY / 4,true);
		this.optionsWindow = new Window("Your options",windowSizeX,windowSizeY / 4,0,0,true);
		this.finalWindow = new Window("Simulation ended",windowSizeX,windowSizeY,0,0,true);

		this.simulationWindow.add(this.optionsWindow.getNewJLabel("Processing simulation...",2),BorderLayout.CENTER);

		this.optionsWindow.setGridLayout(2,5);

		this.optionsWindow.add(this.optionsWindow.getNewJLabel("Starting grid :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Algorithm :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Mode :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Simulation :",1),BorderLayout.CENTER);

	    this.optionsWindow.getJLabelByText("Starting grid :").setBackground(new Color(50,50,50));
	    this.optionsWindow.getJLabelByText("Algorithm :").setBackground(new Color(50,50,50));
	    this.optionsWindow.getJLabelByText("Mode :").setBackground(new Color(50,50,50));
	    this.optionsWindow.getJLabelByText("Simulation :").setBackground(new Color(50,50,50));

		Panel gridPanel = this.getStartingGridPanel(this.optionsWindow,this.gridSize);
	    this.optionsWindow.add(gridPanel,BorderLayout.CENTER);

	    this.optionsWindow.getPanelByType(2).getJLabel().setIcon(null);
	    this.optionsWindow.getPanelByType(2).setBackground(new Color(0,0,255));
	    this.optionsWindow.getPanelByType(3).getJLabel().setIcon(null);
	    this.optionsWindow.getPanelByType(3).setBackground(new Color(0,150,0));

	    if(isRandom == true){

	    	this.optionsWindow.add(this.optionsWindow.getNewJLabel("Random",1),BorderLayout.CENTER);
	    	this.maxSimulationNumber = 100;
	    }else{

	    	this.optionsWindow.add(this.optionsWindow.getNewJLabel("Determinist",1),BorderLayout.CENTER);
	    	this.maxSimulationNumber = 1;
	    }

		this.optionsWindow.add(this.optionsWindow.getNewJLabel("Automatic",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel(this.simulationNumber+"/"+this.maxSimulationNumber,1),BorderLayout.CENTER);

	    this.finalWindow.add(this.finalWindow.getNewJLabel("Simulation ended",2),BorderLayout.NORTH);

	    this.newSimulation();

	    this.algorithm = new Algorithm(isRandom,this,simulationWindow,this.gridSize);

	    this.simulationWindow.setVisible(true);
		this.optionsWindow.setVisible(true);

		Pause pause = new Pause(this);
	}

	public void newSimulation(){

		this.round = 0;
		this.nextDirection = "Undefined";
		this.nextPanelID = -1;

		this.simulationWindow.removePanelArray();
		Panel gridPanel = this.getStartingGridPanel(this.simulationWindow,this.gridSize);
	}
	
	public void startSimulation(){

		this.exitID = this.simulationWindow.getPanelByType(3).getID();
		this.nextRound();
	}

	public void nextRound(){

		this.round++;

		String trash = this.algorithm.getDirection();

		this.move();
	}

	public void move(){

		this.simulationWindow.getPanelByType(2).setType(0,this.gridSize);
		this.simulationWindow.getPanelByID(this.nextPanelID).setType(2,this.gridSize);

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

		if(exitIsFound == true){

			this.numberOfExitFound++;
			this.roundsSum += this.round;
		}

		if(this.simulationNumber < this.maxSimulationNumber){

			JLabel simulationNumberLabel = this.optionsWindow.getJLabelByText(this.simulationNumber+"/"+this.maxSimulationNumber);
			this.simulationNumber++;
			this.optionsWindow.getJLabelByText((this.simulationNumber - 1)+"/"+this.maxSimulationNumber).setText(this.simulationNumber+"/"+this.maxSimulationNumber);

			this.newSimulation();
			Pause pause = new Pause(this);

		}else{

			this.optionsWindow.setVisible(false);
			this.simulationWindow.setVisible(false);

			Panel finalInformationsPanel = new Panel();
			finalInformationsPanel.setLayout(new GridLayout(2,2));

			finalInformationsPanel.add(this.finalWindow.getNewJLabel("Exits found :",2),BorderLayout.CENTER);
			finalInformationsPanel.add(this.finalWindow.getNewJLabel(this.numberOfExitFound+"/"+this.maxSimulationNumber,2),BorderLayout.CENTER);
			finalInformationsPanel.add(this.finalWindow.getNewJLabel("Average of rounds to find the exit :",1),BorderLayout.CENTER);
			finalInformationsPanel.add(this.finalWindow.getNewJLabel(((int)(this.roundsSum / this.maxSimulationNumber))+"/"+this.maxRoundsString,2),BorderLayout.CENTER);

			this.finalWindow.getJLabelByText("Simulation ended").setBackground(new Color(180,180,180));
			this.finalWindow.getJLabelByText("Simulation ended").setForeground(new Color(0,0,0));
			this.finalWindow.getJLabelByText("Exits found :").setBackground(new Color(50,50,50));
		    this.finalWindow.getJLabelByText("Average of rounds to find the exit :").setBackground(new Color(50,50,50));

		    this.finalWindow.add(finalInformationsPanel,BorderLayout.CENTER);

			this.finalWindow.setVisible(true);
		}
	}

	public void getRandomDirection(){

		int direction;
		int playerPosition = this.simulationWindow.getPanelByType(2).getID();

		while(true){

			direction = (int)(Math.random() * 4);

			if(direction == 0 && playerPosition > this.gridSize && this.simulationWindow.getPanelByID(playerPosition - this.gridSize).getType() != 1){

				this.nextPanelID = playerPosition - this.gridSize;
				this.simulationWindow.getPanelByID(this.nextPanelID).setBackground(new Color(255,200,200));

				break;
			}

			if(direction == 1 && ((playerPosition + 1) % this.gridSize) != 0 && this.simulationWindow.getPanelByID(playerPosition + 1).getType() != 1){

				this.nextPanelID = playerPosition + 1;
				this.simulationWindow.getPanelByID(this.nextPanelID).setBackground(new Color(255,200,200));

				break;
			}

			if(direction == 2 && playerPosition < ((this.gridSize * this.gridSize) - this.gridSize) && this.simulationWindow.getPanelByID(playerPosition + this.gridSize).getType() != 1){

				this.nextPanelID = playerPosition + this.gridSize;
				this.simulationWindow.getPanelByID(this.nextPanelID).setBackground(new Color(255,200,200));

				break;
			}

			if(direction == 3 && (playerPosition % this.gridSize) != 0 && this.simulationWindow.getPanelByID(playerPosition - 1).getType() != 1){

				this.nextPanelID = playerPosition - 1;
				this.simulationWindow.getPanelByID(this.nextPanelID).setBackground(new Color(255,200,200));

				break;
			}
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

	public void setNextPanelID(int id){

		this.nextPanelID = id;
	}

	public int getNextPanelID(){

		return this.nextPanelID;
	}
}