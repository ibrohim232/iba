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
    private final RoomService roomService = RoomService.getInstance();
    private static ReservationService reservationService=new ReservationService();

    private ReservationService() {
    }

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
        if (!validateBalance(reservationCreatDTO)) {
            return null;
        }
        if (!validateReservationDates(reservationCreatDTO)) {
            return null;
        }

        BigDecimal bigDecimal = roomService.calculateRoomPrice(reservationCreatDTO.getRoomId(), reservationCreatDTO.getFromDate(), reservationCreatDTO.getToDate());
        user.setBalance(user.getBalance().subtract(bigDecimal));
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
        reservationRepository.save(reservation);
        return new ReservationResponseDTO(reservation);
    }

    private boolean validateReservationDates(ReservationCreatDTO reservationCreateDto) {
        LocalDate fromDate = reservationCreateDto.getFromDate();
        LocalDate toDate = reservationCreateDto.getToDate();

        if (fromDate.isAfter(toDate) || fromDate.isBefore(LocalDate.now())) {
            return false;
        }

        List<Reservation> reservationsByRoomId = reservationRepository.getReservationsByRoomId(reservationCreateDto.getRoomId());

        for (Reservation reservation : reservationsByRoomId) {
            if (reservation.getFromDate().isAfter(fromDate) && reservation.getToDate().isBefore(fromDate)) {
                return false;
            }

            if (reservation.getFromDate().isAfter(toDate) && reservation.getToDate().isBefore(toDate)) {
                return false;
            }
        }

        return true;
    }

    private boolean validateBalance(ReservationCreatDTO reservationCreateDto) {
        User user = userRepository.findById(reservationCreateDto.getUserId());

        BigDecimal priceOfReservation = roomService.calculateRoomPrice(reservationCreateDto.getRoomId(), reservationCreateDto.getFromDate(), reservationCreateDto.getToDate());
        if (user.getBalance().compareTo(priceOfReservation) >= 0) {
            return true;
        }
        return false;
    }

    public static ReservationService getInstance() {
        return reservationService;
    }

}
