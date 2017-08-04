package com.example.divya.sgg;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.divya.sgg.Player;
import com.example.divya.sgg.R;

import java.util.ArrayList;
import java.util.HashMap;

import static android.R.layout.simple_list_item_1;
import static android.R.layout.simple_list_item_2;

public class playview extends AppCompatActivity {

    private ListView mainlist;
    private ImageView imageView;
    public static MediaPlayer mp;

    public static final String[] listcontent = {"Deewani Mastani", "Chaar Kadam", "Wada Raha", "Hasi Ban Gye", "Tujhme Rab Dikhta Hai",
    "Mannipaaya", "Bade Ache Lagte Hai", "Aashiq Banaya Aapne", "Bariyan", "Jaadu Hai Nasha Hai", "Kabhi Jo Badal Barse", "Kaun Kenda Hai", "Mere Dholna",
    "O Rangrez", "Pal Mein Hi", "Rang Jo Lagiyo", "Teri Ore", "Sunn Raha Hai"};

    private final int[] resID = {R.raw.mastani, R.raw.chaarkadam, R.raw.wadaraha, R.raw.hasi, R.raw.rabdikhtah, R.raw.mannipaaya, R.raw.badeachelgteh,
    R.raw.aashiqbanaya, R.raw.bariyan, R.raw.jaaduhainasha, R.raw.kabhijobaadal, R.raw.kaunkendah, R.raw.meredholna, R.raw.orangrez, R.raw.palmeinhi,
    R.raw.rangjo, R.raw.teriore, R.raw.sunnrahah};

   /* public static final Integer[] images = {R.drawable.listicon, R.drawable.listicon, R.drawable.listicon, R.drawable.listicon, R.drawable.listicon, R.drawable.listicon, R.drawable.listicon,
            R.drawable.listicon, R.drawable.listicon, R.drawable.listicon, R.drawable.listicon, R.drawable.listicon, R.drawable.listicon, R.drawable.listicon, R.drawable.listicon,
            R.drawable.listicon, R.drawable.listicon, R.drawable.listicon};


*/

    //  public ArrayList<HashMap<String, String>> songlist = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        mp = new MediaPlayer();
        mainlist = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, simple_list_item_1, listcontent);
        mainlist.setAdapter(adapter);

    /*    imageView = (ImageView)findViewById(R.id.imageplay);
        ArrayAdapter adapter1 = new ArrayAdapter(this, simple_list_item_2, images);
        mainlist.setAdapter(adapter1);
        */

        mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                playSong(position);

            }
        });


    }




    public void playSong(int songIndex) {

        mp.reset();
        mp = MediaPlayer.create(getApplicationContext(), resID[songIndex]);
        mp.start();
        Player.oneTimeOnly =0;
        Intent i = new Intent(getApplicationContext(), Player.class);
        startActivity(i);






    }


   /* @Override
    public void onDestroy(){

    super.onDestroy();
        mp.release();
    }*/


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}





    /*    ArrayList<HashMap<String,String >> listdata = new ArrayList<HashMap<String, String>>();
        listdata.add(new HashMap<String, String>(R.raw.chaarkadam));
        listdata.add(new HashMap<String, String>(R.raw.mastani));
        listdata.add(new HashMap<String, String>(R.raw.wadaraha));

        for (int i=0; i<songlist.size(); i++)
        {
            HashMap<String, String> song = songlist.get(i);
            listdata.add(song);
        }

        ListAdapter adapter = new SimpleAdapter(this, songlist,
                R.layout.playlist, new String[] { "songTitle" }, new int[] {
                R.id.songtitle });

       // setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int songindex = position;
                Intent in = new Intent(getApplicationContext(), MediaPlayer.class);
                in.putExtra("songindex", songindex);
                setResult(100, in);
                finish();

            }
        });

    }

    public ListView getListView() {
        return getListView();
    }
}

*/