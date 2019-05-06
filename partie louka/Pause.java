import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pause implements ActionListener{

	private AutomaticSimulation simulation;
	private Timer timer;

	public Pause(AutomaticSimulation simulation){

		super();

		this.simulation = simulation;
		this.timer = new Timer(10,this);
		this.timer.start();
	}

	public void actionPerformed(ActionEvent e){

		this.timer.stop();
		this.simulation.startSimulation();
	}
}