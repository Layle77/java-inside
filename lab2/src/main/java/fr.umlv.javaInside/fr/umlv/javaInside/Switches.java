package fr.umlv.javaInside;

public class Switches {
	public static String intSwitch(int val) {
		switch (val){
		case 0:
			return "zero";
		case 1:
			return "one";
		case 2:
			return "alot";
		default:
			throw new IllegalArgumentException();
		
		}
		
		
	}
}
