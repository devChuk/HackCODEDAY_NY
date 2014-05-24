import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

public class Pool extends JFrame{
	Image poolTable;
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	private int winner = 0; //0 when nobody has won yet; 1 if player 1 wins, 2 if player 2 wins.

	public Pool() {
		Toolkit tkit = Toolkit.getDefaultToolkit();
		poolTable = tkit.getImage(Pool.class.getResource("data/snookerTableBlue.jpg"));
		
		setTitle("Pool");
		setSize(668, 415);
		setVisible(true);
		addMouseListener(new MouseListener());
		add(new Painter());
	}
	
	public static void main(String[] args) {
		Pool p = new Pool();
	}
	
	class MouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
		
		}
		
		public void mouseClicked(MouseEvent e) {
		
		}
		public void mouseReleased(MouseEvent e) {
		
		}
	} // end of MouseListener
	
	class Painter extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			g2.drawImage(poolTable, 0, 0, this);
		}
	}
}