/*
  class CueBall 
 */

public class CueBall extends Ball {

	public CueBall(int x, int y) {
		super(0, x, y);
	}

	public void shoot(int vX, int vY) {
		setXvel((double)vX);
		setYvel((double)vY);
	}




} // end class Cue Ball
