package project.mahdin.navas.vehicle_reminder;

import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Information_View extends Activity {

	TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
	DBController db = new DBController(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.information_view);
		
		t1=(TextView)findViewById(R.id.textView1);
		t2=(TextView)findViewById(R.id.textView2);
		t3=(TextView)findViewById(R.id.textView3);
		t4=(TextView)findViewById(R.id.textView4);
		t5=(TextView)findViewById(R.id.textView5);
		t6=(TextView)findViewById(R.id.textView6);
		t7=(TextView)findViewById(R.id.textView7);
		t8=(TextView)findViewById(R.id.textView8);
		t9=(TextView)findViewById(R.id.textView9);
		t10=(TextView)findViewById(R.id.textView10);

		
		Bundle bundle = getIntent().getExtras();
		
		Vector v=db.get_item(bundle.getString("registration_number"));
		
		t1.setText(v.elementAt(8).toString()+" : "+v.elementAt(0).toString());
		t2.setText(v.elementAt(1).toString());
		
		t3.setText("Insurance No. : "+v.elementAt(2).toString());
		t4.setText("Next Premium : "+v.elementAt(3).toString());
		
		t5.setText("Tax File No. : "+v.elementAt(4).toString());
		t6.setText("Next Pay : "+v.elementAt(5).toString());
		
		t7.setText("Smoke Certificate No. : "+v.elementAt(6).toString());
		t8.setText("Expiry Date : "+v.elementAt(7).toString());
		
		t9.setText("Driving Lisence No. : "+v.elementAt(9).toString());
		t10.setText("Expiry Date : "+v.elementAt(10).toString());
		
		
	}

	
}
