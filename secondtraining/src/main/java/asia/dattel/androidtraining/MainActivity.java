package asia.dattel.androidtraining;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Calendar startDateCalendar = Calendar.getInstance();
    private Calendar endDateCalendar = Calendar.getInstance();

    private EditText projectEditText;
    private EditText codeEditText;
    private EditText startDateEditText;
    private EditText endDateEditText;
    private EditText versionEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();
        setupDatePickerInput();
        setButtonInteraction();
    }

    private void setButtonInteraction() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFill()) {
                    return;
                }
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage(
                                String.format(
                                        Locale.getDefault(),
                                        "Project Name : %s\n" +
                                                "Code : %s\n" +
                                                "Start Date : %s\n" +
                                                "End Date : %s\n" +
                                                "Version : %s",
                                        projectEditText.getText().toString(),
                                        codeEditText.getText().toString(),
                                        startDateEditText.getText().toString(),
                                        endDateEditText.getText().toString(),
                                        versionEditText.getText().toString()
                                )
                        )
                        .setPositiveButton(R.string.okay, null)
                        .create().show();
            }
        });
    }

    private boolean isAllFill() {
        isEmpty(projectEditText);
        isEmpty(codeEditText);
        isEmpty(startDateEditText);
        isEmpty(endDateEditText);
        isEmpty(versionEditText);

        return !projectEditText.getText().toString().isEmpty()
                && !codeEditText.getText().toString().isEmpty()
                && !startDateEditText.getText().toString().isEmpty()
                && !endDateEditText.getText().toString().isEmpty()
                && !versionEditText.getText().toString().isEmpty()
                ;
    }

    private void isEmpty(EditText editText) {
        if(editText.getText().toString().isEmpty())
            editText.setError(getString(R.string.fillinthisfield));

    }

    private void initialize() {
        projectEditText = findViewById(R.id.editText_project_name);
        codeEditText = findViewById(R.id.editText_code);
        startDateEditText = findViewById(R.id.editText_start_date);
        endDateEditText = findViewById(R.id.editText_end_date);
        versionEditText = findViewById(R.id.editText_version);

        saveButton = findViewById(R.id.button_save);
    }

    private void setupDatePickerInput() {

        addDatePickerInteraction(startDateCalendar, startDateEditText);
        addDatePickerInteraction(endDateCalendar, endDateEditText);

    }

    private void addDatePickerInteraction(final Calendar calendar, final EditText editText) {

        editText.setFocusable(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        SimpleDateFormat dateFormat =
                                new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        calendar.set(year, month, day);
                        editText.setText(dateFormat.format(calendar.getTime()));
                        editText.setError(null);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });
    }

}
