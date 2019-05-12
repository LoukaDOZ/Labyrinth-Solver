import javax.swing.*;
import java.awt.*;

//Cette classe à pour objectif de donner, à la simulation, une direction pour un futur déplacement
public class Algorithm{

	private ManualSimulation manualSimulation;					//Gérant de la simulation manuelle
	private AutomaticSimulation automaticSimulation;			//Gérant de la simulation automatique

	private Window simulationWindow;							//Fenêtre de simulation

	private boolean isRandom;									//Variable indiquant si la simulation est aléatoire(true) ou non(false)
	private boolean isManual;									//Variable indiquant si la simulation est manuelle(true) ou non(false)
	private boolean[] knownPaths;								/*Tableau qui enregistre les chemins déja empruntés par la simulation ou si c'est un mur(true). 
																Il est automatiquement innitialisé avec des "false" c'est-à-dire : aucun chemin n'a été emprunté*/

	private int[] unknownPathsID;								/*Tableau resemblant au principe d'une pile. Il contient les ID des Panels que la simulation à pu voir,
																ce qui permet à la simulation de revenir sur ces pas si nécessaire*/
	private int gridSize;										//Variable indiquant la taille de la grille. Nombre total de cases : gridSize * gridSize.
	private int nextPanelID;									//Variable indiquant l'ID du prochain Panel vers lequel la simulation va se déplacer
	private int playerPosition;									//Variable indiquant l'ID du Panel sur lequel la simulation est actuellement
	private int northCase;										//Variable indiquant l'ID du Panel du dessus
	private int eastCase;										//Variable indiquant l'ID du Panel du dessous
	private int southCase;										//Variable indiquant l'ID du Panel sur la droite
	private int westCase;										//Variable indiquant l'ID du Panel sur la gauche

	private String direction;									//Variable indiquant la direction vers laquelle la simulation va se déplacer sous forme de mot(North,South,East,West)

	//Constructeur de la classe dans le cas d'un simulation manuelle
	public Algorithm(boolean isRandom,ManualSimulation manualSimulation,Window simulationWindow,int gridSize){

		this.isRandom = isRandom;
		this.isManual = true;
		this.manualSimulation = manualSimulation;
		this.simulationWindow = simulationWindow;
		this.gridSize = gridSize;
		this.knownPaths = new boolean[this.gridSize * this.gridSize]; //Initialisation avec la taille du nombre total de cases
	}

	//Constructeur de la classe dans le cas d'un simulation automatique
	public Algorithm(boolean isRandom,AutomaticSimulation automaticSimulation,Window simulationWindow,int gridSize){

		this.isRandom = isRandom;
		this.isManual = false;
		this.automaticSimulation = automaticSimulation;
		this.simulationWindow = simulationWindow;
		this.gridSize = gridSize;
		this.knownPaths = new boolean[this.gridSize * this.gridSize]; //Initialisation avec la taille du nombre total de cases
	}

