package Snake;
import javax.swing.JFrame;

public class SnakeFrame extends JFrame{

	/*
	 * Constructor for a Frame
	 * add(Panel) 	add a Panel to the Frame
	 * setTitle(String) 	set title of the new Frame
	 * setDefaultCloseOperation() 	set close action when hit the close button
	 * setResizable(boolean) 	should the Frame be resizable or not? 
	 * pack ()  packs the components within the window based on the component's preferred sizes.
	 * setVisible(boolean); 	the Frame is invisible by default so we need to set it true
	 * setLocationRelativeTo(null) determine the position of the frame to the middle of the screen 
	 */
	SnakeFrame(){
		this.add(new SnakePanel()); 
		this.setTitle("Snake"); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null); 
	}
	
}
