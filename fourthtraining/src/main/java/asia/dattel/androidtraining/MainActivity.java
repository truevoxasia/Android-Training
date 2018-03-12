package asia.dattel.androidtraining;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import asia.dattel.androidtraining.entity.Project;

public class MainActivity extends AppCompatActivity implements
        ProjectAddFragment.OnFragmentInteractionListerner,
        ProjectListFragment.OnFragmentInteractionListener{


    ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        fragments = new Fragment[]{
                ProjectAddFragment.newInstance(),
                ProjectListFragment.newInstance()
        };
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                if (position < fragments.length) {
                    return fragments[position];
                }
                return new Fragment();

            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_add:
                                viewPager.setCurrentItem(0);
                                return true;
                            case R.id.action_list:
                                viewPager.setCurrentItem(1);
                                return true;
                        }
                        return false;
                    }
                });
    }


    @Override
    public void onProjectAddInteraction(Project project) {
        Fragment fragment = fragments[1];
        if (fragment instanceof ProjectListFragment){
            ((ProjectListFragment) fragment).updateList(project);
        }
    }

    // Todo : Add upload Code here
    // API : https://android-training-7d330.firebaseio.com/projects.json
    // Method : POST
    @Override
    public void onUploadDataInteraction(Project mItem) {

    }
}
