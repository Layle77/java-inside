package fr.umlv.lab3;

public class ExprSwitches {

   
    public enum Msg {
        DEBUG, WARNING, INFO, ERROR
    }
   
    

        public static String intSwitch(int i) {
               
                switch(i) {
                        case 0 : 
			case 3 :
                                return "zero" ;
                        case 1 :
                                return "one" ;
                        case 2 :
                                return "a lot" ;
                }
               
                throw new IllegalArgumentException() ;
        }
        public static String exprIntSwitch(int i) {
               
               return switch(i) {
                        case 0:
			case 3 : break "zero" ;
                        case 1 :break "one" ;
                        case 2 :break "a lot" ;
			default :throw new IllegalArgumentException() ; 
                };
               
                
        }
   


    
       
        public static String intSwitch2(int i) {
               
                switch(i) {
                        case 0 : 
			case 3 :
                                return "zero" ;
                        case 10 :
                                return "one" ;
                        case 100 :
                                return "a lot" ;
                }
               
                throw new IllegalArgumentException() ;
        }
        public static String exprIntSwitch2(int i) {
               
                return switch(i) {
                        case 0,3 -> "zero" ;
                        case 10 -> "one" ;
                        case 100 ->"a lot" ;
                	default -> throw new IllegalArgumentException() ;
		};
		
        }


   
    	public static String stringSwitch(String s) {
               
                switch(s) {
                        case "foo" : case "viva zorg" :
                                return "zero" ;
                        case "bar" :
                                return "one" ;
                        case "baz" :
                                return "a lot" ;
                }
               
                throw new IllegalArgumentException() ;
        }
    	public static String exprStringSwitch(String s) {
               
                return switch(s) {
                        case "foo" -> "zero" ;
                        case "bar" -> "one" ;
                        case "baz" -> "a lot" ;
 			default ->  throw new IllegalArgumentException() ;             
		};
        }



  
    	public static String enumSwitch(Msg i) {
                switch(i) {
                        case DEBUG : 
			case ERROR :
                                return "zero" ;
                        case WARNING :
                                return "one" ;
                        case INFO :
                                return "a lot" ;
                }
               
                throw new IllegalArgumentException() ;
        }
    	public static String exprEnumSwitch(Msg i) {
                return switch(i) {
                        case DEBUG, ERROR ->"zero" ;
                        case WARNING ->"one" ;
                        case INFO ->"a lot" ;
			default -> throw new IllegalArgumentException() ;
                }; 
        }
    
}
