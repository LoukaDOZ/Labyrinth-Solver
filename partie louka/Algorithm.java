import javax.swing.*;
import java.awt.*;

public class Algorithm{

	private ManualSimulation manualSimulation;
	private AutomaticSimulation automaticSimulation;

	private Window simulationWindow;

	private boolean isRandom;
	private boolean isManual;

	private int[] map;
	private int gridSize;
	private int nextPanelID;

	public Algorithm(boolean isRandom,ManualSimulation manualSimulation,Window simulationWindow,int gridSize){

		this.isRandom = isRandom;
		this.isManual = true;
		this.manualSimulation = manualSimulation;
		this.simulationWindow = simulationWindow;
		this.gridSize = gridSize;
		this.map = new int[this.gridSize * this.gridSize];
	}

	public Algorithm(boolean isRandom,AutomaticSimulation automaticSimulation,Window simulationWindow,int gridSize){

		this.isRandom = isRandom;
		this.isManual = false;
		this.automaticSimulation = automaticSimulation;
		this.simulationWindow = simulationWindow;
		this.gridSize = gridSize;
		this.map = new int[this.gridSize * this.gridSize];
	}

	public String getDirection(){

		if(this.isRandom == true){

			return this.getRandomDirection();
		}else{

			return this.getDeterministDirection();
		}
	}

	public String getRandomDirection(){

		int direction;
		int playerPosition = this.simulationWindow.getPanelByType(2).getID();

		while(true){

			direction = (int)(Math.random() * 4);

			if(direction == 0 && playerPosition > this.gridSize && this.simulationWindow.getPanelByID(playerPosition - this.gridSize).getType() != 1){
			
				if(this.isManual == true){

					this.manualSimulation.setNextPanelID(playerPosition - this.gridSize);
					this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setColor(new Color(255,180,200));
				}else{

					this.automaticSimulation.setNextPanelID(playerPosition - this.gridSize);
					this.simulationWindow.getPanelByID(this.automaticSimulation.getNextPanelID()).setColor(new Color(255,180,200));
				}

				return "North";
			}

			if(direction == 1 && ((playerPosition + 1) % this.gridSize) != 0 && this.simulationWindow.getPanelByID(playerPosition + 1).getType() != 1){

				if(this.isManual == true){

					this.manualSimulation.setNextPanelID(playerPosition + 1);
					this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setColor(new Color(255,180,200));
				}else{

					this.automaticSimulation.setNextPanelID(playerPosition + 1);
					this.simulationWindow.getPanelByID(this.automaticSimulation.getNextPanelID()).setColor(new Color(255,180,200));
				}

				return "East";
			}

			if(direction == 2 && playerPosition < ((this.gridSize * this.gridSize) - this.gridSize) && this.simulationWindow.getPanelByID(playerPosition + this.gridSize).getType() != 1){
			
				if(this.isManual == true){

					this.manualSimulation.setNextPanelID(playerPosition + this.gridSize);
					this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setColor(new Color(255,180,200));
				}else{

					this.automaticSimulation.setNextPanelID(playerPosition + this.gridSize);
					this.simulationWindow.getPanelByID(this.automaticSimulation.getNextPanelID()).setColor(new Color(255,180,200));
				}

				return "South";
			}

			if(direction == 3 && (playerPosition % this.gridSize) != 0 && this.simulationWindow.getPanelByID(playerPosition - 1).getType() != 1){
			
				if(this.isManual == true){

					this.manualSimulation.setNextPanelID(playerPosition - 1);
					this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setColor(new Color(255,180,200));
				}else{

					this.automaticSimulation.setNextPanelID(playerPosition - 1);
					this.simulationWindow.getPanelByID(this.automaticSimulation.getNextPanelID()).setColor(new Color(255,180,200));
				}

				return "West";
			}
		}
	}

