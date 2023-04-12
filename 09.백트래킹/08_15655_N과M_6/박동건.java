// N과 M 6

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	/**
	 *  주어지는 수의 개수
	 */
	static int N;
	
	/**
	 *  선택할 숫자 개수
	 */
	static int M;
	
	/**
	 *  주어진 수 배열
	 */
	static int[] nums;
	
	/**
	 *  선택한 숫자 배열
	 */
	static int[] selected;
	
	/**
	 *  숫자 확인여부 저장배열
	 */
	static boolean[] checked;
	
	/**
	 *  탐색 알고리즘
	 * @param cnt 현재까지 선택한 숫자 개수
	 */
	private static void track(int cnt) {
		
		// base case: M개를 선택하고 나면
		if (cnt == M) {
			
			// 조건을 충족하는지 확인
			for (int idx = 0; idx < M; idx++) {
				if (idx == 0) continue;
				// 오름차순 수열이 아니면 해당 경우 제외
				if (selected[idx-1] > selected[idx]) return;
			}
			
			// 조건을 충족하면 출력
			for (int idx = 0; idx < M; idx++) {
				sb.append(selected[idx]).append(" ");
			}
			sb.append("\n");
			
			// 다 했으면 return!!!!!!!!!!!!!!!!!!!!!!!
			return;
		}
		
		// recursive case
		for (int idx = 0; idx < N; idx++) {
			
			// 이미 고려한 숫자면 스킵
			if (checked[idx]) continue;
			
			// 아니면 이번 차례에 선택
			selected[cnt] = nums[idx];
			
			// 선택 체크
			checked[idx] = true;
			
			// 해당 숫자를 선택한 상황에서 다음 선택을 위해 이동
			track(cnt+1);
			
			// 해당 숫자를 해당 순번에 담는 경우는 모두 고려됐으므로 다른 경우를 위해 선택 체크 해제
			checked[idx] = false;
			
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
		
		// 사전식 출력을 위해 정렬
		Arrays.sort(nums);
		
		// 탐색 on
		track(0);
		
		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}