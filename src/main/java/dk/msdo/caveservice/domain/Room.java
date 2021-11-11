package dk.msdo.caveservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room implements Serializable {
    private static final long serialVersionUID = -1L;

    private @Id String id;
    private String description;
    private String creatorId;
    private String creationTimeISO8601;

}
