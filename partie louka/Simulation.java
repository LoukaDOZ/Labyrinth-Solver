import javax.swing.*;
import java.awt.*;

public class Simulation{

	private Window simulationWindow;
	private Panel gridPanel;

	public Simulation(){

		//get usable size of the screen
    	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int windowSizeX = (int)dimension.getWidth();
    	int windowSizeY = (int)dimension.getHeight();

		Window simulationWindow = new Window("Simulation",windowSizeX,windowSizeY,0,0,true);
		simulationWindow.setGridLayout(1,1);
		this.simulationWindow = simulationWindow;
	}
	
	public void startSimulation(int gridSize, int[] typeArray, boolean isRandom, boolean isManual){

		this.gridPanel = new Panel(this.simulationWindow);
		this.gridPanel.setCreatingGrid(this.simulationWindow,gridSize,false);

		for(int i = 0; i < gridSize; i++){

	      for(int j = 0; j < gridSize; j++){

	        this.simulationWindow.getPanelByID(j + (i * gridSize)).setType(typeArray[i + (j * gridSize)],gridSize);
	      }
	    }

		if(isManual == true){

			this.simulationWindow.add(this.gridPanel,BorderLayout.CENTER);
			this.simulationWindow.setVisible(true);
		}else{

			Panel choosenOptionsPanel = new Panel();
		}
	}
}