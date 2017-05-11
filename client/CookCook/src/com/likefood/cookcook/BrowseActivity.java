package com.likefood.cookcook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class BrowseActivity extends Activity implements OnClickListener{
	private EditText _searchTxt = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse_activity);
		((Button)findViewById(R.id.searchBtn)).setOnClickListener(this);
		if(_searchTxt == null) _searchTxt = (EditText) findViewById(R.id.searchTxt);
		_searchTxt.setText(readLastSearch());
	}

	@Override
	public void onClick(View view)
	{
		if(view.getId() == R.id.searchBtn) onSearchClicked();
		else onItemClicked(view);
	}
	
	private void onSearchClicked()
	{
		String txt = _searchTxt.getText().toString();
		writeLastSearch(txt);
		if(txt == "") return;
		System.out.println("search" + txt);
		
		LinearLayout container = (LinearLayout) findViewById(R.id.container);
		
        container.removeAllViews();
		
		for(int i = 0; i < 20; ++i)
	    {
			MenuItem item = new MenuItem(this);
			item.setText("item");
			item.setCookId("id");
			item.setOnClickListener(this);
			container.addView(item);
		}
	}
	
	public void onItemClicked(View view)
	{
		MenuItem item = (MenuItem)view;
	    System.out.println(item.getCookId());	
	}
	
	private String readLastSearch()
	{
		String path = getExternalCacheDir() + "/lastSearch";
		File file = new File(path);
		if(file.exists())
		{
			try {
			    BufferedReader br = new BufferedReader(new FileReader(file));
				try {
					String o = br.readLine();
					br.close();
					return o;
				} catch (IOException e) {
					e.printStackTrace();
					return "";
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
			
		}
		else return "";
	}
	
	private void writeLastSearch(String txt)
	{
		String path = getExternalCacheDir() + "/lastSearch";
		File file = new File(path);
        try {
			FileOutputStream os = new FileOutputStream(file);
			byte[] bts = txt.getBytes();
			try {
				os.write(bts, 0, bts.length);
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	
}
