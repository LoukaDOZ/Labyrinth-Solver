import javax.swing.*;
import java.awt.*;

public class AutomaticSimulation{

	private Window simulationWindow;
	private Window optionsWindow;
	private Window finalWindow;

	private int[] typeArray;
	private int[] passedByArray;
	private String nextDirection;
	private int round;
	private int simulationNumber;
	private int gridSize;
	private int nextPanelID;
	private int exitID;
	private boolean isRandom;
	private int roundsSum;
	private int numberOfExitFound;

	public AutomaticSimulation(int gridSize, int[] typeArray, boolean isRandom){

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
	    }else{

	    	this.optionsWindow.add(this.optionsWindow.getNewJLabel("Determinist",1),BorderLayout.CENTER);
	    }

		this.optionsWindow.add(this.optionsWindow.getNewJLabel("Automatic",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel(this.simulationNumber+"/100",1),BorderLayout.CENTER);

	    this.finalWindow.add(this.finalWindow.getNewJLabel("Simulation ended",2),BorderLayout.NORTH);

	    this.newSimulation();

	    this.simulationWindow.setVisible(true);
		this.optionsWindow.setVisible(true);
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

		this.setNextDirection();
		this.move();
	}

	public void setNextDirection(){

		if(this.isRandom == true){

			this.getRandomDirection();
		}
	}

	public void move(){

		this.addPanelPassedBy(this.simulationWindow.getPanelByType(2).getID());
		this.simulationWindow.getPanelByType(2).setType(0,this.gridSize);
		this.simulationWindow.getPanelByID(this.nextPanelID).setType(2,this.gridSize);

		if(this.simulationWindow.getPanelByType(2).getID() == this.exitID || this.round == 1000){

			if(this.round == 1000){

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

		if(this.simulationNumber < 100){

			JLabel simulationNumberLabel = this.optionsWindow.getJLabelByText(this.simulationNumber+"/100");
			this.simulationNumber++;
			this.optionsWindow.getJLabelByText((this.simulationNumber - 1)+"/100").setText(this.simulationNumber+"/100");

			this.newSimulation();
			Pause pause = new Pause(this);

		}else{

			this.optionsWindow.setVisible(false);
			this.simulationWindow.setVisible(false);

			Panel finalInformationsPanel = new Panel();
			finalInformationsPanel.setLayout(new GridLayout(3,2));

			finalInformationsPanel.add(this.finalWindow.getNewJLabel("Exits found :",2),BorderLayout.CENTER);
			finalInformationsPanel.add(this.finalWindow.getNewJLabel(this.numberOfExitFound+"/100",2),BorderLayout.CENTER);
			finalInformationsPanel.add(this.finalWindow.getNewJLabel("Average of rounds to find the exit :",1),BorderLayout.CENTER);
			finalInformationsPanel.add(this.finalWindow.getNewJLabel(""+((int)(this.roundsSum / 100)),2),BorderLayout.CENTER);
			finalInformationsPanel.add(this.finalWindow.getNewJLabel("All paths taken :",2),BorderLayout.CENTER);

			this.finalWindow.getJLabelByText("Simulation ended").setBackground(new Color(180,180,180));
			this.finalWindow.getJLabelByText("Simulation ended").setForeground(new Color(0,0,0));
			this.finalWindow.getJLabelByText("Exits found :").setBackground(new Color(50,50,50));
		    this.finalWindow.getJLabelByText("Average of rounds to find the exit :").setBackground(new Color(50,50,50));
		    this.finalWindow.getJLabelByText("All paths taken :").setBackground(new Color(255,180,255));
		    this.finalWindow.getJLabelByText("All paths taken :").setForeground(new Color(0,0,0));

			Panel finalGrid = new Panel();
			finalGrid.setLayout(new GridLayout(this.gridSize,this.gridSize));

			Color color;
			int i;
			for(i = 0; i < this.gridSize * this.gridSize; i++){

			  	if((gridSize % 2) == 0){

			   		if(((i + (i / gridSize)) % 2) == 0){

			        	color = new Color(240,240,240);
			        }else{

			        	color = new Color(255,255,255);
			        }
			    }else{

			        if((i % 2) == 0){

			        	color = new Color(240,240,240);
			        }else{

			        	color = new Color(255,255,255);
			    	}
			    }

			   	Panel panel = new Panel(i,color,this.finalWindow.getHeight() / 4);
			   	finalGrid.add(panel,BorderLayout.CENTER);
			   	panel.setType(this.simulationWindow.getPanelByID(i).getType(),this.gridSize);
			   	this.finalWindow.updatePanelArray(panel);
			}

			for(i = 0; i < this.passedByArray.length; i++){

		        this.finalWindow.getPanelByID(this.passedByArray[i]).setBackground(new Color(255,180,255));
		    }

		    this.optionsWindow.getPanelByType(2).setBackground(new Color(0,0,255));
		    this.optionsWindow.getPanelByType(3).setBackground(new Color(0,150,0));

			finalInformationsPanel.add(finalGrid,BorderLayout.CENTER);

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

	public void addPanelPassedBy(int id){

		if(this.passedByArray == null){

	      this.passedByArray = new int[1];
	      this.passedByArray[this.passedByArray.length - 1] = id;

	      this.optionsWindow.getPanelByID(id).setBackground(new Color(255,180,255));
	    }else{

	    	boolean alreadyPassedBy = false;

	    	for(int i = 0; i < this.passedByArray.length; i++){

	        	if(this.passedByArray[i] == id){

	        		alreadyPassedBy = true;
	        	}
	    	}

	    	if(alreadyPassedBy == false){

	    		int[] newArray = new int[this.passedByArray.length + 1];

		    	for(int i = 0; i < this.passedByArray.length; i++){

		        	newArray[i] = this.passedByArray[i];
		    	}

		    	newArray[newArray.length - 1] = id;
		    	this.passedByArray = new int[newArray.length];
		    	this.passedByArray = newArray;

		    	this.optionsWindow.getPanelByID(id).setBackground(new Color(255,180,255));
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
}