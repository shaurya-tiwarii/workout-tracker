package com.example.workouttracker;
import android.app.*; import android.os.*; import android.widget.*;

public class MainActivity extends Activity {
 DBHelper db; EditText exercise,sets,reps,weight;
 public void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);db=new DBHelper(this);
 exercise=findViewById(R.id.exercise);sets=findViewById(R.id.sets);reps=findViewById(R.id.reps);weight=findViewById(R.id.weight);
 findViewById(R.id.save).setOnClickListener(v->save());}
 void save(){if(exercise.getText().length()==0){exercise.setError("Required");return;}db.insert(exercise.getText().toString(),sets.getText().toString(),reps.getText().toString(),weight.getText().toString()); Toast.makeText(this,"Workout saved!",Toast.LENGTH_SHORT).show();exercise.setText("");sets.setText("");reps.setText("");weight.setText("");}
}
