import javax.swing.*;
import java.awt.*;

/**Cette classe gère héritée de JFrame les fenêtre ainsi que les éléments qui la composent*/
public class Window extends JFrame{

	private JLabel[] labelArray;                                  //Tableau contenant tous les JLabel de la fenêtre
	private Panel[] panelArray;                                   //Tableau contenant tous les Panels de la fenêtre
	private JTextArea[] textAreaArray;                            //Tableau contenant tous les JTextArea de la fenêtre
	private MenuActionManagement menuActionManagement;            //Gérant des menus de sélection
  private GridActionManagement gridActionManagement;            //Gérant de la grille de création de labyrinthe
	private GridLayout gridLayout;                                //Disposition de la fenêtre

  /**Constructeur
  @param title Titre de la fenêtre
  @param sizeX Taille horizontale de la fenêtre
  @param sizeY Taille verticale de la fenêtre
  @param locationX Position horizontale sur l'écran
  @param locationY Position verticale sur l'écran
  @param foreground Détermine si la fenêtre est toujours au premier plan(true) ou non(false)*/
  public Window(String title, int sizeX, int sizeY, int locationX, int locationY, boolean foreground){

      super(title);

      this.gridLayout = new GridLayout(0,1);                    //Initialisation de la disposition
      
      this.setMinimumSize(new Dimension(sizeX,sizeY));          //Initialisation de taille de la fenêtre
      this.setResizable(false);                                 //Fenêtre non redimensionnable
      this.setLocation(locationX,locationY);                    //Initialisation de sa position sur l'écran
      this.setAlwaysOnTop(foreground);                          //Affichage toujours au premier plan ou non
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //Quitte quand on appuie sur la croix
      this.pack();                                              //Adaptation de la fenêtre en fonction de quelle contient
  }

  /**Méthode pour donner à la fenêtre le gérant de menu de sélection
  @param menuActionManagement Gérant des menus de sélection*/
  public void setManagement(MenuActionManagement menuActionManagement){

      this.menuActionManagement = menuActionManagement;
  }

  /**Méthode pour donner à la fenêtre le gérant de grille de création de labyrinthe
  @param gridActionManagement Gérant de la grille de labyrinthe*/
  public void setManagement(GridActionManagement gridActionManagement){

      this.gridActionManagement = gridActionManagement;
  }

  /**Méthode pour donner à la fenêtre une nouvelle disposition
  @param rows Nouveau nombre de ligne
  @param columns Nouveau nombre de colonnes*/
  public void setGridLayout(int rows, int columns){

   	  this.gridLayout = new GridLayout(rows,columns);           //Nouvelle disposition
      this.setLayout(this.gridLayout);                          //Ajout de la nouvelle disposition
  }

  /**Méthode pour innitialiser, ajouter à la liste et obtenir un nouveau JLabel
  @param text Texte du JLabel
  @param whitchManagement Quel gestionnaire est demandé
  @param fontSize Taille de police du texte*/
  public JLabel getNewJLabel(String text, String whitchManagement, int fontSize){

   	JLabel label = new JLabel(text);                            //Innitialisation avec pour texte : text

   	label.setHorizontalAlignment(JLabel.CENTER);                //Le texte est au milieu du JLabel
   	label.setOpaque(true);                                      //Le background est visible
   	label.setForeground(new Color(255,255,255));                //L'écriture est blanche
   	label.setBackground(new Color(0,0,0));                      //Le fond est noir
   	
      if(fontSize == 1){                                        //Si la taille du texte doit être petite

         label.setFont(new Font("corps",1,this.getWidth() / 80)); //Adaptation de la taille du texte par rapport à la taille de la fenêtre
      }

      if(fontSize == 2){                                        //Si la taille du texte doit être grande

         label.setFont(new Font("corps",1,this.getWidth() / 25)); //Adaptation de la taille du texte par rapport à la taille de la fenêtre
      }

   	if(whitchManagement.equals("MenuActionManagement")){          //Si le JLabel est celui d'un menu

         label.addMouseListener(this.menuActionManagement);       //Il prend comme mouse listener : menuActionManagement
      }

   	this.updateJLabelArray(label);                                //Ajout du JLabel dans la liste des JLabel

   	return label;                                                 //On retourne le JLabel
  }

