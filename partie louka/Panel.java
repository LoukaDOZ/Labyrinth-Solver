import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel{

	private int id;
	private int type;
	private Color currentColor;
	private Color basicColor;
	private ImageIcon currentImage;
	private GridActionManagement gridActionManagement;

	public Panel(){

		super();
	}

	public Panel(GridActionManagement gridActionManagement){

		super();

		this.gridActionManagement = gridActionManagement;
	}

	public Panel(int id,Color color){

		super();

		this.id = id;
		this.type = 0;
		this.basicColor = color;
		this.currentColor = this.basicColor;
		this.currentImage = null;
		this.setOpaque(true);
	}

	public void paintComponent(Graphics paintbrush) {

	    Graphics secondPaintbrush = paintbrush.create();

	    secondPaintbrush.setColor(this.currentColor);
	    secondPaintbrush.fillRect(0,0,this.getWidth(),this.getHeight());

	    if(this.currentImage != null){

	    	int sizeX = (int)(this.getWidth() * 0.9);
	    	int sizeY = (int)(this.getHeight() * 0.9);
	    	int positionX = (int)(this.getHeight() * 0.05);
	    	int positionY =(int)(this.getHeight() * 0.05);

	    	this.currentImage = new ImageIcon(this.currentImage.getImage().getScaledInstance(sizeX,sizeY,Image.SCALE_DEFAULT));
	    	this.currentImage.paintIcon(this,secondPaintbrush,positionX,positionY);
	    }
	  }

	  public void setType(int type){

		this.type = type;

		if(this.type == 0){

	    	this.currentColor = this.basicColor;
	    	this.currentImage = null;
	    }

		if(this.type == 1){

	    	this.currentColor = new Color(this.basicColor.getRed() - 240,this.basicColor.getRed() - 240,this.basicColor.getRed() - 240);
	    	this.currentImage = null;
	    }

	    if(this.type == 2){

	    	this.currentColor = new Color(0,255,0);
	    	this.currentImage = new ImageIcon("Images/player.png");
	    }

	    if(this.type == 3){

	    	this.currentColor = new Color(255,0,0);
	    	this.currentImage = new ImageIcon("Images/exit.png");
	    }

		this.repaint();
	}

	public void setColor(Color color){

		this.currentColor = color;
		repaint();
	}

	public void setNewGrid(Window window,int gridSize,boolean isRandomFill){

	    this.setLayout(new GridLayout(gridSize,gridSize));

	    Color color = new Color(240,240,240);
	    int random;
	    int i;

	    for(i = 0; i < gridSize * gridSize; i++){

	      if((gridSize % 2) == 0){

	        if(((i + (i / gridSize)) % 2) == 0){

	          color = new Color(240,240,240);
	        }else{

	          color = new Color(255,255,255);
	        }
	      }else{

	        if((i % 2) == 0){

	          color = new Color(240,240,240);
	        }else{

	          color = new Color(255,255,255);
	        }
	      }

	      Panel panel = new Panel(i,color);

	      if(isRandomFill == true){

	        random = (int)(Math.random() * 2);

	        if(random == 1){

	          panel.setType(1);
	        }
	      }

	      panel.addMouseListener(this.gridActionManagement);
	      window.updatePanelArray(panel);
	      this.add(panel,BorderLayout.CENTER);
	    }

	    if(isRandomFill == true){

	    	for(i = 2; i < 4; i++){

	    		random = (int)(Math.random() * (gridSize * gridSize));

	    		window.getPanelByID(random).setType(i);
	    	}
	    }
  	}

	public int getType(){

		return this.type;
	}

	public int getID(){

		return this.id;
	}
}