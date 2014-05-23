package br.renovarlivro;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Detalhes extends Activity{
	
	DbHelper db = new DbHelper(this);
	SQLiteDatabase sql = null;
	
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
		sql = db.getReadableDatabase();
		Cursor cursor = sql.rawQuery("select * from livros where _id= ?",new String []{String.valueOf(id)});
//		Cursor cursor = sql.query(false, "livros",new String []{"autor","isdn","titulo"},null,new String []{String.valueOf(id)}, null, null, null, null);

		Log.d("Activity Detalhes", "Consulta realizada com sucesso");
//		titulo.setText(cursor.getString(cursor.getColumnIndex("titulo")));
		while(cursor.moveToNext()){
			titulo.setText(cursor.getString(cursor.getColumnIndex("titulo")));
			autor.setText(cursor.getString(cursor.getColumnIndex("autor")));
			descricao.setText(cursor.getString(cursor.getColumnIndex("isdn")));
			data.setText(cursor.getString(cursor.getColumnIndex("data_entrega")));
			Log.d("Data",String.valueOf(data.getText()));
		}
//		try{
//			db.close();
//			cursor.close();
//		}
//		catch(SQLiteException e){
//			e.getMessage();
//		}
	}
	public void renovar(View view) throws ParseException{
		Intent it = getIntent();
		int id = it.getIntExtra("id", 0);
				
		String dataRenovacao = data.getText().toString();
		
		SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = dtFormat.parse(dataRenovacao);
		Log.d("tempo",dataRenovacao);
		String novaData = dtFormat.format(dt.getTime()+(7000*60*60*24));
		
		ContentValues valores = new ContentValues();
		valores.put("data_entrega",novaData);
		sql.update("livros", valores,  "_id = ?",new String []{String.valueOf(id)});
		Toast.makeText(this,"O livro "+titulo.getText().toString()+" foi renovado para "+novaData,Toast.LENGTH_LONG).show();

	}
}