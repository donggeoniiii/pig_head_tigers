import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			char[][] maze = new char[M][N];
			
			Queue<int[]> SG = new LinkedList<>();
			Queue<int[]> Fire = new LinkedList<>();
			boolean[][] visitSG = new boolean[M][N];
			boolean[][] visitFire = new boolean[M][N];
			int[][] spreadTime = new int[M][N];
			for(int i = 0; i < M; i++)
				Arrays.fill(spreadTime[i], 1000);
			
			for(int i = 0; i < M; i++) {
				maze[i] = br.readLine().toCharArray();
				for(int j = 0; j < N; j++) {
					if(maze[i][j] == '@') {
						visitSG[i][j] = true;
						SG.add(new int[] {i, j, 0});
					}
					if(maze[i][j] == '*') {
						visitFire[i][j] = true;
						Fire.add(new int[] {i, j, 0});
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
			
			boolean impossible = true;
			Loop : while(!SG.isEmpty()) {
				int[] check = SG.poll();
                if(check[2] >= spreadTime[check[0]][check[1]])
					continue;
				
				for(int d = 0; d < 4; d++) {
					int x = check[0] + dx[d];
					int y = check[1] + dy[d];
					
					if(x < 0 || x > M-1 || y < 0 || y > N-1) {
						System.out.println(check[2] + 1);
						impossible = false;
						break Loop;
					}
					if(maze[x][y] == '.' && !visitSG[x][y]) {
						visitSG[x][y] = true;
						SG.add(new int[] {x, y, check[2] + 1});
					}
				}
			}
			if(impossible)
				System.out.println("IMPOSSIBLE");
		}
	}
}