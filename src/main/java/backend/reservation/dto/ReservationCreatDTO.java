package backend.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationCreatDTO {
    private UUID userId;
    private UUID roomId;
    private UUID hotelId;
    private BigDecimal price;
    private LocalDate fromDate;
    private LocalDate toDate;
}
