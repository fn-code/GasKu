package com.funcode.funcode.gasku.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.funcode.funcode.gasku.R;

/**
 * Created by funcode on 10/31/17.
 */

public class HistoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View history = inflater.inflate(R.layout.history_fragment, container, false);

        return history;
    }
}
