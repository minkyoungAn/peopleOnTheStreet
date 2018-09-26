package buskinggo.seoul.com.buskinggo.configure;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import buskinggo.seoul.com.buskinggo.MyApplication;
import buskinggo.seoul.com.buskinggo.R;
import buskinggo.seoul.com.buskinggo.utils.AsyncUploadPhoto;
import buskinggo.seoul.com.buskinggo.utils.PictureDialog;

public class RegisterBuskerActivity extends AppCompatActivity {

    private ArrayAdapter guAdapter;
    private ArrayAdapter genreAdapter;
    private Spinner guSpinner;
    private Spinner genreSpinner;
    private Button confirmBuskerButton;
//    private String buskerName;
//    private String buskerGu;
//    private String buskerGenre;
//    private String buskerIntroduction;
    private EditText buskerNameText;
    private EditText buskerIntroductionText;

    // image related
    private ImageButton GetImageFromGalleryButton;
    private ImageView ShowSelectedImage;
    private Bitmap FixBitmap;
    ByteArrayOutputStream byteArrayOutputStream;
    byte[] byteArray;
    String ConvertImage;
    private int GALLERY = 1, CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_busker);

        buskerNameText = findViewById(R.id.buskerNameText);
        buskerIntroductionText = findViewById(R.id.buskerIntroductionText);

        guSpinner = findViewById(R.id.guSpinner);
        guAdapter = ArrayAdapter.createFromResource(this, R.array.gus, android.R.layout.simple_spinner_dropdown_item);
        guSpinner.setAdapter(guAdapter);

        genreSpinner = findViewById(R.id.genreSpinner);
        genreAdapter = ArrayAdapter.createFromResource(this, R.array.genres, android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(genreAdapter);

        GetImageFromGalleryButton = findViewById(R.id.getImageFromGalleryButton);
        ShowSelectedImage = findViewById(R.id.showImageFromGalleryButton);
        GetImageFromGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PictureDialog pictureDialog = new PictureDialog();
                pictureDialog.showPictureDialog(RegisterBuskerActivity.this);
            }
        });

        confirmBuskerButton = findViewById(R.id.confirmBuskerButton);
        confirmBuskerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadToServer();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    FixBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    ShowSelectedImage.setImageBitmap(FixBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterBuskerActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            FixBitmap = (Bitmap) data.getExtras().get("data");
            ShowSelectedImage.setImageBitmap(FixBitmap);
        }
    }

    public void uploadToServer() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        FixBitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
        byteArray = byteArrayOutputStream.toByteArray();
        ConvertImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        String url = "http://buskinggo.cafe24.com/registerBusker.php";

        HashMap<String, String> extraData = new HashMap<>();
        extraData.put("buskerName", buskerNameText.getText().toString());
        extraData.put("buskerGu", guSpinner.getSelectedItem().toString());
        extraData.put("buskerGenre", genreSpinner.getSelectedItem().toString());
        extraData.put("buskerIntroduction", buskerIntroductionText.getText().toString());
        extraData.put("userNo", String.valueOf(MyApplication.userDTO.getUserNo()));

        AsyncUploadPhoto AsyncTaskUploadClassOBJ = new AsyncUploadPhoto(RegisterBuskerActivity.this, extraData);
        AsyncTaskUploadClassOBJ.execute(url, ConvertImage);
    }

}
