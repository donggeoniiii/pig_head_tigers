import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int[][] board, boardtemp;
	public static int max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		boardtemp = new int[N][N];
		max = 2;
		
		// 보드 초기 상태 입력
		int notzerocnt = 0;
		int onlyi = 0;
		int onlyj = 0;
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] != 0) {
					notzerocnt++;
					onlyi = i;
					onlyj = j;
				}
			}
		}
		
		if(notzerocnt == 1) {
			System.out.print(board[onlyi][onlyj]);
			return;
		}
		
		for(int i = 1; i < 5; i++)
			move(board, 0, i);
		
		System.out.print(max);
	}
	
	// 보드 안의 숫자 이동
	// dir : 1(위) 2(아래) 3(왼쪽) 4(오른쪽)
	public static void move(int[][] arr, int num, int dir) {
		// 종료 조건
		// 숫자가 한개 남았을 때 -> 아직 구현 미완성
		if (num == 5) {
			int arrmax = 0;
			for(int i = 0; i < N; i++)
				for(int j = 0; j < N; j++)
					arrmax = Math.max(arrmax, arr[i][j]);
			max = Math.max(max, arrmax);
			return;
		}
		
        // 배열 복사
		int[][] temp = new int[N][N];
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				temp[i][j] = arr[i][j];
		
		gravity(temp, dir);
		attach(temp, dir);
		gravity(temp, dir);
		
		for(int i = 1; i < 5; i++)
			move(temp, num + 1, i);
	}
	
	// 특정 방향으로 모든 숫자 이동
	public static void gravity(int[][] arr, int dir) {
		if(dir == 1) {
			for(int j = 0; j < N; j++) {
				for(int i = 0; i < N; i++) {
					if(arr[i][j] == 0) {
						int ni = i;
						while(ni < N-1 && arr[ni][j] == 0) ni++;
						arr[i][j] = arr[ni][j];
						arr[ni][j] = 0;
					}
				}
			}
		}
		else if(dir == 2) {
			for(int j = 0; j < N; j++) {
				for(int i = N-1; i >= 0; i--) {
					if(arr[i][j] == 0) {
						int ni = i;
						while(ni > 0 && arr[ni][j] == 0) ni--;
						arr[i][j] = arr[ni][j];
						arr[ni][j] = 0;
					}
				}
			}
		}
		else if(dir == 3) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(arr[i][j] == 0) {
						int nj = j;
						while(nj < N-1 && arr[i][nj] == 0) nj++;
						arr[i][j] = arr[i][nj];
						arr[i][nj] = 0;
					}
				}
			}
		}
		else {
			for(int i = 0; i < N; i++) {
				for(int j = N-1; j >= 0; j--) {
					if(arr[i][j] == 0) {
						int nj = j;
						while(nj > 0 && arr[i][nj] == 0) nj--;
						arr[i][j] = arr[i][nj];
						arr[i][nj] = 0;
					}
				}
			}
		}
	}
	
	// 연속으로 같은 숫자가 있을 때 합치기
	public static void attach(int[][] arr, int dir) {
		if(dir == 1) {
			for(int j = 0; j < N; j++) {
				for(int i = 0; i < N-1; i++) {
					if(arr[i][j] != 0 && arr[i][j] == arr[i+1][j]) {
						arr[i][j] *= 2;
						arr[i+1][j] = 0;
					}
				}
			}
		}
		else if(dir == 2) {
			for(int j = 0; j < N; j++) {
				for(int i = N-1; i > 0; i--) {
					if(arr[i][j] != 0 && arr[i][j] == arr[i-1][j]) {
						arr[i][j] *= 2;
						arr[i-1][j] = 0;
					}
				}
			}
		}
		else if(dir == 3) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N-1; j++) {
					if(arr[i][j] != 0 && arr[i][j] == arr[i][j+1]) {
						arr[i][j] *= 2;
						arr[i][j+1] = 0;
					}
				}
			}
		}
		else {
			for(int i = 0; i < N; i++) {
				for(int j = N-1; j > 0; j--) {
					if(arr[i][j] != 0 && arr[i][j] == arr[i][j-1]) {
						arr[i][j] *= 2;
						arr[i][j-1] = 0;
					}
				}
			}
		}
	}
}