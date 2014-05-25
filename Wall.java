import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

/*
The Wall class will be used to represent the walls of a pool table. There are 6 "walls" of a pool table. 
Two on each side of the middle hole, one in between two corner ones.
They're basically lines btw. (x1,y1) is one point. (x2,y2) is another point.

Hopefully I'll make it so that the physics engine will detect when a ball reaches a point that's inbetween a wall's two points.
Seems like the only way.
*/

public class Wall {
	private double x1;
	private double y1;
	private double x2;
	private double y2;
}




