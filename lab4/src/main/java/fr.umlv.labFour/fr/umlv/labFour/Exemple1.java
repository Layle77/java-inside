package fr.umlv.labFour;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Exemple1 {
	private enum politique{ STACK,FIFO,RANDOM};
	public static void main() {
		// TODO Auto-generated method stub
//		Object lock = new Object();
		var continuationScope = new ContinuationScope("hello1");
		politique p =politique.RANDOM;
		Scheduler s = new Scheduler(p);
		Runnable myLambda = ()->{
	    	//synchronized (lock) {
    		//Continuation.yield(continuationScope);
    	//}
		s.enqueue(continuationScope);
    	System.out.println(Continuation.getCurrentContinuation(continuationScope));
    	System.out.println("hello Continuation");
    	};
    	
		Runnable myLambda2 = ()->{
		s.enqueue(continuationScope);
		System.out.println("Mylambda 2 phase 1");
		s.enqueue(continuationScope);
		System.out.println("Mylambda 2 phase 2");
    	};
		
		
	    Continuation suite = new Continuation(continuationScope, myLambda);
	    Continuation suite2 = new Continuation(continuationScope, myLambda);
	    Continuation suite3 = new Continuation(continuationScope, myLambda2);
	    Continuation suite4 = new Continuation(continuationScope, myLambda2);
	    
	    System.out.println(Continuation.getCurrentContinuation(continuationScope));
//	    while(!suite.isDone())
//	    	suite.run();
	    suite.run();
	    suite2.run();
	    suite3.run();
	    suite4.run();
	    
	    s.runLoop();
	    
	}


	static class Scheduler{
		private politique myEnum;
		private final Collection<Continuation> deque ;
		public Scheduler(politique myEnum) {
			if(myEnum == politique.RANDOM)
				deque = new ArrayList<>();
			else
				deque = new ArrayDeque<>();
			this.myEnum = myEnum;
		}
		
		public void enqueue(ContinuationScope cs) {
			var continuation = Continuation.getCurrentContinuation(cs);
			if(continuation==null) {
				throw new IllegalStateException(); 
			}
			deque.add(continuation);
			Continuation.yield(cs);
		}
		
		public void runLoop() {
			while(!deque.isEmpty()){
				Continuation continuation;
				switch(myEnum) {
					case STACK: {
						var d = (ArrayDeque<Continuation>)deque;
						continuation = d.removeLast();
						break;
					}
					case FIFO:{
						var d = (ArrayDeque<Continuation>)deque;
						continuation =d.removeFirst();
						break;
					}
					default:{
						var d = (ArrayList<Continuation>)deque;
						continuation =d.remove(ThreadLocalRandom.current().nextInt(d.size()));
						break;
					}
				}
				continuation.run();
			}
		}
	
	}

}
