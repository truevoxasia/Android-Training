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

import java.util.List;

import asia.dattel.androidtraining.entity.DaoSession;
import asia.dattel.androidtraining.entity.Project;
import asia.dattel.androidtraining.entity.ProjectDao;

public class ProjectListFragment extends Fragment {
    private List<Project> projects;
    private DaoSession session;
    private RecyclerView recyclerView;

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
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        projects = session.getProjectDao().queryBuilder().orderDesc(
                ProjectDao.Properties.ProjectId
        ).list();
        recyclerView.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new ProjectRecyclerViewAdapter(projects));
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProjectAddFragment.OnFragmentInteractionListerner) {
            session = ((BaseApplication) getActivity().getApplication()).getSession();

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void updateList(Project project){
        projects.add(0, project);
        recyclerView.getAdapter().notifyItemInserted(0);
        recyclerView.scrollToPosition(0);
    }
}
