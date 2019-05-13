import javax.swing.*;
import java.awt.*;

/**Cette classe à pour but de gérer la simulation automatique*/
public class AutomaticSimulation{

	private Window simulationWindow;							//Fenêtre de simulation
	private Window optionsWindow;								//Fenêtre des options
	private Window finalWindow;									//Fenêtre de fin

	private Algorithm algorithm;								//Gérant de l'algorthme

	private int[] typeArray;									//Tableau contenant tout les type correspondants aux Panels de la grille
	private int maxRound;										//Nombre de tours maximal
	private int round;											//Nombre de tours actuel
	private int gridSize;										//Variable indiquant la taille de la grille. Nombre total de cases : gridSize * gridSize
	private int maxSimulationNumber;							//Nombre de simulation maximal
	private int simulationNumber;								//Nombre de simulations actuel
	private int nextPanelID;									//Variable indiquant l'ID du prochain Panel vers lequel la simulation va se déplacer
	private int exitID;											//Variable indiquant l'ID de la sortie
	private int roundsSum;										//Somme du nombre de tours pour trouver la sortie. Elle ne prend en compte que les simulations qui ont trouvé la sortie
	private int numberOfExitFound;								//Nombre de simulations qui ont trouvées la sortie

	private String nextDirection;								//Variable contenant la prochaine direction en mot(North,East,South,West)

	private boolean isRandom;									//Variable indiquant si la simulation est aléatoire(true) ou non(false)

