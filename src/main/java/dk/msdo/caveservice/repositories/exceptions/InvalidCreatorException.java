package dk.msdo.caveservice.repositories.exceptions;

public class InvalidCreatorException extends Exception
{
    public InvalidCreatorException(String str)
    {
        // calling the constructor of parent Exception
        super(str);
    }
}
