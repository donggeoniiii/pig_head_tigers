import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean[][] paper = new boolean[M][N];
		
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int startn = Integer.parseInt(st.nextToken());
			int startm = Integer.parseInt(st.nextToken());
			int endn = Integer.parseInt(st.nextToken());
			int endm = Integer.parseInt(st.nextToken());
			
			for(int m = startm; m < endm; m++)
				for(int n = startn; n < endn; n++)
					paper[m][n] = true;
		}
		
		Queue<int[]> q = new LinkedList<>();
		PriorityQueue<Integer> dimension = new PriorityQueue<>();
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		int cnt = 0;
		for(int m = 0; m < M; m++) {
			for(int n = 0; n < N; n++) {
				if(!paper[m][n]) {
					cnt++;
					int area = 1;
					int areamax = 0;
					q.add(new int[] {m, n, area++});
					paper[m][n] = true;
					
					while(!q.isEmpty()) {
						int[] check = q.poll();
						areamax = Math.max(areamax, check[2]);
						
						for(int d = 0; d < 4; d++) {
							int x = check[0] + dx[d];
							int y = check[1] + dy[d];
							if(x < 0 || x > M-1 || y < 0 || y > N-1)
								continue;
							if(!paper[x][y]) {
								q.add(new int[] {x, y, area++});
								paper[x][y] = true;
							}
						}
					}
					dimension.add(areamax);
				}
			}
		}
		System.out.println(cnt);
		while(!dimension.isEmpty())
			System.out.print(dimension.poll() + " ");
	}
}
