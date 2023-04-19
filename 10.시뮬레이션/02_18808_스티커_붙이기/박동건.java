//색종이 

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	// 색종이 남은 개수의 배열
	private static int[] papers;
	
	// 사용한 색종이 개수의 최솟값
	private static int minCnt;
	
	// 덮을 종이 선택하는 메소드
	static void choosePaper(int cr, int cc, int cnt, boolean[][] curMap) {
		
		// pruning: 이미 사용한 종이의 개수가 최소값보다 높으면 그만
		if (cnt > minCnt) return;
		
		// base case: 마지막 탐색에 도달하면
		if (cr == 10) {
			
			// 만약 남은 경우가 있으면 failed 표시
			boolean failed = false;
			for (int r = 0; r < 10; r++) {
				for (int c = 0; c < 10; c++) {
					if (curMap[r][c] == true) {
						failed = true;
						break;
					}
				}
				if (failed == true) break;
			}
			
			// 최솟값 갱신
			if (!failed) minCnt = Math.min(minCnt, cnt);
			
			return;
		}
		
		// recursive case
		
		// 이동시킬 좌표
		int nr = cr;
		int nc = cc;
		
		// 좌표가 false일 동안
		while (nr < 10 && nc < 10 && curMap[nr][nc] == false) {
			// 앞으로 전진
			nc++;
			
			// 끝까지 온 경우 줄바꿈
			if (nc == 10) {
				nr++;
				nc = 0;
			}	
		}
		
		// r = 10이면 모든 좌표를 탐색한 것이므로 종료
		if (nr == 10) {
			minCnt = Math.min(minCnt, cnt);
			return;
		}
		
		// 좌표가 1이면 색종이 고르기
		if (curMap[nr][nc] == true) {

			// 큰게 덮는게 빨리 끝나니까 큰 거부터
			for (int size = 5; size >= 1; size--) {
				
				// 현재 좌표에서 해당 색종이를 덮으면 범위를 벗어나는 경우 스킵
				if (nr+size > 10 || nc+size > 10) continue;
				
				// 해당 색종이가 다 떨어졌으면 스킵
				if (papers[size] == 0) continue;
				
				// 해당 색종이 선택
				papers[size]--;
				
				// 맵 복사
				boolean[][] newMap = new boolean[10][10];
				for (int rdx = 0; rdx < 10; rdx++) {
					newMap[rdx] = Arrays.copyOf(curMap[rdx], 10); 
				}
				
				// 색종이 붙이기 메소드를 시행해서 성공했으면 재귀 호출
				if (paste(nr, nc, size, newMap)) 
					// 줄바꿈이 필요한 경우 좌표 바꿔서 재귀 호출
					if (nc+size == 10) choosePaper(nr+1, 0, cnt+1, newMap);
					else choosePaper(nr, nc+size, cnt+1, newMap);
				
				// 다른 선택을 위해 선택 되돌리기
				papers[size]++;
				
			}
		}
	}
	
	
	// 색종이 붙이기 메소드
	private static boolean paste(int cr, int cc, int range, boolean[][] map) {
		
		// 색종이로 칠할 부분을 저장하는 queue
		Queue<Integer> queue = new LinkedList<>();
		
		// 크기만큼 색종이 붙이기
		for (int r = cr; r < cr + range; r++) {
			for (int c = cc; c < cc + range; c++) {
				
				// 만약 false인 값이 있으면 큐 비우고 false 반환 
				if (map[r][c] == false) {
					queue.clear();
					return false;
				}
				
				// 1인 값은 queue에 넣기
				else { // if (map[r][c] == true)
					queue.offer(r);
					queue.offer(c);
				}
			}
		}
		
		// false인 부분 없이 다 true면 queue에서 좌표 빼면서 false로 바꾸기
		while (!queue.isEmpty()) {
			
			int nr = queue.poll();
			int nc = queue.poll();
			
			map[nr][nc] = false;
		}
		
		// true 반환
		return true;
	}
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 배열 생성
		papers = new int[6];
		Arrays.fill(papers, 5);
		boolean[][] map = new boolean[10][10];
		
		// 배열에 값 입력
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				int num = input.nextInt();
				
				// 입력이 0이면 false, 1이면 true
				if (num == 0) map[r][c] = false;
				else map[r][c] = true;
			}
		}
		
		// 최솟값 초기화
		minCnt = 26;
		
		// 처음으로 나온 1 위치 저장
		int sr = -1;
		int sc = -1;
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 10; c++) {
				if (map[r][c] == true) {
					sr = r;
					sc = c;
					break;
				}
			}
			if (sr != -1) break;
		}
		
		// 1값이 없으면 탐색할 필요가 없으므로 정답 = -1
		if (sr == -1) minCnt = 0;
		// 있으면 해당 위치부터 탐색 시작
		else choosePaper(sr, sc, 0, map);
		
		// 초기화값 그대로면 -1로 변경
		if (minCnt == 26) minCnt = -1;
		
		
		// 정답 출력
		System.out.println(minCnt);
		
		input.close();
	}
}