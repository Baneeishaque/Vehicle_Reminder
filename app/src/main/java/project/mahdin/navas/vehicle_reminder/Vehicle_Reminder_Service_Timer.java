package project.mahdin.navas.vehicle_reminder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class Vehicle_Reminder_Service_Timer extends Service {
    // constant
    public static final long NOTIFY_INTERVAL = 30 * 1000; // 10 seconds
    DBController db = new DBController(this);
    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();
    // timer handling
    private Timer mTimer = null;
 
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
 
    @Override
    public void onCreate() {
        // cancel if already existed
        if(mTimer != null) {
            mTimer.cancel();
        } else {
            // recreate new
            mTimer = new Timer();
        }
        // schedule task
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, NOTIFY_INTERVAL);
    }
    
    public void generate_notification() {

		Log.i("Start", "notification");

		Vector items = db.get_items();

		for (int i = 0; i < items.size(); i++) {

			Vector item_details = db.get_item(items.elementAt(i).toString());

			
			int flag = 0;

			/* Invoking the default notification service */
			NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

			mBuilder.setContentTitle(
					item_details.elementAt(8).toString() + " : " + item_details.elementAt(0).toString());
			mBuilder.setContentText("You've new reminders.");
			mBuilder.setTicker("New Message Alert!");
			mBuilder.setSmallIcon(R.drawable.ic_launcher);

			/* Add Big View Specific Configuration */
			NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
			
			int numMessages = 0;

			
			
			Calendar today = Calendar.getInstance();
			/*SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
			try {
				today.setTime(sdf.parse(today.getTime().toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
//			Toast.makeText(getApplicationContext(), today.getTime().toString(), Toast.LENGTH_LONG).show();
			
			Calendar insurance_date = Calendar.getInstance();
			insurance_date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(item_details.elementAt(3).toString().substring(0,
					item_details.elementAt(3).toString().indexOf("-"))));
			String month_year = item_details.elementAt(3).toString()
					.substring(item_details.elementAt(3).toString().indexOf("-") + 1);
			insurance_date.set(Calendar.MONTH, Integer.parseInt(month_year.substring(0, month_year.indexOf("-")))); // 0-11
																												// so
																												// 1
																												// less
			insurance_date.set(Calendar.YEAR, Integer.parseInt(month_year.substring(month_year.indexOf("-") + 1)));
//			Toast.makeText(getApplicationContext(), item_details.elementAt(3).toString(), Toast.LENGTH_LONG).show();
//			Toast.makeText(getApplicationContext(), insurance_date.getTime().toString(), Toast.LENGTH_LONG).show();
		
			

			long insurance_diff = today.getTimeInMillis() - insurance_date.getTimeInMillis(); // result
																		// in
																		// millis
//			Toast.makeText(getApplicationContext(), String.valueOf(insurance_diff), Toast.LENGTH_LONG).show();
	
			long insurance_days = insurance_diff / (24 * 60 * 60 * 1000);
//			Toast.makeText(getApplicationContext(), String.valueOf(insurance_days), Toast.LENGTH_LONG).show();
	
			
			
			
			
			
			if(insurance_days>-36)
			{
				inboxStyle.addLine("Insurance Expiry Date : " + item_details.elementAt(3).toString());
				mBuilder.setNumber(++numMessages);
				flag=1;
			}
			
			
			Calendar tax_date = Calendar.getInstance();
			tax_date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(item_details.elementAt(5).toString().substring(0,
					item_details.elementAt(5).toString().indexOf("-"))));
			month_year = item_details.elementAt(5).toString()
					.substring(item_details.elementAt(5).toString().indexOf("-") + 1);
			tax_date.set(Calendar.MONTH, Integer.parseInt(month_year.substring(0, month_year.indexOf("-")))); // 0-11
																												// so
																												// 1
																												// less
			tax_date.set(Calendar.YEAR, Integer.parseInt(month_year.substring(month_year.indexOf("-") + 1)));

//			Toast.makeText(getApplicationContext(), item_details.elementAt(5).toString(), Toast.LENGTH_LONG).show();
//			Toast.makeText(getApplicationContext(), tax_date.getTime().toString(), Toast.LENGTH_LONG).show();
			
			
			long tax_diff = today.getTimeInMillis() - tax_date.getTimeInMillis(); // result
																		// in
																		// millis
//			Toast.makeText(getApplicationContext(), String.valueOf(tax_diff), Toast.LENGTH_LONG).show();
			
			long tax_days = tax_diff / (24 * 60 * 60 * 1000);
//			Toast.makeText(getApplicationContext(), String.valueOf(tax_days), Toast.LENGTH_LONG).show();
			
			if(tax_days>-36)
			{
				inboxStyle.addLine("Tax Expiry Date : " + item_details.elementAt(5).toString());
				mBuilder.setNumber(++numMessages);
				flag=1;
			}
			
			
			

			Calendar smoke_date = Calendar.getInstance();
			smoke_date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(item_details.elementAt(7).toString().substring(0,
					item_details.elementAt(7).toString().indexOf("-"))));
			month_year = item_details.elementAt(7).toString()
					.substring(item_details.elementAt(7).toString().indexOf("-") + 1);
			smoke_date.set(Calendar.MONTH, Integer.parseInt(month_year.substring(0, month_year.indexOf("-")))); // 0-11
																												// so
																												// 1
																												// less
			smoke_date.set(Calendar.YEAR, Integer.parseInt(month_year.substring(month_year.indexOf("-") + 1)));

//			Toast.makeText(getApplicationContext(), item_details.elementAt(7).toString(), Toast.LENGTH_LONG).show();
//			Toast.makeText(getApplicationContext(), smoke_date.getTime().toString(), Toast.LENGTH_LONG).show();
			
			
			long smoke_diff = today.getTimeInMillis() - smoke_date.getTimeInMillis(); // result
																		// in
																		// millis
//			Toast.makeText(getApplicationContext(), String.valueOf(smoke_diff), Toast.LENGTH_LONG).show();
			

			long smoke_days = smoke_diff / (24 * 60 * 60 * 1000);
//			Toast.makeText(getApplicationContext(), String.valueOf(smoke_days), Toast.LENGTH_LONG).show();
			
			if(smoke_days>-36)
			{
				inboxStyle.addLine("Smoke Expiry Date : " + item_details.elementAt(7).toString());
				mBuilder.setNumber(++numMessages);
				flag=1;

			}
			

			Calendar lisence_date = Calendar.getInstance();
			lisence_date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(item_details.elementAt(10).toString().substring(0,
					item_details.elementAt(10).toString().indexOf("-"))));
			month_year = item_details.elementAt(10).toString()
					.substring(item_details.elementAt(10).toString().indexOf("-") + 1);
			lisence_date.set(Calendar.MONTH, Integer.parseInt(month_year.substring(0, month_year.indexOf("-")))); // 0-11
																												// so
																												// 1
																												// less
			lisence_date.set(Calendar.YEAR, Integer.parseInt(month_year.substring(month_year.indexOf("-") + 1)));

//			Toast.makeText(getApplicationContext(), item_details.elementAt(10).toString(), Toast.LENGTH_LONG).show();
//			Toast.makeText(getApplicationContext(), lisence_date.getTime().toString(), Toast.LENGTH_LONG).show();
			
			
			long lisence_diff = today.getTimeInMillis() - lisence_date.getTimeInMillis(); // result
																		// in
																		// millis
//			Toast.makeText(getApplicationContext(), String.valueOf(lisence_diff), Toast.LENGTH_LONG).show();
			
			long lisence_days = lisence_diff / (24 * 60 * 60 * 1000);
//			Toast.makeText(getApplicationContext(), String.valueOf(lisence_days), Toast.LENGTH_LONG).show();
			
			
			if(lisence_days>-36)
			{
				inboxStyle.addLine("Lisence Expiry Date : " + item_details.elementAt(10).toString());
				mBuilder.setNumber(++numMessages);
				flag=1;

			}
			
			
			// Sets a title for the Inbox style big view
			inboxStyle.setBigContentTitle(
					item_details.elementAt(8).toString() + " : " + item_details.elementAt(0).toString());

			mBuilder.setStyle(inboxStyle);

			/* Creates an explicit intent for an Activity in your app */
			Intent resultIntent = new Intent(this, Overview.class);

			TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
			stackBuilder.addParentStack(Overview.class);

			/*
			 * Adds the Intent that starts the Activity to the top of the stack
			 */
			stackBuilder.addNextIntent(resultIntent);
			PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

			mBuilder.setContentIntent(resultPendingIntent);
			NotificationManager mNotificationManager = (NotificationManager) getSystemService(
					Context.NOTIFICATION_SERVICE);

			if (flag == 1) {
				/*
				 * notificationID allows you to update the notification later
				 * on.
				 */
				mNotificationManager.notify(i, mBuilder.build());
			}

		}

	}
 
    class TimeDisplayTimerTask extends TimerTask {
 
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {
 
                @Override
                public void run() {
                    // display toast
                    generate_notification();
                }
 
            });
        }
 
//        private String getDateTime() {
//            // get date time in custom format
//            SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
//            return sdf.format(new Date());
//        }
 
    }
}

	