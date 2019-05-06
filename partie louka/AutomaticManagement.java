import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AutomaticManagement implements ActionListener{

	private Simulation simulation;
	private Timer timer;

	public AutomaticManagement(Simulation simulation){

		super();

		this.simulation = simulation;
		this.timer = new Timer(50,this);
		this.timer.start();
	}

	public void actionPerformed(ActionEvent e){

		this.timer.stop();
		this.simulation.move();
		this.timer.start();
	}
}