import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Ball {
	private double x;
	private double y;
	private double xVel;
	private double yVel;
	private double wang; //It's an inside joke. This is meant to slow down velocity. The larger the wang, the faster it slows down.
	private boolean hidden; //If the ball goes into a hole, hidden gets set true on Pool.java
	private int ballNumber;
	private int color; //1:blue,2:red,3:black
	private final int MaxV = 10;
	private final int COOLDOWN = 10;
	
	protected ArrayList<Integer> cooldownBalls = new ArrayList<Integer>();
	protected ArrayList<Integer> cooldownTimes = new ArrayList<Integer>();

	public Ball(int ballNumber, int x, int y, int colour) {
		setX((double)x);
		setY((double)y);
		setXvel(0);
		setYvel(0);
		setHidden(false);
		this.ballNumber = ballNumber;
		wang = 0.03;
		color = colour;
	}
	public int getColor() {
		return color;
	}
	public int setColor(int color) {
		return color;
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
		return y;
	}
	public double getXvel() {
		return xVel;
	}
	public double getYvel() {
		return yVel;
	}
	public void setXvel(double v) {
		if (v < MaxV) xVel = v;
		else xVel = MaxV;
	}
	public void setYvel(double v) {
		if (v < MaxV) yVel = v;
		else xVel = MaxV;
	}
	public void move() {
		x += xVel;
		y += yVel;
		slowdown();
	}
	public void setHidden(boolean durr) {
		hidden = durr;
	}
	public void slowdown() {
		double magnitude = Math.sqrt(xVel * xVel + yVel * yVel);
		magnitude -= wang;
		if (magnitude < 0) {
			setXvel(0);
			setYvel(0);
			return;
		}
		double theta = Math.atan2(yVel, xVel);
		setXvel( magnitude * Math.cos(theta) );
		setYvel( magnitude * Math.sin(theta) );
		
	}
	public int getBallNumber() {return ballNumber;}
	public void setBallNumber(int number) {ballNumber = number;}
	public void shoot(int dx, int dy) {}
	public void addToCooldown(int ballNumber) {
		cooldownBalls.add(ballNumber);
		cooldownTimes.add(COOLDOWN);
	}
	public void reduceCooldowns() {
		for (int i = 0; i < cooldownBalls.size(); i++) {
			if (cooldownTimes.get(i) == 0) {
				cooldownBalls.remove(0);
				cooldownTimes.remove(0);
				i--;
				continue;
			}
			cooldownTimes.set(i, cooldownTimes.get(i) - 1);
		}
	}
	
}