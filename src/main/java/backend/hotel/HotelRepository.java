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
    private static String FILE_NAME = "hotel.txt";

    private HotelRepository() {

    }

    @Override
    protected String getFileName() {
        return FILE_NAME;
    }

    @Override
    protected List<Hotel> getList() {
        return hotels;
    }

    public static HotelRepository getInstance() {
        if (hotelRepository == null) {
            hotelRepository = new HotelRepository();
        }
        return hotelRepository;
    }

}
