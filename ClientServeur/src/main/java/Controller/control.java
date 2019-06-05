package Controller;

import dao.Service;
import metier.BookingsEntity;
import metiers.Bookings;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.ServeurService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Calendar;
import java.sql.Date;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class control {

    @RequestMapping(value="/book", method = POST)
    protected void bookCar(HttpServletRequest request, HttpServletResponse response) {
        int id_User = Integer.parseInt(request.getParameter("idUser"));
        int idCar = Integer.parseInt(request.getParameter("idCar"));
        Bookings book = new Bookings();
        book.setIdCar(idCar);
        book.setIdUser(id_User);
        book.setDateDown(new Date(Calendar.getInstance().getTimeInMillis()));
        book.setStatus((byte) 0);
        ServeurService serv = new ServeurService();
        try {
            serv.insert(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/terminateBook", method = POST)
    protected void terminateBook(HttpServletRequest request, HttpServletResponse response) {
        int idBook = Integer.parseInt(request.getParameter("udBook"));
        Service service = new Service();
        try {
            BookingsEntity bookE = service.bookingById(idBook);
            Bookings book = new Bookings();
            book.setIdBooking(bookE.getIdBooking());
            book.setIdUser(bookE.getIdUser());
            book.setIdCar(bookE.getIdCar());
            book.setDateDown(bookE.getDateDown());
            book.setDateUp(new Date(Calendar.getInstance().getTimeInMillis()));
            book.setStatus((byte) 1);

            ServeurService serv = new ServeurService();
            serv.update(book);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
