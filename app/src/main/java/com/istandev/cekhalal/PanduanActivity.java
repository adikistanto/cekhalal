package com.istandev.cekhalal;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PanduanActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Panduan Sertifikasi Halal");

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentPanduan1(), "Langkah 1");
        adapter.addFragment(new FragmentPanduan2(), "Langkah 2");
        adapter.addFragment(new FragmentPanduan3(), "Langkah 3");
        adapter.addFragment(new FragmentPanduan4(), "Langkah 4");
        adapter.addFragment(new FragmentPanduan5(), "Langkah 5");
        adapter.addFragment(new FragmentPanduan6(), "Langkah 6");
        adapter.addFragment(new FragmentPanduan7(), "Langkah 7");
        adapter.addFragment(new FragmentPanduan8(), "Langkah 8");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            //return mFragmentList.get(position);
            switch (position){
                case 0: //tab 1
                    return FragmentPanduan1.newInstance(1);
                case 1: //tab 2
                    return FragmentPanduan2.newInstance(2);
                case 2: //tab 3
                    return FragmentPanduan3.newInstance(3);
                case 3: //tab 4
                    return FragmentPanduan4.newInstance(4);
                case 4: //tab 5
                    return FragmentPanduan5.newInstance(5);
                case 5: //tab 6
                    return FragmentPanduan6.newInstance(6);
                case 6: //tab 7
                    return FragmentPanduan7.newInstance(7);
                case 7: //tab 8
                    return FragmentPanduan8.newInstance(8);
            }
            return null;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_panduan, menu);
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

    //1. Apa Itu Sertifikat Halal?
    //2. Jaminan Halal dari Produsen
    //3. Prosedur Sertifikasi Halal
    //4. Hal yang harus dilakukan perusahaan pemohon
    //5. Pemeriksaan (audit) produk halal
    //6. Sistem Pengawasan Sertifikat Halal
    //7. Prosedur Perpanjangan Sertifikat Halal
    //8. Info lebih lanjut

    //1
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class FragmentPanduan1 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static FragmentPanduan1 newInstance(int sectionNumber) {
            FragmentPanduan1 fragment = new FragmentPanduan1();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public FragmentPanduan1() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_panduan, container, false);
            TextView textViewJudulPanduan = (TextView) rootView.findViewById(R.id.judul_panduan);
            textViewJudulPanduan.setText(Html.fromHtml(getString(R.string.judul_panduan1)));
            TextView textViewIsiPanduan = (TextView) rootView.findViewById(R.id.isi_panduan);
            textViewIsiPanduan.setText(Html.fromHtml(getString(R.string.isi_panduan1)));
            return rootView;
        }
    }

    //1
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class FragmentPanduan2 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static FragmentPanduan2 newInstance(int sectionNumber) {
            FragmentPanduan2 fragment = new FragmentPanduan2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public FragmentPanduan2() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_panduan, container, false);
            TextView textViewJudulPanduan = (TextView) rootView.findViewById(R.id.judul_panduan);
            textViewJudulPanduan.setText(Html.fromHtml(getString(R.string.judul_panduan2)));
            TextView textViewIsiPanduan = (TextView) rootView.findViewById(R.id.isi_panduan);
            textViewIsiPanduan.setText(Html.fromHtml(getString(R.string.isi_panduan2)));
            return rootView;
        }
    }

    //1
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class FragmentPanduan3 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static FragmentPanduan3 newInstance(int sectionNumber) {
            FragmentPanduan3 fragment = new FragmentPanduan3();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public FragmentPanduan3() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_panduan, container, false);
            TextView textViewJudulPanduan = (TextView) rootView.findViewById(R.id.judul_panduan);
            textViewJudulPanduan.setText(Html.fromHtml(getString(R.string.judul_panduan3)));
            TextView textViewIsiPanduan = (TextView) rootView.findViewById(R.id.isi_panduan);
            textViewIsiPanduan.setText(Html.fromHtml(getString(R.string.isi_panduan3)));
            return rootView;
        }
    }

    //1
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class FragmentPanduan4 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static FragmentPanduan4 newInstance(int sectionNumber) {
            FragmentPanduan4 fragment = new FragmentPanduan4();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public FragmentPanduan4() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_panduan, container, false);
            TextView textViewJudulPanduan = (TextView) rootView.findViewById(R.id.judul_panduan);
            textViewJudulPanduan.setText(Html.fromHtml(getString(R.string.judul_panduan4)));
            TextView textViewIsiPanduan = (TextView) rootView.findViewById(R.id.isi_panduan);
            textViewIsiPanduan.setText(Html.fromHtml(getString(R.string.isi_panduan4)));
            return rootView;
        }
    }

    //1
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class FragmentPanduan5 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static FragmentPanduan5 newInstance(int sectionNumber) {
            FragmentPanduan5 fragment = new FragmentPanduan5();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public FragmentPanduan5() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_panduan, container, false);
            TextView textViewJudulPanduan = (TextView) rootView.findViewById(R.id.judul_panduan);
            textViewJudulPanduan.setText(Html.fromHtml(getString(R.string.judul_panduan5)));
            TextView textViewIsiPanduan = (TextView) rootView.findViewById(R.id.isi_panduan);
            textViewIsiPanduan.setText(Html.fromHtml(getString(R.string.isi_panduan5)));
            return rootView;
        }
    }

    //1
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class FragmentPanduan6 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static FragmentPanduan6 newInstance(int sectionNumber) {
            FragmentPanduan6 fragment = new FragmentPanduan6();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public FragmentPanduan6() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_panduan, container, false);
            TextView textViewJudulPanduan = (TextView) rootView.findViewById(R.id.judul_panduan);
            textViewJudulPanduan.setText(Html.fromHtml(getString(R.string.judul_panduan6)));
            TextView textViewIsiPanduan = (TextView) rootView.findViewById(R.id.isi_panduan);
            textViewIsiPanduan.setText(Html.fromHtml(getString(R.string.isi_panduan6)));
            return rootView;
        }
    }

    //1
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class FragmentPanduan7 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static FragmentPanduan7 newInstance(int sectionNumber) {
            FragmentPanduan7 fragment = new FragmentPanduan7();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public FragmentPanduan7() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_panduan, container, false);
            TextView textViewJudulPanduan = (TextView) rootView.findViewById(R.id.judul_panduan);
            textViewJudulPanduan.setText(Html.fromHtml(getString(R.string.judul_panduan7)));
            TextView textViewIsiPanduan = (TextView) rootView.findViewById(R.id.isi_panduan);
            textViewIsiPanduan.setText(Html.fromHtml(getString(R.string.isi_panduan7)));
            return rootView;
        }
    }

    //1
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class FragmentPanduan8 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static FragmentPanduan8 newInstance(int sectionNumber) {
            FragmentPanduan8 fragment = new FragmentPanduan8();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public FragmentPanduan8() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_panduan, container, false);
            TextView textViewJudulPanduan = (TextView) rootView.findViewById(R.id.judul_panduan);
            textViewJudulPanduan.setText(Html.fromHtml(getString(R.string.judul_panduan8)));
            TextView textViewIsiPanduan = (TextView) rootView.findViewById(R.id.isi_panduan);
            textViewIsiPanduan.setText(Html.fromHtml(getString(R.string.isi_panduan8)));
            return rootView;
        }
    }

}
