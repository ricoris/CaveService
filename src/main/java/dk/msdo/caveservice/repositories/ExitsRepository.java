package dk.msdo.caveservice.repositories;
import dk.msdo.caveservice.domain.Direction;
import dk.msdo.caveservice.domain.Exit;

import java.util.List;
import java.util.Set;


public interface ExitsRepository {
    public boolean addExitAtPosition(String position, Direction direciton);

    public Set<String> getAllExitsAtPosition(String position);
}