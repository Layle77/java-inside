package fr.umlv.labFour;

public class Exemple1 {

	public static void main() {
		// TODO Auto-generated method stub
		var continuationScope = new ContinuationScope("hello1");
	    Continuation suite = new Continuation(continuationScope, ()->{
//	    	Continuation.yield(continuationScope);
	    	System.out.println("hello");
	    	});
	    suite.run();
	    suite.run();
	}

}
