import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            char[] comm = br.readLine().toCharArray();
            int N = Integer.parseInt(br.readLine());
            String nums = br.readLine().replace("[", "");
            nums = nums.replace("]", "");
            StringTokenizer st = new StringTokenizer(nums, ",");
            int[] arr = new int[N];
            
            Deque<Integer> d = new ArrayDeque<>();
            for(int n = 0; n < N; n++) {
                arr[n] = Integer.parseInt(st.nextToken());
                d.add(arr[n]);
            }
            
            System.out.println(cal(d, comm));
        }
    }
    
    static String cal(Deque<Integer> d, char[] comm) {
    	boolean reverseFlag = true;
    	
    	for(int i = 0; i < comm.length; i++) {
    		if(comm[i] == 'R') reverseFlag = !reverseFlag;
    		else {
    			if(d.size() <= 0) return "error";
    			
    			if(reverseFlag) d.pollFirst();
    			else d.pollLast();
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("[");
    	while(d.size() > 1) {
    		if(reverseFlag) sb.append(d.pollFirst()).append(",");
    		else sb.append(d.pollLast()).append(",");
    	}
    	if(d.size() == 1)
    		sb.append(d.pollLast()).append("]");
    	else sb.append("]");
    	
    	return sb.toString();
    }
}