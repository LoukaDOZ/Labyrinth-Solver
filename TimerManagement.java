import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**Cette classe gère les actions à réaliser après une pause*/
public class TimerManagement implements ActionListener{

	private String type;										//Type de l'action
	private Window window;										//Fenêtre correspondante
	private Timer timer;										//Timer concerné
	private MenuActionManagement menuActionManagement;			//Gérant des menus
	private ManualSimulation manualSimulation;					//Simulation manuelle
	private AutomaticSimulation automaticSimulation;			//Simulation automatique

	/**Constructeur pour la fermeture d'une fenêtre
	@param window Fenêtre concernée
	@param timer Chronomètre concerné*/
	public TimerManagement(Window window,Timer timer){

		this.window = window;
		this.type = "close window";
		this.timer = timer;

		this.timer.start();										//Démarage du chronomètre avant le lancement de l'action
	}

	/**Constructeur pour le fait que l'utilisateur reste appuyé sur la souris dans un menu de sélection
	@param menuActionManagement Gérant des menus
	@param timer Chronomètre concerné
	@param type Type d'action à réaliser*/
	public TimerManagement(MenuActionManagement menuActionManagement,Timer timer,String type){

		this.menuActionManagement = menuActionManagement;
		this.type = type;
		this.timer = timer;

		this.timer.start();										//Démarage du chronomètre avant le lancement de l'action
	}

	/**Constructeur pour le lancement d'une nouvelle simulation automatique ou lancement d'un nouveau tour
	@param automaticSimulation Gérant de la simulation automatique
	@param timer Chronomètre concerné
	@param type Type d'action à réaliser*/
	public TimerManagement(AutomaticSimulation automaticSimulation,Timer timer,String type){

		this.automaticSimulation = automaticSimulation;
		this.type = type;
		this.timer = timer;

		this.timer.start();										//Démarage du chronomètre avant le lancement de l'action
	}

	/**Constructeur pour le lancement d'une nouvelle simulation manuelle
	@param manualSimulation Gérant de la simulation manuelle
	@param timer Chronomètre concerné
	@param type Type d'action à réaliser*/
	public TimerManagement(ManualSimulation manualSimulation,Timer timer,String type){

		this.manualSimulation = manualSimulation;
		this.type = type;
		this.timer = timer;

		this.timer.start();										//Démarage du chronomètre avant le lancement de l'action
	}

	/**Lorsqu'un Timer à été complété, une action est réalisée*/
	public void actionPerformed(ActionEvent e){

		if(this.type.equals("close window")){					//Si le type est de fermer une fenêtre

			this.window.setVisible(false);						//La fenêtre est fermée
			this.timer.stop();									//Le timer est arrêté
		}

		if(this.type.equals("bigger grid")){					//Si le type est d'augmenter la taille de la fenêtre

			this.menuActionManagement.setGridSize(this.menuActionManagement.getGridSize() + 1); //Augmentation de la taille de 1
		}

		if(this.type.equals("smaller grid")){					//Si le type est de réduire la taille de la fenêtre

			this.menuActionManagement.setGridSize(this.menuActionManagement.getGridSize() - 1); //Réduction de la taille de 1
		}

		if(this.type.equals("next round")){						//Si le type est de fermer une fenêtre

			this.automaticSimulation.nextRound();				//Lancement d'un nouveau tour
			this.timer.stop();									//Le timer est arrêté
		}

		if(this.type.equals("start simulation A")){				//Si le type est de fermer une fenêtre

			this.automaticSimulation.startSimulation();			//Lancement de la simulation
			this.timer.stop();									//Le timer est arrêté
		}

		if(this.type.equals("start simulation M")){				//Si le type est de fermer une fenêtre

			this.manualSimulation.startSimulation();			//Lancement de la simulation
			this.timer.stop();									//Le timer est arrêté
		}
	}
}