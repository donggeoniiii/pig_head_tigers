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
		int[][] arr = new int[N+2][M+2];
		boolean[][] visited = new boolean[N+2][M+2];
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j < M + 1; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		Queue<Node> q = new LinkedList<>();
		int paintings = 0;
		int area = 0;
		int areaMax = 0;
		
		for(int i = 1; i < N + 1; i++) {
			for(int j = 1; j < M + 1; j++) {
				if(arr[i][j] == 1 && !visited[i][j]) {
					visited[i][j] = true;
					paintings++; area = 1;
					Node coordinate = new Node(i, j);
					q.add(coordinate);
					while(!q.isEmpty()) {
						Node check = new Node(q.peek().x, q.peek().y);
						q.poll();
						for(int d = 0; d < 4; d++) {
							int x = check.x + dx[d];
							int y = check.y + dy[d];
							if(arr[x][y] == 1 && !visited[x][y]) {
								visited[x][y] = true;
								Node next = new Node(x, y);
								q.add(next);
								area++;
							}
						}
					}
					areaMax = Math.max(area, areaMax);
				}
			}
		}
		System.out.println(paintings);
		System.out.println(areaMax);
	}
}

class Node {
	int x, y;
	
	Node(int x, int y){
		this.x = x;
		this.y = y;
	}
}

