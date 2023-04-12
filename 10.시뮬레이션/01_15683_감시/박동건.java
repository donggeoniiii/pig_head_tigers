// 감시

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	// 배열의 크기
	static int N;
	static int M;
	
	// 사무실 배열
	static int[][] map;
	
	// CCTV 위치를 저장할 arrayList
	static ArrayList<Integer> cctvIdx;
	
	// 카메라 종류별 델타 배열 (위부터 시계방향 탐색)
	static int[][] dr1 = {{-1},{0},{1},{0}};
	static int[][] dc1 = {{0},{-1},{0},{1}};
	static int[][] dr2 = {{-1,1},{0,0}};
	static int[][] dc2 = {{0,0},{-1,1}};
	static int[][] dr3 = {{-1,0},{-1,0},{1,0},{1,0}};
	static int[][] dc3 = {{0,-1},{0,1},{0,-1},{0,1}};
	static int[][] dr4 = {{-1,0,1},{0,1,0},{1,0,-1},{0,-1,0}};
	static int[][] dc4 = {{0,1,0},{1,0,-1},{0,-1,0},{-1,0,1}};
	static int[] dr5 = {-1,1,0,0};
	static int[] dc5 = {0,0,-1,1};
	
	// 최솟값
	static int minCnt;
	
	// DFS 알고리즘
	static void DFS(int idx, int[][] curMap) {
		
		// pruning: 이미 값이 0이 됐으면 종료
		if (minCnt == 0) 
			return;
		
		// base case: 만약 index가 끝까지 왔으면 
		if (idx >= cctvIdx.size()) {
			
			// CCTV가 닿지 않는 사각지대가 몇개인지 세기
			int curCnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (curMap[r][c] == 0) 
						curCnt++;
				}
			}
			
			// 최솟값 갱신
			minCnt = Math.min(minCnt, curCnt);
			
			return;
		}
		
		// recursive case:
		// 새 좌표 받아오기
		int cr = cctvIdx.get(idx);
		int cc = cctvIdx.get(idx+1);
		
		// 해당 위치의 배열 값을 보고 CCTV 종류 확인
		int cctv = curMap[cr][cc];
		
		// CCTV 종류에 따라 탐색
		switch (cctv) {
			case 1:
				for (int i = 0; i < 4; i++) {
					// 미리 맵 복사
					int[][] newMap = new int[N][M];
					for (int r = 0; r < N; r++) {
						newMap[r] = Arrays.copyOf(curMap[r], M);
					}
					
					for (int dt = 0; dt < dr1[i].length; dt++) {
						
						// 새로운 좌표
						int nr = cr + dr1[i][dt];
						int nc = cc + dc1[i][dt];
						
						// index를 벗어나지 않는 한
						while (nr < N && nr >= 0 && nc < M && nc >= 0) {
							
							// 만약 벽을 만나면 종료
							if (newMap[nr][nc] == 6) 
								break;
							
							// CCTV가 아닌 땅을 만나면 체크표시
							if (newMap[nr][nc] == 0) 
								newMap[nr][nc] = -1; 
							
							// 이동
							nr += dr1[i][dt];
							nc += dc1[i][dt];
						}
					}
					// 다음 좌표로 이동
					DFS(idx+2, newMap);
				}
			
			break;

			case 2:
				for (int i = 0; i < 2; i++) {
					
					// 미리 맵 복사
					int[][] newMap = new int[N][M];
					for (int r = 0; r < N; r++) {
						newMap[r] = Arrays.copyOf(curMap[r], M);
					}
					
					for (int dt = 0; dt < dr2[i].length; dt++) {
						
						// 새로운 좌표
						int nr = cr + dr2[i][dt];
						int nc = cc + dc2[i][dt];
						
						// index를 벗어나지 않는 한
						while (nr < N && nr >= 0 && nc < M && nc >= 0) {
							
							// 만약 벽을 만나면 종료
							if (newMap[nr][nc] == 6) 
								break;
							
							// CCTV가 아닌 땅을 만나면 체크표시
							if (newMap[nr][nc] ==0) 
								newMap[nr][nc] = -1; 
							
							// 이동
							nr += dr2[i][dt];
							nc += dc2[i][dt];
						}
					}
					
					// 다음 좌표로 이동
					DFS(idx+2, newMap);
				}
			break;
			
			case 3:
				for (int i = 0; i < 4; i++) {
					
					// 미리 맵 복사
					int[][] newMap = new int[N][M];
					for (int r = 0; r < N; r++) {
						newMap[r] = Arrays.copyOf(curMap[r], M);
					}
					
					for (int dt = 0; dt < dr3[i].length; dt++) {
						
						// 새로운 좌표
						int nr = cr + dr3[i][dt];
						int nc = cc + dc3[i][dt];
						
						// index를 벗어나지 않는 한
						while (nr < N && nr >= 0 && nc < M && nc >= 0) {
							
							// 만약 벽을 만나면 종료
							if (newMap[nr][nc] == 6) 
								break;
							
							// CCTV가 아닌 땅을 만나면 체크표시
							if (newMap[nr][nc] ==0) 
								newMap[nr][nc] = -1; 
							
							// 이동
							nr += dr3[i][dt];
							nc += dc3[i][dt];
						}
					}
					
					// 다음 좌표로 이동
					DFS(idx+2, newMap);
				}
			break;
			
			case 4:
				for (int i = 0; i < 4; i++) {

					// 미리 맵 복사
					int[][] newMap = new int[N][M];
					for (int r = 0; r < N; r++) {
						newMap[r] = Arrays.copyOf(curMap[r], M);
					}
					
					for (int dt = 0; dt < dr4[i].length; dt++) {
						
						// 새로운 좌표
						int nr = cr + dr4[i][dt];
						int nc = cc + dc4[i][dt];
						
						// index를 벗어나지 않는 한
						while (nr < N && nr >= 0 && nc < M && nc >= 0) {
							
							// 만약 벽을 만나면 종료
							if (newMap[nr][nc] == 6) 
								break;
							
							// CCTV가 아닌 땅을 만나면 체크표시
							if (newMap[nr][nc] ==0) 
								newMap[nr][nc] = -1; 
							
							// 이동
							nr += dr4[i][dt];
							nc += dc4[i][dt];
						}
					}
					
					// 다음 좌표로 이동
					DFS(idx+2, newMap);
				}
			break;
			
			case 5:

				// 미리 맵 복사
				int[][] newMap = new int[N][M];
				for (int r = 0; r < N; r++) {
					newMap[r] = Arrays.copyOf(curMap[r], M);
				}
				
				
				for (int dt = 0; dt < 4; dt++) {
					
					// 새로운 좌표
					int nr = cr + dr5[dt];
					int nc = cc + dc5[dt];
					
					// index를 벗어나지 않는 한
					while (nr < N && nr >= 0 && nc < M && nc >= 0) {
						
						// 만약 벽을 만나면 종료
						if (newMap[nr][nc] == 6) 
							break;
						
						// CCTV가 아닌 땅을 만나면 체크표시
						if (newMap[nr][nc] ==0) 
							newMap[nr][nc] = -1; 
						
						// 이동
						nr += dr5[dt];
						nc += dc5[dt];
					}
					
					// 다음 좌표로 이동
					DFS(idx+2, newMap);
				}
			break;
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 배열 크기 입력
		N = input.nextInt();
		M = input.nextInt();
		
		// 배열 생성
		map = new int[N][M];
		
		// CCTV의 위치를 저장할 ArrayList
		cctvIdx = new ArrayList<>();
		
		// 최솟값 초기화
		minCnt = N*M+1;
		
		// 배열 입력하면서 CCTV 위치 저장
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = input.nextInt(); 
				
				if (map[r][c] > 0 && map[r][c] != 6) {
					cctvIdx.add(r);
					cctvIdx.add(c);
				}
			}
		}
		
		// DFS ON
		DFS(0, map);
		
		// 최솟값 출력
		System.out.println(minCnt);
		
		input.close();
	}
}