  /**Méthode pour innitialiser, ajouter à la liste et obtenir un nouveau JLabel, le tout sans mouse listener
  @param text Texte du JLabel
  @param fontSize Taille de la police du texte*/
  public JLabel getNewJLabel(String text, int fontSize){

      JLabel label = new JLabel(text);                            //Innitialisation avec pour texte : text

      label.setHorizontalAlignment(JLabel.CENTER);                //Le texte est au milieu du JLabel
      label.setOpaque(true);                                      //Le background est visible
      label.setForeground(new Color(255,255,255));                //L'écriture est blanche
      label.setBackground(new Color(0,0,0));                      //Le fond est noir

      if(fontSize == 1){                                          //Si la taille du texte doit être petite

         label.setFont(new Font("corps",1,this.getWidth() / 80)); //Adaptation de la taille du texte par rapport à la taille de la fenêtre
      }

      if(fontSize == 2){                                          //Si la taille du texte doit être grande

         label.setFont(new Font("corps",1,this.getWidth() / 25)); //Adaptation de la taille du texte par rapport à la taille de la fenêtre
      }

      this.updateJLabelArray(label);                                //Ajout du JLabel dans la liste des JLabel

      return label;                                                 //On retourne le JLabel
  }

  /**Méthode qui ajoute un JLabel à la liste de JLabel de la fenêtre
  @param label JLabel à ajouter*/
  public void updateJLabelArray(JLabel label){

      if(this.labelArray == null){                                  //Si le tableau est vide

         this.labelArray = new JLabel[1];                           //On l'innitialise à 1 case
         this.labelArray[this.labelArray.length - 1] = label;       //Cette case vaut le JLabel
      }else{                                                        //Si le tableau n'est pas vide

         JLabel[] newArray = new JLabel[this.labelArray.length + 1]; //On innitialise un nouveau tableau plus grand de 1 case

         for(int i = 0; i < this.labelArray.length; i++){           //Pour chaque valeur du tableau

            newArray[i] = this.labelArray[i];                       //On les donne au nouveau tableau
         }

         newArray[newArray.length - 1] = label;                     //On donne le JLabel à la dernière case du nouveau tableau
         this.labelArray = new JLabel[newArray.length];             //On ajoute au tableau une case en plus
         this.labelArray = newArray;                                //L'ancien tableau devient le nouveau tableau
      }
  }

  /**Méthode pour innitialiser, ajouter à la liste et obtenir un nouveau JTextArea
  @param text Texte du JTextArea*/
  public JTextArea getNewJTextArea(String text){

      JTextArea textArea = new JTextArea(text);                     //Innitialisation avec pour texte : text

      textArea.setFont(new Font("corps",1,this.getWidth() / 25));   //Adaptation de la taille du texte par rapport à la taille de la fenêtre
      textArea.addMouseListener(new JTextAreaManagement(this));     //Ajout d'un gérant de JTextArea

      this.updateJTextAreaArray(textArea);                          //Ajout du JTextArea dans la liste des JTextArea

      return textArea;                                              //On retourne le JTextArea
  }

  /**Méthode qui ajoute un JTextArea à la liste de JTextArea de la fenêtre
  @param textArea JTextArea à ajouter à la fenêtre*/
  public void updateJTextAreaArray(JTextArea textArea){

      if(this.textAreaArray == null){                               //Si le tableau est vide

         this.textAreaArray = new JTextArea[1];                     //On l'innitialise à 1 case
         this.textAreaArray[this.textAreaArray.length - 1] = textArea; //Cette case vaut le JTextArea
      }else{                                                        //Si le tableau n'est pas vide

         JTextArea[] newArray = new JTextArea[this.textAreaArray.length + 1]; //On innitialise un nouveau tableau plus grand de 1 case

         for(int i = 0; i < this.textAreaArray.length; i++){        //Pour chaque valeur du tableau

            newArray[i] = this.textAreaArray[i];                    //On les donne au nouveau tableau
         }

         newArray[newArray.length - 1] = textArea;                  //On donne le JTextArea à la dernière case du nouveau tableau
         this.textAreaArray = new JTextArea[newArray.length];       //On ajoute au tableau une case en plus
         this.textAreaArray = newArray;                             //L'ancien tableau devient le nouveau tableau
      }
  }

  /**Méthode qui ajoute un Panel à la liste de JTextArea de la fenêtre
  @param panel Panel à ajouter à la fenêtre*/
  public void updatePanelArray(Panel panel){

      if(this.panelArray == null){                                  //Si le tableau est vide

         this.panelArray = new Panel[1];                            //On l'innitialise à 1 case
         this.panelArray[this.panelArray.length - 1] = panel;       //Cette case vaut le Panel
      }else{                                                        //Si le tableau n'est pas vide

         Panel[] newArray = new Panel[this.panelArray.length + 1];  //On innitialise un nouveau tableau plus grand de 1 case

         for(int i = 0; i < this.panelArray.length; i++){           //Pour chaque valeur du tableau

            newArray[i] = this.panelArray[i];                       //On les donne au nouveau tableau
         }

         newArray[newArray.length - 1] = panel;                     //On donne le Panel à la dernière case du nouveau tableau
         this.panelArray = new Panel[newArray.length];              //On ajoute au tableau une case en plus
         this.panelArray = newArray;                                //L'ancien tableau devient le nouveau tableau
      }
  }

