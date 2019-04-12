import javax.swing.*;
import java.awt.*;

public class StartSimulation{

	private Window simulationWindow;
	private Panel gridPanel;
	
	public StartSimulation(int gridSize, int[] typeArray, boolean isRandom, boolean isManual){

		//get usable size of the screen
    	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int windowSizeX = (int)dimension.getWidth();
    	int windowSizeY = (int)dimension.getHeight();

		Window simulationWindow = new Window("Simalution",windowSizeX,windowSizeY,0,0,true);
		simulationWindow.setGridLayout(1,1);

		this.gridPanel = new Panel();
		gridPanel.setCreatingGrid(simulationWindow,gridSize,false);
		simulationWindow.add(this.gridPanel,BorderLayout.CENTER);

		for(int i = 0; i < gridSize; i++){

	      for(int j = 0; j < gridSize; j++){

	        simulationWindow.getPanelByID(j + (i * gridSize)).setType(typeArray[i + (j * gridSize)]);
	      }
	    }

		this.simulationWindow = simulationWindow;
		this.simulationWindow.setVisible(true);

		if(isManual == true){

			simulationWindow.add(gridPanel,BorderLayout.CENTER);
		}else{

			Panel choosenOptionsPanel = new Panel();
		}
	}
}