package apps.harshitc.aumpmess.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import apps.harshitc.aumpmess.R;
import apps.harshitc.aumpmess.adapters.MyListAdapter2;
import apps.harshitc.aumpmess.adapters.TabAdapter;
import apps.harshitc.aumpmess.fragments.TabFragment1;
import apps.harshitc.aumpmess.fragments.TabFragment2;
import apps.harshitc.aumpmess.fragments.TabFragment3;
import apps.harshitc.aumpmess.fragments.TabFragment4;
import apps.harshitc.aumpmess.models.MyListData2;
import apps.harshitc.aumpmess.repository.DataSource;
import apps.harshitc.aumpmess.utils.Tools;

public class Main2Activity extends AppCompatActivity {
    private ActionBar actionBar;
    private TabAdapter adapter;
    private ViewPager view_pager;
    private TabLayout tabLayout;
    private ArrayList<MyListData2> list2;
    public RecyclerView recyclerView;
    public MyListAdapter2 myListAdapter2;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer_simple_light);

        initToolbar();
        initNavigationMenu();
        initComponent();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(" Menu");
        actionBar.setLogo(R.drawable.ic_launcher_foreground);
         actionBar.setDisplayUseLogoEnabled(false);
        Tools.setSystemBarColor(this);

    }

    private void initNavigationMenu() {
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                 actionBar.setTitle(item.getTitle());
                if(item.getTitle().toString().contains("About"))
                   startActivity(new Intent(Main2Activity.this,AboutAppSimple.class));



                drawer.closeDrawers();
                return true;
            }
        });

        // open drawer at start
        drawer.openDrawer(GravityCompat.START);
    }




    private void initComponent() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);


        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(view_pager);
        setupViewPager(view_pager);
    }

    private void setupViewPager(ViewPager viewPager) {


        adapter = new TabAdapter(getSupportFragmentManager());

        adapter.addFragment(new TabFragment1(), "Breakfat");
        adapter.addFragment(new TabFragment2(), "Lunch");
        adapter.addFragment(new TabFragment3(), "Snacks");
        adapter.addFragment(new TabFragment4(), "Dinner");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void setActionBarTitle(String s)
    {

        getSupportActionBar().setTitle(s);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            DataSource dataSource=new DataSource();
            dataSource.vollyGet(this);


            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }





}
