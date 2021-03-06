package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class QuestionFour extends AppCompatActivity {
    Button btn_next;
    RadioGroup radgrp_select;
    RadioButton radbtn_select;
    boolean checked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_four);
        radgrp_select=findViewById(R.id.rad_select_q4);
        btn_next=findViewById(R.id.btn_next_q4);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checked){
                    return;
                }
                Intent intent=new Intent(QuestionFour.this, QuestionFive.class);
                radbtn_select=findViewById(radgrp_select.getCheckedRadioButtonId());
                Bundle bundle=getIntent().getExtras();
                bundle.putString("ques04_answer", radbtn_select.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        checked=false;
        radgrp_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checked=true;
            }
        });
    }
}
