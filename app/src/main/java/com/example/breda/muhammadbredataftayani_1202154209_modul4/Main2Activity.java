package com.example.breda.muhammadbredataftayani_1202154209_modul4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {
    EditText input;
    Button submit;
    ImageView images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        input=(EditText)findViewById(R.id.edit);
        images=(ImageView)findViewById(R.id.image);
        submit=(Button)findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url=input.getText().toString();
                new ImageAsync().execute(url);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();

        inflater.inflate(R.menu.menu_utama,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_name :
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.cari_Gambar :
                Intent intents=new Intent(this,Main2Activity.class);
                startActivity(intents);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public class ImageAsync extends AsyncTask<String,String,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            String file=strings[0];
            Bitmap bitmap=null;
            try{
                InputStream stream=new URL(file).openStream();
                bitmap= BitmapFactory.decodeStream(stream);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            images.setImageBitmap(bitmap);
        }
    }
}
