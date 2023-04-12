import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static char[][] pattern;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		pattern = new char[N][N];
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
				pattern[i][j] = ' ';
		
		recur(0, 0, N);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++)
				sb.append(pattern[i][j]);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}
	
	static void recur(int startx, int starty, int size) {
		if(size == 1) {
			pattern[startx][starty] = '*';
			return;
		}
		
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++) {
				if(i == 1 && j == 1)
					continue;
				recur(startx + size/3*i, starty + size/3*j, size/3);
			}
	}
}