	//Méthode chargée de trouver une nouvelle direction pour la simulation
	public String getDirection(){

		this.playerPosition = this.simulationWindow.getPanelByType(2).getID(); //Récupération de la position actuelle de la simulation

		if(this.isRandom == true){									//Si la simulation est manuelle

			this.direction = this.getRandomDirection();				//Récupération d'une direction aléatoire
	
		}else{														//Sinon elle est automatique

			this.getDeterministDirection();							//Donc Récupération d'une direction avec l'agorithme déterministe
		}

		if(this.direction.equals("North")){							//Si la direction est le Nord

			if(this.isManual == true){								//Si la simulation est manuelle

				this.manualSimulation.setNextPanelID(this.playerPosition - this.gridSize); //Indication à la simulation manuelle que la prochaine case est celle au Nord de la position actuelle
				this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setIsNextDirection(true); //Affichage de l'indication visuelle de la prochaine case pour le joueur
			}else{													//Si la simulation est automatique, il n'y a pas besoin d'indiquations visuelles pour le joueur puisqu'il ne voit pas la grille

				this.automaticSimulation.setNextPanelID(this.playerPosition - this.gridSize); //Indication à la simulation automatique que la prochaine case est celle au Nord de la position actuelle
			}
		}

		if(this.direction.equals("East")){							//Si la direction est l'Est

			if(this.isManual == true){								//Si la simulation est manuelle

				this.manualSimulation.setNextPanelID(this.playerPosition + 1); //Indication à la simulation manuelle que la prochaine case est celle à l'Est de la position actuelle
				this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setIsNextDirection(true); //Affichage de l'indication visuelle de la prochaine case pour le joueur
			}else{													//Si la simulation est automatique, il n'y a pas besoin d'indiquations visuelles pour le joueur puisqu'il ne voit pas la grille

				this.automaticSimulation.setNextPanelID(this.playerPosition + 1); //Indication à la simulation automatique que la prochaine case est celle à l'Est de la position actuelle
			}
		}

		if(this.direction.equals("South")){							//Si la direction est le Sud

			if(this.isManual == true){								//Si la simulation est manuelle

				this.manualSimulation.setNextPanelID(this.playerPosition + this.gridSize); //Indication à la simulation manuelle que la prochaine case est celle au Sud de la position actuelle
				this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setIsNextDirection(true); //Affichage de l'indication visuelle de la prochaine case pour le joueur
			}else{													//Si la simulation est automatique, il n'y a pas besoin d'indiquations visuelles pour le joueur puisqu'il ne voit pas la grille

				this.automaticSimulation.setNextPanelID(this.playerPosition + this.gridSize); //Indication à la simulation automatique que la prochaine case est celle au Sud de la position actuelle
			}
		}

		if(this.direction.equals("West")){							//Si la direction est l'Ouest

			if(this.isManual == true){								//Si la simulation est manuelle

				this.manualSimulation.setNextPanelID(this.playerPosition - 1); //Indication à la simulation manuelle que la prochaine case est celle à l'Ouest de la position actuelle
				this.simulationWindow.getPanelByID(this.manualSimulation.getNextPanelID()).setIsNextDirection(true); //Affichage de l'indication visuelle de la prochaine case pour le joueur
			}else{													//Si la simulation est automatique, il n'y a pas besoin d'indiquations visuelles pour le joueur puisqu'il ne voit pas la grille

				this.automaticSimulation.setNextPanelID(this.playerPosition - 1); //Indication à la simulation automatique que la prochaine case est celle à l'Ouest de la position actuelle
			}
		}

		return this.direction;										//Renvoie de la direction sous forme de mot
	}

	//Méthode renvoyant sous forme de mot une direction sélectionnée aléatoirement
	public String getRandomDirection(){

		int direction;												//Variable contenant un entier de 0 à 3 équivalent à chaque directions

		while(true){												//Tant que l'on à pas de direction

			direction = (int)(Math.random() * 4);					//On récupère un entier entre 0 et 3

			if(direction == 0 && this.playerPosition >= this.gridSize){ //Si direction vaut 0 et que la position actuelle n'est pas collé au bord haut de la fenêtre

				return "North";										//La direction est le Nord
			}

			if(direction == 1 && ((this.playerPosition + 1) % this.gridSize) != 0){ //Si direction vaut 1 et que la position actuelle n'est pas collé au bord droit de la fenêtre

				return "East";										//La direction est l'Est
			}

			if(direction == 2 && this.playerPosition < ((this.gridSize * this.gridSize) - this.gridSize)){ //Si direction vaut 2 et que la position actuelle n'est pas collé au bord bas de la fenêtre

				return "South";										//La direction est le Sud
			}

			if(direction == 3 && (this.playerPosition % this.gridSize) != 0){ //Si direction vaut 3 et que la position actuelle n'est pas collé au bord gauche de la fenêtre

				return "West";										//La direction est l'Ouest
			}
		}
	}

	//Méthode qui à pour but de mettre dans this.direction, la valeur en mot de la prochaine direction sélectionnée par un algorithme déterministe
	public void getDeterministDirection(){

		this.direction = "Undefined";								//Initialisation de la direction sur "indéfinie"

		if(this.knownPaths[this.playerPosition] == false){			//Si la simulation ne s'est pas déjà retrouvée sur cette case

			this.lookAround();										//Elle observe toutes les directions possibles autour d'elle
		}

		if(this.direction.equals("Undefined") == true){				//Si la sortie n'est pas à coté de la position actuelle

			int lastIdNoted = this.getLastIDNoted();				//Récupération du dernier chemin noté dans la pile

			if(lastIdNoted == this.playerPosition){					//Si ce dernier chemin noté est le même qui la position actuelle

				lastIdNoted = this.getLastIDNoted();				//Récupération du dernier chemin noté dans la pile encore une fois
			}

			if(lastIdNoted >= 0){									//Si un chemin est rétourné						

				this.getDirectionFromID(lastIdNoted);				//Récupération, en mot, de la direction dans laquelle se trouve ce chemin
			}
		}
	}

