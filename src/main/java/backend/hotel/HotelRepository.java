package backend.hotel;

import backend.common.BaseRepository;
import backend.room.Room;
import backend.user.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HotelRepository extends BaseRepository<Hotel, UUID> {
    private static List<Hotel> hotels;
    private static HotelRepository hotelRepository;
    private static String file= "src/backend/hotel/hotel.txt";

    private HotelRepository() {

    }

    @Override
    protected List<Hotel> getList() {
        return hotels;
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

    public static HotelRepository getInstance() {
        if (hotelRepository == null) {
            hotelRepository = new HotelRepository();
        }
        return hotelRepository;
    }

}
