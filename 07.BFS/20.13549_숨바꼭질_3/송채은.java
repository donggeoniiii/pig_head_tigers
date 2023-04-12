import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int soobin = Integer.parseInt(st.nextToken());
		int sister = Integer.parseInt(st.nextToken());
		int[] map = new int[100001];
		boolean[] visit = new boolean[100001];
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {soobin, 0});
		visit[soobin] = true;
		
		int time = 0;
		while(!q.isEmpty()) {
			int[] check = q.poll();
			if(check[0] == sister) {
				time = check[1];
				break;
			}
			
			if(check[0] * 2 <= 100000 && !visit[check[0] * 2]) {
				q.add(new int[] {check[0] * 2, check[1]});
				visit[check[0] * 2] = true;
			}
			if(check[0] - 1 >= 0 && !visit[check[0] - 1]) {
				q.add(new int[] {check[0] - 1, check[1] + 1});
				visit[check[0] - 1] = true;
			}
			if(check[0] + 1 <= 100000 && !visit[check[0] + 1]) {
				q.add(new int[] {check[0] + 1, check[1] + 1});
				visit[check[0] + 1] = true;
			}
		}
		
		System.out.print(time);
	}
}