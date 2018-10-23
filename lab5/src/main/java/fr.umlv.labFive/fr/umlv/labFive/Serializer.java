package fr.umlv.labFive;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.stream.Collectors;
import static java.util.function.Predicate.not;

public class Serializer {
	
//getmethod renvoie un tableau de methode : tableau = mutable donc clone le tableau
	
	public static String toJSON(Object o) {
		
		
//		return Arrays.stream(o.getClass().getMethods())
//				.filter(method -> method.getName().startsWith("get"))
//				.map(method -> propertyName(method.getName())).collect(Collectors.joining());

		
//		return "{\n"+Arrays.stream(o.getClass().getMethods())
//		.filter(method -> method.getName().startsWith("get") && method.getParameterCount()==0)
//		.filter(not(method -> method.getDeclaringClass() == Object.class && method.getName().equals("getClass")))
//		.map(method -> methodToString(method,o)).collect(Collectors.joining("\n"))+"\n}\n";
//		
		return "{\n"+Arrays.stream(o.getClass().getMethods())
		.filter(method -> method.getName().startsWith("get") && method.getParameterCount()==0)
		.filter(method -> method.isAnnotationPresent(JSONProperty.class))
		.map(method -> methodToString(method,o)).collect(Collectors.joining("\n"))+"\n}\n";
		
	}
	
	public static Object invoker(Method method,Object o) {
		try {
			return method.invoke(o);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			var cause = e.getCause();
			if(cause instanceof RuntimeException) {
				throw (RuntimeException)cause;
			}
			if(cause instanceof Error) {
				throw (Error)cause;
			}
			throw new UndeclaredThrowableException(cause);
			
		}
	
	}
	
	private static String methodToString(Method method,Object o) {
		
		return "  \""+getMethodName(method)+"\": \""+invoker(method,o)+"\"";
		
	}

	private static String getMethodName(Method method) {
		
		var annotation = method.getAnnotation(JSONProperty.class) ;
		
		if(annotation.rename().isEmpty())
			return propertyName(method.getName());
		else
			return annotation.rename() ;
	}
	
    private static String propertyName(String name) {
        return Character.toLowerCase(name.charAt(3)) + name.substring(4);
    }
	
}
