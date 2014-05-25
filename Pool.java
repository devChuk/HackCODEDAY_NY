import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

public class Pool extends JPanel{
	Image poolTable;
	Image redball;
	Image blueball;
	Image whiteball;
	Image cue;
	private static ArrayList<Ball> balls = new ArrayList<Ball>();
	private int winner = 0; //0 when nobody has won yet; 1 if player 1 wins, 2 if player 2 wins.
	private boolean aboutToShoot;
	private int cueX, cueY;
	
	public Pool() {
		Toolkit tkit = Toolkit.getDefaultToolkit();
		poolTable = tkit.getImage(Pool.class.getResource("data/PoolTable.png"));
		redball = tkit.getImage(Pool.class.getResource("data/red.png"));
		blueball = tkit.getImage(Pool.class.getResource("data/blue.png"));
		whiteball = tkit.getImage(Pool.class.getResource("data/white.png"));
		cue = tkit.getImage(Pool.class.getResource("data/cue.png"));
		addMouseListener(new MouseListener());
		initialBallSetup();
	}
	
	public void initialBallSetup() {
		//add balls
			//add cueball
		CueBall cueball = new CueBall(300, 400);
		cueball.setXvel(5.0);
		balls.add(cueball);
		
		int ballCounter = 1;
		for (int i = 1; i <= 5; i++) { // 5 layers
			for (int j = 1; j <= i; j++) {
				Ball newBall = new Ball(ballCounter, 600 + (i * 27), 350 - (14 * (i - 1)) + (28 * (j - 1)));
				ballCounter++;
				balls.add(newBall);
			}
		}
		/*
		Ball firstball = new Ball(1, 600, 350);
		balls.add(firstball);
		Ball secondball = new Ball(2, 623, 338);
		balls.add(secondball);
		Ball thirdball = new Ball(3, 623, 362);
		balls.add(thirdball);
		*/
	}
	
	public static void main(String[] args) {
		Pool p = new Pool();
		Physics phys = new Physics();
		
		JFrame frame = new JFrame("Pool");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700);
		Container c = frame.getContentPane();
		c.add(p);
		frame.setVisible(true);
		while (true) {
			c.repaint();
			
			//check for stuff
			for (int i = 0; i < balls.size(); i++) {
				for (int j = i; j < balls.size(); j++) {
					if (phys.willItCollide(balls.get(i), balls.get(j))) {
						phys.calcFinalVelocity(balls.get(i), balls.get(j));
					}
				}
			}
			//check walls
			for (int i = 0; i < balls.size(); i++) {
				phys.checkWalls(balls.get(i));
			}
			
			//move the balls
			for (int i = 0; i < balls.size(); i++) {
				balls.get(i).move();
				//System.out.println(balls.get(i).getX() + "     " + balls.get(i).getY());
			}
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {}
		}
	}
	
	class MouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			System.out.println(e.getX() + "  " + e.getY());
			if (!aboutToShoot) {
				aboutToShoot = true;
				cueX = e.getX();
				cueY = e.getY();
			}
			else if (aboutToShoot) {
				int x = e.getX();
				int y = e.getY();
				int cueX = (int)balls.get(0).getX();
				int cueY = (int)balls.get(0).getY();
				int dx = cueX - x;
				int dy = cueY - y;
				int multiplier = 10;
				//balls.get(0).shoot(dx * multiplier, dy * multiplier);
				aboutToShoot = false;
			}
		}
	} // end of MouseListener
	
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			setBackground(Color.BLACK);
			
			g2.drawImage(poolTable, 182, 163, this); //pool table dimensions 636 x 373
			
			for (int i = 0; i < balls.size(); i++) {
				g2.drawImage(redball, (int)balls.get(i).getX(), (int)balls.get(i).getY(), this);
			}
			
			//paint the cue
			if (aboutToShoot) {
				int dx = (int)balls.get(0).getX() + 15 - cueX;
				int dy = (int)balls.get(0).getY() + 15 - cueY;
				int theta = (int)Math.toDegrees(Math.atan2(dy, dx)) + 180;
				g2.rotate(Math.toRadians((142 + theta) % 360), cueX, cueY);
				g2.drawImage(cue, (int)balls.get(0).getX() + 15, (int)balls.get(0).getY() + 15, this);
			}
		} // painting method
}
