//토마토1


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	// 행과 열의 길이
	static int N, M;
	
	// 농장 배열
	static int[][] farm;
	
	// 델타탐색을 위한 델타배열
	static int[][] delta = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	
	// BFS를 위한 Queue
	static Queue<int[]> queue;

	
	// BFS 알고리즘
	static void BFS() {
		
		// queue가 빌 때까지
		while (!queue.isEmpty()) {
			
			// 기준좌표 queue에서 받아오기
			int[] cur = queue.poll();
			// 델타순회
			for (int dt = 0; dt < 4; dt++) {
				
				// 현재 값을 기준으로 상하좌우 좌표 접근
				int nr = cur[0] + delta[dt][0];
				int nc = cur[1] + delta[dt][1];
				
				// 만약 index를 넘어가고 아무것도 없거나 이미 익었으면 스킵
				if (nr >= N || nc >= M || nr < 0 || nc < 0) continue;
				if (farm[nr][nc] != 0) continue;
				
				// 다음에 순회할 좌표 큐에 추가
				queue.offer(new int[] {nr, nc});
				
				// 아직 방문하지 않은 안익은 토마토만 남았으므로 만날 때마다 익는데까지 걸리는 시간을 표시하면 된다
				// 익는데 걸리는 시간 = {cur[0], [cur[1]} 의 시간 + 1
				farm[nr][nc] = farm[cur[0]][cur[1]] + 1;
	
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 농장 크기 입력
		M = input.nextInt();
		N = input.nextInt();
		
		// 배열 크기 지정
		farm = new int[N][M];
		
		// 좌표를 담을 Queue 생성
		queue = new LinkedList<>();
		
		// 특수상태를 체크하는 bool 변수
		boolean allRipen = true;
		
		// 토마토 농장 상태 입력
		for (int r = 0; r < N; r++){
			for (int c = 0; c < M; c++) {
				
				farm[r][c] = input.nextInt();
				
				// 하나라도 안 익은게 들어오면 모든 토마토가 익은 건 아니니까
				if (farm[r][c] == 0) allRipen = false;
				
				// 확인한 값이 1(이미 익은 토마토)면 큐에 등록
				if (farm[r][c] == 1) queue.offer(new int[] {r,c});
			}
		}

		// BFS on
		BFS();
		
		// 안익은게 있으면 체크
		boolean notRipen = false;
		
		// 배열 내 최댓값으로 다 익기까지 얼마나 걸리는지 확인
		int answer = 0;
		
		// 다 돌고 나서 순회하면서 안익은 토마토가 있는지 체크
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				// 만약 안익은 토마토가 있으면 bool값 변경
				if (farm[r][c] == 0) notRipen = true;
				
				// 다 익기까지 걸리는 시간 갱신
				if (farm[r][c] > answer) answer = farm[r][c]; 
			}
		}

		// 만약 시작부터 다 익었으면 확인해볼 필요 없으니까 바로 0 출력
		if (allRipen) System.out.println(0);
		// 모든 토마토가 다 익지 않는 경우면 -1 출력
		else if (notRipen) System.out.println(-1);
		// 정상적인 경우면 배열이 익기까지 최소날짜 출력, 익은 토마토의 값인 1에서 시작됐으므로 일수는 -1 
		else System.out.println(answer - 1);
		
	}
}