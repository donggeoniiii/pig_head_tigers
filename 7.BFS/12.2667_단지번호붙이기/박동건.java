package boj2583_영역구하기; // 영역 구하기

import java.util.ArrayList;
// import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	// 모눈종이의 크기
	static int M, N;
	
	// 해당 모눈종이 칸이 덮였는지 아닌지 확인하는 배열
	static boolean[][] covered;
	
	// BFS를 위한 큐
	static Queue<int[]> queue;
	
	// BFS를 위한 델타배열
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	
	// BFS 알고리즘
	static int BFS(int r, int c) {
		// BFS로 탐색한 부분의 넓이
		int size = 1;
		
		// BFS 탐색을 위한 큐 초기화
		queue = new LinkedList<>();
		
		// 시작점 좌표 offer
		queue.offer(new int[] {r,c});
		
		// 방문표시
		covered[r][c] = true;
		
		// 더 이상 탐색할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			
			// 탐색지점 좌표값 받아오기
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			
			// 델타 탐색
			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];
				
				// 만약 index를 벗어나거나 직사각형이 덮은 곳이면 스킵
				if (nr >= N || nc >= M || nr < 0 || nc < 0 || covered[nr][nc]) continue;
				
				// 탐색위치 추가
				queue.offer(new int[] {nr,nc});
				
				// 한번 방문한 곳은 덮은 것으로 처리해 다시 방문하지 않도록
				covered[nr][nc] = true;
				
				// 크기 추가
				size++;
			}
		}
		
		// 탐색한 넓이 반환
		return size;
		
	}
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st;
		
		// 모눈종이의 크기
		st = new StringTokenizer(input.nextLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// M*N 모눈종이
		covered = new boolean[N][M];
		
		// 직사각형의 개수
		int K = Integer.parseInt(st.nextToken());
		
		// K개의 직사각형의 좌상단, 우하단 꼭짓점 좌표
		for (int k = 1; k <= K; k++) {
			
			//꼭짓점 좌표 받기
			st = new StringTokenizer(input.nextLine());

			// index 0, 1: 좌상단, index 2, 3: 우하단 꼭짓점 좌표
			int[] spot = new int[4];
			spot[0] = Integer.parseInt(st.nextToken());
			spot[1] = Integer.parseInt(st.nextToken());
			spot[2] = Integer.parseInt(st.nextToken());
			spot[3] = Integer.parseInt(st.nextToken());
			
			// 좌표값을 이용해 boolean 배열에 덮인 부분 표시
			for (int r = spot[1]; r < spot[3]; r++) {
				for (int c = spot[0]; c < spot[2]; c++) {
					covered[r][c] = true;
				}
			}
		}
		
		// 나누어진 영역의 넓이를 담는 ArrayList 생성
		ArrayList<Integer> area = new ArrayList<>();
		
		// 나누어진 부분의 개수를 세는 변수
		int cnt = 0;
		
		// 모는종이를 돌면서 
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				
				// 만약 처음 보는 안덮인 부분이 나온다면 BFS, 구한 넓이를 List에 추가
				if (!covered[r][c]) {
					area.add(BFS(r, c));
					cnt++;
				}
			}
		}
		
		// 정답 출력을 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		
//		for (int r = 0; r < N; r++) {
//			for (int c = 0; c < M; c++) {
//				if (covered[r][c]) System.out.printf("%-2d", 1);
//				else System.out.printf("%-2d", 0);
//			}
//			System.out.println();
//		}
		
		// 정답 입력 전 ArrayList 정렬
		area.sort(null);
		
//		System.out.println(area);
		
		// 출력형태로 sb에 저장
		sb.append(cnt+"\n");
		for (int num : area) sb.append(num+" ");
		
		// 정답 출력
		System.out.println(sb.toString());
		
	}
}
