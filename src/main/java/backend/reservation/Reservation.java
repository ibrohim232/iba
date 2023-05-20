package backend.reservation;

import backend.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends BaseEntity<UUID>
{
    private UUID userId;
    private UUID roomId;
    private UUID hotelId;
    private BigDecimal price;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Reservation( ReservationCreateDto createDto, BigDecimal price )
    {
        this.id = UUID.randomUUID();
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();

        this.userId = createDto.getUserId();
        this.roomId = createDto.getRoomId();
        this.hotelId = createDto.getHotelId();

        this.price = price;
        this.fromDate = createDto.getFromDate();
        this.toDate = createDto.getToDate();
    }
}
