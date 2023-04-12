//로또

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	// 로또 번호 후보개수
	static int k;
	
	// 주어지는 숫자 배열
	static int[] nums;
	
	// 뽑은 숫자 배열
	static int[] selected;
	
	static void track(int cnt, int start) {
			
		// base case: 6개를 다 고르고 나면
		if (cnt == 6) {
			
			// 선택한 수열 입력
			for (int idx = 0; idx < 6; idx++) {
				sb.append(selected[idx]).append(" ");
			}
			sb.append("\n");
			
			// 끝났으면 return
			return;
		}
		
		// recursive case: 
		for (int idx = start; idx < k; idx++) {
			
			// 숫자 선택
			selected[cnt] = nums[idx];
			
			// 다음 선택을 위해 이동
			track(cnt+1, idx+1);
			
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st;
		
		while (true) {
			// 테스트케이스 입력
			st = new StringTokenizer(input.nextLine());
			
			// 원소의 개수
			k = Integer.parseInt(st.nextToken());
			
			// 개수가 0이면 종료
			if (k == 0) break;
			
			// 배열 생성
			nums = new int[k];
			
			// 배열에 숫자 입력
			for (int idx = 0; idx < k; idx++) 
				nums[idx] = Integer.parseInt(st.nextToken());
			
			
			// 선택한 숫자 배열
			selected = new int[6]; 
			
			// 가능한 경우의수 뽑기
			track(0, 0);
			
			// 테케 구분을 위해 빈줄 추가
			sb.append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}

}