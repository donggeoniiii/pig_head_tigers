import java.util.Scanner;

public class Main {
	// 전체 종이
	static int[][] paper;
	
	// -1, 0, 1별 카운트
	static int[] numCount = new int[3]; // -1,0,1 순
	
	// size: 종이 한변의 길이
	static void tearOut(int size, int[] startpoint) {
		// 시작점과 같은 숫자의 개수 체크
		int cnt = 0;
		
		// 시작점 정보 받아오기
		int sr = startpoint[0];
		int sc = startpoint[1];
		
		// 시작점의 숫자
		int num = paper[sr][sc];
		
		
		for (int r = sr; r < sr + size; r++) {
			for (int c = sc; c < sc + size; c++) {
				// 만약 종이에 써있는 숫자가 시작점과 같다면 카운트 증가
				if (paper[r][c] == paper[sr][sc]) cnt++;
			}
		}
		
		// 카운트가 전체 크기와 같지 않으면 쪼개기 
		if (cnt != size*size) {
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					// 새로운 시작지점 전달
					int[] newPoint = {startpoint[0] + r*size/3, startpoint[1] + c*size/3};
					
					// 쪼개기
					tearOut(size/3, newPoint);
				}
			}
		}
		
		// 카운트가 전체 크기와 같으면 카운트만큼 해당 숫자 index에 추가
		else {
			switch (num) {
			case -1:
				numCount[0]++;
				break;
			case 0:
				numCount[1]++;
				break;
			case 1:
				numCount[2]++;
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 행렬의 크기
		int N = input.nextInt();
		
		// 행렬의 값 입력
		paper = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				paper[r][c] = input.nextInt(); 
			}
		}
		
		// 첫 출발은 0,0에서
		int[] startingPoint = {0, 0};
		
		// 재귀 on
		tearOut(N, startingPoint);
		
		// 정답 출력
		for (int idx = 0; idx < 3; idx++) {
			sb.append(numCount[idx]).append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
}