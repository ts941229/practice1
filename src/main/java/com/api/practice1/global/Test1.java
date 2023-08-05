package com.api.practice1.global;

public class Test1 {
	
	public static void main(String[] args) {
		System.out.println(solution(1,2,3,4)[0]);
		System.out.println(solution(1,2,3,4)[1]);

		System.out.println(solution(9,2,1,3)[0]);
		System.out.println(solution(9,2,1,3)[1]);

		System.out.println(solution(1,1,1,1)[0]);
		System.out.println(solution(1,1,1,1)[1]);
		
	}
	
	public static int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = new int[2];
        
        int denom = 0;
        int numer = 0;
        int gcd = 0;
        
        if(denom1!=denom2) {
        	
        	denom = denom1 * denom2;
        	numer = (numer1*denom2 + numer2*denom1);
        	
        	if(denom>numer) {
        		int a = denom%numer;
 
        		if(denom%a==0) {
        			gcd = a;

        			denom = denom / gcd;
        			numer = numer / gcd;
        		}
        		
        		
        	}else if (denom<numer){
        		int a = numer%denom;
        		
        		if(denom%a==0) {
        			gcd = a;
        			
        			denom = denom / gcd;
        			numer = numer / gcd;
        		}
        		
        	}else {
        		denom = 1;
        		numer = 1;
        	}
        		
        }else {
        	denom = denom1;
			numer = numer1 + numer2;
        }
        
        answer[0] = numer;
        answer[1] = denom;
        
        return answer;
    }
}
