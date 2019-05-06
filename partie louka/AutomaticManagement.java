import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AutomaticManagement implements ActionListener{

	private AutomaticSimulation simulation;
	private Timer timer;

	public AutomaticManagement(AutomaticSimulation simulation){

		super();

		this.simulation = simulation;
		this.timer = new Timer(10,this);
		this.timer.start();
	}

	public void actionPerformed(ActionEvent e){

		this.timer.stop();
		this.simulation.move();
		this.timer.start();
	}
}