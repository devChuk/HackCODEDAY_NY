import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
 
public class Pool extends JPanel{
	private Image poolTable;
	private Image redball;
	private Image blueball;
	private Image whiteball;
	private Image blackball;
	private Image cue;
	private static ImageIcon icon;
	private static KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
	private static boolean close;
	private static ArrayList<Ball> balls = new ArrayList<Ball>();
	private int winner = 0; //0 when nobody has won yet; 1 if player 1 wins, 2 if player 2 wins.
	private boolean aboutToShoot;
	private int cueX, cueY;
	private int rawX, rawY;
	int ddddx = cueX - rawX;
	int ddddy = cueY - rawY;
	double multiplier = 0.1;
	String _2bit;
	public Pool() {
		Toolkit tkit = Toolkit.getDefaultToolkit();
		poolTable = tkit.getImage(Pool.class.getResource("data/PoolTable.png"));
		redball = tkit.getImage(Pool.class.getResource("data/red.png"));
		blueball = tkit.getImage(Pool.class.getResource("data/blue.png"));
		whiteball = tkit.getImage(Pool.class.getResource("data/white.png"));
		blackball = tkit.getImage(Pool.class.getResource("data/black.png"));
		cue = tkit.getImage(Pool.class.getResource("data/cue.png"));
		icon = new ImageIcon("data/icon.png");
		addMouseListener(new MouseListener());
		initialBallSetup();
	}
	public void initialBallSetup() {
		//add balls
			//add cueball
		CueBall cueball = new CueBall(300, 350);
		//cueball.setXvel(5.0);
		balls.add(cueball);
		
		int ballCounter = 1;
		for (int i = 1; i <= 5; i++) { // 5 layers
			for (int j = 1; j <= i; j++) { //1 is blue.
				int hurr = 1;
				if ((i % 2 == 0) && (i != 5) && (i != 3)) {
					hurr = 2;
					if (j % 2 == 0)
						hurr = 1;
				}
				if ((i == 3) && (j == 3))
					hurr = 2;
				if ((i == 5) && (j % 2 != 0)) {
					hurr = 2;
				}
				if ((i == 3) && (j == 2)) {
					hurr = 3;
				}
				Ball newBall = new Ball(ballCounter, 600 + (i * 27), 350 - (14 * (i - 1)) + (28 * (j - 1)), hurr);
				ballCounter++;
				balls.add(newBall);
			}
		}
		balls.get(0).setColor(0);
		for (int i = 1; i < balls.size(); i++) {
			if (i == 5)
				balls.get(i).setColor(3);
			else if (i % 2 == 0)
				balls.get(i).setColor(2);
			else	
				balls.get(i).setColor(1);
		}
	}
	public static void main(String[] args) {
		Pool p = new Pool();
		Physics phys = new Physics();
		
		JFrame frame = new JFrame("Pool");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700);
		frame.setIconImage(icon.getImage());
		Container c = frame.getContentPane();
		c.add(p);
		frame.setVisible(true);
		Action escapeAction = new AbstractAction() {
    		// close the frame when the user presses escape
    		public void actionPerformed(ActionEvent e) {
    			close = true;
    		}
		};
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
		frame.getRootPane().getActionMap().put("ESCAPE", escapeAction);
		while (true) {
			if (close) {
				frame.dispose();
				System.exit(0);
			}
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
			//check for out of bounds
			phys.checkOutOfBounds(balls);
			
			//move the balls
			for (int i = 0; i < balls.size(); i++) {
				balls.get(i).move();
				//System.out.println(balls.get(i).getX() + "     " + balls.get(i).getY());
			}

			//check if 8ball is in.
			close = true;
			for (int i = 0; i < balls.size(); i++){
				if (balls.get(i).getColor() == 3) {
					close = false;
					break;
				}
			}
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {}
		}
	}
	class MouseListener extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			//System.out.println(e.getX() + "  " + e.getY());
			//Position the cue
			if (!aboutToShoot) {
				aboutToShoot = true;
				cueX = e.getX();
				cueY = e.getY();
				rawX = e.getX();	 				//mousex
				rawY = e.getY();					//mousey
			}
			else if (aboutToShoot) {
				rawX = e.getX();	 				//mousex
				rawY = e.getY();					//mousey
				cueX = (int)balls.get(0).getX(); 	//cue ballx
				cueY = (int)balls.get(0).getY(); 	//cue ballx
				ddddx = cueX - rawX; 				//xvect from cue to mouse
				ddddy = cueY - rawY;				//yvect from cue to mouse
				balls.get(0).shoot((int)(ddddx * multiplier), (int)(ddddy * multiplier)); //inserts xvect and yvect into power
				aboutToShoot = false;				//turns off shooting function
			}
		}
	} // end of MouseListener	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		setBackground(Color.BLACK);
		
		g2.drawImage(poolTable, 182, 163, this); //pool table dimensions 636 x 373
		
		g2.drawImage(whiteball, (int)balls.get(0).getX(), (int)balls.get(0).getY(), this);

		for (int i = 1; i < balls.size(); i++) {
			if (balls.get(i).getColor() == 3)
				g2.drawImage(blackball, (int)balls.get(i).getX(), (int)balls.get(i).getY(), this);
			else if (balls.get(i).getColor() == 2)
				g2.drawImage(redball, (int)balls.get(i).getX(), (int)balls.get(i).getY(), this);
			else
				g2.drawImage(blueball, (int)balls.get(i).getX(), (int)balls.get(i).getY(), this);
		}
			
		//paint the cue
		if (aboutToShoot) {
			int dx = rawX - ((int)balls.get(0).getX() + 12); 	//vector from cue to ballx
			int dy = rawY - ((int)balls.get(0).getY() + 12); 	//vector from cue to bally
			double theta = Math.atan2(dy, dx);					//angle of stick
			if (theta < 0) theta += 2 * Math.PI;				//some math thing I'm too lazy to understand for now
			//System.out.println(theta);
			g2.translate((int)balls.get(0).getX()+12,(int)balls.get(0).getY()+12);
			g2.rotate(theta);
			int stickx = 15;
			int sticky = -6;
			g2.drawImage(cue, stickx, sticky, this);
		}
	} // painting method
}