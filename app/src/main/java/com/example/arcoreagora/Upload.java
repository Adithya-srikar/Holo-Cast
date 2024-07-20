package com.example.arcoreagora;


import static android.view.Gravity.apply;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class Upload extends AppCompatActivity {
    private static final int READ_REQUEST_CODE = 42;
    private Button selectModel;
    private Button selectThumbnail;
    private Button upload;
    private String currentSelection, modelName;
    private Uri modelUri = null, thumbnailUri = null;
    private Context currentContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

//        Toolbar toolbar = findViewById(R.id.app_toolbar);
//        setSupportActionBar(toolbar);

        selectModel = findViewById(R.id.upload_select_model);
        selectThumbnail = findViewById(R.id.upload_select_thumbnail);
        upload = findViewById(R.id.upload_model);

        currentContext = this;

        setButtonListeners();
    }

    private void setButtonListeners() {
        selectModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSelection = Constants.MODEL;
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });
        selectThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSelection = Constants.THUMBNAIL;
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, READ_REQUEST_CODE);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText modelNameEditText = (EditText) findViewById(R.id.modelname);
                modelName = modelNameEditText.getText().toString();

                if (modelUri == null) {
                    Toast.makeText(currentContext, "No model selected!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (thumbnailUri == null) {
                    Toast.makeText(currentContext, "No thumbnail selected!", Toast.LENGTH_SHORT).show();
                    return;
                }
                writeFile(modelUri);
                writeFile(thumbnailUri);
                EditText editTextModelName = findViewById(R.id.modelname);
                String modelName = editTextModelName.getText().toString();
                Toast.makeText(currentContext, "Model uploaded successfully!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Upload.this,teacher_main.class);
                intent.putExtra("model_name", modelName);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();
                if (currentSelection.equals(Constants.MODEL)) {
                    modelUri = uri;
                    selectModel.setBackgroundColor(Color.parseColor(Constants.COLOR_GREEN_UPLOAD));
                    selectModel.setText("Model selected");
                }
                else if (currentSelection.equals(Constants.THUMBNAIL)) {
                    thumbnailUri = uri;
                    selectThumbnail.setBackgroundColor(Color.parseColor(Constants.COLOR_GREEN_UPLOAD));
                    selectThumbnail.setText("Thumbnail selected");
                }
                Log.d("Info: ", "Uri: " + uri.toString());
                Log.d("Info: ", "MIME-type: " + getContentResolver().getType(uri));
            }
        }
    }

    private void writeFile(Uri uri) {
        if (isExternalStorageWritable()) {
            File file = generateFile(uri);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(getBytesFromUri(uri));
                fos.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else {
            Toast.makeText(currentContext, "Cannot write to storage", Toast.LENGTH_LONG).show();
            Log.e(Constants.ERROR_TAG, "Cannot write to storage ..");
        }
    }

    private File generateFile(Uri uri) {
        File file = null;
        File modelDir = new File(Environment.getExternalStorageDirectory() + Constants.MODELS_DIR);
        File thumbnailDir = new File(Environment.getExternalStorageDirectory() + Constants.THUMBNAILS_DIR);
        if (!modelDir.isDirectory()) {
            modelDir.mkdirs();
        }
        if (!thumbnailDir.isDirectory()) {
            thumbnailDir.mkdirs();
        }
        String mimeType = getContentResolver().getType(uri);
        if (mimeType.contains("octet")) {
            file = new File(modelDir,  modelName + ".obj");
        }
        else if (mimeType.contains("jpeg")) {
            file = new File(thumbnailDir,  modelName + ".jpeg");
        }
        else if (mimeType.contains("jpg")) {
            file = new File(thumbnailDir,  modelName + ".jpg");
        }
        else if (mimeType.contains("png")) {
            file = new File(thumbnailDir,  modelName + ".png");
        }
        else if (mimeType.contains("gif")) {
            file = new File(thumbnailDir,  modelName + ".gif");
        }
        return file;
    }

    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private byte[] getBytesFromUri(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
}
