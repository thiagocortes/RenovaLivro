package br.renovarlivro;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {
	DbHelper db = new DbHelper(this);
	ListView listLivros;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listLivros = (ListView) findViewById(R.id.listView1);
		
		listar();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		listar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void btCadastrar(View v){
		Intent intent = new Intent (this, Cadastro.class);
		startActivity(intent);
	}
	
	public void listar(){
		SQLiteDatabase sql = db.getWritableDatabase();		
		String [] colunas = {"titulo","isdn","autor"};
		int [] campo = {R.id.tv_titulo_livro,R.id.tv_isdn,R.id.tv_autor};
		Cursor cursor = sql.rawQuery("select * from livros",null);
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.lista, cursor, colunas,campo);
		listLivros.setAdapter(adapter);
		
		listLivros.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				SQLiteCursor c = (SQLiteCursor) adapter.getAdapter().getItem(position);
				Intent detalhes = new Intent (getBaseContext(), Detalhes.class);
				detalhes.putExtra("id",c.getInt(0));
				startActivity(detalhes);			
			}		
		});
		try{
			db.close();
		}
		catch(SQLiteException e){
			e.getMessage();
		}
	}
}
