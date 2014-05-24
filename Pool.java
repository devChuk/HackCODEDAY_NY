import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.event.*;

public class Pool extends JFrame{
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	private int winner = 0; //0 when nobody has won yet; 1 if player 1 wins, 2 if player 2 wins.

	public Pool() {
		setTitle("Pool");
		setSize(500, 500);
	}
	
	class MouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
		
		}
		
		public void mouseClicked(MouseEvent e) {
		
		}
		public void mouseReleased(MouseEvent e) {
		
		}
	}
}