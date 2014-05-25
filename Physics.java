// This is where all the heavy duty computing is!

import java.util.ArrayList;

public class Physics {
	private final int BALL_SIZE = 25;

	public boolean willItCollide(Ball a, Ball b) {
		double dx = Math.abs( a.getX() - b.getX() );
		double dy = Math.abs( a.getY() - b.getY() );
		//System.out.println(Math.sqrt(dx * dx + dy * dy));
		
		if (Math.sqrt(dx * dx + dy * dy) < BALL_SIZE && Math.sqrt(dx * dx + dy * dy) != 0) {
			System.out.println("hit!");
			return true;
		}
		return false;
	}
	
	public boolean willWallCollide(Wall wall, Ball ball) {
		return false;
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
		
		double dxx = Math.abs( A.getX() - B.getX() );
		double dyy = Math.abs( A.getY() - B.getY() );
		
		double contactAngle = Math.atan2(dyy, dxx);
		if (contactAngle < 0) contactAngle += 180;
		
		double theta = Math.atan2(dy, dx);
		//calculate A ball final X velocities

		double newAx = Bvel * Math.cos(Btheta - contactAngle) * Math.cos(contactAngle) + Avel * Math.sin(Atheta - contactAngle) * Math.cos(contactAngle + 45);
		double newAy = Bvel * Math.cos(Btheta - contactAngle) * Math.sin(contactAngle) + Avel * Math.sin(Atheta - contactAngle) * Math.sin(contactAngle + 45);
		
		double newBx = Avel * Math.cos(Atheta - contactAngle) * Math.cos(contactAngle) + Bvel * Math.sin(Btheta - contactAngle) * Math.cos(contactAngle + 45);
		double newBy = Avel * Math.cos(Atheta - contactAngle) * Math.sin(contactAngle) + Bvel * Math.sin(Btheta - contactAngle) * Math.sin(contactAngle + 45);
		
		A.setXvel( - newAx);
		A.setYvel( - newAy);
		B.setXvel(newBx);
		B.setYvel( - newBy);
	}
	
}