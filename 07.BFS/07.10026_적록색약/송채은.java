import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		char[][] painting = new char[N+2][N+2];
		char[][] paintingS = new char[N+2][N+2];
		boolean[][] visited = new boolean[N+2][N+2];
		boolean[][] visitedS = new boolean[N+2][N+2];
		
		for(int i = 1; i < N+1; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j = 1; j < N+1; j++) {
				painting[i][j] = input[j-1]; 
				paintingS[i][j] = input[j-1];
				if(input[j-1] == 'G')
					paintingS[i][j] = 'R';
			}
		}
		
		int normal = search(painting, visited, 'R') + search(painting, visited, 'G') + search(painting, visited, 'B');
		int special = search(paintingS, visitedS, 'R') + search(paintingS, visitedS, 'G') + search(paintingS, visitedS, 'B');
		
		System.out.printf("%d %d", normal, special);
	}
	
	static int search(char[][] arr, boolean[][] visit, char c) {
		Queue<Node> q = new LinkedList<>();
		
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		int area = 0;
		
		for(int i = 1; i < N+1; i++) {
			for(int j = 1; j < N+1; j++) {
				if(arr[i][j] == c && !visit[i][j]) {
					visit[i][j] = true;
					Node color = new Node(i, j);
					q.add(color);
					area++;
					
					while(!q.isEmpty()) {
						Node check = new Node(q.peek().x, q.peek().y);
						q.poll();
						
						for(int d = 0; d < 4; d++) {
							int x = check.x + dx[d];
							int y = check.y + dy[d];
							
							if(arr[x][y] == c && !visit[x][y]) {
								visit[x][y] = true;
								Node next = new Node(x, y);
								q.add(next);
							}
						}
					}
				}
			}
		}
		return area;
	}
}

class Node{
	int x, y;
	Node(int x, int y){
		this.x = x;
		this.y = y;
	}
}