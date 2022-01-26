package com.ntdvv.it_english.ui.languages;

import android.graphics.Bitmap;
import com.ntdvv.it_english.ui.ReferenceItem;

public class Language extends ReferenceItem

{
    public Language()
    {
        super();
    }

    public Language(int id, String name, String description, Bitmap icon, String iconPath)
    {
        super(id, name, description, icon, iconPath);
    }
}
