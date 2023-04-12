package boj2468_안전영역; // 안전영역

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	// 땅의 크기
	static int N;
	
	// 땅의 높이, 방문여부 배열
	static int[][] sunny;
	static boolean[][] visited;
	
	// 델타배열
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	// BFS를 위한 큐
	static Queue<int[]> queue;
	
	
	// BFS 알고리즘
	static void BFS(int r, int c, int height) {
		
		// 시작점부터 탐색
		queue.offer(new int[] {r,c});
		
		// 방문 체크
		visited[r][c] = true;
		
		// 더 방문할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			
			// 새로운 탐색좌표 데이터 받아오기
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			
			// 델타배열로 주변 순회
			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];
				
				// 만약 인덱스를 넘어갔거나 방문 혹은 잠겼다면 스킵
				if (nr >= N || nc >= N || nr < 0 || nc < 0 || visited[nr][nc] || sunny[nr][nc] - height <= 0) continue;
				
				// 새로운 탐색 지점 추가
				queue.offer(new int[] {nr, nc});
				
				// 방문 체크
				visited[nr][nc] = true;
			}
		}
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 땅 크기 입력
		N = input.nextInt();
		
		// 배열 크기 설정
		sunny = new int[N][N];
		
		// 땅 높이 정보 입력
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				sunny[r][c] = input.nextInt();
			}
		}
		
		// bfs를 위한 queue 생성
		queue = new LinkedList<>();
		
		// 안전영역의 최댓값
		int safetyMax = Integer.MIN_VALUE;
		
		// 각 높이별로 배열을 돌면서 최댓값 구하기
		for (int height = 1; height <= 100; height++) {
			
			// 방문여부 초기화
			visited = new boolean[N][N];
			
			// 높이별 안전영역의 수 초기화
			int safetyCnt = 0;
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// 만약 땅이 비온 후에도 잠기지 않았고 방문하지 않았다면 BFS on
					if (sunny[r][c] - height > 0 && !visited[r][c]) {
						BFS(r, c, height);
						
						// 안전영역 수 +1
						safetyCnt++;
					}
				}
			}

			// 최댓값 갱신여부 확인
			if (safetyCnt > safetyMax) safetyMax = safetyCnt;
		}
		
		// 정답 출력
		System.out.println(safetyMax);
		
	}
}
