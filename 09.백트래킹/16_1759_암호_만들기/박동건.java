// 암호 만들기

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	// 암호의 길이 
	static int L;
	
	// 사용하는 단어 개수
	static int C;
	
	// 전체 단어 배열
	static char[] chars;
	
	// 선택한 단어 배열
	static char[] selected;
	
	// 백트래킹 알고리즘
	static void track(int cnt, int vCnt, int start) {
		
		// base case: 뽑은 단어가 L개면
		if (cnt == L) {
			
			// 모음의 개수가 1개 이상이면 출력
			if (vCnt >= 1 && cnt-vCnt >= 2) {
				for (int idx = 0; idx < L; idx++) sb.append(selected[idx]);

				sb.append("\n");
			}
			
			return;
		}
		
		
		// recursive case
		for (int idx = start; idx < C; idx++) {
			
			// 모음 카운트
			int vowel = 0;
			
			// 숫자 선택
			selected[cnt] = chars[idx]; 
			
			// 만약 모음이면 모음카운트 증가
			if (selected[cnt] == 'a' || selected[cnt] == 'e' || selected[cnt] == 'i' || selected[cnt] == 'o' || selected[cnt] == 'u')
				vowel++;

			// 다음 선택을 위해 이동
			track(cnt+1, vCnt+vowel, idx+1);
			
			
		}
		
	}
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st;
		
		// 암호의 길이, 사용하는 단어 개수
		st = new StringTokenizer(input.nextLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 배열 크기설정
		chars = new char[C];
		selected = new char[L];
		
		// 단어 입력
		st = new StringTokenizer(input.nextLine());
		for (int idx = 0; idx < C; idx++) chars[idx] = st.nextToken().charAt(0);
		
		// 정렬
		Arrays.sort(chars);
		
		// 단어찾기
		track(0, 0, 0);
		
		// 출력
		System.out.println(sb.toString());
		
		
		input.close();
	}
}