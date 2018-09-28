package buskinggo.seoul.com.buskinggo.MyPagePast;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.File;
import java.util.ArrayList;

import buskinggo.seoul.com.buskinggo.utils.AsyncPhotoListener;
import buskinggo.seoul.com.buskinggo.dto.BuskingDTO;
import buskinggo.seoul.com.buskinggo.utils.PhotoResizing;
import buskinggo.seoul.com.buskinggo.R;
import buskinggo.seoul.com.buskinggo.utils.AsyncPhoto;

public class MyPastBuskingAdapter extends BaseAdapter {

    private MyPastBuskingViewHolder viewHolder;
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<BuskingDTO> buskingList;
    private int mLayout;


    MyPastBuskingAdapter(Context context, int layout, ArrayList<BuskingDTO> buskingList) {
        this.mContext = context;
        this.mLayout = layout;
        this.buskingList = buskingList;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return buskingList.size();
    }

    @Override
    public Object getItem(int position) {
        return buskingList.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            convertView = mInflater.inflate(mLayout, viewGroup, false);

            viewHolder = new MyPastBuskingViewHolder();
            viewHolder.name = convertView.findViewById(R.id.past_name_item);
            viewHolder.place = convertView.findViewById(R.id.past_place_item);
            viewHolder.date = convertView.findViewById(R.id.past_date_item);
            viewHolder.time = convertView.findViewById(R.id.past_time_item);
            viewHolder.photo = convertView.findViewById(R.id.past_photo_item);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (MyPastBuskingViewHolder) convertView.getTag();
        }

        String name = buskingList.get(position).getBuskerName();
        String date = buskingList.get(position).getBuskingDate();
        String place = buskingList.get(position).getPlace();
        String time = buskingList.get(position).getBuskingTime();
        String photo = buskingList.get(position).getPhoto();
        time = time.substring(0, 5);
        String addr[] = place.split(" "); // 구, 동
        if(addr.length > 2){
            place = addr[1] +" "+ addr[2];
        } else if(addr.length == 1){
            place = addr[1];
        }

        //|| photo.equals("null")
        if (date.equals("null") || place.equals("null") || time.equals("null") || name.equals("null") ) {
            bitmapImgDownload(photo);
            return convertView;

        } else {
            viewHolder.date.setText(date);
            viewHolder.time.setText(time);
            viewHolder.place.setText(place);
            viewHolder.name.setText(name);
            bitmapImgDownload(photo);
        }

        return convertView;
    }

    private void bitmapImgDownload(String photo) {
        // 이미지 다운로드
        AsyncPhoto asyncBuskerPhoto = new AsyncPhoto(new AsyncPhotoListener() {
            @Override
            public void taskComplete(File file) {
                if(file != null){
                    Bitmap bitmap = new PhotoResizing().loadPictureWithResize(file, 150);
                    viewHolder.photo.setImageBitmap(bitmap);
                }
            }
        });
        asyncBuskerPhoto.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, photo, "buskingPhoto");

    }
}


