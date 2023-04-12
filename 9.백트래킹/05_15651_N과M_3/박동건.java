// N과 M 3

import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	/**
	 *  전체 숫자
	 */
	static int N;
	
	/**
	 *  선택할 숫자
	 */
	static int M;
	
	/**
	 *  고른 숫자 배열
	 */
	static int[] selected;
	
	/**
	 * 탐색 알고리즘
	 * @param select 선택한 숫자 개수
	 */
	public static void track(int select) {
		// base case: 선택한 숫자가 M개라면
		if (select == M) {
			
			// 출력
			
			for (int idx = 0; idx < M; idx++) {
				sb.append(selected[idx]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// recursive case
		for (int idx = 1; idx <= N; idx++) {
			
			// 중복을 허용하므로 체크여부 판단 안해도 ㄱㅊ
			selected[select] = idx;  
			
			// 다음 선택으로 이동
			track(select + 1);
		}
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 입력
		N = input.nextInt();
		M = input.nextInt();
		
		// 배열 크기 설정
		selected = new int[M];
		
		// 탐색 on
		track(0);
		
		// 정답 출력
		System.out.println(sb.toString());
		
	}
}