	//Méthode qui à pour objetif de vérifié si la sortie est à coté de la position actuelle et d'ajouter dans la pile les chemin qui n'ont pas encore été umpruntés par la simulation
	public void lookAround(){

		this.knownPaths[this.playerPosition] = true;				//La position actuelle est considérée comme empruntée

		if(this.playerPosition >= this.gridSize){					//Si la position actuelle n'est pas collé au bord haut de la fenêtre

			this.northCase = this.playerPosition - this.gridSize;	//this.northCase vaut l'ID de la case au Nord

			if(this.simulationWindow.getPanelByID(this.northCase).getType() == 3){ //Si la case au Nord est la sortie
			
				this.direction = "North";							//La direction est : Nord
			}

			if(this.knownPaths[this.northCase] == false){			//Si la case au Nord n'a jamais été empruntée

				this.newPath(this.northCase);						//On ajoute son ID à la pile
			}
		}

		if(((this.playerPosition + 1) % this.gridSize) != 0){		//Si la position actuelle n'est pas collé au bord droit de la fenêtre

			this.eastCase = this.playerPosition + 1;				//this.eastCase vaut l'ID de la case à l'Est

			if(this.simulationWindow.getPanelByID(this.eastCase).getType() == 3){ //Si la case à l'Est est la sortie
			
				this.direction = "East";							//La direction est : Est
			}

			if(this.knownPaths[this.eastCase] == false){			//Si la case à l'Est n'a jamais été empruntée

				this.newPath(this.eastCase);						//On ajoute son ID à la pile
			}
		}

		if(this.playerPosition < ((this.gridSize * this.gridSize) - this.gridSize)){ //Si la position actuelle n'est pas collé au bord bas de la fenêtre

			this.southCase = this.playerPosition + this.gridSize;	//this.southCase vaut l'ID de la case au Sud

			if(this.simulationWindow.getPanelByID(this.southCase).getType() == 3){ //Si la case au Sud est la sortie
			
				this.direction = "South";							//La direction est : Sud
			}

			if(this.knownPaths[this.southCase] == false){			//Si la case au Sud n'a jamais été empruntée

				this.newPath(this.southCase);						//On ajoute son ID à la pile
			}
		}

		if((this.playerPosition % this.gridSize) != 0){			//Si la position actuelle n'est pas collé au bord gauche de la fenêtre

			this.westCase = this.playerPosition - 1;			//this.westCase vaut l'ID de la case à l'Ouest

			if(this.simulationWindow.getPanelByID(this.westCase).getType() == 3){ //Si la case à l'Ouest est la sortie
			
				this.direction = "West";							//La direction est : Ouest
			}

			if(this.knownPaths[this.westCase] == false){			//Si la case à l'Ouest n'a jamais été empruntée

				this.newPath(this.westCase);						//On ajoute son ID à la pile
			}
		}
  	}

  	//Méthode qui s'occupe de répertorier les murs
	public void setThisCaseAsWall(int id){

		this.knownPaths[id] = true;									//La case avec correspondant à "id" vaut true. Elle ne sera plus empruntée
	}

	//Méthode qui retourne le dernier ID de la pile
	public int getLastIDNoted(){

		int id = -1;												//Innitialisation de id à -1. Aucune case n'à pour ID -1

		if(this.unknownPathsID != null){							//Si la pile est vide

			try{

				id = this.unknownPathsID[this.unknownPathsID.length - 1]; //id vaut la dernière valeur de la pile
				this.removePath();									//La dernière valeur de la pile est retirée
			}catch(ArrayIndexOutOfBoundsException ex){				//Si il n'y a plus de valeur dans la pile

		    	if(this.isManual == true){							//Si la simulation est manuelle

		    		this.manualSimulation.thereIsNoExit();			//Message d'erreur indiquant que la sortie ne peut être atteinte
		    	}else{												//Si la simulation est automatique

		    		this.automaticSimulation.thereIsNoExit();		//Message d'erreur indiquant que la sortie ne peut être atteinte
		    	}
		    }catch(NegativeArraySizeException ex){					//Si il n'y a plus de valeur dans la pile

		    	if(this.isManual == true){							//Si la simulation est manuelle

		    		this.manualSimulation.thereIsNoExit();			//Message d'erreur indiquant que la sortie ne peut être atteinte
		    	}else{												//Si la simulation est automatique

		    		this.automaticSimulation.thereIsNoExit();		//Message d'erreur indiquant que la sortie ne peut être atteinte
		    	}
		    }
		}

		return id;													//On retourne id
	}

