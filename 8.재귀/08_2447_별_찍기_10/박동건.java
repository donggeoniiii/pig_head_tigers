// 별찍기10

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	// 전체 맵
	static char[][] map;
	
	static void recursion(int size, int[] startingpoint) {
		// 시작점 정보
		int sr = startingpoint[0];
		int sc = startingpoint[1];
		
		// 만약 size가 1이면 별 찍기
		if (size == 1) map[sr][sc] = '*';
		else { // 아니면
		
			// 9분할 
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					
					// 중앙 지점은 공백
					if (r == 1 && c == 1) continue;
					
					// 새로운 출발지점 지정
					int nr = sr + r*size/3;
					int nc = sc + c*size/3;
					int[] newpoint = {nr, nc};
					
					// 다음 지점으로 재귀 진행
					recursion(size/3, newpoint);
				}
			}	
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 전체 크기 N
		int N = Integer.parseInt(br.readLine());
		
		// 크기에 맞게 입력
		map = new char[N][N];
		
		// 0,0 부터 시작
		int[] startingpoint = {0,0};
		
		// 별찍기
		recursion(N, startingpoint);
		
		// 전체 map 출력
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
                if (map[r][c] == '*') bw.write(map[r][c]);
                else bw.write(' ');
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}
}