package com.ntdvv.it_english.ui;

import android.graphics.Bitmap;

public class ReferenceItem
{
    public ReferenceItem(){
        this.id = -1;
        this.title = "";
        this.description = "";
        this.icon = null;
        this.iconPath = "";
    }

    public ReferenceItem(int id, String title, String description, Bitmap icon, String iconPath){
        this.id = id;
        this.title = title;
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


    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
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
    private String title;
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
