package com.example.quizzapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class quiz1 extends AppCompatActivity {
    TextView tv;
    RadioGroup rg;
    RadioButton rb;
    RadioButton rb1;
    RadioButton rb2;
    Button bNext;
    static int x=1;
    String good=null;
    String RepCorrect="Leaning Tower of Pisa";
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DocumentReference document=db.collection("Questions").document("1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        rg=(RadioGroup) findViewById(R.id.rg);
        rb=(RadioButton) findViewById(R.id.rb);
        rb1=(RadioButton) findViewById(R.id.rb1);
        rb2=(RadioButton) findViewById(R.id.rb2);
        bNext=(Button) findViewById(R.id.bNext);
        tv=(TextView) findViewById(R.id.tvQ);






        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rg.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getApplicationContext(),"please choose an answer  !",Toast.LENGTH_SHORT).show();
                }
                else {
                    rb=(RadioButton) findViewById(rg.getCheckedRadioButtonId());

                    if(rb.getText().toString().equals(RepCorrect)){
                        global.score+=1;

                    }
                    Intent intent=new Intent(quiz1.this,quiz2.class);
                    intent.putExtra("score",global.score);
                    startActivity(intent);
                    //overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                    /*overridePendingTransition(R.anim.exit,R.anim.entry);
                    finish();*/
                }

            }
        });


        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    tv.setText(documentSnapshot.getString("Question"));
                    rb.setText(documentSnapshot.getString("first_answer"));
                    rb1.setText(documentSnapshot.getString("second_answer"));
                    rb2.setText(documentSnapshot.getString("third_answer"));
                    good=documentSnapshot.getString("good_answer");
                }
                else
                    Toast.makeText(quiz1.this,"Row not found",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(quiz1.this,"Failed to fetch data",Toast.LENGTH_SHORT).show();
            }
        });

    }

}