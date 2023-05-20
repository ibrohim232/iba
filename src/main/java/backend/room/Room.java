package backend.room;

import backend.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode( callSuper = true )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room extends BaseEntity<UUID>
{
    private Integer floor;
    private UUID hotelId;
    private RoomType roomType;
    private BigDecimal price;
}
