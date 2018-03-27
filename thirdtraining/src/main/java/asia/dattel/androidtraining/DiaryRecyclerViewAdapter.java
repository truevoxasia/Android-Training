package asia.dattel.androidtraining;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import asia.dattel.androidtraining.entity.Project;

public class DiaryRecyclerViewAdapter extends RecyclerView.Adapter<DiaryRecyclerViewAdapter.ViewHolder> {

    private final List<Project> mValues;


    public DiaryRecyclerViewAdapter(List<Project> projects) {
        mValues = projects;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_project, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView projectTextView;
        public final TextView codeTextView;
        public final TextView startDateTextView;
        public final TextView endDateTextView;
        public final TextView versionTextView;
        public Project mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            projectTextView = (TextView) view.findViewById(R.id.textView_project_name);
            codeTextView = (TextView) view.findViewById(R.id.textView_code);
            startDateTextView = (TextView) view.findViewById(R.id.textView_start_date);
            endDateTextView = (TextView) view.findViewById(R.id.textView_end_date);
            versionTextView = (TextView) view.findViewById(R.id.textView_version);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + codeTextView.getText() + "'";
        }
    }
}