	public String getDeterministDirection(){

		int playerPosition = this.simulationWindow.getPanelByType(2).getID();

		int northCase = -2;
		int southCase = -2;
		int eastCase = -2;
		int westCase = -2;
		String direction = "Undefined";

		this.lookAround(playerPosition);

		if(playerPosition > this.gridSize){

			northCase = this.map[playerPosition - this.gridSize];
		}

		if(((playerPosition + 1) % this.gridSize) != 0){

			eastCase = this.map[playerPosition + 1];
		}

		if(playerPosition < ((this.gridSize * this.gridSize) - this.gridSize)){

			southCase = this.map[playerPosition + this.gridSize];
		}

		if((playerPosition % this.gridSize) != 0){

			westCase = this.map[playerPosition - 1];
		}

		for(int i = 0; i < 1; i++){

			if(northCase >= -1 && (eastCase == -2 || northCase <= eastCase) && (southCase == -2 || northCase <= southCase) && (westCase == -2 || northCase <= westCase)){

				if(this.isManual == true){

					this.manualSimulation.setNextPanelID(playerPosition - this.gridSize);
					this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setColor(new Color(255,180,200));
				}else{

					this.automaticSimulation.setNextPanelID(playerPosition - this.gridSize);
				}

				direction = "North";
				break;
			}

			if(eastCase >= -1 && (northCase == -2 || eastCase <= northCase) && (southCase == -2 || eastCase <= southCase) && (westCase == -2 || eastCase <= westCase)){

				if(this.isManual == true){

					this.manualSimulation.setNextPanelID(playerPosition + 1);
					this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setColor(new Color(255,180,200));
				}else{

					this.automaticSimulation.setNextPanelID(playerPosition + 1);
				}

				direction = "East";
				break;
			}

			if(southCase >= -1 && (northCase == -2 || southCase <= northCase) && (eastCase == -2 || southCase <= eastCase) && (westCase == -2 || southCase <= westCase)){

				if(this.isManual == true){

					this.manualSimulation.setNextPanelID(playerPosition + this.gridSize);
					this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setColor(new Color(255,180,200));
				}else{

					this.automaticSimulation.setNextPanelID(playerPosition + this.gridSize);
				}

				direction = "South";
				break;
			}

			if(westCase >= -1 && (northCase == -2 || westCase <= northCase) && (eastCase == -2 || westCase <= eastCase) && (southCase == -2 || westCase <= southCase)){

				if(this.isManual == true){

					this.manualSimulation.setNextPanelID(playerPosition - 1);
					this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setColor(new Color(255,180,200));
				}else{

					this.automaticSimulation.setNextPanelID(playerPosition - 1);
				}

				direction = "West";
				break;
			}
		}

		return direction;
	}

	public void lookAround(int playerPosition){

		this.map[playerPosition]++;

	    if(playerPosition >= this.gridSize){

	    	if(this.simulationWindow.getPanelByID(playerPosition - this.gridSize).getType() == 1){
			
				this.map[playerPosition - this.gridSize] = -2;
			}else{

				if(this.simulationWindow.getPanelByID(playerPosition - this.gridSize).getType() == 3){
			
					this.map[playerPosition - this.gridSize] = -1;
				}
			}
		}

		if(((playerPosition + 1) % this.gridSize) != 0){

			if(this.simulationWindow.getPanelByID(playerPosition + 1).getType() == 1){
			
				this.map[playerPosition + 1] = -2;
			}else{

				if(this.simulationWindow.getPanelByID(playerPosition + 1).getType() == 3){
			
					this.map[playerPosition + 1] = -1;
				}
			}
		}

		if(playerPosition < ((this.gridSize * this.gridSize) - this.gridSize)){
			
			if(this.simulationWindow.getPanelByID(playerPosition + this.gridSize).getType() == 1){
			
				this.map[playerPosition + this.gridSize] = -2;
			}else{

				if(this.simulationWindow.getPanelByID(playerPosition + this.gridSize).getType() == 3){
			
					this.map[playerPosition + this.gridSize] = -1;
				}
			}
		}

		if((playerPosition % this.gridSize) != 0){
			
			if(this.simulationWindow.getPanelByID(playerPosition - 1).getType() == 1){
			
				this.map[playerPosition - 1] = -2;
			}else{

				if(this.simulationWindow.getPanelByID(playerPosition - 1).getType() == 3){
			
					this.map[playerPosition - 1] = -1;
				}
			}
		}
  	}
}