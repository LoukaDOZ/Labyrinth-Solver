import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

    	//get usable size of the screen
    	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int windowSizeX = (int)dimension.getWidth();
    	int windowSizeY = (int)dimension.getHeight();

    	Window welcomeWindow = new Window("Welcome",windowSizeX / 3,windowSizeY / 2,windowSizeX / 3,windowSizeY / 4,true);
		welcomeWindow.addNewMenuJLabel("Welcome! Please, choose an option :",true);
		welcomeWindow.addNewMenuJLabel("Open a grid",false);
		welcomeWindow.addNewMenuJLabel("Create a grid",false);

		Window createWindow = new Window("Create a grid",windowSizeX / 3,windowSizeY / 2,windowSizeX / 3,windowSizeY / 4,true);
		createWindow.addNewMenuJLabel("Choose the size of the grid [20x20] :",true);
		createWindow.addNewMenuJLabel("Bigger",false);
		createWindow.addNewMenuJLabel("Smaller",false);
		createWindow.addNewMenuJLabel("Choose how to fill the grid :",true);
		createWindow.addNewMenuJLabel("Void",false);
		createWindow.addNewMenuJLabel("Random fill",false);
		createWindow.addNewMenuJLabel("Done",false);

		Window mapCreatingWindow = new Window("Create your map",windowSizeX,windowSizeY,0,0,true);

		Window mainWindow = new Window("Play",windowSizeX,windowSizeY,0,0,true);

		welcomeWindow.getMenuActionManagement().addWindows(welcomeWindow,createWindow,mapCreatingWindow,mainWindow);
		createWindow.getMenuActionManagement().addWindows(welcomeWindow,createWindow,mapCreatingWindow,mainWindow);
		mapCreatingWindow.getMenuActionManagement().addWindows(welcomeWindow,createWindow,mapCreatingWindow,mainWindow);
		mapCreatingWindow.getGridActionManagement().addWindows(mapCreatingWindow);

		welcomeWindow.setVisible(true);
	}
}