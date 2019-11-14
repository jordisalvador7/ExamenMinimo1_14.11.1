package dsa.main;

import dsa.models.Object;
import dsa.models.User;


import java.util.HashMap;
import java.util.List;

public interface GameManager {

    //Añadir usuario

    public void addUser (String id, String name, String surname);

    //Size

    public int size();

    //Info usuario

    public User getUser(String iduser);

    //Objetos que pertenecen a un usuario

    public int getObjectsOfUser(String iduser) ;

    //Añadir objeto a usuario

    public void addObjectToUser (String iduser, Object object ) ;

    //Update user

    public void updateUser(String iduser, String name, String surname) ;

    /*Numero de objetos de un usuario

    public int numObjects(String iduser) ;*/

    //Sort users by surname

    public HashMap<String,User> orderUsers();

    public List<User> listUser();

    //Clear

    public void clear();




}
