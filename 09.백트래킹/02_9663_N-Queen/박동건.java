// N-Queen

import java.util.Scanner;

public class Main {
	// 체스판 크기 == 퀸 개수
	static int N;

	// 체스판
	static int[][] map;

	// 열 방문배열
	static boolean[] visited;

	// 대각선1 방문배열
	static boolean[] dvisited1; // 좌하우상

	// 대각선2 방문배열
	static boolean[] dvisited2; // 좌상우하

	// 가능한 경우의 수
	static int cnt;

	// 백트래킹 알고리즘
	static void backtrack(int k) {
		// base case: 선택 다 했으면 카운트 +1
		if (k == N) {
			cnt++;
		}

		// recursive case: 모든 경우에 대해 탐색
		for (int c = 0; c < N; c++) {

			// 해당 열에 놓여진게 있으면 스킵
			if (visited[c]) continue;

			// 대각선에 놓여진 게 있으면 스킵
			if (dvisited1[k + c] || dvisited2[N + c - k]) continue;

			// 해당 열 방문체크
			visited[c] = true;

			// 해당 대각선 방문체크
			dvisited1[k + c] = true;
			dvisited2[N + c - k] = true;

			// 다음 퀸 놓으러 이동
			backtrack(k + 1);

			// 해당 좌표에 놓으면서 각 행, 열을 방문했을 때의 모든 경우 체크했으니까 방문체크 해제
			visited[c] = false;
			dvisited1[k + c] = false;
			dvisited2[N + c - k] = false;

		}

	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// 체스판의 크기 == 퀸 개수
		N = input.nextInt();

		// 배열 크기 입력
		map = new int[N][N];
		visited = new boolean[N];
		dvisited1 = new boolean[2 * N + 1]; // 0 ~ 2N
		dvisited2 = new boolean[2 * N + 1]; // -N ~ N이니까 N을 더해서 0 ~ 2N으로

		// 백트래킹 on
		backtrack(0);

		// 카운트 출력
		System.out.println(cnt);
	}
}