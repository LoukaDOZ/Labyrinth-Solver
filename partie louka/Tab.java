import java.io.*;

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

			this.size = entree.read();
			//System.out.println(this.size);
			this.xbegin = entree.read();
			//System.out.println(this.xbegin);
			this.ybegin = entree.read();
			//System.out.println(this.ybegin);
			this.xend = entree.read();
			//System.out.println(this.xend);
			this.yend = entree.read();
			//System.out.println(this.yend);

			this.map = new short[size*size];
			short[] tmp = new short[8];

			for(int step1 = 0; step1 < size/2; step1++){			/* Permet de lires les positions des cases noires et blanches */
				test = entree.read();
				for(int step2 = 0;step2<8;step2++){
					if((test & 0x80) == 0){
						tmp[step2] = 0;
					}else{
						tmp[step2] = 1;
					}
					test = test << 1;
					this.map[compteur] = tmp[step2];
					compteur++;
				}
			}

			endBegin();

			for(int i = 0; i < this.size*this.size; i++)//System.out.print(this.map[i]);
		entree.close();		

		} catch (FileNotFoundException ev){
		            ev.printStackTrace();
		} catch (IOException ev) {
		        ev.printStackTrace();
		}  
	}



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


			for(int step3 = 0; step3 < sizeTab*sizeTab; step3++){	/* Ecriture du tableau */
				tmp = tmp + Integer.toString(array[step3]);
				if(step3%8==0){
					sortie.writeByte(Integer.parseInt(tmp));
					tmp = "";
				}
			}
			if(size*size%2!=0)sortie.writeByte(Integer.parseInt(tmp));


			sortie.close();

		} catch (FileNotFoundException ev){
		            ev.printStackTrace();
		} catch (IOException ev) {
		        ev.printStackTrace();
		}  
	}




	public void endBegin(){

		this.posb = (this.getSize()*this.getYbegin())+this.getXbegin();
		this.pose = (this.getSize()*this.getYend())+this.getXend();
		this.map[posb] = 2; 				/* Placement de l'entrée */
		this.map[pose] = 3; 				/* Placement de la sortie */
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