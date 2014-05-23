package br.renovarlivro;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends Activity{
	
	DbHelper db = new DbHelper(this);
	String dataEntrega;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro);		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void inserir(View v) throws ParseException{
		EditText titulo = (EditText) findViewById(R.id.et_titulo);
		EditText isdn = (EditText) findViewById(R.id.et_isdn);
		EditText autor = (EditText) findViewById(R.id.et_autor);
		EditText data_entrega = (EditText) findViewById(R.id.et_data);
		dataEntrega = data_entrega.getText().toString();
		
		try{
			SQLiteDatabase sql = db.getWritableDatabase();
			
			ContentValues valor = new ContentValues();
			
			valor.put("titulo", titulo.getText().toString());
			valor.put("isdn", isdn.getText().toString());	
			valor.put("autor", autor.getText().toString());	
			valor.put("data_entrega", getData());
			sql.insert("livros", null, valor);
			Toast.makeText(this,"Livros cadastrados com sucesso",Toast.LENGTH_SHORT).show();
			Log.d("tempo",getData());
		}
		catch(SQLiteException e){
			e.getMessage();
		}
		db.close();
		finish();
	}	
	public String getData(){
		SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = new Date(0);
		Log.d("tempo",dataEntrega);
		return dtFormat.format(dt.parse(dataEntrega)+(7000*60*60*24));
	}
}
