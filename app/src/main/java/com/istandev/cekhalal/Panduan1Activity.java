package com.istandev.cekhalal;

import java.util.Locale;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

public class Panduan1Activity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan1);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setSubtitle("Langkah 1");
        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //actionBar.setTitle(mSectionsPagerAdapter.getPageTitle(0));

        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                // show the given tab
                mViewPager = (ViewPager) findViewById(R.id.pager);
                mViewPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // hide the given tab
            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // probably ignore this event
            }
        };

        // Add 4 tabs, specifying the tab's text and TabListener

        for (int i = 0; i < 4; i++) {
            if (i==0){
                actionBar.addTab(actionBar.newTab().setIcon(R.mipmap.ic_launcher).setTabListener(tabListener));
            }
            else if (i==1){
                actionBar.addTab(actionBar.newTab().setIcon(R.mipmap.ic_launcher).setTabListener(tabListener));
            }
            else if (i==2){
                actionBar.addTab(actionBar.newTab().setIcon(R.mipmap.ic_launcher).setTabListener(tabListener));
            }
            else if (i==3){
                actionBar.addTab(actionBar.newTab().setIcon(R.mipmap.ic_launcher).setTabListener(tabListener));
            }
            else if (i==4){
                actionBar.addTab(actionBar.newTab().setIcon(R.mipmap.ic_launcher).setTabListener(tabListener));
            }
            else {
                actionBar.addTab(actionBar.newTab().setIcon(R.mipmap.ic_launcher).setTabListener(tabListener));
            }
        }

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        getSupportActionBar().setSelectedNavigationItem(position);
                        getSupportActionBar().setDisplayShowHomeEnabled(true);
                        getSupportActionBar().setSubtitle(mSectionsPagerAdapter.getPageTitle(position));
                    }
                });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_panduan1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0 :return FragmentPanduan.newInstance(position + 1);
                case 1 :return FragmentPanduan.newInstance(position + 1);
                case 2 :return FragmentPanduan3.newInstance(position + 1);
                case 3 :return FragmentPanduan4.newInstance(position + 1);
                case 4 :return FragmentPanduan5.newInstance(position + 1);
                case 5 :return FragmentPanduan6.newInstance(position + 1);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 6 total pages.
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1);
                case 1:
                    return getString(R.string.title_section2);
                case 2:
                    return getString(R.string.title_section3);
                case 3:
                    return getString(R.string.title_section4);
                case 4:
                    return getString(R.string.title_section5);
                case 5:
                    return getString(R.string.title_section6);
            }
            return null;
        }
    }
}
