package asia.dattel.androidtraining;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Calendar startDateCalendar = Calendar.getInstance();

    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;
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
                storeIntoDb();
            }
        });
    }

    //Todo : Add storing data code here
    private void storeIntoDb() {


        new AlertDialog.Builder(this)
                .setTitle(R.string.saved)
                .setMessage(
                        String.format(
                                Locale.getDefault(),
                                "Title : %s\n" +
                                        "Date : %s\n" +
                                        "Description : %s",
                                titleEditText.getText().toString(),
                                dateEditText.getText().toString(),
                                descriptionEditText.getText().toString()
                        )
                )
                .setPositiveButton(R.string.okay, null)
                .create().show();
        clearAllField();


    }

    //Todo : Add reset field code here
    private void clearAllField() {

    }

    private boolean isAllFill() {
        isEmpty(titleEditText);
        isEmpty(descriptionEditText);
        isEmpty(dateEditText);

        return !titleEditText.getText().toString().isEmpty()
                && !descriptionEditText.getText().toString().isEmpty()
                && !dateEditText.getText().toString().isEmpty();
    }

    private void isEmpty(EditText editText) {
        if (editText.getText().toString().isEmpty())
            editText.setError(getString(R.string.fillinthisfield));

    }

    private void initialize() {
        titleEditText = findViewById(R.id.editText_title);
        descriptionEditText = findViewById(R.id.editText_description);
        dateEditText = findViewById(R.id.editText_date);

        saveButton = findViewById(R.id.button_save);
    }

    private void setupDatePickerInput() {

        addDatePickerInteraction(startDateCalendar, dateEditText);

    }

    private void addDatePickerInteraction(final Calendar calendar, final EditText editText) {

        editText.setInputType(InputType.TYPE_NULL);
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
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                    editText.performClick();
            }
        });
    }

}
