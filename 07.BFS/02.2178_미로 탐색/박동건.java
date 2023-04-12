//미로탐색

// import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {
	// 배열의 크기
	static int N, M;
	
	// 미로 배열
	static int[][] maze;
	
	// 탐색을 위한 델타배열
	static int[][] dt = {{-1,0},{1,0},{0,-1},{0,1}}; // 상,하,좌,우
	
	// 중복 탐색을 막기 위해 방문 여부를 기록하는 bool배열
	static boolean[][] visited;
	

	
	
	// BFS 알고리즘
	static void BFS(int r, int c) {	
		
		// 방문좌표 관리를 위한 Queue
		Queue<int[]> queue = new LinkedList<>();
		
		// 탐색을 시작할 좌표 큐에 추가
		queue.offer(new int[] {r,c});
		
		// 방문체크
		visited[r][c] = true;
		
		// 더 탐색할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			// 주변 탐색을 위해 큐에서 현재 좌표 데이터 받아오기
			int[] cur = queue.poll();
			
			// 델타 탐색
			for (int delta = 0; delta < 4; delta++) {
				// 새로운 좌표
				int nr = cur[0] + dt[delta][0];
				int nc = cur[1] + dt[delta][1];
				
				// 만약 index를 넘어가거나 이미 방문한 곳이라면 스킵
				if (nr >= N || nc >= M || nr < 0 || nc < 0 || visited[nr][nc]) continue;
				
				// 만약 탐색한 지점이 처음 가는 길이라면 탐색 리스트(큐)에 추가
				if (maze[nr][nc] == 1) {
					queue.offer(new int[] {nr, nc});
					
					// 방문횟수 기억을 위해 좌표값으로 현재 좌표값 +1 입력
					maze[nr][nc] = maze[cur[0]][cur[1]] + 1;
				}				
			}	
			
		}
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(input.nextLine());
		
		// 미로의 가로, 세로 길이
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 입력받은 길이로 배열 크기 설정
		maze = new int[N][M];
		visited = new boolean[N][M];
		
		// 배열 입력
		for (int r = 0; r < N; r++) {
			String s = input.nextLine();
			for (int c = 0; c < M; c++) {
				maze[r][c] = (int) s.charAt(c) - '0';
			}
		}

//		for (int[] rows : maze) {
//			System.out.println(Arrays.toString(rows));
//		}
		
		// (0,0)에서 탐색 시작
		BFS(0,0);

//		for (int[] rows : maze) {
//			System.out.println(Arrays.toString(rows));
//		}
		
		// 정답 출력
		System.out.println(maze[N-1][M-1]);
		
	}
}