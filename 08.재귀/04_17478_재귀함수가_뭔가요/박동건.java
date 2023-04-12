//재귀함수가 뭔가요?

import java.util.Scanner;


public class Main {
	
	// 재귀함수
	static void whatIsRecursion(int n, int cur) {
		// 반복 진행 횟수에 맞게 들여쓰기
		for (int indent = 0; indent < 4*(cur-1); indent++) System.out.print("_");
		System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		for (int indent = 0; indent < 4*(cur-1); indent++) System.out.print("_");
		System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지."); 
		for (int indent = 0; indent < 4*(cur-1); indent++) System.out.print("_");
		System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
		for (int indent = 0; indent < 4*cur; indent++) System.out.print("_");
		System.out.println("\"재귀함수가 뭔가요?\"");
		
		// 같아지면 답변
		if (cur == n) {
			for (int indent = 0; indent < 4*cur; indent++) System.out.print("_");
			System.out.println("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			for (int indent = 0; indent < 4*cur; indent++) System.out.print("_");
			System.out.println("라고 답변하였지.");
		}
		// 아니면 한번 더
		else whatIsRecursion(n, cur+1);
		
		
		for (int indent = 0; indent < 4*(cur-1); indent++) System.out.print("_");
		System.out.println("라고 답변하였지.");
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 재귀함수 반복수
		int N = input.nextInt();
		
		// 시작하는 문장
		String intro = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n"
				+ "\"재귀함수가 뭔가요?\"";
		
		// 출력
		System.out.println(intro);
		whatIsRecursion(N, 1);
		
	}
}