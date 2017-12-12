package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;
import com.example.android.android_me.util.Util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Yuri Levenhagen on 2017-12-12 as part
 * of the Udacity-Google Advanced Android App Development course.
 * <p>
 * The base example code belongs to The Android Open Source Project under the Apache 2.0 licence
 * All code further implemented as part of the course is under the same licence.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {

    private static final String LOG_TAG = MainActivity.class.getCanonicalName();
    public static final String HEAD_BUNDLE_ID = "HEAD_BUNDLE_ID";
    public static final String BODY_BUNDLE_ID = "BODY_BUNDLE_ID";
    public static final String LEGS_BUNDLE_ID = "LEGS_BUNDLE_ID";

    private int headIndex;
    private int bodyIndex;
    private int legsIndex;
    private Button mNextButton;
    private boolean mTwoPane;
    private BodyPartFragment mHeadFragment;
    private BodyPartFragment mBodyFragment;
    private BodyPartFragment mLegsFragment;

    private static final int HEAD = 0;
    private static final int BODY = 1;
    private static final int LEGS = 2;
    private static final int UNKNOWN_PART = 3;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
        HEAD,
        BODY,
        LEGS,
        UNKNOWN_PART
    })
    public @interface BodyPart {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setEnabled(false);

        // If we're in two pane mode, inflate the Android body parts
        mTwoPane = findViewById(R.id.android_me_linear_layout) != null;
        if (mTwoPane) {

            // Create a new head BodyPartFragment
            mHeadFragment = new BodyPartFragment();
            mHeadFragment.setImageIds(AndroidImageAssets.getHeads());
            // Create a new body BodyPartFragment
            mBodyFragment = new BodyPartFragment();
            mBodyFragment.setImageIds(AndroidImageAssets.getBodies());
            // Create a new legs BodyPartFragment
            mLegsFragment = new BodyPartFragment();
            mLegsFragment.setImageIds(AndroidImageAssets.getLegs());

            mNextButton.setVisibility(View.GONE);
            if (savedInstanceState == null) {
                // We call this helper (Igor?) function that will attach
                // our body parts onto the current view
                Util.attachBodyPartsToScreen(
                        getSupportFragmentManager(),
                        mHeadFragment,
                        mBodyFragment,
                        mLegsFragment
                );
            }
        }

        GridView gridView = (GridView) findViewById(R.id.images_grid_view);
        gridView.setNumColumns(2);
    }


    /**
     * Handles app behaviour when a image is tapped in MasterListFragment
     * @param position - The position of the tapped image
     */
    public void onImageSelected(int position) {
        if (mTwoPane) {
            handleTwoPanelImageSelected(position);
        } else {
            handleSinglePanelImageSelected(position);
        }
    }

    /**
     * Handles image taps on double panels
     * @param position - The position of the tapped image
     */
    private void handleTwoPanelImageSelected(int position) {
        BodyPartFragment newBodyPart = new BodyPartFragment();

        switch (getBodyPart(position)) {
            case HEAD:
                newBodyPart.setImageIds(AndroidImageAssets.getHeads());
                newBodyPart.setListIndex(getBodyPartIndex(HEAD, position));
                replaceBodyPart(R.id.head_container, newBodyPart);
                break;
            case BODY:
                newBodyPart.setImageIds(AndroidImageAssets.getBodies());
                newBodyPart.setListIndex(getBodyPartIndex(BODY, position));
                replaceBodyPart(R.id.body_container, newBodyPart);
                break;
            case LEGS:
                newBodyPart.setImageIds(AndroidImageAssets.getLegs());
                newBodyPart.setListIndex(getBodyPartIndex(LEGS, position));
                replaceBodyPart(R.id.legs_container, newBodyPart);
                break;
            case UNKNOWN_PART:
                Log.v(LOG_TAG, "The clicked android part exceeds the expected boundary for head, body and legs (12 for each)");
                break;
        }
    }

    /**
     * Handles image taps on single panels
     * @param position - The position of the tapped image
     */
    private void handleSinglePanelImageSelected(int position) {
        switch (getBodyPart(position)) {
            case HEAD: headIndex = getBodyPartIndex(HEAD, position);
                break;
            case BODY: bodyIndex = getBodyPartIndex(BODY, position);
                break;
            case LEGS: legsIndex = getBodyPartIndex(LEGS, position);
                break;
            case UNKNOWN_PART:
                Log.v(LOG_TAG, "The clicked android part exceeds the expected boundary for head, body and legs (12 for each)");
                break;
        }

        Bundle bundle = new Bundle();
        bundle.putInt(HEAD_BUNDLE_ID, headIndex);
        bundle.putInt(BODY_BUNDLE_ID, bodyIndex);
        bundle.putInt(LEGS_BUNDLE_ID, legsIndex);

        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(bundle);

        mNextButton.setEnabled(true);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    /**
     * Replaces the passed fragment id with the passed body part
     * @param viewID - The ID of the view to be replaced
     * @param newBodyPart - The body part which will replace the current one
     */
    private void replaceBodyPart(int viewID, BodyPartFragment newBodyPart) {
        getSupportFragmentManager().beginTransaction()
                .replace(viewID, newBodyPart)
                .commit();
    }

    /**
     * Gets the body part which was tapped relative to its position in
     * the group of 12 heads, bodies and legs
     * @param pos - The tapped position
     * @return - A type-safe integer representing the tapped body part
     */
    private @BodyPart int getBodyPart(int pos) {
        switch (pos / 12) {
            case 0:
                return HEAD;
            case 1:
                return BODY;
            case 2:
                return LEGS;
            default:
                return UNKNOWN_PART;
        }
    }

    /**
     * Gets the index of the image asset based on the body part
     * This is done by calculating it's relative position to the group of
     * 12 heads, bodies and legs
     * @param part - The part of the body whose ID will be retrieved
     * @param pos - The absolute position tapped on the grid view
     * @return - The position of the body part within its array
     */
    private int getBodyPartIndex(@BodyPart int part, int pos) {
        return pos - 12 * part;
    }

}
