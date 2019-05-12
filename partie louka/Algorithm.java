import javax.swing.*;
import java.awt.*;

public class Algorithm{

	private ManualSimulation manualSimulation;
	private AutomaticSimulation automaticSimulation;

	private Window simulationWindow;

	private boolean isRandom;
	private boolean isManual;
	private boolean[] knownPaths;

	private int[] unknownPathsID;
	private int gridSize;
	private int nextPanelID;
	private int playerPosition;
	private int northCase;
	private int eastCase;
	private int southCase;
	private int westCase;

	private String direction;

	public Algorithm(boolean isRandom,ManualSimulation manualSimulation,Window simulationWindow,int gridSize){

		this.isRandom = isRandom;
		this.isManual = true;
		this.manualSimulation = manualSimulation;
		this.simulationWindow = simulationWindow;
		this.gridSize = gridSize;
		this.knownPaths = new boolean[this.gridSize * this.gridSize];
	}

	public Algorithm(boolean isRandom,AutomaticSimulation automaticSimulation,Window simulationWindow,int gridSize){

		this.isRandom = isRandom;
		this.isManual = false;
		this.automaticSimulation = automaticSimulation;
		this.simulationWindow = simulationWindow;
		this.gridSize = gridSize;
		this.knownPaths = new boolean[this.gridSize * this.gridSize];
	}

	public String getDirection(){

		this.playerPosition = this.simulationWindow.getPanelByType(2).getID();

		if(this.isRandom == true){

			this.direction = this.getRandomDirection();
	
		}else{

			this.getDeterministDirection();
		}

		if(this.direction.equals("North")){

			if(this.isManual == true){

				this.manualSimulation.setNextPanelID(this.playerPosition - this.gridSize);
				this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setIsNextDirection(true);
			}else{

				this.automaticSimulation.setNextPanelID(this.playerPosition - this.gridSize);
			}
		}

		if(this.direction.equals("East")){

			if(this.isManual == true){

				this.manualSimulation.setNextPanelID(this.playerPosition + 1);
				this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setIsNextDirection(true);
			}else{

				this.automaticSimulation.setNextPanelID(this.playerPosition + 1);
			}
		}

		if(this.direction.equals("South")){

			if(this.isManual == true){

				this.manualSimulation.setNextPanelID(this.playerPosition + this.gridSize);
				this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setIsNextDirection(true);
			}else{

				this.automaticSimulation.setNextPanelID(this.playerPosition + this.gridSize);
			}
		}

		if(this.direction.equals("West")){

			if(this.isManual == true){

				this.manualSimulation.setNextPanelID(this.playerPosition - 1);
				this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setIsNextDirection(true);
			}else{

				this.automaticSimulation.setNextPanelID(this.playerPosition - 1);
			}
		}

		return this.direction;
	}

	public String getRandomDirection(){

		int direction;

		while(true){

			direction = (int)(Math.random() * 4);

			if(direction == 0 && this.playerPosition > this.gridSize){

				return "North";
			}

			if(direction == 1 && ((this.playerPosition + 1) % this.gridSize) != 0){

				return "East";
			}

			if(direction == 2 && this.playerPosition < ((this.gridSize * this.gridSize) - this.gridSize)){

				return "South";
			}

			if(direction == 3 && (this.playerPosition % this.gridSize) != 0){

				return "West";
			}
		}
	}

	public void getDeterministDirection(){

		this.direction = "Undefined";

		if(this.knownPaths[this.playerPosition] == false){

			this.lookAround();
		}

		if(this.direction.equals("Undefined") == true){

			int lastIdNoted = this.getLastIDNoted();

			if(lastIdNoted == this.playerPosition){

				lastIdNoted = this.getLastIDNoted();
			}

			if(lastIdNoted > 0){

				this.getDirectionFromID(lastIdNoted);
			}
		}
	}

