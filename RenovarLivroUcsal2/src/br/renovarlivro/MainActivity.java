package br.renovarlivro;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
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
import android.widget.Toast;
 
public class MainActivity extends Activity {

	private ListView lista;
	List<ItemLista> itens = new ArrayList<ItemLista>();
	ItemAdapter adapter = new ItemAdapter(this, itens);
	TextView aviso = null;
	
	DbHelper db = new DbHelper(this);;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setTitle("Biblioteca UCSAL");	
		setContentView(R.layout.activity_main);		
		Consulta conn = new Consulta(MainActivity.this);
		conn.execute("http://thiagocortes.besaba.com/json.php");
		
		this.exibirNoticias();
		
		lista = (ListView) findViewById(R.id.listView1);
			
		lista.setAdapter(adapter);
		
		//Registrar o menu de contexto
		registerForContextMenu(lista);
	}
		
   private void exibirNoticias()   {
	   
	    SQLiteDatabase sql = null;
		sql = db.getReadableDatabase();
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
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
				
		int position = ((AdapterContextMenuInfo)item.getMenuInfo()).position; 
		
		Object adp = adapter.getItem( position );		
	   	
		switch (item.getItemId()) {
		
		case R.id.action_exibir:			
			Intent intent =	new Intent(this,Detalhes.class);
			startActivity(intent);
			return true;
		case R.id.action_excluir:
			itens.remove(info.position);
			Toast.makeText(this, "Item removivido com sucesso", Toast.LENGTH_LONG).show();
			onResume();
			return true;
		case R.id.action_renovar:
			Intent it =	new Intent(this,Detalhes.class);
			startActivity(it);
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
	
	private boolean verificaConexao() {
        
		boolean conectado = false;
       
        try
        {
            ConnectivityManager cm = (ConnectivityManager)
            getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            	conectado = true;
            }
        }catch (Exception e) {
        	Log.e("ERRO DE CONEXAO", e.getMessage());;
        }
        return conectado;
	 }

	
	
}
