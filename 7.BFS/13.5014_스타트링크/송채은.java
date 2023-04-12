import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.parseInt(st.nextToken());
		int now = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		int up = Integer.parseInt(st.nextToken());
		int down = Integer.parseInt(st.nextToken());
		int[] dh = {up, -down};
		
		if(now == target) {
			System.out.print("0");
			return;
		}
		
		boolean[] visited = new boolean[height];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {now-1, 0});
		visited[now-1] = true;
		
		while(!q.isEmpty()) {
			int[] check = q.poll();
			if(check[0] == target - 1) {
				System.out.print(check[1]);
				return;
			}
			
			for(int i = 0; i < 2; i++) {
				int floor = check[0] + dh[i];
				if(floor < 0 || floor > height-1)
					continue;
				if(!visited[floor]) {
					q.add(new int[] {floor, check[1] + 1});
					visited[floor] = true;
				}
			}
		}
		System.out.print("use the stairs");
	}
}