package com.momoda.testlistview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by qiukangbo on 14-10-15.
 */
public class ListViewAdapter extends BaseAdapter {
    private List<String> items;
    private LayoutInflater inflater;

    public ListViewAdapter(Context context, List<String> items) {
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_item, null);
        }
//        TextView text = (TextView) view.findViewById(R.id.list_item_text);
//        text.setText(items.get(position));
//        return view;

        String imageUrl = items.get(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.one_image_item);
        ImageLoader.getInstance().displayImage(imageUrl,imageView);

        return view;
    }

    /**
     * 添加列表项
     * @param item
     */
    public void addItem(String item) {
        items.add(item);
    }


}
