package com.istandev.cekhalal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ADIK on 29/02/2016.
 */
public class FragmentPanduan6 extends android.support.v4.app.Fragment {
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
        View rootView = inflater.inflate(R.layout.fragment_panduan6, container, false);
        return rootView;
    }
}
