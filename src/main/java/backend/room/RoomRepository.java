package backend.room;

import backend.common.BaseRepository;
import backend.hotel.Hotel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomRepository extends BaseRepository<Room, UUID>
{
    private final List<Room> rooms = new ArrayList<>();
    private static RoomRepository roomRepository;
    private static String file="src/backend/room/room.txt";

    private RoomRepository()
    {
    }


    @Override
    protected List<Room> getList()
    {
        return rooms;
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
