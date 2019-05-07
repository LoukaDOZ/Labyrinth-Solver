import javax.swing.*;
import java.awt.*;

public class Algorithm{

	private ManualSimulation manualSimulation;
	private AutomaticSimulation automaticSimulation;

	private Window simulationWindow;

	private boolean isRandom;

	private int[] map;
	private int gridSize;
	private int nextPanelID;

	public Algorithm(boolean isRandom, ManualSimulation manualSimulation){

		this.isRandom = isRandom;
		this.manualSimulation = manualSimulation;
		this.simulationWindow = this.manualSimulation.getSimulationWindow();
		this.gridSize = this.manualSimulation.getGridSize();
		this.nextPanelID = this.manualSimulation.getNextPanelID();
		this.map = new int[this.gridSize * this.gridSize];
	}

	public Algorithm(boolean isRandom, AutomaticSimulation automaticSimulation){

		this.isRandom = isRandom;
		this.automaticSimulation = automaticSimulation;
		this.simulationWindow = this.automaticSimulation.getSimulationWindow();
		this.gridSize = this.automaticSimulation.getGridSize();
		this.nextPanelID = this.automaticSimulation.getNextPanelID();
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
			
				this.manualSimulation.setNextPanelID(playerPosition - this.gridSize);
				this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setBackground(new Color(255,180,200));

				return "North";
			}

			if(direction == 1 && ((playerPosition + 1) % this.gridSize) != 0 && this.simulationWindow.getPanelByID(playerPosition + 1).getType() != 1){

				this.manualSimulation.setNextPanelID(playerPosition + 1);
				this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setBackground(new Color(255,180,200));

				return "East";
			}

			if(direction == 2 && playerPosition < ((this.gridSize * this.gridSize) - this.gridSize) && this.simulationWindow.getPanelByID(playerPosition + this.gridSize).getType() != 1){
			
				this.manualSimulation.setNextPanelID(playerPosition + this.gridSize);
				this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setBackground(new Color(255,200,200));

				return "South";
			}

			if(direction == 3 && (playerPosition % this.gridSize) != 0 && this.simulationWindow.getPanelByID(playerPosition - 1).getType() != 1){
			
				this.manualSimulation.setNextPanelID(playerPosition - 1);
				this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setBackground(new Color(255,200,200));

				return "West";
			}
		}
	}

	public String getDeterministDirection(){

		int playerPosition = this.simulationWindow.getPanelByType(2).getID();
		String path = this.getTheLessExploredPath(playerPosition);

		return path;
	}

	public String getTheLessExploredPath(int playerPosition){

		this.lookAround(playerPosition);

		return "";
	}

	public void lookAround(int playerPosition){

	    if(playerPosition > this.gridSize){

	    	if(this.simulationWindow.getPanelByID(playerPosition - this.gridSize).getType() == 1){
			
				this.map[playerPosition - this.gridSize] = -2;
			}else{

				if(this.simulationWindow.getPanelByID(playerPosition - this.gridSize).getType() == 3){
			
					this.map[playerPosition - this.gridSize] = -1;
				}else{

					this.map[playerPosition - this.gridSize]++;
				}
			}
		}

		if(((playerPosition + 1) % this.gridSize) != 0){

			if(this.simulationWindow.getPanelByID(playerPosition + 1).getType() == 1){
			
				this.map[playerPosition + 1] = -2;
			}else{

				if(this.simulationWindow.getPanelByID(playerPosition + 1).getType() == 3){
			
					this.map[playerPosition + 1] = -1;
				}else{

					this.map[playerPosition + 1]++;
				}
			}
		}

		if(playerPosition < ((this.gridSize * this.gridSize) - this.gridSize)){
			
			if(this.simulationWindow.getPanelByID(playerPosition + this.gridSize).getType() == 1){
			
				this.map[playerPosition + this.gridSize] = -2;
			}else{

				if(this.simulationWindow.getPanelByID(playerPosition + this.gridSize).getType() == 3){
			
					this.map[playerPosition + this.gridSize] = -1;
				}else{

					this.map[playerPosition + this.gridSize]++;
				}
			}
		}

		if((playerPosition % this.gridSize) != 0){
			
			if(this.simulationWindow.getPanelByID(playerPosition - 1).getType() == 1){
			
				this.map[playerPosition - 1] = -2;
			}else{

				if(this.simulationWindow.getPanelByID(playerPosition - 1).getType() == 3){
			
					this.map[playerPosition - 1] = -1;
				}else{

					this.map[playerPosition - 1]++;
				}
			}
		}
  	}
}