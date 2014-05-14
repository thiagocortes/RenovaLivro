package br.renovarlivro;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Detalhes extends Activity{
	
	DbHelper db = new DbHelper(this);
	
	TextView titulo;
	TextView autor;
	TextView descricao;
	TextView data;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detalhes_livro);
		
		titulo = (TextView) findViewById(R.id.tv_subtitulo);
		autor = (TextView) findViewById(R.id.tv_subautor);
		descricao = (TextView) findViewById(R.id.tv_subdescricao);
		data = (TextView) findViewById(R.id.tv_subdata);
		
		Intent it = getIntent();
		int id = it.getIntExtra("id", 0);
		Log.d("Activity Detalhes", String.valueOf(id));
		
		SQLiteDatabase sql = db.getReadableDatabase();
		Cursor cursor = sql.rawQuery("select * from livros where _id= ?",new String []{String.valueOf(id)});
//		Cursor cursor = sql.query(false, "livros",new String []{"autor","isdn","titulo"},null,new String []{String.valueOf(id)}, null, null, null, null);

		Log.d("Activity Detalhes", "Consulta realizada com sucesso");
//		titulo.setText(cursor.getString(cursor.getColumnIndex("titulo")));
		while(cursor.moveToNext()){
			titulo.setText(cursor.getString(cursor.getColumnIndex("titulo")));
			autor.setText(cursor.getString(cursor.getColumnIndex("autor")));
			descricao.setText(cursor.getString(cursor.getColumnIndex("isdn")));
			//data.setText(cursor.getString(cursor.getColumnIndex("data_entrega")));
		}
		try{
			db.close();
			cursor.close();
		}
		catch(SQLiteException e){
			e.getMessage();
		}
	}
}