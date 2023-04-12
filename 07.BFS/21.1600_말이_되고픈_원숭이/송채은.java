import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[M][N];
		boolean[][] visith = new boolean[M][N];
		boolean[][] visitm = new boolean[M][N];
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int n = 0; n < N; n++)
				if(st.nextToken().equals("1"))
					map[m][n] = true;
		}
		
		int[] mx = {-1, 1, 0, 0};
		int[] my = {0, 0, -1, 1};
		int[] hx = {-2, -1, 1, 2, 2, 1, -1, -2};
		int[] hy = {-1, -2, -2, -1, 1, 2, 2, 1};

		int move = -1;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0, 0));
		visith[0][0] = true;
		visitm[0][0] = true;
		
		while(!q.isEmpty()) {
			Node check = q.poll();
			if(check.x == M-1 && check.y == N-1) {
				move = check.movecnt;
				break;
			}
			
			for(int dir = 0; dir < 4; dir++) {
				int x = check.x + mx[dir];
				int y = check.y + my[dir];
				if(x < 0 || x >= M || y < 0 || y >= N)
					continue;
				if(check.horse > 0) {
					if(!map[x][y] && !visith[x][y]) {
						q.add(new Node(x, y, check.horse, check.movecnt+1));
						visith[x][y] = true;
					}
				}else {
					if(!map[x][y] && !visitm[x][y]) {
						q.add(new Node(x, y, check.horse, check.movecnt+1));
						visitm[x][y] = true;
					}
				}
			}
			
			if(check.horse < K) {
				for(int dir = 0; dir < 8; dir++) {
					int x = check.x + hx[dir];
					int y = check.y + hy[dir];
					if(x < 0 || x >= M || y < 0 || y >= N)
						continue;
					if(!map[x][y] && !visith[x][y]) {
						q.add(new Node(x, y, check.horse+1, check.movecnt+1));
						visith[x][y] = true;
					}
				}
			}
		}
		System.out.print(move);
	}
}

class Node{
	int x, y;
	int horse;
	int movecnt;
	Node(int x, int y, int horse, int movecnt){
		this.x = x;
		this.y = y;
		this.horse = horse;
		this.movecnt = movecnt;
	}
}
