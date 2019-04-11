import javax.swing.*;
import java.awt.*;

public class StartSimulation{
	
	public StartSimulation(int gridSize, int[] typeArray, boolean isRandom, boolean isManual){

		//get usable size of the screen
    	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int windowSizeX = (int)dimension.getWidth();
    	int windowSizeY = (int)dimension.getHeight();

		Window simulationWindow = new Window("Simalution",windowSizeX,windowSizeY,0,0,true);

		if(isManual == true){


		}else{


		}
	}
}