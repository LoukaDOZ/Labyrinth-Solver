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

		Window algorithmWindow = new Window("Choose an algorithm",windowSizeX / 3,windowSizeY / 2,windowSizeX / 3,windowSizeY / 4,true);

		MenuActionManagement menuActionManagement = new MenuActionManagement(welcomeWindow,createWindow,mapCreatingWindow,algorithmWindow);
		GridActionManagement gridActionManagement = new GridActionManagement(mapCreatingWindow);

		welcomeWindow.setManagement(menuActionManagement);

		createWindow.setManagement(menuActionManagement);

		mapCreatingWindow.setManagement(menuActionManagement);
		mapCreatingWindow.setManagement(gridActionManagement);

		algorithmWindow.setManagement(menuActionManagement);

		welcomeWindow.setGridLayout(3,1);
		welcomeWindow.add(welcomeWindow.getNewJLabel("Welcome! Please, choose an option :",2),BorderLayout.CENTER);
		welcomeWindow.add(welcomeWindow.getNewJLabel("Open a grid","MenuActionManagement",2),BorderLayout.CENTER);
		welcomeWindow.add(welcomeWindow.getNewJLabel("Create a grid","MenuActionManagement",2),BorderLayout.CENTER);

		createWindow.setGridLayout(7,1);
		createWindow.add(createWindow.getNewJLabel("Choose the size of the grid [50x50] :",2),BorderLayout.CENTER);
		createWindow.add(createWindow.getNewJLabel("Bigger","MenuActionManagement",2),BorderLayout.CENTER);
		createWindow.add(createWindow.getNewJLabel("Smaller","MenuActionManagement",2),BorderLayout.CENTER);
		createWindow.add(createWindow.getNewJLabel("Choose how to fill the grid :",2),BorderLayout.CENTER);
		createWindow.add(createWindow.getNewJLabel("Void","MenuActionManagement",2),BorderLayout.CENTER);
		createWindow.add(createWindow.getNewJLabel("Random fill","MenuActionManagement",2),BorderLayout.CENTER);
		createWindow.add(createWindow.getNewJLabel("Done","MenuActionManagement",2),BorderLayout.CENTER);

		Panel panel = new Panel();
		panel.setLayout(new GridLayout(9,1));
		panel.add(mapCreatingWindow.getNewJLabel("  Change the map :  ",1));
		panel.add(mapCreatingWindow.getNewJLabel("Reset","MenuActionManagement",1));
		panel.add(mapCreatingWindow.getNewJLabel("Filled with walls","MenuActionManagement",1));
		panel.add(mapCreatingWindow.getNewJLabel("Rubber","MenuActionManagement",1));
		panel.add(mapCreatingWindow.getNewJLabel("Put a wall","MenuActionManagement",1));
		panel.add(mapCreatingWindow.getNewJLabel("Put the start","MenuActionManagement",1));
		panel.add(mapCreatingWindow.getNewJLabel("Put the exit","MenuActionManagement",1));
		panel.add(mapCreatingWindow.getNewJLabel("Save the map","MenuActionManagement",1));
		panel.add(mapCreatingWindow.getNewJLabel("Done","MenuActionManagement",1));
		mapCreatingWindow.add(panel,BorderLayout.WEST);

		algorithmWindow.setGridLayout(9,1);
		algorithmWindow.add(algorithmWindow.getNewJLabel("Choose a maximum of rounds :",2),BorderLayout.CENTER);
		algorithmWindow.add(algorithmWindow.getNewJTextArea("infinite"),BorderLayout.CENTER);
		algorithmWindow.add(algorithmWindow.getNewJLabel("Choose an algorithm :",2),BorderLayout.CENTER);
		algorithmWindow.add(algorithmWindow.getNewJLabel("Random","MenuActionManagement",2),BorderLayout.CENTER);
		algorithmWindow.add(algorithmWindow.getNewJLabel("Determinist","MenuActionManagement",2),BorderLayout.CENTER);
		algorithmWindow.add(algorithmWindow.getNewJLabel("Choose a mode :",2),BorderLayout.CENTER);
		algorithmWindow.add(algorithmWindow.getNewJLabel("Manual","MenuActionManagement",2),BorderLayout.CENTER);
		algorithmWindow.add(algorithmWindow.getNewJLabel("Automatic","MenuActionManagement",2),BorderLayout.CENTER);
		algorithmWindow.add(algorithmWindow.getNewJLabel("Done","MenuActionManagement",2),BorderLayout.CENTER);

		welcomeWindow.setVisible(true);
	}
}