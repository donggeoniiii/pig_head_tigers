import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] want = new int[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int n = 1; n <= N; n++)
				want[n] = Integer.parseInt(st.nextToken());
			
			int success = 0;
			for(int i = 1; i <= N; i++) {
				if(want[i] == 0) continue;
				
				if(i == want[i]) success++;
				else {
					Queue<int[]> q = new LinkedList<>();
					LinkedList<Integer> cycle = new LinkedList<>();
					q.add(new int[] {i, want[i]});
					cycle.add(i);
					
					int repeat = 1;
					
					while(repeat <= N - i + 1) {
						int[] check = q.poll();
						int nextidx = check[1];
						int nextwant = want[nextidx];
						
						if(want[nextwant] == 0)
							break;
						
						cycle.add(check[1]);
						
						if(nextidx == nextwant) 
							break;
						
						if(cycle.contains(nextwant)) {
							int index = cycle.indexOf(nextwant);
							for(int a = 0; a < cycle.size(); a++)
								want[cycle.get(a)] = 0;
							success += cycle.size() - index;
							break;
						}
						q.add(new int[] {nextidx, nextwant});
						repeat++;
					}
				}
			}
			System.out.println(N - success);
		}
	}
}
