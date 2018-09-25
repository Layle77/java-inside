package fr.umlv.javainside.labOne;

import java.util.stream.IntStream;

public class Sums {
	public static int loopSum(int n) {
		int result=0;
		for(int i=1;i<n;i++) {
			result +=i;
		}
		return result;
	}
	public static int StreamSum(int n) {
		return IntStream.range(0,n).sum();
	}
	
}
