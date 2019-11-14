package dsa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import sun.awt.image.ImageWatched;

import java.util.LinkedList;

public class User {


    //Atributes
    public String iduser;
    String name;
    String surname;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    LinkedList<Object> userobjects;


    //Constructor vacio para json
    public User(){

    }

    //Constructor
    public User(String iduser, String name, String surname) {
        this.iduser = iduser;
        this.name = name;
        this.surname = surname;
    }

    //Getters y setters


    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void addObject(Object object){
        userobjects.add(object);
    }

    public LinkedList<Object> getUserobjects(){
        return userobjects;
    }

    public void setUserobjects(LinkedList<Object> userobjects){
        this.userobjects = userobjects;

    }
}
