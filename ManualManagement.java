import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**Cette classe gère l'appuie de touche lors d'une simulation manuelle*/
public class ManualManagement implements KeyListener{

	private ManualSimulation simulation;						//Variable de la simulation manuelle

	/**Constructeur
	@param gridSize Variable contenant la simulation manuelle*/
	public ManualManagement(ManualSimulation simulation) {

		super();

		this.simulation = simulation;							//Innitialistation de la simulation manuelle
	}

	/**Touche pressée
	Méthode utilisée pour lancer la méthode move() de la simulation manuelle quand l'utilisateur appuie sur une touche*/
	public void keyPressed(KeyEvent e) {

		this.simulation.move();									//Déplacement de la simulation quand une touche est pressée
	}

	/**Touche lachée (non utilisée)*/
	public void keyReleased(KeyEvent e) {}

	/**Touche tapée (non utilisée)*/
	public void keyTyped(KeyEvent e) {}
}