// 별찍기11

import java.util.Scanner;

public class Main {
	// 전체 map
	static char[][] map;
	
	// 재귀 메소드
	static void recursion(int size, int[] startingpoint) {
		// 시작위치 
		int sr = startingpoint[0];
		int sc = startingpoint[1];
		
		// base case: 높이가 3이 되면 종료
		if (size == 3) {
			// 별 찍기
			for (int r = sr-2; r <= sr; r++) {
				String[] lines = {"  *  ", " * * ", "*****"};
				
				for (int c = sc; c < sc+5; c++) {
					map[r][c] = lines[r-(sr-2)].charAt(c-sc); 
				}
				
			}
			return;
		}
		
		// recursive case : 해당 newpoint에서 재귀 진행
		int[][] newpoints = {{sr, sc}, {sr - size/2, sc + size/2}, {sr, sc + size}};
		for (int i = 0; i < 3; i++) {
			int nr = newpoints[i][0];
			int nc = newpoints[i][1];
			int[] np = {nr, nc};
			recursion(size/2, np);
		}
		
		
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 전체 크기
		int N = input.nextInt();
		
		// 배열 크기 설정
		map = new char[N][2*N];
		
		// 재귀 on
		int[] startingpoint = {N-1, 0};
		recursion(N, startingpoint);
		
		// 12+6+3
		
		// 별찍기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < 2*N; c++) {
				// 별이면 별 찍기
				if (map[r][c] == '*') sb.append("*");
				// 아니면 공백 처리
				else sb.append(" ");
			}
			// 개행문자 입력
			sb.append("\n");
		}
		
		// 출력
		System.out.println(sb.toString());
	}
}