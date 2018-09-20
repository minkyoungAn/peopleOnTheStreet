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

/*
*  좋아요한 버스커 정보
*  내가 좋아요한 버스커/ 버스커 좋아요 수
* */
public class AsyncLikeBusker extends AsyncTask<Integer, String, String> {
    private AsyncListener asyncListener;

    AsyncLikeBusker(AsyncListener asyncListener){
        this.asyncListener = asyncListener;
    }
    @Override
    protected String doInBackground(Integer... params) {
        HttpURLConnection httpURLConnection = null;

        try {
            int userNo = params[0];
            int buskerNo = params[1];

            String data;
            String link;

            data = URLEncoder.encode("userNo", "UTF-8") + "=" + userNo;
            data += "&" + URLEncoder.encode("buskerNo", "UTF-8") + "=" + buskerNo;
            link = "http://buskinggo.cafe24.com/" + "LikeBusker.php";

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
            int likeSum, myLike;
            while (count < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(count);
                likeSum = object.getInt("likeSum");
                myLike = object.getInt("myLike");
                buskerDTO = new BuskerDTO(likeSum, myLike);

                count++;
            }

            // ui 작업 리스너 호출
            asyncListener.taskComplete(buskerDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
