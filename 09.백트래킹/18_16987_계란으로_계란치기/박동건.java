// 계란으로 계란치기

import java.util.Scanner;

public class Main {
	
	// 계란의 개수
	static int N;
	
	// 계란의 정보
	// idx 0: 내구도(hp), 1: 무게(atk)
	static int[][] eggs;
	
	// 깰 수 있는 계란의 최대 개수
	static int maxEggs;
	
	// 백트래킹 알고리즘
	static void track(int idx) {
		
		// base case: N개의 계란에 대해 다 살피고 나면 종료
		if (idx == N) {
			// 최댓값 갱신을 위한 카운트 변수
			int cur = 0;
			
			for (int i = 0; i < N; i++) {
				if (eggs[i][0] <= 0) cur++;
			}

			// 최댓값 갱신
			maxEggs = Math.max(maxEggs, cur);
			
			return;
		}
		
		
		// recursive case:
		// pruning: 만약 집은 계란이 깨졌으면 다음으로
		if (eggs[idx][0] <= 0) {
			track(idx+1);
		}
		// 집은 계란이 안 깨졌으면
		else {
			// 다른 깨진 계란이 몇개인지 세는 변수
			int left = 0;
			for (int i = 0; i < N; i++) {
				
				// 자기 자신을 치는 경우 스킵
				if (i == idx) continue;
				
				// 이미 깨진 계란이면 skip
				if (eggs[i][0] <= 0) {left++; continue;}
				
				// 계란으로 치기
				eggs[i][0] -= eggs[idx][1];
				eggs[idx][0] -= eggs[i][1];
				
				// 다음 계란치기를 위해 이동
				track(idx + 1);
				
				// 다른 선택지를 위해 원상복구 시키기
				eggs[i][0] += eggs[idx][1];
				eggs[idx][0] += eggs[i][1];
			}
			
			// 다 깨진경우 마지막 index로 이동
			if (left == N-1) track(N);
		}
		
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 계란의 개수
		N = input.nextInt();
		
		// 배열 초기화
		eggs = new int[N][2];
		
		// 배열 정보 입력
		for (int idx = 0; idx < N; idx++) {
			eggs[idx][0] = input.nextInt();
			eggs[idx][1] = input.nextInt();
		}
		
		maxEggs = Integer.MIN_VALUE;
		
		// 백트래킹 알고리즘을 통해 깨진 계란의 최대갯수 구하기
		track(0);
		
		// 정답 출력
		System.out.println(maxEggs);
		
		input.close();
	}
}