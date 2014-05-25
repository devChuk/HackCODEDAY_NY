// This is where all the heavy duty computing is!

import java.util.ArrayList;

public class Physics {
	private final int BALL_SIZE = 25;
	private final int friction = 3;
	private boolean open = true;

	public boolean willItCollide(Ball a, Ball b) {
		double dx = Math.abs( a.getX() - b.getX() );
		double dy = Math.abs( a.getY() - b.getY() );
		//System.out.println(Math.sqrt(dx * dx + dy * dy));
		
		if (Math.sqrt(dx * dx + dy * dy) < BALL_SIZE && Math.sqrt(dx * dx + dy * dy) != 0) {
			//System.out.println("hit!");
			return true;
		}
		
		return false;
	}
	
	public void checkWalls(Ball a) {
		//check walls
			//left wall - X = 214, Y = 223, 476
			//right wall  X = 794
			//top wall - Y = 198, X = 237 474, 526 765
			//bottom wall    500
		int x = (int)a.getX();
		int y = (int)a.getY();
			//left + right
		if (x < 214 && y > 223 && y < 476) a.setXvel(-a.getXvel());
		if (x > 794 - (BALL_SIZE) && y > 223 && y < 476) a.setXvel(-a.getXvel());
			//top wall
		if (y < 198 && x > 237 && x < 474) a.setYvel(-a.getYvel());
		if (y < 198 && x > 526 && x < 765) a.setYvel(-a.getYvel());
			//bottom wall
		if (y > 500 - (BALL_SIZE) && x > 237 && x < 474) a.setYvel(-a.getYvel());
		if (y > 500 - (BALL_SIZE) && x > 526 && x < 765) a.setYvel(-a.getYvel());
	}
	

	public boolean getOpen() {
		return open;
	}
	
	public void checkOutOfBounds(ArrayList<Ball> balls) {
		for (int i = 0; i < balls.size(); i++) {
			if (balls.get(i).getX() < 205 || balls.get(i).getY() > 509 - (BALL_SIZE) || 
				balls.get(i).getX() > 803 - (BALL_SIZE) || balls.get(i).getY() < 189) {
					if (i != 0) {
						balls.remove(i);
						i--;
						open = false;
					}
					if (i == 0) {
						balls.get(0).setX(300);
						balls.get(0).setY(350);
						balls.get(0).setXvel(0);
						balls.get(0).setYvel(0);
					}
				}
		}
	}

	/*  ~~~~~~   Elastic collision calculator   ~~~~~  */
	public void calcFinalVelocity(Ball A, Ball B) {
		double AxVel = A.getXvel();
		double BxVel = B.getXvel();
		double AyVel = A.getYvel();
		double ByVel = B.getYvel();

		double Avel = Math.sqrt(AxVel * AxVel + AyVel * AyVel);
		double Bvel = Math.sqrt(BxVel * BxVel + ByVel * ByVel);
		
		double Atheta = Math.atan2(AyVel, AxVel);
		double Btheta = Math.atan2(ByVel, BxVel);
		
		double dx = AxVel - BxVel;
		double dy = AyVel - ByVel;
		
		double dxx = A.getX() - B.getX();
		double dyy = A.getY() - B.getY();
		
		double contactAngle = Math.atan2(dyy, dxx);
		//System.out.println(contactAngle * 180 / Math.PI);
		
		double theta = Math.atan2(dy, dx);
		//calculate A ball final X velocities

		double newAx = Bvel * Math.cos(Btheta - contactAngle) * Math.cos(contactAngle) + Avel * Math.sin(Atheta - contactAngle) * Math.cos(contactAngle + 45);
		double newAy = Bvel * Math.cos(Btheta - contactAngle) * Math.sin(contactAngle) + Avel * Math.sin(Atheta - contactAngle) * Math.sin(contactAngle + 45);
		
		double newBx = Avel * Math.cos(Atheta - contactAngle) * Math.cos(contactAngle) + Bvel * Math.sin(Btheta - contactAngle) * Math.cos(contactAngle + 45);
		double newBy = Avel * Math.cos(Atheta - contactAngle) * Math.sin(contactAngle) + Bvel * Math.sin(Btheta - contactAngle) * Math.sin(contactAngle + 45);
		
		A.setXvel(-newAx);
		A.setYvel(newAy);
		
		
		if ((contactAngle * 180 / Math.PI) < 110) {
			B.setXvel(newBx);
			B.setYvel(newBy);
		}
		else {
		
			B.setXvel(newBx);
			B.setYvel(newBy);
		}
	}
	
}