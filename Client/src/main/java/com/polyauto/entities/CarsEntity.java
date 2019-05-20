package com.polyauto.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = "CarsEntity.findAvailableCars",
        query = "select c from CarsEntity c where c.status = 1")
@Table(name = "cars", schema = "polyauto", catalog = "")
public class CarsEntity {
    private int idCar;
    private int model;
    private double lat;
    private double lon;
    private byte status;

    @Id
    @Column(name = "idCar")
    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    @Basic
    @Column(name = "model")
    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    @Basic
    @Column(name = "lat")
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Basic
    @Column(name = "lon")
    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Basic
    @Column(name = "status")
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarsEntity that = (CarsEntity) o;
        return idCar == that.idCar &&
                model == that.model &&
                Double.compare(that.lat, lat) == 0 &&
                Double.compare(that.lon, lon) == 0 &&
                status == that.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCar, model, lat, lon, status);
    }
}
