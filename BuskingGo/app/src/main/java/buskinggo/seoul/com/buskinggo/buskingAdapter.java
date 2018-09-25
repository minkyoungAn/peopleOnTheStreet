package buskinggo.seoul.com.buskinggo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class buskingAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, ArrayList<String>>> arrayList;
    private int layout;
    private Context context;

    buskingAdapter(Context context, int layout, ArrayList<HashMap<String, ArrayList<String>>> arrayList) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayList = arrayList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        @SuppressLint("ViewHolder") View view = View.inflate(context, R.layout.busking_list_item, null);

        HashMap<String, ArrayList<String>> hashMap = arrayList.get(i);

        return view;
    }
}