import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class CaptureTheCastle extends JFrame {
	private boolean up, down, left, right;

	public static void main(String[] args) {
		
	}


	class ArrowListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					up = true;
					break;
				case KeyEvent.VK_DOWN:
					down = true;
					break;
				case KeyEvent.VK_LEFT:
					left = true;
					break;
				case KeyEvent.VK_RIGHT:
					right = true;
					break;
			}
		}
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					up = false;
					break;
				case KeyEvent.VK_DOWN:
					down = false;
					break;
				case KeyEvent.VK_LEFT:
					left = false;
					break;
				case KeyEvent.VK_RIGHT:
					right = false;
					break;
			}
		}
	} // end of ArrowListener
} // end of CTC class