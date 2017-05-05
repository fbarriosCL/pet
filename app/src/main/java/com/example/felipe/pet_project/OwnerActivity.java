package com.example.felipe.pet_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OwnerActivity extends AppCompatActivity {
    private Button btnCreateOwner, btnCleanOwner;
    private EditText etName, etAddress, etRut, etTelephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        btnCreateOwner = (Button) findViewById(R.id.btnCreateOwner);
        btnCreateOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id) {
            case R.id.menuHome:
                Intent intentHome = new Intent(OwnerActivity.this, HomeActivity.class);
                startActivity(intentHome);
                break;
            case R.id.menuFormOwner:
                Intent intentFormOwner = new Intent(OwnerActivity.this, OwnerActivity.class);
                startActivity(intentFormOwner);
                break;
            case R.id.menuFormPet:
                Intent intentFormPet = new Intent(OwnerActivity.this, PetActivity.class);
                startActivity(intentFormPet);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
