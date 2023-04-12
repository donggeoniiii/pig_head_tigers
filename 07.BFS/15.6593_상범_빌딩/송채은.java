import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int H = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			if(H == 0 && R == 0 && C == 0) 
				return;
			
			char[][][] building = new char[H][R][C];
			boolean[][][] visit = new boolean[H][R][C];
			int starth = 0, startr = 0, startc = 0;
			
			for(int h = 0; h < H; h++) {
				for(int r = 0; r < R; r++) {
					building[h][r] = br.readLine().toCharArray();
					for(int c = 0; c < C; c++)
						if(building[h][r][c] == 'S') {
							starth = h;
							startr = r;
							startc = c;
						}
				}
				br.readLine();
			}
			
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {starth, startr, startc, 0});
			visit[starth][startr][startc] = true;
			boolean impossible = true;
			
			int[] dr = {0, 0, 1, -1, 0, 0};
			int[] dc = {1, -1, 0, 0, 0, 0};
			int[] dh = {0, 0, 0, 0, 1, -1};
			
			Loop : while(!q.isEmpty()) {
				int[] check = q.poll();
				
				for(int d = 0; d < 6; d++) {
					int h = check[0] + dh[d];
					int r = check[1] + dr[d];
					int c = check[2] + dc[d];
					
					if(h < 0 || h > H-1 || r < 0 || r > R-1 || c < 0 || c > C-1)
						continue;
					if(building[h][r][c] == 'E') {
						System.out.printf("Escaped in %d minute(s).\n", check[3] + 1);
						impossible = false;
						break Loop;
					}
					if(building[h][r][c] == '.' && !visit[h][r][c]) {
						q.add(new int[] {h, r, c, check[3] + 1});
						visit[h][r][c] = true;
					}
				}
			}
			if(impossible)
				System.out.println("Trapped!");
		}
	}
}