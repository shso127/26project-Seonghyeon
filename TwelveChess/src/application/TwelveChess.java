package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TwelveChess extends Application{
	
	@Override
	public void start(Stage primaryStage) {
		
		Cell gameBoard = new Cell(4,3,150);
		gameBoard.setAlignment(Pos.CENTER_LEFT);
		Cell deadSpace1 = new Cell(1,6,75);
		Cell deadSpace2 = new Cell(1,6,75);
		GridPane lbPane = new GridPane();
		lbPane.setAlignment(Pos.CENTER);
		lbPane.setPrefWidth(430);
		lbPane.setVgap(10);
		
		
		Button btStart = new Button("Start the game!");
		Button btRule = new Button("Rules");
		Button btBack = new Button("Back");
		lbPane.add(btStart,0,0);
		lbPane.add(btRule,0,3);
		
		Text tplayer1 = new Text("PLAYER1");
		tplayer1.setFill(Color.RED);
		tplayer1.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 25));
		Text tplayer2 = new Text("PLAYER2");
		tplayer2.setFill(Color.GREEN);
		tplayer2.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 25));
		
		Text rtitle = new Text("RULES");
		rtitle.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 25));
		Label rule1 = new Label("1. �� ������ Ư���� �������θ� �̵��� �� ������ ������ ����\n"
				+ "�� ������ ����.\n"
				+ "-��(��). �ڽ��� ���� �����ʿ� ���̴� ���� ��, �ڿ� ��, ���\n"
				+ "  �̵��� �����ϴ�.\n"
				+ "-��(��). �ڽ��� ���� ���ʿ� ���̸� �밢�� 4�������� �̵���\n"
				+ "  �� �ִ�.\n"
				+ "-��(��). �ڽ��� ���� �߾ӿ� ��ġ�ϸ� ��, ��, ��, ��, �밢��\n"
				+ "  ������� ��� �������� �̵��� �����ϴ�.\n"
				+ "-��(�). ���� �տ� ���̸� ������ �����θ� �̵��� �� �ִ�.\n"
				+ "-��(�)�� ��� ������ ���� ����� ��(��)�� ���ȴ�.\n"
				+ "  ��(��)�� �밢�� ���� ������ ������ �� �������� �̵��� ��\n"
				+ "  �ִ�.\n");
		Label rule2 = new Label("2. ������ ���۵Ǹ� �� �÷��̾���� �� 1���� 1ĭ �̵���ų �� \n"
				+ "�ִ�. ���� �̵����� ������ ���� ���� ���, �ش� ���� ����\n"
				+ "�� ��� �Ǹ� ���η� ���� ���� ���� �Ϻ��� �ڽ��� ���� �����\n"
				+ "�� �ִ�.\n");
		Label rule3 = new Label("3. ���� �ǿ� ���η� ���� ���� �������� �ൿ�� ���� �Ҹ��ϴ�\n"
				+ "���̸� �̹� ���� ������ ���̳� ����� �������� ���� ��������\n"
				+ "�� ����.\n");
		Label rule4 = new Label("4. ������ ��(��)�� ��� �ڽ��� ���� ����� ��쿡�� ��\n"
				+ "(�)�� ����� ����ؾ� �Ѵ�.\n");
		Label rule5 = new Label("5. ������ �� �÷��̾ ������ ��(��)�� ������ �ش� �÷���\n"
				+ "���� �¸��� ����ȴ�.\n");
		Label rule6 = new Label("6. ���� �ڽ��� ��(��)�� ������ ������ �� �ڽ��� ����\n"
				+ "�ٽ� ���ƿ� ������ �� ���� ��ƿ ��� �ش� �÷��̾��� �¸���\n"
				+ "������ ����ȴ�.\n");
		Label rule7 = new Label("7. �ڼ��� ������ ŷ����Ű��...");
		
		King1 king1 = new King1();
		King2 king2 = new King2();
		Elephant1 elephant1 = new Elephant1();
		Elephant2 elephant2 = new Elephant2();
		Rook1 rook1 = new Rook1();
		Rook2 rook2 = new Rook2();
		Pawn1 pawn1 = new Pawn1();
		Pawn2 pawn2 = new Pawn2();
		
		btRule.setOnAction(e->{
			lbPane.add(rtitle,0, 0);
			lbPane.add(rule1, 0, 2);
			lbPane.add(rule2, 0, 3);
			lbPane.add(rule3, 0, 4);
			lbPane.add(rule4, 0, 5);
			lbPane.add(rule5, 0, 6);
			lbPane.add(rule6, 0, 7);
			lbPane.add(rule7, 0, 8);
			lbPane.getChildren().removeAll(btStart,btRule);
			lbPane.add(btBack,0,9);
		}
		);
		
		btBack.setOnAction(e->{
			lbPane.getChildren().removeAll(btBack,rtitle,rule1,rule2,rule3,rule4,rule5,rule6,rule7);
			lbPane.getChildren().addAll(btStart,btRule);
		}
		);
		
		btStart.setOnAction(e->{
			lbPane.add(tplayer1,0,60);
			lbPane.add(tplayer2,0, 0);
			lbPane.getChildren().removeAll(btStart,btRule);
			
			king1.setLocation(1, 3);
			gameBoard.getChildren().add(king1.pane);
			king2.setLocation(1, 0);
			gameBoard.getChildren().add(king2.pane);
			elephant1.setLocation(0, 3);
			gameBoard.getChildren().add(elephant1.pane);
			elephant2.setLocation(2, 0);
			gameBoard.getChildren().add(elephant2.pane);
			rook1.setLocation(2, 3);
			gameBoard.getChildren().add(rook1.pane);
			rook2.setLocation(0, 0);
			gameBoard.getChildren().add(rook2.pane);
			pawn1.setLocation(1, 2);
			gameBoard.getChildren().add(pawn1.pane);
			pawn2.setLocation(1, 1);
			gameBoard.getChildren().add(pawn2.pane);
			
		}
		);
		
		king1.pane.setOnMouseClicked(e->{
			king1.setLocation(0, 2);
			gameBoard.getChildren().add(king1.pane);
		});
		
		king2.pane.setOnMouseClicked(e->{
			System.out.println("hello");
		});
		
		elephant1.pane.setOnMouseClicked(e->{
			System.out.println("hello");
		});
		
		elephant2.pane.setOnMouseClicked(e->{
			System.out.println("hello");
		});
		
		rook1.pane.setOnMouseClicked(e->{
			System.out.println("hello");
		});
		
		rook2.pane.setOnMouseClicked(e->{
			System.out.println("hello");
		});
		
		pawn1.pane.setOnMouseClicked(e->{
			System.out.println("hello");
		});
		
		pawn2.pane.setOnMouseClicked(e->{
			System.out.println("hello");
		});
		
		
		
		
		
		BorderPane bdPane = new BorderPane();
		bdPane.setPadding(new Insets(15,15,15,15));
		bdPane.setCenter(gameBoard);
		bdPane.setTop(deadSpace2);
		bdPane.setBottom(deadSpace1);
		bdPane.setRight(lbPane);
		bdPane.setStyle("-fx-background-color: rgb(255,223,128)");
		
		Scene scene = new Scene(bdPane,900,880);
		primaryStage.setTitle("�������");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	class Cell extends GridPane{
		boolean [][]isFilled = {{true,true,true},{false,true,false},{false,true,false},{true,true,true}};
		
		public Cell() {}

		public Cell(int row, int col, int squareSize) {
			for(int i = 0; i < col; i++) {
				for(int j = 0; j < row; j++) {
					Rectangle rec = new Rectangle(0,0,squareSize,squareSize);
					rec.setFill(Color.WHITE);
					rec.setStroke(Color.BLACK);
					rec.setOnMouseClicked(e->System.out.println("HEllo"));
					super.add(rec,i,j);
				}
			}
		}
		
		public boolean getFilled(int col, int row) {
			return isFilled[col][row];
		}
		
		public void setFilled(int col,int row) {
			isFilled[col][row] = !isFilled[col][row];
		}
		
		public void addPiece(StackPane p, int col, int row) {
			super.add(p,col,row);
		}
		
		
	}
	
	class King1 extends Cell{
		private boolean isClicked = false;
		private boolean isDead = false;
		private int row;
		private int col;
		StackPane pane = new StackPane();
		StackPane p = new StackPane();
		
		King1(){
			Rectangle rec = new Rectangle(0,0,130,130);
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
			Text text = new Text("��");
			text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			text.setFill(Color.RED);
			pane.getChildren().add(rec);
			pane.getChildren().add(text);
		}
		
		public void setLocation(int col, int row) {
			super.addPiece(pane, col, row);
			this.row = row;
			this.col = col;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}
		
		
	}
	
	class King2 extends Cell{
		private int isDead = 0;
		private int row;
		private int col;
		StackPane pane = new StackPane();
		
		King2(){
			Rectangle rec = new Rectangle(0,0,130,130);
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
			Text text = new Text("��");
			text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			text.setFill(Color.GREEN);
			text.setRotate(180);
			pane.getChildren().add(rec);
			pane.getChildren().add(text);
		}
		
		public void setLocation(int col, int row) {
			super.addPiece(pane, col, row);
			this.row = row;
			this.col = col;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}
		
	}
	
	class Elephant1 extends Cell{
		private int isDead = 0;
		private int row;
		private int col;
		StackPane pane = new StackPane();
		
		Elephant1(){
			Rectangle rec = new Rectangle(0,0,130,130);
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
			Text text = new Text("��");
			text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			text.setFill(Color.RED);
			pane.getChildren().add(rec);
			pane.getChildren().add(text);
		}
		
		public void setLocation(int col, int row) {
			super.addPiece(pane, col, row);
			this.row = row;
			this.col = col;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}
		
	}
	
	class Elephant2 extends Cell{
		private int isDead = 0;
		private int row;
		private int col;
		StackPane pane = new StackPane();
		
		Elephant2(){
			Rectangle rec = new Rectangle(0,0,130,130);
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
			Text text = new Text("��");
			text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			text.setFill(Color.GREEN);
			text.setRotate(180);
			pane.getChildren().add(rec);
			pane.getChildren().add(text);
		}
		
		public void setLocation(int col, int row) {
			super.addPiece(pane, col, row);
			this.row = row;
			this.col = col;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}
		
	}
	
	class Rook1 extends Cell{
		private int isDead = 0;
		private int row;
		private int col;
		StackPane pane = new StackPane();
		
		Rook1(){
			Rectangle rec = new Rectangle(0,0,130,130);
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
			Text text = new Text("��");
			text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			text.setFill(Color.RED);
			pane.getChildren().add(rec);
			pane.getChildren().add(text);
		}
		
		public void setLocation(int col, int row) {
			super.addPiece(pane, col, row);
			this.row = row;
			this.col = col;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}
		
	}
	
	class Rook2 extends Cell{
		private int isDead = 0;
		private int row;
		private int col;
		StackPane pane = new StackPane();
		
		Rook2(){
			Rectangle rec = new Rectangle(0,0,130,130);
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
			Text text = new Text("��");
			text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			text.setFill(Color.GREEN);
			text.setRotate(180);
			pane.getChildren().add(rec);
			pane.getChildren().add(text);
		}
		
		public void setLocation(int col, int row) {
			super.addPiece(pane, col, row);
			this.row = row;
			this.col = col;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}
		
	}
	
	class Pawn1 extends Cell{
		private int isDead = 0;
		private int row;
		private int col;
		StackPane pane = new StackPane();
		
		Pawn1(){
			Rectangle rec = new Rectangle(0,0,130,130);
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
			Text text = new Text("�");
			text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			text.setFill(Color.RED);
			pane.getChildren().add(rec);
			pane.getChildren().add(text);
		}
		
		public void setLocation(int col, int row) {
			super.addPiece(pane, col, row);
			this.row = row;
			this.col = col;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}
		
	}
	
	class Pawn2 extends Cell{
		private int isDead = 0;
		private int row;
		private int col;
		StackPane pane = new StackPane();
		
		Pawn2(){
			Rectangle rec = new Rectangle(0,0,130,130);
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
			Text text = new Text("�");
			text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			text.setFill(Color.GREEN);
			text.setRotate(180);
			pane.getChildren().add(rec);
			pane.getChildren().add(text);
		}
		
		public void setLocation(int col, int row) {
			super.addPiece(pane, col, row);
			this.row = row;
			this.col = col;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}
	}
	
	class ShowMoveable {
		StackPane pane = new StackPane();
		Circle circle = new Circle(20);
		
		ShowMoveable(){
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}

