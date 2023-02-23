// 유기농 배추

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * <그림>이랑 완전 비슷하다. 아니 오히려 쉬워졌다..
 * BFS를 새로 시작할 때마다 카운트를 올리면 된다.
 */

public class Main {
	// 배추지을 땅
	static int[][] map;
	
	// 가로 세로 배추 개수
	static int N, M, K;
	
	// 방문여부 체크
	static boolean[][] visited;
	
	// 델타배열
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	
	
	// bfs 알고리즘
	static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		
		// 시작점 추가
		queue.offer(new int[] {r,c});
		
		// 큐에 추가했으면 해당 좌표는 또 확인하지 않도록 방문체크
		visited[r][c] = true;
		
		// 더 탐색할 좌표가 없을 때까지
		while (!queue.isEmpty()) {
			
			// 탐색할 좌표 데이터를 queue의 head에서 받아온다
			int[] cur = queue.poll();
			
			// 델타배열로 사방탐색
			for (int dt = 0; dt < 4; dt++) {
				
				// 새로운 index로 접근
				int nr = cur[0] + delta[dt][0];
				int nc = cur[1] + delta[dt][1];
				
				// 이미 방문했거나 map 배열을 벗어나면 스킵
				if (nr >= N || nc >= M || nr < 0 || nc < 0 || visited[nr][nc]) continue;
				
				// 만약 좌표가 그냥 땅이 아니라 배추면
				if (map[nr][nc] == 1) {
					
					// 다음에 이 지점을 기준으로 또 탐색하기 위해 좌표 추가
					queue.offer(new int[] {nr,nc});
					
					// 큐에 추가했으면 해당 좌표는 또 확인하지 않도록 방문체크
					visited[nr][nc] = true;
				}
				
			}
			
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 땅크기 입력
			N = input.nextInt();
			M = input.nextInt();
			
			// 배추 개수 입력
			K = input.nextInt();
			
			// 크기에 맞춰 배열 초기화
			map = new int[N][M];
			visited = new boolean[N][M];
			
			// 배추 위치 입력
			for (int k = 1; k <= K; k++) {
				int r = input.nextInt();
				int c = input.nextInt();
				
				map[r][c] = 1;
			}
			
			// 연결된 배추 뭉치(?) 카운트
			int bCnt = 0;
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					// 만약 배추를 찾았는데 안가본 위치라면
					if (map[r][c] == 1 && !visited[r][c]) {
						// bfs on
						bfs(r,c);
						
						// 카운트 +1
						bCnt++;
						
					}
				}
			}
			
			// 정답 출력
			System.out.println(bCnt);
			
		}

	}
}