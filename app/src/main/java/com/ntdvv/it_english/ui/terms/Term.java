package com.ntdvv.it_english.ui.terms;

import android.graphics.Bitmap;

import com.ntdvv.it_english.ui.ReferenceItem;

public class Term extends ReferenceItem
{
    public Term()
    {
        super();
    }

    public Term(int id, String title, String description, Bitmap icon, String iconPath)
    {
        super(id, title, description, icon, iconPath);
    }
}
