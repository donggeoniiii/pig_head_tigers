import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			boolean [][] visited = new boolean[N][N];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int startx = Integer.parseInt(st.nextToken());
			int starty = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine(), " ");
			int endx = Integer.parseInt(st.nextToken());
			int endy = Integer.parseInt(st.nextToken());
			
			int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
			int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
			
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] {startx, starty, 0});
			visited[startx][starty] = true;
			
			Loop : while(!q.isEmpty()) {
				int[] check = q.poll();
				if(check[0] == endx && check[1] == endy) {
					System.out.println(check[2]);
					break;
				}
				
				for(int d = 0; d < 8; d++) {
					int x = check[0] + dx[d];
					int y = check[1] + dy[d];
					if(x == endx && y == endy) {
						System.out.println(check[2] + 1);
						break Loop;
					}
					if(x < 0 || x > N-1 || y < 0 || y > N-1)
						continue;
					if(!visited[x][y]) {
						q.add(new int[] {x, y, check[2] + 1});
						visited[x][y] = true;
					}
				}
			}
		}
	}
}