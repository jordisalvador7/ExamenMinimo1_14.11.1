package dsa.services;


import dsa.exceptions.UserNotFoundException;
import dsa.models.Object;
import dsa.models.User;
import dsa.main.GameManager;
import dsa.main.GameManagerImp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/user", description = "Endpoint to Students Service")
@Path("/")

public class GameService {

    private GameManager gm;

    public GameService() throws UserNotFoundException {
        this.gm = GameManagerImp.getInstance();

        if(this.gm.size() == 0){
            this.gm.addUser("001", "Jordi", "Salavdor");
            this.gm.addUser("002", "Leo", "Messi");
            this.gm.addUser("003", "Saitama", "Sensei");

            Object obj1 = new Object ("Espada", "¡No te cortes!");
            Object obj2 = new Object ("Escudo", "¡Protegete de todo!");
            Object obj3 = new Object ("Balon", "Mete muchos goles");
            Object obj4 = new Object ("Pocion", "Curate de todo");
            Object obj5 = new Object ("Katana", "Corta hasta el aire");

            this.gm.addObjectToUser("001", obj1);
            this.gm.addObjectToUser("001",obj2);
            this.gm.addObjectToUser("002",obj3);
            this.gm.addObjectToUser("001", obj4);
            this.gm.addObjectToUser("003",obj5);
        }
    }

    //Get users sorted by surname
    @GET
    @ApiOperation(value = "Get all users sorted by surname", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Succesful", response = User.class, responseContainer = "List")
    })

    @Path("/User")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(){

        List<User> users = this.gm.listUser();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build() ;
    }

    //Get user through id
    @GET
    @ApiOperation(value = "Get user by its id", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/User/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) throws UserNotFoundException {
        User u = this.gm.getUser(id);
        if (u == null) {
            return Response.status(404).build();
        }
        else return Response.status(201).entity(u).build();
    }

    //Add user
    @POST
    @ApiOperation(value = "Add a new user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/User/{iduser}/{name}/{surname}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newStudent(@PathParam("iduser") String iduser, @PathParam("name") String name, @PathParam("surname") String surname) throws UserNotFoundException {

        if(iduser == null || name == null || surname == null){
            return Response.status(500).build();
        }
        this.gm.addUser(iduser,name,surname);
        User u = this.gm.getUser(iduser);
        return Response.status(201).entity(u).build();
    }

    //Get user objects
    @GET
    @ApiOperation(value = "Get the objects of a user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Object.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/User/{id}/Objects")
    public Response getObjectsUser(@PathParam("id") String id) throws UserNotFoundException {
        User user = this.gm.getUser(id);
        if(user == null) return Response.status(404).build();
        else{
            List<Object> object = user.getObjects();
            GenericEntity<List<Object>> entity = new GenericEntity<List<Object>>(object) {};
            return Response.status(201).entity(entity).build() ;
        }
    }


    /*Add object to a user
    @PUT
    @ApiOperation(value = "Add an object to a user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = Object.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/User/{id}")
    public Response enrollStudent(@PathParam("id") String id, Object obj) {

        User u = this.gm.getUser(id);

        if (u == null) return Response.status(404).build();
        else {
            this.gm.addObjectToUser(id,obj.getIdObject(),obj.getDescription());
            return Response.status(201).entity(u).build();
        }
    }*/

    //Update user
    @PUT
    @ApiOperation(value = "Update data of a user", notes = "x")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful",response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/User")
    public Response updateUser(User user) throws UserNotFoundException {

        User user1 = this.gm.getUser(user.getIduser());
        if (user1 == null || user == null) return Response.status(404).build();
        else {
            this.gm.updateUser(user.getIduser(), user.getName(), user.getSurname());
            return Response.status(201).entity(user1).build();
        }
    }




}
