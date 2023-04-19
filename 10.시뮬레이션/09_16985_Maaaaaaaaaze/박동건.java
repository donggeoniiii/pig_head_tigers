import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	// 미로의 크기
	private static final int N = 5;
	
	// 미로 배열
	private static int[][][] maze;
	
	// 미로 방문 배열(for bfs())
	private static int[][][] visited;
	
	// 쌓을 순서를 보관할 배열(for chooseSeq())
	private static int[] selectedFloor;
	
	// 층별 회전여부를 저장하는 배열(사이즈 5지만 index 1부터 사용, for chooseDir())
	private static int[] selectDir;
	
	// 쌓을 순서를 저장하는 매열 (for chooseSeq())
	private static boolean[] selected;
	
	// 델타배열(for bfs())
	private static int[] dh = {1,0,0,0,0,-1};
	private static int[] dr = {0,1,-1,0,0,0};
	private static int[] dc = {0,0,0,1,-1,0};
	
	// 최소거리 저장 변수
	private static int minDist;
	
	// bfs 메소드
	private static int bfs(int sr, int sc, int[][][] curMap) {
		
		// 순회를 위한 queue
		Queue<int[]> queue = new LinkedList<>();
		
		// 시작점 추가
		queue.offer(new int[] {0,sr,sc});
		
		// 방문배열 초기화, 시작점 방문체크
		visited = new int[N][N][N];
		
		visited[0][sr][sc] = 1;
		
		// 더 탐색할 지점이 없을 때까지
		while(!queue.isEmpty()) {
			
			// 탐색좌표 받아오기
			int[] cur = queue.poll();
			int ch = cur[0];
			int cr = cur[1];
			int cc = cur[2];
			
			// 탐색
			for (int dt = 0; dt < 6; dt++) {
				int nh = ch + dh[dt];
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];
				
				// index 벗어나면 스킵
				if (nh >= N || nr >= N || nc >= N || nh < 0 || nr < 0 || nc < 0)
					continue;
				
				// 갈 수 없는 땅이면 스킵
				if (curMap[nh][nr][nc]== 0)
					continue;
				
				// 방문했으면 스킵
				if (visited[nh][nr][nc] > 0)
					continue;
				
				// 방문체크 및 방문시간 기록
				visited[nh][nr][nc] = visited[ch][cr][cc] +1;

				// 만약 탐색좌표가 도착점이면 종료
				if (nh == N-1 && nr == (N-1)-sr && nc == (N-1)-sc)
					return visited[nh][nr][nc] -1;
				
				// 다음 탐색좌표 추가
				queue.offer(new int[] {nh, nr, nc});
				
			}
			
		}
		
		// 도착 못하고 종료했으면 최솟값 갱신 못하게
		return Integer.MAX_VALUE;
	}
	
	// 뒤집는 메소드
	private static int[][] flip(int[][] curFloor, int dir) {
		
		int[][] newFloor = new int[N][N];
		
		switch (dir) {
		case 1:
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					newFloor[r][c] = curFloor[(N-1)-c][r]; 
				}
			}			
			break;

		case 2:
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					newFloor[r][c] = curFloor[(N-1)-r][(N-1)-c]; 
				}
			}
			
			break;

		case 3:
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					newFloor[r][c] = curFloor[c][(N-1)-r]; 
				}
			}
			
			break;
		default:

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					newFloor[r][c] = curFloor[r][c]; 
				}
			}
			
			break;
		}
		
		
		
		return newFloor;
	}
	
	// 회전 여부를 정하는 메소드
	private static void chooseDir(int cnt) {
		
		// pruning: 이미 최소길이가 12가 나오면
		if (minDist <= 12)
			return;
		
		// base case: 4층을 다 정하면(1층 제외) 
		if (cnt == 5) {
			
			// bfs 돌릴 새 배열 생성
			int[][][] newMap = new int[N][N][N];
			
			// 순서대로 쌓기
			for (int floor = 0; floor < N; floor++) {
				
				// 현재 쌓을 층수의 원래 위치
				int idx = selectedFloor[floor];
				
				// 층별 배열, 돌리기
				int[][] newFloor = flip(maze[idx], selectDir[floor]);
				
				// 돌린 배열 새 배열에 추가
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						newMap[floor][r][c] = newFloor[r][c];  
					}
				}
			}
			
			int[] er = {0, 0, N-1, N-1};
			int[] ec = {0, N-1, 0, N-1};
			
			// 다 쌓았으면 출발 가능한지 확인
			for (int dt = 0; dt < 4; dt++) {
				int nr = er[dt];
				int nc = ec[dt];
				
				if (newMap[0][nr][nc] == 1) {
					
					// 최솟값 구하기
					int curDist = bfs(nr, nc, newMap);
					
					// 갱신하기
					minDist = Math.min(curDist, minDist);
				}
			}
			
			return;
		}
		
		// recursive case
		// 순서대로 쌓기
		for (int dir = 0; dir < N; dir++) {
			
			selectDir[cnt] = dir;
			
			chooseDir(cnt+1);
			
		}
		
	}
		
	// 쌓기 메소드
	private static void chooseSeq(int cnt) {
		
		// base case: 5층을 다 쌓으면
		if (cnt == 5) {
			
			// 회전 여부 확인을 위해 필요한 배열 초기화
			selectDir = new int[N];
			
			// 회전 여부 정하러 이동(2층부터)
			chooseDir(1);
			
			return;
		}
		
		// recursive case
		for (int i = 0; i < N; i++) {
			
			// 이미 고른 층이면 스킵
			if (selected[i]) 
				continue;
			
			// 선택하기
			selected[i] = true;
			
			// cnt(+1)층에 i번째 배열 넣기
			selectedFloor[cnt] = i;
			
			// 다음 선택으로 이동
			chooseSeq(cnt+1);
			
			// 다른 선택을 위해 선택 해제
			selected[i] = false;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 배열 만들기
		maze = new int[N][N][N];
		
		// 입력
		for (int h = 0; h < N; h++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					maze[h][r][c] = input.nextInt(); 
				}
			}
		}
		
		// 배열 초기화
		selectedFloor = new int[N];
		selected = new boolean[N];
		
		// 최솟값 초기화
		minDist = Integer.MAX_VALUE;
		
		// 찾으러 가기
		chooseSeq(0);
		
		// 최솟값 갱신 안됐으면 -1, 됐으면 최솟값 출력
		if (minDist == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(minDist);
		
		
		input.close();
	}
}