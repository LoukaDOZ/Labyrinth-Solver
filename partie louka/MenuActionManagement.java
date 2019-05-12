import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

//Cette va gérer tous les boutons des menus de sélection
public class MenuActionManagement implements MouseListener{

	private JLabel currentLabel;								//Variable qui contient le JLabel concerné par des modifications
	
	private String currentSelection;							//Variable qui va contenir la sélection du moyen de remplir la grille et l'algorithme choisi
	private String currentSelection2;							//Variable qui va contenir la sélection du mode de simulation
	
	private int gridSize;										//Variable indiquant la taille de la grille. Nombre total de cases : gridSize * gridSize
	
	private Timer timer;										//Timer pour quand l'utilisateur reste appuyé sur la souris

	private Window welcomeWindow;								//Fenêtre de début
	private Window createWindow;								//Fenêtre du choix de création de labyrinthe
	private Window mapCreatingWindow;							//Fenêtre de création de labyrinthe
	private Window algorithmWindow;								//Fenêtre de sélection de la simulation et de l'algorithme

	private Tab loadedMap;										//Lecture et écriture de fichier
	private File file;											//Variable qui va contenir le fichier ouvert

	//Constructeur
	public MenuActionManagement(Window welcomeWindow, Window createWindow, Window mapCreatingWindow, Window algorithmWindow){

		this.currentSelection = "none";							//Innitialisation avec une valeur nulle
		this.currentSelection2 = "none";						//Innitialisation avec une valeur nulle
		this.gridSize = 20;

		this.welcomeWindow = welcomeWindow;
		this.createWindow = createWindow;
		this.mapCreatingWindow = mapCreatingWindow;
		this.algorithmWindow = algorithmWindow;

		this.loadedMap = new Tab();
	}

	//Méthode qui permet de donner aux Panels du créateur de labyrinthe les types lus dans le fichier
	public void addMapToMapCreator(){

	    this.gridSize = this.loadedMap.getSize();				//Récupération de la taille de la grille

	    //Les ID des Panels de la grilles sont numérotés de 0 à gridSize * gridSize et rangés ligne par ligne alors que getMap() renvoie les types sont rangés colonne par colonne
	    for(int i = 0; i < this.gridSize; i++){

	      for(int j = 0; j < this.gridSize; j++){

	        this.mapCreatingWindow.getPanelByID(j + (i * this.gridSize)).setType(this.loadedMap.getMap(i + (j * this.gridSize))); //Pour chaque Panels(cases), ajout de son type retourné par getMap()
	      }
	    }
	}

	//Méthode qui permet de mettre à jour la taille à la grille
	public void setGridSize(int size){

		if(size > 4 && size <= 100){							//Si la nouvelle taille est bien comprise entre 4 et 100

			this.createWindow.getJLabelByText("Choose the size of the grid ["+this.gridSize+"x"+this.gridSize+"] :").setText("Choose the size of the grid ["+size+"x"+size+"] :"); //Récupération du JLabel affichant la taille de lagrille pour le mettre à jour
			this.gridSize = size;								//La taille est mise à jour
		}
	}

	//Méthode qui renvoie la taille de la grille
	public int getGridSize(){

		return this.gridSize;
	}

	//La souris entre
	public void mouseEntered(MouseEvent e) {

	  this.currentLabel = (JLabel)e.getSource();				//Récupération du JLabel concerné
	  this.currentLabel.setBackground(new Color(100,100,100));	//Sa couleur de fond devient grise
	}

