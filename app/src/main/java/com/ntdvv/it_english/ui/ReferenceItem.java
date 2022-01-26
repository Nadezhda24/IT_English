package com.ntdvv.it_english.ui;

import android.graphics.Bitmap;

public class ReferenceItem
{
    public ReferenceItem(){
        this.id = -1;
        this.name = "";
        this.description = "";
        this.icon = null;
        this.iconPath = "";
    }

    public ReferenceItem(int id, String name, String description, Bitmap icon, String iconPath){
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.iconPath = iconPath;
    }


    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }


    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }


    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }


    public Bitmap getIcon(){
        return this.icon;
    }

    public void setIcon(Bitmap icon){
        this.icon = icon;
    }


    public String getIconPath(){
        return this.iconPath;
    }

    public void setIconPath(String  iconPath){
        this.iconPath = iconPath;
    }

    /**
     * Идентификатор записи в справочнике
     */
    private int id;
    /**
     * Текст заголовка записи
     */
    private String name;
    /**
     * Текста записи
     */
    private String description;
    /**
     * Иконка, отображаемая рядом с заголовком записи
     */
    private Bitmap icon;
    /**
     * Путь к иконке
     */
    private String iconPath;
}
