package com.example.it_english.ui.profession;

public class Profession {
    private int id;
    private String name;
    private String  description;
    private int icon;

    public Profession(){
        this.id = -1;
        this.name = "";
        this.description = "";
        this.icon = -1;
    }

    public Profession(int id, String name, String  description, int icon ){
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

    public int getIcon(){
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

    public void setIcon(int icon){
        this.icon = icon;
    }


}
