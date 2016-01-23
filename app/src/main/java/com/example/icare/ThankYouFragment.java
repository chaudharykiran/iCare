package com.example.icare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kiran on 1/23/16.
 */
public class ThankYouFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            return inflater.inflate(R.layout.thank_you, container, false);
    }

    /**
     * This create new ThankYouFragment
     * @return
     */
    public static ThankYouFragment newInstance() {
        ThankYouFragment fragment = new ThankYouFragment();

        return fragment;
    }

}
