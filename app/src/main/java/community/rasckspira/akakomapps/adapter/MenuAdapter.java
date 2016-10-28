package community.rasckspira.akakomapps.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import community.rasckspira.akakomapps.fragment.BeritaFragment;
import community.rasckspira.akakomapps.fragment.KampusFragment;

/**
 * Created by muhwid on 10/27/2016.
 */

public class MenuAdapter extends FragmentStatePagerAdapter {

    public MenuAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment;

        switch (position) {
            case 0:
                returnFragment = new BeritaFragment();
                break;
            case 1:
                returnFragment = new KampusFragment();
                break;
            default:
                return null;
        }
        return  returnFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
