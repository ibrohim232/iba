package backend.hotel;

import backend.common.BaseRepository;
import backend.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HotelRepository extends BaseRepository<Hotel, UUID>
{
    private final List<Hotel> hotels = new ArrayList<>();
    private static HotelRepository hotelRepository;

    private HotelRepository()
    {
    }

    @Override
    protected List<Hotel> getList()
    {
        return hotels;
    }

    public static HotelRepository getInstance()
    {
        if( hotelRepository == null )
        {
            hotelRepository = new HotelRepository();
        }
        return hotelRepository;
    }

}
