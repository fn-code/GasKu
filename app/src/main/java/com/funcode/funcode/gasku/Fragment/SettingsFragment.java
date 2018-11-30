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

public class SettingsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View setting = inflater.inflate(R.layout.settings_fragment, container, false);

        return setting;
    }
}
