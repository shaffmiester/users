package com.shaffersoft;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Joel on 8/1/2014.
 */
public class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private List<String> descriptions;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<String> getDescriptions(){
        return descriptions;
    }
    public void setDescriptions(List<String> descriptions){
        this.descriptions = descriptions;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
