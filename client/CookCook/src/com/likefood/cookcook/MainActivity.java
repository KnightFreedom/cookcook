package com.likefood.cookcook;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.loginBtn)).setOnClickListener(this);
        ((Button)findViewById(R.id.signupBtn)).setOnClickListener(this);
        ((Button)findViewById(R.id.guestBtn)).setOnClickListener(this);
    }
    
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private View.OnClickListener _onLoginClicked;
    private View.OnClickListener _onSignUpClicked;
    
	@Override
	public void onClick(View view) {
		if(view.getId()==R.id.loginBtn) onLoginClicked();
		else if(view.getId() == R.id.signupBtn) onSignupClicked();
		else if(view.getId() == R.id.guestBtn) onGuestClicked();
	}
	
    private void onLoginClicked()
    {
    	System.out.println("onLoginClicked");
    }

    private void onSignupClicked()
    {
    	System.out.println("onSignupClicked");
    }
    
    private void onGuestClicked()
    {
    	System.out.println("onGuestClicked");
    	Intent in = new Intent();
    	in.setClassName(getApplicationContext(), "com.likefood.cookcook.BrowseActivity");
    	startActivity(in);
    }
    
}
