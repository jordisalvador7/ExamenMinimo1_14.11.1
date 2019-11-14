package dsa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.LinkedList;

public class User {

    //Atributes
    public String iduser;
    String name;
    String surname;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    LinkedList<Object> objects;


    //Constructor vacio para json
    public User(){

    }

    //Constructor
    public User(String iduser, String name, String surname) {
        this.iduser = iduser;
        this.name = name;
        this.surname = surname;
        this.objects = new LinkedList<>();
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

    public LinkedList<Object> getObjects(){
        return objects;
    }

    public void setObjects(LinkedList<Object> objects){
        this.objects = objects;

    }

    //Add an object
    public void addObject (Object object){
        objects.add(object);
    }

    //Get number of objects owned by user
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public int getNumObjects() {
        return this.objects.size();
    }

}
