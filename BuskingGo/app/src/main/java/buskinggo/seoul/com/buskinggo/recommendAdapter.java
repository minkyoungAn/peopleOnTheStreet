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

public class RecommendAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private JSONArray jsonArray;
    private int layout;
    private JSONObject jsonObject;
    private Context context;

    public RecommendAdapter(Context context, JSONArray jsonArray, int layout) {
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
        String name = null;

        try {
            Photo = String.valueOf(jsonObject.get("Photo"));
            place = String.valueOf(jsonObject.get("Place"));
            time = String.valueOf(jsonObject.get("BuskingTime"));
            name = String.valueOf(jsonObject.get("BuskerName"));
            time = time.substring(0,5);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView nameTextview = view.findViewById(R.id.busker_name);
        TextView placeTextview = view.findViewById(R.id.textViewBuskingPlace);
        TextView timeTextview = view.findViewById(R.id.textViewBuskingTime);

        nameTextview.setText(name);
        placeTextview.setText(place);
        timeTextview.setText(time);

        return view;
    }
}