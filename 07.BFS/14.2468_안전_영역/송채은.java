import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		int rainmin = 101;
		int rainmax = 0;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				rainmin = Math.min(rainmin, map[i][j]);
				rainmax = Math.max(rainmax, map[i][j]);
			}
		}
				
		int areamax = 0;
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		for(int rain = rainmin-1; rain < rainmax; rain++) {
			Queue<int[]> q = new LinkedList<>();
			boolean[][] visited = new boolean[N][N];
			int area = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] > rain && !visited[i][j]) {
						visited[i][j] = true;
						q.add(new int[] {i, j});
						area++;
						
						while(!q.isEmpty()) {
							int[] check = q.poll();
							for(int d = 0; d < 4; d++) {
								int x = check[0] + dx[d];
								int y = check[1] + dy[d];
								if(x < 0 || x > N-1 || y < 0 || y > N-1)
									continue;
								if(map[x][y] > rain && !visited[x][y]) {
									visited[x][y] = true;
									q.add(new int[] {x, y});
								}
							}
						}
					}
				}
			}
			areamax = Math.max(areamax, area);
		}
		System.out.print(areamax);
	}
}