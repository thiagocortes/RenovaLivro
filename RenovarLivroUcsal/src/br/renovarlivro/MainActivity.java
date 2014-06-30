/*
 * Alunos: Thiago Silva Côrtes, Roosevelt Ferreira, Marcos Souza, Ailton Junior
 * Prof: Mario Jorge Pereira
 * Disciplina: Desenvolvimento Mobile
 * 
 */

package br.renovarlivro;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.TextView;
 
public class MainActivity extends Activity {

	private ListView lista;
	List<ItemLista> itens = new ArrayList<ItemLista>();
	ItemAdapter adapter = new ItemAdapter(this, itens);
	TextView aviso = null;
	Bundle savedInstanceState = null;
	DbHelper db = new DbHelper(this);;	
	
@Override
protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setTitle("Biblioteca UCSAL");	
		setContentView(R.layout.activity_main);		
		Consulta conn = new Consulta(MainActivity.this);
		conn.execute("http://thiagocortes.besaba.com/json.php");
		
		this.exibirLivros();		
		lista = (ListView) findViewById(R.id.listView1);
		lista.setAdapter(adapter);
	
		registerForContextMenu(lista);
	}
		
public void exibirLivros()   {
	   
	    SQLiteDatabase sql = db.getReadableDatabase();
		Cursor cursor = sql.rawQuery("select * from livros", null);

		int flag = 0;
		String titulo=null;
		String autor=null;
		String isdn=null;
		
		while(cursor.moveToNext()){
			flag++;
			int id = cursor.getColumnIndex("_id");
			titulo = cursor.getString(cursor.getColumnIndex("titulo"));
			autor =  cursor.getString(cursor.getColumnIndex("autor"));
			isdn =   cursor.getString(cursor.getColumnIndex("isdn"));
			itens.add(new ItemLista(id, titulo, autor,isdn));
		}
		Log.d("QUERY", "Consulta realizada com sucesso -  Flag:"+ flag);
	}

	@Override
public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);	
	}
	
public boolean onContextItemSelected(MenuItem item) {
				
		int position = ((AdapterContextMenuInfo)item.getMenuInfo()).position; 
		
		Log.d("Objeto 1",String.valueOf(position));
		ItemLista adp = adapter.getItem( position );
		
	   //	Log.d("Objeto 2",String.valueOf(adp.getId()));
		
	   	switch (item.getItemId()) {
		
		case R.id.action_exibir:	
			Intent intent =	new Intent(MainActivity.this,Detalhes.class);
			Bundle texto = new Bundle();
			
			Log.d("Objeto 2",adp.getTitulo());
			
			texto.putString("id", adp.getTitulo());
			intent.putExtras(texto);			
			startActivity(intent);			
			return true;
			
		case R.id.action_renovar:
			Intent inte =	new Intent(Intent.ACTION_VIEW, Uri.parse(
					"https://www.ucsal.br/PortalSagres/Modules/Acervo/Leitor/Emprestimos.aspx "));
			startActivity(inte);
			return true;
		default:
			return super.onContextItemSelected(item);
		}
}	

	@Override
protected void onResume() {
		super.onResume();
		adapter.notifyDataSetChanged();
	}
public void atualizarLivros(View v) {
		// TODO Auto-generated method stub
		//exibirLivros();
		SQLiteDatabase sql = db.getWritableDatabase();
		sql.delete("livros", null, null);
		onCreate(savedInstanceState);
	}
}
