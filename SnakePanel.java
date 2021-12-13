package Snake;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;


/*
 * @extends JPanel 		to add a Panel to the Frame
 * @implements ActionListener	add Actions to the Panel 
 */
public class SnakePanel extends JPanel implements ActionListener{
	
	
	// MAIN METHOD 
	public static void main(String[] args) {
		SnakeFrame frame = new SnakeFrame(); 
	}
	
	/* 
	 * SCREEN_WIDTH 	width of the Game Panel 
	 * SCREEN_HEIGHT 	height of the Game Panel
	 * UNIT_SIZE		Size of the Objects in the Panel
	 * GAME_UNITS		How many Objects fit on the Screen 
	 * DELAY 			Delay of the game (the higher the value, the slower the game) 
	 */
	static final int SCREEN_WIDTH = 700;
	static final int SCREEN_HEIGHT = 700;
	static final int UNIT_SIZE = 10; 
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE; 
	int DELAY = 75; 
	
	/*
	 * Matrix that represents all the body parts of the Snake
	 * x [] 			x-coordinates
	 * y []				y-coordinates
	 * bodyParts  		the amount of body Parts the snake is starting with
	 * appleseaten 		how many apples did the snake eat (initially 0) 
	 * appleX, appleY  	the x and y location of the apple (appears randomly) 
	 * direction 		the direction, the snake is going (initially right) 
	 * 					'R' = right , 'L' = left , 'U' = up , 'D' = down
	 * running 			does the snake move? (initiallsy false) 
	 */
	final int x[] = new int[GAME_UNITS]; 
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6; 
	int appleseaten = 0; 
	int appleX;
	int appleY; 
	char direction = 'R'; 
	boolean running = false; 
	Timer timer;
	Random random; 
	Menu menu = new Menu(); 
	
	
	public static enum DIFFICULTY{
		EASY, MEDIUM, HARD;
	}
	
	public static enum STATE{
		GAME, MENU; 
	};
	
	public static STATE state = STATE.MENU; 
	public static DIFFICULTY difficulty = DIFFICULTY.EASY;
	
	// Quit Button for Game Over method
	public Rectangle quitButton2 = new Rectangle(SnakePanel.SCREEN_WIDTH/2 -75, 450, 150, 50); 

	/*
	 * Constructor for a Frame
	 * random 			new random Value (for the location of the apple) 
	 */
	SnakePanel(){
		random = new Random(); 
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new SnakeKeyAdapter());
		this.addMouseListener(new MouseInput());
		startGame(); 
	}
	
	
	public void startGame() {
		newApple(); 			// create a new apple on the Panel 
		running = true; 		// let the snake move
		timer = new Timer(DELAY, this); 
		timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g); 
	}
	
	public void draw(Graphics g) {
		
		/*
		 * Set the speed of the Snake for each difficulty
		 */
		if(difficulty == DIFFICULTY.EASY) {
			timer.setDelay(100);
		}else if(difficulty == DIFFICULTY.MEDIUM) {
			timer.setDelay(70);
		}else if(difficulty == DIFFICULTY.HARD) {
			timer.setDelay(30);
		}
		
		
		if(state == STATE.GAME) {
			if(running) {
				
				// Draw the apple
				g.setColor(Color.red);
				g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
				
				//Draw the snake
				for(int i = 0; i < bodyParts ; i++) {
					// the head
					if(i == 0) {
						g.setColor(Color.GREEN);
						g.fillRect(x[0], y[0], UNIT_SIZE, UNIT_SIZE);
					// the rest of the body
					}else{
						g.setColor(Color.green);
						g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
					}
				}
				
				// Show Score
				g.setColor(Color.white);
				g.setFont(new Font("Ubuntu", Font.BOLD, 20));
				FontMetrics metrics1 = getFontMetrics(g.getFont()); 
				g.drawString("Score: " + appleseaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + appleseaten))/2,g.getFont().getSize());
				
			}else {
				gameOver(g); 
			}
			
		}else if (state == STATE.MENU) {
			menu.draw(g); 
		}
		
	}
	
	public void newApple() {
		appleX = random.nextInt((int) (SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE; 
		appleY = random.nextInt((int) (SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		
	}
	
 	public void move() {
 		// iterate over all body Parts
 		// Shifting all the body parts
		for(int i = bodyParts ; i > 0 ; i--) {
			x[i] = x[i-1];  
			y[i] = y[i-1];  
		}
		
		switch(direction) {
		case 'U': 
			y[0] = y[0] - UNIT_SIZE; 
			break; 
		case 'D': 
			y[0] = y[0] + UNIT_SIZE; 
			break; 
		case 'R': 
			x[0] = x[0] + UNIT_SIZE; 
			break; 
		case 'L': 
			x[0] = x[0] - UNIT_SIZE; 
			break; 
		}
	}

	public void checkApple() {
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++; 
			appleseaten++; 
			newApple(); 
		}
	}
	
	public void checkCollision() {
		
		// Check if head collides with body
		for(int i = bodyParts; i > 0; i--) {
			// Head collided with the body
			if((x[0] == x[i]) && (y[0] == y[i])) {
				running = false; 
			}
		}
		
		/*
		 * DIFFICULTY : EASY 
		 * - There is no collision when hitting the borders.  
		 */
		if(difficulty == DIFFICULTY.EASY) {
			// Check if head touches left border
			if(x[0] < 0) {
				x[0] = SCREEN_WIDTH-1; 
			}
			// Check if head touches right border
			if(x[0] > SCREEN_WIDTH) {
				x[0] = 1;  
			}
			// Check if head touches top border
			if(y[0] < 0) {
				y[0] = SCREEN_HEIGHT-1;  
			}
			// Check if head touches bottom border
			if(y[0] > SCREEN_HEIGHT) {
				y[0] = 1; 
			}
		/*
		* DIFFICULTY : HARD  
		* - There is collision when hitting the borders.  
		*/
		}else if(difficulty == DIFFICULTY.HARD || difficulty == DIFFICULTY.MEDIUM){
			// Check if head touches left border
			if(x[0] < 0) {
				running = false; 
			}
			// Check if head touches right border
			if(x[0] > SCREEN_WIDTH) {
				running = false; 
			}
			// Check if head touches top border
			if(y[0] < 0) {
				running = false; 
			}
			// Check if head touches bottom border
			if(y[0] > SCREEN_HEIGHT) {
				running = false; 
			}
		}
		
		if(!running) {
			timer.stop(); 
		}
	}
	
	public void gameOver(Graphics g) {
		
		//Game Over Text
		g.setColor(Color.white);
		g.setFont(new Font("Ubuntu", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont()); 
		g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("GameOver"))/2, SCREEN_HEIGHT/2); 	
		g.setFont(new Font("Ubuntu", Font.PLAIN, 30));
		g.drawString("Score: " + appleseaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + appleseaten))/2 + 95 , SCREEN_HEIGHT/2 + 50); 		

		
		//Buttons
		Graphics2D g2d = (Graphics2D) g; 
		Font fnt2 = new Font("Ubuntu", Font.BOLD, 30); 
		g.setFont(fnt2);
		g.drawString("Quit", quitButton2.x + 30, quitButton2.y + 35);
		g2d.draw(quitButton2);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(running) {
			move(); 
			checkApple(); 
			checkCollision();
		}
		repaint(); 
	}
	

	public class SnakeKeyAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}
	
}
