package backend.user;

import backend.hotel.dto.HotelResponseDTO;
import backend.user.dto.UserCreateDto;
import backend.user.dto.UserResponseDto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UserService {
    private UserRepository userRepository = UserRepository.getInstance();
    private static UserService userService = new UserService();

    private UserService() {
    }

    public UserResponseDto singIn(String name, String password) {
        User user = userRepository.findUserByName(name);
        if (user == null) {
            return null;
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encryptedPassword = digest.digest((password + user.getSalt().toString()).getBytes());
            boolean equals = Arrays.equals(user.getPassword(), encryptedPassword);
            if (equals) {
                return new UserResponseDto(user);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    public UserResponseDto singUp(UserCreateDto userCreateDto) {
        User userByName = userRepository.findUserByName(userCreateDto.getName());
        if (userByName != null) {
            return null;
        }
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        user.setUserType(userCreateDto.getUserType());
        user.setName(userCreateDto.getName());
        user.setBalance(userCreateDto.getBalance());
        user.setSalt(UUID.randomUUID());
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encryptedPassword = digest.digest((userCreateDto.getPassword() + user.getSalt().toString()).getBytes());
            user.setPassword(encryptedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        userRepository.save(user);

        return new UserResponseDto(user);
    }

    public List<UserResponseDto> getAll() {
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        userRepository.getAll().forEach(user -> userResponseDtos.add(new UserResponseDto(user)));
        return userResponseDtos;
    }

    public void delete(UUID id) {
        userRepository.getList().removeIf(user -> user.getId().equals(id));
    }

    public static UserService getInstance() {
        return userService;
    }


}