	//Méthode ajoutant un nouveau chemin à la pile
	public void newPath(int nextPath){

	    if(this.unknownPathsID == null){							//Si le tableau est vide

	     	this.unknownPathsID = new int[2];						//Il est innitialisé à 2
	      	this.unknownPathsID[0] = this.playerPosition;			//On ajoute la position actuelle
	      	this.unknownPathsID[1] = nextPath;						//On ajoute le prochain chemin
	    }else{														//Si le tabelau n'est pas vide

	      	int[] newArray = new int[this.unknownPathsID.length + 2]; //Innitialisation d'un nouveau tableau de 2 cases plus grand que la pile

	      	for(int i = 0; i < this.unknownPathsID.length; i++){	//Chaque valeur de la pile

	        	newArray[i] = this.unknownPathsID[i];				//Est placée dans le nouveau tableau
	      	}

	      	newArray[newArray.length - 2] = this.playerPosition;	//Les cases vides sont remplis avec l'ID de la position actuelle
	     	newArray[newArray.length - 1] = nextPath;				//Et l'ID du prochain chemin
	      	this.unknownPathsID = new int[newArray.length];			//La pile est agrandie de 2 case
	      	this.unknownPathsID = newArray;							//La pile prend les valeurs du nouveau tableau
	    }
  	}

  	//Méthode qui supprime la dernière valeur de la pile
  	public void removePath(){

	    int[] newArray = new int[this.unknownPathsID.length - 1];	//Innitialisation d'un nouveau tableau d'une cases de moins que la pile

	    for(int i = 0; i < newArray.length; i++){					//Chaque valeur de la pile

	        newArray[i] = this.unknownPathsID[i];					//Est placée dans le nouveau tableau
	    }

	    this.unknownPathsID = new int[newArray.length - 1];			//La pile est reduite d'une case
	    this.unknownPathsID = newArray;								//La pile prend les valeurs du nouveau tableau
	}

	//Méthode qui donne une direction en mot par rapport à un id
	public void getDirectionFromID(int id){

		this.direction = "Undefined";								//Innitialisation de this.direction sur "indéfini"

		if(this.playerPosition >= this.gridSize){					//Si la position actuelle n'est pas collé au bord haut de la fenêtre

			this.northCase = this.playerPosition - this.gridSize;	//this.northCase vaut l'ID de la case au Nord

			if(this.simulationWindow.getPanelByID(this.northCase).getID() == id){ //Si la case au Nord à le même ID que "id"
			
				this.direction = "North";							//La direction est : Nord
			}
		}

		if(((this.playerPosition + 1) % this.gridSize) != 0){		//Si la position actuelle n'est pas collé au bord droit de la fenêtre

			this.eastCase = this.playerPosition + 1;				//this.eastCase vaut l'ID de la case à l'Est

			if(this.simulationWindow.getPanelByID(this.eastCase).getID() == id){ //Si la case à l'Est à le même ID que "id"
			
				this.direction = "East";							//La direction est : Est
			}
		}

		if(this.playerPosition < ((this.gridSize * this.gridSize) - this.gridSize)){ //Si la position actuelle n'est pas collé au bord bas de la fenêtre

			this.southCase = this.playerPosition + this.gridSize;	//this.southCase vaut l'ID de la case au Sud

			if(this.simulationWindow.getPanelByID(this.southCase).getID() == id){ //Si la case au Sud à le même ID que "id"
			
				this.direction = "South";							//La direction est : Sud
			}
		}

		if((this.playerPosition % this.gridSize) != 0){				//Si la position actuelle n'est pas collé au bord gauche de la fenêtre

			this.westCase = this.playerPosition - 1;				//this.westCase vaut l'ID de la case à l'Ouest

			if(this.simulationWindow.getPanelByID(this.westCase).getID() == id){ //Si la case aà l'Ouest à le même ID que "id"
			
				this.direction = "West";							//La direction est : Ouest
			}
		}
	}
}
