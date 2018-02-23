package asia.dattel.androidtraining;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import asia.dattel.androidtraining.entity.Project;

public class ProjectListFragment extends Fragment {
    private List<Project> projects;

    public ProjectListFragment() {
    }

    public static ProjectListFragment newInstance() {
        return new ProjectListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_list, container, false);

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        projects = new ArrayList<>();
        recyclerView.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new ProjectRecyclerViewAdapter(projects));
        return view;
    }

    // Todo : add code here, to notify list
    public void updateList(Project project){

    }
}
