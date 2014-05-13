package br.renovarlivro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper{
	
	private static final String DB_NAME = "cadastrolivro.db";
	private static final int VERSION = 1;
	private static final String TABLE_NAME = "livros";
	
	private final String COLUMN_ID = "_id";
	private final String COLUMN_TITULO = "titulo";
	private final String COLUMN_ISDN = "isdn";
	private final String COLUMN_AUTOR = "autor";
	
	private final static String CREATE_DATABASE ="create database "+DB_NAME;
	private final String CREATE_TABLE = "create table "+TABLE_NAME+"("+COLUMN_ID+" integer primary key autoincrement,"
			+COLUMN_TITULO+" varchar,"+COLUMN_ISDN+" integer,"+COLUMN_AUTOR+" varchar)";
	private static final String DELETE_TABLE = "drop table "+TABLE_NAME;

	public DbHelper(Context context) {
		super(context, CREATE_DATABASE, null, VERSION);
		Log.d("banco","Banco criado com sucesso");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
		Log.d("Criar tabela","TABELA LIVROS CRIADA");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
