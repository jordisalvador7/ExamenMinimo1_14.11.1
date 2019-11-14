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
        User theUser = new User (idUser, name, surname);
        this.users.put(theUser.getIduser(),theUser);
        log.info("El usuario ha sido añadido con los siguientes datos: " + idUser + " " + name + " " + surname);

    }

    @Override
    public int size() {
        int usernum=this.users.size();
        log.info("El numero de usuarios es: " + usernum);
        return usernum;
    }

    //Info del usuario
    public User getUser( String iduser) throws UserNotFoundException{
        User user = users.get(iduser);
        if(user != null) {
            return users.get(iduser);
        }
        else{
            log.error("User not found");
            throw new UserNotFoundException();
        }
    }
    //Get objects of a user
    public int getObjectsOfUser(String userId) throws UserNotFoundException {

        User user = users.get(userId);

        if(user != null) {
            log.info(user.getObjects());
            return user.getObjects().size();
        }
        else {
            log.error("User not found");
            throw new UserNotFoundException();
        }
    }

    @Override
    public void addObjectToUser(String iduser, Object object) throws UserNotFoundException {
        User user =users.get(iduser);
        if (user != null) {
            user.addObject(object);
            log.info("El usuario " + user + " tiene los siguientes objetos: " + user.getObjects());
        }
        else{
            log.error("User not found");
            throw new UserNotFoundException();
        }


    }

    //Update user
    public void updateUser(String iduser, String name, String surname) throws UserNotFoundException{

        User user = this.users.get(iduser);
        if (user != null) {

            //Borramos el usuario
            this.users.remove(iduser);

            //Le damos los nuevos valores
            user.setName(name);
            user.setSurname(surname);
            this.users.put(iduser, user);
            log.info("El usuario ha sido modificado, los nuevos datos son: " + user.getIduser() + " " + user.getName() + " " + user.getSurname());
        }

        else {
            log.error("El usuario no existe.");
            throw new UserNotFoundException();

        }
    }

    public HashMap<String, User> orderUsers() {
        return this.users;
    }

    public List<User> listUser() {
        List<User> userList = new LinkedList<User>(this.orderUsers().values());
        Collections.sort(userList);
        log.info(userList);
        return userList;
    }

    public void clear() {
        instance = null;
        this.objectsUser.clear();
        this.users.clear();
        users.clear();
    }
}
