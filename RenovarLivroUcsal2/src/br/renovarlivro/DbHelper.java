package br.renovarlivro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "cadastrolivro.db";
	private static int DB_VERSION = 1;
	private static final String TABLE_NAME = "livros";
	private static final String TABLE_TEMP = "temp";
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_TITULO = "titulo";
	private static final String COLUMN_ISDN = "isdn";
	private static final String COLUMN_AUTOR = "autor";
	private static final String COLUMN_DATA_EMPRESTIMO = "data_emprestimo";
	private static final String COLUMN_DATA_ENTREGA = "data_entrega";
	private static final String COLUMN_DEVOLVIDO = "devolvido";
	
    private static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
						+ TABLE_NAME + " ( "
						+ COLUMN_ID + " integer primary key autoincrement, " 
						+ COLUMN_TITULO	+ " varchar(100), "
						+ COLUMN_AUTOR  + " varchar(55), "
						+ COLUMN_DATA_EMPRESTIMO + " varchar(100), "
						+ COLUMN_DATA_ENTREGA  + " varchar(55), "
						+ COLUMN_DEVOLVIDO  + " varchar(55), "
						+ COLUMN_ISDN + " integer ); "
						
						+ "CREATE TABLE IF NOT EXISTS "
						+ TABLE_TEMP + " ( "
						+ COLUMN_ID + " integer primary key autoincrement, " 
						+ COLUMN_TITULO	+ " varchar(100), "
						+ COLUMN_AUTOR  + " varchar(55), "
						+ COLUMN_DATA_EMPRESTIMO + " varchar(100), "
						+ COLUMN_DATA_ENTREGA  + " varchar(55), "
						+ COLUMN_DEVOLVIDO  + " varchar(55), "
						+ COLUMN_ISDN + " integer ); ";
   
	public DbHelper(Context context) {
	            super(context, DB_NAME, null, DB_VERSION);         
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(TABLE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
  
}

