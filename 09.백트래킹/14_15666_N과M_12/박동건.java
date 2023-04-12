// N과 M12

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	// 주어질 숫자 개수, 뽑을 숫자 개수
	static int N, M;
	
	// 주어지는 숫자 배열
	static int[] nums;
	
	// 뽑은 숫자 배열
	static int[] selected;
	
	static void track(int cnt, int start) {
		
		// base case: 뽑은 숫자가 M개면
		if (cnt == M) {
			
			// 뽑은 수열 출력
			for (int idx = 0; idx < M; idx++) 
				sb.append(selected[idx]).append(" ");
			sb.append("\n");
			
			return;
		}
		
		// recursive case
		int last = 0; // 같은 idx의 같은 수를 여러번 뽑지 않기 위한 마지막에 선택한 숫자 기억 변수
		for (int idx = start; idx < N; idx++) {
			
			// 전에 뽑은 숫자면 제외
			if (nums[idx] == last) continue;
			
			// 숫자 선택
			selected[cnt] = nums[idx];
			
			// 마지막 선택 기억
			last = nums[idx];
			
			// 다음 선택을 위해 재귀호출
			// 비내림차순으로 선택하기 위해, 그리고 본인도 선택가능하게 하도록 start index 전달
			track(cnt+1, idx);
			
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
		
		// 숫자 정보 입력
		for (int idx = 0; idx < N; idx++)
			nums[idx] = input.nextInt();
		
		// 배열 정렬
		Arrays.sort(nums);
		
		// 해당하는 모든 순열 탐색
		track(0, 0);

		// 정답 출력
		System.out.println(sb.toString());
		input.close();
	}
}