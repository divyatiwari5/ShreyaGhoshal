package com.example.divya.sgg;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.divya.CircularSeekbar;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.concurrent.TimeUnit;


public class Player extends AppCompatActivity {


    CircularSeekbar circularSeekbar;
    private int forwardtime = 5000;
    private int backwardtime = 5000;
    public static int oneTimeOnly = 0;
    int count =0;

    public static ImageButton pauseb;
    public static ImageButton playb;
  //  final ImageView imageview = (ImageView) findViewById(R.id.playbutton);

    private double startTime = 0;
    private double finalTime = 0;
    private Handler myHandler = new Handler();
    private SeekBar seekbar;

    private Runnable UpdateSongTime;
    private TextView text1, text2;
    private ShareActionProvider mShareActionProvider;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }


      // progressBar = (SeekBar) findViewById(R.id.circs);

        circularSeekbar = (CircularSeekbar) findViewById(R.id.circlesb);
        circularSeekbar.getProgress();
       // circularSeekbar.setProgress(50);
        playb = (ImageButton) findViewById(R.id.playbutton);
        pauseb = (ImageButton) findViewById(R.id.pausebutton);

        final ImageButton backb = (ImageButton) findViewById(R.id.prev);
        final ImageButton forwb = (ImageButton) findViewById(R.id.skip);

        seekbar = (SeekBar) findViewById(R.id.seek);
        seekbar.setClickable(true);

        text1 = (TextView) findViewById(R.id.textView);
        // text2 = (TextView) findViewById(R.id.textView2);

        text1.setText(String.format("%d:%d ",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime)))
        );


       /* text2.setText(String.format("%d ",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime)- TimeUnit.MILLISECONDS.toMinutes((long) finalTime))
        );
        */


        UpdateSongTime = new Runnable() {
            @Override
            public void run() {
                startTime = playview.mp.getCurrentPosition();
                text1.setText(String.format("%d:%d",
                        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
                        TimeUnit.MILLISECONDS.toSeconds((long) startTime) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));

                seekbar.setProgress((int) startTime);
                circularSeekbar.setProgress((int) startTime);
                myHandler.postDelayed(this, 100);
            }
        };

        myHandler.postDelayed(UpdateSongTime, 100);

        playb.setVisibility(View.INVISIBLE);


        //  seekbar.setProgress((int) startTime);
        finalTime = playview.mp.getDuration();
        startTime = playview.mp.getCurrentPosition();


        if (oneTimeOnly == 0) {
            seekbar.setMax((int) finalTime);
            circularSeekbar.setMax((int) finalTime);
            oneTimeOnly = 1;
        }


        playb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                pauseb.setVisibility(View.VISIBLE);

                playb.setVisibility(View.INVISIBLE);

                playview.mp.start();

            }

            // pauseb.setEnabled(true);
            // playb.setEnabled(false);
            ///playb.setBackgroundResource(R.drawable.stop);


        });


        pauseb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseb.setVisibility(View.INVISIBLE);
                playb.setVisibility(View.VISIBLE);
                playview.mp.pause();
                // pauseb.setBackgroundResource(R.drawable.play);

                                         /* pauseb.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {

                                                  pauseb.setBackgroundResource(R.drawable.stop);


                                              }
                                          });
                                          */
            }
        });

        forwb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;

                if ((temp + forwardtime) <= finalTime) {
                    startTime = startTime + forwardtime;
                    playview.mp.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(), "You have jumped forward 5sec", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Cannot jump", Toast.LENGTH_LONG).show();
                }


            }
        });

        backb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;

                if ((temp - backwardtime) > 0) {
                    startTime = startTime - backwardtime;
                    playview.mp.seekTo((int) startTime);
                    Toast.makeText(getApplicationContext(), "Jumbed backward 5sec", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Cannot Jumbed", Toast.LENGTH_SHORT).show();
                }
            }


        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id== R.id.share){
            //  MenuItem item = menu.findItem(R.id.share);
            mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
            String sharepath = Environment.getExternalStorageDirectory().getPath()+"Soundboard/Ringtones/custom_ringtone.ogg";
            Uri uri = Uri.parse(sharepath);
            Intent i = new Intent(android.content.Intent.ACTION_SEND);
            i.setType("audio/mp3");
            MediaPlayer sharebody = playview.mp;
            i.putExtra(Intent.EXTRA_STREAM, uri);
           // i.putExtra(Intent.ACTION_MEDIA_SHARED, (Parcelable) sharebody);

            startActivity(Intent.createChooser(i, "Share via"));        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Player Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}