package com.android.example.leanback.fastlane;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v17.leanback.widget.AbstractDetailsDescriptionPresenter;
import android.support.v17.leanback.widget.SpeechRecognitionCallback;
import android.util.Log;

import com.android.example.leanback.R;
import com.android.example.leanback.data.Video;

public class DetailsDescriptionPresenter extends AbstractDetailsDescriptionPresenter {
    private Context mContext;

    public DetailsDescriptionPresenter(Context context) {
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    protected void onBindDescription(ViewHolder viewHolder, Object o) {
        Video video = (Video) o;

        if (video != null) {
            Log.d("Presenter", String.format("%s, %s, %s", video.getTitle(), video.getThumbUrl(), video.getDescription()));
            viewHolder.getTitle().setText(video.getTitle());

            viewHolder.getBody().setText(video.getDescription());
        }
    }
}
