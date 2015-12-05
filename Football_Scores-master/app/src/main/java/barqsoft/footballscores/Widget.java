package barqsoft.footballscores;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;


public class Widget extends AppWidgetProvider {
    static String TAG = "Widget";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d(TAG, "onUpdate called");

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            RemoteViews widget = new RemoteViews(context.getPackageName(), R.layout.widget);
            Intent serviceIntent = new Intent(context, barqsoft.footballscores.WidgetService.class);
            widget.setRemoteAdapter(R.id.listView, serviceIntent);
            appWidgetManager.updateAppWidget(appWidgetId, widget);
        }
    }

}


//TODO: Change update interval to every 30 mins
//TODO: Update "no icon" with actual drawables. Maybe create a hashmap for this?