package pnj.uas.aplikasiku;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import pnj.uas.aplikasiku.utils.Config;
import pnj.uas.aplikasiku.utils.VolleyMultipartRequest;

public class AddMakananActivity extends AppCompatActivity {
    EditText edit_Nama, edit_Harga, edit_Image;
    Button button_Simpan, actionImage;
    ImageView imageView;

    public static final int REQUEST_PERMISSION = 100;
    public static final int PICK_IMAGE_REQUEST = 1;
    public static final int CAMERA_REQUEST = 2;
    private String filepath;
    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_makanan);

        edit_Nama = findViewById(R.id.edit_Nama);
        edit_Harga = findViewById(R.id.edit_Harga);
        edit_Image = findViewById(R.id.edit_Image);

        button_Simpan = findViewById(R.id.button_Simpan);
        actionImage = findViewById(R.id.action_Image);
        imageView = findViewById(R.id.image);

        button_Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_Nama.getText().toString().length() > 0 &&
                        edit_Harga.getText().toString().length() > 0){
                    saveData();
                }
            }
        });

        actionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) &&
                        (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                        && (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {

                    ActivityCompat.requestPermissions(AddMakananActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_PERMISSION);

                } else {

                    AlertDialog.Builder alert = new AlertDialog.Builder(AddMakananActivity.this);
                    alert.setItems(new String[]{"Pilih Gambar", "Camera"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                showFileChooser();
                            } else {
                                showCamera();
                            }
                        }
                    });
                    alert.show();

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {

            Picasso.get().load(data.getData()).into(imageView);
            try {
                image = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

            } catch (IOException e) {
                Toast.makeText(AddMakananActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        } else if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            File file = new File(filepath);

            Uri photoURI = FileProvider.getUriForFile(this,
                    "pnj.uas.aplikasiku.fileprovider",
                    file);
            Picasso.get().load(photoURI).into(imageView);

            image = BitmapFactory.decodeFile(filepath);

        }
    }

    void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), PICK_IMAGE_REQUEST);
    }

    void showCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(AddMakananActivity.this, "" + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "pnj.uas.aplikasiku.fileprovider",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST);
            }
        }

    }

    private byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        filepath = image.getAbsolutePath();
        return image;
    }

    private void saveData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        VolleyMultipartRequest request = new VolleyMultipartRequest(Request.Method.POST,
                Config._ADD_MAKANAN,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject jsonObject = new JSONObject(new String(response.data));
                            Toast.makeText(AddMakananActivity.this, jsonObject.getString("message"),
                                    Toast.LENGTH_SHORT).show();

                            if (jsonObject.getString("status").equals("OK")) {
                                Toast.makeText(AddMakananActivity.this, jsonObject.getString("message"),
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        } catch (JSONException ex) {
                            Toast.makeText(AddMakananActivity.this, "" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddMakananActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("name", edit_Nama.getText().toString());
                parameter.put("price", edit_Harga.getText().toString());

                return parameter;
            }

            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                HashMap<String, DataPart> datas = new HashMap<>();
                datas.put("image", new DataPart(System.currentTimeMillis()+".jpeg", getFileDataFromDrawable(image)));
                return datas;
            }
        };
        requestQueue.add(request);
    }




}
