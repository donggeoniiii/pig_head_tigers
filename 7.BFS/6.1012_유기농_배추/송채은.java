import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[][] ground = new int[M+2][N+2];
			boolean[][] visited = new boolean[M+2][N+2];
			
			for(int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine(), " ");
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				ground[x+1][y+1] = 1;
			}
			
			int worm = 0;
			Queue<Node> q = new LinkedList<>();
			for(int i = 1; i < M + 1; i++) {
				for(int j = 1; j < N + 1; j++) {
					if(ground[i][j] == 1 && !visited[i][j]) {
						visited[i][j] = true;
						Node baechoo = new Node(i, j);
						q.add(baechoo);
						worm++;
						
						int[] dm = {1, -1, 0, 0};
						int[] dn = {0, 0, -1, 1};
						while(!q.isEmpty()) {
							Node check = new Node(q.peek().M, q.peek().N);
							q.poll();
							
							for(int d = 0; d < 4; d++) {
								int m = check.M + dm[d];
								int n = check.N + dn[d];
								if(ground[m][n] == 1 && !visited[m][n]) {
									visited[m][n] = true;
									Node next = new Node(m, n);
									q.add(next);
								}
							}
						}
					}
				}
			}
			System.out.println(worm);
		}
	}
}

class Node{
	int M, N;
	Node(int M, int N){
		this.M = M;
		this.N = N;
	}
}
