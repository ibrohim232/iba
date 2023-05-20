package backend.user;

import backend.common.BaseRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class UserRepository extends BaseRepository<User, UUID> {
    private final List<User> users = new ArrayList<>();
    private static UserRepository userRepository;

    private UserRepository() {
    }

    @Override
    protected List<User> getList() {
        return users;
    }

    public static UserRepository getInstance() throws IOException {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        Path of = Path.of("backend.user.User.txt");
        Stream<Path> walk = Files.walk(of.getParent());
        AtomicReference<Path> path = new AtomicReference<>(Path.of(""));
        walk.filter(walk1 ->{
            if (walk1.equals(of)) {
                path.set(walk1);
            }
            return true;
        });



        return userRepository;
    }

    public User findUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }
}
