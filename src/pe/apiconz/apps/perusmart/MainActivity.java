package pe.apiconz.apps.perusmart;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends ListActivity implements OnItemClickListener {

	public static String TAG = "PeruSmartReader";
	private ProgressDialog progressDialog;
	List<Articulo> result = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
		progressDialog.setTitle("Cargando");

		AsynFeedReader asynFeedReader = new AsynFeedReader();
		asynFeedReader.execute();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	private class AsynFeedReader extends
			AsyncTask<Void, Integer, List<Articulo>> {

		@Override
		protected void onPreExecute() {
			progressDialog.show();
		}

		@Override
		protected List<Articulo> doInBackground(Void... params) {

			try {
				Log.d(MainActivity.TAG, "Antes de leer el feed");
				FeedReader reader = new FeedReader(
						"http://www.perusmart.com/feed");
				Log.d(MainActivity.TAG, "Despues de leer el feed");
				result = reader.getItems();
				return reader.getItems();
			} catch (Exception e) {
				Log.e(MainActivity.TAG, e.getMessage(), e);
			}
			return result;
		}

		@Override
		protected void onPostExecute(List<Articulo> result) {

			Log.d(MainActivity.TAG, "Entro a onPostExecute");

			ListFeedAdapter listFeedAdapter = new ListFeedAdapter(
					MainActivity.this, result);

			setListAdapter(listFeedAdapter);

			progressDialog.dismiss();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(result.get(position).getEnlace()));
		startActivity(i);
	}
}
