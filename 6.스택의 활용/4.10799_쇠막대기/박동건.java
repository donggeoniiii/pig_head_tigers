// 쇠막대기

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// 괄호(parentheses) 문자열 입력
		String p = input.nextLine();
		
		// 보기 편하게 하기 위해 레이저는 직관적으로 일직선 ('|')으로 표시
		p = p.replace("()", "|");
		
		// ( 카운트
		int lCnt = 0;
		// ) 카운트
		int rCnt = 0;
		// 쇠막대기 조각 총 개수 카운트
		int total = 0;
		
		// 레이저+쇠막대기 배열 순회
		char[] signs = p.toCharArray();

		for (int i = 0; i < signs.length; i++) {
		
			// (, ) 카운트
			if (signs[i] == '(') lCnt++;
			else if (signs[i] == ')') rCnt++;
			
			// 만약 레이저를 만나면  
			else {
				// 그 전까지 이어지고 있던 파이프의 개수 + 끝났지만 직전 레이저에 의해 잘려나온 조각, 곧 '('의 개수만큼 잘리는 막대기 조각 수 합산 
				total += lCnt;
				// 다음 레이저로 가기 전 끝나지 않은 파이프의 개수의 숫자를 알기 위해 == ')'의 개수만큼 lCnt 감소 
				lCnt -= rCnt;
				// 파이프가 이어지는지 반영이 됐으므로 rCnt 초기화
				rCnt = 0;	
			}
			
			// 마지막까지 왔다면 
			if (i == signs.length-1) {
				// 마지막까지 안 닫힌 파이프 개수 == rCnt 개수만큼 합산
				total += rCnt;
			}
			
		}
		
		// 정답 출력
		System.out.println(total);
		
	}
}