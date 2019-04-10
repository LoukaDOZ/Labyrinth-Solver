import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel{

	private int id;
	private int type;
	private Color basicColor;
	private GridActionManagement gridActionManagement;

	private JLabel label;

	public Panel(){}

	public Panel(GridActionManagement gridActionManagement){

		this.gridActionManagement = gridActionManagement;
	}

	public Panel(int id, Color color){

		this.id = id;
		this.type = 0;
		this.basicColor = color;
		this.setOpaque(true);
		this.setBackground(this.basicColor);

		this.label = new JLabel();
		JLabel voidLabel = new JLabel();
		this.add(this.label,BorderLayout.CENTER);
	}

	public void setType(int type){

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
			this.setImage("Image/player-south.png");
			this.setBackground(new Color(220,220,255));
		}

		if(type == 3){

			this.setBackground(this.basicColor);
			this.setImage("Image/exit.png");
			this.setBackground(new Color(220,255,220));
		}
	}

	public void setImage(String source){

		int size = (int)(this.getHeight() * 0.80);

		this.label.setIcon(new ImageIcon(new ImageIcon(source).getImage().getScaledInstance(size,size,Image.SCALE_DEFAULT)));
	}

	public void removeImage(){

		this.label.setIcon(null);
	}

	public void setCreatingGrid(Window window, int gridSize, boolean isRandomFill){

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

      Panel panel = new Panel(i,color);

      if(isRandomFill == true){

        int random = (int)(Math.random() * 2);

        if(random == 1){

          panel.setType(1);
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
}