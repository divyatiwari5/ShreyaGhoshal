package com.example.divya.sgg;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import static com.example.divya.sgg.R.id.cancel_action;
import static com.example.divya.sgg.R.id.fab;
import static com.example.divya.sgg.R.id.fab1;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ShareActionProvider mShareActionProvider;
    WebView web;
    FrameLayout layoutParams;
    private Boolean isFabOpen = false;
    FloatingActionButton fab1, fab2, fab3, fab;
    private Animation show_fab1, hide_fab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         show_fab1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_show);
         hide_fab1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab1_hide);



        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab = (FloatingActionButton) findViewById(R.id.fab);

/*        fab.setOnClickListener((View.OnClickListener) this);
        fab1.setOnClickListener((View.OnClickListener) this);
        fab2.setOnClickListener((View.OnClickListener) this);
        fab3.setOnClickListener((View.OnClickListener) this);
        */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                    case R.id.fab:
                        animateFab();
                        break;
                    case R.id.fab1:
                        Log.d("FB", "Fab1");
                        break;
                    case R.id.fab2:
                        Log.d("Twitter", "Fab2");
                        break;
                    case R.id.fab3:
                        Log.d("Insta", "Fab3");
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        // web = (WebView) findViewById(R.id.webview);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        if (id == R.id.share) {
            //  MenuItem item = menu.findItem(R.id.share);
            mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
            Intent i = new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            String sharebody = "Content body";
            i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share subject");
            i.putExtra(android.content.Intent.EXTRA_TEXT, sharebody);

            startActivity(Intent.createChooser(i, "share via"));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            Intent i = new Intent(this, playview.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            Intent i = new Intent(this, Gallery.class);

            startActivity(i);

        } else if (id == R.id.nav_slideshow) {


            Intent i = new Intent(this, web.class);
            startActivity(i);


        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


public void animateFab() {
        if (isFabOpen) {
            fab1.startAnimation(hide_fab1);
            fab2.startAnimation(hide_fab1);
            fab3.startAnimation(hide_fab1);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            isFabOpen = false;
            Log.d("close", "close");
        } else {
            fab1.startAnimation(show_fab1);
            fab2.startAnimation(show_fab1);
            fab3.startAnimation(show_fab1);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            isFabOpen = true;
            Log.d("open", "open");


        }
    }


      /*  private void goToUrl (String url) {
            Uri uriUrl = Uri.parse(url);
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        }
        */

}

