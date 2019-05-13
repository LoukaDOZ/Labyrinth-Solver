import javax.swing.*;
import java.awt.*;

/**Cette classe hérite de JPanel et gère les différents types de Panel*/
public class Panel extends JPanel{

	private int id;												//ID du Panel, il permet de le différencier des autres
	private int type;											//Type du Panel (0[case vide],1[mur],2[position de la simulation],3[sortie])
	private Color currentColor;									//Couleur de fond actuelle du Panel
	private Color originalColor;								//Couleur de fond lors de l'innitialisation du Panel
	private boolean isNextDirection;							//Variable indiquand si ce Panel est la prochaine direction(true) ou non(false)
	private ImageIcon currentImage;								//Image actuelle du Panel
	private GridActionManagement gridActionManagement;			//Gérant de la création du labyrinthe

	/**Constructeur pour un Panel sans particularité*/
	public Panel(){

		super();
	}

	/**Constructeur pour un Panel avec une innitialisation de this.gridActionManagement
	@param gridActionManagement Gérant de la création de labyrinthe*/
	public Panel(GridActionManagement gridActionManagement){

		super();

		this.gridActionManagement = gridActionManagement;
	}

	/**Constructeur pour un Panel considéré comme une case du labyrinthe
	@param id ID du Panel (équivaut à un numéro de case)
	@param color Couleur de base du Panel*/
	public Panel(int id,Color color){

		super();

		this.id = id;
		this.type = 0;
		this.isNextDirection = false;
		this.originalColor = color;
		this.currentColor = this.originalColor;
		this.currentImage = null;
		this.setOpaque(true);
	}

