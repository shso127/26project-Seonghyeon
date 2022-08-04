package application;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends GridPane{
	
	public Cell() {
		
	}
	
	public Cell(int row, int col, int squareSize) {
		for(int i = 0; i < col; i++) {
			for(int j = 0; j < row; j++) {
				Rectangle rec = new Rectangle(0,0,squareSize,squareSize);
				rec.setFill(Color.WHITE);
				rec.setStroke(Color.BLACK);
				super.add(rec,i,j);
			}
		}
	}
	
	public void addPiece(Piece p, int row, int col) {
		super.add(p,row,col);
	}
	
	
}
