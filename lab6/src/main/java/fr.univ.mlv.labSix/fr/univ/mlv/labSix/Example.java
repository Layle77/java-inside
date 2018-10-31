package fr.univ.mlv.labSix;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.UndeclaredThrowableException;

public class Example {
	private static final MethodHandle EQUAL_METHOD;
	private static final MethodHandle DEFAULT_VALUE;
	static {
		MethodType methodType = MethodType.methodType(boolean.class,Object.class);
		MethodHandle fallback =MethodHandles.constant(int.class, -1);
		DEFAULT_VALUE =MethodHandles.dropArguments(fallback, 0, String.class);
		try {
			EQUAL_METHOD = MethodHandles.lookup().findVirtual(String.class, "equals", methodType);
		} catch (ReflectiveOperationException e) {
			throw new UndeclaredThrowableException(e);
		}
		
	}
	
	
	
	private static String aStaticHello(int value) {
		return "question " + value;
	}
	private String anInstanceHello(int value) {
		return "question " + value;
	}
	
	
	public static MethodHandle stringSwitch(String... matches) {
	    MethodHandle meth = DEFAULT_VALUE;
		for(int i=0;i<matches.length;i++) {
			var equalMethodWithOptions = MethodHandles.insertArguments(EQUAL_METHOD, 1, matches[i]);
			MethodHandle meth1 =MethodHandles.constant(int.class, i);
			MethodHandle success =MethodHandles.dropArguments(meth1, 0, String.class);		
	    	meth = MethodHandles.guardWithTest(equalMethodWithOptions,success ,meth);
	    }
		return meth;
	}
	
	
	
}
