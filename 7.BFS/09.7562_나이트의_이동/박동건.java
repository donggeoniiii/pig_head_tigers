package boj7562_나이트의이동; // 나이트의 이동

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

// 숨바꼭질의 연장선인 느낌?

public class Main {
	// 체스판의 크기
	static int L;
	
	// 델타배열, 나이트의 이동 방식을 고려해서 
	static int[] dr = {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[] dc = {-1, 1, -2, 2, -2, 2, -1, 1};
	
	// 방문 여부 및 몇 번째 방문인지를 기록할 배열
	static int[][] visited;
	
	// bfs를 위한 큐
	static Queue<int[]> queue;
	
	// 목표위치
	static int tr, tc;
	
	// BFS 알고리즘
	static void BFS() {
		// 큐에 값이 남았을 때 while문을 탈출하기 위한 변수
		boolean arrived = false;
		
		// 더 이상 탐색할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			
			// 시작점 좌표값 받아오기
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			
			// 델타탐색 on
			for (int dt = 0; dt < 8; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];
				
				// index를 벗어나거나 이미 방문했으면(== visited[nr][nc]가 -1이 아니면) 스킵
				if (nr >= L || nc >= L || nr < 0 || nc < 0 || visited[nr][nc] != -1) continue;
				
				// 새로운 탐색지점 추가
				queue.offer(new int[] {nr,nc});
				
				// 몇 번째 이동에 도달했는지 체크
				visited[nr][nc] = visited[cr][cc] + 1;
				
				// 만약 탐색한 값이 목표위치와 동일하다면 탐색 여부 갱신
				if (nr == tr && nc == tc) arrived = true;
			}
			
			// 만약 값을 찾았다면 탈출
			if (arrived) break;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st;
		
		// 테스트케이스 개수
		int T = Integer.parseInt(input.nextLine());
		
		for (int testcase = 1; testcase <= T; testcase++) {
			// 체스판의 길이
			L = Integer.parseInt(input.nextLine());
			
			// 나이트의 위치
			st = new StringTokenizer(input.nextLine());
			int kr = Integer.parseInt(st.nextToken()); // row
			int kc = Integer.parseInt(st.nextToken()); // col
			
			// 목표 위치
			st = new StringTokenizer(input.nextLine());
			tr = Integer.parseInt(st.nextToken()); // row
			tc = Integer.parseInt(st.nextToken()); // col

			// 방문 여부 및 몇 번째 방문인지를 기록할 배열
			visited = new int[L][L];
			
			// 모든 값이 0이면 시작점과 구분이 되지 않으므로 -1로 초기화
			for (int r = 0; r < L; r++)	Arrays.fill(visited[r], -1);
			
			// bfs를 위한 큐
			queue = new LinkedList<>();
			
			// queue에 나이트 위치 등록
			queue.offer(new int[] {kr,kc});
			
			// 방문체크겸 시작점 표시
			visited[kr][kc] = 0;
			
			// bfs on
			BFS();
			
			// 결과 출력
			System.out.println(visited[tr][tc]);
			
		}
	}
}
