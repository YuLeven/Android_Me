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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNextButton = (Button) findViewById(R.id.next_button);
    }

    // Define the behavior for onImageSelected
    public void onImageSelected(int position) {
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

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

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

    private int getBodyPartIndex(@BodyPart int part, int pos) {
        return pos - 12 * part;
    }

}
