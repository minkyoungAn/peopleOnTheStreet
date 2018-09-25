package buskinggo.seoul.com.buskinggo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TodayBuskingListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private JSONArray jsonArray;
    private int layout;
    private JSONObject jsonObject;
    private Context context;

    public TodayBuskingListAdapter(Context context, JSONArray jsonArray, int layout) {
        this.inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.jsonArray = jsonArray;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int i) {
        try {
            return jsonArray.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(layout, viewGroup, false);
        }

        jsonObject = new JSONObject();

        try {
            jsonObject = jsonArray.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String Photo;
        String place = null;
        String time = null;

        try {
            Photo = String.valueOf(jsonObject.get("Photo"));
            place = String.valueOf(jsonObject.get("Place"));
            time = String.valueOf(jsonObject.get("BuskingTime"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView placeTextview = view.findViewById(R.id.textViewBuskingPlace);
        TextView timeTextview = view.findViewById(R.id.textViewBuskingTime);

        placeTextview.setText(place);
        timeTextview.setText(time);

        return view;
    }
}
