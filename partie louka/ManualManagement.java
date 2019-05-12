import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Cette classe gère l'appuie de touche lors d'une simulation manuelle
public class ManualManagement implements KeyListener{

	private ManualSimulation simulation;						//Variable de la simulation manuelle

	//Constructeur
	public ManualManagement(ManualSimulation simulation) {

		super();

		this.simulation = simulation;							//Innitialistation de la simulation manuelle
	}

	//Touche pressée
	public void keyPressed(KeyEvent e) {

		this.simulation.move();									//Déplacement de la simulation quand une touche est pressée
	}

	//Touche lachée
	public void keyReleased(KeyEvent e) {}

	//Touche tapée
	public void keyTyped(KeyEvent e) {}
}