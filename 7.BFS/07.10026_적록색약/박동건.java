// 적록색약

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static boolean[][] cblind; // 색약배열
	static char[][] normal; // 일반인배열
	static boolean[][] bVisited; // 색약 방문체크배열
	static boolean[][] nVisited; // 일반인 방문체크배열
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}}; // 델타배열
	static Queue<int[]> queue; // 탐색에 사용할 queue
	
	// 배열 크기
	static int N;
	
	// 색약 bfs
	static void blindBFS(int r, int c) {
		// 큐 생성
		queue = new LinkedList<>();
		
		// 시작점 queue에 offer
		queue.offer(new int[] {r,c});
		
		// 방문체크
		bVisited[r][c] = true;
		
		// queue가 빌 때까지
		while(!queue.isEmpty()) {
			
			// 현재 좌표 데이터 poll해오기
			int[] cur = queue.poll();
			
			// 델타탐색
			for (int dt = 0; dt < 4; dt++) {
				int nr = cur[0] + delta[dt][0];
				int nc = cur[1] + delta[dt][1];
				
				// 인덱스 넘어가는 경우나 이미 방문했으면 skip
				if (nr >= N || nc >= N || nr < 0 || nc < 0 || bVisited[nr][nc]) continue;
				
				// 만약 파란색이면(true) 큐에 다음 탐색좌표 offer
				if (cblind[nr][nc] == cblind[cur[0]][cur[1]]) {
					queue.offer(new int[] {nr, nc});
					
					// 방문체크
					bVisited[nr][nc] = true;
					
				}
				
			}
		}
		
	}
	
	// 일반인 bfs
	static void normalBFS(int r, int c) {
		// 큐 생성
		queue = new LinkedList<>();
		
		// 시작점 queue에 offer
		queue.offer(new int[] {r,c});
		
		// 방문체크
		nVisited[r][c] = true;
		
		// queue가 빌 때까지
		while(!queue.isEmpty()) {
			
			// 현재 좌표 데이터 poll해오기
			int[] cur = queue.poll();
			
			// 델타탐색
			for (int dt = 0; dt < 4; dt++) {
				int nr = cur[0] + delta[dt][0];
				int nc = cur[1] + delta[dt][1];
				
				// 인덱스 넘어가는 경우나 이미 방문했으면 skip
				if (nr >= N || nc >= N || nr < 0 || nc < 0 || nVisited[nr][nc]) continue;
				
				// 만약 같은색이면 큐에 다음 탐색좌표 offer
				if (normal[nr][nc] == normal[cur[0]][cur[1]]) {
					queue.offer(new int[] {nr, nc});
					
					// 방문체크
					nVisited[nr][nc] = true;
				}
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 배열의 넓이
		N = Integer.parseInt(input.nextLine());
		
		// 배열 넓이설정
		cblind = new boolean[N][N]; // 색약배열
		normal = new char[N][N]; // 일반인배열
		bVisited = new boolean[N][N]; // 색약 방문체크배열
		nVisited = new boolean[N][N]; // 일반인 방문체크배열
		
		// 색약, 일반인 카운트
		int bCnt = 0;
		int nCnt = 0;
		
		// 배열 입력
		for (int r = 0; r < N; r++) {
			String s = input.nextLine();
			for (int c = 0; c < N; c++) {
				normal[r][c] = s.charAt(c);
				if (s.charAt(c) == 'B') cblind[r][c] = true;
			}
		}
		
		// 배열별로 bfs
		// 색약
		// 파란색(true)
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 만약 파란색(true)고 방문하지 않았다면 bfs on
				if (cblind[r][c] && !bVisited[r][c]) {
					blindBFS(r, c);
					
					// 카운트
					bCnt++;
				}
			}
		}
		// 방문 확인 초기화
		bVisited = new boolean[N][N];

		// 빨간 +녹색(false)
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 만약 빨강 or 녹색(false)고 방문하지 않았다면 bfs on
				if (cblind[r][c] == false && !bVisited[r][c]) {
					blindBFS(r, c);
					
					// 카운트
					bCnt++;
				}
			}
		}
		
		
		// 일반인
		// 파란색
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 만약 파란색이고 방문하지 않았다면 bfs on
				if (normal[r][c] == 'B' && !nVisited[r][c]) {
					normalBFS(r, c);
					
					// 카운트
					nCnt++;
				}
			}
		}
		// 방문 확인 초기화
		nVisited = new boolean[N][N];
		
		// 빨간색
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 만약 파란색이고 방문하지 않았다면 bfs on
				if (normal[r][c] == 'R' && !nVisited[r][c]) {
					normalBFS(r, c);
					
					// 카운트
					nCnt++;
				}
			}
		}
		// 방문 확인 초기화
		nVisited = new boolean[N][N];
		
		// 녹색
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 만약 파란색이고 방문하지 않았다면 bfs on
				if (normal[r][c] == 'G' && !nVisited[r][c]) {
					normalBFS(r, c);
					
					// 카운트
					nCnt++;
				}
			}
		}
		
		// 정답 출력
		System.out.println(nCnt +" "+bCnt);
		
	}
	
}