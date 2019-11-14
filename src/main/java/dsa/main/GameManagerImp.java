package dsa.main;

import dsa.exceptions.UserNotFoundException;
import dsa.models.Object;
import dsa.models.User;
import org.apache.log4j.Logger;

import java.util.*;

public class GameManagerImp implements GameManager {

    //Log4j properties
    final static Logger log = Logger.getLogger(GameManagerImp.class.getName());


    //Implementacion fachada usando Singleton
    private static GameManager instance;


    private int numUsers;
    private LinkedList<Object> objectsUser;
    private HashMap<String, User> users;

    private GameManagerImp(){
        numUsers = 0;
        objectsUser = new LinkedList<>();
        users = new HashMap<>();
    }

    public static GameManager getInstance(){
        if(instance==null){
            instance = new GameManagerImp();
        }
        return instance;
    }

    //Add a new User
    public void addUser(String idUser, String name, String surname){
        this.users.put(idUser, new User(idUser, name, surname));
    }


    //Get objects of a user
    public LinkedList<Object> objectsOfUser(String userId) throws UserNotFoundException {
        LinkedList<Object> objects;

        //We want to find the given user
        User theUser = this.users.get(userId);

        if(theUser!=null){
            log.info("User: " + theUser);
            objects = theUser.getObjects();
        }
        else{
            log.error("The user doesn't exist");
            throw new UserNotFoundException();
        }
        log.info("Objects owned by the user: " + objects);
        return objects;
    }

    //Number of users
    public int numUsers(){
        log.info("Number of users: " + this.users.size());
        return this.users.size();
    }

    //Add object to user
    public void addObject (String idObject, String description, String iduser) throws UserNotFoundException{
        User user = null;
        Object object;
        for(int i = 0; i<this.numUsers; i++) {
            if(iduser.equals(this.users.get(iduser))){
                user = this.users.get(iduser);
            }
        }

        log.info("User: " + user);

        if(user != null) {
            LinkedList<Object> objects = user.getObjects();
            user.addObject(new Object(idObject, description));
        }
        else {
            log.error("The user doesn't exist");
            throw new UserNotFoundException();
        }
    }

    //Number of objects of a user
    public int numObjects(String iduser) throws UserNotFoundException{
        //We get the user that has this iduser
        User user = null;
        int numObjects;
        for(int i = 0; i<this.numUsers; i++) {
            if(iduser.equals(this.users.get(iduser))){
                user = this.users.get(iduser);
            }
        }

        log.info("idUser of this User: " + user.iduser);

        if (user != null){
            numObjects = user.getNumObjects();
            log.info("Number of bikes of this station: " + numObjects);
        }
        else{
            log.error("The user doesn't exist");
            throw new UserNotFoundException();
        }

        return numObjects;
    }

    //Update user
    public User updateUser(User user) throws UserNotFoundException {
        User theUser = this.users.get(user.getIduser());
        if (user!=null) {
            log.info(user + "is going to be updated.");

            theUser.setName(user.getName());
            theUser.setSurname(user.getSurname());


            log.info(theUser + "has been updated.");
        }
        else {
            log.error("The user doesn't exist.");
            throw new UserNotFoundException();
        }

        return theUser;
    }

    //Consultar usuario
    public User consultUser (User user) throws UserNotFoundException{
        User theUser = this.users.get(user.getIduser());
        String name;
        String surname;
        String iduser;

        if (user!= null){
            log.info("Información del usuario " + theUser);

            iduser = theUser.getIduser();
            name = theUser.getName();
            surname = theUser.getSurname();

            log.info("El id del usuario es: " + iduser + ", el nombre del usuario es: " + name + " y el apellido del usuario es: " + surname);
        }

        else {
            log.error("El usuario no existe.");
            throw new UserNotFoundException();
        }

        return theUser;
    }




}
