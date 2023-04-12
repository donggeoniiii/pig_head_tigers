// N과 M8

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	/**
	 *  주어지는 숫자 개수
	 */
	static int N;
	
	/**
	 *  고르는 숫자 개수
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
	 *  탐색 알고리즘
	 * @param cnt 현재까지 선택한 숫자 개수
	 */
	private static void track(int cnt) {
		// base case: M개의 숫자를 고르고 나면
		if (cnt == M) {
			
			// 조건에 맞는 수열인지 확인
			for (int idx = 0; idx < M; idx++) {
				if (idx == 0) continue;
				
				// 내림차순인 부분이 있으면 조건에 맞지 않으므로 해당 경우 제외
				if (selected[idx-1] > selected[idx]) return;
			}
			
			// 조건에 맞는 경우 stringbuilder에 추가
			for (int idx = 0; idx < M; idx++) {
				sb.append(selected[idx]).append(" ");
			}
			sb.append("\n");
			
			// 끝나면 return!!!!!!!!!!!!!!!!!!!!
			return;
		}
		
		// recursive case
		for (int idx = 0; idx < N; idx++) {
			
			// 중복해서 선택해도 되니까 체크 안해도 ㄱㅊ
			selected[cnt] = nums[idx]; 
			
			// 다음 선택을 위해 이동
			track(cnt+1);
			
		}
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 입력
		N = input.nextInt();
		M = input.nextInt();
		
		// 배열크기 지정
		nums = new int[N];
		selected = new int[M];
	
		// 숫자 입력하고
		for (int idx = 0; idx < N; idx++) nums[idx] = input.nextInt();
		
		// 사전식 출력을 위해 정렬
		Arrays.sort(nums);
		
		// 탐색 on
		track(0);
		
		// 탐색이 끝나면 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}