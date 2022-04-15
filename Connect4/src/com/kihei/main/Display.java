package com.kihei.main;

import java.awt.*;

public class Display {
	
	public static int[][] board = new int[6][7];
	public static int ct = 1;
	public static int winner = 0;
	
	public void render(Graphics g, int width, int height) {
		int w = 700;
		int h = w*6/7;
		int cX = ((width - w) / 2);
		int cY = ((height - h) / 2);
		boolean full = true;
		
		g.setColor(new Color(0, 112, 191));
		g.fillRect(cX-50, cY-50, w+100, h+100);
		
		for(int r = 0; r < 6; r++) {
			for(int c = 0; c < 7; c++) {
				if(board[r][c] == 0) {
					full = false;
					g.setColor(new Color(255, 252, 236));
				} else if (board[r][c] == 1) {
					g.setColor(new Color(255, 0, 0));
				} else if (board[r][c] == 2) {
					g.setColor(new Color(255, 255, 0));
				}
				
				g.fillOval(cX, cY, 100, 100);
				
				cX += 100;
			}
			cY += 100;
			cX -= 700;
		}
		
		/* Check for win */
		// horizontal 4 in a row
		for(int r = 0; r < 6; r++) {
			for(int c = 0; c < 4; c++) {
				if (board[r][c] != 0 && board[r][c] == board[r][c+1] && board[r][c] == board[r][c+2] && board[r][c] == board[r][c+3]) {
					winner = board[r][c];
				}
			}
		}
		// vertical
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 7; c++) {
				if (board[r][c] != 0 && board[r][c] == board[r+1][c] && board[r][c] == board[r+2][c] && board[r][c] == board[r+3][c]) {
					winner = board[r][c];
				}
			}
		}
		
		// right diagonal
		for(int r = 0; r < 3; r++) {
			for(int c = 0; c < 4; c++) {
				if (board[r][c] != 0 && board[r][c] == board[r+1][c+1] && board[r][c] == board[r+2][c+2] && board[r][c] == board[r+3][c+3]) {
					winner = board[r][c];
				}
			}
		}
		// left diagonal
		for(int r = 0; r < 3; r++) {
			for(int c = 6; c >= 3; c--) {
				if (board[r][c] != 0 && board[r][c] == board[r+1][c-1] && board[r][c] == board[r+2][c-2] && board[r][c] == board[r+3][c-3]) {
					winner = board[r][c];
				}
			}
		}
		
		g.setColor(Color.black);
		if (winner == 1 || winner == 2) {
			cStr(g, tts(winner) + " won!", 0, -390, width, height, 40);
		} else if (full) {
			cStr(g, "Draw", 0, -390, width, height, 40);
			winner = 3;
		}
		
		if (winner != 0) {
			g.setColor(Color.black);
			g.fillRect((width - 200) / 2, (height - 75) / 2 + 413, 200, 75);
			g.setColor(Color.white);
			cStr(g, "Play Again", (width - 200) / 2, (height - 75) / 2 + 413, 200, 75, 35);
		}
		
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 40));
		g.drawString("Current turn:", 20, 50);
		if(Display.ct == 1) {
			g.setColor(new Color(255, 0, 0));
		} else {
			g.setColor(new Color(255, 255, 0));
		}
		g.fillOval(250, 14, 50, 50);
	}
	
	// current turn (integer) to string
	private String tts(int i) {
		if (i == 1)
			return "Red";
		else if (i == 2)
			return "Yellow";
		else
			return "";
	}
	
	// center string
	private void cStr(Graphics g, String text, int rectX, int rectY, int rectW, int rectH, int fontSize) {
	    FontMetrics metrics = g.getFontMetrics(new Font(g.getFont().getFontName(), Font.PLAIN, fontSize));
	    // Determine the X coordinate for the text
	    int x = rectX + (rectW - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = rectY + ((rectH - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, fontSize));
	    g.drawString(text, x, y);
	}
}
