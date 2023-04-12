import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int M, N;
	public static int[][] map;
	public static int x, y;
	public static int[] dice = new int[6];
	public static StringBuilder sb = new StringBuilder();
		
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < K; i++) {
			int dir = Integer.parseInt(st.nextToken());
			move(dir);
		}
		
		sb.deleteCharAt(sb.length() - 1);
		System.out.print(sb.toString());
	}

	public static void move(int dir) {
		if(dir == 1) {
			if(y + 1 >= N) return;
			y += 1;
			east();
			copy();
			sb.append(dice[0]).append("\n");
		} else if(dir == 2) {
			if(y - 1 < 0) return;
			y -= 1;
			west();
			copy();
			sb.append(dice[0]).append("\n");
		} else if(dir == 3) {
			if(x - 1 < 0) return;
			x -= 1;
			north();
			copy();
			sb.append(dice[0]).append("\n");
		} else {
			if(x + 1 >= M) return;
			x += 1;
			south();
			copy();
			sb.append(dice[0]).append("\n");
		}
	}
	
	public static void copy() {
		if(map[x][y] == 0) 
			map[x][y] = dice[2];
		else {
			dice[2] = map[x][y];
			map[x][y] = 0;
		}
	}
	
	public static void east() {
		int[] temp = new int[6];
		temp[0] = dice[4];
		temp[1] = dice[1];
		temp[2] = dice[5];
		temp[3] = dice[3];
		temp[4] = dice[2];
		temp[5] = dice[0];
		dice = Arrays.copyOf(temp, 6);
	}
	
	public static void west() {
		int[] temp = new int[6];
		temp[0] = dice[5];
		temp[1] = dice[1];
		temp[2] = dice[4];
		temp[3] = dice[3];
		temp[4] = dice[0];
		temp[5] = dice[2];
		dice = Arrays.copyOf(temp, 6);
	}
	
	public static void north() {
		int[] temp = new int[6];
		temp[0] = dice[1];
		temp[1] = dice[2];
		temp[2] = dice[3];
		temp[3] = dice[0];
		temp[4] = dice[4];
		temp[5] = dice[5];
		dice = Arrays.copyOf(temp, 6);
	}
	
	public static void south() {
		int[] temp = new int[6];
		temp[0] = dice[3];
		temp[1] = dice[0];
		temp[2] = dice[1];
		temp[3] = dice[2];
		temp[4] = dice[4];
		temp[5] = dice[5];
		dice = Arrays.copyOf(temp, 6);
	}
}