import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Ball {
	private double x;
	private double y;
	private double xVel;
	private double yVel;
	private int wang; //It's an inside joke. This is meant to slow down velocity. The larger the wang, the faster it slows down.
	private boolean hidden; //If the ball goes into a hole, hidden gets set true on Pool.java

	public Ball(int x, int y) {
		setX(x);
		setY(y);
		setXvel(0);
		setYvel(0);
		setHidden(false);
	}
	public void setX(double hurr) {
		x = hurr;
	}
	public void setY(double hurr) {
		y = hurr;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return x;
	}
	public double getXvel() {
		return xVel;
	}
	public double getYvel() {
		return yVel;
	}
	public void setXvel(double v) {
		xVel = v;
	}
	public void setYvel(double v) {
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