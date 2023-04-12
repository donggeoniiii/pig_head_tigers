// N과 M7

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	// 주어지는 숫자 개수
	static int N;
	
	// 선택할 숫자 개수
	static int M;
	
	// 주어지는 숫자 배열
	static int[] nums;
	
	// 선택한 숫자 배열
	static int[] selected;
	
	
	// 탐색 알고리즘
	private static void track(int cnt) {
		// base case: M개를 다 고르고 나면
		if (cnt == M) {
			// 출력을 위해 추가
			for (int idx = 0; idx < M; idx++) {
				sb.append(selected[idx]).append(" ");
			}
			sb.append("\n");
			
			// 끝났으면 return!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			return;
		}
		
		// recursive case
		for (int idx = 0; idx < N; idx++) {
			
			// 중복해도 되니까 체크 안해도 ㄱㅊ
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
		
		// 배열크기 설정
		nums = new int[N];
		selected = new int[M];
		
		// 숫자 입력
		for (int idx = 0; idx < N; idx++) nums[idx] = input.nextInt();
		
		// 사전식 출력을 위해 숫자 정렬
		Arrays.sort(nums);
		
		track(0);
		
		System.out.println(sb.toString());
		
		input.close();
	}
}