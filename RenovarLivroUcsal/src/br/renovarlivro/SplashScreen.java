package br.renovarlivro;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
 
public class SplashScreen extends Activity{
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Biblioteca UCSAL");
        setContentView(R.layout.splashscreen);
        new Timer().schedule(new TimerTask(){
        	public void run() {
                finish();
                Intent intent = new Intent();
                intent.setClass(SplashScreen.this, MainActivity.class);
                startActivity(intent);
            }
        }, 6000);  
    }
}
