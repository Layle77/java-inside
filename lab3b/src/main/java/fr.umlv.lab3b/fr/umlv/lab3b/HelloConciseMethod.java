package fr.umlv.lab3b;
import java.util.*;
public class HelloConciseMethod{
	public static void printSomething() -> System.out.println("Salut");

	public static void printString(String msg) = System.out::println;
	

	public static long countList(List l) ->  l.stream().count();

	

}
