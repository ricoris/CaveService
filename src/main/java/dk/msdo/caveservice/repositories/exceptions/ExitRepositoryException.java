package dk.msdo.caveservice.repositories.exceptions;

public class ExitRepositoryException extends Exception
{
    public Integer error;

    public static  Integer E_TEAPOT = 418; // i'm a teapot

    public ExitRepositoryException(String str, Integer error)
    {
        // calling the constructor of parent Exception
        super(str);
        this.error = error;
    }
}
