package buskinggo.seoul.com.buskinggo;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuskingRegisterFragment extends Fragment {

    private String photo = "test";
    private String buskingDate;
    private String buskingTime;
    private String place = "test";
    private String introduce;


    public BuskingRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_busking_register, container, false);

        final Button dateButton = view.findViewById(R.id.date_button);
        final Button timeButton = view.findViewById(R.id.time_button);
        Button registerButton = view.findViewById(R.id.busking_register_button);
        final EditText introduceEditText = view.findViewById(R.id.edit_text_box);

        //dateButton, timeButton에 초기값을 주기 위해서 현재 날짜, 시간 정보 가져오기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat CurTimeFormat = new SimpleDateFormat("HH:mm", Locale.KOREA);
        String strCurDate = CurDateFormat.format(date);
        String strCurTime = CurTimeFormat.format(date);

        dateButton.setText(strCurDate);
        timeButton.setText(strCurTime);

        //일자 dialog
        final Calendar cal = Calendar.getInstance();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(container.getContext(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        String msg = String.valueOf(year)+"-"+addZero(String.valueOf(month))+"-"+addZero(String.valueOf(date));
                        Toast.makeText(container.getContext(), msg, Toast.LENGTH_SHORT).show();
                        dateButton.setText(msg);
                    }

                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
                dialog.getDatePicker().setMinDate(new Date().getTime()); //이전 날짜 선택 X
                dialog.show();
            }
        });

        //시간 dialog
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(container.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        String msg = addZero(String.valueOf(hour))+":"+addZero(String.valueOf(min));
                        Toast.makeText(container.getContext(), msg, Toast.LENGTH_SHORT).show();
                        timeButton.setText(msg);
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                dialog.show();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buskingDate = String.valueOf(dateButton.getText());
                buskingTime =  String.valueOf(timeButton.getText());
                introduce = String.valueOf(introduceEditText.getText());
                buskingRegister buskingRegister = new buskingRegister();
                buskingRegister.execute(photo, buskingDate, buskingTime, place, introduce);
            }
        });

        return view;
    }

    @SuppressLint("StaticFieldLeak")
    class buskingRegister extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.w("test", "test");
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection httpURLConnection = null;

            try {
                String Photo = params[0];
                String BuskingDate = params[1];
                String BuskingTime = params[2];
                String Place = params[3];
                String Introduce = params[4];
                Log.w("test", Photo+BuskingDate+BuskingTime+Place+Introduce);

                String data = URLEncoder.encode("Photo", "UTF-8") + "=" + URLEncoder.encode(Photo, "UTF-8");// UTF-8로  설정 실제로 string 상으로 봤을땐, tmsg="String" 요런식으로 설정 된다.
                data += "&" + URLEncoder.encode("BuskingDate", "UTF-8") + "=" + URLEncoder.encode(BuskingDate, "UTF-8");
                data += "&" + URLEncoder.encode("BuskingTime", "UTF-8") + "=" + URLEncoder.encode(BuskingTime, "UTF-8");
                data += "&" + URLEncoder.encode("Place", "UTF-8") + "=" + URLEncoder.encode(Place, "UTF-8");
                data += "&" + URLEncoder.encode("Introduce", "UTF-8") + "=" + URLEncoder.encode(Introduce, "UTF-8");
                //버스커 넘버도 넘겨야 함
                String link = "http://buskinggo.cafe24.com/BuskingRegister.php";

                URL url = new URL(link);

                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStreamWriter wr = new OutputStreamWriter(httpURLConnection.getOutputStream());
                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader
                        (httpURLConnection.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);//

                }

                httpURLConnection.disconnect();
                return sb.toString();
            } catch (Exception e) {

                assert httpURLConnection != null;
                httpURLConnection.disconnect();
                return "Exception Occure" + e.getMessage();
            }
        }
    }

    //초기값과 날짜 or 시간을 선택했을 때 형식을 같게하기 위하여 1자리수(1월,2월...)일 경우 앞에 0(01월, 02월...)을 붙여주는 메소드
    private String addZero(String string){

        if(string.length() == 1)
            string = "0"+string;

        return string;

    }
}
