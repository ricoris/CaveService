package dk.msdo.caveservice.repositories.exceptions;

public class RoomRepositoryException extends Exception
{
    Integer error;

    public static  Integer E_TEAPOT = 418; // i'm a teapot

    public RoomRepositoryException(String str, Integer error)
    {
        // calling the constructor of parent Exception
        super(str);
        this.error = error;
    }
}
