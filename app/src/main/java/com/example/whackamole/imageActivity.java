package com.example.whackamole;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class imageActivity extends AppCompatActivity {
    private RadioButton moleSelect;
    private RadioButton catSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_view);

        moleSelect = findViewById(R.id.moleSelect);
        catSelect = findViewById(R.id.catSelect);

        Intent i = getIntent();
        int imageNum = i.getIntExtra("IMAGE",0);
        if(imageNum == 0){
            moleSelect.setChecked(true);
        }else if(imageNum == 1){
            catSelect.setChecked(true);
        }
    }

    @Override
    public void onBackPressed(){
        int imageNum;
        if(moleSelect.isChecked()){
            imageNum = 0;
        }else{
            imageNum = 1;
        }
        Intent i = new Intent();
        i.putExtra("IMAGE", imageNum);
        setResult(RESULT_OK, i);
        finish();
    }
}
