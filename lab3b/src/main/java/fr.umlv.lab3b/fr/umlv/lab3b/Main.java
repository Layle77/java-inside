package fr.umlv.lab3b;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello !");
	List l = new ArrayList();
	l.add(2);
	HelloConciseMethod.printSomething();
	HelloConciseMethod.printString("Woaw");
	System.out.println(HelloConciseMethod.countList(l));

	
  }
}
