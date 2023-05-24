package backend.reservation;

import backend.common.BaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservationRepository extends BaseRepository<Reservation, UUID>
{

    private final List<Reservation> reservations = new ArrayList<>();
    private static ReservationRepository repository;
    private static String path="reservation.txt";

    private ReservationRepository()
    {
    }


    @Override
    protected List<Reservation> getList()
    {
        return reservations;
    }

    @Override
    protected String getPath() {
        return path;
    }

    public static ReservationRepository getInstance()
    {
        if( repository == null )
        {
            repository = new ReservationRepository();
        }
        return repository;
    }

    public List<Reservation> getReservationsByHotelId( UUID hotelId )
    {
        List<Reservation> reservationList = new ArrayList<>();
        for( Reservation reservation : reservations )
        {
            if( reservation.getHotelId().equals( hotelId ) )
            {
                reservationList.add( reservation );
            }
        }
        return reservationList;
    }

    public List<Reservation> getReservationsByRoomId( UUID roomId )
    {
        List<Reservation> reservationList = new ArrayList<>();
        for( Reservation reservation : reservations )
        {
            if( reservation.getRoomId().equals( roomId ) )
            {
                reservationList.add( reservation );
            }
        }
        return reservationList;
    }
}
