package com.example.pk.minitrello.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;import com.example.pk.minitrello.R;import java.lang.Override;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        initComponent();
    }

    private void initComponent()
    {
        ImageView bg = (ImageView) findViewById(R.id.intro_image);
        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this, ShowBoardScreen.class);
                startActivity(intent);
            }
        });


    }
}
