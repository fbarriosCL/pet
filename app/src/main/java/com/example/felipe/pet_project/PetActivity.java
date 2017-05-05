package com.example.felipe.pet_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.felipe.pet_project.db.dbHelper;
import com.example.felipe.pet_project.model.Owner;
import com.example.felipe.pet_project.model.Pet;

public class PetActivity extends AppCompatActivity {

    private String[] arraySpinnerTypePet, arraySpinnerColor, arraySpinnerRace, arraySpinnerUser;
    private Spinner TypePetMethod, ColorPetMethod, RacePetMethod, UserPetMethod;
    private Button btnCreatePet, btnCleanPet;
    private EditText necklacePet;
    dbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        this.arraySpinnerTypePet = new String[] {
                "Dog", "Cat", "Pig"
        };
        this.arraySpinnerColor = new String[] {
                "Red", "Blue", "Pink"
        };
        this.arraySpinnerRace = new String[] {
                "Boxer", "Schnauzer", "Boyero"
        };
        this.arraySpinnerUser = new String[] {
                "Felipe", "Paola", "Romelio"
        };

        TypePetMethod = (Spinner) findViewById(R.id.type_pet_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinnerTypePet);
        TypePetMethod.setAdapter(adapter);

        ColorPetMethod = (Spinner) findViewById(R.id.color_spinner);
        ArrayAdapter<String> adapter_color = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinnerColor);
        ColorPetMethod.setAdapter(adapter_color);

        RacePetMethod = (Spinner) findViewById(R.id.race_spinner);
        ArrayAdapter<String> adapter_race = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinnerRace);
        RacePetMethod.setAdapter(adapter_race);

        UserPetMethod = (Spinner) findViewById(R.id.user_spinner);
        ArrayAdapter<String> adapter_user = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinnerUser);
        UserPetMethod.setAdapter(adapter_user);

        btnCreatePet = (Button) findViewById(R.id.btnCreatePet);
        btnCreatePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = dbHelper.getInstance(getApplicationContext());
                Spinner TypePetSpinner = (Spinner) findViewById(R.id.type_pet_spinner);
                String typePet = TypePetSpinner.getSelectedItem().toString();

                Spinner ColorPetSpinner = (Spinner) findViewById(R.id.color_spinner);
                String colorPet = ColorPetSpinner.getSelectedItem().toString();

                Spinner RacePetSpinner = (Spinner) findViewById(R.id.race_spinner);
                String racePet = RacePetSpinner.getSelectedItem().toString();
                necklacePet = (EditText) findViewById(R.id.necklace);
                Pet PetData = new Pet();

                PetData.type = typePet;
                PetData.color = colorPet;
                PetData.race = racePet;
                PetData.necklace = necklacePet.getText().toString();
                dbHelper.insertPet(PetData);

                Intent intent = new Intent(PetActivity.this, ShowPetActivity.class);
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
                Intent intentHome = new Intent(PetActivity.this, HomeActivity.class);
                startActivity(intentHome);
                break;
            case R.id.menuFormOwner:
                Intent intentFormOwner = new Intent(PetActivity.this, OwnerActivity.class);
                startActivity(intentFormOwner);
                break;
            case R.id.menuFormPet:
                Intent intentFormPet = new Intent(PetActivity.this, PetActivity.class);
                startActivity(intentFormPet);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
