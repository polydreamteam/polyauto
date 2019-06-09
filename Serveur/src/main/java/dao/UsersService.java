package dao;

import meserreurs.MonException;
import com.polyauto.entities.Users;
import com.polyauto.entities.UsersEntity;

public class UsersService extends Service {

    @Override
    public void insertObject(Object object) throws Exception {
        Users user = (Users)object;

        // on construit un objet Entity
        UsersEntity userEntity = new UsersEntity();
        userEntity.setFirstname(user.getFirstName());
        userEntity.setLastname(user.getLastName());
        userEntity.setIdUser(user.getIdUser());
        userEntity.setLogin(user.getLogin());
        userEntity.setPassword(user.getPassword());
        userEntity.setNote(user.getNote());
        super.insertObject(userEntity);
    }

    @Override
    public void updateObject(Object object) throws MonException, Exception {
        super.updateObject(object);
    }
}
