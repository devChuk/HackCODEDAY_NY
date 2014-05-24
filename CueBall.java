//class CueBall

public class CueBall extends Ball {

	private int direction;
	
	public CueBall(int x, int y) {
		super(0, x, y);
	}

	public void shoot(int vX, int vY) {
		setXvel((double)vX);
		setYvel((double)vY);
	}

 
    public int setDirection(int dir){
		direction = dir;
    }

} // end class Cue Ball
