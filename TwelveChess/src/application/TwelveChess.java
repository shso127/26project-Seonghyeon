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
		primaryStage.setTitle("�������");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}