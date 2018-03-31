package com.example.odapro.normalduer;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The buttons
        Button mBkropp;
        Button mButseendet;
        Button mBsosialt;
        Button mBforeldre;
        Button mBpersonlighet;
        Button mBgenerelt;
        Button mBtilfeldige;
        Button loginbutton;

        mBkropp = (Button) findViewById(R.id.mBkropp);
        mBkropp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DisplayKropp.class));
            }
        });

        loginbutton = (Button) findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();
                mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

            }
        });


        //Data endata = new Data(1, "navnet", "adressen");
        //DBHandler enhandler = new DBHandler(this);

        Log.d("hei du", "better be message");
        //System.out.println(enhandler.getAllData());
        Log.d("there you are", "her er en message");
    }
}