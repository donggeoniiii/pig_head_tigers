import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static char[][] tree;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tree = new char[N][N];
		
		for(int i = 0; i < N; i++)
			tree[i] = br.readLine().toCharArray();
		
		recur(0, 0, N);
		
		System.out.println(sb.toString());
	}
	
	static void recur(int startx, int starty, int size) {
		if(size == 1) {
			sb.append(tree[startx][starty]);
			return;
		}
		
		if(check(startx, starty, size)) {
			sb.append(tree[startx][starty]);
		}else {
			sb.append('(');
			recur(startx, starty, size/2);
			recur(startx, starty + size/2, size/2);
			recur(startx + size/2, starty, size/2);
			recur(startx + size/2, starty + size/2, size/2);
			sb.append(')');
		}
		
	}
	
	static boolean check(int startx, int starty, int size) {
		char temp = tree[startx][starty];
		for(int i = startx; i < startx + size; i++)
			for(int j = starty; j < starty + size; j++)
				if(tree[i][j] != temp)
					return false;
		return true;
	}
}