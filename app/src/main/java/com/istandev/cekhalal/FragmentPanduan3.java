package com.istandev.cekhalal;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link FragmentPanduan3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPanduan3 extends android.support.v4.app.Fragment {
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
        View rootView = inflater.inflate(R.layout.fragment_panduan3, container, false);
        return rootView;
    }
}