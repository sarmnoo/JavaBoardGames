package fiveChess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ChessBoard extends JPanel implements MouseListener{
	class Point{
		public int x;
		public int y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public Image board;
	private int row = 15;
	private int col = 15;
	private int color = 1;
	private ChessMan chessStatus[][] = new ChessMan[row][col]; 
	
	public ChessBoard(){
		board = Toolkit.getDefaultToolkit().getImage("/Users/ddning/Documents/workspace/fiveChess/board1.jpg");
		if(board == null){
			System.out.println("Image is not exit!");
		}	
		addMouseListener(this);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//画棋盘背景
		int imgWidth = board.getWidth(this);
		int imgHeight = board.getHeight(this);
		int gWidth = getWidth();
		int gHeight = getHeight();
		
		int borderX = (gWidth - imgWidth)/2;
		int borderY = (gHeight - imgHeight)/2;
		g.drawImage(board, borderX, borderY, null);
		
		
		//画线
		int interBoder = 20;
		int margin = borderX + interBoder;
		int grid = (imgWidth - interBoder*2)/row;
		
		int x = margin;
		int y = margin;
		for(int i = 0; i < row; i++){
			g.drawLine(x, y + i*grid, x + grid*row, y + i*grid);
		}
		for(int j = 0; j < col;j ++){
			g.drawLine(x + grid*j, y, x + grid*j, y + grid*col);
		}
        int radius = 30;
        
        // 绘制棋盘中心点，（x,y）为左上角 radius 为直径
        g.setColor(Color.BLACK);
        g.fillOval(x+grid*7-5, y+grid*7-5, 10, 10);
        
//        ChessMan chessMan = new ChessMan(8,8,1);
//        drawChessMan(g,chessMan,margin,grid,radius);
        
        for(int i = 0; i < row; i++){
        		for(int j = 0; j < col; j++){
        			if(chessStatus[i][j] != null){
        				drawChessMan(g,chessStatus[i][j],margin,grid,radius);
        			}
        		}			
        }
        
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		int imgWidth = board.getWidth(this);
		int imgHeight = board.getHeight(this);
		int gWidth = getWidth();
		int gHeight = getHeight();
		
		int borderX = (gWidth - imgWidth)/2;
		int borderY = (gHeight - imgHeight)/2;

		//画线
		int interBoder = 20;
		int margin = borderX + interBoder;
		int grid = (imgWidth - interBoder*2)/row;
		
		int chessManX = (x-margin+grid/2)/grid;
		int chessManY = (y-margin+grid/2)/grid;
		ChessMan newChessMan = new ChessMan(chessManX,chessManY,color);
		chessStatus[chessManX][chessManY] = newChessMan;
		if(color == 1){
			color = 0;
		}
		else{
			color = 1;
		}
		repaint();
		if(isWin(newChessMan)){
			int color = newChessMan.getColor();
			String winner = color==1?"黑棋获胜！":"白棋获胜！";
			System.out.print(winner);
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void drawChessMan(Graphics g,ChessMan chessMan,int margin,int grid,int radius){
		if(chessMan.getColor() == 1){
			g.setColor(Color.BLACK);
		}
		else{
			g.setColor(Color.WHITE);
		}
		g.fillOval(margin+grid*chessMan.getX()-15, margin+grid*chessMan.getY()-15, radius, radius);
	}
	public boolean isWin(ChessMan chessMan){
		int x = chessMan.getX();
		int y = chessMan.getY();
		int color = chessMan.getColor();
		Point direction[][] = new Point[][] {
			{new Point(1, 0), new Point(-1, 0)},
			{new Point(0, -1), new Point(0, 1)},
			{new Point(-1, -1), new Point(1, 1)},
			{new Point(-1, 1), new Point(1, -1)}
		};
		
		for(int i = 0; i < 4;i++){
			int dis = 0;
			for(int j = 0;j < 2;j++){
				int k = 1;
				
				while(k < 5){
					int dx = direction[i][j].x * k;
					int dy = direction[i][j].y * k;
					if(x+dx >= 0 && x+dx < this.col && y+dy >= 0 && y+dy < this.row 
							&& chessStatus[x+dx][y+dy] != null && color == chessStatus[x+dx][y+dy].getColor()){
						dis++;
					}
					else
						break;
					k++;
				}	
			}
			if(dis >= 4)
				return true;
		}
		return false;

	}
	
}
