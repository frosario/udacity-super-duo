package barqsoft.footballscores;

import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;

public class WidgetService extends RemoteViewsService {
    String TAG = "WidgetService";

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d(TAG, "onGetViewFactory called");
        return new WidgetViewsFactory(this.getApplicationContext(), intent);
    }
}