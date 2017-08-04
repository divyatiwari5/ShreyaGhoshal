package com.example.divya.sgg;

import android.content.Context;
import android.database.DataSetObserver;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

/**
 * Created by divya on 21/3/17.
 */

public class ImageAdapter extends Gallery implements ListAdapter {


        private Context context;
        public static int position;

        public static Integer[] mThumbids = {
                R.drawable.pic1, R.drawable.pic2,
                R.drawable.pic3, R.drawable.pic4,
                R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic10, R.drawable.pic9,
                R.drawable.pic8, R.drawable.pic11, R.drawable.pic12, R.drawable.pic13, R.drawable.pic14, R.drawable.pic15
                
        };

        public ImageAdapter(Context c) {
            context = c;
        }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
        public int getCount() {
            return mThumbids.length;
        }

        @Override
        public Object getItem(int position) {
            return mThumbids[position];
        }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(600, 600));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(18, 18, 18, 18);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbids[position]);
        return imageView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}

