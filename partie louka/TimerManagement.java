import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerManagement implements ActionListener{

	private String type;
	private Window window;
	private Timer timer;
	private ManualSimulation manualSimulation;
	private AutomaticSimulation automaticSimulation;

	public TimerManagement(Window window,Timer timer){

		this.window = window;
		this.type = "close window";
		this.timer = timer;

		this.timer.start();
	}

	public TimerManagement(AutomaticSimulation automaticSimulation,Timer timer,String pauseType){

		this.automaticSimulation = automaticSimulation;
		this.type = pauseType;
		this.timer = timer;

		this.timer.start();
	}

	public TimerManagement(ManualSimulation manualSimulation,Timer timer,String pauseType){

		this.manualSimulation = manualSimulation;
		this.type = pauseType;
		this.timer = timer;

		this.timer.start();
	}

	public void actionPerformed(ActionEvent e){

		if(this.type.equals("close window")){

			this.window.setVisible(false);
			this.timer.stop();
		}

		if(this.type.equals("next round")){

			this.automaticSimulation.nextRound();
			this.timer.stop();
		}

		if(this.type.equals("start simulation A")){

			this.automaticSimulation.startSimulation();
			this.timer.stop();
		}

		if(this.type.equals("start simulation M")){

			this.manualSimulation.startSimulation();
			this.timer.stop();
		}
	}
}