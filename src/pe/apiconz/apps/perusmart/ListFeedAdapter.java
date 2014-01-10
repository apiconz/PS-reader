package pe.apiconz.apps.perusmart;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListFeedAdapter extends ArrayAdapter<Articulo> {

	public ListFeedAdapter(Activity context, List<Articulo> listFeed) {
		super(context, R.layout.blog_item, listFeed);
		this.listFeed = listFeed;
		this.context = context;
	}

	private List<Articulo> listFeed;
	private Activity context;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;

		if (convertView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			view = inflater.inflate(R.layout.blog_item, null);

			final ViewHolder holder = new ViewHolder();
			holder.txtTitle = (TextView) view.findViewById(R.id.title);
			holder.txtDate = (TextView) view.findViewById(R.id.date);
			view.setTag(holder);

		} else {
			view = convertView;
		}

		ViewHolder holder = (ViewHolder) view.getTag();

		Articulo articulo = this.listFeed.get(position);
		holder.txtTitle.setText(articulo.getTitulo());
		holder.txtDate.setText(articulo.getFecha());

		return view;
	}

	private class ViewHolder {
		public TextView txtTitle;
		public TextView txtDate;
	}
}
