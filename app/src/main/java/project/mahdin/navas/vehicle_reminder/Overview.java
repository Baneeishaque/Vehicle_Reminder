package project.mahdin.navas.vehicle_reminder;

import java.util.Vector;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Overview extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.overview);
		
		DBController db = new DBController(this);
		
		ArrayAdapter adapter=new ArrayAdapter<String>(this,R.layout.listview,db.get_items());
		final ListView listview=(ListView) findViewById(R.id.listView1);
		
		listview.setAdapter(adapter);
		
		listview.setClickable(true);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					
					//Create the bundle
					Bundle bundle = new Bundle();
					//Add your data from getFactualResults method to bundle
					bundle.putString("registration_number", listview.getItemAtPosition(position).toString());
					//Add the bundle to the intent
					Intent target = new Intent(Overview.this, Information_View.class);
					target.putExtras(bundle);
					startActivity(target);	
			}

			
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.overview, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.menu_add_item) {
			Intent target = new Intent(this, Information_Addition.class);
			startActivity(target);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
