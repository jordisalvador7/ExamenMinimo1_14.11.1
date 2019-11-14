package dsa;

import dsa.exceptions.UserNotFoundException;
import dsa.main.GameManager;
import dsa.main.GameManagerImp;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class setUP {

    final static Logger log = Logger.getLogger(setUP.class.getName());

    private GameManager gm;

    @Before
    public void setUp() throws Exception {
        this.gm = GameManagerImp.getInstance();
        this.gm.addUser("user1", "Jordi", "Salvador");


        this.gm.addUser("User1","description:: User1", 10, 3, 3);
        this.gm.addUser("User2","description:: User2", 10, 3, 3);


        Assert.assertEquals(2, this.gm.numUsers());
        Assert.assertEquals(0, this.gm.nugmikes("User1"));

        try {
            this.gm.addObject("Object101", "descripton", 25.45, "User1");
            this.gm.addObject("Object102", "descripton", 70.3, "User1");
            this.gm.addObject("Object103", "descripton", 10.2, "User1");
        }
        catch (UserNotFoundException e){
            log.error("The User doesn't exist: " +e.getMessage());
        }
        Assert.assertEquals(3, this.gm.nugmikes("User1"));

        try {
            this.gm.addObject("Object201", "descripton", 1325.45, "User2");
            this.gm.addObject("Object202", "descripton", 74430.3, "User2");
            this.gm.addObject("Object203", "descripton", 1320.2, "User2");
        }
        catch (UserNotFoundException e){
            log.error("The User doesn't exist: " +e.getMessage());
        }
        Assert.assertEquals(3, this.gm.nugmikes("User2"));
    }

    @After
    public void tearDown(){
        this.gm.clear();
    }

    @Test
    public void testAddUser() {
        this.gm.addUser("3334445ZX", "Juan", "Lopex");
        Assert.assertEquals(2, this.gm.numUsers());
    }


    @Test
    public void testAddUsers() {
        this.gm.addUser("User3","description", 10, 3, 3);
        Assert.assertEquals(3, this.gm.numUsers());
    }

    @Test
    public void testAddObject() throws Exception {

        try {
            this.gm.addObject("Object500", "descripton", 43.3, "User1");
            this.gm.addObject("Object600", "descripton", 45.3, "User1");
        }
        catch (UserNotFoundException e){
            log.error("The User doesn't exist: " +e.getMessage());
        }

        Assert.assertEquals(5, this.gm.nugmikes("User1"));


    }

    @Test(expected = UserFullException.class)
    public void testAddObjectsAndUserFull() throws Exception {
        this.gm.addUser("User3","description", 2, 3, 3);
        Assert.assertEquals(3, this.gm.numUsers());
        Assert.assertEquals(0, this.gm.nugmikes("User3"));

        this.gm.addObject("Object1", "descripton", 25.3, "User3");
        this.gm.addObject("Object2", "descripton", 70.2, "User3");
        this.gm.addObject("Object3", "descripton", 10.4, "User3");

    }


    @Test(expected = UserNotFoundException.class)
    public void testAddObjectsAndUserNotFound() throws Exception {
        this.gm.addObject("Object1", "descripton", 55.4,"UserXXXXX");
    }



    @Test
    public void testObjectsByUser() throws Exception {

        try {
            List<Object> Objects = this.gm.ObjectsByUserOrderByKms("User1");
            Assert.assertEquals("Object103", Objects.get(0).getObjectId());
            Assert.assertEquals(10, Objects.get(0).getKms(),1);

            Assert.assertEquals("Object101", Objects.get(1).getObjectId());
            Assert.assertEquals(25, Objects.get(1).getKms(),1);

            Assert.assertEquals("Object102", Objects.get(2).getObjectId());
            Assert.assertEquals(70, Objects.get(2).getKms(),1);
        }
        catch(UserNotFoundException e){
            log.error("The User doesn't exist: " +e.getMessage());
        }
    }

    @Test
    public void testGetObjects() throws Exception {

        try {
            Object b1 = this.gm.getObject("User1", "user1");
            Assert.assertEquals("Object101", b1.getObjectId());
            Assert.assertEquals(2, this.gm.nugmikes("User1"));

            Object b2 = this.gm.getObject("User2", "user1");
            Assert.assertEquals("Object201", b2.getObjectId());
            Assert.assertEquals(2, this.gm.nugmikes("User1"));

            List<Object> Objects = this.gm.ObjectsByUser("user1");

            Assert.assertEquals("Object101", Objects.get(0).getObjectId());
            Assert.assertEquals("Object201", Objects.get(1).getObjectId());
        }
        catch(UserNotFoundException e){
            log.error("The user doesn't exist: " +e.getMessage());
        }
        catch(UserNotFoundException e){
            log.error("The User doesn't exist: " +e.getMessage());
        }
    }


}
