package controller; 
 
public class Pre { 
	public static void require(boolean precondition) { 
		if (!precondition) 
			throw new RuntimeException("Pre condition violated"); 
	} 
} 
 
 
