package com.cpinfo.familydoctor.activity.patient;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.cpinfo.familydoctor.R;
import com.cpinfo.familydoctor.base.BaseActivity;

import butterknife.BindView;

public class VideoActivity extends BaseActivity {

    @BindView(R.id.video_view)
    VideoView videoView;
    @BindView(R.id.buffer)
    ProgressBar buffer;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_video;
    }

    @Override
    protected void lockScreen() {

    }

    @Override
    protected void handleIntent(Intent intent) {
        link = intent.getStringExtra("link");
    }

    private void initView() {
        Uri uri = Uri.parse(link);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                buffer.setVisibility(View.GONE);
            }
        });
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int what, int i1) {
                if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
                    buffer.setVisibility(View.VISIBLE);
                } else if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
                    if (mediaPlayer.isPlaying()) {
                        buffer.setVisibility(View.GONE);
//                        videoView.setVisibility(View.VISIBLE);
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
    }
}
