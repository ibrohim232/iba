package backend.hotel.dto;

import backend.hotel.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponseDTO {
    private String name;
    private String location;
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime updated;

    public HotelResponseDTO(Hotel hotel) {
        this.created = hotel.getCreated();
        this.updated = hotel.getUpdated();
        this.id = hotel.getId();
        this.location = hotel.getLocation();
        this.name = hotel.getName();
    }
}
