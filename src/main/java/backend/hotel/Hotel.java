package backend.hotel;

import backend.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode( callSuper = true )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel extends BaseEntity<UUID>
{
    private String name;
    private String location;
}
