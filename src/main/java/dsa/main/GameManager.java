package dsa.main;

import dsa.models.Object;
import dsa.models.User;

import dsa.exceptions.UserNotFoundException;


import java.util.HashMap;
import java.util.List;

public interface GameManager {

    public void addUser (String id, String name, String surname);//Añadir usuario

    public int size();//Number of users

    public User getUser(String iduser) throws UserNotFoundException;//Info usuario

    public int getObjectsOfUser(String iduser) throws UserNotFoundException ;//Objetos que pertenecen a un usuario

    public void addObjectToUser (String iduser, Object object ) throws UserNotFoundException;//Añadir objeto a usuario

    public void updateUser(String iduser, String name, String surname) throws UserNotFoundException;//Update user

    public HashMap<String,User> orderUsers();//Sort users by surname

    public List<User> listUser();

    public void clear();//Clear


}
