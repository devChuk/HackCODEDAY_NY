import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

public class Pool extends JPanel{
	Image poolTable;
	Image ball;
	Image cue;
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	private int winner = 0; //0 when nobody has won yet; 1 if player 1 wins, 2 if player 2 wins.
	private boolean aboutToShoot;
	private int cueX, cueY;
	
	public Pool() {
		Toolkit tkit = Toolkit.getDefaultToolkit();
		poolTable = tkit.getImage(Pool.class.getResource("data/snookerTableBlue.jpg"));
		//ball = tkit.getImage(Pool.class.getResource("data/ball.jpg"));
		cue = tkit.getImage(Pool.class.getResource("data/cue.png"));
		addMouseListener(new MouseListener());
		//add balls
			//add cueball
		CueBall cueball = new CueBall(300, 400);
		balls.add(cueball);
	}
	
	public static void main(String[] args) {
		Pool p = new Pool();
		
		JFrame frame = new JFrame("Pool");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700);
		Container c = frame.getContentPane();
		c.add(p);
		frame.setVisible(true);
	}
	
	class MouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
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
				balls.get(0).shoot(dx * multiplier, dy * multiplier);
				aboutToShoot = false;
			}
		}
	} // end of MouseListener
	
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			setBackground(Color.BLACK);
			
			g2.drawImage(poolTable, 173, 155, this); //pool table dimensions 653 x 390
			
			for (int i = 0; i < balls.size(); i++) {
				
			}
			
			//paint the cue
			if (aboutToShoot) {
				g2.drawImage(cue, cueX, cueY, this);
			}
		} // painting method
}