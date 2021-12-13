package Snake;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Snake.SnakePanel.DIFFICULTY;
import Snake.SnakePanel.STATE;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Getting the mouse location
		int mouseX = e.getX(); 
		int mouseY = e.getY(); 

		
		if(mouseX >= SnakePanel.SCREEN_WIDTH/2 -75 && mouseX <= SnakePanel.SCREEN_WIDTH/2 +225 ) {
			if(mouseY >= 150 && mouseY <= 200) {
				//Pressed EASY
				SnakePanel.difficulty = DIFFICULTY.EASY;
				SnakePanel.state = STATE.GAME; 
			}else if(mouseY >= 250 && mouseY <= 300) {
				//Pressed MEDIUM Button
				SnakePanel.difficulty = DIFFICULTY.MEDIUM;
				SnakePanel.state = STATE.GAME; 
			}else if(mouseY >= 350 && mouseY <= 400) {
				//Pressed HARD Button
				SnakePanel.difficulty = DIFFICULTY.HARD;
				SnakePanel.state = STATE.GAME; 
			}else if(mouseY >= 450 && mouseY <= 500) {
				//Pressed QUIT Button
				System.exit(1); 
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
