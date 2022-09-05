package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
	//왕1,왕2,상1,상11,상2,상22,룩1,룩11,룩2,룩22,폰1,폰11,폰2,폰22,후1,후11,후2,후22
	private boolean []whoClicked = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	private boolean [][]Location1 = {{false,false,false},{false,false,false},{false,true,false},{true,true,true}};//플레이어1의 말이 어디에 존재하는지 확인
	private boolean [][]Location2 = {{true,true,true},{false,true,false},{false,false,false},{false,false,false}};//플레이어2의 말이 어디에 존재하는지 확인
	private boolean []dead1 = {false,false,false,false,false,false};
	private boolean []dead2 = {false,false,false,false,false,false};
	private int whoseTurn = 0;
	private int kingInOtherSide1 = 0;
	private int kingInOtherSide2 = 0;
	
	Cell gameBoard = new Cell(4,3,150);
	Cell deadSpace1 = new Cell(1,6,75);
	Cell deadSpace2 = new Cell(1,6,75);
	King1 king1 = new King1();
	King2 king2 = new King2();
	Elephant1 elephant1 = new Elephant1();
	Elephant1 elephant11 = new Elephant1();
	Elephant2 elephant2 = new Elephant2();
	Elephant2 elephant22 = new Elephant2();
	Rook1 rook1 = new Rook1();
	Rook1 rook11 = new Rook1();
	Rook2 rook2 = new Rook2();
	Rook2 rook22 = new Rook2();
	Pawn1 pawn1 = new Pawn1();
	Pawn1 pawn11 = new Pawn1();
	Pawn2 pawn2 = new Pawn2();
	Pawn2 pawn22 = new Pawn2();
	Hoo1 hoo1 = new Hoo1();		
	Hoo1 hoo11 = new Hoo1();
	Hoo2 hoo2 = new Hoo2();
	Hoo2 hoo22 = new Hoo2();
	ShowCircle circle1 = new ShowCircle(0,0);
	ShowCircle circle2 = new ShowCircle(1,0);
	ShowCircle circle3 = new ShowCircle(2,0);
	ShowCircle circle4 = new ShowCircle(0,1);
	ShowCircle circle5 = new ShowCircle(1,1);
	ShowCircle circle6 = new ShowCircle(2,1);
	ShowCircle circle7 = new ShowCircle(0,2);
	ShowCircle circle8 = new ShowCircle(1,2);
	ShowCircle circle9 = new ShowCircle(2,2);
	ShowCircle circle10 = new ShowCircle(0,3);
	ShowCircle circle11 = new ShowCircle(1,3);
	ShowCircle circle12 = new ShowCircle(2,3);
	BorderPane bdPane = new BorderPane();
	GridPane lbPane = new GridPane();
	Text turn = new Text("Player1's turn");
	
	@Override
	public void start(Stage primaryStage) {
		gameBoard.setAlignment(Pos.CENTER_LEFT);
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
		turn.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 30));
		
		
		Text rtitle = new Text("RULES");
		rtitle.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 25));
		Label rule1 = new Label("1. 각 말들은 특정한 방향으로만 이동할 수 있으며 각각의 역할\n"
				+ "은 다음과 같다.\n"
				+ "-장(將). 자신의 진영 오른쪽에 놓이는 말로 앞, 뒤와 좌, 우로\n"
				+ "  이동이 가능하다.\n"
				+ "-상(相). 자신의 진영 왼쪽에 놓이며 대각선 4방향으로 이동할\n"
				+ "  수 있다.\n"
				+ "-왕(王). 자신의 진영 중앙에 위치하며 앞, 뒤, 좌, 우, 대각선\n"
				+ "  방향까지 모든 방향으로 이동이 가능하다.\n"
				+ "-자(子). 왕의 앞에 놓이며 오로지 앞으로만 이동할 수 있다.\n"
				+ "-자(子)는 상대 진영에 들어가면 뒤집어서 후(侯)로 사용된다.\n"
				+ "  후(侯)는 대각선 뒤쪽 방향을 제외한 전 방향으로 이동할 수\n"
				+ "  있다.\n");
		Label rule2 = new Label("2. 게임이 시작되면 선 플레이어부터 말 1개를 1칸 이동시킬 수 \n"
				+ "있다. 말을 이동시켜 상대방의 말을 잡은 경우, 해당 말을 포로\n"
				+ "로 잡게 되며 포로로 잡은 말은 다음 턴부터 자신의 말로 사용할\n"
				+ "수 있다.\n");
		Label rule3 = new Label("3. 게임 판에 포로로 잡은 말을 내려놓는 행동도 턴을 소모하는\n"
				+ "것이며 이미 말이 놓여진 곳이나 상대의 진영에는 말을 내려놓을\n"
				+ "수 없다.\n");
		Label rule4 = new Label("4. 상대방의 후(侯)를 잡아 자신의 말로 사용할 경우에는 자\n"
				+ "(子)로 뒤집어서 사용해야 한다.\n");
		Label rule5 = new Label("5. 게임은 한 플레이어가 상대방의 왕(王)을 잡으면 해당 플레이\n"
				+ "어의 승리로 종료된다.\n");
		Label rule6 = new Label("6. 만약 자신의 왕(王)이 상대방의 진영에 들어가 자신의 턴이\n"
				+ "다시 돌아올 때까지 한 턴을 버틸 경우 해당 플레이어의 승리로\n"
				+ "게임이 종료된다.\n");
		Label rule7 = new Label("7. 자세한 사항은 킹무위키로...");
		
		
		
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
			lbPane.add(turn, 0, 30);
			lbPane.getChildren().removeAll(btStart,btRule);
			
			king1.setLocation(1, 3);
			king1.onBoard=true;
			gameBoard.getChildren().add(king1.pane);
			king2.setLocation(1, 0);
			king2.onBoard=true;
			gameBoard.getChildren().add(king2.pane);
			elephant1.setLocation(0, 3);
			elephant1.onBoard=true;
			gameBoard.getChildren().add(elephant1.pane);
			elephant2.setLocation(2, 0);
			elephant2.onBoard=true;
			gameBoard.getChildren().add(elephant2.pane);
			rook1.setLocation(2, 3);
			rook1.onBoard=true;
			gameBoard.getChildren().add(rook1.pane);
			rook2.setLocation(0, 0);
			rook2.onBoard=true;
			gameBoard.getChildren().add(rook2.pane);
			pawn1.setLocation(1, 2);
			pawn1.onBoard=true;
			gameBoard.getChildren().add(pawn1.pane);
			pawn2.setLocation(1, 1);
			pawn2.onBoard=true;
			gameBoard.getChildren().add(pawn2.pane);
			
		}
		);
		
		king1.pane.setOnMouseClicked(e->{
			if(!whoClicked[0]&& whoseTurn%2==0) {
				whoClicked[0] = true;
				moveable();
			}
			else {
				removeCircle();
				for(int i=0;i<18;i++) {
					whoClicked[i] = false;
				}
			}
		});
		
		king2.pane.setOnMouseClicked(e->{
			if(!whoClicked[1]&& whoseTurn%2==1) {
				whoClicked[1] = true;
				moveable();
			}
			else {
				removeCircle();
				for(int i=0;i<18;i++) {
					whoClicked[i] = false;
				}
			}
		});
		
		elephant1.pane.setOnMouseClicked(e->{
			if(!elephant1.isDead) {
				if(!whoClicked[2]&& whoseTurn%2==0) {
					whoClicked[2] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[2]&& whoseTurn%2==0) {
					whoClicked[2] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		elephant11.pane.setOnMouseClicked(e->{
			if(!elephant11.isDead) {
				if(!whoClicked[3]&& whoseTurn%2==0) {
					whoClicked[3] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[3]&& whoseTurn%2==0) {
					whoClicked[3] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		elephant2.pane.setOnMouseClicked(e->{
			if(!elephant2.isDead) {
				if(!whoClicked[4]&& whoseTurn%2==1) {
					whoClicked[4] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[4]&& whoseTurn%2==1) {
					whoClicked[4] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		elephant22.pane.setOnMouseClicked(e->{
			if(!elephant22.isDead) {
				if(!whoClicked[5]&& whoseTurn%2==1) {
					whoClicked[5] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[5]&& whoseTurn%2==1) {
					whoClicked[5] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		rook1.pane.setOnMouseClicked(e->{
			if(!rook1.isDead) {
				if(!whoClicked[6]&& whoseTurn%2==0) {
					whoClicked[6] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[6]&& whoseTurn%2==0) {
					whoClicked[6] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		rook11.pane.setOnMouseClicked(e->{
			if(!rook11.isDead) {
				if(!whoClicked[7]&& whoseTurn%2==0) {
					whoClicked[7] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[7]&& whoseTurn%2==0) {
					whoClicked[7] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		rook2.pane.setOnMouseClicked(e->{
			if(!rook2.isDead) {
				if(!whoClicked[8]&& whoseTurn%2==1) {
					whoClicked[8] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[8]&& whoseTurn%2==1) {
					whoClicked[8] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		rook22.pane.setOnMouseClicked(e->{
			if(!rook22.isDead) {
				if(!whoClicked[9]&& whoseTurn%2==1) {
					whoClicked[9] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[9]&& whoseTurn%2==1) {
					whoClicked[9] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		pawn1.pane.setOnMouseClicked(e->{
			if(!pawn1.isDead) {
				if(!whoClicked[10]&& whoseTurn%2==0) {
					whoClicked[10] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[10]&& whoseTurn%2==0) {
					whoClicked[10] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		pawn11.pane.setOnMouseClicked(e->{
			if(!pawn11.isDead) {
				if(!whoClicked[11]&& whoseTurn%2==0) {
					whoClicked[11] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[11]&& whoseTurn%2==0) {
					whoClicked[11] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		pawn2.pane.setOnMouseClicked(e->{
			if(!pawn2.isDead) {
				if(!whoClicked[12]&& whoseTurn%2==1) {
					whoClicked[12] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[12]&& whoseTurn%2==1) {
					whoClicked[12] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		pawn22.pane.setOnMouseClicked(e->{
			if(!pawn22.isDead) {
				if(!whoClicked[13]&& whoseTurn%2==1) {
					whoClicked[13] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[13]&& whoseTurn%2==1) {
					whoClicked[13] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		hoo1.pane.setOnMouseClicked(e->{
			if(!hoo1.isDead) {
				if(!whoClicked[14]&& whoseTurn%2==0) {
					whoClicked[14] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[14]&& whoseTurn%2==0) {
					whoClicked[14] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		hoo11.pane.setOnMouseClicked(e->{
			if(!hoo11.isDead) {
				if(!whoClicked[15]&& whoseTurn%2==0) {
					whoClicked[15] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[15]&& whoseTurn%2==0) {
					whoClicked[15] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		hoo2.pane.setOnMouseClicked(e->{
			if(!hoo2.isDead) {
				if(!whoClicked[16]&& whoseTurn%2==1) {
					whoClicked[16] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[16]&& whoseTurn%2==1) {
					whoClicked[16] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		hoo22.pane.setOnMouseClicked(e->{
			if(!hoo22.isDead) {
				if(!whoClicked[17]&& whoseTurn%2==1) {
					whoClicked[17] = true;
					moveable();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
			else {
				if(!whoClicked[17]&& whoseTurn%2==1) {
					whoClicked[17] = true;
					emptyBoard();
				}
				else {
					removeCircle();
					for(int i=0;i<18;i++) {
						whoClicked[i] = false;
					}
				}
			}
		});
		
		circle1.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),0);
			for(int i=0;i<18;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle2.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),1);
			for(int i=0;i<18;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle3.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),2);
			for(int i=0;i<18;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle4.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),3);
			for(int i=0;i<18;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle5.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),4);
			for(int i=0;i<18;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle6.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),5);
			for(int i=0;i<18;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle7.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),6);
			for(int i=0;i<18;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle8.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),7);
			for(int i=0;i<18;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle9.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),8);
			for(int i=0;i<18;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle10.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),9);
			for(int i=0;i<18;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle11.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),10);
			for(int i=0;i<18;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle12.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),11);
			for(int i=0;i<18;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		bdPane.setPadding(new Insets(15,15,15,15));
		bdPane.setCenter(gameBoard);
		bdPane.setTop(deadSpace2);
		bdPane.setBottom(deadSpace1);
		bdPane.setRight(lbPane);
		bdPane.setStyle("-fx-background-color: rgb(255,223,128)");
		
		Scene scene = new Scene(bdPane,900,880);
		primaryStage.setTitle("십이장기");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void setText() {
		if(whoseTurn%2==0) {
			turn.setText("Player1's Turn");
		}
		else {
			turn.setText("Player2's Turn");
		}
			
	}
	
	public void addCircle(int num) {
		switch(num) {
		case 1:
			gameBoard.getChildren().add(circle1.pane);
			break;
		case 2:
			gameBoard.getChildren().add(circle2.pane);
			break;
		case 3:
			gameBoard.getChildren().add(circle3.pane);
			break;
		case 4:
			gameBoard.getChildren().add(circle4.pane);
			break;
		case 5:
			gameBoard.getChildren().add(circle5.pane);
			break;
		case 6:
			gameBoard.getChildren().add(circle6.pane);
			break;
		case 7:
			gameBoard.getChildren().add(circle7.pane);
			break;
		case 8:
			gameBoard.getChildren().add(circle8.pane);
			break;
		case 9:
			gameBoard.getChildren().add(circle9.pane);
			break;
		case 10:
			gameBoard.getChildren().add(circle10.pane);
			break;
		case 11:
			gameBoard.getChildren().add(circle11.pane);
			break;
		case 12:
			gameBoard.getChildren().add(circle12.pane);
			break;
			
		}
	}
	
	public void removeCircle() {
		gameBoard.getChildren().removeAll(circle1.pane,circle2.pane,circle3.pane,circle4.pane,
				circle5.pane,circle6.pane,circle7.pane,circle8.pane, 
				circle9.pane,circle10.pane,circle11.pane,circle12.pane);
	}
	
	//어떤 기물이 클릭했는지 확인
	public int findWhoClicked() {
		int num = 0;
		for(int i=0;i<18;i++) {
			if(whoClicked[i])
				num=i;
		}
		return num;
	}
	
	//원을 눌렀을 때 움직이는 함수
	public void circleClicked(int num1, int num2) {
		switch(num1) {
		case 0:
			Location1[king1.row][king1.col]=false;
			Location1[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(king1.pane);
			king1.setLocation(num2%3, num2/3);
			checkKing();
			checkDie();
			isWin();
			gameBoard.getChildren().add(king1.pane);
			break;
		case 1:
			Location2[king2.row][king2.col]=false;
			Location2[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(king2.pane);
			king2.setLocation(num2%3, num2/3);
			checkKing();
			checkDie();
			isWin();
			gameBoard.getChildren().add(king2.pane);
			break;
		case 2:
			if(elephant1.isDead) {
				elephant1.isDead=false;
				dead1[elephant1.col]=false;
				elephant1.onBoard=true;
				elephant1.setSize(elephant1.isDead);
			}
			else {
				Location1[elephant1.row][elephant1.col]=false;
			}
			Location1[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(elephant1.pane);
			elephant1.setLocation(num2%3, num2/3);
			checkKing();
			checkDie();
			isWin();
			gameBoard.getChildren().add(elephant1.pane);
			break;
		case 3:
			if(elephant11.isDead) {
				elephant11.isDead=false;
				dead1[elephant11.col]=false;
				elephant11.onBoard=true;
				elephant11.setSize(elephant11.isDead);
			}
			else {
				Location1[elephant11.row][elephant11.col]=false;
			}
			Location1[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(elephant11.pane);
			elephant11.setLocation(num2%3, num2/3);
			checkKing();
			checkDie();
			isWin();
			gameBoard.getChildren().add(elephant11.pane);
			break;
		case 4:
			if(elephant2.isDead) {
				elephant2.isDead=false;
				dead2[elephant2.col]=false;
				elephant2.onBoard=true;
				elephant2.setSize(elephant2.isDead);
			}
			else {
				Location2[elephant2.row][elephant2.col]=false;
			}
			Location2[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(elephant2.pane);
			elephant2.setLocation(num2%3, num2/3);
			checkKing();
			checkDie();
			isWin();
			gameBoard.getChildren().add(elephant2.pane);
			break;
		case 5:
			if(elephant22.isDead) {
				elephant22.isDead=false;
				dead2[elephant22.col]=false;
				elephant22.onBoard=true;
				elephant22.setSize(elephant22.isDead);
			}
			else {
				Location2[elephant22.row][elephant22.col]=false;
			}
			Location2[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(elephant22.pane);
			elephant22.setLocation(num2%3, num2/3);
			checkKing();
			checkDie();
			isWin();
			gameBoard.getChildren().add(elephant22.pane);
			break;
		case 6:
			if(rook1.isDead) {
				rook1.isDead=false;
				dead1[rook1.col]=false;
				rook1.onBoard=true;
				rook1.setSize(rook1.isDead);
			}
			else {
				Location1[rook1.row][rook1.col]=false;
			}
			Location1[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(rook1.pane);
			rook1.setLocation(num2%3, num2/3);
			checkKing();
			checkDie();
			isWin();
			gameBoard.getChildren().add(rook1.pane);
			break;
		case 7:
			if(rook11.isDead) {
				rook11.isDead=false;
				dead1[rook11.col]=false;
				rook11.onBoard=true;
				rook11.setSize(rook11.isDead);
			}
			else {
				Location1[rook11.row][rook11.col]=false;
			}
			Location1[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(rook11.pane);
			rook11.setLocation(num2%3, num2/3);
			checkKing();
			checkDie();
			isWin();
			gameBoard.getChildren().add(rook11.pane);
			break;
		case 8:
			if(rook2.isDead) {
				rook2.isDead=false;
				dead2[rook2.col]=false;
				rook2.onBoard=true;
				rook2.setSize(rook2.isDead);
			}
			else {
				Location2[rook2.row][rook2.col]=false;
			}
			Location2[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(rook2.pane);
			rook2.setLocation(num2%3, num2/3);
			checkKing();
			checkDie();
			isWin();
			gameBoard.getChildren().add(rook2.pane);
			break;
		case 9:
			if(rook22.isDead) {
				rook22.isDead=false;
				dead2[rook22.col]=false;
				rook22.onBoard=true;
				rook22.setSize(rook22.isDead);
			}
			else {
				Location2[rook22.row][rook22.col]=false;
			}
			Location2[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(rook22.pane);
			rook22.setLocation(num2%3, num2/3);
			checkKing();
			checkDie();
			isWin();
			gameBoard.getChildren().add(rook22.pane);
			break;
		case 10:
			if(pawn1.isDead) {
				pawn1.isDead=false;
				dead1[pawn1.col]=false;
				pawn1.onBoard=true;
				pawn1.setSize(pawn1.isDead);
			}
			else {
				Location1[pawn1.row][pawn1.col]=false;
			}
			Location1[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(pawn1.pane);
			checkKing();
			checkDie();
			isWin();
			pawn1.setLocation(num2%3, num2/3);
			if(pawn1.row!=0) {
				gameBoard.getChildren().add(pawn1.pane);
			}
			else {
				pawn1.onBoard=false;
				hoo1.onBoard=true;
				hoo1.setLocation(num2%3, num2/3);
				gameBoard.getChildren().add(hoo1.pane);
			}
			break;
		case 11:
			if(pawn11.isDead) {
				pawn11.isDead=false;
				dead1[pawn11.col]=false;
				pawn11.onBoard=true;
				pawn11.setSize(pawn11.isDead);
			}
			else {
				Location1[pawn11.row][pawn11.col]=false;
			}
			Location1[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(pawn11.pane);
			checkKing();
			checkDie();
			isWin();
			pawn11.setLocation(num2%3, num2/3);
			if(pawn11.row!=0) {
				gameBoard.getChildren().add(pawn11.pane);
			}
			else {
				pawn11.onBoard=false;
				hoo11.onBoard=true;
				hoo11.setLocation(num2%3, num2/3);
				gameBoard.getChildren().add(hoo11.pane);
			}
			break;
		case 12:
			if(pawn2.isDead) {
				pawn2.isDead=false;
				dead2[pawn2.col]=false;
				pawn2.onBoard=true;
				pawn2.setSize(pawn2.isDead);
			}
			else {
				Location2[pawn2.row][pawn2.col]=false;
			}
			Location2[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(pawn2.pane);
			checkKing();
			checkDie();
			isWin();
			pawn2.setLocation(num2%3, num2/3);
			if(pawn2.row!=3) {
				gameBoard.getChildren().add(pawn2.pane);
			}
			else {
				pawn2.onBoard=false;
				hoo2.onBoard=true;
				hoo2.setLocation(num2%3, num2/3);
				gameBoard.getChildren().add(hoo2.pane);
			}
			break;
		case 13:
			if(pawn22.isDead) {
				pawn22.isDead=false;
				dead2[pawn22.col]=false;
				pawn22.onBoard=true;
				pawn22.setSize(pawn22.isDead);
			}
			else {
				Location2[pawn22.row][pawn22.col]=false;
			}
			Location2[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(pawn22.pane);
			checkKing();
			checkDie();
			isWin();
			pawn22.setLocation(num2%3, num2/3);
			if(pawn22.row!=3) {
				gameBoard.getChildren().add(pawn22.pane);
			}
			else {
				pawn22.onBoard=false;
				hoo22.onBoard=true;
				hoo22.setLocation(num2%3, num2/3);
				gameBoard.getChildren().add(hoo22.pane);
			}
			break;
		case 14:
			Location1[hoo1.row][hoo1.col]=false;
			Location1[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(hoo1.pane);
			hoo1.setLocation(num2%3, num2/3);
			checkKing();
			checkDie();
			isWin();
			gameBoard.getChildren().add(hoo1.pane);
			break;
		case 15:
			Location1[hoo11.row][hoo11.col]=false;
			Location1[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(hoo11.pane);
			hoo11.setLocation(num2%3, num2/3);
			checkKing();
			checkDie();
			isWin();
			gameBoard.getChildren().add(hoo11.pane);
			break;
		case 16:
			Location2[hoo2.row][hoo2.col]=false;
			Location2[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(hoo2.pane);
			hoo2.setLocation(num2%3, num2/3);
			checkKing();
			checkDie();
			isWin();
			gameBoard.getChildren().add(hoo2.pane);
			break;
		case 17:
			Location2[hoo22.row][hoo22.col]=false;
			Location2[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(hoo22.pane);
			hoo22.setLocation(num2%3, num2/3);
			checkKing();
			checkDie();
			isWin();
			gameBoard.getChildren().add(hoo22.pane);
			break;
		}
	}
	
	//움직일수 있는 위치 표시하는 함수
	public void moveable() {
		int num = 1;
		for(int i=0;i<18;i++) {
			if(whoClicked[i])
				num=i;
		}
		
		switch(num) {
		//플레이어1 왕
		case 0:
			if((king1.col==0)&&(king1.row==3)) {
				if(!Location1[2][0])
					addCircle(7);
				if(!Location1[2][1])
					addCircle(8);
				if(!Location1[3][1])
					addCircle(11);
				
			}
			else if((king1.col==1)&&(king1.row==3)) {
				if(!Location1[2][0])
					addCircle(7);
				if(!Location1[2][1])
					addCircle(8);
				if(!Location1[2][2])
					addCircle(9);
				if(!Location1[3][0])
					addCircle(10);
				if(!Location1[3][2])
					addCircle(12);
			}
			else if((king1.col==2)&&(king1.row==3)) {
				if(!Location1[2][1])
					addCircle(8);
				if(!Location1[2][2])
					addCircle(9);
				if(!Location1[3][1])
					addCircle(11);
			}
			else if((king1.col==0)&&(king1.row==0)) {
				if(!Location1[0][1])
					addCircle(2);
				if(!Location1[1][0])
					addCircle(4);
				if(!Location1[1][1])
					addCircle(5);
			}
			else if((king1.col==1)&&(king1.row==0)) {
				if(!Location1[0][0])
					addCircle(1);
				if(!Location1[0][2])
					addCircle(3);
				if(!Location1[1][0])
					addCircle(4);
				if(!Location1[1][1])
					addCircle(5);
				if(!Location1[1][2])
					addCircle(6);
			}
			else if((king1.col==2)&&(king1.row==0)) {
				if(!Location1[0][1])
					addCircle(2);
				if(!Location1[1][1])
					addCircle(5);
				if(!Location1[1][2])
					addCircle(6);
			}
			else if((king1.col==0)&&(king1.row<3)&&(king1.row>0)) {
				if(!Location1[king1.row-1][king1.col])
					addCircle(3*(king1.row-1)+king1.col+1);
				if(!Location1[king1.row-1][king1.col+1])
					addCircle(3*(king1.row-1)+king1.col+2);
				if(!Location1[king1.row][king1.col+1])
					addCircle(3*(king1.row)+king1.col+2);
				if(!Location1[king1.row+1][king1.col+1])
					addCircle(3*(king1.row+1)+king1.col+2);
				if(!Location1[king1.row+1][king1.col])
					addCircle(3*(king1.row+1)+king1.col+1);
			}
			else if((king1.col==2)&&(king1.row<3)&&(king1.row>0)) {
				if(!Location1[king1.row-1][king1.col])
					addCircle(3*(king1.row-1)+king1.col+1);
				if(!Location1[king1.row-1][king1.col-1])
					addCircle(3*(king1.row-1)+king1.col);
				if(!Location1[king1.row][king1.col-1])
					addCircle(3*(king1.row)+king1.col);
				if(!Location1[king1.row+1][king1.col-1])
					addCircle(3*(king1.row+1)+king1.col);
				if(!Location1[king1.row+1][king1.col])
					addCircle(3*(king1.row+1)+king1.col+1);
			}
			else {
				if(!Location1[king1.row+1][king1.col])
					addCircle(3*(king1.row+1)+king1.col+1);
				if(!Location1[king1.row][king1.col+1])
					addCircle(3*(king1.row)+king1.col+2);
				if(!Location1[king1.row-1][king1.col])
					addCircle(3*(king1.row-1)+king1.col+1);
				if(!Location1[king1.row][king1.col-1])
					addCircle(3*(king1.row)+king1.col);
				if(!Location1[king1.row+1][king1.col+1])
					addCircle(3*(king1.row+1)+king1.col+2);
				if(!Location1[king1.row-1][king1.col-1])
					addCircle(3*(king1.row-1)+king1.col);
				if(!Location1[king1.row+1][king1.col-1])
					addCircle(3*(king1.row+1)+king1.col);
				if(!Location1[king1.row-1][king1.col+1])
					addCircle(3*(king1.row-1)+king1.col+2);
			}
			break;
		//플레이어2 왕
		case 1:
			if((king2.col==0)&&(king2.row==3)) {
				if(!Location2[2][0])
					addCircle(7);
				if(!Location2[2][1])
					addCircle(8);
				if(!Location2[3][1])
					addCircle(11);
				
			}
			else if((king2.col==1)&&(king2.row==3)) {
				if(!Location2[2][0])
					addCircle(7);
				if(!Location2[2][1])
					addCircle(8);
				if(!Location2[2][2])
					addCircle(9);
				if(!Location2[3][0])
					addCircle(10);
				if(!Location2[3][2])
					addCircle(12);
			}
			else if((king2.col==2)&&(king2.row==3)) {
				if(!Location2[2][1])
					addCircle(8);
				if(!Location2[2][2])
					addCircle(9);
				if(!Location2[3][1])
					addCircle(11);
			}
			else if((king2.col==0)&&(king2.row==0)) {
				if(!Location2[0][1])
					addCircle(2);
				if(!Location2[1][0])
					addCircle(4);
				if(!Location2[1][1])
					addCircle(5);
			}
			else if((king2.col==1)&&(king2.row==0)) {
				if(!Location2[0][0])
					addCircle(1);
				if(!Location2[0][2])
					addCircle(3);
				if(!Location2[1][0])
					addCircle(4);
				if(!Location2[1][1])
					addCircle(5);
				if(!Location2[1][2])
					addCircle(6);
			}
			else if((king2.col==2)&&(king2.row==0)) {
				if(!Location2[0][1])
					addCircle(2);
				if(!Location2[1][1])
					addCircle(5);
				if(!Location2[1][2])
					addCircle(6);
			}
			else if((king2.col==0)&&(king2.row<3)&&(king2.row>0)) {
				if(!Location2[king2.row-1][king2.col])
					addCircle(3*(king2.row-1)+king2.col+1);
				if(!Location2[king2.row-1][king2.col+1])
					addCircle(3*(king2.row-1)+king2.col+2);
				if(!Location2[king2.row][king2.col+1])
					addCircle(3*(king2.row)+king2.col+2);
				if(!Location2[king2.row+1][king2.col+1])
					addCircle(3*(king2.row+1)+king2.col+2);
				if(!Location2[king2.row+1][king2.col])
					addCircle(3*(king2.row+1)+king2.col+1);
			}
			else if((king2.col==2)&&(king2.row<3)&&(king2.row>0)) {
				if(!Location2[king2.row-1][king2.col])
					addCircle(3*(king2.row-1)+king2.col+1);
				if(!Location2[king2.row-1][king2.col-1])
					addCircle(3*(king2.row-1)+king2.col);
				if(!Location2[king2.row][king2.col-1])
					addCircle(3*(king2.row)+king2.col);
				if(!Location2[king2.row+1][king2.col-1])
					addCircle(3*(king2.row+1)+king2.col);
				if(!Location2[king2.row+1][king2.col])
					addCircle(3*(king2.row+1)+king2.col+1);
			}
			else {
				if(!Location2[king2.row+1][king2.col])
					addCircle(3*(king2.row+1)+king2.col+1);
				if(!Location2[king2.row][king2.col+1])
					addCircle(3*(king2.row)+king2.col+2);
				if(!Location2[king2.row-1][king2.col])
					addCircle(3*(king2.row-1)+king2.col+1);
				if(!Location2[king2.row][king2.col-1])
					addCircle(3*(king2.row)+king2.col);
				if(!Location2[king2.row+1][king2.col+1])
					addCircle(3*(king2.row+1)+king2.col+2);
				if(!Location2[king2.row-1][king2.col-1])
					addCircle(3*(king2.row-1)+king2.col);
				if(!Location2[king2.row+1][king2.col-1])
					addCircle(3*(king2.row+1)+king2.col);
				if(!Location2[king2.row-1][king2.col+1])
					addCircle(3*(king2.row-1)+king2.col+2);
			}
			break;
		//플레이어1 상
		case 2:
			if(((elephant1.col==0)||(elephant1.col==2))&&(elephant1.row==0)) {
				if(!Location1[1][1])
					addCircle(5);
			}
			else if((elephant1.col==1)&&(elephant1.row==0)) {
				if(!Location1[1][0])
					addCircle(4);
				if(!Location1[1][2])
					addCircle(6);
			}
			else if(((elephant1.col==0)||(elephant1.col==2))&&(elephant1.row==3)) {
				if(!Location1[2][1])
					addCircle(8);
			}
			else if((elephant1.col==1)&&(elephant1.row==3)) {
				if(!Location1[2][0])
					addCircle(7);
				if(!Location1[2][2])
					addCircle(9);
			}
			else if((elephant1.col==0)&&(elephant1.row>0)&&(elephant1.row<3)) {
				if(!Location1[elephant1.row-1][elephant1.col+1])
					addCircle(3*(elephant1.row-1)+elephant1.col+2);
				if(!Location1[elephant1.row+1][elephant1.col+1])
					addCircle(3*(elephant1.row+1)+elephant1.col+2);
			}
			else if((elephant1.col==2)&&(elephant1.row>0)&&(elephant1.row<3)) {
				if(!Location1[elephant1.row-1][elephant1.col-1])
					addCircle(3*(elephant1.row-1)+elephant1.col);
				if(!Location1[elephant1.row+1][elephant1.col-1])
					addCircle(3*(elephant1.row+1)+elephant1.col);
			}
			else {
				if(!Location1[elephant1.row-1][elephant1.col-1])
					addCircle(3*(elephant1.row-1)+elephant1.col);
				if(!Location1[elephant1.row+1][elephant1.col-1])
					addCircle(3*(elephant1.row+1)+elephant1.col);
				if(!Location1[elephant1.row-1][elephant1.col+1])
					addCircle(3*(elephant1.row-1)+elephant1.col+2);
				if(!Location1[elephant1.row+1][elephant1.col+1])
					addCircle(3*(elephant1.row+1)+elephant1.col+2);
			}
			break;
		case 3:
			if(((elephant11.col==0)||(elephant11.col==2))&&(elephant11.row==0)) {
				if(!Location1[1][1])
					addCircle(5);
			}
			else if((elephant11.col==1)&&(elephant11.row==0)) {
				if(!Location1[1][0])
					addCircle(4);
				if(!Location1[1][2])
					addCircle(6);
			}
			else if(((elephant11.col==0)||(elephant11.col==2))&&(elephant11.row==3)) {
				if(!Location1[2][1])
					addCircle(8);
			}
			else if((elephant11.col==1)&&(elephant11.row==3)) {
				if(!Location1[2][0])
					addCircle(7);
				if(!Location1[2][2])
					addCircle(9);
			}
			else if((elephant11.col==0)&&(elephant11.row>0)&&(elephant11.row<3)) {
				if(!Location1[elephant11.row-1][elephant11.col+1])
					addCircle(3*(elephant11.row-1)+elephant11.col+2);
				if(!Location1[elephant11.row+1][elephant11.col+1])
					addCircle(3*(elephant11.row+1)+elephant11.col+2);
			}
			else if((elephant11.col==2)&&(elephant11.row>0)&&(elephant11.row<3)) {
				if(!Location1[elephant11.row-1][elephant11.col-1])
					addCircle(3*(elephant11.row-1)+elephant11.col);
				if(!Location1[elephant11.row+1][elephant11.col-1])
					addCircle(3*(elephant11.row+1)+elephant11.col);
			}
			else {
				if(!Location1[elephant11.row-1][elephant11.col-1])
					addCircle(3*(elephant11.row-1)+elephant11.col);
				if(!Location1[elephant11.row+1][elephant11.col-1])
					addCircle(3*(elephant11.row+1)+elephant11.col);
				if(!Location1[elephant11.row-1][elephant11.col+1])
					addCircle(3*(elephant11.row-1)+elephant11.col+2);
				if(!Location1[elephant11.row+1][elephant11.col+1])
					addCircle(3*(elephant11.row+1)+elephant11.col+2);
			}
			break;
		//플레이어2 상
		case 4:
			if(((elephant2.col==0)||(elephant2.col==2))&&(elephant2.row==0)) {
				if(!Location2[1][1])
					addCircle(5);
			}
			else if((elephant2.col==1)&&(elephant2.row==0)) {
				if(!Location2[1][0])
					addCircle(4);
				if(!Location2[1][2])
					addCircle(6);
			}
			else if(((elephant2.col==0)||(elephant2.col==2))&&(elephant2.row==3)) {
				if(!Location2[2][1])
					addCircle(8);
			}
			else if((elephant2.col==1)&&(elephant2.row==3)) {
				if(!Location2[2][0])
					addCircle(7);
				if(!Location2[2][2])
					addCircle(9);
			}
			else if((elephant2.col==0)&&(elephant2.row>0)&&(elephant2.row<3)) {
				if(!Location2[elephant2.row-1][elephant2.col+1])
					addCircle(3*(elephant2.row-1)+elephant2.col+2);
				if(!Location2[elephant2.row+1][elephant2.col+1])
					addCircle(3*(elephant2.row+1)+elephant2.col+2);
			}
			else if((elephant2.col==2)&&(elephant2.row>0)&&(elephant2.row<3)) {
				if(!Location2[elephant2.row-1][elephant2.col-1])
					addCircle(3*(elephant2.row-1)+elephant2.col);
				if(!Location2[elephant2.row+1][elephant2.col-1])
					addCircle(3*(elephant2.row+1)+elephant2.col);
			}
			else {
				if(!Location2[elephant2.row-1][elephant2.col-1])
					addCircle(3*(elephant2.row-1)+elephant2.col);
				if(!Location2[elephant2.row+1][elephant2.col-1])
					addCircle(3*(elephant2.row+1)+elephant2.col);
				if(!Location2[elephant2.row-1][elephant2.col+1])
					addCircle(3*(elephant2.row-1)+elephant2.col+2);
				if(!Location2[elephant2.row+1][elephant2.col+1])
					addCircle(3*(elephant2.row+1)+elephant2.col+2);
			}
			break;
		case 5:
			if(((elephant22.col==0)||(elephant22.col==2))&&(elephant22.row==0)) {
				if(!Location2[1][1])
					addCircle(5);
			}
			else if((elephant22.col==1)&&(elephant22.row==0)) {
				if(!Location2[1][0])
					addCircle(4);
				if(!Location2[1][2])
					addCircle(6);
			}
			else if(((elephant22.col==0)||(elephant22.col==2))&&(elephant22.row==3)) {
				if(!Location2[2][1])
					addCircle(8);
			}
			else if((elephant22.col==1)&&(elephant22.row==3)) {
				if(!Location2[2][0])
					addCircle(7);
				if(!Location2[2][2])
					addCircle(9);
			}
			else if((elephant22.col==0)&&(elephant22.row>0)&&(elephant22.row<3)) {
				if(!Location2[elephant22.row-1][elephant22.col+1])
					addCircle(3*(elephant22.row-1)+elephant22.col+2);
				if(!Location2[elephant22.row+1][elephant22.col+1])
					addCircle(3*(elephant22.row+1)+elephant22.col+2);
			}
			else if((elephant22.col==2)&&(elephant22.row>0)&&(elephant22.row<3)) {
				if(!Location2[elephant22.row-1][elephant22.col-1])
					addCircle(3*(elephant22.row-1)+elephant22.col);
				if(!Location2[elephant22.row+1][elephant22.col-1])
					addCircle(3*(elephant22.row+1)+elephant22.col);
			}
			else {
				if(!Location2[elephant22.row-1][elephant22.col-1])
					addCircle(3*(elephant22.row-1)+elephant22.col);
				if(!Location2[elephant22.row+1][elephant22.col-1])
					addCircle(3*(elephant22.row+1)+elephant22.col);
				if(!Location2[elephant22.row-1][elephant22.col+1])
					addCircle(3*(elephant22.row-1)+elephant22.col+2);
				if(!Location2[elephant22.row+1][elephant22.col+1])
					addCircle(3*(elephant22.row+1)+elephant22.col+2);
			}
			break;
		//플레이어1 룩
		case 6:
			if((rook1.col==0)&&(rook1.row==0)) {
				if(!Location1[0][1])
					addCircle(2);
				if(!Location1[1][0])
					addCircle(4);
			}
			else if((rook1.col==1)&&(rook1.row==0)) {
				if(!Location1[0][0])
					addCircle(1);
				if(!Location1[0][2])
					addCircle(3);
				if(!Location1[1][1])
					addCircle(5);
			}
			else if((rook1.col==2)&&(rook1.row==0)) {
				if(!Location1[0][1])
					addCircle(2);
				if(!Location1[1][2])
					addCircle(6);
			}
			else if((rook1.col==0)&&(rook1.row==3)) {
				if(!Location1[2][0])
					addCircle(7);
				if(!Location1[3][1])
					addCircle(11);
			}
			else if((rook1.col==1)&&(rook1.row==3)) {
				if(!Location1[2][1])
					addCircle(8);
				if(!Location1[3][0])
					addCircle(10);
				if(!Location1[3][2])
					addCircle(12);
			}
			else if((rook1.col==2)&&(rook1.row==3)) {
				if(!Location1[2][2])
					addCircle(9);
				if(!Location1[3][1])
					addCircle(11);
			}
			else if((rook1.col==0)&&(rook1.row>0)&&(rook1.row<3)) {
				if(!Location1[rook1.row-1][rook1.col])
					addCircle(3*(rook1.row-1)+rook1.col+1);
				if(!Location1[rook1.row+1][rook1.col])
					addCircle(3*(rook1.row+1)+rook1.col+1);
				if(!Location1[rook1.row][rook1.col+1])
					addCircle(3*(rook1.row)+rook1.col+2);
			}
			else if((rook1.col==2)&&(rook1.row>0)&&(rook1.row<3)) {
				if(!Location1[rook1.row-1][rook1.col])
					addCircle(3*(rook1.row-1)+rook1.col+1);
				if(!Location1[rook1.row+1][rook1.col])
					addCircle(3*(rook1.row+1)+rook1.col+1);
				if(!Location1[rook1.row][rook1.col-1])
					addCircle(3*(rook1.row)+rook1.col);
			}
			else {
				if(!Location1[rook1.row][rook1.col-1])
					addCircle(3*(rook1.row)+rook1.col);
				if(!Location1[rook1.row][rook1.col+1])
					addCircle(3*(rook1.row)+rook1.col+2);
				if(!Location1[rook1.row+1][rook1.col])
					addCircle(3*(rook1.row+1)+rook1.col+1);
				if(!Location1[rook1.row-1][rook1.col])
					addCircle(3*(rook1.row-1)+rook1.col+1);
			}
			break;
		case 7:
			if((rook11.col==0)&&(rook11.row==0)) {
				if(!Location1[0][1])
					addCircle(2);
				if(!Location1[1][0])
					addCircle(4);
			}
			else if((rook11.col==1)&&(rook11.row==0)) {
				if(!Location1[0][0])
					addCircle(1);
				if(!Location1[0][2])
					addCircle(3);
				if(!Location1[1][1])
					addCircle(5);
			}
			else if((rook11.col==2)&&(rook11.row==0)) {
				if(!Location1[0][1])
					addCircle(2);
				if(!Location1[1][2])
					addCircle(6);
			}
			else if((rook11.col==0)&&(rook11.row==3)) {
				if(!Location1[2][0])
					addCircle(7);
				if(!Location1[3][1])
					addCircle(11);
			}
			else if((rook11.col==1)&&(rook11.row==3)) {
				if(!Location1[2][1])
					addCircle(8);
				if(!Location1[3][0])
					addCircle(10);
				if(!Location1[3][2])
					addCircle(12);
			}
			else if((rook11.col==2)&&(rook11.row==3)) {
				if(!Location1[2][2])
					addCircle(9);
				if(!Location1[3][1])
					addCircle(11);
			}
			else if((rook11.col==0)&&(rook11.row>0)&&(rook11.row<3)) {
				if(!Location1[rook11.row-1][rook11.col])
					addCircle(3*(rook11.row-1)+rook11.col+1);
				if(!Location1[rook11.row+1][rook11.col])
					addCircle(3*(rook11.row+1)+rook11.col+1);
				if(!Location1[rook11.row][rook11.col+1])
					addCircle(3*(rook11.row)+rook11.col+2);
			}
			else if((rook11.col==2)&&(rook11.row>0)&&(rook11.row<3)) {
				if(!Location1[rook11.row-1][rook11.col])
					addCircle(3*(rook11.row-1)+rook11.col+1);
				if(!Location1[rook11.row+1][rook11.col])
					addCircle(3*(rook11.row+1)+rook11.col+1);
				if(!Location1[rook11.row][rook11.col-1])
					addCircle(3*(rook11.row)+rook11.col);
			}
			else {
				if(!Location1[rook11.row][rook11.col-1])
					addCircle(3*(rook11.row)+rook11.col);
				if(!Location1[rook11.row][rook11.col+1])
					addCircle(3*(rook11.row)+rook11.col+2);
				if(!Location1[rook11.row+1][rook11.col])
					addCircle(3*(rook11.row+1)+rook11.col+1);
				if(!Location1[rook11.row-1][rook11.col])
					addCircle(3*(rook11.row-1)+rook11.col+1);
			}
			break;
		//플레이어2 룩
		case 8:
			if((rook2.col==0)&&(rook2.row==0)) {
				if(!Location2[0][1])
					addCircle(2);
				if(!Location2[1][0])
					addCircle(4);
			}
			else if((rook2.col==1)&&(rook2.row==0)) {
				if(!Location2[0][0])
					addCircle(1);
				if(!Location2[0][2])
					addCircle(3);
				if(!Location2[1][1])
					addCircle(5);
			}
			else if((rook2.col==2)&&(rook2.row==0)) {
				if(!Location2[0][1])
					addCircle(2);
				if(!Location2[1][2])
					addCircle(6);
			}
			else if((rook2.col==0)&&(rook2.row==3)) {
				if(!Location2[2][0])
					addCircle(7);
				if(!Location2[3][1])
					addCircle(11);
			}
			else if((rook2.col==1)&&(rook2.row==3)) {
				if(!Location2[2][1])
					addCircle(8);
				if(!Location2[3][0])
					addCircle(10);
				if(!Location2[3][2])
					addCircle(12);
			}
			else if((rook2.col==2)&&(rook2.row==3)) {
				if(!Location2[2][2])
					addCircle(9);
				if(!Location2[3][1])
					addCircle(11);
			}
			else if((rook2.col==0)&&(rook2.row>0)&&(rook2.row<3)) {
				if(!Location2[rook2.row-1][rook2.col])
					addCircle(3*(rook2.row-1)+rook2.col+1);
				if(!Location2[rook2.row+1][rook2.col])
					addCircle(3*(rook2.row+1)+rook2.col+1);
				if(!Location2[rook2.row][rook2.col+1])
					addCircle(3*(rook2.row)+rook2.col+2);
			}
			else if((rook2.col==2)&&(rook2.row>0)&&(rook2.row<3)) {
				if(!Location2[rook2.row-1][rook2.col])
					addCircle(3*(rook2.row-1)+rook2.col+1);
				if(!Location2[rook2.row+1][rook2.col])
					addCircle(3*(rook2.row+1)+rook2.col+1);
				if(!Location2[rook2.row][rook2.col-1])
					addCircle(3*(rook2.row)+rook2.col);
			}
			else {
				if(!Location2[rook2.row][rook2.col-1])
					addCircle(3*(rook2.row)+rook2.col);
				if(!Location2[rook2.row][rook2.col+1])
					addCircle(3*(rook2.row)+rook2.col+2);
				if(!Location2[rook2.row+1][rook2.col])
					addCircle(3*(rook2.row+1)+rook2.col+1);
				if(!Location2[rook2.row-1][rook2.col])
					addCircle(3*(rook2.row-1)+rook2.col+1);
			}
			break;
		case 9:
			if((rook22.col==0)&&(rook22.row==0)) {
				if(!Location2[0][1])
					addCircle(2);
				if(!Location2[1][0])
					addCircle(4);
			}
			else if((rook22.col==1)&&(rook22.row==0)) {
				if(!Location2[0][0])
					addCircle(1);
				if(!Location2[0][2])
					addCircle(3);
				if(!Location2[1][1])
					addCircle(5);
			}
			else if((rook22.col==2)&&(rook22.row==0)) {
				if(!Location2[0][1])
					addCircle(2);
				if(!Location2[1][2])
					addCircle(6);
			}
			else if((rook22.col==0)&&(rook22.row==3)) {
				if(!Location2[2][0])
					addCircle(7);
				if(!Location2[3][1])
					addCircle(11);
			}
			else if((rook22.col==1)&&(rook22.row==3)) {
				if(!Location2[2][1])
					addCircle(8);
				if(!Location2[3][0])
					addCircle(10);
				if(!Location2[3][2])
					addCircle(12);
			}
			else if((rook22.col==2)&&(rook22.row==3)) {
				if(!Location2[2][2])
					addCircle(9);
				if(!Location2[3][1])
					addCircle(11);
			}
			else if((rook22.col==0)&&(rook22.row>0)&&(rook22.row<3)) {
				if(!Location2[rook22.row-1][rook22.col])
					addCircle(3*(rook22.row-1)+rook22.col+1);
				if(!Location2[rook22.row+1][rook22.col])
					addCircle(3*(rook22.row+1)+rook22.col+1);
				if(!Location2[rook22.row][rook22.col+1])
					addCircle(3*(rook22.row)+rook22.col+2);
			}
			else if((rook22.col==2)&&(rook22.row>0)&&(rook22.row<3)) {
				if(!Location2[rook22.row-1][rook22.col])
					addCircle(3*(rook22.row-1)+rook22.col+1);
				if(!Location2[rook22.row+1][rook22.col])
					addCircle(3*(rook22.row+1)+rook22.col+1);
				if(!Location2[rook22.row][rook22.col-1])
					addCircle(3*(rook22.row)+rook22.col);
			}
			else {
				if(!Location2[rook22.row][rook22.col-1])
					addCircle(3*(rook22.row)+rook22.col);
				if(!Location2[rook22.row][rook22.col+1])
					addCircle(3*(rook22.row)+rook22.col+2);
				if(!Location2[rook22.row+1][rook22.col])
					addCircle(3*(rook22.row+1)+rook22.col+1);
				if(!Location2[rook22.row-1][rook22.col])
					addCircle(3*(rook22.row-1)+rook22.col+1);
			}
			break;
		//플레이어1 폰
		case 10:
			if(!Location1[pawn1.row-1][pawn1.col])
				addCircle(3*(pawn1.row-1)+pawn1.col+1);
			break;
		case 11:
			if(!Location1[pawn11.row-1][pawn11.col])
				addCircle(3*(pawn11.row-1)+pawn11.col+1);
			break;
		//플레이어2 폰
		case 12:
			if(!Location2[pawn2.row+1][pawn2.col])
				addCircle(3*(pawn2.row+1)+pawn2.col+1);
			break;
		case 13:
			if(!Location2[pawn22.row+1][pawn22.col])
				addCircle(3*(pawn22.row+1)+pawn22.col+1);
			break;
		//플레이어1 후
		case 14:
			if((hoo1.col==0)&&(hoo1.row==0)) {
				if(!Location1[1][0])
					addCircle(4);
				if(!Location1[0][1])
					addCircle(2);	
			}
			else if((hoo1.col==1)&&(hoo1.row==0)) {
				if(!Location1[0][0])
					addCircle(1);
				if(!Location1[1][1])
					addCircle(5);
				if(!Location1[0][2])
					addCircle(3);
			}
			else if((hoo1.col==2)&&(hoo1.row==0)) {
				if(!Location1[0][1])
					addCircle(2);
				if(!Location1[1][2])
					addCircle(6);
			}
			else if((hoo1.col==0)&&(hoo1.row==3)) {
				if(!Location1[2][0])
					addCircle(7);
				if(!Location1[2][1])
					addCircle(8);
				if(!Location1[3][1])
					addCircle(11);
			}
			else if((hoo1.col==1)&&(hoo1.row==3)) {
				if(!Location1[2][0])
					addCircle(7);
				if(!Location1[2][1])
					addCircle(8);
				if(!Location1[2][2])
					addCircle(9);
				if(!Location1[3][0])
					addCircle(10);
				if(!Location1[3][2])
					addCircle(12);
			}
			else if((hoo1.col==2)&&(hoo1.row==3)) {
				if(!Location1[2][1])
					addCircle(8);
				if(!Location1[2][2])
					addCircle(9);
				if(!Location1[3][1])
					addCircle(11);
			}
			else if((hoo1.col==0)&&(hoo1.row<3)&&(hoo1.row>0)) {
				if(!Location1[hoo1.row-1][hoo1.col])
					addCircle(3*(hoo1.row-1)+hoo1.col+1);
				if(!Location1[hoo1.row-1][hoo1.col+1])
					addCircle(3*(hoo1.row-1)+hoo1.col+2);
				if(!Location1[hoo1.row][hoo1.col+1])
					addCircle(3*(hoo1.row)+hoo1.col+2);
				if(!Location1[hoo1.row+1][hoo1.col])
					addCircle(3*(hoo1.row+1)+hoo1.col+1);
			}
			else if((hoo1.col==2)&&(hoo1.row<3)&&(hoo1.row>0)) {
				if(!Location1[hoo1.row-1][hoo1.col])
					addCircle(3*(hoo1.row-1)+hoo1.col+1);
				if(!Location1[hoo1.row-1][hoo1.col-1])
					addCircle(3*(hoo1.row-1)+hoo1.col);
				if(!Location1[hoo1.row][hoo1.col-1])
					addCircle(3*(hoo1.row)+hoo1.col);
				if(!Location1[hoo1.row+1][hoo1.col])
					addCircle(3*(hoo1.row+1)+hoo1.col+1);
			}
			else {
				if(!Location1[hoo1.row+1][hoo1.col])
					addCircle(3*(hoo1.row+1)+hoo1.col+1);
				if(!Location1[hoo1.row][hoo1.col+1])
					addCircle(3*(hoo1.row)+hoo1.col+2);
				if(!Location1[hoo1.row-1][hoo1.col])
					addCircle(3*(hoo1.row-1)+hoo1.col+1);
				if(!Location1[hoo1.row][hoo1.col-1])
					addCircle(3*(hoo1.row)+hoo1.col);
				if(!Location1[hoo1.row-1][hoo1.col-1])
					addCircle(3*(hoo1.row-1)+hoo1.col);
				if(!Location1[hoo1.row-1][hoo1.col+1])
					addCircle(3*(hoo1.row-1)+hoo1.col+2);
			}
			break;
		case 15:
			if((hoo11.col==0)&&(hoo11.row==0)) {
				if(!Location1[1][0])
					addCircle(4);
				if(!Location1[0][1])
					addCircle(2);	
			}
			else if((hoo11.col==1)&&(hoo11.row==0)) {
				if(!Location1[0][0])
					addCircle(1);
				if(!Location1[1][1])
					addCircle(5);
				if(!Location1[0][2])
					addCircle(3);
			}
			else if((hoo11.col==2)&&(hoo11.row==0)) {
				if(!Location1[0][1])
					addCircle(2);
				if(!Location1[1][2])
					addCircle(6);
			}
			else if((hoo11.col==0)&&(hoo11.row==3)) {
				if(!Location1[2][0])
					addCircle(7);
				if(!Location1[2][1])
					addCircle(8);
				if(!Location1[3][1])
					addCircle(11);
			}
			else if((hoo11.col==1)&&(hoo11.row==3)) {
				if(!Location1[2][0])
					addCircle(7);
				if(!Location1[2][1])
					addCircle(8);
				if(!Location1[2][2])
					addCircle(9);
				if(!Location1[3][0])
					addCircle(10);
				if(!Location1[3][2])
					addCircle(12);
			}
			else if((hoo11.col==2)&&(hoo11.row==3)) {
				if(!Location1[2][1])
					addCircle(8);
				if(!Location1[2][2])
					addCircle(9);
				if(!Location1[3][1])
					addCircle(11);
			}
			else if((hoo11.col==0)&&(hoo11.row<3)&&(hoo11.row>0)) {
				if(!Location1[hoo11.row-1][hoo11.col])
					addCircle(3*(hoo11.row-1)+hoo11.col+1);
				if(!Location1[hoo11.row-1][hoo11.col+1])
					addCircle(3*(hoo11.row-1)+hoo11.col+2);
				if(!Location1[hoo11.row][hoo11.col+1])
					addCircle(3*(hoo11.row)+hoo11.col+2);
				if(!Location1[hoo11.row+1][hoo11.col])
					addCircle(3*(hoo11.row+1)+hoo11.col+1);
			}
			else if((hoo11.col==2)&&(hoo11.row<3)&&(hoo11.row>0)) {
				if(!Location1[hoo11.row-1][hoo11.col])
					addCircle(3*(hoo11.row-1)+hoo11.col+1);
				if(!Location1[hoo11.row-1][hoo11.col-1])
					addCircle(3*(hoo11.row-1)+hoo11.col);
				if(!Location1[hoo11.row][hoo11.col-1])
					addCircle(3*(hoo11.row)+hoo11.col);
				if(!Location1[hoo11.row+1][hoo11.col])
					addCircle(3*(hoo11.row+1)+hoo11.col+1);
			}
			else {
				if(!Location1[hoo11.row+1][hoo11.col])
					addCircle(3*(hoo11.row+1)+hoo11.col+1);
				if(!Location1[hoo11.row][hoo11.col+1])
					addCircle(3*(hoo11.row)+hoo11.col+2);
				if(!Location1[hoo11.row-1][hoo11.col])
					addCircle(3*(hoo11.row-1)+hoo11.col+1);
				if(!Location1[hoo11.row][hoo11.col-1])
					addCircle(3*(hoo11.row)+hoo11.col);
				if(!Location1[hoo11.row-1][hoo11.col-1])
					addCircle(3*(hoo11.row-1)+hoo11.col);
				if(!Location1[hoo11.row-1][hoo11.col+1])
					addCircle(3*(hoo11.row-1)+hoo11.col+2);
			}
			break;
		//플레이어2 후
		case 16:
			if((hoo2.col==0)&&(hoo2.row==3)) {
				if(!Location2[2][0])
					addCircle(7);
				if(!Location2[3][1])
					addCircle(11);	
			}
			else if((hoo2.col==1)&&(hoo2.row==3)) {
				if(!Location2[2][1])
					addCircle(8);
				if(!Location2[3][0])
					addCircle(10);
				if(!Location2[3][2])
					addCircle(12);
			}
			else if((hoo2.col==2)&&(hoo2.row==3)) {
				if(!Location2[2][2])
					addCircle(9);
				if(!Location2[3][1])
					addCircle(11);
			}
			else if((hoo2.col==0)&&(hoo2.row==0)) {
				if(!Location2[1][0])
					addCircle(4);
				if(!Location2[0][1])
					addCircle(2);
				if(!Location2[1][1])
					addCircle(5);
			}
			else if((hoo2.col==1)&&(hoo2.row==0)) {
				if(!Location2[0][0])
					addCircle(1);
				if(!Location2[0][2])
					addCircle(3);
				if(!Location2[1][0])
					addCircle(4);
				if(!Location2[1][1])
					addCircle(5);
				if(!Location2[1][2])
					addCircle(6);
			}
			else if((hoo2.col==2)&&(hoo2.row==0)) {
				if(!Location2[0][1])
					addCircle(2);
				if(!Location2[1][1])
					addCircle(5);
				if(!Location2[1][2])
					addCircle(6);
			}
			else if((hoo2.col==0)&&(hoo2.row<3)&&(hoo2.row>0)) {
				if(!Location2[hoo2.row-1][hoo2.col])
					addCircle(3*(hoo2.row-1)+hoo2.col+1);
				if(!Location2[hoo2.row][hoo2.col+1])
					addCircle(3*(hoo2.row)+hoo2.col+2);
				if(!Location2[hoo2.row+1][hoo2.col+1])
					addCircle(3*(hoo2.row+1)+hoo2.col+2);
				if(!Location2[hoo2.row+1][hoo2.col])
					addCircle(3*(hoo2.row+1)+hoo2.col+1);
			}
			else if((hoo2.col==2)&&(hoo2.row<3)&&(hoo2.row>0)) {
				if(!Location2[hoo2.row-1][hoo2.col])
					addCircle(3*(hoo2.row-1)+hoo2.col+1);
				if(!Location2[hoo2.row][hoo2.col-1])
					addCircle(3*(hoo2.row)+hoo2.col);
				if(!Location2[hoo2.row+1][hoo2.col-1])
					addCircle(3*(hoo2.row+1)+hoo2.col);
				if(!Location2[hoo2.row+1][hoo2.col])
					addCircle(3*(hoo2.row+1)+hoo2.col+1);
			}
			else {
				if(!Location2[hoo2.row+1][hoo2.col])
					addCircle(3*(hoo2.row+1)+hoo2.col+1);
				if(!Location2[hoo2.row][hoo2.col+1])
					addCircle(3*(hoo2.row)+hoo2.col+2);
				if(!Location2[hoo2.row-1][hoo2.col])
					addCircle(3*(hoo2.row-1)+hoo2.col+1);
				if(!Location2[hoo2.row][hoo2.col-1])
					addCircle(3*(hoo2.row)+hoo2.col);
				if(!Location2[hoo2.row+1][hoo2.col-1])
					addCircle(3*(hoo2.row+1)+hoo2.col);
				if(!Location2[hoo2.row+1][hoo2.col+1])
					addCircle(3*(hoo2.row+1)+hoo2.col+2);
			}
			break;
		case 17:
			if((hoo22.col==0)&&(hoo22.row==3)) {
				if(!Location2[2][0])
					addCircle(7);
				if(!Location2[3][1])
					addCircle(11);	
			}
			else if((hoo22.col==1)&&(hoo22.row==3)) {
				if(!Location2[2][1])
					addCircle(8);
				if(!Location2[3][0])
					addCircle(10);
				if(!Location2[3][2])
					addCircle(12);
			}
			else if((hoo22.col==2)&&(hoo22.row==3)) {
				if(!Location2[2][2])
					addCircle(9);
				if(!Location2[3][1])
					addCircle(11);
			}
			else if((hoo22.col==0)&&(hoo22.row==0)) {
				if(!Location2[1][0])
					addCircle(4);
				if(!Location2[0][1])
					addCircle(2);
				if(!Location2[1][1])
					addCircle(5);
			}
			else if((hoo22.col==1)&&(hoo22.row==0)) {
				if(!Location2[0][0])
					addCircle(1);
				if(!Location2[0][2])
					addCircle(3);
				if(!Location2[1][0])
					addCircle(4);
				if(!Location2[1][1])
					addCircle(5);
				if(!Location2[1][2])
					addCircle(6);
			}
			else if((hoo22.col==2)&&(hoo22.row==0)) {
				if(!Location2[0][1])
					addCircle(2);
				if(!Location2[1][1])
					addCircle(5);
				if(!Location2[1][2])
					addCircle(6);
			}
			else if((hoo22.col==0)&&(hoo22.row<3)&&(hoo22.row>0)) {
				if(!Location2[hoo22.row-1][hoo22.col])
					addCircle(3*(hoo22.row-1)+hoo22.col+1);
				if(!Location2[hoo22.row][hoo22.col+1])
					addCircle(3*(hoo22.row)+hoo22.col+2);
				if(!Location2[hoo22.row+1][hoo22.col+1])
					addCircle(3*(hoo22.row+1)+hoo22.col+2);
				if(!Location2[hoo22.row+1][hoo22.col])
					addCircle(3*(hoo22.row+1)+hoo22.col+1);
			}
			else if((hoo22.col==2)&&(hoo22.row<3)&&(hoo22.row>0)) {
				if(!Location2[hoo22.row-1][hoo22.col])
					addCircle(3*(hoo22.row-1)+hoo22.col+1);
				if(!Location2[hoo22.row][hoo22.col-1])
					addCircle(3*(hoo22.row)+hoo22.col);
				if(!Location2[hoo22.row+1][hoo22.col-1])
					addCircle(3*(hoo22.row+1)+hoo22.col);
				if(!Location2[hoo22.row+1][hoo22.col])
					addCircle(3*(hoo22.row+1)+hoo22.col+1);
			}
			else {
				if(!Location2[hoo22.row+1][hoo22.col])
					addCircle(3*(hoo22.row+1)+hoo22.col+1);
				if(!Location2[hoo22.row][hoo22.col+1])
					addCircle(3*(hoo22.row)+hoo22.col+2);
				if(!Location2[hoo22.row-1][hoo22.col])
					addCircle(3*(hoo22.row-1)+hoo22.col+1);
				if(!Location2[hoo22.row][hoo22.col-1])
					addCircle(3*(hoo22.row)+hoo22.col);
				if(!Location2[hoo22.row+1][hoo22.col-1])
					addCircle(3*(hoo22.row+1)+hoo22.col);
				if(!Location2[hoo22.row+1][hoo22.col+1])
					addCircle(3*(hoo22.row+1)+hoo22.col+2);
			}
			break;
		}
	}
	
	public void checkDie() {
		if(whoseTurn%2==0) {
			if(whoClicked[0]) {
				if((king1.row==king2.row)&&(king1.col==king2.col)) {
					king2.onBoard=false;
				}
				else if((king1.row==elephant2.row)&&(king1.col==elephant2.col)) {
					elephant2.onBoard=false;
					Location2[elephant2.row][elephant2.col]=false;
					gameBoard.getChildren().remove(elephant2.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if((king1.row==elephant22.row)&&(king1.col==elephant22.col)) {
					elephant22.onBoard=false;
					Location2[elephant22.row][elephant22.col]=false;
					gameBoard.getChildren().remove(elephant22.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if((king1.row==rook2.row)&&(king1.col==rook2.col)) {
					rook2.onBoard=false;
					Location2[rook2.row][rook2.col]=false;
					gameBoard.getChildren().remove(rook2.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead){
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if((king1.row==rook22.row)&&(king1.col==rook22.col)) {
					rook22.onBoard=false;
					Location2[rook22.row][rook22.col]=false;
					gameBoard.getChildren().remove(rook22.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead){
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if((king1.row==pawn2.row)&&(king1.col==pawn2.col)) {
					pawn2.onBoard=false;
					Location2[pawn2.row][pawn2.col]=false;
					gameBoard.getChildren().remove(pawn2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),10);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if((king1.row==pawn22.row)&&(king1.col==pawn22.col)) {
					pawn22.onBoard=false;
					Location2[pawn22.row][pawn22.col]=false;
					gameBoard.getChildren().remove(pawn22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if((king1.row==hoo2.row)&&(king1.col==hoo2.col)) {
					hoo2.onBoard=false;
					Location2[hoo2.row][hoo2.col]=false;
					gameBoard.getChildren().remove(hoo2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if((king1.row==hoo22.row)&&(king1.col==hoo22.col)) {
					hoo22.onBoard=false;
					Location2[hoo22.row][hoo22.col]=false;
					gameBoard.getChildren().remove(hoo22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
			}
			else if(whoClicked[2]) {
				if((elephant1.row==king2.row)&&(elephant1.col==king2.col)) {
					king2.onBoard=false;
				}
				else if((elephant1.row==elephant2.row)&&(elephant1.col==elephant2.col)) {
					elephant2.onBoard=false;
					Location2[elephant2.row][elephant2.col]=false;
					gameBoard.getChildren().remove(elephant2.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if((elephant1.row==elephant22.row)&&(elephant1.col==elephant22.col)) {
					elephant22.onBoard=false;
					Location2[elephant22.row][elephant22.col]=false;
					gameBoard.getChildren().remove(elephant22.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if((elephant1.row==rook2.row)&&(elephant1.col==rook2.col)) {
					rook2.onBoard=false;
					Location2[rook2.row][rook2.col]=false;
					gameBoard.getChildren().remove(rook2.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead){
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(rook22.onBoard&&(elephant1.row==rook22.row)&&(elephant1.col==rook22.col)) {
					Location2[rook22.row][rook22.col]=false;
					rook22.onBoard=false;
					gameBoard.getChildren().remove(rook22.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead){
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(pawn2.onBoard&&(elephant1.row==pawn2.row)&&(elephant1.col==pawn2.col)) {
					Location2[pawn2.row][pawn2.col]=false;
					pawn2.onBoard=false;
					gameBoard.getChildren().remove(pawn2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(pawn22.onBoard&&(elephant1.row==pawn22.row)&&(elephant1.col==pawn22.col)) {
					Location2[pawn22.row][pawn22.col]=false;
					pawn22.onBoard=false;
					gameBoard.getChildren().remove(pawn22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(hoo2.onBoard&&(elephant1.row==hoo2.row)&&(elephant1.col==hoo2.col)) {
					Location2[hoo2.row][hoo2.col]=false;
					hoo2.onBoard=false;
					gameBoard.getChildren().remove(hoo2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(hoo22.onBoard&&(elephant1.row==hoo22.row)&&(elephant1.col==hoo22.col)) {
					Location2[hoo22.row][hoo22.col]=false;
					hoo22.onBoard=false;
					gameBoard.getChildren().remove(hoo22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
					}
				}
			else if(whoClicked[3]) {
				if((elephant11.row==king2.row)&&(elephant11.col==king2.col)) {
					king2.onBoard=false;
				}
				else if((elephant11.row==elephant2.row)&&(elephant11.col==elephant2.col)) {
					elephant2.onBoard=false;
					Location2[elephant2.row][elephant2.col]=false;
					gameBoard.getChildren().remove(elephant2.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if((elephant11.row==elephant22.row)&&(elephant11.col==elephant22.col)) {
					elephant22.onBoard=false;
					Location2[elephant22.row][elephant22.col]=false;
					gameBoard.getChildren().remove(elephant22.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if((elephant11.row==rook2.row)&&(elephant11.col==rook2.col)) {
					rook2.onBoard=false;
					Location2[rook2.row][rook2.col]=false;
					gameBoard.getChildren().remove(rook2.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead){
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(rook22.onBoard&&(elephant11.row==rook22.row)&&(elephant11.col==rook22.col)) {
					Location2[rook22.row][rook22.col]=false;
					rook22.onBoard=false;
					gameBoard.getChildren().remove(rook22.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead){
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(pawn2.onBoard&&(elephant11.row==pawn2.row)&&(elephant11.col==pawn2.col)) {
					Location2[pawn2.row][pawn2.col]=false;
					pawn2.onBoard=false;
					gameBoard.getChildren().remove(pawn2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(pawn22.onBoard&&(elephant11.row==pawn22.row)&&(elephant11.col==pawn22.col)) {
					Location2[pawn22.row][pawn22.col]=false;
					pawn22.onBoard=false;
					gameBoard.getChildren().remove(pawn22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(hoo2.onBoard&&(elephant11.row==hoo2.row)&&(elephant11.col==hoo2.col)) {
					Location2[hoo2.row][hoo2.col]=false;
					hoo2.onBoard=false;
					gameBoard.getChildren().remove(hoo2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(hoo22.onBoard&&(elephant11.row==hoo22.row)&&(elephant11.col==hoo22.col)) {
					Location2[hoo22.row][hoo22.col]=false;
					hoo22.onBoard=false;
					gameBoard.getChildren().remove(hoo22.pane);
						if(!pawn1.onBoard&&!pawn1.isDead) {
							pawn1.isDead=true;
							pawn1.setSize(pawn1.isDead);
							pawn1.setLocation(emptyDead(),0);
							deadSpace1.getChildren().add(pawn1.pane);
						}
						else if(!pawn11.onBoard&&!pawn11.isDead){
							pawn11.isDead=true;
							pawn11.setSize(pawn11.isDead);
							pawn11.setLocation(emptyDead(),0);
							deadSpace1.getChildren().add(pawn11.pane);
						}
					}
				}
			else if(whoClicked[6]) {
				if((rook1.row==king2.row)&&(rook1.col==king2.col)) {
					king2.onBoard=false;
				}
				else if(elephant2.onBoard&&(rook1.row==elephant2.row)&&(rook1.col==elephant2.col)) {
					Location2[elephant2.row][elephant2.col]=false;
					elephant2.onBoard=false;
					gameBoard.getChildren().remove(elephant2.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if(elephant22.onBoard&&(rook1.row==elephant22.row)&&(rook1.col==elephant22.col)) {
					Location2[elephant22.row][elephant22.col]=false;
					elephant22.onBoard=false;
					gameBoard.getChildren().remove(elephant22.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if(rook2.onBoard&&(rook1.row==rook2.row)&&(rook1.col==rook2.col)) {
					Location2[rook2.row][rook2.col]=false;
					rook2.onBoard=false;
					gameBoard.getChildren().remove(rook2.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead) {
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(rook22.onBoard&&(rook1.row==rook22.row)&&(rook1.col==rook22.col)) {
					Location2[rook22.row][rook22.col]=false;
					rook22.onBoard=false;
					gameBoard.getChildren().remove(rook22.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead) {
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(pawn2.onBoard&&(rook1.row==pawn2.row)&&(rook1.col==pawn2.col)) {
					Location2[pawn2.row][pawn2.col]=false;
					pawn2.onBoard=false;
					gameBoard.getChildren().remove(pawn2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(pawn22.onBoard&&(rook1.row==pawn22.row)&&(rook1.col==pawn22.col)) {
					Location2[pawn22.row][pawn22.col]=false;
					pawn22.onBoard=false;
					gameBoard.getChildren().remove(pawn22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}				
				else if(hoo2.onBoard&&(rook1.row==hoo2.row)&&(rook1.col==hoo2.col)) {
					Location2[hoo2.row][hoo2.col]=false;
					hoo2.onBoard=false;
					gameBoard.getChildren().remove(hoo2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(hoo22.onBoard&&(rook1.row==hoo22.row)&&(rook1.col==hoo22.col)) {
					Location2[hoo22.row][hoo22.col]=false;
					hoo22.onBoard=false;
					gameBoard.getChildren().remove(hoo22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
			}
			else if(whoClicked[7]) {
				if((rook11.row==king2.row)&&(rook11.col==king2.col)) {
					king2.onBoard=false;
				}
				else if(elephant2.onBoard&&(rook11.row==elephant2.row)&&(rook11.col==elephant2.col)) {
					Location2[elephant2.row][elephant2.col]=false;
					elephant2.onBoard=false;
					gameBoard.getChildren().remove(elephant2.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if(elephant22.onBoard&&(rook11.row==elephant22.row)&&(rook11.col==elephant22.col)) {
					Location2[elephant22.row][elephant22.col]=false;
					elephant22.onBoard=false;
					gameBoard.getChildren().remove(elephant22.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if(rook2.onBoard&&(rook11.row==rook2.row)&&(rook11.col==rook2.col)) {
					Location2[rook2.row][rook2.col]=false;
					rook2.onBoard=false;
					gameBoard.getChildren().remove(rook2.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead) {
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(rook22.onBoard&&(rook11.row==rook22.row)&&(rook11.col==rook22.col)) {
					Location2[rook22.row][rook22.col]=false;
					rook22.onBoard=false;
					gameBoard.getChildren().remove(rook22.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead) {
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(pawn2.onBoard&&(rook11.row==pawn2.row)&&(rook11.col==pawn2.col)) {
					Location2[pawn2.row][pawn2.col]=false;
					pawn2.onBoard=false;
					gameBoard.getChildren().remove(pawn2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(pawn22.onBoard&&(rook11.row==pawn22.row)&&(rook11.col==pawn22.col)) {
					Location2[pawn22.row][pawn22.col]=false;
					pawn22.onBoard=false;
					gameBoard.getChildren().remove(pawn22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}				
				else if(hoo2.onBoard&&(rook11.row==hoo2.row)&&(rook11.col==hoo2.col)) {
					Location2[hoo2.row][hoo2.col]=false;
					hoo2.onBoard=false;
					gameBoard.getChildren().remove(hoo2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(hoo22.onBoard&&(rook11.row==hoo22.row)&&(rook11.col==hoo22.col)) {
					Location2[hoo22.row][hoo22.col]=false;
					hoo22.onBoard=false;
					gameBoard.getChildren().remove(hoo22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
			}
			else if(whoClicked[10]) {
				if((pawn1.row==king2.row+1)&&(pawn1.col==king2.col)) {
					king2.onBoard=false;
				}
				else if(elephant2.onBoard&&(pawn1.row==elephant2.row+1)&&(pawn1.col==elephant2.col)) {
					Location2[elephant2.row][elephant2.col]=false;
					elephant2.onBoard=false;
					gameBoard.getChildren().remove(elephant2.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if(elephant22.onBoard&&(pawn1.row==elephant22.row+1)&&(pawn1.col==elephant22.col)) {
					Location2[elephant22.row][elephant22.col]=false;
					elephant22.onBoard=false;
					gameBoard.getChildren().remove(elephant22.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if(rook2.onBoard&&(pawn1.row==rook2.row+1)&&(pawn1.col==rook2.col)) {
					Location2[rook2.row][rook2.col]=false;
					rook2.onBoard=false;
					gameBoard.getChildren().remove(rook2.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead){
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(rook22.onBoard&&(pawn1.row==rook22.row+1)&&(pawn1.col==rook22.col)) {
					Location2[rook22.row][rook22.col]=false;
					rook22.onBoard=false;
					gameBoard.getChildren().remove(rook22.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead){
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(pawn2.onBoard&&(pawn1.row==pawn2.row+1)&&(pawn1.col==pawn2.col)) {
					Location2[pawn2.row][pawn2.col]=false;
					pawn2.onBoard=false;
					gameBoard.getChildren().remove(pawn2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead) {
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(pawn22.onBoard&&(pawn1.row==pawn22.row+1)&&(pawn1.col==pawn22.col)) {
					Location2[pawn22.row][pawn22.col]=false;
					pawn22.onBoard=false;
					gameBoard.getChildren().remove(pawn22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead) {
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				
				else if(hoo2.onBoard&&(pawn1.row==hoo2.row+1)&&(pawn1.col==hoo2.col)) {
					Location2[hoo2.row][hoo2.col]=false;
					hoo2.onBoard=false;
					gameBoard.getChildren().remove(hoo2.pane);					
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead) {
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}	
				}
				else if(hoo22.onBoard&&(pawn1.row==hoo22.row+1)&&(pawn1.col==hoo22.col)) {
					Location2[hoo22.row][hoo22.col]=false;
					hoo22.onBoard=false;
					gameBoard.getChildren().remove(hoo22.pane);									
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead) {
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
			}
			else if(whoClicked[11]) {
				if((pawn11.row==king2.row+1)&&(pawn11.col==king2.col)) {
					king2.onBoard=false;
				}
				else if(elephant2.onBoard&&(pawn11.row==elephant2.row+1)&&(pawn11.col==elephant2.col)) {
					Location2[elephant2.row][elephant2.col]=false;
					elephant2.onBoard=false;
					gameBoard.getChildren().remove(elephant2.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if(elephant22.onBoard&&(pawn11.row==elephant22.row+1)&&(pawn11.col==elephant22.col)) {
					Location2[elephant22.row][elephant22.col]=false;
					elephant22.onBoard=false;
					gameBoard.getChildren().remove(elephant22.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if(rook2.onBoard&&(pawn11.row==rook2.row+1)&&(pawn11.col==rook2.col)) {
					Location2[rook2.row][rook2.col]=false;
					rook2.onBoard=false;
					gameBoard.getChildren().remove(rook2.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead){
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(rook22.onBoard&&(pawn11.row==rook22.row+1)&&(pawn11.col==rook22.col)) {
					Location2[rook22.row][rook22.col]=false;
					rook22.onBoard=false;
					gameBoard.getChildren().remove(rook22.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead){
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(pawn2.onBoard&&(pawn11.row==pawn2.row+1)&&(pawn11.col==pawn2.col)) {
					Location2[pawn2.row][pawn2.col]=false;
					pawn2.onBoard=false;
					gameBoard.getChildren().remove(pawn2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead) {
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(pawn22.onBoard&&(pawn11.row==pawn22.row+1)&&(pawn11.col==pawn22.col)) {
					Location2[pawn22.row][pawn22.col]=false;
					pawn22.onBoard=false;
					gameBoard.getChildren().remove(pawn22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead) {
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				
				else if(hoo2.onBoard&&(pawn11.row==hoo2.row+1)&&(pawn11.col==hoo2.col)) {
					Location2[hoo2.row][hoo2.col]=false;
					hoo2.onBoard=false;
					gameBoard.getChildren().remove(hoo2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead) {
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(hoo22.onBoard&&(pawn11.row==hoo22.row+1)&&(pawn11.col==hoo22.col)) {
					Location2[hoo22.row][hoo22.col]=false;
					hoo22.onBoard=false;
					gameBoard.getChildren().remove(hoo22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead) {
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
			}
			else if(whoClicked[14]) {
				if((hoo1.row==king2.row)&&(hoo1.col==king2.col)) {
					king2.onBoard=false;
				}
				else if(elephant2.onBoard&&(hoo1.row==elephant2.row)&&(hoo1.col==elephant2.col)) {
					Location2[elephant2.row][elephant2.col]=false;
					elephant2.onBoard=false;
					gameBoard.getChildren().remove(elephant2.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if(elephant22.onBoard&&(hoo1.row==elephant22.row)&&(hoo1.col==elephant22.col)) {
					Location2[elephant22.row][elephant22.col]=false;
					elephant22.onBoard=false;
					gameBoard.getChildren().remove(elephant22.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if(rook2.onBoard&&(hoo1.row==rook2.row)&&(hoo1.col==rook2.col)) {
					Location2[rook2.row][rook2.col]=false;
					rook2.onBoard=false;
					gameBoard.getChildren().remove(rook2.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead){
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(rook22.onBoard&&(hoo1.row==rook22.row)&&(hoo1.col==rook22.col)) {
					Location2[rook22.row][rook22.col]=false;
					rook22.onBoard=false;
					gameBoard.getChildren().remove(rook22.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead){
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(pawn2.onBoard&&(hoo1.row==pawn2.row)&&(hoo1.col==pawn2.col)) {
					Location2[pawn2.row][pawn2.col]=false;
					pawn2.onBoard=false;
					gameBoard.getChildren().remove(pawn2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(pawn22.onBoard&&(hoo1.row==pawn22.row)&&(hoo1.col==pawn22.col)) {
					Location2[pawn22.row][pawn22.col]=false;
					pawn22.onBoard=false;
					gameBoard.getChildren().remove(pawn22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				
				else if(hoo2.onBoard&&(hoo1.row==hoo2.row)&&(hoo1.col==hoo2.col)) {
					Location2[hoo2.row][hoo2.col]=false;
					hoo2.onBoard=false;
					gameBoard.getChildren().remove(hoo2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(hoo22.onBoard&&(hoo1.row==hoo22.row)&&(hoo1.col==hoo22.col)) {
					Location2[hoo22.row][hoo22.col]=false;
					hoo22.onBoard=false;
					gameBoard.getChildren().remove(hoo22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
			}
			else if(whoClicked[15]) {
				if((hoo11.row==king2.row)&&(hoo11.col==king2.col)) {
					king2.onBoard=false;
				}
				else if(elephant2.onBoard&&(hoo11.row==elephant2.row)&&(hoo11.col==elephant2.col)) {
					Location2[elephant2.row][elephant2.col]=false;
					elephant2.onBoard=false;
					gameBoard.getChildren().remove(elephant2.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if(elephant22.onBoard&&(hoo11.row==elephant22.row)&&(hoo11.col==elephant22.col)) {
					Location2[elephant22.row][elephant22.col]=false;
					elephant22.onBoard=false;
					gameBoard.getChildren().remove(elephant22.pane);
					if(!elephant1.onBoard&&!elephant1.isDead) {
						elephant1.isDead=true;
						elephant1.setSize(elephant1.isDead);
						elephant1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant1.pane);
					}
					else if(!elephant11.onBoard&&!elephant11.isDead){
						elephant11.isDead=true;
						elephant11.setSize(elephant11.isDead);
						elephant11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(elephant11.pane);
					}
				}
				else if(rook2.onBoard&&(hoo11.row==rook2.row)&&(hoo11.col==rook2.col)) {
					Location2[rook2.row][rook2.col]=false;
					rook2.onBoard=false;
					gameBoard.getChildren().remove(rook2.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead){
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(rook22.onBoard&&(hoo11.row==rook22.row)&&(hoo11.col==rook22.col)) {
					Location2[rook22.row][rook22.col]=false;
					rook22.onBoard=false;
					gameBoard.getChildren().remove(rook22.pane);
					if(!rook1.onBoard&&!rook1.isDead) {
						rook1.isDead=true;
						rook1.setSize(rook1.isDead);
						rook1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook1.pane);
					}
					else if(!rook11.onBoard&&!rook11.isDead){
						rook11.isDead=true;
						rook11.setSize(rook11.isDead);
						rook11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(rook11.pane);
					}
				}
				else if(pawn2.onBoard&&(hoo11.row==pawn2.row)&&(hoo11.col==pawn2.col)) {
					Location2[pawn2.row][pawn2.col]=false;
					pawn2.onBoard=false;
					gameBoard.getChildren().remove(pawn2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(pawn22.onBoard&&(hoo11.row==pawn22.row)&&(hoo11.col==pawn22.col)) {
					Location2[pawn22.row][pawn22.col]=false;
					pawn22.onBoard=false;
					gameBoard.getChildren().remove(pawn22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				
				else if(hoo2.onBoard&&(hoo11.row==hoo2.row)&&(hoo11.col==hoo2.col)) {
					Location2[hoo2.row][hoo2.col]=false;
					hoo2.onBoard=false;
					gameBoard.getChildren().remove(hoo2.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
				else if(hoo22.onBoard&&(hoo11.row==hoo22.row)&&(hoo11.col==hoo22.col)) {
					Location2[hoo22.row][hoo22.col]=false;
					hoo22.onBoard=false;
					gameBoard.getChildren().remove(hoo22.pane);
					if(!pawn1.onBoard&&!pawn1.isDead) {
						pawn1.isDead=true;
						pawn1.setSize(pawn1.isDead);  
						pawn1.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn1.pane);
					}
					else if(!pawn11.onBoard&&!pawn11.isDead){
						pawn11.isDead=true;
						pawn11.setSize(pawn11.isDead);
						pawn11.setLocation(emptyDead(),0);
						deadSpace1.getChildren().add(pawn11.pane);
					}
				}
			}
		}
		else {
			if(whoClicked[1]) {
				if((king2.row==king1.row)&&(king2.col==king1.col)) {
					king1.onBoard = false;
				}
				else if(elephant1.onBoard&&(king2.row==elephant1.row)&&(king2.col==elephant1.col)) {
					Location1[elephant1.row][elephant1.col]=false;
					elephant1.onBoard=false;
					gameBoard.getChildren().remove(elephant1.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead){
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(elephant11.onBoard&&(king2.row==elephant11.row)&&(king2.col==elephant11.col)) {
					Location1[elephant11.row][elephant11.col]=false;
					elephant11.onBoard=false;
					gameBoard.getChildren().remove(elephant11.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead){
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(rook1.onBoard&&(king2.row==rook1.row)&&(king2.col==rook1.col)) {
					Location1[rook1.row][rook1.col]=false;
					rook1.onBoard=false;
					gameBoard.getChildren().remove(rook1.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead){
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(rook11.onBoard&&(king2.row==rook11.row)&&(king2.col==rook11.col)) {
					Location1[rook11.row][rook11.col]=false;
					rook11.onBoard=false;
					gameBoard.getChildren().remove(rook11.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead){
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(pawn1.onBoard&&(king2.row==pawn1.row)&&(king2.col==pawn1.col)) {
					Location1[pawn1.row][pawn1.col]=false;
					pawn1.onBoard=false;
					gameBoard.getChildren().remove(pawn1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(pawn11.onBoard&&(king2.row==pawn11.row)&&(king2.col==pawn11.col)) {
					Location1[pawn11.row][pawn11.col]=false;
					pawn11.onBoard=false;
					gameBoard.getChildren().remove(pawn11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo1.onBoard&&(king2.row==hoo1.row)&&(king2.col==hoo1.col)) {
					Location1[hoo1.row][hoo1.col]=false;
					hoo1.onBoard=false;
					gameBoard.getChildren().remove(hoo1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo11.onBoard&&(king2.row==hoo11.row)&&(king2.col==hoo11.col)) {
					Location1[hoo11.row][hoo11.col]=false;
					hoo11.onBoard=false;
					gameBoard.getChildren().remove(hoo11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
			}
			else if(whoClicked[4]) {
				if((elephant2.row==king1.row)&&(elephant2.col==king1.col)) {
					king1.onBoard = false;
				}
				else if(elephant1.onBoard&&(elephant2.row==elephant1.row)&&(elephant2.col==elephant1.col)) {
					Location1[elephant1.row][elephant1.col]=false;
					elephant1.onBoard=false;
					gameBoard.getChildren().remove(elephant1.pane);	
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead) {
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(elephant11.onBoard&&(elephant2.row==elephant11.row)&&(elephant2.col==elephant11.col)) {
					Location1[elephant11.row][elephant11.col]=false;
					elephant11.onBoard=false;
					gameBoard.getChildren().remove(elephant11.pane);		
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead) {
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(rook1.onBoard&&(elephant2.row==rook1.row)&&(elephant2.col==rook1.col)) {
					Location1[rook1.row][rook1.col]=false;
					rook1.onBoard=false;
					gameBoard.getChildren().remove(rook1.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead){
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(rook11.onBoard&&(elephant2.row==rook11.row)&&(elephant2.col==rook11.col)) {
					Location1[rook11.row][rook11.col]=false;
					rook11.onBoard=false;
					gameBoard.getChildren().remove(rook11.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead){
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(pawn1.onBoard&&(elephant2.row==pawn1.row)&&(elephant2.col==pawn1.col)) {
					Location1[pawn1.row][pawn1.col]=false;
					pawn1.onBoard=false;
					gameBoard.getChildren().remove(pawn1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(pawn11.onBoard&&(elephant2.row==pawn11.row)&&(elephant2.col==pawn11.col)) {
					Location1[pawn11.row][pawn11.col]=false;
					pawn11.onBoard=false;
					gameBoard.getChildren().remove(pawn11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo1.onBoard&&(elephant2.row==hoo1.row)&&(elephant2.col==hoo1.col)) {
					Location1[hoo1.row][hoo1.col]=false;
					hoo1.onBoard=false;
					gameBoard.getChildren().remove(hoo1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo11.onBoard&&(elephant2.row==hoo11.row)&&(elephant2.col==hoo11.col)) {
					Location1[hoo11.row][hoo11.col]=false;
					hoo11.onBoard=false;
					gameBoard.getChildren().remove(hoo11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
			}
			else if(whoClicked[5]) {
				if((elephant22.row==king1.row)&&(elephant22.col==king1.col)) {
					king1.onBoard = false;
				}
				else if(elephant1.onBoard&&(elephant22.row==elephant1.row)&&(elephant22.col==elephant1.col)) {
					Location1[elephant1.row][elephant1.col]=false;
					elephant1.onBoard=false;
					gameBoard.getChildren().remove(elephant1.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead) {
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(elephant11.onBoard&&(elephant22.row==elephant11.row)&&(elephant22.col==elephant11.col)) {
					Location1[elephant11.row][elephant11.col]=false;
					elephant11.onBoard=false;
					gameBoard.getChildren().remove(elephant11.pane);					
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead) {
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
					
				}
				else if(rook1.onBoard&&(elephant22.row==rook1.row)&&(elephant22.col==rook1.col)) {
					Location1[rook1.row][rook1.col]=false;
					rook1.onBoard=false;
					gameBoard.getChildren().remove(rook1.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead){
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(rook11.onBoard&&(elephant22.row==rook11.row)&&(elephant22.col==rook11.col)) {
					Location1[rook11.row][rook11.col]=false;
					rook11.onBoard=false;
					gameBoard.getChildren().remove(rook11.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead){
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(pawn1.onBoard&&(elephant22.row==pawn1.row)&&(elephant22.col==pawn1.col)) {
					Location1[pawn1.row][pawn1.col]=false;
					pawn1.onBoard=false;
					gameBoard.getChildren().remove(pawn1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(pawn11.onBoard&&(elephant22.row==pawn11.row)&&(elephant22.col==pawn11.col)) {
					Location1[pawn11.row][pawn11.col]=false;
					pawn11.onBoard=false;
					gameBoard.getChildren().remove(pawn11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo1.onBoard&&(elephant22.row==hoo1.row)&&(elephant22.col==hoo1.col)) {
					Location1[hoo1.row][hoo1.col]=false;
					hoo1.onBoard=false;
					gameBoard.getChildren().remove(hoo1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo11.onBoard&&(elephant22.row==hoo11.row)&&(elephant22.col==hoo11.col)) {
					Location1[hoo11.row][hoo11.col]=false;
					hoo11.onBoard=false;
					gameBoard.getChildren().remove(hoo11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
			}
			
			else if(whoClicked[8]) {
				if((rook2.row==king1.row)&&(rook2.col==king1.col)) {
					king1.onBoard = false;
				}
				else if(elephant1.onBoard&&(rook2.row==elephant1.row)&&(rook2.col==elephant1.col)) {
					Location1[elephant1.row][elephant1.col]=false;
					elephant1.onBoard=false;
					gameBoard.getChildren().remove(elephant1.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead){
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(elephant11.onBoard&&(rook2.row==elephant11.row)&&(rook2.col==elephant11.col)) {
					Location1[elephant11.row][elephant11.col]=false;
					elephant11.onBoard=false;
					gameBoard.getChildren().remove(elephant11.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead){
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(rook1.onBoard&&(rook2.row==rook1.row)&&(rook2.col==rook1.col)) {
					Location1[rook1.row][rook1.col]=false;
					rook1.onBoard=false;
					gameBoard.getChildren().remove(rook1.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead) {
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(rook11.onBoard&&(rook2.row==rook11.row)&&(rook2.col==rook11.col)) {
					Location1[rook11.row][rook11.col]=false;
					rook11.onBoard=false;
					gameBoard.getChildren().remove(rook11.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead) {
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(pawn1.onBoard&&(rook2.row==pawn1.row)&&(rook2.col==pawn1.col)) {
					Location1[pawn1.row][pawn1.col]=false;
					pawn1.onBoard=false;
					gameBoard.getChildren().remove(pawn1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(pawn11.onBoard&&(rook2.row==pawn11.row)&&(rook2.col==pawn11.col)) {
					Location1[pawn11.row][pawn11.col]=false;
					pawn11.onBoard=false;
					gameBoard.getChildren().remove(pawn11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo1.onBoard&&(rook2.row==hoo1.row)&&(rook2.col==hoo1.col)) {
					Location1[hoo1.row][hoo1.col]=false;
					hoo1.onBoard=false;
					gameBoard.getChildren().remove(hoo1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo11.onBoard&&(rook2.row==hoo11.row)&&(rook2.col==hoo11.col)) {
					Location1[hoo11.row][hoo11.col]=false;
					hoo11.onBoard=false;
					gameBoard.getChildren().remove(hoo11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
			}
			else if(whoClicked[9]) {
				if((rook22.row==king1.row)&&(rook22.col==king1.col)) {
					king1.onBoard = false;
				}
				else if(elephant1.onBoard&&(rook22.row==elephant1.row)&&(rook22.col==elephant1.col)) {
					Location1[elephant1.row][elephant1.col]=false;
					elephant1.onBoard=false;
					gameBoard.getChildren().remove(elephant1.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead){
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(elephant11.onBoard&&(rook22.row==elephant11.row)&&(rook22.col==elephant11.col)) {
					Location1[elephant11.row][elephant11.col]=false;
					elephant11.onBoard=false;
					gameBoard.getChildren().remove(elephant11.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead){
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(rook1.onBoard&&(rook22.row==rook1.row)&&(rook22.col==rook1.col)) {
					Location1[rook1.row][rook1.col]=false;
					rook1.onBoard=false;
					gameBoard.getChildren().remove(rook1.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead) {
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(rook11.onBoard&&(rook22.row==rook11.row)&&(rook22.col==rook11.col)) {
					Location1[rook11.row][rook11.col]=false;
					rook11.onBoard=false;
					gameBoard.getChildren().remove(rook11.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead) {
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(pawn1.onBoard&&(rook22.row==pawn1.row)&&(rook22.col==pawn1.col)) {
					Location1[pawn1.row][pawn1.col]=false;
					pawn1.onBoard=false;
					gameBoard.getChildren().remove(pawn1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(pawn11.onBoard&&(rook22.row==pawn11.row)&&(rook22.col==pawn11.col)) {
					Location1[pawn11.row][pawn11.col]=false;
					pawn11.onBoard=false;
					gameBoard.getChildren().remove(pawn11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo1.onBoard&&(rook22.row==hoo1.row)&&(rook22.col==hoo1.col)) {
					Location1[hoo1.row][hoo1.col]=false;
					hoo1.onBoard=false;
					gameBoard.getChildren().remove(hoo1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo11.onBoard&&(rook22.row==hoo11.row)&&(rook22.col==hoo11.col)) {
					Location1[hoo11.row][hoo11.col]=false;
					hoo11.onBoard=false;
					gameBoard.getChildren().remove(hoo11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
			}
			else if(whoClicked[12]) {
				if((pawn2.row==king1.row-1)&&(pawn2.col==king1.col)) {
					king1.onBoard = false;
				}
				else if(elephant1.onBoard&&(pawn2.row==elephant1.row-1)&&(pawn2.col==elephant1.col)) {
					Location1[elephant1.row][elephant1.col]=false;
					elephant1.onBoard=false;
					gameBoard.getChildren().remove(elephant1.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead){
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(elephant11.onBoard&&(pawn2.row==elephant11.row-1)&&(pawn2.col==elephant11.col)) {
					Location1[elephant11.row][elephant11.col]=false;
					elephant11.onBoard=false;
					gameBoard.getChildren().remove(elephant11.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead){
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(rook1.onBoard&&(pawn2.row==rook1.row-1)&&(pawn2.col==rook1.col)) {
					Location1[rook1.row][rook1.col]=false;
					rook1.onBoard=false;
					gameBoard.getChildren().remove(rook1.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead){
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(rook11.onBoard&&(pawn2.row==rook11.row-1)&&(pawn2.col==rook11.col)) {
					Location1[rook11.row][rook11.col]=false;
					rook11.onBoard=false;
					gameBoard.getChildren().remove(rook11.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead){
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(pawn1.onBoard&&(pawn2.row==pawn1.row-1)&&(pawn2.col==pawn1.col)) {
					Location1[pawn1.row][pawn1.col]=false;
					pawn1.onBoard=false;
					gameBoard.getChildren().remove(pawn1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead) {
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(pawn11.onBoard&&(pawn2.row==pawn11.row-1)&&(pawn2.col==pawn11.col)) {
					Location1[pawn11.row][pawn11.col]=false;
					pawn11.onBoard=false;
					gameBoard.getChildren().remove(pawn11.pane);				
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead) {
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo1.onBoard&&(pawn2.row==hoo1.row-1)&&(pawn2.col==hoo1.col)) {
					Location1[hoo1.row][hoo1.col]=false;
					hoo1.onBoard=false;
					gameBoard.getChildren().remove(hoo1.pane);				
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead) {
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo11.onBoard&&(pawn2.row==hoo11.row-1)&&(pawn2.col==hoo11.col)) {
					Location1[hoo11.row][hoo11.col]=false;
					hoo11.onBoard=false;
					gameBoard.getChildren().remove(hoo11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead) {
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
			}
			else if(whoClicked[13]) {
				if((pawn22.row==king1.row-1)&&(pawn22.col==king1.col)) {
					king1.onBoard = false;
				}
				else if(elephant1.onBoard&&(pawn22.row==elephant1.row-1)&&(pawn22.col==elephant1.col)) {
					Location1[elephant1.row][elephant1.col]=false;
					elephant1.onBoard=false;
					gameBoard.getChildren().remove(elephant1.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead){
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(elephant11.onBoard&&(pawn22.row==elephant11.row-1)&&(pawn22.col==elephant11.col)) {
					Location1[elephant11.row][elephant11.col]=false;
					elephant11.onBoard=false;
					gameBoard.getChildren().remove(elephant11.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead){
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(rook1.onBoard&&(pawn22.row==rook1.row-1)&&(pawn22.col==rook1.col)) {
					Location1[rook1.row][rook1.col]=false;
					rook1.onBoard=false;
					gameBoard.getChildren().remove(rook1.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead){
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(rook11.onBoard&&(pawn22.row==rook11.row-1)&&(pawn22.col==rook11.col)) {
					Location1[rook11.row][rook11.col]=false;
					rook11.onBoard=false;
					gameBoard.getChildren().remove(rook11.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead){
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(pawn1.onBoard&&(pawn22.row==pawn1.row-1)&&(pawn22.col==pawn1.col)) {
					Location1[pawn1.row][pawn1.col]=false;
					pawn1.onBoard=false;
					gameBoard.getChildren().remove(pawn1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead) {
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(pawn11.onBoard&&(pawn22.row==pawn11.row-1)&&(pawn22.col==pawn11.col)) {
					Location1[pawn11.row][pawn11.col]=false;
					pawn11.onBoard=false;
					gameBoard.getChildren().remove(pawn11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead) {
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo1.onBoard&&(pawn22.row==hoo1.row-1)&&(pawn22.col==hoo1.col)) {
					Location1[hoo1.row][hoo1.col]=false;
					hoo1.onBoard=false;
					gameBoard.getChildren().remove(hoo1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead) {
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo11.onBoard&&(pawn22.row==hoo11.row-1)&&(pawn22.col==hoo11.col)) {
					Location1[hoo11.row][hoo11.col]=false;
					hoo11.onBoard=false;
					gameBoard.getChildren().remove(hoo11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead) {
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}			
				}
			}
			else if(whoClicked[16]) {
				if((hoo2.row==king1.row)&&(hoo2.col==king1.col)) {
					king1.onBoard = false;
				}
				else if(elephant1.onBoard&&(hoo2.row==elephant1.row)&&(hoo2.col==elephant1.col)) {
					Location1[elephant1.row][elephant1.col]=false;
					elephant1.onBoard=false;
					gameBoard.getChildren().remove(elephant1.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead){
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(elephant11.onBoard&&(hoo2.row==elephant11.row)&&(hoo2.col==elephant11.col)) {
					Location1[elephant11.row][elephant11.col]=false;
					elephant11.onBoard=false;
					gameBoard.getChildren().remove(elephant11.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead){
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(rook1.onBoard&&(hoo2.row==rook1.row)&&(hoo2.col==rook1.col)) {
					Location1[rook1.row][rook1.col]=false;
					rook1.onBoard=false;
					gameBoard.getChildren().remove(rook1.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead){
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(rook11.onBoard&&(hoo2.row==rook11.row)&&(hoo2.col==rook11.col)) {
					Location1[rook11.row][rook11.col]=false;
					rook11.onBoard=false;
					gameBoard.getChildren().remove(rook11.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead){
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(pawn1.onBoard&&(hoo2.row==pawn1.row)&&(hoo2.col==pawn1.col)) {
					Location1[pawn1.row][pawn1.col]=false;
					pawn1.onBoard=false;
					gameBoard.getChildren().remove(pawn1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(pawn11.onBoard&&(hoo2.row==pawn11.row)&&(hoo2.col==pawn11.col)) {
					Location1[pawn11.row][pawn11.col]=false;
					pawn11.onBoard=false;
					gameBoard.getChildren().remove(pawn11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo1.onBoard&&(hoo2.row==hoo1.row)&&(hoo2.col==hoo1.col)) {
					Location1[hoo1.row][hoo1.col]=false;
					hoo1.onBoard=false;
					gameBoard.getChildren().remove(hoo1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo11.onBoard&&(hoo2.row==hoo11.row)&&(hoo2.col==hoo11.col)) {
					Location1[hoo11.row][hoo11.col]=false;
					hoo11.onBoard=false;
					gameBoard.getChildren().remove(hoo11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
			}
			
			else if(whoClicked[17]) {
				if((hoo22.row==king1.row)&&(hoo22.col==king1.col)) {
					king1.onBoard = false;
				}
				else if(elephant1.onBoard&&(hoo22.row==elephant1.row)&&(hoo22.col==elephant1.col)) {
					Location1[elephant1.row][elephant1.col]=false;
					elephant1.onBoard=false;
					gameBoard.getChildren().remove(elephant1.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead){
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(elephant11.onBoard&&(hoo22.row==elephant11.row)&&(hoo22.col==elephant11.col)) {
					Location1[elephant11.row][elephant11.col]=false;
					elephant11.onBoard=false;
					gameBoard.getChildren().remove(elephant11.pane);
					if(!elephant2.onBoard&&!elephant2.isDead) {
						elephant2.isDead=true;
						elephant2.setSize(elephant2.isDead);
						elephant2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant2.pane);
					}
					else if(!elephant22.onBoard&&!elephant22.isDead){
						elephant22.isDead=true;
						elephant22.setSize(elephant22.isDead);
						elephant22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(elephant22.pane);
					}
				}
				else if(rook1.onBoard&&(hoo22.row==rook1.row)&&(hoo22.col==rook1.col)) {
					Location1[rook1.row][rook1.col]=false;
					rook1.onBoard=false;
					gameBoard.getChildren().remove(rook1.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead){
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(rook11.onBoard&&(hoo22.row==rook11.row)&&(hoo22.col==rook11.col)) {
					Location1[rook11.row][rook11.col]=false;
					rook11.onBoard=false;
					gameBoard.getChildren().remove(rook11.pane);
					if(!rook2.onBoard&&!rook2.isDead) {
						rook2.isDead=true;
						rook2.setSize(rook2.isDead);
						rook2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook2.pane);
					}
					else if(!rook22.onBoard&&!rook22.isDead){
						rook22.isDead=true;
						rook22.setSize(rook22.isDead);
						rook22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(rook22.pane);
					}
				}
				else if(pawn1.onBoard&&(hoo22.row==pawn1.row)&&(hoo22.col==pawn1.col)) {
					Location1[pawn1.row][pawn1.col]=false;
					pawn1.onBoard=false;
					gameBoard.getChildren().remove(pawn1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(pawn11.onBoard&&(hoo22.row==pawn11.row)&&(hoo22.col==pawn11.col)) {
					Location1[pawn11.row][pawn11.col]=false;
					pawn11.onBoard=false;
					gameBoard.getChildren().remove(pawn11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo1.onBoard&&(hoo22.row==hoo1.row)&&(hoo22.col==hoo1.col)) {
					Location1[hoo1.row][hoo1.col]=false;
					hoo1.onBoard=false;
					gameBoard.getChildren().remove(hoo1.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
				else if(hoo11.onBoard&&(hoo22.row==hoo11.row)&&(hoo22.col==hoo11.col)) {
					Location1[hoo11.row][hoo11.col]=false;
					hoo11.onBoard=false;
					gameBoard.getChildren().remove(hoo11.pane);
					if(!pawn2.onBoard&&!pawn2.isDead) {
						pawn2.isDead=true;
						pawn2.setSize(pawn2.isDead);
						pawn2.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn2.pane);
					}
					else if(!pawn22.onBoard&&!pawn22.isDead){
						pawn22.isDead=true;
						pawn22.setSize(pawn22.isDead);
						pawn22.setLocation(emptyDead(),0);
						deadSpace2.getChildren().add(pawn22.pane);
					}
				}
			}
			}
		}
	
	public int emptyDead() {
		if(whoseTurn%2==0) {
			for(int i=0;i<6;i++) {
				if(!dead1[i]) {
					dead1[i]=true;
					return i;
				}
			}
		}
		else {
			for(int i=0;i<6;i++) {
				if(!dead2[i]) {
					dead2[i]=true;
					return i;
				}
			}
		}
		
		return -1;
	}
	
	public void emptyBoard() {
		if(whoseTurn%2==0) {
			for(int i=3;i<12;i++) {
				if(!Location1[i/3][i%3]&&!Location2[i/3][i%3]) {
					addCircle(i+1);
				}
			}
		}
		else {
			for(int i=0;i<9;i++) {
				if(!Location1[i/3][i%3]&&!Location2[i/3][i%3]) {
					addCircle(i+1);
				}
			}
		}
	}
	
	public void checkKing() {
		if(king1.row==0) {
			kingInOtherSide1++;
		}
		else if(king2.row==3) {
			kingInOtherSide2++;
		}
		else {
			kingInOtherSide1=0;
			kingInOtherSide2=0;
		}
	}
	
	public void isWin() {
		if(kingInOtherSide1==2||!king2.onBoard) {
			Text text = new Text("Game Over\n Player1 is win!");
			text.setFont(Font.font("Ink Free", FontWeight.BOLD, FontPosture.ITALIC, 80));
			text.setFill(Color.RED);
			Pane p = new Pane();
			p.getChildren().add(text);
			gameBoard.getChildren().clear();
			deadSpace1.getChildren().clear();
			deadSpace2.getChildren().clear();
			lbPane.getChildren().clear();
			bdPane.setCenter(p);
			bdPane.setPadding(new Insets(400,0,0,225));
		}
		else if(kingInOtherSide2==2||!king1.onBoard) {
			Text text = new Text("Game Over\n Player2 is win!");
			text.setFont(Font.font("Ink Free", FontWeight.BOLD, FontPosture.ITALIC, 80));
			text.setFill(Color.RED);
			Pane p = new Pane();
			p.getChildren().add(text);
			gameBoard.getChildren().clear();
			deadSpace1.getChildren().clear();
			deadSpace2.getChildren().clear();
			lbPane.getChildren().clear();
			bdPane.setCenter(p);
			bdPane.setPadding(new Insets(400,0,0,225));
		}
	}
	
	class Cell extends GridPane{
		public Cell() {}

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
		
		public void addPiece(StackPane p, int col, int row) {
			super.add(p,col,row);
		}
	}
	
	class King1 extends Cell{
		private int row;
		private int col;
		private boolean onBoard = false;
		StackPane pane = new StackPane();
		StackPane p = new StackPane();
		Rectangle rec = new Rectangle(0,0,130,130);
		Text text = new Text("王");
		
		King1(){
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
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
		 
		public void setSize(boolean n) {
			if(!n) {
				rec.setHeight(130);
				rec.setWidth(130);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			}
			else {
				rec.setHeight(65);
				rec.setWidth(65);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 50));
			}
		}
		
	}
	
	class King2 extends Cell{
		private int row;
		private int col;
		private boolean onBoard = false;
		StackPane pane = new StackPane();
		Rectangle rec = new Rectangle(0,0,130,130);
		Text text = new Text("王");
		
		King2(){
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
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
		
		public void setSize(boolean n) {
			if(!n) {
				rec.setHeight(130);
				rec.setWidth(130);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			}
			else {
				rec.setHeight(65);
				rec.setWidth(65);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 50));
			}
		}
		
	}
	
	class Elephant1 extends Cell{
		private int row;
		private int col;
		private boolean isDead = false;
		private boolean onBoard = false;
		StackPane pane = new StackPane();
		Rectangle rec = new Rectangle(0,0,130,130);
		Text text = new Text("相");
		
		Elephant1(){
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
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
		
		public void setSize(boolean n) {
			if(!n) {
				rec.setHeight(130);
				rec.setWidth(130);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			}
			else {
				rec.setHeight(65);
				rec.setWidth(65);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 50));
			}
		}
		
	}
	
	class Elephant2 extends Cell{
		private int row;
		private int col;
		private boolean isDead = false;
		private boolean onBoard = false;
		StackPane pane = new StackPane();
		Rectangle rec = new Rectangle(0,0,130,130);
		Text text = new Text("相");
		
		Elephant2(){	
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
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
		
		public void setSize(boolean n) {
			if(!n) {
				rec.setHeight(130);
				rec.setWidth(130);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			}
			else {
				rec.setHeight(65);
				rec.setWidth(65);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 50));
			}
		}
		
	}
	
	class Rook1 extends Cell{
		private int row;
		private int col;
		private boolean isDead = false;
		private boolean onBoard = false;
		StackPane pane = new StackPane();
		Rectangle rec = new Rectangle(0,0,130,130);
		Text text = new Text("將");
		
		Rook1(){
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
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
		
		public void setSize(boolean n) {
			if(!n) {
				rec.setHeight(130);
				rec.setWidth(130);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			}
			else {
				rec.setHeight(65);
				rec.setWidth(65);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 50));
			}
		}
	}
	
	class Rook2 extends Cell{
		private int row;
		private int col;
		private boolean isDead = false;
		private boolean onBoard = false;
		StackPane pane = new StackPane();
		Rectangle rec = new Rectangle(0,0,130,130);
		Text text = new Text("將");
		
		Rook2(){
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
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
		
		public void setSize(boolean n) {
			if(!n) {
				rec.setHeight(130);
				rec.setWidth(130);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			}
			else {
				rec.setHeight(65);
				rec.setWidth(65);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 50));
			}
		}
	}
	
	class Pawn1 extends Cell{
		private int row;
		private int col;
		private boolean isDead = false;
		private boolean onBoard = false;
		StackPane pane = new StackPane();
		Rectangle rec = new Rectangle(0,0,130,130);
		Text text = new Text("子");
		
		Pawn1(){
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
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
		
		public void setSize(boolean n) {
			if(!n) {
				rec.setHeight(130);
				rec.setWidth(130);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			}
			else {
				rec.setHeight(65);
				rec.setWidth(65);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 50));
			}
		}
	}
	
	class Pawn2 extends Cell{
		private int row;
		private int col;
		private boolean isDead = false;
		private boolean onBoard = false;
		StackPane pane = new StackPane();
		Rectangle rec = new Rectangle(0,0,130,130);
		Text text = new Text("子");
		
		Pawn2(){
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
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
		
		public void setSize(boolean n) {
			if(!n) {
				rec.setHeight(130);
				rec.setWidth(130);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			}
			else {
				rec.setHeight(65);
				rec.setWidth(65);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 50));
			}
		}
	}
	
	class Hoo1 extends Cell{
		private int row;
		private int col;
		private boolean isDead = false;
		private boolean onBoard = false;
		StackPane pane = new StackPane();
		Rectangle rec = new Rectangle(0,0,130,130);
		Text text = new Text("侯");
		
		Hoo1(){
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
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
		
		public void setSize(boolean n) {
			if(!n) {
				rec.setHeight(130);
				rec.setWidth(130);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			}
			else {
				rec.setHeight(65);
				rec.setWidth(65);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 50));
			}
		}
	}
	
	class Hoo2 extends Cell{
		private int row;
		private int col;
		private boolean isDead = false;
		private boolean onBoard = false;
		StackPane pane = new StackPane();
		Rectangle rec = new Rectangle(0,0,130,130);
		Text text = new Text("侯");
		
		Hoo2(){
			rec.setFill(Color.LIGHTGRAY);
			rec.setStroke(Color.BLACK);
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
		
		public void setSize(boolean n) {
			if(!n) {
				rec.setHeight(130);
				rec.setWidth(130);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 100));
			}
			else {
				rec.setHeight(65);
				rec.setWidth(65);
				text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 50));
			}
		}
	}
	
	class ShowCircle extends Cell{
		private int row;
		private int col;
		private int number = (row*3)+(col+1);
		StackPane pane = new StackPane();
		
		ShowCircle() {
			
		}
		
		ShowCircle(int col, int row){
			Circle circle = new Circle(20);
			circle.setStroke(Color.BLACK);
			circle.setFill(Color.RED);
			pane.getChildren().add(circle);
			super.addPiece(pane, col, row);
			this.col = col;
			this.row = row;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return row;
		}
		
		public int getNumber() {
			return number;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}