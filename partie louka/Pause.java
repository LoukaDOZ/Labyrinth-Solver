import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pause implements ActionListener{

	private ManualSimulation manualSimulation;
	private AutomaticSimulation automaticSimulation;
	private Timer timer;

	public Pause(ManualSimulation manualSimulation){

		super();

		this.manualSimulation = manualSimulation;
		this.timer = new Timer(10,this);
		this.timer.start();
	}

	public Pause(AutomaticSimulation automaticSimulation){

		super();

		this.automaticSimulation = automaticSimulation;
		this.timer = new Timer(10,this);
		this.timer.start();
	}

	public void actionPerformed(ActionEvent e){

		this.timer.stop();

		if(this.automaticSimulation == null){

			this.manualSimulation.startSimulation();
		}else{

			this.automaticSimulation.startSimulation();
		}
	}
}