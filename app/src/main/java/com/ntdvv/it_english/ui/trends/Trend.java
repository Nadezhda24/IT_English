package com.ntdvv.it_english.ui.trends;

import android.graphics.Bitmap;

public class Trend {
    private int id;
    private String name;
    private String  description;
    private Bitmap icon;
    private String iconPath;

    public Trend(){
        this.id = -1;
        this.name = "";
        this.description = "";
    }


    public Trend(int id, String name, String  description, Bitmap icon, String iconPath){
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.iconPath = iconPath;
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

    public String getIconPath(){
        return this.iconPath;
    }

    public Bitmap getIcon(){
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

    public void setIconPath(String  iconPath){
        this.iconPath = iconPath;
    }

    public void setIcon(Bitmap icon){
        this.icon = icon;
    }

}
