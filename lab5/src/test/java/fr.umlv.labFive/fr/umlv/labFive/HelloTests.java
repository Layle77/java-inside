package fr.umlv.labFive;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method")
class HelloTests {
  // pro requires the test class to finish with 'Tests'  
  
  @Test
  void serializer() {
	  Person p = new Person("Gaspard", "LaFondue");
	  Person p2 = new Person("Balthazard", "Savoyarde");
	  System.out.println(Serializer.toJSON(p));
	  System.out.println(Serializer.toJSON(p2));
	  
	  
	  Alien a = new Alien("Lambada",5);
	  Alien a2 = new Alien("Spoutnik",48);
	  
	  System.out.println(Serializer.toJSON(a));
	  System.out.println(Serializer.toJSON(a2));
	 }
  
  

  
  
  
  public class Person {
	  private final String firstName;
	  private final String lastName;

	  public Person(String firstName, String lastName) {
	    this.firstName = Objects.requireNonNull(firstName);
	    this.lastName = Objects.requireNonNull(lastName);
	  }
	  
	  public String getFirstName() {
	    return firstName;
	  }
	  public String getLastName() {
	    return lastName;
	  }
	}
  public class Alien {
	  private final String planet;
	  private final int age;

	  public Alien(String planet, int age) {
	    if (age <= 0) {
	      throw new IllegalArgumentException("Too young...");
	    }
	    this.planet = Objects.requireNonNull(planet);
	    this.age = age;
	  }

	  public String getPlanet() {
	    return planet;
	  }

	  public int getAge() {
	    return age;
	  }
	}
  
  
  
}
