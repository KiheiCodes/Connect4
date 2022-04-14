package com.kihei.main;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (Display.winner == 0) {
			int w = 700;
			int h = w*6/7;
			int cX = ((984 - w) / 2);
			int cY = ((961 - h) / 2);
			
			for(int c = 0; c < 7; c++) {
				if (mouseOver(mx, my, cX, cY, 100, 600)) {
					for(int r = 5; r >= 0; r--) {
						if (Display.board[r][c] == 0) {
							Display.board[r][c] = Display.ct;
							swapTurn();
							break;
						}
					}
				}
					
				cX += 100;
			}
		} else {
			if (mouseOver(mx, my, (984 - 200) / 2, (961 - 75) / 2 + 413, 200, 75)) {
				Display.board = new int[6][7];
				Display.winner = 0;
				Display.ct = 1;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width && my > y && my < y + height)
			return true;
		else
			return false;
	}
	
	private void swapTurn() {
		if (Display.ct == 1)
			Display.ct = 2;
		else
			Display.ct = 1;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
	
}