	//La souris sort
	public void mouseExited(MouseEvent e) {

		this.currentLabel = (JLabel)e.getSource();				//Récupération du JLabel concerné
		this.currentLabel.setBackground(new Color(0,0,0));		//Sa couleur de fond redevient noire

		//Tests de sélection pour tous les boutons qui doivent rester sélectionnés
		if(this.currentLabel == this.createWindow.getJLabelByText("Void") && this.currentSelection.equals("Void") == true){ //Si c'est le bouton pour un remplissage de grille vide

		  this.currentLabel.setBackground(new Color(100,100,100));	//Sa couleur de fond devient grise
		}

		if(this.currentLabel == this.createWindow.getJLabelByText("Random fill") && this.currentSelection.equals("Random fill") == true){ //Si c'est le bouton pour un remplissage aléatoire

		  this.currentLabel.setBackground(new Color(100,100,100));	//Sa couleur de fond devient grise
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Rubber") && this.currentSelection.equals("Rubber") == true){ //Si c'est le bouton pour gommer

			this.currentLabel.setBackground(new Color(100,100,100));	//Sa couleur de fond devient grise
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put a wall") && this.currentSelection.equals("Put a wall") == true){ //Si c'est le bouton pour mettre un mur

			this.currentLabel.setBackground(new Color(100,100,100));	//Sa couleur de fond devient grise
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put the start") && this.currentSelection.equals("Put the start") == true){ //Si c'est le bouton pour mettre le départ

			this.currentLabel.setBackground(new Color(100,100,100));	//Sa couleur de fond devient grise
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put the exit") && this.currentSelection.equals("Put the exit") == true){ //Si c'est le bouton pour mettre la fin

			this.currentLabel.setBackground(new Color(100,100,100));	//Sa couleur de fond devient grise
		}

		/*Tests pour la fenêtre de choix d'algorithme et de simulation.
		Si le bouton cliqué est "algorithme aléatoire" et la sélection actuelle aussi
		Si le bouton cliqué est "algorithme déterministe" et la sélection actuelle aussi
		Si le bouton cliqué est "simulation manuelle" et la sélection actuelle aussi
		Si le bouton cliqué est "simulation automatique" et la sélection actuelle aussi*/
		if((this.currentLabel == this.algorithmWindow.getJLabelByText("Random") && this.currentSelection.equals("Random") == true) || (this.currentLabel == this.algorithmWindow.getJLabelByText("Determinist") && this.currentSelection.equals("Determinist") == true)
		|| (this.currentLabel == this.algorithmWindow.getJLabelByText("Manual") && this.currentSelection2.equals("Manual") == true) || (this.currentLabel == this.algorithmWindow.getJLabelByText("Automatic") && this.currentSelection2.equals("Automatic") == true)){

			this.currentLabel.setBackground(new Color(100,100,100));	//Sa couleur de fond devient grise
		}
	}

