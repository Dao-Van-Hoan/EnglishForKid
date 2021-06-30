package com.example.englishforkids.Music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.englishforkids.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MusicActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView tvTitle, tvSongTime, tvTotalTime;
    SeekBar seekBar;
    ImageButton btnPrevious, btnPlay, btnStop, btnNext;
    ArrayList<Song> songArrayList;
    CircleImageView circleImageView;
    int position = 0;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        AnhXa();
        Actionbar();
        AddSong();
        KhoiTaoMediaPlayer();
        EventClick();
    }

    private void EventClick() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.ic_play);
                    StopAnim();
                } else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.ic_pause);
                    StartAnim();
                }
                SetTimeTotal();
                UpdateTimeSong();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                StopAnim();
                btnPlay.setImageResource(R.drawable.ic_play);
                KhoiTaoMediaPlayer();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                if (position > songArrayList.size() - 1) {
                    position = 0;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                StartAnim();
                btnPlay.setImageResource(R.drawable.ic_pause);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if (position < 0) {
                    position = songArrayList.size() - 1;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                StartAnim();
                btnPlay.setImageResource(R.drawable.ic_pause);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }

    private void UpdateTimeSong() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
                tvSongTime.setText(dateFormat.format(mediaPlayer.getCurrentPosition()));
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > songArrayList.size() - 1) {
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                            StopAnim();
                        }
                        KhoiTaoMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.ic_pause);
                        StartAnim();
                        SetTimeTotal();
                        UpdateTimeSong();
                    }
                });
                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    private void SetTimeTotal() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        tvTotalTime.setText(dateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void KhoiTaoMediaPlayer() {
        mediaPlayer = MediaPlayer.create(MusicActivity.this, songArrayList.get(position).getFile());
        tvTitle.setText(songArrayList.get(position).getTitle());
    }

    private void StartAnim() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                circleImageView.animate().rotationBy(360).withEndAction(this).setDuration(10000).setInterpolator(new LinearInterpolator()).start();
            }
        };
        circleImageView.animate().rotationBy(360).withEndAction(runnable).setDuration(10000).setInterpolator(new LinearInterpolator()).start();
    }

    private void StopAnim() {
        circleImageView.animate().cancel();
    }

    private void AddSong() {
        songArrayList = new ArrayList<>();
        songArrayList.add(new Song("ABC Song", R.raw.abc_song));
        songArrayList.add(new Song("Baby Shark", R.raw.baby_shark));
        songArrayList.add(new Song("Bingo", R.raw.bingo_song));
        songArrayList.add(new Song("Five Little Duck", R.raw.five_little_ducks));
        songArrayList.add(new Song("Good Bye Song", R.raw.good_bye_song));
        songArrayList.add(new Song("Happy Birthday", R.raw.happy_birthday));
        songArrayList.add(new Song("Head Shoulders Kness An Toes", R.raw.head_shoulders_knees_an_toes));
        songArrayList.add(new Song("Hello Song", R.raw.hello_song));
        songArrayList.add(new Song("If You Are Happy", R.raw.if_you_are_happy));
        songArrayList.add(new Song("Jingle Bells", R.raw.jingle_bells));
        songArrayList.add(new Song("Old Macdonald Had A Farm", R.raw.old_macdonald_had_a_farm));
        songArrayList.add(new Song("Rain Rain Go Away", R.raw.rain_rain_go_away));
        songArrayList.add(new Song("Twinkle Twinkle Little Star", R.raw.twinkle_twinkle_little_star));
    }

    private void AnhXa() {
        toolbar = (Toolbar) findViewById(R.id.TB_LearnActivity);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvSongTime = (TextView) findViewById(R.id.tvSongTime);
        tvTotalTime = (TextView) findViewById(R.id.tvTotalTime);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        btnPrevious = (ImageButton) findViewById(R.id.bt_previous);
        btnPlay = (ImageButton) findViewById(R.id.bt_play);
        btnStop = (ImageButton) findViewById(R.id.bt_stop);
        btnNext = (ImageButton) findViewById(R.id.bt_next);
        circleImageView = (CircleImageView) findViewById(R.id.img_cd);
    }

    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    KhoiTaoMediaPlayer();
                }
                finish();
            }
        });
    }
}