package dsa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

public class Object {

    //Atributes
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private int id;

    String idObject;
    String description;

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
}
