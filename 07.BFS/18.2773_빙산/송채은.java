import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[M][N];
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int n = 0; n < N; n++)
				map[m][n] = Integer.parseInt(st.nextToken());
		}
		
		int year = 0;
		if(bfs(map) >= 2) {
			System.out.print("0");
			return;
		}
		
		while(true) {
			map = after(map);
			if(map == null) {
				System.out.print("0");
				return;
			}
			if(bfs(map) < 2) year++;
			else break;
		}
		
		System.out.print(year + 1);
		
	}
	
	static int[][] after(int[][] arr){
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int[][] zerocnt = new int[arr.length][arr[0].length];
		
		for(int d = 0; d < 4; d++) {
			for(int m = 0; m < arr.length; m++) {
				for(int n = 0; n < arr[0].length; n++) {
					int x = m + dx[d];
					int y = n + dy[d];
					
					if(x < 0 || x > arr.length - 1 || y < 0 || y > arr[0].length - 1)
						continue;
					if(arr[x][y] == 0) {
						zerocnt[m][n]--;
					}
				}
			}
		}
		
		int allzerocheck = 0;
		for(int m = 0; m < arr.length; m++)
			for(int n = 0; n < arr[0].length; n++) {
				arr[m][n] += zerocnt[m][n];
				if(arr[m][n] < 0) arr[m][n] = 0;
				if(arr[m][n] == 0) allzerocheck++;
			}
		
		if(allzerocheck == arr.length * arr[0].length)
			return null;
		return arr;
	}
	
	static int bfs(int[][] arr) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[arr.length][arr[0].length];
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		int piece = 0;
		
		for(int m = 0; m < arr.length; m++) {
			for(int n = 0; n < arr[0].length; n++) {
				if(arr[m][n] != 0 && !visit[m][n]) {
					piece++;
					visit[m][n] = true;
					q.add(new int[] {m, n});
					
					while(!q.isEmpty()) {
						int[] check = q.poll();
						
						for(int d = 0; d < 4; d++) {
							int x = check[0] + dx[d];
							int y = check[1] + dy[d];
							
							if(x < 0 || x > arr.length - 1 || y < 0 || y > arr[0].length - 1)
								continue;
							if(arr[x][y] != 0 && !visit[x][y]) {
								visit[x][y] = true;
								q.add(new int[] {x, y});
							}
						}
					}
				}
			}
		}
		
		return piece;
	}
}
