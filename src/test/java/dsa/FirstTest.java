package dsa;

import dsa.exceptions.UserNotFoundException;
import dsa.main.GameManager;
import dsa.main.GameManagerImp;
import dsa.models.Object;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;

public class FirstTest {
    GameManager gm;

    final static Logger log = Logger.getLogger(GameManagerImp.class.getName());

    @Before
    public void SetUp() throws UserNotFoundException {
        gm = GameManagerImp.getInstance();

        this.gm.addUser("010","Ansu","Fati");
        this.gm.addUser("011", "JC", "Todibo");
        this.gm.addUser("012", "Moussa", "Wague");

        Object obj1 = new Object ("Espada", "¡No te cortes!");
        Object obj2 = new Object ("Escudo", "¡Protegete de todo!");
        Object obj3 = new Object ("Balon", "Mete muchos goles");
        Object obj4 = new Object ("Pocion", "Curate de todo");
        Object obj5 = new Object ("Katana", "Corta hasta el aire");

        this.gm.addObjectToUser("010", obj1);
        this.gm.addObjectToUser("010", obj2);
        this.gm.addObjectToUser("011", obj3);
        this.gm.addObjectToUser("010", obj4);
        this.gm.addObjectToUser("012", obj5);
        this.gm.size();
    }

    @Test //Adding users + checking the number of users
    public void testAddUsers() {

        this.gm.addUser("013", "Valverde", "Dimision");

        Assert.assertEquals(4, this.gm.size());
    }

    @Test
    public void testAddObjects() throws UserNotFoundException {

        Object obj7 = new Object ("Arco", "¿Hasta donde puedes llegar?");
        Object obj8 = new Object ("Flechas", "Sin ellas no te servirá el arco");
        Object obj9 = new Object ("Mochila", "Para que puedas llevar más objetos");

        this.gm.addObjectToUser("010",obj7);
        this.gm.addObjectToUser("011", obj8);
        this.gm.addObjectToUser("012", obj9);

        Assert.assertEquals(4, this.gm.getObjectsOfUser("010"));
        Assert.assertEquals(2,this.gm.getObjectsOfUser("011"));
        Assert.assertEquals(2,this.gm.getObjectsOfUser("012"));


    }

    @Test
    public void testModifyUser() throws UserNotFoundException {

        this.gm.updateUser("011","Todibo","GOAT");
        Assert.assertEquals("GOAT",this.gm.getUser("011").getSurname());
    }

    @Test
    public void testModifyUserException() throws UserNotFoundException {

        try{

            this.gm.updateUser("015","Todibo","GOAT");
        }

        catch (UserNotFoundException e){
            log.error("Error: " + e.getMessage());
        }


    }


    @After
    public void tearDown(){
        this.gm.clear();
    }
}

