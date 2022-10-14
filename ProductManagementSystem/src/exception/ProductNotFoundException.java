package exception;

public class ProductNotFoundException extends ClassNotFoundException {
	ProductNotFoundException(){
		
	}
	ProductNotFoundException(String message){
		System.out.println(message);
	}

}
