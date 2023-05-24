import backend.hotel.Hotel;
import backend.hotel.HotelRepository;
import backend.hotel.HotelService;
import backend.hotel.dto.HotelCreatDTO;

public class test {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
     HotelRepository instance = HotelRepository.getInstance();
        instance.save(hotel);

    }
}
