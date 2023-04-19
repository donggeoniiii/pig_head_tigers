// 로봇청소기

import java.util.Scanner;

public class Main {
	
	// 배열의 크기
	static int N, M;
	
	// 배열
	static int[][] map;
	
	// 방문배열
	static boolean[][] visited;
	
	// 델타배열, 시계방향
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	
	// index가 벗어나는지 확인하는 메소드
	static boolean outOfIndex(int r, int c) {
		return r >= N || c >= M || r < 0 || c < 0;
	}
	
	// 시스템 스택을 이용한 DFS
	static int dfs(int cr, int cc, int dir, int cnt) {
		
		// 주변돌면서 확인, 반시계방향으로 먼저 회전하고 봐야하니까 dt는 0~3의 값을 가지고 3에서 0으로 감소
		for (int dt = 3; dt >= 0; dt--) {
			
			// 새로운 좌표, 인덱싱을 위해 나머지 연산
			int nr = cr + dr[(dt+dir) % 4];
			int nc = cc + dc[(dt+dir) % 4];
			
			// index를 벗어나면 스킵
			if (outOfIndex(nr, nc))
				continue;
			
			// 갈 수 없으면 스킵
			if (visited[nr][nc])
				continue;
			
			// 가능한 부분 찾았으면 전진하고 청소한 다음 넘어가기
			visited[nr][nc] = true; 
			
			return dfs(nr, nc, (dt+dir) % 4, cnt+1);
			
		}
		
		// 만약 이동할 수 있는 지역이 없어서 나왔으면
		// 뒤로 한칸 이동하게 확인
		int nr = cr + dr[(dir+2) % 4];
		int nc = cc + dc[(dir+2) % 4];
		
		
		
		// 뒤가 벽이 아니면 
		if (!outOfIndex(nr,nc) && map[nr][nc] != 1) {
			
			// 이동할 수 있으므로 이동
			return dfs(nr, nc, dir, cnt);
		}
		
		// 뒤가 벽이라 갈 수 없으면 끝난거니까 지금까지 카운트 반환
		return cnt;
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 배열 크기 입력
		N = input.nextInt();
		M = input.nextInt();
		
		// 로봇청소기 좌표
		int sr = input.nextInt();
		int sc = input.nextInt();
		
		// 시작방향
		int dir = input.nextInt();
		
		// 배열 생성
		map = new int[N][M];
		visited = new boolean[N][M];
		
		// 배열 입력하기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = input.nextInt();

				// 만약 벽이면 바로 방문처리
				if (map[r][c] == 1)
					visited[r][c] = true; 
			}
		}
		
		// 시작점 방문 체크
		visited[sr][sc] = true; 
		
		// 카운트 세기(시작점 닦았으니까 카운트 시작은 1)
		int total = dfs(sr, sc, dir, 1);
		
		// 출력
		System.out.println(total);
		
		input.close();
	}
}