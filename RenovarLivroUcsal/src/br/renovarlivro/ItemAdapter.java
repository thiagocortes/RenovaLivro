package br.renovarlivro;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

	private List<ItemLista> lista;
	private Activity activity;

	public ItemAdapter(Activity activity, List<ItemLista> lista) {
		this.activity = activity;
		this.lista = lista;
	}

	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public ItemLista getItem(int posicao) {
		return lista.get(posicao);
	}

	@Override
	public long getItemId(int posicao) {
		return lista.get(posicao).getId();
	}

	@Override
	public View getView(int posicao, View convertView, ViewGroup viewGroup) {
	    View view = convertView;
		if(view == null){
			view = activity.getLayoutInflater().inflate(R.layout.lista,null);
		}
		ItemLista item = lista.get(posicao);

		TextView titulo = (TextView) view.findViewById(R.id.tv_titulo_livro);
		titulo.setText(item.getTitulo());
		
		TextView autor = (TextView) view.findViewById(R.id.tv_autor);
		autor.setText(item.getAutor());
		
		TextView isdn = (TextView) view.findViewById(R.id.tv_isdn);
		isdn.setText(item.getIsdn());
		return view;
	}
}
