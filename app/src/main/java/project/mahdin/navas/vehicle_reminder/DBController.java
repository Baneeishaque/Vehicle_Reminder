package project.mahdin.navas.vehicle_reminder;

import java.util.HashMap;
import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBController extends SQLiteOpenHelper 
{

	public DBController(Context applicationcontext) 
	{
		super(applicationcontext, "androidsqlite.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase database) 
	{
		String query;
		query = "CREATE TABLE vehicle_reminder ( registration_number TEXT PRIMARY KEY, description TEXT, "
				+ "insuranec_number TEXT, insurance_expiration_date TEXT, tax_file_number TEXT, "
				+ "next_tax_pay_date TEXT, smoke_certificate_number TEXT, smoke_certificate_expiry_date TEXT, "
				+ "category TEXT, driving_lisence_number TEXT, driving_lisence_expiration_date TEXT)";
		database.execSQL(query);

	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old,
			int current_version) 
	{
		String query;
		query = "DROP TABLE IF EXISTS vehicle_reminder";
		database.execSQL(query);
		onCreate(database);
	}

	public long insertdb(Vector queryValues) 
	{
		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("registration_number", queryValues.elementAt(0).toString());
		values.put("description", queryValues.elementAt(1).toString());
		values.put("insuranec_number", queryValues.elementAt(2).toString());
		values.put("insurance_expiration_date", queryValues.elementAt(3).toString());
		values.put("tax_file_number", queryValues.elementAt(4).toString());
		values.put("next_tax_pay_date", queryValues.elementAt(5).toString());
		values.put("smoke_certificate_number", queryValues.elementAt(6).toString());
		values.put("smoke_certificate_expiry_date", queryValues.elementAt(7).toString());
		values.put("category", queryValues.elementAt(8).toString());
		values.put("driving_lisence_number", queryValues.elementAt(9).toString());
		values.put("driving_lisence_expiration_date", queryValues.elementAt(10).toString());
		long result=database.insert("vehicle_reminder", null, values);
		database.close();
		return result;
	}

	

	

	public Vector get_item(String registration_number) 
	{
		Vector wordList=new Vector();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM vehicle_reminder where registration_number='"
				+ registration_number + "'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) 
		{
			do 
			{
				wordList.add(cursor.getString(0));
				wordList.add(cursor.getString(1));
				wordList.add(cursor.getString(2));
				wordList.add(cursor.getString(3));
				wordList.add(cursor.getString(4));
				wordList.add(cursor.getString(5));
				wordList.add(cursor.getString(6));
				wordList.add(cursor.getString(7));
				wordList.add(cursor.getString(8));
				wordList.add(cursor.getString(9));
				wordList.add(cursor.getString(10));

			} while (cursor.moveToNext());
		}
		return wordList;
	}
	
	public Vector get_items() 
	{
		Vector wordList=new Vector();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT registration_number FROM vehicle_reminder";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) 
		{
			do 
			{
				wordList.add(cursor.getString(0));

			} while (cursor.moveToNext());
		}
		return wordList;
	}
}
