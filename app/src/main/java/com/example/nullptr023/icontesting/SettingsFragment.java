package com.example.nullptr023.icontesting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private Toolbar mToolbar;


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        setupToolbar(rootView);

        return rootView;
    }

    private void setupToolbar(View rootView) {
        // setup toolbar
        mToolbar = (Toolbar) rootView.findViewById(R.id.settings_toolbar);
        mToolbar.setTitle("Settings");

        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d(TAG, "FBusStopPrediction back!!!222222!!!!!!!!!");
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        mToolbar.inflateMenu(R.menu.menu_settings);
        setupMenuItem();
    }

    private void setupMenuItem() {
        Menu menu = mToolbar.getMenu();
        // save menu item
        MenuItem menuItem = menu.getItem(0);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                saveSettings();
                return true;
            }
        });
    }

    private boolean saveSettings() {

        Toast.makeText(getActivity(), "Save Successfully!", Toast.LENGTH_LONG).show();

        return true;
    }

}
