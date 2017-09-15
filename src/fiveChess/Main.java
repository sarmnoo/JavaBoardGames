package fiveChess;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Main extends JFrame {
	private ChessBoard board;
    public Main() {        
    		board = new ChessBoard();
        
        //Frame标题
        setTitle("单机五子棋");
        setSize(750,775);
        setResizable(false);
        Container containerPane =getContentPane();
        containerPane.add(board);   
        
        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.out.println("退出游戏！");
				System.exit(0);
			}
		});
    }
    public static void main(String[] args) {
        Main m = new Main();
        m.setVisible(true);
    }
}
