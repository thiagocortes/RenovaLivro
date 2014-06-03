package br.renovarlivro;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.util.Log;

 
public class Consulta extends AsyncTask<String, Void, Boolean> {	 
	
	ProgressDialog Dialog;
	Context context;
	String dataEntrega;
	@Override
	protected void onPreExecute() {
			super.onPreExecute();
			Dialog = ProgressDialog.show(context, "Carregando","Por Favor Aguarde...");
	}
		
	public Consulta(Context context) { 		
		this.context = context;
					
	}

	@SuppressWarnings("null")
	@Override	
	protected Boolean doInBackground(String... params)
	{	    	   	
    	String URL = params[0];
        String linha = "";	      
        Boolean Erro = true;
	        
        if (params.length > 0)
           	        	
            try {	 
            		     	
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(URL);                  
                HttpResponse resposta = httpClient.execute(httpGet);	             
                
                HttpEntity httpEntity = resposta.getEntity();
                linha = EntityUtils.toString(httpEntity);           
                JSONObject object = (JSONObject) new JSONTokener(linha).nextValue();	               
                JSONArray message = object.getJSONArray("livros");	                
             
                int flag = 0;
                DbHelper db = new DbHelper(context);
                SQLiteDatabase sql = db.getWritableDatabase();
        		sql.delete("livros", null, null);
                
                for(int i=0;i<message.length();i++)
                {               	
                	JSONObject lines = (JSONObject) new JSONTokener(message.getString(i)).nextValue();
                	String titulo = lines.getString("titulo");
                    String autor = lines.getString("autor");  
                    dataEntrega = lines.getString("data_emprestimo");
                    int isdn = Integer.parseInt(lines.getString("isdn"));
                		                   
            	    try {
            		   Log.d("titulo", titulo);
            		   this.inserir(titulo, autor,isdn); 
            		   flag++;
            		   
                    }catch (Exception e) {
                	   Log.e("ERRO DE CONEXAO", e.getMessage());;
                    }
                }               	                
                Log.d("FLAG", "Flag: "+flag);  
                Dialog.dismiss();                
                Erro = false;               
 
            } catch (Exception e) {
            	Dialog.dismiss();
               	Log.e("ERRO", "Não foi possivel conectar" + e.getMessage());
                Erro = true;
            }
        
        return Erro;
    }
    
	//@Override
	protected void onPostExecute(JSONObject result) {	
		
	}
				
	public Boolean inserir(String TITULO, String autor,int isdn) throws ParseException{	
		DbHelper db = new DbHelper(context);
		try{
				
			SQLiteDatabase sql = db.getWritableDatabase();
				
		    ContentValues valor = new ContentValues();
					
			valor.put("titulo", TITULO);
			valor.put("autor", autor);	
			valor.put("isdn",isdn);
			valor.put("data_entrega",getData());
				
			sql.insert( "livros" , null, valor);			
		}
		catch(SQLiteException e){
			e.getMessage();
			return false;
		}
		db.close();
			return true;
			//finish();		
		}	
	
	public String getData() throws ParseException{
		SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = dtFormat.parse(dataEntrega);   
		return dtFormat.format(dt);
	}
}
