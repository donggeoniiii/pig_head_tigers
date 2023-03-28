// 부분수열의 합

import java.util.Scanner;

public class Main {
	// 정수의 개수
	static int N;
	
	// 원하는 합
	static int S;
	
	// S가 되는 부분집합 카운트
	static int sCnt;
	
	// 부분집합의 모음
	static int[] powerset;
	
	// 탐색 알고리즘
	public static void track(int cnt, int sum) {
		// base case: 모든 원소를 고려하고 나면 종료
		if (cnt == N) {
			// 만약 합이 S면 카운트 증가
			if (sum == S) sCnt++;
			return;
		}

		// recursive case
		// 해당 원소 안 추가
		track(cnt+1, sum);
		
		// 해당 원소 추가
		track(cnt+1, sum + powerset[cnt]);
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 정수의 개수
		N = input.nextInt();
		
		// target 합
		S = input.nextInt();
		
		// 배열 크기 설정
		powerset = new int[N];
		
		// 정수 입력
		for (int idx = 0; idx < N; idx++) powerset[idx] = input.nextInt(); 
		
		// 탐색 on
		track(0, 0);

		// 만약 target 합이 0이면 공집합 제외
		if (S == 0) sCnt--;
		
		// 정답 출력
		System.out.println(sCnt);
		
		input.close();
	}
}