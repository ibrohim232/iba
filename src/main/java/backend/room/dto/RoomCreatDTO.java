package backend.room.dto;

import backend.room.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RoomCreatDTO {
    private Integer floor;
    private UUID hotelId;
    private RoomType roomType;
    private BigDecimal price;
}