	/**Remplissage du Panel par sa couleur et son image*/
	public void paintComponent(Graphics paintbrush) {

	    Graphics secondPaintbrush = paintbrush.create();

	    if(this.isNextDirection == false){						//Si il n'est pas la prochaine direction

	    	//Il est colorié par sa couleur actuelle
	    	secondPaintbrush.setColor(this.currentColor);
	    	secondPaintbrush.fillRect(0,0,this.getWidth(),this.getHeight());
	    }else{													//Si il est la prochaine direction

	    	//Il est rempli d'un carré plein orange
	    	secondPaintbrush.setColor(new Color(255,180,0));
	    	secondPaintbrush.fillRect(0,0,this.getWidth(),this.getHeight());

	    	//Innitialisation des coordonnées et taille pour un plus petit carré 
	    	int positionX = (int)(this.getWidth() * 0.2);
	    	int positionY = (int)(this.getHeight() * 0.2);
	    	int sizeX = this.getWidth() - (positionX * 2);
	    	int sizeY = this.getHeight() - (positionY * 2);

	    	//Affichage d'un plus petit carré de la couleur actuelle pour créer un trou au milieu du carré orange
	    	secondPaintbrush.setColor(this.currentColor);
	    	secondPaintbrush.fillRect(positionX,positionY,sizeX,sizeY);
	    }

	    if(this.currentImage != null){							//Si il possède une image

	    	//Affichage de l'image
	    	this.currentImage = new ImageIcon(this.currentImage.getImage().getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_DEFAULT));
	    	this.currentImage.paintIcon(this,secondPaintbrush,0,0);
	    }
	  }

	  /**Méthode permettant de donner un type au Panel
	  @param type Nouveau type*/
	  public void setType(int type){

		this.type = type;

		if(this.type == 0){										//Si le type est "case vide"

	    	this.currentColor = this.originalColor;				//La couleur actuelle est la couleur originale
	    	this.currentImage = null;							//Il n'a pas d'image
	    }

		if(this.type == 1){										//Si le type est "mur"

	    	this.currentColor = new Color(this.originalColor.getRed() - 240,this.originalColor.getRed() - 240,this.originalColor.getRed() - 240);//La couleur actuelle est la couleur originale est un noir lié a la couleur de base
	    	this.currentImage = null;							//Il n'a pas d'image
	    }

	    if(this.type == 2){										//Si le type est "départ"

	    	this.currentColor = new Color(0,255,0);				//La couleur actuelle est verte
	    	this.currentImage = new ImageIcon("Images/player.png"); //Innitialisation de son image
	    }

	    if(this.type == 3){										//Si le type est "sortie"

	    	this.currentColor = new Color(255,0,0);				//La couleur actuelle est rouge
	    	this.currentImage = new ImageIcon("Images/exit.png"); //Innitialisation de son image
	    }

		this.repaint();											//Affichage des changements
	}

	/**Méthode qui donne au Panel un type aléatoire entre "vide" et "mur"*/
	public void setRandomType(){

	    int random = (int)(Math.random() * 2);					//Récupération d'une valeur entre 0 et 1

	    this.setType(random);									//Le type vaut random
	}

	/**Méthode qui indique au Panel qu'il est la prochaine direction
	@param isNextDirection Variable indiquant si le Panel est la prochaine direction(true) ou non(false)*/
	public void setIsNextDirection(boolean isNextDirection){

		this.isNextDirection = isNextDirection;
		this.repaint();											//Affichage des changements
	}

	/**Méthode qui donne au Panel une grille de labyrinthe
	@param window Fenêtre dans laquelle les Panels correspondants aux cases de la grille, seront enregistrés
	@param gridSize Variable indiquant la taille de la grille. Nombre total de cases : gridSize * gridSize
	@param isRandomFill Variable indiquant si le remplissage de la grille est aléatoire(true) ou non(false)*/
	public void setNewGrid(Window window,int gridSize,boolean isRandomFill){

	    this.setLayout(new GridLayout(gridSize,gridSize));		//Division du Panel en cases de la taille de la grille

	    //Innitialisation de variables
	    Color color;											
	    int random;
	    int i;

	    for(i = 0; i < gridSize * gridSize; i++){				//Pour chaque cases

	    	if((gridSize % 2) == 0){							//Si la taille de la grille est paire

		        if(((i + (i / gridSize)) % 2) == 0){			//Si la case est paire

		          	color = new Color(240,240,240);				//color est un gris très clair
		        }else{											//Si la case est impaire

		          	color = new Color(255,255,255);				//color est blanc
		        }
	    	}else{												//Si la taille de la grille est impaire

		        if((i % 2) == 0){								//Si la case est paire

		          	color = new Color(240,240,240);				//color est un gris très clair
		        }else{											//Si la case est impaire

		          	color = new Color(255,255,255);				//color est blanc
		        }
	    	}

	   		Panel panel = new Panel(i,color);					//Création d'un Panel(case) avec i pour ID et color pour couleur de base

		    if(isRandomFill == true){							//Si le remplissage est aléatoire

		    random = (int)(Math.random() * 2);					//Récupération d'une valeur entre 0 et 1

		        if(random == 1){								//Si random vaut 1

		          	panel.setType(1);							//Le Panel est un mur
		        }												//Si random vaut 0, le Panel est une case vide et est déjà de la bonne couleur
		    }

	      	panel.addMouseListener(this.gridActionManagement);	//Ajout de gérant de grille de création de labyrinthe
	      	window.updatePanelArray(panel);						//Ajout du Panel dans la liste de Panel de la fenêtre
	      	this.add(panel,BorderLayout.CENTER);				//Ajout du Panel servant de case au Panel contenant toute la grille
	    }

	    if(isRandomFill == true){								//Si le remplissage est aléatoire

	    	for(i = 2; i < 4; i++){								//Pour 2 tours

	    		random = (int)(Math.random() * (gridSize * gridSize)); //Récupération dune valeur en 0 et le nombre total de case

	    		window.getPanelByID(random).setType(i);			//Le Panel ayant l'ID correspondant à la valeur prend le type départ(2) ou sortie(3)
	    	}
	    }
  	}

  	/**Méthode pour récupéré le type du Panel*/
	public int getType(){

		return this.type;
	}

	/**Méthode pour récupérer l'ID du Panel*/
	public int getID(){

		return this.id;
	}
}