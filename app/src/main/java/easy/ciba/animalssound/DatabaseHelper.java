package easy.ciba.animalssound;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper  extends SQLiteOpenHelper {

	private  static final String DB_NAME = "Users.db";
	private  static final String DB_TABLE = "Users_Table";


	private  static final String COL1 = "ID";
	private  static final String COL2 = "SCOR";
	private  static final String COL3 = "TIME";



	public DatabaseHelper(Context context){
		super(context, DB_NAME, null, 3);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase) {
		String createTable = "CREATE TABLE " + DB_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
				" SCOR TEXT, TIME TEXT)";
		sqLiteDatabase.execSQL(createTable);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);

		onCreate(sqLiteDatabase);
	}

	public boolean insertDate(String scor, String time){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COL2, scor);
		contentValues.put(COL3, time);


		long result  = db.insert(DB_TABLE, null, contentValues);

		if(result == -1){
			return false;
		}else{
			return true;
		}

	}



	public Cursor viewData(){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor data = db.rawQuery("SELECT * FROM " + DB_TABLE, null );
		return data;
	}



	public void delete(String table_name, Object o, Object o1) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from "+ DB_TABLE);
		db.close();
	}
}
