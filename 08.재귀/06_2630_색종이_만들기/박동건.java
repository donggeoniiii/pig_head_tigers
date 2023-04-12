// 색종이 만들기

import java.util.Scanner;

public class Main {
	// 종이의 전체 크기
	static int N;
	
	// 전체 종이의 칸별 색깔 배열
	static int[][] color;
	
	// 파란색 종이 수
	static int bCnt;
	
	// 흰색 종이 수
	static int wCnt;
	
	// 재귀함수
	static void recursion(int size, int[] startingpoint) {
		// 시작칸 정보 받아오기
		int sr = startingpoint[0]; // starting row
		int sc = startingpoint[1]; // starting col
			
		// 시작칸의 색과 같은 칸수 카운트 초기화
		int cnt = 0;
		
		for (int r = sr; r < sr + size; r++) {
			for (int c = sc; c < sc + size; c++) {
				// 만약 시작칸과 탐색지점의 칸 색이 같으면 카운트 증가
				if (color[r][c] == color[sr][sc]) cnt++;
			}
		}
		
		
		// 만약 카운트가 size^2와 같다면
		if (cnt == Math.pow(size, 2)) {

			// 시작칸의 색깔 확인하고 색깔별 종이 카운트 올리기: 0이면 흰색+1, 1이면 파란색+1
			switch (color[sr][sc]) {
			case 0:
				wCnt++;
				break;
			case 1:
				bCnt++;
				break;
			}	
		} 
		else { // 아니면
			
			// 한 변의 길이 2로 나누고 4방향으로 쪼개기
			for (int r = 0; r <= 1; r++) {
				for (int c = 0; c <= 1; c++) {
					int nr = sr + r * size/2;
					int nc = sc + c * size/2;
					int[] newpoint = {nr, nc};
					recursion(size/2, newpoint);
				}
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 종이의 크기
		int N = input.nextInt();
		
		// 배열 크기 설정
		color = new int[N][N];
		
		// 배열 칸별 색입력
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				color[r][c] = input.nextInt();
			}
		}
		
		// 0,0에서 시작
		int[] startingpoint = {0,0};
		recursion(N, startingpoint);
		
		// 정답 출력
		sb.append(wCnt).append("\n").append(bCnt);
		System.out.println(sb.toString());
	}
}