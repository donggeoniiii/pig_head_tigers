import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int MAX_SIZE = 1000005;
	static char[] data = new char[MAX_SIZE];
	static int[] pre = new int[MAX_SIZE];
	static int[] nxt = new int[MAX_SIZE];
	static int unused = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int cursor = 0;
		Arrays.fill(pre, -1);
		Arrays.fill(nxt, -1);
		
		String str = br.readLine();
		for(int i = 0; i < str.length(); i++) {
			insert(i, str.charAt(i));
			cursor++;
		}
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			char in = input.charAt(0);
			
			if(in == 'P') {
				char c = input.charAt(2);
				cursor = insert(cursor, c);
			} else if(in == 'L') {
				if(pre[cursor] != -1)
					cursor = pre[cursor];
			} else if(in == 'D') {
				if(nxt[cursor] != -1)
					cursor = nxt[cursor];
			} else {
				if(pre[cursor] != -1)
					cursor = erase(cursor);
			}			
		}
		
		int cur = nxt[0];
		while(cur != -1) {
			bw.write(data[cur]);
			cur = nxt[cur];
		}
		bw.flush();
		br.close();
		bw.close();
	}
	
	public static int insert(int cursor, char c) {
		data[unused] = c;
		pre[unused] = cursor;
		nxt[unused] = nxt[cursor];
		if(nxt[cursor] != -1)
			pre[nxt[cursor]] = unused;
		nxt[cursor] = unused;
		unused++;
		return nxt[cursor];
	}
	
	public static int erase(int cursor) {
		nxt[pre[cursor]] = nxt[cursor];
		if(nxt[cursor] != -1)
			pre[nxt[cursor]] = pre[cursor];
		return pre[cursor];
	}
}
