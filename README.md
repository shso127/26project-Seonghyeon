# 26project-Seonghyeon

프로젝트형 스터디 진행 계획서

이름 : 소성현 
학번 : 18100437   

제목 : 십이장기

주제 설명
1) 프로젝트 개요
   
   더 지니어스에서 나온 게임 중 하나로 십이장기 게임을 프로그래밍 한다.

2) 주요 기능
   
   말의 이동 구현, 게임 규칙 적용, 말의 이동시 이동 가능 위치 표시, 승리 조건 구현,      포로로 잡힌 말을 놓을 수 있는 위치 구현, 제한시간 타이머 적용 등등
 

개발 환경 : javafx

1주차 javafx의 class 공부

2주차 javafx의 class 공부

3주차 게임판 제작

4주차 기물 이동 구현

5주차 포로로 잡힌 기물 구현

6주차 게임 규칙 적용(이동 및 위치 가능한 구역 설정)

7주차 승리 조건 적용 및 기타 시간제한 등 추가 설정 요건 구현

8주차 십이장기 게임 시연

# 8/14 십이장기 판 제작

![십이장기 인터페이스1](https://user-images.githubusercontent.com/102570051/184538564-d8068644-84d3-4800-a5aa-24fe923b8fa0.png)  
+ GridPane을 상속 받는 Cell class를 만들어서 기본적인 게임판 제작  
+ BorderPane으로 대략적인 레이아웃 형성 

# 8/28 기물이동 및 이동경로 구현
![image1](https://user-images.githubusercontent.com/102570051/187072353-3ba56c49-6340-4c2f-b42a-d0c61b72f3f6.png) 
+ 각각의 기물 class 제작
+ 기물들의 이동 구현

# 9/5 죽은기물처리 및 승리조건 구현
![image2](https://user-images.githubusercontent.com/102570051/188353251-e20eb4d0-effb-423c-9a5c-5721954cd593.png)
![image3](https://user-images.githubusercontent.com/102570051/188353271-ccf48a56-0103-4f34-9904-7066adef0b19.png)
+ 죽었을 때 기물이 죽은공간으로 이동하도록 제작
+ 자(子)기물이 상대진영으로 들어와 있을 때 후(侯)기물로 변하도록 설정
+ 게임이 끝나는 조건 구현
