package dao;

import com.polyauto.entities.Bookings;
import com.polyauto.entities.BookingsEntity;

public class BookingsService extends Service {

    @Override
    public void insertObject(Object object) throws Exception {
        Bookings booking = (Bookings)object;

        // on construit un objet Entity
        BookingsEntity bookingEntity = new BookingsEntity();
        // on tansfère les données reçues dans l'objet Entity
        bookingEntity.setStatus(booking.getStatus());
        bookingEntity.setDateUp(booking.getDateUp());
        bookingEntity.setDateDown(booking.getDateDown());
        bookingEntity.setIdBooking(booking.getIdBooking());
        bookingEntity.setIdCar(booking.getIdCar());
        bookingEntity.setIdUser(booking.getIdUser());
        super.insertObject(bookingEntity);
    }

    @Override
    public void updateObject(Object object) throws Exception {
        Bookings booking = (Bookings)object;

        // on construit un objet Entity
        BookingsEntity bookingEntity = new BookingsEntity();
        // on tansfère les données reçues dans l'objet Entity
        bookingEntity.setStatus(booking.getStatus());
        bookingEntity.setDateUp(booking.getDateUp());
        bookingEntity.setDateDown(booking.getDateDown());
        bookingEntity.setIdBooking(booking.getIdBooking());
        bookingEntity.setIdCar(booking.getIdCar());
        bookingEntity.setIdUser(booking.getIdUser());
        super.updateObject(bookingEntity);
    }
}
