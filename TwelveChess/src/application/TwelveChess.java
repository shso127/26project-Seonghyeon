package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TwelveChess extends Application{
	public static int isStart =0;
	
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
		
		btStart.setOnAction(e->{
			isStart = 1;
			lbPane.add(tplayer1,0,60);
			lbPane.add(tplayer2,0, 0);
			lbPane.getChildren().removeAll(btStart,btRule);
		}
		);
		
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
	
	public static void main(String[] args) {
		launch(args);
	}

}