package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;

public class Report extends AppCompatActivity {
    Button btn_next;
    Button btn_save;
    Bundle bundle;
    TextView tv_answer_q1;
    TextView tv_answer_q2;
    TextView tv_answer_q3;
    TextView tv_answer_q4;
    TextView tv_answer_q5;
    TextView tv_answer_q6;
    TextView tv_answer_q7;
    TextView tv_answer_q8;
    TextView tv_answer_q9;
    TextView tv_answer_q10;
    TextView tv_answer_q11;
    TextView tv_answer_q12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        tv_answer_q1=findViewById(R.id.tv_answ_q1);
        tv_answer_q2=findViewById(R.id.tv_answ_q2);
        tv_answer_q3=findViewById(R.id.tv_answ_q3);
        tv_answer_q4=findViewById(R.id.tv_answ_q4);
        tv_answer_q5=findViewById(R.id.tv_answ_q5);
        tv_answer_q6=findViewById(R.id.tv_answ_q6);
        tv_answer_q7=findViewById(R.id.tv_answ_q7);
        tv_answer_q8=findViewById(R.id.tv_answ_q8);
        tv_answer_q9=findViewById(R.id.tv_answ_q9);
        tv_answer_q10=findViewById(R.id.tv_answ_q10);
        tv_answer_q11=findViewById(R.id.tv_answ_q11);
        tv_answer_q12=findViewById(R.id.tv_answ_q12);

        bundle=getIntent().getExtras();

        tv_answer_q1.setText(bundle.getString("ques01_answer"));
        tv_answer_q2.setText(bundle.getString("ques02_answer"));
        tv_answer_q3.setText(bundle.getString("ques03_answer"));
        tv_answer_q4.setText(bundle.getString("ques04_answer"));
        tv_answer_q5.setText(bundle.getString("ques05_answer"));
        tv_answer_q6.setText(bundle.getString("ques06_answer"));
        tv_answer_q7.setText(bundle.getString("ques07_answer"));
        tv_answer_q8.setText(bundle.getString("ques08_answer"));
        tv_answer_q9.setText(bundle.getString("ques09_answer"));
        tv_answer_q10.setText(bundle.getString("ques10_answer"));
        tv_answer_q11.setText(bundle.getString("ques11_answer"));
        tv_answer_q12.setText(bundle.getString("ques12_answer"));

        btn_next=findViewById(R.id.btn_finish);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Report.this, Finish.class);
                startActivity(intent);
            }
        });
        btn_save=findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Report.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(getApplicationContext(),"Request Writing Permission",Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }
                int i=0;
                String num;
                String str;
                str="{";
                for(;i<12;i++){
                    if(i<9){
                        num="0"+(i+1);
                    }
                    else{
                        num=String.valueOf((i+1));
                    }
                    str+=("ques"+num+"_answer:'"+bundle.getString("ques"+num+"_answer")+"'");
                    if(i<11)
                        str+=",";
                }
                str+="}";

                saveFileToApp(str);
                saveFile(str);

            }
        });

    }
    public void saveFileToApp(String msg){
        FileOutputStream fout;
        File save_file;
        String dir=""+getFilesDir();
        int i=1;
        String str="saveData"+i+".json";
        save_file=new File(dir,str);
        while(save_file.exists()){
            i++;
            str="saveData"+i+".json";
            save_file=new File(dir,str);
        }
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
        try {
            fout=getApplicationContext().openFileOutput(str,MODE_PRIVATE);
            fout.write(msg.getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(getApplicationContext(),"Successful recorded.\n"+"Recorded in App:"+getFilesDir(),Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Record failed!",Toast.LENGTH_LONG).show();
            return;
        }
    }
    public void saveFile(String msg){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_UNKNOWN)){
            Toast.makeText(getApplicationContext(),"No sdcard",Toast.LENGTH_LONG).show();
            return;
        }
        File sd_file;
        File save_file;
        int i=1;
        String str="saveData"+i+".json";
        sd_file= getExternalFilesDir(null);
        save_file=new File(sd_file,str);
        while(save_file.exists()){
            i++;
            str="saveData"+i+".json";
            save_file=new File(sd_file,str);
        }

        try {
            FileOutputStream fout=new FileOutputStream(save_file);
            fout.write(msg.getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(getApplicationContext(),"Saving succeeded.\n"+"Saved in sdCard:"+save_file,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Saving failed",Toast.LENGTH_LONG).show();
        }
    }
}
