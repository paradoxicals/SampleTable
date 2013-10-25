package com.sample.table;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class SampleTableActivity extends Activity {
    /** Called when the activity is first created. */
	
	
	SQLiteDatabase database;
    private static String DBNAME = "sample.db";    
    private static String TABLE = "test";
    
    TableLayout tableLayout;
    TableRow row;
    TextView firstCol;
    TextView secondCol;
    TextView thirdCol;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tableLayout=(TableLayout)findViewById(R.id.tableLayout1);
        
        createDB();
        insertValues();
        displayDB();
    }
    
    
	private void displayDB() {
		// TODO Auto-generated method stub
		database=openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
		if(database!=null)
		{
			Cursor cursor=database.rawQuery("SELECT * FROM "+  TABLE, null);
			
			Integer index0=cursor.getColumnIndex("ID");
			Integer index1 = cursor.getColumnIndex("NAME");	
            Integer index2 = cursor.getColumnIndex("LOCATION");
            if(cursor.getCount()>0)
            {
            	cursor.moveToFirst();
            	do
            	{
            		row=new TableRow(this);
            	    row.setId(100);
            		row.setLayoutParams(new LayoutParams(
                            LayoutParams.FILL_PARENT,
                            LayoutParams.WRAP_CONTENT));  
            		
            		/*Setting up the first coloumn parameters*/
    				firstCol=new TextView(this);
    				firstCol.setText(cursor.getString(index0));
    				firstCol.setTextSize(16);
    				firstCol.setTextColor(Color.GREEN);
    				firstCol.setLayoutParams(new LayoutParams(
    	                    LayoutParams.FILL_PARENT,
    	                    LayoutParams.WRAP_CONTENT));
    				row.addView(firstCol); //adding coloumn to row

    				/*Setting up the second coloumn parameters*/			
    				secondCol=new TextView(this);
    				secondCol.setText(cursor.getString(index1));
    				secondCol.setTextColor(Color.YELLOW);
    				secondCol.setTextSize(16);
    				secondCol.setLayoutParams(new LayoutParams(
    	                    LayoutParams.FILL_PARENT,
    	                    LayoutParams.WRAP_CONTENT));
    				row.addView(secondCol); //adding coloumn to row
    				
    				/*Setting up the third coloumn parameters*/
    				thirdCol=new TextView(this);
    				thirdCol.setText(cursor.getString(index2));
    				thirdCol.setTextColor(Color.MAGENTA);
    				thirdCol.setTextSize(16);
    				thirdCol.setLayoutParams(new LayoutParams(
    	                    LayoutParams.WRAP_CONTENT,
    	                    LayoutParams.WRAP_CONTENT));
    				row.addView(thirdCol); //adding coloumn to row

    				/*Adding the row to the tablelayout*/
    				tableLayout.addView(row,new TableLayout.LayoutParams(
    	                    LayoutParams.FILL_PARENT,
    	                    LayoutParams.WRAP_CONTENT));
    				
            	}while(cursor.moveToNext());
            	database.close();
            }
            else
            {
            	Toast.makeText(getBaseContext(), "NOT AVAILABLE", Toast.LENGTH_LONG).show();
            }
		}
	}


	private void insertValues() {
		// TODO Auto-generated method stub
		try
		{
			database=openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
		    database.execSQL("INSERT INTO " + TABLE + "(ID,NAME, LOCATION) VALUES(1,'JACK','INDIA')");
            database.execSQL("INSERT INTO " + TABLE + "(ID,NAME, LOCATION) VALUES(2,'ARUN','USA')");
            database.execSQL("INSERT INTO " + TABLE + "(ID,NAME, LOCATION) VALUES(3,'SHEN','CHINA')");
            database.execSQL("INSERT INTO " + TABLE + "(ID,NAME, LOCATION) VALUES(4,'ALEN','EUROPE')");
            database.execSQL("INSERT INTO " + TABLE + "(ID,NAME, LOCATION) VALUES(5,'IMAN','SRILANKA')");
        	database.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return;

	}


	private void createDB() {
		// TODO Auto-generated method stub
		try
		{
			database=openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE, null);
			database.execSQL("CREATE TABLE IF  NOT EXISTS "+ TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NAME TEXT, LOCATION TEXT);");
			database.close();
		}
		catch(Exception e)
		{
			Log.e("Database Creation", "Error "+e.toString());
		}
	}
}