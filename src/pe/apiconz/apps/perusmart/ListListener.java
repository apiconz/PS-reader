package pe.apiconz.apps.perusmart;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ListListener implements OnItemClickListener {

	List<Articulo> listItems;
	Activity activity;

	public ListListener(List<Articulo> listItems, Activity activity) {
		this.listItems = listItems;
		this.activity = activity;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(listItems.get(pos).getEnlace()));
		activity.startActivity(i);
	}

}
