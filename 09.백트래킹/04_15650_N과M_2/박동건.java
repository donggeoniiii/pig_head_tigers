// N과 M 2

import java.util.Scanner;

public class Main {
	/** 
	 *  숫자의 개수
	 */
	static int N;
	
	/**
	 *  뽑을 숫자 개수
	 */
	static int M;
	
	/**
	 *  숫자 전체 배열 (1~N)
	 */
	static int[] nums;
	
	/** 
	 *  선택한 숫자 배열 
	 */
	static int[] select;
	
	/**
	 *  체크 표시 배열
	 */
	static boolean[] checked;
	
	
	/**
	 *  순열 알고리즘
	 * @param cnt 현재까지 선택한 숫자 수
	 */
	public static void numSelect(int cnt) {
		// base case: cnt == M이면
		if (cnt == M) {

			// 선택한 숫자들 출력
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < M; i++) {
				
				// 0일 때는 그냥 추가만 하고 스킵
				if (i == 0) {
					sb.append(select[i]).append(" "); 
					continue;
				}
				else {
					// 이후 감소하는 부분 등장하면 종료
					if (select[i-1] > select[i]) return;
					sb.append(select[i]).append(" ");
				}
			}
			
			// 통과하면 출력
			System.out.println(sb.toString());	
			return;
		}
		
		// recursive case
		for (int idx = 1; idx <= N; idx++) {
			
			// 이미 체크한 수면 스킵
			if (!checked[idx]) {

				// 숫자 선택
				select[cnt] = nums[idx];

				// 체크표시 
				checked[idx] = true;
				
				// 다음 선택으로 이동
				numSelect(cnt+1); // 선택
				
				// 체크표시 해제
				checked[idx] = false;	
			}
			
		}
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 숫자 개수
		N = input.nextInt();
		
		// 뽑을 숫자 개수
		M = input.nextInt();
		
		// 배열 설정
		nums = new int[N+1]; 
		select = new int[M];
		checked = new boolean[N+1];
		
		for (int i = 0; i <= N; i++) nums[i] = i;
		
		// 탐색 on
		numSelect(0);
		
		input.close();
	}
}