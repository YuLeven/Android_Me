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

<<<<<<< Updated upstream
=======
    private BodyPartFragment mHeadFragment;
    private BodyPartFragment mBodyFragment;
    private BodyPartFragment mLegsFragment;
>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

<<<<<<< Updated upstream
        // Only create new fragments when there is no previously saved state
        if(savedInstanceState == null) {

            // TODO (5) Retrieve list index values that were sent through an intent; use them to display the desired Android-Me body part image
                // Use setListindex(int index) to set the list index for all BodyPartFragments

            // Create a new head BodyPartFragment
            BodyPartFragment headFragment = new BodyPartFragment();

            // Set the list of image id's for the head fragment and set the position to the second image in the list
            headFragment.setImageIds(AndroidImageAssets.getHeads());
            headFragment.setListIndex(1);

            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();

            // Create and display the body and leg BodyPartFragments

            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setImageIds(AndroidImageAssets.getBodies());
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyFragment)
                    .commit();

            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setImageIds(AndroidImageAssets.getLegs());
            fragmentManager.beginTransaction()
                    .add(R.id.leg_container, legFragment)
                    .commit();
        }

=======
        // Create a new head BodyPartFragment
        mHeadFragment = new BodyPartFragment();
        mHeadFragment.setmImageIds(AndroidImageAssets.getHeads());
        // Create a new body BodyPartFragment
        mBodyFragment = new BodyPartFragment();
        mBodyFragment.setmImageIds(AndroidImageAssets.getBodies());
        // Create a new legs BodyPartFragment
        mLegsFragment = new BodyPartFragment();
        mLegsFragment.setmImageIds(AndroidImageAssets.getLegs());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mHeadFragment.setmListIndex(bundle.getInt(MainActivity.HEAD_BUNDLE_ID));
            mBodyFragment.setmListIndex(bundle.getInt(MainActivity.BODY_BUNDLE_ID));
            mLegsFragment.setmListIndex(bundle.getInt(MainActivity.LEGS_BUNDLE_ID));
        } else {
            // Here we set the initial images for the body parts
            initializeBodyPartsToDefaultIndex(1);
        }

        if (savedInstanceState == null) {
            // We call this helper (Igor?) funciton that will attach
            // our body parts onto the current view
            attachBodyPartsToScreen();
        }
    }

    private void initializeBodyPartsToDefaultIndex(int intialIndex) {
        mHeadFragment.setmListIndex(intialIndex);
        mBodyFragment.setmListIndex(intialIndex);
        mLegsFragment.setmListIndex(intialIndex);
    }

    /**
     * A Victor Frankenstein style function to attach body parts onto something
     */
    private void attachBodyPartsToScreen() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.head_container, mHeadFragment)
                .add(R.id.body_container, mBodyFragment)
                .add(R.id.legs_container, mLegsFragment)
                .commit();
>>>>>>> Stashed changes
    }
}
