package com.example.workouttracker;
import android.app.*; import android.os.*; import android.content.*; import android.database.sqlite.*; import android.database.Cursor; import android.widget.*; import java.text.*; import java.util.*;

public class MainActivity extends Activity {
 DB db; EditText exercise,sets,reps,weight; TextView history;
 public void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);db=new DB(this);
 exercise=findViewById(R.id.exercise);sets=findViewById(R.id.sets);reps=findViewById(R.id.reps);weight=findViewById(R.id.weight);history=findViewById(R.id.history);
 findViewById(R.id.save).setOnClickListener(v->save()); refresh();}
 void save(){if(exercise.getText().length()==0){exercise.setError("Required");return;}db.insert(exercise.getText().toString(),sets.getText().toString(),reps.getText().toString(),weight.getText().toString()); Toast.makeText(this,"Workout saved",Toast.LENGTH_SHORT).show();exercise.setText("");sets.setText("");reps.setText("");weight.setText("");refresh();}
 void refresh(){Cursor c=db.all();StringBuilder s=new StringBuilder("Workout History\n\n");while(c.moveToNext())s.append(c.getString(1)).append(" - ").append(c.getString(2)).append(" sets x ").append(c.getString(3)).append(" reps @ ").append(c.getString(4)).append(" kg\n").append(c.getString(5)).append("\n\n");history.setText(s.toString());c.close();}
 // on-device db
 static class DB extends SQLiteOpenHelper{
  DB(Context c){super(c,"workouts.db",null,1);} public void onCreate(SQLiteDatabase d){d.execSQL("CREATE TABLE workouts(id INTEGER PRIMARY KEY AUTOINCREMENT,exercise TEXT,sets TEXT,reps TEXT,weight TEXT,date TEXT)");}
  public void onUpgrade(SQLiteDatabase d,int o,int n){d.execSQL("DROP TABLE IF EXISTS workouts");onCreate(d);}
  void insert(String e,String s,String r,String w){getWritableDatabase().execSQL("INSERT INTO workouts(exercise,sets,reps,weight,date) VALUES(?,?,?,?,?)",new Object[]{e,s,r,w,new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault()).format(new Date())});}
  Cursor all(){return getReadableDatabase().rawQuery("SELECT * FROM workouts ORDER BY id DESC",null);}
 }
}
