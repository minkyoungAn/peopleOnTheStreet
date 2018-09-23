package buskinggo.seoul.com.buskinggo;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/*
 *  버스커의 공연정보 가져오기
 * */
public class AsyncBuskingListByBusker extends AsyncTask<Integer, String, String> {
    private AsyncListener asyncListener;

    AsyncBuskingListByBusker(AsyncListener asyncListener){
        this.asyncListener = asyncListener;
    }
    @Override
    protected String doInBackground(Integer... params) {
        HttpURLConnection httpURLConnection = null;

        try {
            int buskerNo = params[1];

            String data;
            String link;

            data = URLEncoder.encode("buskerNo", "UTF-8") + "=" + buskerNo;
            link = "http://buskinggo.cafe24.com/" + "BuskerInfoLoad.php";

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
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }

    @Override
    protected void onPostExecute(String result) {   // 결과 처리부분
        try {
            BuskerDTO buskerDTO = null;
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("response");

            int count = 0;
            String place, buskingTime, buskingDate;
            ArrayList<BuskingDTO> buskingDTOS = new ArrayList<>();
            while (count < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(count);
                place = object.getString("place");
                buskingTime = object.getString("buskingTime");
                buskingDate = object.getString("buskingDate");

                BuskingDTO buskingDTO = new BuskingDTO(place, buskingDate, buskingTime);
                buskingDTOS.add(buskingDTO);

                count++;
            }

            // ui 작업 리스너 호출
            asyncListener.taskComplete(buskerDTO); // arraylist형으로 바꾸기
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
