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
	  assertEquals(toJSON(p),Serializer.toJSON(p));
	  assertEquals(toJSON(p2),Serializer.toJSON(p2));
	  assertEquals(toJSON(a),Serializer.toJSON(a));
	  assertEquals(toJSON(a2),Serializer.toJSON(a2));
	 }
  
  

  
  
	
	
  public class Person {
	  private final String firstName;
	  private final String lastName;

	  public Person(String firstName, String lastName) {
	    this.firstName = Objects.requireNonNull(firstName);
	    this.lastName = Objects.requireNonNull(lastName);
	  }
	  @JSONProperty
	  public String getFirstName() {
	    return firstName;
	  }
	  @JSONProperty
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
	  @JSONProperty
	  public String getPlanet() {
	    return planet;
	  }
	  @JSONProperty(rename = "members")
	  public int getAge() {
	    return age;
	  }
	}
  public static String toJSON(Person person) {
	    return
	        "{\n" +
	        "  \"firstName\": \"" + person.getFirstName() + "\"\n" +
	        "  \"lastName\": \"" + person.getLastName() + "\"\n" +
	        "}\n";
	  }

	  public static String toJSON(Alien alien) {
	    return 
	        "{\n" + 
	        "  \"planet\": \"" + alien.getPlanet() + "\"\n" + 
	        "  \"members\": \"" + alien.getAge() + "\"\n" + 
	        "}\n";
	  }
  
  
}
