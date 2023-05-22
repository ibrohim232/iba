package backend.room.dto;

import backend.room.Room;
import backend.room.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RoomResponseDTO {
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Integer floor;
    private UUID hotelId;
    private RoomType roomType;
    private BigDecimal price;

    public RoomResponseDTO(Room room) {
        this.id = room.getId();
        this.created = room.getCreated();
        this.updated = room.getUpdated();
        this.floor= room.getFloor();
        this.hotelId=room.getHotelId();
        this.roomType=room.getRoomType();
        this.price=room.getPrice();
    }
}
