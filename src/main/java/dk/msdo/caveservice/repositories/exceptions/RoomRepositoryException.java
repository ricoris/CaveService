package dk.msdo.caveservice.repositories.exceptions;

public class RoomRepositoryException extends Exception
{
    Integer error;
    public RoomRepositoryException(String str, Integer error)
    {
        // calling the constructor of parent Exception
        super(str);
        this.error = error;
    }
}
