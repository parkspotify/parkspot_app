package de.hdmstuttgart.parkspot.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import de.hdmstuttgart.parkspot.Constants;
import de.hdmstuttgart.parkspot.RaspberryLocationListFragment;
import de.hdmstuttgart.parkspot.dummy.DummyContent;
import de.hdmstuttgart.parkspot.fragments.HereMapFragment;
import de.hdmstuttgart.parkspot.fragments.MapFragment;
import de.hdmstuttgart.parkspot.R;
import de.hdmstuttgart.parkspot.SharedPrefs;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements
        MapFragment.OnMapFragmentInteractionListener,
        RaspberryLocationListFragment.OnListFragmentInteractionListener,
        HereMapFragment.OnFragmentInteractionListener {

    private ViewPager mPager;
    private static final int NUM_PAGES = 2;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        checkPermissions();
        SharedPrefs.init(getApplicationContext());
        mPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * Check if needed permissions are granted - if not request them.
     */
    private void checkPermissions() {

        final List<String> permissionsList = new ArrayList<>();
        addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        addPermission(permissionsList, Manifest.permission.ACCESS_COARSE_LOCATION);
        addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION);
        addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        addPermission(permissionsList, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionsList.size() > 0) {
            ActivityCompat.requestPermissions(this, permissionsList.toArray(new String[permissionsList.size()]),
                    Constants.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
        }
    }

    private void addPermission(List<String> permissionsList, String permission) {
        if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        if (requestCode == Constants.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS) {
            Map<String, Integer> perms = new HashMap<>();
            perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
            perms.put(Manifest.permission.READ_CONTACTS, PackageManager.PERMISSION_GRANTED);
            perms.put(Manifest.permission.WRITE_CONTACTS, PackageManager.PERMISSION_GRANTED);
            for (int i = 0; i < permissions.length; i++)
                perms.put(permissions[i], grantResults[i]);
            if (perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                // All Permissions Granted
                Toast.makeText(MainActivity.this, getResources().getText(R.string.permission_granted), Toast.LENGTH_SHORT)
                        .show();
            } else {
                // Permission Denied
                Toast.makeText(MainActivity.this, getResources().getText(R.string.permission_denied), Toast.LENGTH_SHORT)
                        .show();
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem mItem) {

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return HereMapFragment.newInstance();
            else if (position == 1)
                return RaspberryLocationListFragment.newInstance();
            else return MapFragment.newInstance();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
