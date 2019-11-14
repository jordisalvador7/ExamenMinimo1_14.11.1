package dsa.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

public class User implements Comparable<User>{

    //Atributes
    public String iduser;
    String name;
    String surname;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    List<Object> objects;


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

    public List<Object> getObjects(){
        return objects;
    }

    public void setObjects(List<Object> objects){
        this.objects = objects;

    }

    //Add an object
    public void addObject (Object object){
        this.objects.add(object);
    }

    //Get number of objects owned by user

    public int getNumObjects() {
        return this.objects.size();
    }

    @Override
    public int compareTo(User user) {
        return this.surname.compareTo(user.surname);
    }

    public String toString(){
        return this.name;
    }
}
