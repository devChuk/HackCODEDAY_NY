import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Ball {
	private int x;
	private int y;
	private int xVel;
	private int yVel;
	private int wang; //It's an inside joke. This is meant to slow down velocity. The larger the wang, the faster it slows down.
	private boolean hidden; //If the ball goes into a hole, hidden gets set true on Pool.java

	public Ball(int x, int y) {
		setX(x);
		setY(y);
		setXvel(0);
		setYvel(0);
		setHidden(false);
	}
	public int setX(int hurr) {
		x = hurr;
		return x;
	}
	public int setY(int hurr) {
		y = hurr;
		return y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return x;
	}
	public int getXvel() {
		return xVel;
	}
	public int getYvel() {
		return yVel;
	}
	public void setXvel(int v) {
		xVel = v;
	}
	public void setYvel(int v) {
		yVel = v;
	}
	public void move() {
		x += xVel;
		y += yVel;
	}
	public void setHidden(boolean durr) {
		hidden = durr;
	}
	public void slowdown() {
		if (xVel >= wang) {
			xVel -= wang;
		}
		if (yVel >= wang) {
			yVel -= wang;
		}
	}
}