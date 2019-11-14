package dsa.main;

import dsa.exceptions.UserNotFoundException;
import dsa.models.Object;
import dsa.models.User;

import java.util.LinkedList;

public interface GameManager {

    /**Añadir usuario
     *
     * @param id
     * @param name
     * @param surname
     */
    public void addUser (String id, String name, String surname);

    /**Objetos que pertenecen a un usuario
     *
     * @param iduser
     * @return
     * @throws UserNotFoundException
     */
    public LinkedList<Object> objectsOfUser(String iduser) throws UserNotFoundException;

    /**Numero de usuarios
     *
     * @return
     */
    public int numUsers();

    /**Añadir objeto a usuario
     *
     * @param idObject
     * @param description
     * @param iduser
     * @throws UserNotFoundException
     */
    public void addObject (String idObject, String description, String iduser) throws UserNotFoundException;

    /**Numero de objetos de un usuario
     *
     * @param iduser
     * @return number of objects of a user
     * @throws UserNotFoundException
     */
    public int numObjects(String iduser) throws UserNotFoundException;

    /**Update user
     *
      * @param p
     * @return
     */
    public User updateUser(User p) throws UserNotFoundException;




}
