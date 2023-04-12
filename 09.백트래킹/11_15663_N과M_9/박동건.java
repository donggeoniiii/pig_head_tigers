//N과 M9

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	/** 
	 * 주어지는 수의 개수 
	 */
	static int N;
	
	/**
	 *  고르는 수의 개수
	 */
	static int M;
	
	/**
	 *  주어지는 숫자 배열
	 */
	static int[] nums;
	
	/**
	 *  선택한 숫자 배열
	 */
	static int[] selected;
	
	/**
	 * 추가했는지 확인하는 배열
	 */
	static boolean[] checked;
	
	/**
	 * 탐색 알고리즘
	 * @param cnt 현재까지 선택한 숫자 개수 
	 * @param sdx 다음 숫자를 고르기 시작할 index (start index)
	 */
	static void track(int cnt, int sdx) {
		
		// base case: M개를 선택하면
		if (cnt == M) {
			// 출력
			for (int idx = 0; idx < M; idx++) {
				sb.append(selected[idx]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// 마지막에 고른 수
		int last = 0;
		
		// recursive case
		for (int i = 0; i < N; i++) {
			// 이미 선택한 경우 제외
			if (checked[i]) continue;
			
			// 만약 이전 좌표값과 같으면 제외
			if (i != 0 && nums[i] == last) continue;
			
			// 선택
			checked[i] = true;
			
			// 마지막에 고른 수 저장
			last = nums[i];
			
			selected[cnt] = nums[i];
			
			// 다음 선택을 위해 이동
			track(cnt+1, sdx);
			
			// 선택 해제
			checked[i] = false;
			
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 입력
		N = input.nextInt();
		M = input.nextInt();
		
		// 배열 크기 입력
		nums = new int[N];
		selected = new int[M];
		checked = new boolean[N];
		
		// 숫자 입력
		for (int idx = 0; idx < N; idx++) nums[idx] = input.nextInt();
	
		// 오름차순 출력을 휘해 정렬
		Arrays.sort(nums);
		
		// 탐색 on
		track(0, 0);
		
		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}