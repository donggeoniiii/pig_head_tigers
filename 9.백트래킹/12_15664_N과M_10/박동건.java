import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	
	static int M;
	
	static int[] nums;
	
	static int[] selected;
	
	static boolean[] checked;
	
	static void track(int cnt, int start) {
		// base case: 뽑은 숫자가 M개면
		if (cnt == M) {
			
			// 뽑은 숫자 출력
			for (int idx = 0; idx < M; idx++) {
				sb.append(selected[idx]).append(" ");
			}
			sb.append("\n");
			
			// 끝났으면 return!!!!!!!!!!!!!!!!!!!!!!!
			return;
		}
		
		// 마지막에 뽑은 숫자
		int last = 0;
		
		// recursive case 
		for (int idx = start; idx < N; idx++) {
			
			// 선택한 수 제외
			if (checked[idx]) continue;
			
			// 만약 이전에 뽑은 숫자랑 같은 숫자면 제외
			if (nums[idx] == last) continue;
			
			// 숫자 선택
			selected[cnt] = nums[idx];
			
			// 선택표시
			checked[idx] = true; 
			
			// 마지막 선택 기록
			last = nums[idx];
			
			// 다음 선택을 위해 이동
			track(cnt+1, idx+1);
			
			// 다른 경우를 위해 체크 해제
			checked[idx] = false;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 주어지는 숫자 개수
		N = input.nextInt();
		
		// 뽑을 숫자 개수
		M = input.nextInt();
		
		// 배열크기 초기화
		nums = new int[N];
		selected = new int[M];
		checked = new boolean[N];
		
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = input.nextInt();
		}
		
		// 배열 정렬
		Arrays.sort(nums);
		
		// 조건에 맞는 배열 찾기
		track(0, 0);
		
		// 정답 출력
		System.out.println(sb.toString());
		
		
		input.close();
	}
}