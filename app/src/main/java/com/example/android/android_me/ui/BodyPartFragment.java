package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by Yuri Levenhagen on 2017-12-12 as part
 * of the Udacity-Google Advanced Android App Development course.
 *
 * The base example code belongs to The Android Open Source Project under the Apache 2.0 licence
 * All code further implemented as part of the course is under the same licence.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class BodyPartFragment extends Fragment {
    /**
     * Android uses this empty constructor to do its thing. Should be left as is.
     */
    public BodyPartFragment() {}

    /**
     * This will be called after the hosting activity's onCreate()
     * Here we inflate the layout and set any needed resources
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflates the rootView which will host our image
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        // Get a reference to the ImageView inside the frament layout.
        ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);
        // Displays a mock image
        imageView.setImageResource(AndroidImageAssets.getHeads().get(0));

        return rootView;
    }
}
