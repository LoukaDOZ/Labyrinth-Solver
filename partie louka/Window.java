import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

	private JLabel[] labelArray;
	private Panel[] panelArray;
	private JTextArea[] textAreaArray;
	private MenuActionManagement menuActionManagement;
  private SaveActionManagement saveActionManagement;
  private GridActionManagement gridActionManagement;
	private GridLayout gridLayout;

  public Window(String title, int sizeX, int sizeY, int locationX, int locationY, boolean foreground){

    super(title);

    this.gridLayout = new GridLayout(0,1);
    
    this.setMinimumSize(new Dimension(sizeX,sizeY));
    this.setResizable(false);
    this.setLocation(locationX,locationY);
    this.setAlwaysOnTop(foreground);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
  }

  public void setManagement(MenuActionManagement menuActionManagement){

    this.menuActionManagement = menuActionManagement;
  }

  public void setManagement(SaveActionManagement saveActionManagement){

    this.saveActionManagement = saveActionManagement;
  }

  public void setManagement(GridActionManagement gridActionManagement){

    this.gridActionManagement = gridActionManagement;
  }

  public void setGridLayout(int rows, int columns){

  	this.gridLayout = new GridLayout(rows,columns);
    this.setLayout(this.gridLayout);
  }

  public JLabel getNewJLabel(String text, String whitchManagement, int fontSize){

  	JLabel label = new JLabel(text);

  	label.setHorizontalAlignment(JLabel.CENTER);
  	label.setOpaque(true);
  	label.setForeground(new Color(255,255,255));
  	label.setBackground(new Color(0,0,0));
  	
    if(fontSize == 1){

      label.setFont(new Font("corps",1,this.getWidth() / 80));
    }

    if(fontSize == 2){

      label.setFont(new Font("corps",1,this.getWidth() / 25));
    }

  	if(whitchManagement.equals("MenuActionManagement")){

      label.addMouseListener(this.menuActionManagement);
    }

    if(whitchManagement.equals("SaveActionManagement")){

      label.addMouseListener(this.saveActionManagement);
    }

  	this.updateJLabelArray(label);

  	return label;
  }

  public JLabel getNewJLabel(String text, int fontSize){

    JLabel label = new JLabel(text);

    label.setHorizontalAlignment(JLabel.CENTER);
    label.setOpaque(true);
    label.setForeground(new Color(255,255,255));
    label.setBackground(new Color(0,0,0));

    if(fontSize == 1){

      label.setFont(new Font("corps",1,this.getWidth() / 80));
    }

    if(fontSize == 2){

      label.setFont(new Font("corps",1,this.getWidth() / 25));
    }

    this.updateJLabelArray(label);

    return label;
  }

  public void updateJLabelArray(JLabel label){

    if(this.labelArray == null){

      this.labelArray = new JLabel[1];
      this.labelArray[this.labelArray.length - 1] = label;
    }else{

      JLabel[] newArray = new JLabel[this.labelArray.length + 1];

      for(int i = 0; i < this.labelArray.length; i++){

        newArray[i] = this.labelArray[i];
      }

      newArray[newArray.length - 1] = label;
      this.labelArray = new JLabel[newArray.length];
      this.labelArray = newArray;
    }
  }

  public JTextArea getNewJTextArea(String text){

    JTextArea textArea = new JTextArea(text);

    textArea.setFont(new Font("corps",1,this.getWidth() / 25));
    textArea.addMouseListener(new JTextAreaManagement());

    this.updateJTextAreaArray(textArea);

    return textArea;
  }

  public void updateJTextAreaArray(JTextArea textArea){

    if(this.textAreaArray == null){

      this.textAreaArray = new JTextArea[1];
      this.textAreaArray[this.textAreaArray.length - 1] = textArea;
    }else{

      JTextArea[] newArray = new JTextArea[this.textAreaArray.length + 1];

      for(int i = 0; i < this.textAreaArray.length; i++){

        newArray[i] = this.textAreaArray[i];
      }

      newArray[newArray.length - 1] = textArea;
      this.textAreaArray = new JTextArea[newArray.length];
      this.textAreaArray = newArray;
    }
  }

  public void updatePanelArray(Panel panel){

    if(this.panelArray == null){

      this.panelArray = new Panel[1];
      this.panelArray[this.panelArray.length - 1] = panel;
    }else{

      Panel[] newArray = new Panel[this.panelArray.length + 1];

      for(int i = 0; i < this.panelArray.length; i++){

        newArray[i] = this.panelArray[i];
      }

      newArray[newArray.length - 1] = panel;
      this.panelArray = new Panel[newArray.length];
      this.panelArray = newArray;
    }
  }

  public JLabel getJLabelByText(String text){

    int i;
    for(i = 0; this.labelArray[i].getText().equals(text) == false && i < this.labelArray.length; i++){}

    return this.labelArray[i];
  }

  public Panel getPanelByID(int id){

      int i;
      for(i = 0; this.panelArray[i].getID() != id && i < this.panelArray.length; i++){}

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

  public int getTotalPanel(){

    return this.panelArray.length;
  }

  //Return an array of all case type from the grid, the initial grid was made rows by rows, this array contain types columns by columns
  public int[] getPanelTypeAsAnArray(int gridSize){

    int[] typeArray = new int[gridSize * gridSize];
    int[][] convertArray = new int[gridSize][gridSize];

    for(int i = 0; i < gridSize; i++){

      for(int j = 0; j < gridSize; j++){

        convertArray[j][i] = this.panelArray[j + (i * gridSize)].getType();
      }
    }

    for(int i = 0; i < gridSize; i++){

      for(int j = 0; j < gridSize; j++){

        typeArray[j + (i * gridSize)] = convertArray[i][j];
      }
    }

    return typeArray;
  }

  public JTextArea getJTextAreaByArrivedOrder(int order){

    return this.textAreaArray[order - 1];
  }

  public MenuActionManagement getMenuActionManagement(){

    return this.menuActionManagement;
  }

  public SaveActionManagement getSaveActionManagement(){

    return this.saveActionManagement;
  }

  public GridActionManagement getGridActionManagement(){

    return this.gridActionManagement;
  }
}