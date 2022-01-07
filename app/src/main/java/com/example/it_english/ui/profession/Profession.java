package com.example.it_english.ui.profession;

public class Profession {
    private int id;
    private String name;
    private String  description;
    private String icon;

    public Profession(){
        this.id = -1;
        this.name = "";
        this.description = "";
    }

    public Profession(int id, String name, String  description, String icon ){
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public String getIcon(){
        return this.icon;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String  description){
        this.description = description;
    }

    public void setIcon(String icon){
        this.icon = icon;
    }


}
