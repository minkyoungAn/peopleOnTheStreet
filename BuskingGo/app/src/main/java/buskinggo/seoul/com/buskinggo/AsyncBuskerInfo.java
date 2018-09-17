package buskinggo.seoul.com.buskinggo;

import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class AsyncBuskerInfo extends AsyncTask<Integer, String, String> {
    AsyncListener asyncListener;

    public AsyncBuskerInfo(AsyncListener asyncListener){
        this.asyncListener = asyncListener;
    }
    @Override
    protected String doInBackground(Integer... params) {
        HttpURLConnection httpURLConnection = null;

        try {
            int userNo = params[0];

            int data;
            String link;

            data = userNo;
            link = "http://buskinggo.cafe24.com/" + "BuskerInfo.php";

            URL url = new URL(link);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
            wr.write(data); // data 보내기
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader
                    (httpURLConnection.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line); // 데이터 받기
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            httpURLConnection.disconnect();
        }
    }

    @Override
    protected void onPostExecute(String result) {   // 결과 처리부분
        try {
            BuskerDTO buskerDTO = null;
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("response");

            int count = 0;
            String buskerName, photo, mainPlace, genre, introduce;
            while (count < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(count);
                buskerName = object.getString("buskerName");
                photo = object.getString("photo");
                mainPlace = object.getString("mainPlace");
                genre = object.getString("genre");
                introduce = object.getString("introduce");
                buskerDTO = new BuskerDTO(buskerName, photo, mainPlace, genre, introduce);

                count++;
            }
            // ui 작업 리스너 호출
            asyncListener.taskComplete(buskerDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
