package application;

import javafx.application.Application;
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
	
	private boolean []whoClicked = {false,false,false,false,false,false,false,false,false,false};//왕1,왕2,상1,상2,룩1,룩2,폰1,폰2,후1,후2
	private boolean [][]Location1 = {{false,false,false},{false,false,false},{false,true,false},{true,true,true}};//플레이어1의 말이 어디에 존재하는지 확인
	private boolean [][]Location2 = {{true,true,true},{false,true,false},{false,false,false},{false,false,false}};//플레이어2의 말이 어디에 존재하는지 확인
	private int whoseTurn = 0;
	
	Cell gameBoard = new Cell(4,3,150);
	Cell deadSpace1 = new Cell(1,6,75);
	Cell deadSpace2 = new Cell(1,6,75);
	King1 king1 = new King1();
	King2 king2 = new King2();
	Elephant1 elephant1 = new Elephant1();
	Elephant2 elephant2 = new Elephant2();
	Rook1 rook1 = new Rook1();
	Rook2 rook2 = new Rook2();
	Pawn1 pawn1 = new Pawn1();
	Pawn2 pawn2 = new Pawn2();
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
	Text turn = new Text("Player1's turn");
	
	@Override
	public void start(Stage primaryStage) {

		
		gameBoard.setAlignment(Pos.CENTER_LEFT);
		
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
			if(!whoClicked[0]&& whoseTurn%2==0) {
				whoClicked[0] = true;
				moveable();
			}
			else {
				removeCircle();
				for(int i=0;i<8;i++) {
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
				for(int i=0;i<8;i++) {
					whoClicked[i] = false;
					}
				}
		});
		
		elephant1.pane.setOnMouseClicked(e->{
			if(!whoClicked[2]&& whoseTurn%2==0) {
				whoClicked[2] = true;
				moveable();
				}
			else {
				removeCircle();
				for(int i=0;i<8;i++) {
					whoClicked[i] = false;
					}
				}
		});
		
		elephant2.pane.setOnMouseClicked(e->{
			if(!whoClicked[3]&& whoseTurn%2==1) {
				whoClicked[3] = true;
				moveable();
				}
			else {
				removeCircle();
				for(int i=0;i<8;i++) {
					whoClicked[i] = false;
					}
				}
		});
		
		rook1.pane.setOnMouseClicked(e->{
			if(!whoClicked[4]&& whoseTurn%2==0) {
				whoClicked[4] = true;
				moveable();
				}
			else {
				removeCircle();
				for(int i=0;i<8;i++) {
					whoClicked[i] = false;
					}
				}
		});
		
		rook2.pane.setOnMouseClicked(e->{
			if(!whoClicked[5]&& whoseTurn%2==1) {
				whoClicked[5] = true;
				moveable();
				}
			else {
				removeCircle();
				for(int i=0;i<8;i++) {
					whoClicked[i] = false;
					}
				}
		});
		
		pawn1.pane.setOnMouseClicked(e->{
			if(!whoClicked[6]&& whoseTurn%2==0) {
				whoClicked[6] = true;
				moveable();
				}
			else {
				removeCircle();
				for(int i=0;i<8;i++) {
					whoClicked[i] = false;
					}
				}
		});
		
		pawn2.pane.setOnMouseClicked(e->{
			if(!whoClicked[7]&& whoseTurn%2==1) {
				whoClicked[7] = true;
				moveable();
				}
			else {
				removeCircle();
				for(int i=0;i<8;i++) {
					whoClicked[i] = false;
					}
				}
		});
		
		circle1.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),0);
			for(int i=0;i<8;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle2.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),1);
			for(int i=0;i<8;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle3.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),2);
			for(int i=0;i<8;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle4.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),3);
			for(int i=0;i<8;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle5.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),4);
			for(int i=0;i<8;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle6.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),5);
			for(int i=0;i<8;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle7.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),6);
			for(int i=0;i<8;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle8.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),7);
			for(int i=0;i<8;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle9.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),8);
			for(int i=0;i<8;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle10.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),9);
			for(int i=0;i<8;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle11.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),10);
			for(int i=0;i<8;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		circle12.pane.setOnMouseClicked(e->{
			circleClicked(findWhoClicked(),11);
			for(int i=0;i<8;i++) {
				whoClicked[i] = false;
				}
			whoseTurn++;
			setText();
		});
		
		BorderPane bdPane = new BorderPane();
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
		for(int i=0;i<10;i++) {
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
			gameBoard.getChildren().add(king1.pane);
			break;
		case 1:
			Location2[king2.row][king2.col]=false;
			Location2[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(king2.pane);
			king2.setLocation(num2%3, num2/3);
			gameBoard.getChildren().add(king2.pane);
			break;
		case 2:
			Location1[elephant1.row][elephant1.col]=false;
			Location1[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(elephant1.pane);
			elephant1.setLocation(num2%3, num2/3);
			gameBoard.getChildren().add(elephant1.pane);
			break;
		case 3:
			Location2[elephant2.row][elephant2.col]=false;
			Location2[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(elephant2.pane);
			elephant2.setLocation(num2%3, num2/3);
			gameBoard.getChildren().add(elephant2.pane);
			break;
		case 4:
			Location1[rook1.row][rook1.col]=false;
			Location1[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(rook1.pane);
			rook1.setLocation(num2%3, num2/3);
			gameBoard.getChildren().add(rook1.pane);
			break;
		case 5:
			Location2[rook2.row][rook2.col]=false;
			Location2[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(rook2.pane);
			rook2.setLocation(num2%3, num2/3);
			gameBoard.getChildren().add(rook2.pane);
			break;
		case 6:
			Location1[pawn1.row][pawn1.col]=false;
			Location1[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(pawn1.pane);
			pawn1.setLocation(num2%3, num2/3);
			gameBoard.getChildren().add(pawn1.pane);
			break;
		case 7:
			Location2[pawn2.row][pawn2.col]=false;
			Location2[num2/3][num2%3]=true;
			removeCircle();
			gameBoard.getChildren().remove(pawn2.pane);
			pawn2.setLocation(num2%3, num2/3);
			gameBoard.getChildren().add(pawn2.pane);
			break;
		case 8:
			break;
		case 9:
			break;
		}
	}
	
	//움직일수 있는 위치 표시하는 함수
	public void moveable() {
		int num = 1;
		for(int i=0;i<8;i++) {
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
		//플레이어2 상
		case 3:
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
		//플레이어1 룩
		case 4:
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
		//플레이어2 룩
		case 5:
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
		//플레이어1 폰
		case 6:
			if(!Location1[pawn1.row-1][pawn1.col])
				addCircle(3*(pawn1.row-1)+pawn1.col+1);
			break;
		//플레이어2 폰
		case 7:
			if(!Location2[pawn2.row+1][pawn2.col])
				addCircle(3*(pawn2.row+1)+pawn2.col+1);
			break;
		
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
			Text text = new Text("王");
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
			Text text = new Text("王");
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
			Text text = new Text("相");
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
			Text text = new Text("相");
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
			Text text = new Text("將");
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
			Text text = new Text("將");
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
			Text text = new Text("子");
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
			Text text = new Text("子");
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
