package dod.hackathon.combatfeeding.objects.dbadapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import dod.hackathon.combatfeeding.objects.Food;

public class AppDbAdapter {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_ITEM = "ITEM";
	public static final String KEY_MENU = "MENU";
	public static final String KEY_ITEMTYPE = "ITEMTYPE";
	public static final String KEY_RATION = "RATION";
	public static final String KEY_CARBOHYDRATES_G = "CARBOHYDRATES_G";
	public static final String KEY_PROTEINS_G = "PROTEINS_G";
	public static final String KEY_TOTALFAT_G = "TOTALFAT_G";
	public static final String KEY_CALORIES = "CALORIES";

	private static final String TAG = "DataDbAdapter";
	private DatabaseHelper mDbHelper;
	private Context mContext;
	private SQLiteDatabase mDb;

	private static final String DATABASE_NAME = "AppDataCache";
	private static final String DATABASE_TABLE_FOODS = "foods";

	private static final int DATABASE_VERSION = 1;

	public AppDbAdapter(Context context) {
		this.mContext = context;
	}

	/**
	 * Opens a writable Database.
	 * 
	 * @return an adapter from which you can make changes to the Database
	 * @throws SQLException
	 */
	public AppDbAdapter open() throws SQLException {

		mDbHelper = new DatabaseHelper(mContext);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	/**
	 * Closes the current Database.
	 */
	public void close() {
		mDbHelper.close();
	}

	/**
	 * Internal class that helps creat an internal Database in case there is no
	 * data connectivity.
	 * 
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper {

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE_FOODS);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_FOODS);

			onCreate(db);
		}
	}

	/**
	 * Database creation sql statement
	 */
	private static final String DATABASE_CREATE_FOODS = "CREATE TABLE IF NOT EXISTS "
			+ DATABASE_TABLE_FOODS
			+ " ("
			+ KEY_CARBOHYDRATES_G
			+ " text not null, "
			+ KEY_MENU
			+ " text not null, "
			+ KEY_CALORIES
			+ " text not null, "
			+ KEY_ROWID
			+ " text not null, "
			+ KEY_RATION
			+ " text not null, "
			+ KEY_ITEM
			+ " text not null, "
			+ KEY_TOTALFAT_G 
			+ " text not null, " 
			+ KEY_PROTEINS_G 
			+ " text not null, " 
			+ KEY_ITEMTYPE
			+ " text not null);";

	public long insertFood(Food f) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_CARBOHYDRATES_G, safeString((String) f.get("CARBOHYDRATES_G")));
		initialValues.put(KEY_MENU, safeString((String) f.get("MENU")));
		initialValues.put(KEY_CALORIES, safeString((String) f.get("CALORIES")));
		initialValues.put(KEY_ROWID, safeString((String) f.get("_id")));
		initialValues.put(KEY_RATION, safeString((String) f.get("RATION")));
		initialValues.put(KEY_ITEM, safeString((String) f.get("ITEM")));
		initialValues.put(KEY_TOTALFAT_G, safeString((String) f.get("TOTALFAT_G")));
		initialValues.put(KEY_PROTEINS_G, safeString((String) f.get("PROTEINS_G")));
		initialValues.put(KEY_ITEMTYPE, safeString((String) f.get("ITEMTYPE")));

		return mDb.insert(DATABASE_TABLE_FOODS, null, initialValues);
	}
	
	public String safeString(String input) {
		if (input == null) return "";
		else return input;
	}
	
	public Cursor getFoods() {

		Cursor mCursor = mDb.rawQuery("SELECT * FROM "
				+ DATABASE_TABLE_FOODS, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}	
	
	public Cursor getFoodWithKey(String id) {
		Cursor mCursor = mDb.rawQuery("SELECT * FROM "
				+ DATABASE_TABLE_FOODS + " WHERE _id = " + id, null);
		
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		
		return mCursor;
	}

}