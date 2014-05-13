package br.renovarlivro;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Cadastro extends Activity{
	
	DbHelper db = new DbHelper(this);
	
	TextView titulo;
	TextView isdn;
	TextView autor;
	
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
	
	
	public void inserir(View v){
		EditText titulo = (EditText) findViewById(R.id.et_titulo);
		EditText isdn = (EditText) findViewById(R.id.et_isdn);
		EditText autor = (EditText) findViewById(R.id.et_autor);
		try{
			SQLiteDatabase sql = db.getWritableDatabase();
			
			ContentValues valor = new ContentValues();
			
			valor.put("titulo", titulo.getText().toString());
			valor.put("isdn", isdn.getText().toString());	
			valor.put("autor", autor.getText().toString());	
			sql.insert("livros", null, valor);
			Toast.makeText(this,"Livros cadastrados com sucesso",Toast.LENGTH_SHORT).show();
		}
		catch(SQLiteException e){
			e.getMessage();
		}
		db.close();
		finish();
	}	
}
