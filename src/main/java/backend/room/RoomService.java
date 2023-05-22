package backend.room;

import backend.hotel.Hotel;
import backend.hotel.HotelRepository;
import backend.hotel.dto.HotelResponseDTO;
import backend.room.dto.RoomCreatDTO;
import backend.room.dto.RoomResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomService {
    private RoomRepository roomRepository = RoomRepository.getInstance();
    private HotelRepository hotelRepository = HotelRepository.getInstance();

    public RoomResponseDTO creat(RoomCreatDTO roomCreatDTO) {
        Hotel hotel = hotelRepository.findById(roomCreatDTO.getHotelId());
        if (hotel != null) {
            return null;
        }
        Room room = new Room();
        room.setId(UUID.randomUUID());
        room.setCreated(LocalDateTime.now());
        room.setUpdated(LocalDateTime.now());
        room.setRoomType(roomCreatDTO.getRoomType());
        room.setFloor(roomCreatDTO.getFloor());
        room.setPrice(roomCreatDTO.getPrice());
        room.setHotelId(roomCreatDTO.getHotelId());
        roomRepository.save(room);
        return new RoomResponseDTO(room);

    }

    public RoomResponseDTO findById(UUID id) {
        Room room = roomRepository.findById(id);
        return new RoomResponseDTO(room);
    }

    public List<RoomResponseDTO> getTypeOfRooms(RoomType roomType) {
        List<RoomResponseDTO> list = new ArrayList<>();
        for (Room room : roomRepository.getAll()) {
            if (room.getRoomType().equals(roomType)) {
                list.add(new RoomResponseDTO(room));
            }
        }
        return list;
    }

    public List<RoomResponseDTO> getTypeOfRooms(RoomType roomType, HotelResponseDTO hotelResponseDTO) {
        List<RoomResponseDTO> list = new ArrayList<>();
        for (Room room : roomRepository.getAll()) {
            if (room.getRoomType().equals(roomType) && room.getHotelId().equals(hotelResponseDTO.getId())) {
                list.add(new RoomResponseDTO(room));
            }
        }
        return list;
    }

    public List<RoomResponseDTO> getTypeOfRooms(HotelResponseDTO hotelResponseDTO) {
        List<RoomResponseDTO> list = new ArrayList<>();
        for (Room room : roomRepository.getAll()) {
            if (room.getHotelId().equals(hotelResponseDTO.getId())) {
                list.add(new RoomResponseDTO(room));
            }
        }
        return list;
    }

    public List<RoomResponseDTO> getAll() {
        List<RoomResponseDTO> list = new ArrayList<>();
        for (Room room : roomRepository.getAll()) {
            list.add(new RoomResponseDTO(room));
        }
        return list;
    }

    public BigDecimal calculateRoomPrice(UUID roomId, LocalDate fromDate, LocalDate toDate) {
        Room room = roomRepository.findById(roomId);

        LocalDate tempFromDate = fromDate;
        long days = 0;

        while (!tempFromDate.equals(toDate)) {
            days++;
            tempFromDate = tempFromDate.plusDays(1);
        }

        return room.getPrice().multiply(BigDecimal.valueOf(days));
    }

    public void delete(UUID id) {
        roomRepository.deleteById(id);
    }

}
