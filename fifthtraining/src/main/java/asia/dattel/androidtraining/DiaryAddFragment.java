package asia.dattel.androidtraining;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import asia.dattel.androidtraining.entity.DaoSession;
import asia.dattel.androidtraining.entity.Diary;

public class DiaryAddFragment extends Fragment {

    private Calendar startDateCalendar = Calendar.getInstance();
    private Calendar endDateCalendar = Calendar.getInstance();

    private EditText projectEditText;
    private EditText codeEditText;
    private EditText startDateEditText;
    private EditText endDateEditText;
    private EditText versionEditText;
    private Button saveButton;

    private DaoSession session;
    private OnFragmentInteractionListerner mListener;

    public DiaryAddFragment() {
    }


    public static DiaryAddFragment newInstance() {
        return new DiaryAddFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diary_add, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initialize(view);
        setupDatePickerInput();
        setButtonInteraction();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListerner) {
            session = ((BaseApplication) getActivity().getApplication()).getSession();

            mListener = (OnFragmentInteractionListerner) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    private void storeIntoDb() {
        final Diary diary = new Diary();
        diary.projectName = projectEditText.getText().toString();
        diary.code = codeEditText.getText().toString();
        diary.startDate = startDateEditText.getText().toString();
        diary.endDate = endDateEditText.getText().toString();
        diary.version = Long.parseLong(versionEditText.getText().toString());

        if (session.getDiaryDao().insert(diary) != -1) {

            mListener.onProjectAddInteraction(diary);
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.saved)
                    .setMessage(
                            String.format(
                                    Locale.getDefault(),
                                    "Diary Name : %s\n" +
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
            clearAllField();

        } else {

            Snackbar.make(saveButton, R.string.unabletostoreproject, Snackbar.LENGTH_LONG);
        }
    }


    private void clearAllField() {
        projectEditText.setText(null);
        codeEditText.setText(null);
        startDateEditText.setText(null);
        endDateEditText.setText(null);
        versionEditText.setText(null);

        projectEditText.requestFocus();
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
        if (editText.getText().toString().isEmpty())
            editText.setError(getString(R.string.fillinthisfield));

    }

    private void initialize(View view) {
        projectEditText = view.findViewById(R.id.editText_project_name);
        codeEditText = view.findViewById(R.id.editText_code);
        startDateEditText = view.findViewById(R.id.editText_start_date);
        endDateEditText = view.findViewById(R.id.editText_end_date);
        versionEditText = view.findViewById(R.id.editText_version);

        saveButton = view.findViewById(R.id.button_save);
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
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
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

    public interface OnFragmentInteractionListerner {

        void onProjectAddInteraction(Diary diary);
    }
}
