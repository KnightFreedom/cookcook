package com.likefood.cookcook;

import android.content.Context;
import android.widget.Button;

public class MenuItem extends Button 
{
    public MenuItem(Context ct)
    {
    	super(ct);
    }
    
    private String _id;
    public String getCookId()
    {
    	return _id;
    }
    
    public void setCookId(String id)
    {
    	_id = id;
    }
    
    public void setExpanded(boolean value)
    {
    	if(value)
    	{
    		super.setBackgroundColor(getSolidColor());
    	}
    	
    	
    	
    }
    
}
