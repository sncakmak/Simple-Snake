package Snake;

import java.awt.*;
import javax.swing.*;



public class Menu extends JPanel{
	
	public Rectangle easyButton = new Rectangle(SnakePanel.SCREEN_WIDTH/2 -75, 150, 150, 50); 
	public Rectangle mediumButton = new Rectangle(SnakePanel.SCREEN_WIDTH/2 -75, 250, 150, 50); 
	public Rectangle hardButton = new Rectangle(SnakePanel.SCREEN_WIDTH/2 -75, 350, 150, 50); 
	public Rectangle quitButton = new Rectangle(SnakePanel.SCREEN_WIDTH/2 -75, 450, 150, 50); 


	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; 
		
		Font fnt1 = new Font("Ubuntu", Font.BOLD, 50); 
		g.setFont(fnt1);
		g.setColor(Color.white);
		FontMetrics metrics = getFontMetrics(g.getFont()); 
		g.drawString("Snake", (SnakePanel.SCREEN_WIDTH - metrics.stringWidth("Snake"))/2, 100);
		
		
		//Buttons
		Font fnt2 = new Font("Ubuntu", Font.BOLD, 30); 
		g.setFont(fnt2);
		g.drawString("Easy", easyButton.x + 20, easyButton.y + 35);
		g.drawString("Medium", mediumButton.x + 20, mediumButton.y + 35);
		g.drawString("Hard", hardButton.x + 20, hardButton.y + 35);
		g.drawString("Quit", quitButton.x + 20, quitButton.y + 35);

		g2d.draw(easyButton);
		g2d.draw(mediumButton);
		g2d.draw(hardButton);
		g2d.draw(quitButton);
	}
	
}