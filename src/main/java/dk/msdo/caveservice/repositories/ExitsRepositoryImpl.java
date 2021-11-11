package dk.msdo.caveservice.repositories;

import dk.msdo.caveservice.domain.Direction;
import dk.msdo.caveservice.domain.Exit;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class ExitsRepositoryImpl implements ExitsRepository {

        private final String referencePrefix = "Exits:";

        @Resource(name="exitsTemplate")          // 'redisTemplate' is defined as a Bean in Configuration.java
        private SetOperations<String, String> exitSetOperations;

        @Override
        public boolean addExitAtPosition(String position, Direction direction) {
            //creates one record in Redis DB if record with that Id is not present
            String reference = referencePrefix + position;
            if (Direction.isValid(direction.toString())) {
                    exitSetOperations.add(reference, direction.toString());
                    return true;
            }
            return false;
        }

        @Override
        public Set<String> getAllExitsAtPosition(String position) {
                String reference = referencePrefix + position;
                return exitSetOperations.members(reference);
        }

}
