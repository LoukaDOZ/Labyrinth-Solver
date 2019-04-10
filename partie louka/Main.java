import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

    	//get usable size of the screen
    	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int windowSizeX = (int)dimension.getWidth();
    	int windowSizeY = (int)dimension.getHeight();

    	Window welcomeWindow = new Window("Welcome",windowSizeX / 3,windowSizeY / 2,windowSizeX / 3,windowSizeY / 4,true);

		Window createWindow = new Window("Create a grid",windowSizeX / 3,windowSizeY / 2,windowSizeX / 3,windowSizeY / 4,true);

		Window mapCreatingWindow = new Window("Create your map",windowSizeX,windowSizeY,0,0,true);

		Window mainWindow = new Window("Play",windowSizeX,windowSizeY,0,0,true);

		MenuActionManagement menuActionManagement = new MenuActionManagement(welcomeWindow,createWindow,mapCreatingWindow,mainWindow);
		GridActionManagement gridActionManagement = new GridActionManagement(mapCreatingWindow);

		welcomeWindow.setManagement(menuActionManagement);

		createWindow.setManagement(menuActionManagement);

		mapCreatingWindow.setManagement(menuActionManagement);
		mapCreatingWindow.setManagement(gridActionManagement);

		mainWindow.setManagement(menuActionManagement);

		welcomeWindow.setGridLayoutBigger(3,1);
		welcomeWindow.add(welcomeWindow.getNewJLabel("Welcome! Please, choose an option :",2),BorderLayout.CENTER);
		welcomeWindow.add(welcomeWindow.getNewJLabel("Open a grid","MenuActionManagement",2),BorderLayout.CENTER);
		welcomeWindow.add(welcomeWindow.getNewJLabel("Create a grid","MenuActionManagement",2),BorderLayout.CENTER);

		createWindow.setGridLayoutBigger(7,1);
		createWindow.add(createWindow.getNewJLabel("Choose the size of the grid [20x20] :",2),BorderLayout.CENTER);
		createWindow.add(createWindow.getNewJLabel("Bigger","MenuActionManagement",2),BorderLayout.CENTER);
		createWindow.add(createWindow.getNewJLabel("Smaller","MenuActionManagement",2),BorderLayout.CENTER);
		createWindow.add(createWindow.getNewJLabel("Choose how to fill the grid :",2),BorderLayout.CENTER);
		createWindow.add(createWindow.getNewJLabel("Void","MenuActionManagement",2),BorderLayout.CENTER);
		createWindow.add(createWindow.getNewJLabel("Random fill","MenuActionManagement",2),BorderLayout.CENTER);
		createWindow.add(createWindow.getNewJLabel("Done","MenuActionManagement",2),BorderLayout.CENTER);

		welcomeWindow.setVisible(true);
	}
}