package exceptions;

public class InvalidInfixExpressionException extends Exception{
    public InvalidInfixExpressionException(){
        super();
    }

    public InvalidInfixExpressionException(String s){
        super(s);
    }
}
