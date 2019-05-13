import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**Cette classe gère les JTextArea*/
public class JTextAreaManagement implements MouseListener{

	private JTextArea currentTextArea;							//Variable qui contient le JTextArea concerné par des modifications
	private Window window;										//Fenêtre contenant le JTextArea concerné

	/**Constructeur
	@param window Variable indiquant la fenêtre concernée*/
	public JTextAreaManagement(Window window){

		this.window = window;									//Innitialisation de la fenêtre
	}

	/**Souris cliquée
  	Méthode utilisée pour supprimer tout le texte du JTextArea quand une souris clique dessus*/
	public void mouseClicked(MouseEvent e){

		this.currentTextArea = (JTextArea)e.getSource();       //Récupération du JTextArea concerné

		this.currentTextArea.setText("");						//Le texte affiché est retiré
	}

	/**La souris sort
  	Méthode utilisée pour vérifier que la valeur entrée par l'utilisateur est conforme*/
	public void mouseExited(MouseEvent e) {

		this.currentTextArea = (JTextArea)e.getSource();       //Récupération du JTextArea concerné

		if(this.currentTextArea == this.window.getJTextAreaByOrderOfArrival(1) && this.window.getTitle().equals("Choose an algorithm")){ //Si c'est le premier JTextArea (dans l'ordre d'arrivée) de la fenêtre et que cette fenêtre est algorithmWindow

			try{												//Test de convertion du texte du JTextArea en entier

				int test = Integer.parseInt(this.currentTextArea.getText()); //Convertion du texte en entier

				if(test < 2){									//Si cet entier est inférieur à 2

					this.currentTextArea.setText("2");			//Le texte affiche 2 (car en dessous de 2 tours, il n'y à pas d'interêt)
				}

				if(test > 10000){								//Si cet entier est supérieur à 10000

					this.currentTextArea.setText("10000");		//Le texte affiche 10000 (car au dessus de 10000 tours, la simulation est trop longue)
				}
			}catch(NumberFormatException ex){					//Si le texte n'est pas un entier

				this.currentTextArea.setText("5000");			//Le texte affiche 5000
			}
		}
		
	}

	/**La souris entre (non utilisée)*/
	public void mouseEntered(MouseEvent e) {}

	/**Souris pressée (non utilisée)*/
	public void mousePressed(MouseEvent e){}

	/**Souris lachée (non utilisée)*/
	public void mouseReleased(MouseEvent e){}
}