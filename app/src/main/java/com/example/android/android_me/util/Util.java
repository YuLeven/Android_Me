package com.example.android.android_me.util;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.example.android.android_me.R;
import com.example.android.android_me.ui.BodyPartFragment;

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

public class Util {
    /**
     * A Victor Frankenstein style function to attach body parts onto something
     */
    public static void attachBodyPartsToScreen(FragmentManager fragmentManager, BodyPartFragment head, BodyPartFragment body, BodyPartFragment legs) {
        fragmentManager.beginTransaction()
                .add(R.id.head_container, head)
                .add(R.id.body_container, body)
                .add(R.id.legs_container, legs)
                .commit();
    }
}
