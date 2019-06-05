package metier;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "bookings", schema = "polyauto", catalog = "")
public class BookingsEntity {
    private int idBooking;
    private byte status;
    private Date dateUp;
    private Date dateDown;
    private int idCar;
    private int idUser;

    @Id
    @Column(name = "idBooking")
    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    @Basic
    @Column(name = "status")
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "dateUp")
    public Date getDateUp() {
        return dateUp;
    }

    public void setDateUp(Date dateUp) {
        this.dateUp = dateUp;
    }

    @Basic
    @Column(name = "dateDown")
    public Date getDateDown() {
        return dateDown;
    }

    public void setDateDown(Date dateDown) {
        this.dateDown = dateDown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingsEntity that = (BookingsEntity) o;

        if (idBooking != that.idBooking) return false;
        if (status != that.status) return false;
        if (dateUp != null ? !dateUp.equals(that.dateUp) : that.dateUp != null) return false;
        if (dateDown != null ? !dateDown.equals(that.dateDown) : that.dateDown != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBooking;
        result = 31 * result + (int) status;
        result = 31 * result + (dateUp != null ? dateUp.hashCode() : 0);
        result = 31 * result + (dateDown != null ? dateDown.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "idCar")
    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    @Basic
    @Column(name = "idUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
