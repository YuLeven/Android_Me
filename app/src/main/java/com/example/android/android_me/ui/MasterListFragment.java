package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

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

public class MasterListFragment extends Fragment {

    // This is the adapter responsible for populating
    // the grid view with several Android body parts.
    private MasterListAdapter mMasterListAdapter;
    protected OnImageClickListener mCallback;

    public interface OnImageClickListener {
        void onImageSelected(int position);
    }

    public MasterListFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // We ensure the host activity implemented OnImageClickListener,
        // otherwise we make sure all hell breaks loose
        try {
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.getClass().getCanonicalName() + " must imeplement OnImageClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Initialize the adapter
        mMasterListAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        // Get the grid view. We will both return it and use it's reference to set it's adapter
        GridView rootView = (GridView) inflater.inflate(R.layout.fragment_master_list, container, false);
        rootView.setAdapter(mMasterListAdapter);

        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                mCallback.onImageSelected(pos);
            }
        });

        return rootView;
    }
}
