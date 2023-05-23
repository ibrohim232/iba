import backend.hotel.HotelService;
import backend.hotel.dto.HotelCreatDTO;
import backend.user.User;
import backend.user.UserRepository;

public class test {
    public static void main(String[] args) {
        UserRepository instance = UserRepository.getInstance();
        User save = instance.save(new User());

    }
}
