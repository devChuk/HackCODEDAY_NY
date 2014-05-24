// This is where all the heavy duty computing is!

public class Physics {
	private final int BALL_SIZE = 30;

	public boolean willItCollide(Ball a, Ball b) {
		int dx = Math.abs( a.getX() - a.getY() );
		int dy = Math.abs( a.getY() - a.getY() );
		
		if (Math.sqrt(dx * dx + dy * dy) < BALL_SIZE) return true;
		return false;
	}
}