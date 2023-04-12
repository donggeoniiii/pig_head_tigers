import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[][] map = new boolean[N][N];
		boolean[][] visit = new boolean[N][N];
		
		for(int m = 0; m < N; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int n = 0; n < N; n++)
				if(st.nextToken().equals("1"))
					map[m][n] = true;
		}
		
		// 같은 육지에 속하는 땅 분류 작업
		Queue<int[]> q = new LinkedList<>();
		int[][] group = new int[N][N];
		int groupcnt = 1;
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		for(int m = 0; m < N; m++) {
			for(int n = 0; n < N; n++) {
				if(map[m][n] == true && !visit[m][n]) {
					q.add(new int[] {m, n});
					visit[m][n] = true;
					group[m][n] = groupcnt++;
					
					while(!q.isEmpty()) {
						int[] check = q.poll();
						
						for(int dir = 0; dir < 4; dir++) {
							int x = check[0] + dx[dir];
							int y = check[1] + dy[dir];
							
							if(x < 0 || x >= N || y < 0 || y >= N)
								continue;
							if(map[x][y] == true && !visit[x][y]) {
								q.add(new int[] {x, y});
								visit[x][y] = true;
								group[x][y] = group[check[0]][check[1]];
							}
						}
					}
				}
			}
		}
		
		int distance_min = Integer.MAX_VALUE;
		boolean[][] alreadyvis = new boolean[N][N];
		
		for(int m = 0; m < N; m++) {
			for(int n = 0; n < N; n++) {
				if(map[m][n] == true) {
					alreadyvis[m][n] = true;
					boolean[][] vis = new boolean[N][N];
					
					q.add(new int[] {m, n, 0});
					vis[m][n] = true;
					
					while(!q.isEmpty()) {
						int[] check = q.poll();
						
						for(int dir = 0; dir < 4; dir++) {
							int x = check[0] + dx[dir];
							int y = check[1] + dy[dir];
							
							if(x < 0 || x >= N || y < 0 || y >= N || alreadyvis[x][y])
								continue;
							if(map[x][y] == true && group[x][y] != 0 && group[x][y] != group[m][n]) {
								distance_min = Math.min(distance_min, check[2]);
								continue;
							}
							if(map[x][y] == false && !vis[x][y]) {
								q.add(new int[] {x, y, check[2] + 1});
								vis[x][y] = true;
							}
						}
					}
				}
			}
		}
		
		System.out.print(distance_min);
	}
}
