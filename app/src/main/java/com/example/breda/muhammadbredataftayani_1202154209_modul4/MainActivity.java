package com.example.breda.muhammadbredataftayani_1202154209_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[] items;
    ListView listView;
    Button buttonsyn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView=(ListView)findViewById(R.id.list);
        buttonsyn=(Button)findViewById(R.id.button1);

        buttonsyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsynTaskRun(MainActivity.this).execute();
            }
        });
        //mengambil nilai array
        items= getResources().getStringArray(R.array.array);
        //menyimpan nilai
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,
                new ArrayList<String>()
        );
        listView.setAdapter(adapter);
    }

    //pembuaatan menu
    @Override
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

    //inner class
    public class AsynTaskRun extends AsyncTask<Void,String,Void> {
        ArrayAdapter<String> arrays;
        ProgressDialog progress;
        int count=0;
        // constracktor untuk asynctask melakukan di aktivitas ini
        public AsynTaskRun(MainActivity activity){
            progress=new ProgressDialog(activity);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            //memunculkan list nama dengan kondisi
            for (String name: items) {
                publishProgress(name);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            //kondisi sebelum dimulai
            progress.setTitle("Loading Data");
            progress.setCancelable(false);
            progress.setProgress(0);
            progress.setMax(100);
            progress.setButton(DialogInterface.BUTTON_NEGATIVE, "cancel process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    AsynTaskRun.this.cancel(true);
                    progress.dismiss();
                }
            });
            progress.show();
            arrays=(ArrayAdapter<String>)listView.getAdapter();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //untuk memasukan setiap item pada backround
            arrays.add(values[0]);
            int process = count++;
            progress.setProgress(process);
            progress.setMessage(process+"%");

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progress.dismiss();
            Toast.makeText(MainActivity.this, "asyn completed", Toast.LENGTH_SHORT).show();
        }
    }
}
