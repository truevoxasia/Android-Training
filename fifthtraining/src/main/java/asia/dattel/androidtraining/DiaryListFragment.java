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
import asia.dattel.androidtraining.entity.Diary;
import asia.dattel.androidtraining.entity.ProjectDao;

public class DiaryListFragment extends Fragment {
    private List<Diary> diaries;
    private DaoSession session;
    private RecyclerView recyclerView;

    public DiaryListFragment() {
    }

    public static DiaryListFragment newInstance() {
        return new DiaryListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary_list, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        diaries = session.getDiaryDao().queryBuilder().orderDesc(
                ProjectDao.Properties.ProjectId
        ).list();
        recyclerView.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new DiaryRecyclerViewAdapter(diaries));
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DiaryAddFragment.OnFragmentInteractionListerner) {
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

    public void updateList(Diary diary){
        diaries.add(0, diary);
        recyclerView.getAdapter().notifyItemInserted(0);
        recyclerView.scrollToPosition(0);
    }
}
