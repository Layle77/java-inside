package fr.univ.mlv.labSix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;


public class ExampleTests {

	@Test
	public void aStaticHelloTest() throws ReflectiveOperationException {
		Method method1 =Example.class.getDeclaredMethod("aStaticHello", int.class);
		method1.setAccessible(true);
		System.out.println(method1.invoke(Example.class, 10));
	}
	@Test
	public void anInstanceHelloTest() throws ReflectiveOperationException{
		Method method1 =Example.class.getDeclaredMethod("anInstanceHello", int.class);
		method1.setAccessible(true);
		Example ex = new Example();
		System.out.println(method1.invoke(ex, 10));
	}
	@Test
	public void methodHandleStaticHelloTest() throws Throwable {
		Lookup lookup = MethodHandles.lookup(); 
		Lookup privateLookup = MethodHandles.privateLookupIn(Example.class,lookup);
		MethodType methodType = MethodType.methodType(String.class,int.class);
		MethodHandle meth = privateLookup.findStatic(Example.class, "aStaticHello", methodType);
		System.out.println((String)meth.invokeExact(15));	
	}
	@Test
	public void methodHandleInstanceHelloTest() throws Throwable{
		Lookup lookup = MethodHandles.lookup(); 
		Lookup privateLookup = MethodHandles.privateLookupIn(Example.class,lookup);
		MethodType methodType = MethodType.methodType(String.class,int.class);
		MethodHandle meth = privateLookup.findVirtual(Example.class, "anInstanceHello", methodType);
		System.out.println((String)meth.invokeExact(new Example(),15));
	}
	@Test
	public void argumentInsertionInstanceHelloTest() throws Throwable{
		Lookup lookup = MethodHandles.lookup(); 
		Lookup privateLookup = MethodHandles.privateLookupIn(Example.class,lookup);
		MethodType methodType = MethodType.methodType(String.class,int.class);
		MethodHandle meth = privateLookup.findVirtual(Example.class, "anInstanceHello", methodType);
		MethodHandle meth2 =MethodHandles.insertArguments(meth, 1, 8);
		System.out.println((String)meth2.invokeExact(new Example()));
	}
	@Test
	public void dropArgumentInstanceHelloTest() throws Throwable{
		Lookup lookup = MethodHandles.lookup(); 
		Lookup privateLookup = MethodHandles.privateLookupIn(Example.class,lookup);
		MethodType methodType = MethodType.methodType(String.class,int.class);
		MethodHandle meth = privateLookup.findVirtual(Example.class, "anInstanceHello", methodType);
		MethodHandle meth3 =MethodHandles.dropArguments(meth, 2, int.class);
		System.out.println((String)meth3.invokeExact(new Example(),9,48));
	}
	@Test
	public void asTypeArgumentInstanceHelloTest() throws Throwable{
		Lookup lookup = MethodHandles.lookup(); 
		Lookup privateLookup = MethodHandles.privateLookupIn(Example.class,lookup);
		MethodType methodType = MethodType.methodType(String.class,int.class);
		MethodHandle meth = privateLookup.findVirtual(Example.class, "anInstanceHello", methodType);
		
		MethodType methodType2 = MethodType.methodType(String.class, Example.class,Integer.class );
		MethodHandle meth2 = meth.asType(methodType2);
		System.out.println((String)meth2.invokeExact(new Example(),Integer.valueOf(5)));
		
	}
	@Test
	public void constantInstanceHelloTest() throws Throwable{
		MethodHandle meh = MethodHandles.constant(String.class, "59");
		System.out.println((String)meh.invokeExact());
		
	}
	@Test
	public void guardWithTestTest() throws Throwable {
		Lookup lookup = MethodHandles.lookup(); 
		MethodType methodType = MethodType.methodType(boolean.class,Object.class);
		MethodHandle test= lookup.findVirtual(String.class, "equals", methodType);
		MethodHandle test2= MethodHandles.insertArguments(test, 1, "foo");
		
		MethodHandle target =MethodHandles.constant(int.class, 1);
		MethodHandle target2 =MethodHandles.dropArguments(target, 0, String.class);
		
		MethodHandle fallback =MethodHandles.constant(int.class, -1);
		MethodHandle fallback2 =MethodHandles.dropArguments(fallback, 0, String.class);
		
		MethodHandle finalTest =MethodHandles.guardWithTest(test2, target2, fallback2);
		System.out.println((int)finalTest.invokeExact("foo"));
	}
	
	@Test
	public void stringSwitchTests() throws Throwable {
	    var mh = Example.stringSwitch("foo", "bar", "bazz");
	    assertEquals(0, (int)mh.invokeExact("foo"));
	    assertEquals(1, (int)mh.invokeExact("bar"));
	    assertEquals(-1, (int)mh.invokeExact("booze"));
		
		
	}
}
