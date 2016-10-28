package community.rasckspira.akakomapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import community.rasckspira.akakomapps.adapter.MenuAdapter;
import community.rasckspira.akakomapps.fragment.BeritaFragment;
import community.rasckspira.akakomapps.fragment.VisiMisiFragment;
import community.rasckspira.akakomapps.fragment.JurusanFragment;
import community.rasckspira.akakomapps.fragment.ProfileFragment;
import community.rasckspira.akakomapps.fragment.FragmentJabatan;
import community.rasckspira.akakomapps.fragment.KampusFragment;

public class MainActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private TabLayout tabMenu;
    private ViewPager vpHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);

        MenuAdapter adapter = new MenuAdapter(getSupportFragmentManager());
        vpHome = (ViewPager) findViewById(R.id.vp_home);
        vpHome.setAdapter(adapter);
        tabMenu = (TabLayout) findViewById(R.id.tab_menu);
        tabMenu.setupWithViewPager(vpHome);
        tabMenu.getTabAt(0).setText("BERITA");
        tabMenu.getTabAt(1).setText("INFO");

       /* ProfileFragment fragment = new ProfileFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();*/
        navigationView = (NavigationView) findViewById(R.id.navigationView);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(true);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();


                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {

                    //Replacing the main content with ProfileFragment Which is our Inbox View;
                    case R.id.nav_home:
                        /*ProfileFragment fragment = new ProfileFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.commit();
                        return true;*/

                    // For rest of the options we just show a toast on click

                    case R.id.nav_profil:
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        return true;
                       /* VisiMisiFragment fragment2 = new VisiMisiFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment2);
                        fragmentTransaction.commit();
                        return true;*/
                    case R.id.nav_visi_misi:
                        startActivity(new Intent(MainActivity.this, VisiMisiActivity.class));
                        return true;
                    case R.id.nav_programstudi:
                        startActivity(new Intent(MainActivity.this, JurusanActivity.class));
                        return true;
                        /*JurusanFragment fragment3 = new JurusanFragment();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment3);
                        fragmentTransaction.commit();
                        return true;*/
                    case R.id.nav_jabatan:
                        startActivity(new Intent(MainActivity.this, JabatanActivity.class));
                        return true;
                        /*FragmentJabatan fragment4 = new FragmentJabatan();
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, fragment4);
                        fragmentTransaction.commit();
                        return true;*/
                    case R.id.nav_kontakkami:
                        startActivity(new Intent(MainActivity.this, KontakMapsActivity.class));
                        return true;
                    case R.id.nav_tentangaplikasi:
                        DialogFrament customDialogFragment = new DialogFrament();
                        customDialogFragment.show(getSupportFragmentManager(), null);
                        return true;

                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.crot) {

            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}