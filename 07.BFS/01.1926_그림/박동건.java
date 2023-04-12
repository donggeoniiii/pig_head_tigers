// 그림

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	// 그림 배열
	static int[][] drawing;
	
	// 그림의 크기를 담을 변수
	static int N; // row
	static int M; // col
	
	// 이미 갔다온 곳은 방문하지 않도록 방문한 곳을 기억하는 boolean 배열
	static boolean[][] visited;
	
	// 주변 사방 탐색을 위한 델타 배열
	static int[][] dt = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우 순
	
	// 각 그림 크기를 비교해 최댓값을 찾기 위한 변수
	static int pMax = 0;
	
	// BFS를 위한 Queue
	static Queue<int[]> queue = new LinkedList<>();
	
	// BFS 알고리즘
	static void BFS(int r, int c) {
		
		// 현재 칸부터 탐색 시작
		queue.offer(new int[] {r, c});
		
		// 해당 그림의 크기
		int size = 1;
		
		// 더이상 탐색할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			// 큐에 들어간 순서대로 새롭게 탐색
			int[] cur = queue.poll();
			
			// 델타 탐색
			for (int delta = 0; delta < 4; delta++) {
				int nr = cur[0] + dt[delta][0];
				int nc = cur[1] + dt[delta][1];
				
				// 범위를 벗어나거나 이미 방문했으면 스킵
				if (nr >= N || nc >= M || nr < 0 || nc < 0 || visited[nr][nc]) continue;
				
				// 빈종이가 아니라 그림인 칸이라면
				if (drawing[nr][nc] == 1) {
					
					// 다음에 주변 탐색을 위해 큐에 추가
					queue.offer(new int[] {nr, nc});
					
					// 방문표시 
					visited[nr][nc] = true;
					
					// 크기 반영
					size++;
				}
			}
		}
		
		// 만약 현재 그림의 크기가 지금까지 가장 크다면 최대값 갱신
		if (size > pMax) pMax = size;
		
	}
	
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 그림의 크기
		N = input.nextInt();
		M = input.nextInt();
		
		// 배열 크기 입력
		drawing = new int[N][M];
		visited = new boolean[N][M];
		
		// 배열에 입력
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				drawing[r][c] = input.nextInt();
			}
		}
		
		// 종이에 있는 그림의 개수 
		int pCnt = 0;
		
		
		// 순회하면서 길이 탐색
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				// 만약 방문하지 않은 곳이면서 그림이 그려진 곳이라면
				if (!visited[r][c] && drawing[r][c] == 1) {
					// 방문체크
					visited[r][c] = true;
					
					// BFS 시행
					BFS(r, c);
					
					// 종이에 그려진 그림 +1
					pCnt++;
				}
			}
		}
		
		// 정답 출력
		System.out.println(pCnt);
		System.out.println(pMax);
		
	}
}