// N과 M5

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	/**
	 *  주어지는 숫자 개수
	 */
	static int N;
	
	/**
	 *  선택할 수의 개수
	 */
	static int M;
	
	/**
	 *  주어진 숫자 배열
	 */
	static int[] nums;
	
	/**
	 *  선택한 숫자 배열
	 */
	static int[] selected;
	
	/**
	 *  고려한 숫자 체크하는 배열
	 */
	static boolean[] checked;
	
	
	/**
	 *  탐색 알고리즘
	 * @param cnt 현재까지 선택한 숫자 개수
	 */
	private static void track(int cnt) {
		
		// base case: M개를 다 선택하고 나면
		if (cnt == M) {
			
			// 출력을 위해 추가
			for (int idx = 0; idx < M; idx++) {
				sb.append(selected[idx]).append(" ");
			}
			sb.append("\n");
			
			// 탐색이 끝났으니까 return!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			return;
		}
		
		// recursive case
		for (int idx = 0; idx < N; idx++) {
			
			// 이미 체크한 숫자면 제외
			if (checked[idx]) continue;
			
			// 아니면 해당 숫자 선택
			selected[cnt] = nums[idx];
			
			// 체크표시
			checked[idx] = true;
			
			// 해당 숫자를 넣은 상태에서 다음 선택을 위해 이동
			track(cnt + 1);
			
			// 해당 숫자를 넣은 경우 모두 체크했으니까 다음 경우를 보기 위해 체크 해제
			checked[idx] = false; 
		}
		
	}
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 입력
		N = input.nextInt();
		M = input.nextInt();
		
		// 배열 크기 설정
		nums = new int[N];
		selected = new int[M];
		checked = new boolean[N];
		
		// 주어진 숫자 입력
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = input.nextInt(); 
		}
		
		// 사전식 탐색 및 출력을 위한 정렬
		Arrays.sort(nums);

		// 탐색 on
		track(0);
		
		// 정답 출력
		System.out.println(sb.toString());

		input.close();
	}
}