package metier;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "bookings", schema = "polyauto", catalog = "")
public class BookingsEntity {
    private int idBooking;
    private byte status;
    private Date dateUp;
    private Date dateDown;

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
        return idBooking == that.idBooking &&
                status == that.status &&
                Objects.equals(dateUp, that.dateUp) &&
                Objects.equals(dateDown, that.dateDown);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBooking, status, dateUp, dateDown);
    }
}
