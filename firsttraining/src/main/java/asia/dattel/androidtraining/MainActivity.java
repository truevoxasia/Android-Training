package asia.dattel.androidtraining;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Calendar myCalendar = Calendar.getInstance();
    EditText datestart;
    EditText dateend;
    EditText project_name;
    EditText project_code;
    EditText version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        project_name = (EditText)findViewById(R.id.editText_project_name);
        project_code = (EditText)findViewById(R.id.editText_code);
        datestart = (EditText)findViewById(R.id.editText_start_date);
        dateend = (EditText)findViewById(R.id.editText_end_date);
        version = (EditText)findViewById(R.id.editText_version);

        Button save = (Button)findViewById(R.id.button_save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Succes Add Data", Toast.LENGTH_SHORT).show();

                String P_name = project_name.getText().toString();
                String date_s = datestart.getText().toString();
                String date_e = dateend.getText().toString();
                String code = project_code.getText().toString();
                String proj_vers = version.getText().toString();
                Integer proj_version = Integer.parseInt(proj_vers);

                dialog(P_name,date_s,code,date_e,proj_version);
            }
        });



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatedatestart();
            }

        };


        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatedateend();
            }

        };
        datestart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(datestart.getWindowToken(), 0);
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dateend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(datestart.getWindowToken(), 0);
                new DatePickerDialog(MainActivity.this, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    private void dialog(String project_name,String Date_s,String code, String Date_e, int version_proj) {


        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Data Saved \n\nProject Name : " + project_name + "\nCode : "+ code+ "\nDate Start : " + Date_s
                + "\nDate End : " + Date_e+ "\nVersion : " + version_proj);

        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });



        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    private void updatedatestart() {

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        datestart.setText(sdf.format(myCalendar.getTime()));
    }

    private void updatedateend() {

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateend.setText(sdf.format(myCalendar.getTime()));
    }


}
