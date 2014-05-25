// This is where all the heavy duty computing is!

import java.util.ArrayList;

public class Physics {
	private final int BALL_SIZE = 25;

	public boolean willItCollide(Ball a, Ball b) {
		double dx = Math.abs( a.getX() - b.getX() );
		double dy = Math.abs( a.getY() - b.getY() );
		
		if (Math.sqrt(dx * dx + dy * dy) < BALL_SIZE) return true;
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
		
		double Atheta = Math.atan2(AyVel, AxVel) * 180 / Math.PI;
		double Btheta = Math.atan2(ByVel, BxVel) * 180 / Math.PI;
		
		double dx = AxVel - BxVel;
		double dy = AyVel - ByVel;
		
		double dxx = Math.abs( A.getX() - B.getX() );
		double dyy = Math.abs( A.getY() - B.getY() );
		
		double contactAngle = Math.atan2(dyy, dxx) * 180 / Math.PI;
		//System.out.println(contactAngle);
		
		double theta = Math.atan2(dy, dx) * 180 / Math.PI;
		//calculate A ball final X velocities
		double newAx = Bvel * Math.cos(Math.toRadians(Btheta - contactAngle)) * Math.cos(Math.toRadians(contactAngle)) + Avel * Math.sin(Math.toRadians(Atheta - contactAngle)) * Math.cos(Math.toRadians(contactAngle + 45));
		double newAy = Bvel * Math.cos(Math.toRadians(Btheta - contactAngle)) * Math.sin(Math.toRadians(contactAngle)) + Avel * Math.sin(Math.toRadians(Atheta - contactAngle)) * Math.sin(Math.toRadians(contactAngle + 45));
		
		double newBx = Avel * Math.cos(Math.toRadians(Atheta - contactAngle)) * Math.cos(Math.toRadians(contactAngle)) + Bvel * Math.sin(Math.toRadians(Btheta - contactAngle)) * Math.cos(Math.toRadians(contactAngle + 45));
		double newBy = Avel * Math.cos(Math.toRadians(Atheta - contactAngle)) * Math.sin(Math.toRadians(contactAngle)) + Bvel * Math.sin(Math.toRadians(Btheta - contactAngle)) * Math.sin(Math.toRadians(contactAngle + 45));
		
		A.setXvel(newAx);
		A.setYvel(newAy);
		B.setXvel(newBx);
		B.setYvel(newBy);
	}
	
}