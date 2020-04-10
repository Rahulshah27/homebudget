package com.example.budgetmanagementdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editAq, editDes, editPrice, choseDate;
    Button saveButton;
    DatePickerDialog datePickerDialog;
    Button show;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.data, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.show:
                Intent intent1 = new Intent(this, ShowDataAct.class);
                startActivity(intent1);

            case R.id.delete:
                Intent intent2 = new Intent(this, DeleteDataAct.class);
                startActivity(intent2);

            case R.id.update:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;

            default:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAq = findViewById(R.id.edit_aq);
        editPrice = findViewById(R.id.edit_price);
        editDes = findViewById(R.id.edit_place);
        choseDate = findViewById(R.id.chose_date);

        saveButton = findViewById(R.id.add_item);

        choseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                choseDate.setText(dayOfMonth + "/" +
                                        (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                String date = sdf.format(new Date());

                MyDatabase database = new MyDatabase(getApplicationContext());

                database.insertValues(editAq.getText().toString(),
                        editDes.getText().toString(),
                        Integer.parseInt(editPrice.getText().toString()),
                        choseDate.getText().toString());

               //Toast.makeText(MainActivity.this, "saved successfully, check history for more", Toast.LENGTH_LONG).show();
            }
        });

    }

}
