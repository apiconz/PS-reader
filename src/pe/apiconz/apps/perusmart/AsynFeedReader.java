package pe.apiconz.apps.perusmart;

import java.util.List;

import android.os.AsyncTask;
import android.util.Log;

class AsynFeedReader extends AsyncTask<Void, Integer, List> {

	@Override
	protected List<Articulo> doInBackground(Void... params) {
		List<Articulo> result = null;

		try {
			FeedReader reader = new FeedReader("http://www.perusmart.com/feed");
			result = reader.getItems();
			return reader.getItems();
		} catch (Exception e) {
			Log.e("PeruSmartReader", e.getMessage(), e);
		}
		return result;
	}

}
