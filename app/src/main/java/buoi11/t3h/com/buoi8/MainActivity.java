package buoi11.t3h.com.buoi8;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_IMAGE = 1;
    private TextView tvWelcome;
    private EditText edtData;
    private Button btnCall;
    private Button btnLink;
    private Button btnGallery;
    private ImageView imPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        // lay du lieu tu intent
        Intent intent = getIntent();
        String userName = intent.getStringExtra(LoginActivity.KEY_USER_NAME);
        String password = intent.getStringExtra(LoginActivity.KEY_PASSWORD);
        tvWelcome.setText("Hi, " + userName + " - " + password);

        edtData = (EditText) findViewById(R.id.edtData);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnLink = (Button) findViewById(R.id.btnLink);
        btnGallery = (Button) findViewById(R.id.btnGallery);
        imPhoto = (ImageView) findViewById(R.id.imPhoto);
        btnCall.setOnClickListener(this);
        btnLink.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String data = edtData.getText().toString();
        Intent intent;

        switch (v.getId()) {
            case R.id.btnCall:
                intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:" + data);
                intent.setData(uri);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
                break;
            case R.id.btnLink:
                intent = new Intent(Intent.ACTION_VIEW);
                // www.dsajkdhkahdkas.dhsdhasjkd
                intent.setData(Uri.parse(data));
                startActivity(intent);
                break;
            case R.id.btnGallery:
                intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult
                        (Intent.createChooser(intent,"Select image"),REQUEST_IMAGE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE){
            if (resultCode == RESULT_OK){
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap
                            (getContentResolver(),data.getData());
                    imPhoto.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
