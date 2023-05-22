package backend.hotel;

import backend.hotel.dto.HotelCreatDTO;
import backend.hotel.dto.HotelResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HotelService {
    private HotelRepository hotelRepository = HotelRepository.getInstance();

    public HotelResponseDTO creat(HotelCreatDTO hotelCreatDTO) {
        Hotel hotel = new Hotel();
        hotel.setId(UUID.randomUUID());
        hotel.setName(hotelCreatDTO.getName());
        hotel.setCreated(LocalDateTime.now());
        hotel.setUpdated(LocalDateTime.now());
        hotel.setLocation(hotel.getLocation());
        hotelRepository.save(hotel);
        return new HotelResponseDTO(hotel);
    }

    public List<HotelResponseDTO> getAll() {
        List<HotelResponseDTO> hotelResponseDTOS = new ArrayList<>();
        for (Hotel hotel : hotelRepository.getAll()) {
            hotelResponseDTOS.add(new HotelResponseDTO(hotel));
        }
        return hotelResponseDTOS;
    }

    public HotelResponseDTO findById(UUID id) {
        Hotel byId = hotelRepository.findById(id);
        return new HotelResponseDTO(byId);
    }

    public void delete(UUID id) {
        hotelRepository.deleteById(id);
    }
}
