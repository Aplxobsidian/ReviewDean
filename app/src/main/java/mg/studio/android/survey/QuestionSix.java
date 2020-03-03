package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QuestionSix extends AppCompatActivity {
    Button btn_next;
    EditText edt_answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_six);
        edt_answer=findViewById(R.id.edt_answer_q6);
        btn_next=findViewById(R.id.btn_next_q6);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_answer.length()==0){
                    return;
                }
                Intent intent=new Intent(QuestionSix.this, QuestionSeven.class);
                Bundle bundle=getIntent().getExtras();
                bundle.putString("ques06_answer", edt_answer.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
