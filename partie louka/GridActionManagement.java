import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Cette classe gère met les types sur chaque case du labyrinthe dans le menu de création de labyrinthe
public class GridActionManagement implements MouseListener{

	private Panel currentPanel;                                    //Variable qui contient le Panel concerné par des modifications
	private String action;                                         //Variable qui contient le type d'action
  private boolean isPressing;                                    //Variable qui indique si l'utilisateur reste appuyé sur la souris(true) ou non(false)

	private Window mapCreatingWindow;                              //Fenêtre de création de labyrinthe

  //Constructeur
	public GridActionManagement(Window mapCreatingWindow){

		this.mapCreatingWindow = mapCreatingWindow;                  //Innitialisation de la fenêtre de création de labyrinthe
		this.action = "none";                                        //Innitialisation de l'action sur "aucune"
    this.isPressing = false;                                     //Innitialisation de si l'utilisateur reste appuyé sur "non"
	}

  //Méthode qui permet d'indiquer le type d'action
	public void setAction(String action){

		this.action = action;
	}

  //Souris cliquée
  public void mouseClicked(MouseEvent e){

  	this.currentPanel = (Panel)e.getSource();                    //Récupération du Panel concerné

  	if(this.action.equals("Put the start")){                     //Si l'action est de poser le départ

  		if(this.mapCreatingWindow.getPanelByType(2) != null){      //Si il à déjà été posé précédement

  			this.mapCreatingWindow.getPanelByType(2).setType(0);     //L'ancienne position est remise normale
  		}

  		this.currentPanel.setType(2);                              //Ce panel devient le départ
  	}

  	if(this.action.equals("Put the exit")){                      //Si l'action est de poser la sortie

  		if(this.mapCreatingWindow.getPanelByType(3) != null){      //Si elle à déjà été posée

  			this.mapCreatingWindow.getPanelByType(3).setType(0);     //L'ancienne position est remise normale
  		}

  		this.currentPanel.setType(3);                              //Ce panel devient la sortie
  	}
}

	//La souris entre
  public void mouseEntered(MouseEvent e) {

    this.currentPanel = (Panel)e.getSource();                    //Récupération du Panel concerné

    if(this.action.equals("Rubber") && this.isPressing == true){ //Si l'action est d'effacer que l'utilisateur reste appuyé

      this.currentPanel.setType(0);                              //Ce Panel devient vide
    }

    if(this.action.equals("Put a wall") && this.isPressing == true){ //Si l'action est de poser un mur et que l'utilisateur reste appuyé

      this.currentPanel.setType(1);                              //Ce Panel devient un mur
    }
  }

  //Souris pressée
  public void mousePressed(MouseEvent e){

      this.currentPanel = (Panel)e.getSource();                    //Récupération du Panel concerné

      if(this.action.equals("Rubber")){                            //Si l'action est d'effacer

        this.currentPanel.setType(0);                              //Ce panel devient vide
      }

      if(this.action.equals("Put a wall")){                        //Si l'action est de poser un mur

        this.currentPanel.setType(1);                              //Ce panel devient un mur
      }

      this.isPressing = true;                                      //Indiquation que l'utilisateur appuie
  }

  //Souris lachée
  public void mouseReleased(MouseEvent e){

    this.isPressing = false;                                       //Indiquation que l'utilisateur n'appuie plus
  }

  //La souris sort
  public void mouseExited(MouseEvent e) {}
}