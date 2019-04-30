import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManualManagement implements KeyListener{

	private Simulation simulation;

	public ManualManagement(Simulation simulation) {

		super();

		this.simulation = simulation;
	}

	//key pressed
	public void keyPressed(KeyEvent e) {

		this.simulation.move();
	}

	//key release
	public void keyReleased(KeyEvent e) {}

	//key just typed
	public void keyTyped(KeyEvent e) {}
}