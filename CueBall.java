//class CueBall

public class CueBall extends Ball {
	private final int MaxV = 10;
	private int direction;
	
	public CueBall(int x, int y) {
		super(0, x, y, 0);
	}

	public void shoot(int vX, int vY) {
		if (vX < MaxV) setXvel((double)vX);
		else setXvel(MaxV);
		if (vY < MaxV) setYvel((double)vY);
		else setYvel(MaxV);
	}
 
    public void setDirection(int dir){
		direction = dir;
    }

} // end class Cue Ball
