package boj;

// 스티커 붙이기

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;


public class BOJ18808_스티커붙이기 {
	
	// 배열의 크기
	static int N, M;
	
	// 붙일 스티커의 개수
	static int T;
	
	// 노트북
	static int[][] laptop;
	
	// 스티커별로 맞는 위치 찾기 메소드
	static void stick(int[][] curSticker) {
		
		// 스티커 길이 받아오기
		int R = curSticker.length;
		int C = curSticker[0].length;
		
		int idx = 0;
	
		// 붙일 수 있는지 확인하는 변수
		boolean terminated = false;

		// 회전한 스티커 모양, 추가할 위치 담는 변수
		int[][] newSticker = curSticker;
		int cr = -1;
		int cc = -1;

		// 시작점이 될 수 있는 범위내에서, 찾기 전까지 탐색
		while (!terminated || idx < (N-R+1)*(M-C+1)){
			
			// index 갱신
			cr = idx % (M-C+1);
			cc = idx / (M-C+1);
			
			// 돌려가면서 확인
			for (int degree = 0; degree < 4; degree++) {
				
				// 스티커 돌리기
				newSticker = flip(curSticker, degree);
				
				// 스티커가 index를 벗어나면 스킵
				if (cr + newSticker.length >= N || cc + newSticker[0].length >= M)
					continue;
				
				// 붙일 수 없으면 스킵, 붙일 수 있으면 종료조건 켜기
				if (!match(cr, cc, laptop, newSticker))
					continue;
				else 
					terminated = true;
			}			
			
			// index 이동
			idx++;
		}
		
		// 시작위치가 갱신됐다면 붙일 곳이 있는 것이므로 스티커 붙이기
		if (cr != -1) {
			
			for (int r = cr; r < cr + newSticker.length; r++) {
				for (int c = cc; c < cc + newSticker[0].length; c++) {
					laptop[r][c] = newSticker[r][c]; 
				}
			}
		}
		
	}
	
	// 대보기 메소드
	static boolean match(int cr, int cc, int[][] map, int[][] sticker) {
		
		// 해당 좌표부터 스티커 붙이기 시뮬 on
		for (int r = cr; r < cr + sticker.length; r++) {
			for (int c = cc; c < cc + sticker[0].length; c++) {
				
				// 만약 스티커 붙여야 되는 자린데 이미 자리에 뭐가 붙어있으면 false 반환
				if (sticker[r][c] == 1 && map[r][c] == 1) 
					return false;
			}
		}
		
		return true;
	}
	
	// 배열 돌리기 메소드
	static int[][] flip(int[][] curMap, int degree){
		
		int[][] newMap;
		
		// 돌리는 각도에 따라 맵 전환
		switch (degree) {
		case 1:
			// 90도 돌리기(오른쪽)
			
			// 일단 배열 만들고
			newMap = new int[M][N];
			
			// 값은 열부터, 거꾸로 넣기
			for (int c = N-1; c >= 0; c--) {
				for (int r = 0; r < M; r++) {
					newMap[r][c] = curMap[(N-1)-c][r];
				} 
			}
			
			break;
		case 2:
			// 180도 돌리기

			// 일단 배열 만들고
			newMap = new int[N][M];
			
			// 값은 행부터, 거꾸로 넣기
			for (int r = N-1; r >= 0; r--) {
				for (int c = M-1; c >= 0; c--) {
					newMap[r][c] = curMap[(N-1)-r][(M-1)-c];
				}
			}
			
			break;
		case 3:
			// 270도 돌리기(왼쪽)

			// 일단 배열 만들고
			newMap = new int[M][N];
			
			// 값은 열순으로 넣기
			for (int c = 0; c < N; c++) {
				for (int r = M-1; r >= 0; r--) {
					newMap[r][c] = curMap[c][(M-1)-r];
				}
			}
			
			break;
		default:
			// 안 돌리기
			newMap = new int[curMap.length][curMap[0].length];
			for (int r = 0; r < curMap.length; r++) {
				newMap[r] = Arrays.copyOf(curMap[r], curMap[0].length);
			}
			
			break;
		}
		return newMap;
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 노트북 크기
		N = input.nextInt();
		M = input.nextInt();
		laptop = new int[N][M];

		// 스티커 개수
		T = input.nextInt();
		
		// 스티커 정보 저장할 arrayList(스티커의 크기가 서로 다르면 한 배열에 저장 어려움)
		ArrayList<int[][]> stickers = new ArrayList<>();
		
		// 스티커 정보 입력받고 저장
		for (int st = 0; st < T; st++) {
			
			// 스티커별 세로 가로 크기
			int sr = input.nextInt();
			int sc = input.nextInt();
			int[][] newSticker = new int[sr][sc];
			
			// 스티커 정보 입력
			for (int r = 0; r < sr; r++) {
				for (int c = 0; c < sc; c++) {
					newSticker[r][c] = input.nextInt();
				}
			}
			
			// list에 추가
			stickers.add(newSticker);
		}
		
		
		// 차례대로 붙이기
		for (int st = 0; st < T; st++) {
			
			// 스티커 정보 받아오기
			int[][] curSticker = stickers.get(st);
			
			// 붙일곳 찾기
			stick(curSticker);
		}
		
		// 다 붙이고 나서 남는 지점 세기
		int answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (laptop[r][c] == 1) answer++;
			}
		}
		
		// 정답 출력
		System.out.println(answer);
		
		input.close();
	}
}
