import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

	private JLabel[] labelArray;
	private JTextArea textArea;
	private int lastOne;
	private MenuActionManagement menuActionManagement;
	private GridLayout gridLayout;

  public Window(String title, int sizeX, int sizeY, int locationX, int locationY, boolean foreground){

    super(title);

    this.lastOne = 0;
    this.menuActionManagement = new MenuActionManagement();
    this.gridLayout = new GridLayout(0,1);
    
    this.setMinimumSize(new Dimension(sizeX,sizeY));
    this.setResizable(false);
    this.setLocation(locationX,locationY);
    this.setAlwaysOnTop(foreground);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
  }

  public void setGridLayoutBigger(){

  	int r = this.gridLayout.getRows();
  	this.gridLayout.setRows(r + 1);
  }

  public void addNewMenuJLabel(String text, boolean isTitle){

  	JLabel label = new JLabel(text);

  	label.setHorizontalAlignment(JLabel.CENTER);
  	label.setOpaque(true);
  	label.setForeground(new Color(255,255,255));
  	label.setBackground(new Color(0,0,0));
  	label.setFont(new Font("corps",1,this.getWidth() / 25));

  	if(isTitle == false){

  		label.addMouseListener(this.menuActionManagement);
  	}

  	this.setGridLayoutBigger();
  	this.setLayout(this.gridLayout);
  	this.add(label,BorderLayout.CENTER);

  	this.updateArray();
  	this.labelArray[this.lastOne - 1] = label;
 }

 public void addNewJTextArea(String text){

  	this.textArea = new JTextArea(text);
  	this.textArea.setFont(new Font("corps",1,this.getWidth() / 25));

    this.setGridLayoutBigger();
  	this.setLayout(this.gridLayout);
    this.add(this.textArea,BorderLayout.CENTER);
  }

  public void addGrid(){

  	int gridSize = this.menuActionManagement.getGridSize();
  	this.setLayout(new GridLayout(gridSize,gridSize));

  	for(int i = 0; i < gridSize * gridSize; i++){

  		JLabel label = new JLabel();
  		label.setOpaque(true);
  		this.add(label,BorderLayout.CENTER);
  	}
  }

  public void updateArray(){

  	this.lastOne++;

  	if(this.labelArray == null){

  		this.labelArray = new JLabel[1];
  	}else{

  		JLabel[] newArray = new JLabel[this.lastOne];

  		for(int i = 0; i < this.labelArray.length; i++){

  			newArray[i] = this.labelArray[i];
  		}

  		this.labelArray = newArray;
  	}
}

  public JLabel getJLabelByText(String text){

  	int i;

  	try{

  		for(i = 0; this.labelArray[i].getText().equals(text) == false && i < this.lastOne; i++){}
  	}catch(IndexOutOfBoundsException ex){

  		return null;
  	}

  		return this.labelArray[i];
	}

	public JTextArea getJTextArea(){

		return this.textArea;
	}

	public MenuActionManagement getMenuActionManagement(){

		return this.menuActionManagement;
	}
}