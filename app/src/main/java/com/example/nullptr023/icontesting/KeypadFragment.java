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


/**
 * A simple {@link Fragment} subclass.
 */
public class KeypadFragment extends Fragment {

    private static final String TAG = KeypadFragment.class.getName();
    private Toolbar mToolbar;


    public KeypadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_keypad, container, false);

        // setup toolbar
        mToolbar = rootView.findViewById(R.id.keypad_toolbar);
        mToolbar.setTitle("Dialer");
        mToolbar.inflateMenu(R.menu.menu_dialer);
        setupMenuItem();


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    private void setupMenuItem() {
        Menu menu = mToolbar.getMenu();
        // settings
        MenuItem menuItem = menu.getItem(0);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(android.R.id.content, new SettingsFragment(), "settingsFragment")
                        .addToBackStack("settingsFragment")
                        .commit();

                return true;
            }
        });


    }

}