  /**Méthode qui supprime tous les Panel de la liste de Panel*/
  public void removePanelArray(){

      this.panelArray = null;                                       //Le tableau de Panel est effacé
  }

  /**Méthode qui permet de récupérer un JLabel en fonction de son texte
  @param text Texte du JLabel à récupérer*/
  public JLabel getJLabelByText(String text){

      int i;
      //Tant que le JLabel ayant pour texte "text" n'est pas trouvé, la boucle s'arrête lorsqu'il est trouvé
      for(i = 0; this.labelArray[i].getText().equals(text) == false && i < this.labelArray.length; i++){} 

      return this.labelArray[i];                                    //On retourne le JLabel en position i
  }

  /**Méthode qui permet de récupérer un Panel en fonction de son ID
  @param id ID du Panel à récupérer*/
  public Panel getPanelByID(int id){

         int i;
         //Tant que le Panel ayant pour ID "id" n'est pas trouvé, la boucle s'arrête lorsqu'il est trouvé
         for(i = 0; this.panelArray[i].getID() != id && i < this.panelArray.length; i++){}

         return this.panelArray[i];                                  //On retourne le Panel en position i
  }

  /**Méthode qui permet de récupérer un Panel en fonction de son type
  @param type Type du Panel à récupérer*/
  public Panel getPanelByType(int type){

         int i;
         try{                                                        //On essaye de trouver le Panel
            //Tant que le Panel ayant pour type "type" n'est pas trouvé, la boucle s'arrête lorsqu'il est trouvé
            for(i = 0; this.panelArray[i].getType() != type; i++){}

            return this.panelArray[i];                               //On retourne le Panel en position i
      }catch(IndexOutOfBoundsException ex){                          //Si aucun Panel ne possède ce type

         return null;                                                //On retourne null
      }
  }

  /**Méthode pour récupérer le nombre total de Panels de la fenêtre*/
  public int getTotalPanel(){

      return this.panelArray.length;
  }

  /**Méthode qui retourne un tableau à une dimension de toutes les type des Panels du labyrinthe. Il est en colonne par colonne alors que la grille est en ligne par ligne
  @param gridSize Variable indiquant la taille de la grille. Nombre total de cases : gridSize * gridSize*/
  public int[] getGridAsAnArray(int gridSize){

      int[] typeArray = new int[gridSize * gridSize];              //Innitialisation d'un tableau à 1 dimension
      int[][] convertArray = new int[gridSize][gridSize];          //Innitialisation d'un tableau à 2 dimension pour aider à la conversion

      for(int i = 0; i < gridSize; i++){

         for(int j = 0; j < gridSize; j++){

            convertArray[j][i] = this.panelArray[j + (i * gridSize)].getType(); //On range les types dans le tableau à 2 dimensions
         }
      }

      for(int i = 0; i < gridSize; i++){

         for(int j = 0; j < gridSize; j++){

            typeArray[j + (i * gridSize)] = convertArray[i][j];   //On range les types du tableau à 2 dimensions dans le tableau à 1 dimensions
         }
      }

      return typeArray;                                           //On retourne le tableau à 1 dimension
  }

  //Méthode qui retourne un JTextArea en fontion de son ordre d'arrivée
  public JTextArea getJTextAreaByOrderOfArrival(int order){

      return this.textAreaArray[order - 1];
  }

  /**Méthode qui réalise une l'affichage de cette fenêtre comme une popup*/
  public void doPopupAnimation(){

      this.setVisible(true);                                     //On rend la fenêtre visible

      Timer timer = new Timer(3000,null);                        //On créé un timer de 3 secondes
      timer.addActionListener(new TimerManagement(this,timer));  //On lui donne un TimerManagement pour fermer la fenêtre dans 3 secondes
  }

  /**Méthode pour obtenir le gérant des menus de sélection*/
  public MenuActionManagement getMenuActionManagement(){

      return this.menuActionManagement;
  }

  /**Méthode pour obtenir le gérant de la grille de création du labyrinthe*/
  public GridActionManagement getGridActionManagement(){

      return this.gridActionManagement;
  }
}