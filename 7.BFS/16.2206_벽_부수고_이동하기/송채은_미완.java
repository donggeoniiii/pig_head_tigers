import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		boolean[][] visit1 = new boolean[M][N];
		
		for(int m = 0; m < M; m++)
			maze[m] = br.readLine().toCharArray();
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		Queue<int[]> route1 = new LinkedList<>();
		Queue<int[]> route2 = new LinkedList<>();
		
		int startx = 0;
		int starty = 0;
		route1.add(new int[] {startx, starty, 1});
		visit1[startx][starty] = true;
		
		int route_nobreak = Integer.MAX_VALUE;
		boolean success = false;
		
		while(!route1.isEmpty()) {
			int[] check = route1.poll();
			
			if(check[0] == M-1 && check[1] == N-1) {
				success = true;
				route_nobreak = check[2];
				break;
			}
			
			for(int d = 0; d < 4; d++) {
				int x = check[0] + dx[d];
				int y = check[1] + dy[d];
				
				if(x == M-1 && y == N-1) {
					success = true;
					route_nobreak = check[2] + 1;
					break;
				}
				
				if(x < 0 || x > M-1 || y < 0 || y > N-1)
					continue;
				if(!visit1[x][y] && maze[x][y] == '0') {
					route1.add(new int[] {x, y, check[2] + 1});
					visit1[x][y] = true;
				}
				if(!visit1[x][y] && maze[x][y] == '1') {
					route2.add(new int[] {x, y, check[2] + 1});
					visit1[x][y] = true;
				}
			}
		}
		
		int route_break = Integer.MAX_VALUE;
		boolean[][] visit2 = new boolean[M][N];
		Queue<int[]> from1to0 = new LinkedList<>();
		
		Loop : while(!route2.isEmpty()) {
			from1to0.add(route2.poll());
			
			while(!from1to0.isEmpty()) {
				
				int[] check = from1to0.poll();
				for(int d = 0; d < 4; d++) {
					int x = check[0] + dx[d];
					int y = check[1] + dy[d];
					
					if(x == M-1 && y == N-1) {
						success = true;
						route_break = check[2] + 1;
						break Loop;
					}
					
					if(x < 0 || x > M-1 || y < 0 || y > N-1)
						continue;
					if(!visit1[x][y] && maze[x][y] == '0') {
						from1to0.add(new int[] {x, y, check[2] + 1});
						visit1[x][y] = true;
					}
				}
			}
		}
		
		if(success)
			System.out.print(Math.min(route_nobreak, route_break));
		else
			System.out.print("-1");
	}
}
