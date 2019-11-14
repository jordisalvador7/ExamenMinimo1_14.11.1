package dsa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

public class Object implements Comparable <Object>{

    //Atributes
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private int id;

    public String idObject;
    public String description;

    //Constructor vacío para Json
    public Object(){

    }

    //Constructor
    public Object(String idObject, String description) {
        this.idObject = idObject;
        this.description = description;
    }

    //Getters y setters

    public String getIdObject() {
        return idObject;
    }

    public void setIdObject(String idObject) {
        this.idObject = idObject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public String getObjectid() {
        return this.idObject;
    }

    @Override
    public int compareTo(Object object) {
        return 0;
    }

    public String toString(){
        return this.idObject;
    }
}
