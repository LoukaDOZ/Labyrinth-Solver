import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

	private JLabel[] labelArray;
	private Panel[] panelArray;
	private JTextArea textArea;
	private int lastOneJLabel;
	private int lastOnePanel;
	private boolean isMapCreatingWindowCharged;
	private MenuActionManagement menuActionManagement;
	private GridActionManagement gridActionManagement;
	private GridLayout gridLayout;

  public Window(String title, int sizeX, int sizeY, int locationX, int locationY, boolean foreground){

    super(title);

    this.lastOneJLabel = 0;
    this.lastOnePanel = 0;
    this.isMapCreatingWindowCharged = false;
    this.menuActionManagement = new MenuActionManagement();
    this.gridActionManagement = new GridActionManagement();
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

  	this.updateJLabelArray();
  	this.labelArray[this.lastOneJLabel - 1] = label;
 }

 public void addNewJTextArea(String text){

  	this.textArea = new JTextArea(text);
  	this.textArea.setFont(new Font("corps",1,this.getWidth() / 25));
  	this.textArea.addMouseListener(new JTextAreaManagement());

    this.setGridLayoutBigger();
  	this.setLayout(this.gridLayout);
    this.add(this.textArea,BorderLayout.CENTER);
  }

  public void addCreatingGrid(int gridSize, boolean isRandomFill){

  	this.isMapCreatingWindowCharged = true;

  	JPanel mapPanel = new JPanel();
  	mapPanel.setLayout(new GridLayout(gridSize,gridSize));

  	Color color = new Color(240,240,240);
  	int type = 0;

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

	  	if(isRandomFill == true){

	  		int random = (int)(Math.random() * 2);

	  		if(random == 1){

	  			color = new Color(0,0,0);
	  			type = 1;
	  		}
	  	}

  		Panel panel = new Panel(i,color,type);
  		panel.addMouseListener(this.gridActionManagement);
  		this.updatePanelArray();
  		this.panelArray[this.lastOnePanel - 1] = panel;
  		mapPanel.add(panel,BorderLayout.CENTER);
  	}

  	JLabel label1 = new JLabel("  Change the map :  ");
  	JLabel label2 = new JLabel("Rubber");
  	JLabel label3 = new JLabel("Put a wall");
  	JLabel label4 = new JLabel("Put the start");
  	JLabel label5 = new JLabel("Put the exit");
  	JLabel label6 = new JLabel("Save the map");
  	JLabel label7 = new JLabel("Done");

  	JPanel menuPanel = new JPanel();
  	menuPanel.setLayout(new GridLayout(7,1));

  	label1.setHorizontalAlignment(JLabel.CENTER);
  	label2.setHorizontalAlignment(JLabel.CENTER);
  	label3.setHorizontalAlignment(JLabel.CENTER);
  	label4.setHorizontalAlignment(JLabel.CENTER);
  	label5.setHorizontalAlignment(JLabel.CENTER);
  	label6.setHorizontalAlignment(JLabel.CENTER);
  	label7.setHorizontalAlignment(JLabel.CENTER);

  	label1.setOpaque(true);
  	label2.setOpaque(true);
  	label3.setOpaque(true);
  	label4.setOpaque(true);
  	label5.setOpaque(true);
  	label6.setOpaque(true);
  	label7.setOpaque(true);

  	label1.setForeground(new Color(255,255,255));
  	label2.setForeground(new Color(255,255,255));
  	label3.setForeground(new Color(255,255,255));
  	label4.setForeground(new Color(255,255,255));
  	label5.setForeground(new Color(255,255,255));
  	label6.setForeground(new Color(255,255,255));
  	label7.setForeground(new Color(255,255,255));

  	label1.setBackground(new Color(0,0,0));
  	label2.setBackground(new Color(0,0,0));
  	label3.setBackground(new Color(0,0,0));
  	label4.setBackground(new Color(0,0,0));
  	label5.setBackground(new Color(0,0,0));
  	label6.setBackground(new Color(0,0,0));
  	label7.setBackground(new Color(0,0,0));

  	label1.setFont(new Font("corps",1,this.getWidth() / 90));
  	label2.setFont(new Font("corps",1,this.getWidth() / 90));
  	label3.setFont(new Font("corps",1,this.getWidth() / 90));
  	label4.setFont(new Font("corps",1,this.getWidth() / 90));
  	label5.setFont(new Font("corps",1,this.getWidth() / 90));
  	label6.setFont(new Font("corps",1,this.getWidth() / 90));
  	label7.setFont(new Font("corps",1,this.getWidth() / 90));

  	label2.addMouseListener(this.menuActionManagement);
  	label3.addMouseListener(this.menuActionManagement);
  	label4.addMouseListener(this.menuActionManagement);
  	label5.addMouseListener(this.menuActionManagement);
  	label6.addMouseListener(this.menuActionManagement);
  	label7.addMouseListener(this.menuActionManagement);

  	this.updateJLabelArray();
  	this.labelArray[this.lastOneJLabel - 1] = label1;
  	this.updateJLabelArray();
  	this.labelArray[this.lastOneJLabel - 1] = label2;
  	this.updateJLabelArray();
  	this.labelArray[this.lastOneJLabel - 1] = label3;
  	this.updateJLabelArray();
  	this.labelArray[this.lastOneJLabel - 1] = label4;
  	this.updateJLabelArray();
  	this.labelArray[this.lastOneJLabel - 1] = label5;
  	this.updateJLabelArray();
  	this.labelArray[this.lastOneJLabel - 1] = label6;
  	this.updateJLabelArray();
  	this.labelArray[this.lastOneJLabel - 1] = label7;

  	menuPanel.add(label1,BorderLayout.CENTER);
  	menuPanel.add(label2,BorderLayout.CENTER);
  	menuPanel.add(label3,BorderLayout.CENTER);
  	menuPanel.add(label4,BorderLayout.CENTER);
  	menuPanel.add(label5,BorderLayout.CENTER);
  	menuPanel.add(label6,BorderLayout.CENTER);
  	menuPanel.add(label7,BorderLayout.CENTER);

  	this.add(mapPanel,BorderLayout.CENTER);
  	this.add(menuPanel,BorderLayout.WEST);
  }

  public void updateJLabelArray(){

  	this.lastOneJLabel++;

  	if(this.labelArray == null){

  		this.labelArray = new JLabel[1];
  	}else{

  		JLabel[] newArray = new JLabel[this.lastOneJLabel];

  		for(int i = 0; i < this.labelArray.length; i++){

  			newArray[i] = this.labelArray[i];
  		}

  		this.labelArray = newArray;
  	}
}

