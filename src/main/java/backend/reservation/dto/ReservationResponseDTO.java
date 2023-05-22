package backend.reservation.dto;

import backend.reservation.Reservation;
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
public class ReservationResponseDTO {
    private UUID userId;
    private UUID roomId;
    private UUID hotelId;
    private BigDecimal price;
    private LocalDate fromDate;
    private LocalDate toDate;
    protected UUID id;
    protected LocalDateTime created;
    protected LocalDateTime updated;

    public ReservationResponseDTO(Reservation reservation) {
     this.userId=reservation.getUserId();
     this.roomId=reservation.getRoomId();
     this.hotelId=reservation.getHotelId();
     this.price=reservation.getPrice();
     this.fromDate=reservation.getFromDate();
     this.toDate=reservation.getToDate();
     this.id=reservation.getId();
     this.created=reservation.getCreated();
     this.updated=reservation.getUpdated();
    }
}
