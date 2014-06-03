package br.renovarlivro;

import java.text.ParseException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
		setTitle("Biblioteca UCSAL");
		setContentView(R.layout.detalhes_livro);
		exibirDetalhes();		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		exibirDetalhes();		
	}
	
	private void exibirDetalhes() {
		
		titulo = (TextView) findViewById(R.id.tv_subtitulo);
		autor = (TextView) findViewById(R.id.tv_subautor);
		descricao = (TextView) findViewById(R.id.tv_subdescricao);
		data = (TextView) findViewById(R.id.tv_subdata);
		
		
		Intent it = getIntent();
		Bundle pegarTexto = it.getExtras();
		String tituloLivro = pegarTexto.getString("id");
		Log.d("Activity Detalhes", String.valueOf(tituloLivro));
		
		sql = db.getReadableDatabase();
		Cursor cursor = sql.rawQuery("select * from livros where titulo = ?",new String []{String.valueOf(tituloLivro)});

		Log.d("Activity Detalhes", "Consulta realizada com sucesso");
		while(cursor.moveToNext()){
			titulo.setText(cursor.getString(cursor.getColumnIndex("titulo")));
			autor.setText(cursor.getString(cursor.getColumnIndex("autor")));
			descricao.setText(cursor.getString(cursor.getColumnIndex("isdn")));
			data.setText(cursor.getString(cursor.getColumnIndex("data_entrega")));
			Log.d("Data",String.valueOf(data.getText()));
		}
		try{
			//db.close();
			cursor.close();
		}
		catch(SQLiteException e){
			e.getMessage();
		}
	}
	
	public void renovar(View view) throws ParseException{
		Intent inte =	new Intent(Intent.ACTION_VIEW, Uri.parse(
				"https://www.ucsal.br/PortalSagres/Modules/Acervo/Leitor/Emprestimos.aspx "));
		startActivity(inte);
	}
//	public void devolver(View view) {
//		Intent it = getIntent();
//		int id = it.getIntExtra("id", 0);
//		
//		ContentValues valores = new ContentValues();
//		valores.put("devolvido",0);
//		sql.update("livros", valores,  "_id = ?",new String []{String.valueOf(id)});
//		Toast.makeText(this,"O livro "+titulo.getText().toString()+" foi devolvido!",Toast.LENGTH_LONG).show();
//		try{
//			sql.close();
//			
//		}catch(SQLiteException e){
//			e.getMessage();
//		}	
//		finish();
//	}
}