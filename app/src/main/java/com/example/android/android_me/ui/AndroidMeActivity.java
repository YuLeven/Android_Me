/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    private BodyPartFragment mHeadFragment;
    private BodyPartFragment mBodyFragment;
    private BodyPartFragment mLegsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        // TODO (5) Only create new fragments when there is no previously saved state

        // We call this helper (Igor?) funciton that will attach
        // our body parts onto the current view
        attachBodyPartsToScreen();
    }

    private void setInitialBodyPartsImages(int intialIndex) {
        // Create a new head BodyPartFragment
        mHeadFragment = new BodyPartFragment();
        mHeadFragment.setmImageIds(AndroidImageAssets.getHeads());
        mHeadFragment.setmListIndex(intialIndex);

        // Create a new body BodyPartFragment
        mBodyFragment = new BodyPartFragment();
        mBodyFragment.setmImageIds(AndroidImageAssets.getBodies());
        mBodyFragment.setmListIndex(intialIndex);

        // Create a new legs BodyPartFragment
        mLegsFragment = new BodyPartFragment();
        mLegsFragment.setmImageIds(AndroidImageAssets.getLegs());
        mLegsFragment.setmListIndex(intialIndex);
    }

    /**
     * A Victor Frankenstein style function to attach body parts onto something
     */
    private void attachBodyPartsToScreen() {

        // Here we set the initial images for the body parts
        setInitialBodyPartsImages(1);

        // Add the fragment to its container using a FragmentManager and a Transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.head_container, mHeadFragment)
                .add(R.id.body_container, mBodyFragment)
                .add(R.id.legs_container, mLegsFragment)
                .commit();
    }
}
