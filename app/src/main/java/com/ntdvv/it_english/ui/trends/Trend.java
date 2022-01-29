package com.ntdvv.it_english.ui.trends;

import android.graphics.Bitmap;

import com.ntdvv.it_english.ui.ReferenceItem;

public class Trend extends ReferenceItem
{
    public Trend()
    {
        super();
    }

    public Trend(int id, String title, String description, Bitmap icon, String iconPath)
    {
        super(id, title, description, icon, iconPath);
    }
}
