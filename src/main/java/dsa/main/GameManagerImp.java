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
    private final char u;

    private int numUsers;
    private User arrayUsers[];
    private LinkedList<Object> objectsUser;
    private HashMap<String, User> users;

    private GameManagerImp(){
        numUsers = 0;
        arrayUsers = new User[u];
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
            objects = theUser.getUserobjects();
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
            if(iduser.equals(this.arrayUsers[i].iduser)){
                user = this.arrayUsers[i];
            }
        }

        log.info("User: " + user);

        if(user != null) {
            LinkedList<Object> objects = user.getUserobjects();
            user.addObject(new Object(idObject, description));
        }
        else {
            log.error("The user doesn't exist");
            throw new UserNotFoundException();
        }
    }




}
