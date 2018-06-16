package project.mahdin.navas.vehicle_reminder;

import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Information_Addition extends Activity {

	DBController db = new DBController(this);
	EditText t1,t2,t3,t5,t7,t9;
	DatePicker d1,d2,d3,d4;
	Spinner s1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.information_addition);
		String[] category_list = { "Bike", "Car" };
		
		s1 = (Spinner) findViewById(R.id.spinner1);
		
		t1=(EditText) findViewById(R.id.editText1);
		t2=(EditText) findViewById(R.id.editText2);
		t3=(EditText) findViewById(R.id.editText3);
	
		d1=(DatePicker) findViewById(R.id.datePicker1);
		t5=(EditText) findViewById(R.id.editText5);

		d2=(DatePicker) findViewById(R.id.datePicker2);
		t7=(EditText) findViewById(R.id.editText7);

		d3=(DatePicker) findViewById(R.id.datePicker3);
		
		t9=(EditText) findViewById(R.id.editText9);

		d4=(DatePicker) findViewById(R.id.datePicker4);
		
		ArrayAdapter<String> category = new ArrayAdapter<String>(Information_Addition.this,
				android.R.layout.simple_spinner_item, category_list);
		category.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s1.setAdapter(category);
	}
	
	public void save(View v)
	{
		Vector queryValues=new Vector();
		queryValues.add(t1.getText());
		queryValues.add(t2.getText());
		queryValues.add(t3.getText());
		queryValues.add(d1.getDayOfMonth()+"-"+(d1.getMonth()+1)+"-"+d1.getYear());
		queryValues.add(t5.getText());
		queryValues.add(d2.getDayOfMonth()+"-"+(d2.getMonth()+1)+"-"+d2.getYear());
		queryValues.add(t7.getText());
		queryValues.add(d3.getDayOfMonth()+"-"+(d3.getMonth()+1)+"-"+d3.getYear());
		queryValues.add(s1.getSelectedItem().toString());
		queryValues.add(t9.getText());
		queryValues.add(d4.getDayOfMonth()+"-"+(d4.getMonth()+1)+"-"+d4.getYear());
		if((db.insertdb(queryValues))==-1)
		{
			Toast.makeText(getApplicationContext(), "Try Again...", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Toast.makeText(getApplicationContext(), "Item Added Successfully", Toast.LENGTH_SHORT).show();
			Intent target = new Intent(this, Overview.class);
			startActivity(target);
		}
	}

}