public void updatePanelArray(){

  	this.lastOnePanel++;

  	if(this.panelArray == null){

  		this.panelArray = new Panel[1];
  	}else{

  		Panel[] newArray = new Panel[this.lastOnePanel];

  		for(int i = 0; i < this.panelArray.length; i++){

  			newArray[i] = this.panelArray[i];
  		}

  		this.panelArray = newArray;
  	}
}

public JLabel getJLabelByText(String text){

  	int i;

  	for(i = 0; this.labelArray[i].getText().equals(text) == false && i < this.lastOneJLabel; i++){}

  	return this.labelArray[i];
}

public Panel getPanelByID(int id){

  	int i;

  	for(i = 0; this.panelArray[i].getID() == id && i < this.lastOnePanel; i++){}

  	return this.panelArray[i];
}

public Panel getPanelByType(int type){

  	int i;

  	try{

	  	for(i = 0; this.panelArray[i].getType() != type; i++){}

	  	return this.panelArray[i];
	}catch(IndexOutOfBoundsException ex){

		return null;
	}
}

	public JTextArea getJTextArea(){

		return this.textArea;
	}

	public MenuActionManagement getMenuActionManagement(){

		return this.menuActionManagement;
	}

	public GridActionManagement getGridActionManagement(){

		return this.gridActionManagement;
	}

	public boolean getIsMapCreatingWindowCharged(){

		return this.isMapCreatingWindowCharged;
	}
}