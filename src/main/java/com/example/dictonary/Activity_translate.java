package com.example.dictonary;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

public class Activity_translate extends AppCompatActivity {

    EditText inputen;
    TextView bnoutput;
    ImageView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_translate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bnoutput=findViewById(R.id.textviewbangla);
        inputen=findViewById(R.id.edinput);
        button=findViewById(R.id.button);

        TranslatorOptions options = new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.ENGLISH)  // উৎস ভাষা (ইংরেজি)
                .setTargetLanguage(TranslateLanguage.BENGALI)  // লক্ষ্য ভাষা (বাংলা)
                .build();

        Translator englishToBanglaTranslator = Translation.getClient(options);

        DownloadConditions conditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();

        ProgressDialog progressDialog=new ProgressDialog(Activity_translate.this);
        progressDialog.setMessage("Loading please wait..");
        progressDialog.setTitle("Downloading Start");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();



        englishToBanglaTranslator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        
                       progressDialog.dismiss();
                        Toast.makeText(Activity_translate.this, "Downloading Completed", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Activity_translate.this, "Downloading Faild", Toast.LENGTH_SHORT).show();

                    }
                });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input=inputen.getText().toString();
                englishToBanglaTranslator.translate(input)
                        .addOnSuccessListener(translatedText -> {
                            // অনুবাদিত টেক্সট পাওয়া গেছে
                           bnoutput.setText(translatedText);
                        })
                        .addOnFailureListener(e -> {
                            // অনুবাদ ব্যর্থ হয়েছে
                            Log.e("MLKit", "Translation failed.", e);
                        });


            }
        });


    }
}