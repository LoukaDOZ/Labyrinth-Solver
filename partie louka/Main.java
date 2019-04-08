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
		welcomeWindow.setVisible(true);

		Window createWindow = new Window("Create a grid",windowSizeX / 3,windowSizeY / 2,windowSizeX / 3,windowSizeY / 4,true);
		createWindow.addNewMenuJLabel("Choose the size of the grid [15x15] :",true);
		createWindow.addNewMenuJLabel("Bigger",false);
		createWindow.addNewMenuJLabel("Smaller",false);
		createWindow.addNewMenuJLabel("Choose how to fill the grid :",true);
		createWindow.addNewMenuJLabel("Void",false);
		createWindow.addNewMenuJLabel("Random fill",false);
		createWindow.addNewMenuJLabel("Done",false);

		Window mapCreatingWindow = new Window("Create your map",(windowSizeX / 4) * 3,windowSizeY,0,0,false);
		mapCreatingWindow.addGrid();

		Window mapCreatingOptionsWindow = new Window("Create your map",windowSizeX / 4,windowSizeY,(windowSizeX / 4) * 3,0,false);
		mapCreatingOptionsWindow.addNewMenuJLabel("Change the map :",true);
		mapCreatingOptionsWindow.addNewMenuJLabel("Remove a wall",false);
		mapCreatingOptionsWindow.addNewMenuJLabel("Put a wall",false);
		mapCreatingOptionsWindow.addNewMenuJLabel("Put starting position",false);
		mapCreatingOptionsWindow.addNewMenuJLabel("Put the exit",false);
		mapCreatingOptionsWindow.addNewMenuJLabel("Done",false);

		Window mainWindow = new Window("Play",windowSizeX,windowSizeY,0,0,true);

		welcomeWindow.getMenuActionManagement().addWindows(welcomeWindow,createWindow,mapCreatingWindow,mapCreatingOptionsWindow,mainWindow);
		createWindow.getMenuActionManagement().addWindows(welcomeWindow,createWindow,mapCreatingWindow,mapCreatingOptionsWindow,mainWindow);
		mapCreatingOptionsWindow.getMenuActionManagement().addWindows(welcomeWindow,createWindow,mapCreatingWindow,mapCreatingOptionsWindow,mainWindow);
    }
}