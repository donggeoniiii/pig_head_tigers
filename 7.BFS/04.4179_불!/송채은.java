import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		char[][] maze = new char[M][N];
		boolean[][] visitJH = new boolean[M][N];
		boolean[][] visitFire = new boolean[M][N];
		Queue<int[]> JH = new LinkedList<>();
		Queue<int[]> Fire = new LinkedList<>();
		int[][] spreadTime = new int[M][N];
		for(int i = 0; i < M; i++)
			Arrays.fill(spreadTime[i], 1000);
		
		for(int m = 0; m < M; m++) {
			maze[m] = br.readLine().toCharArray();
			for(int n = 0; n < N; n++) {
				if(maze[m][n] == 'J') {
					JH.add(new int[] {m, n, 0});
					visitJH[m][n] = true;
				}
				if(maze[m][n] == 'F') {
					Fire.add(new int[] {m, n, 0});
					visitFire[m][n] = true;
				}
			}
		}
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		while(!Fire.isEmpty()) {
			int[] check = Fire.poll();
			spreadTime[check[0]][check[1]] = check[2];
			
			for(int d = 0; d < 4; d++) {
				int x = check[0] + dx[d];
				int y = check[1] + dy[d];
				
				if(x < 0 || x > M-1 || y < 0 || y > N-1)
					continue;
				if(maze[x][y] != '#' && !visitFire[x][y]) {
					visitFire[x][y] = true;
					Fire.add(new int[] {x, y, check[2] + 1});
				}
			}
		}
		
		while(!JH.isEmpty()) {
			int[] check = JH.poll();
			if(check[2] >= spreadTime[check[0]][check[1]])
				continue;
			
			for(int d = 0; d < 4; d++) {
				int x = check[0] + dx[d];
				int y = check[1] + dy[d];
				
				if(x < 0 || x > M-1 || y < 0 || y > N-1) {
					System.out.print(check[2] + 1);
					return;
				}
				if(maze[x][y] == '.' && !visitJH[x][y]) {
					visitJH[x][y] = true;
					JH.add(new int[] {x, y, check[2] + 1});
				}
			}
		}
		System.out.print("IMPOSSIBLE");
	}
}