package dao;

import metier.Cars;
import metier.CarsEntity;

public class CarsService extends Service {

    @Override
    public void insertObject(Object object) throws Exception {
        Cars car = (Cars)object;

        // on construit un objet Entity
        CarsEntity carEntity = new CarsEntity();
        // on tansfère les données reçues dans l'objet Entity
        carEntity.setIdCar(car.getIdCar());
        carEntity.setLat(car.getLat());
        carEntity.setLon(car.getLon());
        carEntity.setModel(car.getModel());
        carEntity.setStatus(car.getStatus());
        super.insertObject(carEntity);
    }
}
