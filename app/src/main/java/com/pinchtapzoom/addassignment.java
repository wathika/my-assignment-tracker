package com.pinchtapzoom;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class addassignment extends Activity {

	DBAdapter db = new DBAdapter(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		
	}
	public void addAssignment(View v)
	{
		Log.d("test", "adding");
	   	//get data from form
	   	EditText nameTxt = (EditText)findViewById(R.id.editTitle);
	   	EditText dateTxt = (EditText)findViewById(R.id.editDuedate);
	   	EditText courseTxt = (EditText)findViewById(R.id.editCourse);
	   	EditText notesTxt = (EditText)findViewById(R.id.editNotes);

	       db.open();
			populateListViewFromDB();
		
	       long id = db.insertRecord(nameTxt.getText().toString(), dateTxt.getText().toString(), courseTxt.getText().toString(), notesTxt.getText().toString());        
	       db.close();
	       
	    nameTxt.setText("");
	    dateTxt.setText("");
	    courseTxt.setText("");
	    notesTxt.setText("");
        Toast.makeText(addassignment.this,"Assignment Added", Toast.LENGTH_LONG).show();  

	}

	private void populateListViewFromDB() {
		Cursor cursor = db.getAllRecords();


		//set up mapping from cursor to view fields
		String[] fromFieldNames = 	new String[]
				{DBAdapter.KEY_TITLE};
		int[] toViewId = new int[]
				{R.id.assignment_name};


		//create adapter to map columns of the db into the ui
		SimpleCursorAdapter myCursorAdapter =
				new SimpleCursorAdapter(
						this,    //context
						R.layout.assignment_item,     //Row layout template
						cursor                      //cursor
				);



		//set the adapter for the listview
		ListView myList = (ListView)findViewById(R.id.listViewFromDb);
		myList.setAdapter(myCursorAdapter);
	}

	public void viewAssignments(View v)
	{
	Intent i = new Intent(this, AssignmentTracker.class);
	startActivity(i);
	}

}

