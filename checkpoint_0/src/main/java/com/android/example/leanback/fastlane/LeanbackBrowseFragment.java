package com.android.example.leanback.fastlane;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.CursorObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.SinglePresenterSelector;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.android.example.leanback.R;
import com.android.example.leanback.data.VideoDataManager;
import com.android.example.leanback.data.VideoItemContract;

public class LeanbackBrowseFragment extends BrowseFragment {
    private static final String[] HEADERS = new String[]{
            "Featured", "Popular", "Editor's choice"
    };

    private ArrayObjectAdapter mRowsAdapter;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }
    @TargetApi(Build.VERSION_CODES.M)
    public void init() {
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        setAdapter(mRowsAdapter);

        setBrandColor(ContextCompat.getColor(getContext(), R.color.primary));
        setBadgeDrawable(ContextCompat.getDrawable(getContext(), R.drawable.filmi));

        for (int position = 0; position < HEADERS.length; position++) {
            ObjectAdapter rowContents = new CursorObjectAdapter((new SinglePresenterSelector(new CardPresenter())));
            VideoDataManager manager = new VideoDataManager(getActivity(),
                    getLoaderManager(),
                    VideoItemContract.VideoItem.buildDirUri(),
                    rowContents);
            manager.startDataLoading();

            HeaderItem headerItem = new HeaderItem(position, HEADERS[position]);
            mRowsAdapter.add(new ListRow(headerItem, manager.getItemList()));
        }
    }

}
