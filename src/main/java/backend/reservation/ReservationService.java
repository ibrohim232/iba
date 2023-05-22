package backend.reservation;

import backend.hotel.Hotel;
import backend.hotel.HotelRepository;
import backend.reservation.dto.ReservationCreatDTO;
import backend.reservation.dto.ReservationResponseDTO;
import backend.room.Room;
import backend.room.RoomRepository;
import backend.room.RoomService;
import backend.user.User;
import backend.user.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservationService {
    private final ReservationRepository reservationRepository = ReservationRepository.getInstance();
    private final UserRepository userRepository = UserRepository.getInstance();
    private final HotelRepository hotelRepository = HotelRepository.getInstance();
    private final RoomRepository roomRepository = RoomRepository.getInstance();
    private final RoomService roomService = new RoomService();

    public ReservationResponseDTO findById(UUID id) {
        return new ReservationResponseDTO(reservationRepository.findById(id));
    }

    public void delete(UUID id) {
        reservationRepository.deleteById(id);
    }

    public List<ReservationResponseDTO> getAll() {
        ArrayList<ReservationResponseDTO> list = new ArrayList<>();
        reservationRepository.getAll().forEach(reservation -> list.add(new ReservationResponseDTO(reservation)));
        return list;
    }

    public ReservationResponseDTO creat(ReservationCreatDTO reservationCreatDTO) {
        User user = userRepository.findById(reservationCreatDTO.getUserId());
        Room room = roomRepository.findById(reservationCreatDTO.getRoomId());
        Hotel hotel = hotelRepository.findById(reservationCreatDTO.getHotelId());
        if (user == null || room == null || hotel == null) {
            return null;
        }
        BigDecimal bigDecimal = roomService.calculateRoomPrice(reservationCreatDTO.getRoomId(), reservationCreatDTO.getFromDate(), reservationCreatDTO.getToDate());

        Reservation reservation = new Reservation();
        reservation.setId(UUID.randomUUID());
        reservation.setRoomId(reservationCreatDTO.getRoomId());
        reservation.setUserId(reservationCreatDTO.getUserId());
        reservation.setHotelId(reservationCreatDTO.getHotelId());
        reservation.setPrice(bigDecimal);
        reservation.setToDate(reservationCreatDTO.getToDate());
        reservation.setFromDate(reservationCreatDTO.getFromDate());
        reservation.setUpdated(LocalDateTime.now());
        reservation.setCreated(LocalDateTime.now());
        return new ReservationResponseDTO(reservation);
    }

}
