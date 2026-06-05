package com.example.workouttracker;
import android.content.Context; import android.database.Cursor; import android.database.sqlite.SQLiteDatabase; import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat; import java.util.Date; import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper {
 DBHelper(Context c){super(c,"workouts.db",null,1);}
 public void onCreate(SQLiteDatabase d){d.execSQL("CREATE TABLE workouts(id INTEGER PRIMARY KEY AUTOINCREMENT,exercise TEXT,sets TEXT,reps TEXT,weight TEXT,date TEXT)");}
 public void onUpgrade(SQLiteDatabase d,int o,int n){d.execSQL("DROP TABLE IF EXISTS workouts");onCreate(d);}
 void insert(String e,String s,String r,String w){getWritableDatabase().execSQL("INSERT INTO workouts(exercise,sets,reps,weight,date) VALUES(?,?,?,?,?)",new Object[]{e,s,r,w,new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.getDefault()).format(new Date())});}
 Cursor all(){return getReadableDatabase().rawQuery("SELECT * FROM workouts ORDER BY id DESC",null);}
}
