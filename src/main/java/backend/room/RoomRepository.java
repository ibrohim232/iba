package backend.room;

import backend.common.BaseRepository;
import backend.hotel.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomRepository extends BaseRepository<Room, UUID>
{
    private final List<Room> rooms = new ArrayList<>();
    private static RoomRepository roomRepository;

    private RoomRepository()
    {
    }

    @Override
    protected List<Room> getList()
    {
        return rooms;
    }

    public static RoomRepository getInstance()
    {
        if( roomRepository == null )
        {
            roomRepository = new RoomRepository();
        }
        return roomRepository;
    }

    public List<Room> findRoomsByHotelId( UUID hotelId )
    {
        List<Room> roomsByHotelId = new ArrayList<>();
        for( Room room : rooms )
        {
            if( room.getHotelId().equals( hotelId ) )
            {
                roomsByHotelId.add( room );
            }
        }

        return roomsByHotelId;
    }

}
