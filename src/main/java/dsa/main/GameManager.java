package dsa.main;

import dsa.exceptions.UserNotFoundException;
import dsa.models.Object;

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




}