	/**Constructeur
	@param gridSize Variable indiquant la taille de la grille. Nombre total de cases : gridSize * gridSize
	@param typeArray Tableau contenant tous les types de la grille rangés en colonne
	@param isRandom Variable indiquant si la simulation est aléatoire
	@param maxRound Nombre de tours maximal*/
	public AutomaticSimulation(int gridSize, int[] typeArray, boolean isRandom, String maxRound){

		//Innitialisation des valeurs
		this.maxRound = Integer.parseInt(maxRound);
		this.round = 0;
		this.typeArray = typeArray;
		this.gridSize = gridSize;
		this.isRandom = isRandom;
		this.simulationNumber = 1;
		this.roundsSum = 0;
		this.numberOfExitFound = 0;

		//Récupération de la taille utilisable de l'écran
    	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    	int windowSizeX = (int)dimension.getWidth();			//Taille horizontale
    	int windowSizeY = (int)dimension.getHeight();			//Taille verticale

    	//Innitialisation des fenêtres
		this.simulationWindow = new Window("Simulation",windowSizeX,(windowSizeY / 4) * 3,0,windowSizeY / 4,true);
		this.optionsWindow = new Window("Your options",windowSizeX,windowSizeY / 4,0,0,true);
		this.finalWindow = new Window("Simulation ended",windowSizeX,windowSizeY,0,0,true);

		//Innitialisation du contenu de la fenêtre de simulation
		this.simulationWindow.add(this.optionsWindow.getNewJLabel("Processing simulation...",2),BorderLayout.CENTER);
		//Fin de l'innitialisation du contenu de la fenêtre de simulation

		//Innitialisation du contenu de la fenêtre des options
		this.optionsWindow.setGridLayout(2,5);

		this.optionsWindow.add(this.optionsWindow.getNewJLabel("Starting grid :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Algorithm :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Mode :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Round max :",1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel("Simulation :",1),BorderLayout.CENTER);

	    this.optionsWindow.getJLabelByText("Starting grid :").setBackground(new Color(50,50,50));
	    this.optionsWindow.getJLabelByText("Algorithm :").setBackground(new Color(50,50,50));
	    this.optionsWindow.getJLabelByText("Mode :").setBackground(new Color(50,50,50));
	    this.optionsWindow.getJLabelByText("Round max :").setBackground(new Color(50,50,50));
	    this.optionsWindow.getJLabelByText("Simulation :").setBackground(new Color(50,50,50));

		this.optionsWindow.add(this.getStartingGridPanel(this.optionsWindow,this.gridSize),BorderLayout.CENTER);

	    if(isRandom == true){

	    	this.optionsWindow.add(this.optionsWindow.getNewJLabel("Random",1),BorderLayout.CENTER);
	    	this.maxSimulationNumber = 100;
	    }else{

	    	this.optionsWindow.add(this.optionsWindow.getNewJLabel("Determinist",1),BorderLayout.CENTER);
	    	this.maxSimulationNumber = 1;
	    }

		this.optionsWindow.add(this.optionsWindow.getNewJLabel("Automatic",1),BorderLayout.CENTER);
		this.optionsWindow.add(this.optionsWindow.getNewJLabel(""+this.maxRound,1),BorderLayout.CENTER);
	    this.optionsWindow.add(this.optionsWindow.getNewJLabel(this.simulationNumber+"/"+this.maxSimulationNumber,1),BorderLayout.CENTER);
	    //Fin innitialisation du contenu de la fenêtre de simulation

	    //Innitialisation du contenu de la fenêtre de fin
	    this.finalWindow.add(this.finalWindow.getNewJLabel("Simulation ended",2),BorderLayout.NORTH);
	    this.finalWindow.getJLabelByText("Simulation ended").setBackground(new Color(180,180,180));
		this.finalWindow.getJLabelByText("Simulation ended").setForeground(new Color(0,0,0));
		//Fin innitialisation du contenu de la fenêtre de fin

	    this.newSimulation();									//Nouvelle simulation

	    //Innitialisation de l'algorithme
	    this.algorithm = new Algorithm(isRandom,this,simulationWindow,this.gridSize);

	    //Affichage des fenêtres de simulation
	    this.simulationWindow.setVisible(true);
		this.optionsWindow.setVisible(true);

		this.pause("start simulation A");						//Démarage de la simulation après une pause
	}

	/**Méthode qui met une pose de 0,10 secondes avant de démarer une nouvelle simulation.
	Elle à surtout pour but d'empêcher l'affichage de message d'erreur lorsque l'enchaînement des méthodes créera une boucle
	@param pauseType Chaîne caractères indiquant l'action à réaliser après la pause*/
	public void pause(String pauseType){

		Timer timer = new Timer(10,null);						//Création d'un nouveau Timer
    	timer.addActionListener(new TimerManagement(this,timer,pauseType)); //Ajout d'un TimerManagement
	}

	/**Méthode qui prépare le programme à une nouvelle simulation*/
	public void newSimulation(){

		this.round = 0;											//Innitialisation des tours à 0
		this.nextDirection = "Undefined";						//Innitialisation de la prochaine direction sur "indéfinie"
		this.nextPanelID = -1;									//Innitialisation de l'ID de la prochaine case sur une id absurde

		this.simulationWindow.removePanelArray();				//Supression du labyrinthe de simulation
		Panel trash = this.getStartingGridPanel(this.simulationWindow,this.gridSize); //Ajout du labyrinthe de départ à la simulation. La valeur de retour n'est pas utilisée
	}
	
	/**Méthode qui démare la simulation*/
	public void startSimulation(){

		this.exitID = this.simulationWindow.getPanelByType(3).getID(); //this.exitID vaut l'ID de la sortie
		this.nextRound();										//Lancêment du prochain tour
	}

	/**Méthode qui exécute un tour*/
	public void nextRound(){

		this.round++;											//Augmentation du nombre de tours

		String trash = this.algorithm.getDirection();			//Récupération d'une nouvelle direction. La valeur de retour n'est pas utilisée

		this.move();											//Déplacement vers la prochaine case
	}

	/**Méthode qui déplace la simulation vers la prochaine case*/
	public void move(){

		if(this.simulationWindow.getPanelByID(this.nextPanelID).getType() != 1){ //Si la prochaine case n'est pas un mur

			this.simulationWindow.getPanelByType(2).setType(0); //La position actuelle est mise au type "case vide" (car la simulation ne peut se déplacer que sur des cases vides)
			this.simulationWindow.getPanelByID(this.nextPanelID).setType(2); //La prochaine direction devient la position actuelle, la simulation s'est alors déplacée
		}else{													//Si c'est un mur

			this.algorithm.setThisCaseAsWall(this.nextPanelID);	//La prochaine direction est notée comme un mur. La simulation ne s'y déplace pas
		}

		if(this.simulationWindow.getPanelByType(2).getID() == this.exitID || this.round == this.maxRound){ //Si la sortie est trouvée ou si le nombre maximal de tours est atteint

			if(this.round == this.maxRound){					//Si le nombre maximum de tours est atteint

				this.endSimulation(false);						//Fin de la simulation sans avoir trouvé la sortie
			}else{												//Si la sortie est trouvée

				this.endSimulation(true);						//Fin de la sortie en ayant trouvé la sortie
			}
		}else{													//Si la sortie n'est pas trouvée et que des tours peuvent encore êtres fait

			this.pause("next round");							//On passe au tour suivant
		}
	}

	/**Méthode qui à pour but de terminer la simulation
	@param exitIsFound Variable indiquant si la sortie est trouvée(true) ou non(false)*/
	public void endSimulation(boolean exitIsFound){

		if(exitIsFound == true){								//Si la sortie est trouvée

			this.numberOfExitFound++;							//Le nombre de sorties trouvées est incrémenté de 1
			this.roundsSum += this.round;						//On ajoute à la somme des tours, le nombre de tours qu'il a fallut pour atteindre la sortie
		}

		if(this.simulationNumber < this.maxSimulationNumber){	//Si le nombre de simulation est inférieur au nombre total de simulations

			this.simulationNumber++;							//On augmente le nombre de simulation
			this.optionsWindow.getJLabelByText((this.simulationNumber - 1)+"/"+this.maxSimulationNumber).setText(this.simulationNumber+"/"+this.maxSimulationNumber);//On récupère le JLabel qui affiche le nombre de simulations pour le mettre à jour

			this.newSimulation();								//Innitialisation d'un nouvelle simulation
			this.pause("start simulation A");					//Démarage de la simulation après une pause

		}else{													//Si le nombre total de simulations est atteint

			//Fermeture des fenêtres de simulation
			this.optionsWindow.setVisible(false);
			this.simulationWindow.setVisible(false);

			//Innitialisation d'un Panel contenant les informations finales
			Panel finalInformationsPanel = new Panel();
			finalInformationsPanel.setLayout(new GridLayout(2,2));

			//Innitialisation du contenu de finalInformationsPanel
			if(this.isRandom == true){							//Si la simulation est aléatoire
				
				finalInformationsPanel.add(this.finalWindow.getNewJLabel("Exits found :",2),BorderLayout.CENTER);
				finalInformationsPanel.add(this.finalWindow.getNewJLabel(this.numberOfExitFound+"/"+this.maxSimulationNumber,2),BorderLayout.CENTER);
				finalInformationsPanel.add(this.finalWindow.getNewJLabel("Average of rounds to find the exit :",2),BorderLayout.CENTER);
				finalInformationsPanel.add(this.finalWindow.getNewJLabel(((int)(this.roundsSum / this.maxSimulationNumber))+"/"+this.maxRound,2),BorderLayout.CENTER);
				
				this.finalWindow.getJLabelByText("Exits found :").setBackground(new Color(50,50,50));
			    this.finalWindow.getJLabelByText("Average of rounds to find the exit :").setBackground(new Color(50,50,50));
			}else{												//Si la simulation est déterministe

				finalInformationsPanel.add(this.finalWindow.getNewJLabel("Exit found :",2),BorderLayout.CENTER);
				finalInformationsPanel.add(this.finalWindow.getNewJLabel(""+exitIsFound,2),BorderLayout.CENTER);
				finalInformationsPanel.add(this.finalWindow.getNewJLabel("Number of rounds :",2),BorderLayout.CENTER);
				finalInformationsPanel.add(this.finalWindow.getNewJLabel(this.round+"/"+this.maxRound,2),BorderLayout.CENTER);

				this.finalWindow.getJLabelByText("Exit found :").setBackground(new Color(50,50,50));
			    this.finalWindow.getJLabelByText("Number of rounds :").setBackground(new Color(50,50,50));
			}
			//Fin de l'innitialisation du contenu de finalInformationsPanel

		    this.finalWindow.add(finalInformationsPanel,BorderLayout.CENTER); //Ajout de finalInformationsPanel à la fenêtre de fin

			this.finalWindow.setVisible(true);				//Affichage de la fenêtre de fin
		}
	}

	/**Méthode affichant un message d'erreur si la simulation ne pourra jamais atteindre la sortie*/
	public void thereIsNoExit(){

		//Fermeture des fenêtres de simulation
		this.optionsWindow.setVisible(false);
		this.simulationWindow.setVisible(false);

		//Innitialisation d'un Panel contenant les informations finales
		Panel finalInformationsPanel = new Panel();
		finalInformationsPanel.setLayout(new GridLayout(2,2));

		//Innitialisation du contenu de finalInformationsPanel
		finalInformationsPanel.add(this.finalWindow.getNewJLabel("What have you done?",2),BorderLayout.CENTER);
		finalInformationsPanel.add(this.finalWindow.getNewJLabel("There is no way to find the exit!",2),BorderLayout.CENTER);
		//Fin de l'innitialisation du contenu de finalInformationsPanel

		this.finalWindow.add(finalInformationsPanel,BorderLayout.CENTER); //Ajout de finalInformationsPanel à la fenêtre de fin

		this.finalWindow.setVisible(true);				//Affichage de la fenêtre de fin
	}

	/**Méthode retournant un Panel contenant le labyrinthe créé par l'utilisateur
	@param window Variable indiquant dans quelle fenêtre enregistrer les Panels du labyrinthe
	@param gridSize Variable indiquant la taille de la grille. Nombre total de cases : gridSize * gridSize*/
	public Panel getStartingGridPanel(Window window,int gridSize){

		//Création d'un Panel contenant une grille
		Panel gridPanel = new Panel();
		gridPanel.setNewGrid(window,this.gridSize,false);

		//Les ID des Panels de la grilles sont numérotés de 0 à gridSize * gridSize et rangés ligne par ligne alors que dans typeArray, les types sont rangés colonne par colonne
		for(int i = 0; i < this.gridSize; i++){

	      for(int j = 0; j < this.gridSize; j++){

	        window.getPanelByID(j + (i * this.gridSize)).setType(this.typeArray[i + (j * this.gridSize)]); //Pour chaque Panels(cases), ajout de son type contenu dans typeArray
	      }
	    }

	    return gridPanel;							//Le labyrinthe est retourné
	}

	/**Méthode qui permet de donner l'ID de la prochaine case
	@param id Variable indiquant l'ID de la prochaine case*/
	public void setNextPanelID(int id){

		this.nextPanelID = id;
	}

	/**Méthode qui renvoie l'ID de la prochaine case*/
	public int getNextPanelID(){

		return this.nextPanelID;
	}
}