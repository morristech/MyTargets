/*
 * Copyright (C) 2017 Florian Dreier
 *
 * This file is part of MyTargets.
 *
 * MyTargets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2
 * as published by the Free Software Foundation.
 *
 * MyTargets is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package de.dreier.mytargets.features.timer;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import de.dreier.mytargets.base.activities.ChildActivityBase;

public class TimerActivity extends ChildActivityBase {

    private static final String FRAGMENT_TAG = "fragment";
    Fragment childFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            // Create the fragment only when the activity is created for the first time.
            // ie. not after orientation changes
            childFragment = getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
            if (childFragment == null) {
                childFragment = instantiateFragment();
                Bundle bundle = getIntent() != null ? getIntent().getExtras() : null;
                childFragment.setArguments(bundle);
            }

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(android.R.id.content, childFragment, FRAGMENT_TAG);
            ft.commit();
        }
    }

    public Fragment instantiateFragment() {
        return new TimerFragment();
    }

}
