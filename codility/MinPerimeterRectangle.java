public class MinPerimeterRectangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(300));
		

	}
	public static int solution(int N) {
		int sumMin =Integer.MAX_VALUE;
		for(int i=1 ;i<=Math.sqrt(N)  ;i++) {
			if(N%i==0 ) {
				if(2*(i+N/i) < sumMin) sumMin=2*(i+N/i);				
			}			
		}		
		return sumMin;
    }

}
