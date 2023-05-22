import backend.hotel.HotelService;
import backend.hotel.dto.HotelCreatDTO;

public class test {
    public static void main(String[] args) {
        HotelService hotelService = new HotelService();
        hotelService.creat(new HotelCreatDTO());

    }
}
