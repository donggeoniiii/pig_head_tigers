// N과 M 4

import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	/**
	 *  수의 범위
	 */
	static int N;
	
	/**
	 *  선택할 숫자 개수
	 */
	static int M;
	
	/**
	 *  선택한 숫자 배열
	 */
	static int[] selected;

	/**
	 *  탐색 알고리즘
	 * @param idx 현재까지 선택한 숫자 개수
	 */
	public static void track(int idx) {
		
		// base case: 선택한 숫자가 M개면
		if (idx == M) {
			
			// 조건에 맞는지 확인
			for (int i = 0; i < M; i++) {
				if (i == 0) continue;
				
				// 내림차순인 구간이 있다면 해당케이스 스킵
				if (selected[i-1] > selected[i]) return;
			}
			
			// 조건에 맞으니 출력
			for (int i = 0; i < M; i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// recursive case
		for (int i = 1; i <= N; i++) {
			
			// 중복선택 가능하므로 체크 안해도 ㄱㅊ
			selected[idx] = i;
			
			// 다음 선택을 위해 이동
			track(idx + 1);
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 입력
		N = input.nextInt();
		M = input.nextInt();
		
		// 배열 초기화
		selected = new int[M];
		
		// 탐색 on
		track(0);
		
		// 출력
		System.out.println(sb.toString());
	}
}