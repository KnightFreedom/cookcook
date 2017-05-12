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
import android.util.DisplayMetrics;
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
	}
	
    @Override 
    protected void onStart()
    {
    	super.onStart();
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
			item.setText("item" + i);
			item.setCookId("id" + i);
			item.setOnClickListener(this);
			container.addView(item);
		}
	}
	
	
	//用来详细显示做菜的过程，放在被点击的item之下.
	//如果用户再点击另一个item，那么删除当前textview，重新在被点击的item下面再生成textview
	private TextView _expandedView = null;
	private MenuItem _lastClicked = null;
	public void onItemClicked(View view)
	{
		MenuItem item = (MenuItem)view;
		LinearLayout container = (LinearLayout) findViewById(R.id.container);
	
		if(_expandedView == null) expandItem(item);//view not expanded
		else//view expanded
		{
			//1先关闭
			container.removeView(_expandedView);
			_expandedView = null;
			//2如果上次点击的和这次点击的是同一个，那么上述关闭之后不再打开
			if(_lastClicked == item) ;
			//3如果上次点击的和这次点击的不是同一个item，那么关闭之后再打开
			else expandItem(item);
		}
			
	}
	
	private void expandItem(MenuItem item)
	{
		LinearLayout container = (LinearLayout) findViewById(R.id.container);
		int count = container.getChildCount();
		int idx = 0;
		for(int i = 0; i < count; ++i)
		{
			if(item == (MenuItem)container.getChildAt(i))
			{
				idx = i;
				break;
			}
		}
		_expandedView = new TextView(this);
		_expandedView.setText("this is from server");
		int[] location = new int[2];
		item.getLocationOnScreen(location);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		if(location[1] < dm.heightPixels / 2)
			container.addView(_expandedView, idx + 1);
		else 
			container.addView(_expandedView, idx);
		
		//send request to httpserver??
		_lastClicked = item;
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
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
	}
	
	
}