	//Souris cliquée
	public void mouseClicked(MouseEvent e){

		this.currentLabel = (JLabel)e.getSource();				//Récupération du JLabel concerné


		////////////Menu du début du programme////////////
		if(this.currentLabel == this.welcomeWindow.getJLabelByText("Open a grid")){ //Si c'est le bouton pour ouvrir un labyrinthe

			this.welcomeWindow.setVisible(false);				//Le menu de début est fermé

			//Innitialisation de la sélection de fichier
			JFileChooser fileChooser = new JFileChooser(new File("./save"));
			fileChooser.setApproveButtonText("Choix du fichier...");
			fileChooser.showOpenDialog(null);

			this.file = fileChooser.getSelectedFile();			//Récupération du fichier choisi

			if(this.file != null){								//Si un fichier est choisi

				this.loadedMap.loadMap(this.file);				//Récupération du labyrinthe

				//Innitialisation d'une grille
				Panel panel = new Panel(this.mapCreatingWindow.getGridActionManagement());
				panel.setNewGrid(this.mapCreatingWindow,this.loadedMap.getSize(),false);

				this.mapCreatingWindow.add(panel,BorderLayout.CENTER); //Ajout de la grille à la fenêtre de modification de labyrinthe

				this.addMapToMapCreator();						//Ajout du labyrinthe lu dans le fichier à la grille du modificateur de fichier

				this.mapCreatingWindow.setVisible(true);		//Le menu de modification de labyrinthe est affiché
			}else{												//Si aucun fichier n'est choisi

				this.welcomeWindow.setVisible(true);			//Le menu de début est de nouveau affiché
			}
		}

		if(this.currentLabel == this.welcomeWindow.getJLabelByText("Create a grid")){ //Si c'est le bouton pour créer un labyrinthe

			this.welcomeWindow.setVisible(false);				//Le menu de début est fermé
			this.createWindow.setVisible(true);					//Le menu d'innitialisation de labyrinthe est ouvert
		}

		////////////Menu d'innitialisation du labyrinthe////////////
		if(this.currentLabel == this.createWindow.getJLabelByText("Void")){ //Si c'est le bouton pour avoir un labyrinthe vide

			this.currentLabel.setBackground(new Color(100,100,100)); //Sa couleur de fond est grise
			this.createWindow.getJLabelByText("Random fill").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour avoir un labyrinthe rempli aléatoirement est noire
			this.currentSelection = this.currentLabel.getText(); //La sélection actuelle devient
		}

		if(this.currentLabel == this.createWindow.getJLabelByText("Random fill")){ //Si c'est le bouton pour avoir un labyrinthe rempli aléatoirement est noire

			this.currentLabel.setBackground(new Color(100,100,100)); //Sa couleur de fond est grise
			this.createWindow.getJLabelByText("Void").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour avoir un labyrinthe vide est noire
			this.currentSelection = this.currentLabel.getText(); //La sélection actuelle devient
		}

		if(this.currentLabel == this.createWindow.getJLabelByText("Done")){ //Si c'est le bouton pour confirmer

			if(this.currentSelection.equals("none") == false){ //Si il y a bien eu une sélection

				//Innitialisation d'un labyrinthe
				Panel panel = new Panel(this.mapCreatingWindow.getGridActionManagement());
				panel.setNewGrid(this.mapCreatingWindow,this.gridSize,this.currentSelection.equals("Random fill"));

				this.mapCreatingWindow.add(panel,BorderLayout.CENTER); //Ajout du labyrinthe à la fenêtre du modificateur de labyrinthe

				this.createWindow.setVisible(false);			//La fenêtre d'inntialisation de labyrinthe est fermé
				this.mapCreatingWindow.setVisible(true);		//La fenêtre de modification de labyrinthe est ouverte
			}else{												//Si il n'y a pas eu de sélection

				//Innitialisation d'une popup pour indiquer de choisir une option
				Window chooseFillWindow = new Window("Please choose a way to fill",this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 2,this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 4,true);
				chooseFillWindow.setGridLayout(3,1);
				chooseFillWindow.add(chooseFillWindow.getNewJLabel("",2),BorderLayout.CENTER);
				chooseFillWindow.add(chooseFillWindow.getNewJLabel("You have to choose a way to fill the grid",2),BorderLayout.CENTER);
				chooseFillWindow.add(chooseFillWindow.getNewJLabel("",2),BorderLayout.CENTER);
				chooseFillWindow.setVisible(true);

				chooseFillWindow.doPopupAnimation();			//Lancement de la popup
			}
		}

		////////////Menu de modification du labyrinthe////////////
		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Reset")){//Si c'est le bouton pour tout effacer

			//Tous les Panel sont mit au type vide(0)
			for(int i = 0; i < this.mapCreatingWindow.getTotalPanel(); i++){

				this.mapCreatingWindow.getPanelByID(i).setType(0);
			}
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Filled with walls")){//Si c'est le bouton pour remplir de murs

			//Tous les Panels sont mit au type mur(1)
			for(int i = 0; i < this.mapCreatingWindow.getTotalPanel(); i++){

				this.mapCreatingWindow.getPanelByID(i).setType(1);
			}
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Random fill")){//Si c'est le bouton pour remplir aléatoirement la grille

			int i;
			//Tous les Panels sont remplis aléatoirement entre vide(0) et mur(1)
			for(i = 0; i < this.mapCreatingWindow.getTotalPanel(); i++){

				this.mapCreatingWindow.getPanelByID(i).setRandomType();
			}

			for(i = 2; i < 4; i++){								//Pour 2 tours

			  	int random = (int)(Math.random() * (this.gridSize * this.gridSize)); //Obtention d'un ID aléatoire

			  	this.mapCreatingWindow.getPanelByID(random).setType(i); //Le Panel correspondant à l'ID est le départ ou la sortie
		  	}
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Rubber")){ //Si c'est le bouton pour effacer

			this.currentLabel.setBackground(new Color(100,100,100)); //Sa couleur de fond est grise
			this.mapCreatingWindow.getJLabelByText("Put a wall").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour mettre un mur est noire
			this.mapCreatingWindow.getJLabelByText("Put the start").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour mettre le départ est noire
			this.mapCreatingWindow.getJLabelByText("Put the exit").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour mettre la sortie est noire
			this.currentSelection = this.currentLabel.getText(); //La sélection actuelle devient
			this.mapCreatingWindow.getGridActionManagement().setAction(this.currentLabel.getText()); //Mise à jour de l'action de l'utilisateur
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put a wall")){ //Si c'est le bouton pour mettre un mur

			this.currentLabel.setBackground(new Color(100,100,100)); //Sa couleur de fond est grise
			this.mapCreatingWindow.getJLabelByText("Rubber").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour effacer est noire
			this.mapCreatingWindow.getJLabelByText("Put the start").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour mettre le départ est noire
			this.mapCreatingWindow.getJLabelByText("Put the exit").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour mettre la sortie est noire
			this.currentSelection = this.currentLabel.getText(); //La sélection actuelle devient
			this.mapCreatingWindow.getGridActionManagement().setAction(this.currentLabel.getText()); //Mise à jour de l'action de l'utilisateur
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put the start")){ //Si c'est le bouton pour mettre le départ

			this.currentLabel.setBackground(new Color(100,100,100)); //Sa couleur de fond est grise
			this.mapCreatingWindow.getJLabelByText("Rubber").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour effacer est noire
			this.mapCreatingWindow.getJLabelByText("Put a wall").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour mettre un mur est noire
			this.mapCreatingWindow.getJLabelByText("Put the exit").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour mettre la sortie est noire
			this.currentSelection = this.currentLabel.getText(); //La sélection actuelle devient
			this.mapCreatingWindow.getGridActionManagement().setAction(this.currentLabel.getText()); //Mise à jour de l'action de l'utilisateur
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Put the exit")){ //Si c'est le bouton pour mettre la sortie

			this.currentLabel.setBackground(new Color(100,100,100)); //Sa couleur de fond est grise
			this.mapCreatingWindow.getJLabelByText("Rubber").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour effacer est noire
			this.mapCreatingWindow.getJLabelByText("Put a wall").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour mettre un mur est noire
			this.mapCreatingWindow.getJLabelByText("Put the start").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour mettre le départ est noire
			this.currentSelection = this.currentLabel.getText(); //La sélection actuelle devient
			this.mapCreatingWindow.getGridActionManagement().setAction(this.currentLabel.getText()); //Mise à jour de l'action de l'utilisateur
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Save the map")){ //Si c'est le bouton pour sauvegarder le labyrinthe

			if(this.mapCreatingWindow.getPanelByType(2) == null || this.mapCreatingWindow.getPanelByType(3) == null){ //Si le départ et la sortie n'ont pas été placés

				//Innitialisation d'un popup pour indiquer que le départ et/ou la sortie ne sont pas placés
				Window setStartExitWindow = new Window("Please set the start and the exit position",this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 2,this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 4,true);
				setStartExitWindow.setGridLayout(4,1);
				setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);

				if(this.mapCreatingWindow.getPanelByType(2) == null){ //Si le départ n'est pas placé, on prévient qu'il ne l'est pas

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("You have to choose the starting position",2),BorderLayout.CENTER);
				}else{

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);
				}

				if(this.mapCreatingWindow.getPanelByType(3) == null){ //Si la sortie n'est pas placées, on prévient qu'elle ne l'est pas

						setStartExitWindow.add(setStartExitWindow.getNewJLabel("You have to choose the exit position",2),BorderLayout.CENTER);
				}else{

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);
				}

				setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);

				setStartExitWindow.doPopupAnimation();			//Lancement de la popup
				}else{											//Si le départ et la sortie sont placés

				//Sauvegarde du labyrinthe//
				int[] map;

				this.mapCreatingWindow.setVisible(false);		//La fenêtre de modification de labyrinthe est fermée
				JFileChooser fileSaver = new JFileChooser(new File("./save"));
				int returnVal = fileSaver.showOpenDialog(null);
	 			fileSaver.setApproveButtonText("Sauvegarde du fichier ..");

	 			if(returnVal == JFileChooser.APPROVE_OPTION) {
					this.file = fileSaver.getSelectedFile(); 
					map = mapCreatingWindow.getGridAsAnArray(this.gridSize);
					this.loadedMap.saveMap(file,map,this.gridSize);
				}
				//Fin de sauvegarde//

				this.mapCreatingWindow.setVisible(true);		//La fenêtre de modification de labyrinthe est de nouveau affichée
			}		
		}

		if(this.currentLabel == this.mapCreatingWindow.getJLabelByText("Done")){ //Si c'est le bouton pour confirmer

			if(this.mapCreatingWindow.getPanelByType(2) == null || this.mapCreatingWindow.getPanelByType(3) == null){ //Si le départ et la sortie n'ont pas été placés

				//Innitialisation d'un popup pour indiquer que le départ et/ou la sortie ne sont pas placés
				Window setStartExitWindow = new Window("Please set the start and the exit position",this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 2,this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 4,true);
				setStartExitWindow.setGridLayout(4,1);
				setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);

				if(this.mapCreatingWindow.getPanelByType(2) == null){ //Si le départ n'est pas placé, on prévient qu'il ne l'est pas

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("You have to choose the starting position",2),BorderLayout.CENTER);
				}else{

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);
				}

				if(this.mapCreatingWindow.getPanelByType(3) == null){ //Si la sortie n'est pas placée, on prévient qu'elle ne l'est pas

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("You have to choose the exit position",2),BorderLayout.CENTER);
				}else{

					setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);
				}

				setStartExitWindow.add(setStartExitWindow.getNewJLabel("",2),BorderLayout.CENTER);
				
				setStartExitWindow.doPopupAnimation();			//Lancement de la popup
			}else{

				this.mapCreatingWindow.setVisible(false);		//La fenêtre de modification de labyrinthe est fermée
				this.algorithmWindow.setVisible(true);			//La fenêtre de sélection de l'algorithme et de la simulation est affichée
			}
		}

		////////////Menu de sélection de l'algorithme et de la simulation////////////
		if(this.currentLabel == this.algorithmWindow.getJLabelByText("Random")){ //Si c'est le bouton pour un algorithme aléatoire

			this.currentLabel.setBackground(new Color(100,100,100)); //Sa couleur de fond est grise
			this.algorithmWindow.getJLabelByText("Determinist").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour un algorithme déterministe est noire
			this.currentSelection = this.currentLabel.getText(); //La sélection actuelle devient
		}

		if(this.currentLabel == this.algorithmWindow.getJLabelByText("Determinist")){ //Si c'est le bouton pour un algorithme déterministe

			this.currentLabel.setBackground(new Color(100,100,100)); //Sa couleur de fond est grise
			this.algorithmWindow.getJLabelByText("Random").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour un algorithme aléatoire est noire
			this.currentSelection = this.currentLabel.getText(); //La sélection actuelle devient
		}

		if(this.currentLabel == this.algorithmWindow.getJLabelByText("Manual")){ //Si c'est le bouton pour une simulation manuelle


			this.currentLabel.setBackground(new Color(100,100,100)); //Sa couleur de fond est grise
			this.algorithmWindow.getJLabelByText("Automatic").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour simulation automatique est noire
			this.currentSelection2 = this.currentLabel.getText(); //La sélection actuelle devient
		}

		if(this.currentLabel == this.algorithmWindow.getJLabelByText("Automatic")){ //Si c'est le bouton pour une simulation automatique

			this.currentLabel.setBackground(new Color(100,100,100)); //Sa couleur de fond est grise
			this.algorithmWindow.getJLabelByText("Manual").setBackground(new Color(0,0,0)); //La couleur de fond du bouton pour simulation manuelle est noire
			this.currentSelection2 = this.currentLabel.getText(); //La sélection actuelle devient
		}

		if(this.currentLabel == this.algorithmWindow.getJLabelByText("Done")){ //Si c'est le bouton pour confirmer

			if((this.currentSelection.equals("Random") == true || this.currentSelection.equals("Determinist") == true) && this.currentSelection2.equals("none") == false){ //Si un algorithme à été sélectionné et si une simulation à été sélectionnée

				this.algorithmWindow.setVisible(false);			//La fenêtre de sélection de l'algorithme et de la simulation est fermée
				
				if(this.currentSelection2.equals("Automatic") == true){ //Si le choix est simulation automatic

					//Lancement de la simulation
					AutomaticSimulation simulation = new AutomaticSimulation(this.gridSize,this.mapCreatingWindow.getGridAsAnArray(this.gridSize),this.currentSelection.equals("Random"),this.algorithmWindow.getJTextAreaByOrderOfArrival(1).getText());
				}else{											 //Si le choix est simulation manuelle

					//Lancement de la simulation
					ManualSimulation simulation = new ManualSimulation(this.gridSize,this.mapCreatingWindow.getGridAsAnArray(this.gridSize),this.currentSelection.equals("Random"),this.algorithmWindow.getJTextAreaByOrderOfArrival(1).getText());
				}
			}else{												//S'il manque une sélection

				//Innitialisation d'un popup pour indiquer que quelque chose n'a pas été sélectionné
				Window chooseWindow = new Window("Please choose something",this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 2,this.mapCreatingWindow.getWidth() / 3,this.mapCreatingWindow.getHeight() / 4,true);
				chooseWindow.setGridLayout(4,1);
				chooseWindow.add(chooseWindow.getNewJLabel("",2),BorderLayout.CENTER);

				if(this.currentSelection.equals("Random") == false && this.currentSelection.equals("Determinist") == false){ //Si un algorithme n'a pas été sélectionnée, on previent l'utilisateur

					chooseWindow.add(chooseWindow.getNewJLabel("You have to choose an algorithm",2),BorderLayout.CENTER);
				}else{

					chooseWindow.add(chooseWindow.getNewJLabel("",2),BorderLayout.CENTER);
				}

				if(this.currentSelection2.equals("none") == true){ //Si une simulation n'a pas été sélectionnée, on previent l'utilisateur

					chooseWindow.add(chooseWindow.getNewJLabel("You have to choose a mode",2),BorderLayout.CENTER);
				}else{

					chooseWindow.add(chooseWindow.getNewJLabel("",2),BorderLayout.CENTER);
				}

				chooseWindow.add(chooseWindow.getNewJLabel("",2),BorderLayout.CENTER);
				
				chooseWindow.doPopupAnimation();			//Lancement de la popup
			}
		}
	}

	//Souris pressée
	public void mousePressed(MouseEvent e){

	  	this.currentLabel = (JLabel)e.getSource();				//Récupération du JLabel concerné

	  	if(this.currentLabel == this.createWindow.getJLabelByText("Bigger")){ //Si c'est le bouton pour augmenter la taille de la grille

	  		this.setGridSize(this.gridSize + 1);				//On augmente la taille de la grille de 1
	  		this.timer = new Timer(150,null);					//On créé un nouveau Timer de 0,150 secondes
    		this.timer.addActionListener(new TimerManagement(this,this.timer,"bigger grid")); //On ajoute un nouveau TimerLanagement
		}

		if(this.currentLabel == this.createWindow.getJLabelByText("Smaller")){ //Si c'est le bouton pour réduire la taille de la grille

	  		this.setGridSize(this.gridSize - 1);				//On réduit la taille de la grille de 1
	  		this.timer = new Timer(150,null);					//On créé un nouveau Timer de 0,150 secondes
    		this.timer.addActionListener(new TimerManagement(this,this.timer,"smaller grid")); //On ajoute un nouveau TimerLanagement
		}
	}

	//Souris lachée
	public void mouseReleased(MouseEvent e){

		this.currentLabel = (JLabel)e.getSource();				//Récupération du JLabel concerné

		if(this.currentLabel == this.createWindow.getJLabelByText("Bigger") || this.currentLabel == this.createWindow.getJLabelByText("Smaller")){ //Si c'est le bouton pour réduire ou augmenter la taille de la grille
	  	
	  		this.timer.stop();									//Le timer est arrêté
	  	}
	}
}