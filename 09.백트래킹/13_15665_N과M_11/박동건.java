// N과 M11

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	// 주어지는 수의 개수, 뽑을 숫자 개수
	static int N, M;
	
	// 주어지는 숫자 배열
	static int[] nums;
	
	// 뽑은 숫자 배열
	static int[] selected;
	
	
	static void track(int cnt) {
		
		// base case: M개를 다 고르고 나면
		if (cnt == M) {
			
			// 선택한 배열 출력
			for (int idx = 0; idx < M; idx++) {
				sb.append(selected[idx]).append(" ");
			}
			sb.append("\n");
			
			return;
		}
		
		// recursive case
		int last = 0; // 마지막에 뽑은 숫자 기억하는 변수
		for (int idx = 0; idx < N; idx++) {
			
			// 이전에 뽑은 수와 같은 index면 제외
			if (nums[idx] == last) 
				continue;
			
			// 선택
			selected[cnt] = nums[idx];
			
			// 마지막 선택 기억
			last = nums[idx];
			
			// 다음 숫자 고르기 위해 재귀 호출
			track(cnt+1);
			
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 주어지는 숫자 개수
		N = input.nextInt();
		
		// 뽑을 숫자 개수
		M = input.nextInt();
		
		// 배열 크기 설정
		nums = new int[N];
		selected = new int[M];
		
		// 배열 정보 입력
		for (int idx = 0; idx < N; idx++) 
			nums[idx] = input.nextInt();
		
		// 배열 정렬
		Arrays.sort(nums);
		
		// 해당하는 모든 경우의 수 뽑기
		track(0);
		
		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}