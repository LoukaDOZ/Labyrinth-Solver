import java.io.*;

  /**
   * Cette classe permet de lire un fichier, puis de stocker les valeurs dans les attributs : map[], size, xbegin, ybegin, xend, yend
   	 Elle permet aussi de Sauvegarder les grilles crées dans l'éditeur de grille.
   */


public class Tab{


	private int size = 0;
	private int xbegin = 0;
	private int ybegin = 0;
	private int xend = 0;
	private int yend = 0;
	private int test = 0, compteur = 0;
	private short[] map;
	private int pose;
	private int posb;



/* Va chercher les informations dans le fichier fileName */

	public void loadMap(File fileName){

		try{
			FileInputStream fichier = new FileInputStream(fileName);
			DataInputStream entree = new DataInputStream(fichier);

			this.size = entree.read();					/* Correspond dans l'ordre aux caractéristique : taille de la grille, coordonnée de l'entrée, coordonnée de la sorie */
			this.xbegin = entree.read();
			this.ybegin = entree.read();
			this.xend = entree.read();
			this.yend = entree.read();

			this.map = new short[size*size];
			short[] tmp = new short[8];

			for(int step1 = 0; fichier.available() > 0; step1++){						/* Lit octet par octet jusqu'à la fin du fichier */		
				test = entree.read();
				for(int step2 = 0;step2<8 && compteur < size*size;step2++){			/* Permet de récupéré les bits de l'octet 1 par  */
					if((test & 0x80) == 0){											/* On compara sa valeur avec 0x80 pour savoir si le bit est égale à 0 ou 1 */
						tmp[step2] = 0;
					}else{
						tmp[step2] = 1;
					}
					test = test << 1;	/* On décale jusqu'au prochain bit */
					this.map[compteur] = tmp[step2];			/* Range les bits 1 par 1 dans le tableau map correspondant à la grille finale */		
					compteur++;
				}
			} 

			endBegin();	
			entree.close();		

		} catch (FileNotFoundException ev){
		            ev.printStackTrace();
		} catch (IOException ev) {
		        ev.printStackTrace();
		}  
	}



  /**
   * Méthode permettant de sauvegarder dans un fichier les données renvoyées par l'éditeur de grille
   */

	public void saveMap(File fileName, int[] array, int sizeTab){

		try{

			int posxBegin = 0;
			int posyBegin = 0;

			int posxEnd = 0;
			int posyEnd = 0;

			String tmp = "";

			FileOutputStream fichier = new FileOutputStream(fileName);
			DataOutputStream sortie = new DataOutputStream(fichier);

			sortie.writeByte(sizeTab);

			for(int i = 0; i < sizeTab*sizeTab; i++){				/* Coordonnée de l'entrée */
				if(i%sizeTab == 0){
					posyBegin++;
					posxBegin = 0;
				}
				posxBegin++;
				if(array[i] == 2){
					array[i] = 0;
					sortie.writeByte(posxBegin-1);
					sortie.writeByte(posyBegin-1);
				}
			}

			for(int i = 0; i < sizeTab*sizeTab; i++){				/*Coordonnée de la sortie */
				if(i%sizeTab == 0){
					posyEnd++;
					posxEnd = 0;
				}
				posxEnd++;
				if(array[i] == 3){
					array[i] = 0;
					sortie.writeByte(posxEnd-1);
					sortie.writeByte(posyEnd-1);
				}
			}


			for(int step3 = 1; step3 <= sizeTab*sizeTab; step3++){			/* Ecriture du tableau */
				tmp = tmp + Integer.toString(array[step3-1]);				/* Récupére les bits 1 par 1 pour les concaténer ensemble */
				if(step3%8==0 && step3!=0){
					sortie.writeByte(Integer.parseInt(tmp,2));				/* Convertit la chaine de caractére tmp en Int */
					tmp = "";												/* vide la variable tmp pour pouvoir la remplir de nouvelle valeur */
				}
			}
			if(tmp.length()!=0){											/* Si tmp n'est pas vide à la fin, alors on écrit les bits qui restent dans le fichier */
				sortie.writeByte(Integer.parseInt(tmp,2));
			}
			sortie.close();

		} catch (FileNotFoundException ev){
		            ev.printStackTrace();
		} catch (IOException ev) {
		        ev.printStackTrace();
		}  
	}



  /**
   * Permet de placer la sortie et l'entrée du labyrinthe dans le tableau map 
   */
	public void endBegin(){

		this.posb = (this.getSize()*this.getYbegin())+this.getXbegin();
		this.pose = (this.getSize()*this.getYend())+this.getXend();
		this.map[posb] = 2; 				/* Placement de l'entrée, ici elle aura la valeur 2 */ 
		this.map[pose] = 3; 				/* Placement de la sortie ici elle aura la valeur 3 */
		
	}

/* -------------------- Récupération des informations ----------------------- */


	public int getSize(){
		return size;
	}


	public int getXbegin(){
		return xbegin;
	}


	public int getYbegin(){
		return ybegin;
	}


	public int getXend(){
		return xend;
	}


	public int getYend(){
		return yend;
	}

	public short getMap(int step){
		return this.map[step];
	}
/*-------------------------------------------------------------------------------*/

}
