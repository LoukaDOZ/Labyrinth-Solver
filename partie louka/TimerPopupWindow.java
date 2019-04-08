import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerPopupWindow implements ActionListener{

	private Timer timer;
	private Window window;

	public TimerPopupWindow(Window window){

		this.window = window;
		this.timer = new Timer(3000,this);
		this.timer.start();
	}

	public void actionPerformed(ActionEvent e){

		this.window.setVisible(false);
		this.timer.stop();
	}
}