package fiveChess;

import java.awt.Color;

public class ChessMan {
	private int x;
	private int y;
	private int radius = 30;
	private int color;
	
	public ChessMan(int x,int y,int color){
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public int getX(){
		return x;
	}
	public void setX(int x){
		this.x = x;
	}
	public int getY(){
		return y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	public int getColor(){
		return color;
	}
	public void setColor(int color){
		this.color = color;
	}
	
}
