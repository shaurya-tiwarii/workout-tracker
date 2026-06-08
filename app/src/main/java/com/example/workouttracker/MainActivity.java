package com.example.workouttracker;
import android.app.*; import android.os.*; import android.database.Cursor; import android.widget.*; import java.util.*;

public class MainActivity extends Activity {
 DBHelper db; EditText exercise,sets,reps,weight,date; TextView history;
 public void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);db=new DBHelper(this);
 exercise=findViewById(R.id.exercise);sets=findViewById(R.id.sets);reps=findViewById(R.id.reps);weight=findViewById(R.id.weight);date=findViewById(R.id.date);history=findViewById(R.id.history);
 // date picker
date.setOnClickListener(v->{Calendar c=Calendar.getInstance();new DatePickerDialog(this,(view,y,m,d)->date.setText(String.format("%04d-%02d-%02d",y,m+1,d)),c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();});
 findViewById(R.id.save).setOnClickListener(v->save()); refresh();}
 void save(){if(exercise.getText().length()==0){exercise.setError("Required");return;}db.insert(exercise.getText().toString(),sets.getText().toString(),reps.getText().toString(),weight.getText().toString()); Toast.makeText(this,"Workout saved",Toast.LENGTH_SHORT).show();exercise.setText("");sets.setText("");reps.setText("");weight.setText("");date.setText("");refresh();}
 void refresh(){Cursor c=db.all();StringBuilder s=new StringBuilder("Workout History\n\n");while(c.moveToNext())s.append(c.getString(1)).append(" — ").append(c.getString(2)).append(" sets × ").append(c.getString(3)).append(" reps @ ").append(c.getString(4)).append(" kg\n").append(c.getString(5)).append("\n\n");history.setText(s.toString());c.close();}
}
