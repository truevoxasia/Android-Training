package asia.dattel.androidtraining;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.infideap.stylishwidget.view.AButton;

import java.util.List;

import asia.dattel.androidtraining.entity.Project;

public class ProjectRecyclerViewAdapter extends RecyclerView.Adapter<ProjectRecyclerViewAdapter.ViewHolder> {

    private final List<Project> mValues;


    public ProjectRecyclerViewAdapter(List<Project> projects) {
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

        holder.projectTextView.setText(holder.mItem.projectName);
        holder.versionTextView.setText(String.valueOf(holder.mItem.version));
        holder.startDateTextView.setText(holder.mItem.startDate);
        holder.endDateTextView.setText(holder.mItem.endDate);
        holder.codeTextView.setText(holder.mItem.code);

        if (holder.mItem.isUploaded) {
            holder.actionButton.setText(R.string.sent);
            holder.actionButton.setBackgroundResource(R.drawable.selector_button_success_fill);
        } else {
            holder.actionButton.setText(R.string.upload);
            holder.actionButton.setBackgroundResource(R.drawable.selector_button_accent_fill);
        }

        if (holder.mItem.isUploaded)
            holder.mView.setOnClickListener(null);
        else
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Todo : Add upload Code here
                    // API : https://android-training-7d330.firebaseio.com/projects.json
                    // Method : POST

                }
            });

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
        public final AButton actionButton;
        public Project mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            projectTextView = (TextView) view.findViewById(R.id.textView_project_name);
            codeTextView = (TextView) view.findViewById(R.id.textView_code);
            startDateTextView = (TextView) view.findViewById(R.id.textView_start_date);
            endDateTextView = (TextView) view.findViewById(R.id.textView_end_date);
            versionTextView = (TextView) view.findViewById(R.id.textView_version);

            actionButton = view.findViewById(R.id.button_action);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + codeTextView.getText() + "'";
        }
    }
}