	public void lookAround(){

		this.direction = "Undefined";

		this.knownPaths[this.playerPosition] = true;

		if(this.playerPosition >= this.gridSize){

			this.northCase = this.playerPosition - this.gridSize;

			if(this.simulationWindow.getPanelByID(this.northCase).getType() == 3){
			
				this.direction = "North";
			}

			if(this.knownPaths[this.northCase] == false){

				this.newPath(this.northCase);
			}
		}

		if(((this.playerPosition + 1) % this.gridSize) != 0){

			this.eastCase = this.playerPosition + 1;

			if(this.simulationWindow.getPanelByID(this.eastCase).getType() == 3){
			
				this.direction = "East";
			}

			if(this.knownPaths[this.eastCase] == false){

				this.newPath(this.eastCase);
			}
		}

		if(this.playerPosition < ((this.gridSize * this.gridSize) - this.gridSize)){

			this.southCase = this.playerPosition + this.gridSize;

			if(this.simulationWindow.getPanelByID(this.southCase).getType() == 3){
			
				this.direction = "South";
			}

			if(this.knownPaths[this.southCase] == false){

				this.newPath(this.southCase);
			}
		}

		if((this.playerPosition % this.gridSize) != 0){

			this.westCase = this.playerPosition - 1;

			if(this.simulationWindow.getPanelByID(this.westCase).getType() == 3){
			
				this.direction = "West";
			}

			if(this.knownPaths[this.westCase] == false){

				this.newPath(this.westCase);
			}
		}
  	}

	public void setThisCaseAsWall(int id){

		this.knownPaths[id] = true;
	}

	public int getLastIDNoted(){

		int id = -1;

		if(this.unknownPathsID != null){

			try{

				id = this.unknownPathsID[this.unknownPathsID.length - 1];
				this.removePath();
			}catch(ArrayIndexOutOfBoundsException ex){

		    	if(this.isManual == true){

		    		this.manualSimulation.thereIsNoExit();
		    	}else{

		    		this.automaticSimulation.thereIsNoExit();
		    	}
		    }catch(NegativeArraySizeException ex){

		    	if(this.isManual == true){

		    		this.manualSimulation.thereIsNoExit();
		    	}else{

		    		this.automaticSimulation.thereIsNoExit();
		    	}
		    }
		}

		return id;
	}

	public void newPath(int nextPath){

	    if(this.unknownPathsID == null){

	     	this.unknownPathsID = new int[2];
	      	this.unknownPathsID[0] = this.playerPosition;
	      	this.unknownPathsID[1] = nextPath;
	    }else{

	      	int[] newArray = new int[this.unknownPathsID.length + 2];

	      	for(int i = 0; i < this.unknownPathsID.length; i++){

	        	newArray[i] = this.unknownPathsID[i];
	      	}

	      	newArray[newArray.length - 2] = this.playerPosition;
	     	newArray[newArray.length - 1] = nextPath;
	      	this.unknownPathsID = new int[newArray.length];
	      	this.unknownPathsID = newArray;
	    }
  	}

  	public void removePath(){

	    int[] newArray = new int[this.unknownPathsID.length - 1];

	    for(int i = 0; i < newArray.length; i++){

	        newArray[i] = this.unknownPathsID[i];
	    }

	    this.unknownPathsID = new int[newArray.length - 1];
	    this.unknownPathsID = newArray;
	}

	public void getDirectionFromID(int id){

		this.direction = "Undefined";

		if(this.playerPosition >= this.gridSize){

			this.northCase = this.playerPosition - this.gridSize;

			if(this.simulationWindow.getPanelByID(this.northCase).getID() == id){
			
				this.direction = "North";
			}
		}

		if(((this.playerPosition + 1) % this.gridSize) != 0){

			this.eastCase = this.playerPosition + 1;

			if(this.simulationWindow.getPanelByID(this.eastCase).getID() == id){
			
				this.direction = "East";
			}
		}

		if(this.playerPosition < ((this.gridSize * this.gridSize) - this.gridSize)){

			this.southCase = this.playerPosition + this.gridSize;

			if(this.simulationWindow.getPanelByID(this.southCase).getID() == id){
			
				this.direction = "South";
			}
		}

		if((this.playerPosition % this.gridSize) != 0){

			this.westCase = this.playerPosition - 1;

			if(this.simulationWindow.getPanelByID(this.westCase).getID() == id){
			
				this.direction = "West";
			}
		}
	}
}