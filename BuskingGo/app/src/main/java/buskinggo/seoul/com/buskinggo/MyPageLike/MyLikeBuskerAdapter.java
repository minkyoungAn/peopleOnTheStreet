package buskinggo.seoul.com.buskinggo.MyPageLike;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.io.File;
import java.util.ArrayList;

import buskinggo.seoul.com.buskinggo.AsyncPhotoListener;
import buskinggo.seoul.com.buskinggo.BuskerDTO;
import buskinggo.seoul.com.buskinggo.utils.PhotoResizing;
import buskinggo.seoul.com.buskinggo.R;
import buskinggo.seoul.com.buskinggo.utils.AsyncPhoto;

public class MyLikeBuskerAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<BuskerDTO> buskerList;
    private int mLayout;
    private MyLikeBuskerViewHolder viewHolder;


    MyLikeBuskerAdapter(Context context, int layout, ArrayList<BuskerDTO> buskerList) {
        this.mContext = context;
        this.mLayout = layout;
        this.buskerList = buskerList;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return buskerList.size();
    }

    @Override
    public Object getItem(int position) {
        return buskerList.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = mInflater.inflate(mLayout, viewGroup, false);

            viewHolder = new MyLikeBuskerViewHolder();
            viewHolder.photo = convertView.findViewById(R.id.like_busker_photo_item);
            viewHolder.name = convertView.findViewById(R.id.like_busker_name_item);
            viewHolder.genre = convertView.findViewById(R.id.like_busker_genre_item);

            convertView.setTag(viewHolder);

        }else{

            viewHolder = (MyLikeBuskerViewHolder) convertView.getTag();
        }

        String photo = buskerList.get(position).getPhoto();
        String name = buskerList.get(position).getBuskerName();
        String genre = buskerList.get(position).getGenre();

        if(photo.equals("null") || name.equals("null") || genre.equals("null")){
            bitmapImgDownload(photo);
            return convertView;
        }else{
            viewHolder.name.setText(name);
            viewHolder.genre.setText(genre);
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
                    Bitmap bitmap = new PhotoResizing().loadPictureWithResize(file, 160);
                    viewHolder.photo.setImageBitmap(bitmap);
                }
            }
        });
        asyncBuskerPhoto.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, photo, "buskerPhoto");

    }


}

