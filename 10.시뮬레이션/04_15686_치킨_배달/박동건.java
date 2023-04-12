// 치킨배달

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	// 도시 크기
	static int N;
	
	// 살려두어야 하는 치킨집 수
	static int M;
	
	// 도시 배열
	static int[][] city;
	
	// 치킨집의 위치를 저장하는 map
	static ArrayList<int[]> stores;

	// 치킨거리 합
	static int minTotal;
	

	// 치킨 거리 구하기 전, 남겨둘 치킨집 구하기 알고리즘
	static void remain(int cnt, int start, int[] curSelected) {
		
		// base case
		// M개의 살려둘 집을 고르고 나면 
		if (cnt == M) {
			int curTotal = 0;
			
			// 치킨거리 구하기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					
					if (city[r][c] == 1) 
						curTotal += dist(r, c, curSelected);
					
				}
			}
			
			// 최솟값 갱신
			minTotal = Math.min(curTotal, minTotal);
			
			return;
		}
		
		
		// recursive case
		// 살려둘 집, 죽일 집 고르기
		for (int i = start; i < stores.size(); i++) {

			// 선택함
			curSelected[cnt] = i;
			
			// 골라놓고 다음으로 이동, 겹치지 않게 현재 고른 값보다 1 큰데서 시작하게 시작값 전달
			remain(cnt+1, i+1, curSelected);
		}
	}
	
	
	// 치킨 거리 구하기 알고리즘
	static int dist(int cr, int cc, int[] selected) {

		// 제일 가까운 치킨집까지 맨해튼 거리
		int mindist = Integer.MAX_VALUE;

		// 마지막 치킨집에 방문할 때까지
		for (int i = 0; i < M; i++) {
			int idx = selected[i];
			
			int tr = stores.get(idx)[0];
			int tc = stores.get(idx)[1];
			
			// 거리 계산
			int nr = Math.abs(tr - cr);
			int nc = Math.abs(tc - cc);
			
			// 최솟값 갱신
			mindist = Math.min(mindist, nr+nc);
			
			// 최솟값이 1이 되면 종료
			if (mindist == 1) break;
		}
		
		
		return mindist;
	}
	
	
	public static void main(String[] args) {
		Scanner	input = new Scanner(System.in);
		
		// 도시 크기
		N = input.nextInt();
		
		// 살릴 치킨집 수
		M = input.nextInt();
		
		// 배열 크기 입력
		city = new int[N][N];
		int[] selected = new int[M];
		
		
		// 자료구조 선언
		stores = new ArrayList<>();
		
		// 도시 정보 입력하면서 치킨집 주소 알아놓기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				city[r][c] = input.nextInt(); 
				
				if (city[r][c] == 2)
					stores.add(new int[] {r,c});
			}
		}
		
		// 최솟값 초기화
		minTotal = Integer.MAX_VALUE;
		
		// 치킨집 고르고 거리 구하기
		remain(0, 0, selected);
		
		// 정답 출력
		System.out.println(minTotal);
		
		input.close();
	}
}