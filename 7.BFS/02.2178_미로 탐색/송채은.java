import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] maze = new int[N+2][M+2];
		boolean[][] visited = new boolean[N+2][M+2]; 
		
		for(int i = 1; i < N+1; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j = 1; j < M+1; j++) {
				maze[i][j] = input[j-1] - '0';
			}
		}
		
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		Queue<Node> q = new LinkedList<>();
		int cnt = 0;
		
		Loop: for(int i = 1; i < N+1; i++) {
			for(int j = 1; j < M+1; j++) {
				if(maze[i][j] == 1 && !visited[i][j]) {
					visited[i][j] = true;
					Node coordinate = new Node(i, j, cnt + 1);
					q.add(coordinate);
					while(!q.isEmpty()) {
						Node check = new Node(q.peek().x, q.peek().y, q.peek().cnt);
						q.poll();
						for(int d = 0; d < 4; d++) {
							int x = check.x + dx[d];
							int y = check.y + dy[d];
                            if(maze[x][y] == 1 && !visited[x][y]) {
								if(x == N && y == M) {
									cnt = check.cnt + 1;
									break Loop;
								}
								visited[x][y] = true;
								cnt++;
								Node next = new Node(x, y, check.cnt + 1);
								q.add(next);
								
							}
						}
					}
				}
			}
		}
		System.out.println(cnt);
	}
}

class Node{
	int x, y, cnt;
	
	Node(int x, int y, int cnt){
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}