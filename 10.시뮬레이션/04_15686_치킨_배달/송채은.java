import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static int[][] map;
	public static int Chickencnt;
	public static ArrayList<House> depart = new ArrayList<>();
	public static ArrayList<Chicken> dest = new ArrayList<>();
	public static boolean[] use;
	public static int min;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		// 지도 정보 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1)
					depart.add(new House(i, j));
				else if(map[i][j] == 2) {
					dest.add(new Chicken(i, j));
					Chickencnt++;
				}
			}
		}
		
		// 각 집에서 모든 치킨집까지의 거리를 계산
		int sum = 0;
		for(int i = 0; i < depart.size(); i++) {
			int x = depart.get(i).x;
			int y = depart.get(i).y;
			depart.get(i).dist = new int[Chickencnt];
			
			for(int j = 0; j < dest.size(); j++) {
				int nx = dest.get(j).x;
				int ny = dest.get(j).y;
				int distance = Math.abs(nx - x) + Math.abs(ny - y);
				depart.get(i).dist[j] = distance;
			}
		}
        
		// 치킨집 N개 고르기
		min = Integer.MAX_VALUE;
		use = new boolean[Chickencnt];
		
		for(int i = 1; i <= M; i++)
			selectMchicken(0, 0, i);
		
		System.out.print(min);
	}
	
	public static void selectMchicken(int index, int m, int limit) {
		// 종료 조건
		if(m == limit) {
			int sum = 0;
			for(int i = 0; i < depart.size(); i++) {
				int minDist = Integer.MAX_VALUE;
				for(int j = 0; j < Chickencnt; j++)
					if(use[j])
						minDist = Math.min(minDist, depart.get(i).dist[j]);
				sum += minDist;
			}
			min = Math.min(sum, min);
			return;
		}
		if(index >= Chickencnt) return;
		
		// 반복 조건
		use[index] = true;
		selectMchicken(index + 1, m + 1, limit);
		
		use[index] = false;
		selectMchicken(index + 1, m, limit);
	}
}

class House{
	int x, y;
	int[] dist;
	House(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Chicken{
	int x, y;
	Chicken(int x, int y){
		this.x = x;
		this.y = y;
	}
}