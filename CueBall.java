//class CueBall

public class CueBall extends Ball {

	private int direction;
	
	public CueBall(int x, int y) {
		super(0, x, y);
	}

	public void shoot(int vX, int vY) {
		if (vX > 10) {
			setXvel(10.0);	
		}
		else
			setXvel((double)vX);
		if (vY > 10) {
			setYvel(10.0);	
		}
		else
			setYvel((double)vY);
	}
 
    public void setDirection(int dir){
		direction = dir;
    }

} // end class Cue Ball
