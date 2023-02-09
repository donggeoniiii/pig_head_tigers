import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			LinkedList<Character> list = new LinkedList<>();
			ListIterator<Character> ilist = list.listIterator();
			
			String str = br.readLine();
			for(int j = 0; j < str.length(); j++) {
				if(str.charAt(j) == '<') {
					if(ilist.hasPrevious())
						ilist.previous();
				} else if(str.charAt(j) == '>') {
					if(ilist.hasNext())
						ilist.next();
				} else if(str.charAt(j) == '-') {
					if(ilist.hasPrevious()) {
						ilist.previous();
						ilist.remove();
					}
				} else {
					ilist.add(str.charAt(j));
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for(char c : list) {
				sb.append(c);
			}
			
			bw.write(sb.toString());
			bw.write("\n");
			bw.flush();
		}
		
		br.close();
		bw.close();
	}
}
