// 상범빌딩

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	// 상범빌딩의 크기
	static int L, R, C;
	
	// 델타배열
	static int[] dl, dr, dc;
	
	// 상범빌딩
	static int[][][] building;
	
	// 도착지점
	static int tl, tr, tc;
	
	// BFS를 위한 큐
	static Queue<int[]> queue;

	
	// BFS 알고리즘
	static boolean BFS() {
		
		// 더 이상 탐색할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			
			// 탐색할 좌표 정보 받아오기
			int[] cur = queue.poll();
			int cl = cur[0];
			int cr = cur[1];
			int cc = cur[2];
			
			// 델타 배열
			dl = new int[] {-1, 1, 0, 0, 0, 0};
			dr = new int[] {0, 0, -1, 1, 0, 0};
			dc = new int[] {0, 0, 0, 0, -1, 1};
			
			// 델타 탐색
			for (int dt = 0; dt < 6; dt++) {
				int nl = cl + dl[dt];
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];
				
				// index를 벗어나거나 방문한 지역(벽 포함)이면 스킵
				if (nl >= L || nr >= R || nc >= C || nl < 0 || nr < 0 || nc < 0 || building[nl][nr][nc] != 0) continue;
				
				// 새로운 탐색지점 추가
				queue.offer(new int[] {nl, nr, nc});
				
				// 탐색 기준좌표값 +1로 지금까지 걸린 시간 표시
				building[nl][nr][nc] = building[cl][cr][cc] +1;
				
				// 만약 탐색지점이 도착지점과 같으면 탈출
				if (nl == tl && nr == tr && nc == tc) return true;
			}
		}
		// 탈출구를 찾지 못하고 탐색이 끝나면 탈출 실패
		return false;
	}
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			// 입력
			String s = input.nextLine();
			
			// 입력으로 0 0 0이 들어오면 종료
			if (s.equals("0 0 0")) break;
			
			// 빌딩 크기 입력
			st = new StringTokenizer(s);
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			// 빌딩 생성
			building = new int[L][R][C];
			
			// BFS를 위한 큐 생성
			queue = new LinkedList<>();
			
			// 빌딩 정보 입력
			for (int l = 0; l < L; l++) {
				for (int r = 0; r < R; r++) {
						String line = input.nextLine();
					for (int c = 0; c < C; c++) {
						// 입력값이 .면 길, 일단 0으로 표시
						if (line.charAt(c) == '.') building[l][r][c] = 0; 
						
						// 입력값이 #면 벽이, -1로 표시
						if (line.charAt(c) == '#') building[l][r][c] = -1;
						
						// 입력값이 S면 시작점이니까 큐에 시작점 바로 저장, 방문표시
						if (line.charAt(c) == 'S') {
							queue.offer(new int[] {l,r,c});
							building[l][r][c] = 1;
						}
						
						// 입력값이 E면 탈출구니까 따로 저장
						if (line.charAt(c) == 'E') { tl = l; tr = r; tc = c;}
						
					}
				}
				input.nextLine(); // 각 층 input 사이 빈 줄 제거
			}
			
			// 시작점부터 BFS해서 탈출여부 확인
			boolean escaped = BFS();
			
			// StringBuilder를 이용해 정답 여부에 따라 구분
			if (escaped) sb.append("Escaped in ").append(building[tl][tr][tc] -1).append(" minute(s).\n");
			else sb.append("Trapped!\n");
			
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
	}
}