import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static char[][] map = new char[12][6];
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 맵 정보 입력
		for(int i = 0; i < 12; i++)
			map[i] = br.readLine().toCharArray();
		
		int chain = 0;
		while(pop()) {
			chain++;
			gravity();
		}
		System.out.print(chain);
	}
	
	// 연속된 블럭이 있는지 확인하고 .으로 바꾸기
	public static boolean pop() {
		boolean[][] visit;
		Queue<int[]> change;
		Queue<int[]> count;
		boolean possible = false;
		
		for(int i = 11; i >= 0; i--) {
			for(int j = 0; j < 6; j++) {
				if(map[i][j] != '.') {
					visit = new boolean[12][6];
					change = new LinkedList<>();
					count = new LinkedList<>();
					int cnt = 0;
					
					char c = map[i][j];
					visit[i][j] = true;
					cnt++;
					count.add(new int[] {i, j});
					change.add(new int[] {i, j});
					
					while(!count.isEmpty()) {
						int[] check = count.poll();
						
						for(int dir = 0; dir < 4; dir++) {
							int x = check[0] + dx[dir];
							int y = check[1] + dy[dir];
							
							if(x < 0 || x >= 12 || y < 0 || y >= 6) continue;
							if(!visit[x][y] && map[x][y] == c) {
								count.add(new int[] {x, y});
								change.add(new int[] {x, y});
								visit[x][y] = true;
								cnt++;
							}
						}
					}
					
					if(cnt >= 4) {
						while(!change.isEmpty()) {
							int[] check = change.poll();
							map[check[0]][check[1]] = '.';
							possible = true;
						}
					}
				}
			}
		}
		
		if(possible) return true;
		else return false;
	}
	
	
	// 빈공간 확인하고 블럭 정렬하기
	public static void gravity() {
		for(int j = 0; j < 6; j++) {
			for(int i = 11; i >= 0; i--) {
				if(map[i][j] == '.') {
					int ni = i;
					while(ni > 0 && map[ni][j] == '.') ni--;
					map[i][j] = map[ni][j];
					map[ni][j] = '.';
				}
			}
		}
	}
}