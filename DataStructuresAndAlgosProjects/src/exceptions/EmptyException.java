package exceptions;

public class EmptyException extends Exception{
    public EmptyException(){
        super();
    }

    public EmptyException(String s){
        super(s);
    }
}
