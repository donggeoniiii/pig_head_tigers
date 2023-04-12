import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N = 14;
	static int cnt = 0;
	static boolean[] col;
	static boolean[] dia1;
	static boolean[] dia2;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new boolean[N];
		dia1 = new boolean[N*2-1];
		dia2 = new boolean[N*2-1];
		
		recur(0);
		
		System.out.print(cnt);
	}
	
	public static void recur(int depth) {
		if(depth == N) {
			cnt++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!col[i] && !dia1[depth+i] && !dia2[depth-i+(N-1)]) {
				col[i] = true;
				dia1[depth+i] = true;
				dia2[depth-i+(N-1)] = true;
				recur(depth+1);
				
				col[i] = false;
				dia1[depth+i] = false;
				dia2[depth-i+(N-1)] = false;
			}
		}
	}
}
