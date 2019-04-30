import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel{

	private int id;
	private int type;
	private Color basicColor;
	private GridActionManagement gridActionManagement;

	private JLabel label;
	private int containerHeight;

	public Panel(){

		super();
	}

	public Panel(int containerHeight){

		super();

		this.containerHeight = containerHeight;
	}

	public Panel(GridActionManagement gridActionManagement, int containerHeight){

		super();

		this.gridActionManagement = gridActionManagement;
		this.containerHeight = containerHeight;
	}

	public Panel(int id, Color color, int containerHeight){

		super();

		this.containerHeight = containerHeight;

		this.id = id;
		this.type = 0;
		this.basicColor = color;
		this.setOpaque(true);
		this.setBackground(this.basicColor);

		this.label = new JLabel();
		JLabel voidLabel = new JLabel();
		this.add(this.label,BorderLayout.CENTER);
	}

	public void setType(int type, int gridSize){

		this.removeImage();
		this.type = type;

		if(type == 0){

			this.setBackground(this.basicColor);
		}

		if(type == 1){

			this.setBackground(new Color(this.basicColor.getRed() - 240,this.basicColor.getRed() - 240,this.basicColor.getRed() - 240));
		}

		if(type == 2){

			this.setBackground(this.basicColor);
			this.setImage("Image/player.png",gridSize);
			this.setBackground(new Color(220,220,255));
		}

		if(type == 3){

			this.setBackground(this.basicColor);
			this.setImage("Image/exit.png",gridSize);
			this.setBackground(new Color(220,255,220));
		}
	}

	public void setImage(String source, int gridSize){

		int size = (int)((this.containerHeight / gridSize) * 0.8);

		this.label.setIcon(new ImageIcon(new ImageIcon(source).getImage().getScaledInstance(size,size,Image.SCALE_DEFAULT)));
	}

	public void removeImage(){

		this.label.setIcon(null);
	}

	public void setCreatingGrid(Window window, int containerHeight, int gridSize, boolean isRandomFill){

	    this.setLayout(new GridLayout(gridSize,gridSize));

	    Color color = new Color(240,240,240);

	    for(int i = 0; i < gridSize * gridSize; i++){

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

	      Panel panel = new Panel(i,color,containerHeight);

	      if(isRandomFill == true){

	        int random = (int)(Math.random() * 2);

	        if(random == 1){

	          panel.setType(1,window.getMenuActionManagement().getGridSize());
	        }
	      }

	      panel.addMouseListener(this.gridActionManagement);
	      window.updatePanelArray(panel);
	      this.add(panel,BorderLayout.CENTER);
	    }
  	}

	public int getType(){

		return this.type;
	}

	public int getID(){

		return this.id;
	}

	public JLabel getJLabel(){

		return this.label;
	}
}