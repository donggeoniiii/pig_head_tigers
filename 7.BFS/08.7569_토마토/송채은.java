import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[][][] box = new int[M+2][N+2][H+2];
		boolean[][][] visited = new boolean[M+2][N+2][H+2];
		for(int i = 0; i < box.length; i++)
			for(int j = 0; j < box[0].length; j++)
				Arrays.fill(box[i][j], 2);
		Queue<Node> q = new LinkedList<>();
		int days = 0;
		int maxdays = 0;
		
		for(int h = 1; h <= H; h++)
			for(int m = 1; m <= M; m++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int n = 1; n <= N; n++) {
					box[m][n][h] = Integer.parseInt(st.nextToken());
					if(box[m][n][h] == 1) {
						Node tomato = new Node(m, n, h, days);
						q.add(tomato);
						visited[m][n][h] = true;
					}
				}
			}
		
		int[] dm = {-1, 1, 0, 0, 0, 0};
		int[] dn = {0, 0, -1, 1, 0, 0};
		int[] dh = {0, 0, 0, 0, 1, -1};
		
		while(!q.isEmpty()) {
			Node check = new Node(q.peek().m, q.peek().n, q.peek().h, q.peek().days);
			q.poll();
			maxdays = Math.max(check.days, maxdays);
			
			for(int d = 0; d < 6; d++) {
				int m = check.m + dm[d];
				int n = check.n + dn[d];
				int h = check.h + dh[d];
				
				if(box[m][n][h] == 0 && !visited[m][n][h]) {
					visited[m][n][h] = true;
					box[m][n][h] = 1;
					Node next = new Node(m, n, h, check.days + 1);
					q.add(next);
				}
			}
		}
		
		int cnt = 0;
		for(int m = 1; m <= M; m++)
			for(int n = 1; n <= N; n++)
				for(int h = 1; h <= H; h++)
					if(box[m][n][h] == 0) cnt++;
		if(cnt == 0)
			System.out.println(maxdays);
		else
			System.out.println("-1");
	}
}

class Node{
	int m, n, h, days;
	Node(int m, int n, int h, int days){
		this.m = m;
		this.n = n;
		this.h = h;
		this.days = days;
	}
}
