import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

public class Pool extends JPanel{
	Image poolTable;
	Image ball;
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	private int winner = 0; //0 when nobody has won yet; 1 if player 1 wins, 2 if player 2 wins.

	public Pool() {
		Toolkit tkit = Toolkit.getDefaultToolkit();
		poolTable = tkit.getImage(Pool.class.getResource("data/snookerTableBlue.jpg"));
		//ball = tkit.getImage(Pool.class.getResource("data/ball.jpg"));
		addMouseListener(new MouseListener());
		//add balls
		Ball oneBall = new Ball(1, 200, 300);
	}
	
	public static void main(String[] args) {
		Pool p = new Pool();
		
		JFrame frame = new JFrame("Pool");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
		Container c = frame.getContentPane();
		c.add(p);
		frame.setVisible(true);
	}
	
	class MouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
		
		}
		
		public void mouseClicked(MouseEvent e) {
			
		}
		
		public void mouseReleased(MouseEvent e) {
		
		}
	} // end of MouseListener
	
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			setBackground(Color.BLACK);
			
			g2.drawImage(poolTable, 0, 0, this);
			
			for (int i = 0; i < balls.size(); i++) {
				
			}
		} // painting method
}