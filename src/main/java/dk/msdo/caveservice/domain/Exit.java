package dk.msdo.caveservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Exit implements Serializable {
    private static final long serialVersionUID = -1L;

    Direction direction;
}
