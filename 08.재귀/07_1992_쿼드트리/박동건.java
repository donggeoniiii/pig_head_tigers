// 쿼드트리

import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	
	// 흑백 영상 전체
	static int[][] video;
	
	static void recursion(int size, int[] startingpoint) {
		
		// 시작지점 정보 받아오기
		int sr = startingpoint[0];
		int sc = startingpoint[1];
		
		// 시작지점의 색깔을 기준으로 한 카운트
		int cnt = 0;
		
		// size만큼 돌면서 시작지점의 색깔과 같은 색깔을 가지는 지점 수 카운트
		for (int r = sr; r < sr + size; r++) {
			for (int c = sc; c < sc + size; c++) {
				
				// 만약 시작점의 색과 같다면 카운트 증가
				if (video[r][c] == video[sr][sc]) cnt++;
				
			}
		}
		
		// 만약 카운트가 size^2와 같으면 
		if (cnt == Math.pow(size, 2)) {
			
			// 시작점의 색깔을 보고 색깔 입력
			switch (video[sr][sc]) {
			case 0:
				sb.append("0");
				break;
			case 1:
				sb.append("1");
				break;
			}
		}
		else { // cnt != Math.pow(size, 2)
			
			// 쪼개지는거 표시하기 위해 괄호 입력
			sb.append("(");
			
			// 4분할, 위 -> 아래, 왼쪽 -> 오른쪽 순으로
			for (int r = 0; r <= 1; r++) {
				for (int c = 0; c <= 1; c++) {
					int nr = sr + r*size/2;
					int nc = sc + c*size/2;
					int[] newpoint = {nr, nc};
					recursion(size/2, newpoint);
				}
			}
			
			// 다 했으면 괄호 닫기
			sb.append(")");	
		}
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 영상의 크기 N
		int N = Integer.parseInt(input.nextLine());
		
		// 영상의 흑백 데이터 입력 배열
		video = new int[N][N];
		
		// 영상의 흑백 데이터 입력
		for (int r = 0; r < N; r++) {
			String[] line = input.nextLine().split("");
			for (int c = 0; c < N; c++) {
				video[r][c] = Integer.parseInt(line[c]);
			}
		}
		
		// 0,0 부터 탐색 시작해서 결과 추가
		int[] startingpoint = {0,0};
		recursion(N, startingpoint);

		// 정답 출력
		System.out.println(sb.toString());
	}
	
}