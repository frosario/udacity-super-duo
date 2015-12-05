package barqsoft.footballscores;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.HashMap;

public class WidgetViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private String TAG = "WidgetViewsFactory";
    private Context context;
    private Intent intent;
    private scoresAdapter scoresAdapter;

    public WidgetViewsFactory(Context c, Intent i){
        context = c;
        intent = i;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate called");
        String uri_string = "content://barqsoft.footballscores";
        Uri uri = Uri.parse(uri_string);
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        scoresAdapter = new scoresAdapter(context,cursor,0);
    }

    @Override
    public void onDestroy() { Log.d(TAG, "onDestroy called"); }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount called");
        int count = scoresAdapter.getCount();
        Log.d(TAG,"Number of score entries: " + String.valueOf(count));
        return count;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.d(TAG, "getViewAt called");
        Cursor cursor = (Cursor) scoresAdapter.getItem(position);
        RemoteViews scoreListItem = new RemoteViews(context.getPackageName(),R.layout.scores_list_item);

        //Attached debugger and inspected cursor, these all return strings
        int id = 0;
        int date = 1;
        int time = 2;
        int home = 3;
        int away = 4;
        int league = 5;
        int home_goals = 6;
        int away_goals = 7;
        int match_id = 8;
        int match_day = 9;

        //Grab the info we need
        String homeString = cursor.getString(home);
        String homeGoalsString = cursor.getString(home_goals);
        String awayGoalsString = cursor.getString(away_goals);
        String timeString = cursor.getString(time);
        String awayString = cursor.getString(away);
        String dateString = cursor.getString(date);

        scoreListItem.setImageViewResource(R.id.home_crest, Utilies.getTeamCrestByTeamName(homeString));

        scoreListItem.setTextViewText(R.id.home_name, homeString);
        scoreListItem.setTextColor(R.id.home_name, Color.BLACK);

        String scoreString = homeGoalsString + " to " + awayGoalsString;
        scoreListItem.setTextViewText(R.id.score_textview, scoreString);
        scoreListItem.setTextColor(R.id.score_textview, Color.BLACK);
        scoreListItem.setFloat(R.id.score_textview, "setTextSize", 14);

        scoreListItem.setTextViewText(R.id.data_textview, dateString + " " + timeString);
        scoreListItem.setTextColor(R.id.data_textview, Color.BLACK);
        scoreListItem.setFloat(R.id.data_textview, "setTextSize", 14);

        //TODO: Find the real image and method
        scoreListItem.setImageViewResource(R.id.away_crest, Utilies.getTeamCrestByTeamName(awayString));
        scoreListItem.setTextViewText(R.id.away_name, awayString);
        scoreListItem.setTextColor(R.id.away_name, Color.BLACK);

        return scoreListItem;
    }

    @Override
    public RemoteViews getLoadingView() {
        Log.d(TAG, "getLoadingView called");
        return null;
    }

    @Override
    public int getViewTypeCount() {
        Log.d(TAG, "getViewTypeCount called");
        return 1;
    }

    @Override
    public long getItemId(int position) {
        Log.d(TAG, "getItemId called");
        return position;
    }

    @Override
    public boolean hasStableIds() {
        Log.d(TAG, "hasStableIds called");
        return true;
    }

    @Override
    public void onDataSetChanged() { Log.d(TAG, "onDataSetChanged called"); }
}

