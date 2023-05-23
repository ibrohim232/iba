package backend.user;

import backend.common.BaseRepository;

import java.io.File;
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
    private static String file="src/backend/user/user.txt";

    private UserRepository() {
    }

    @Override
    protected List<User> getList() {
        return users;
    }

    @Override
    protected String getPath() {
        File file1 = new File(file);
        if(!file1.exists()){
            try {
                file1.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }

    public static UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
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
