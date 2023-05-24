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
    private final List<Hotel> hotels;
    private static HotelRepository hotelRepository;
    private final static String path="hotel.txt";

    private HotelRepository(List<Hotel> list) {
     this.hotels=list;
    }

    @Override
    protected List<Hotel> getList() {
        return hotels;
    }

    @Override
    protected String getPath() {
        return path;
    }

    public static HotelRepository getInstance() {
        if (hotelRepository == null) {
            try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
               List<Hotel> hotelList =(List<Hotel>) objectInputStream.readObject();
               return new HotelRepository(hotelList);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return hotelRepository;
    }

}
