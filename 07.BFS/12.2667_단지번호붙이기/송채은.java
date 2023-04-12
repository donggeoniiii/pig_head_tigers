import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		
		for(int m = 0; m < N; m++) {
			char[] input = br.readLine().toCharArray();
			for(int n = 0; n < N; n++)
				map[m][n] = input[n] - '0';
		}
		
		Queue<int[]> q = new LinkedList<>();
		PriorityQueue<Integer> dimension = new PriorityQueue<>();
		int dangi = 0;
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		for(int m = 0; m < N; m++) {
			for(int n = 0; n < N; n++) {
				if(map[m][n] == 1 && !visited[m][n]) {
					dangi++;
					visited[m][n] = true;
					int area = 1;
					int maxarea = 0;
					q.add(new int[] {m, n, area++});
					
					while(!q.isEmpty()) {
						int[] check = q.poll();
						maxarea = Math.max(maxarea, check[2]);
						
						for(int d = 0; d < 4; d++) {
							int x = check[0] + dx[d];
							int y = check[1] + dy[d];
							if(x < 0 || x > N-1 || y < 0 || y > N-1)
								continue;
							if(map[x][y] == 1 && !visited[x][y]) {
								visited[x][y] = true;
								q.add(new int[] {x, y, area++});
							}
						}
					}
					dimension.add(maxarea);
				}
			}
		}
		System.out.println(dangi);
		while(!dimension.isEmpty())
			System.out.println(dimension.poll());
	}
}