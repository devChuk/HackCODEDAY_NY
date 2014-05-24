//class CueBall

public class CueBall extends Ball {

    //instance vars
    private int direction;

    //constructor
    public CueBall(int x, int y) {
	super(0, x, y);
    }
    
    public int setDirection(int dir){
	direction = dir;
    }

} // end class Cue Ball
