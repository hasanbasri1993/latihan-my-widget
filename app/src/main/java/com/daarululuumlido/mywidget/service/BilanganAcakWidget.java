package com.daarululuumlido.mywidget.service;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.daarululuumlido.mywidget.R;

/**
 * Implementation of App Widget functionality.
 */
public class BilanganAcakWidget extends AppWidgetProvider {

    private static String WIDGET_CLICK = "widgetclick";
    private static String WIDGET_ID_EXTRA = "widget_id_extra";

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bilangan_acak_widget);
        String lastUpdate = "Random: " + NumberGenerator.Generate(100);
        views.setOnClickPendingIntent(R.id.btn_click, getPendingSelfIntent(context, appWidgetId, WIDGET_CLICK));
        views.setTextViewText(R.id.appwidget_text, lastUpdate);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (WIDGET_CLICK.equals(intent.getAction())) {
            String lastUpdate = "Random: " + NumberGenerator.Generate(100);
            int appWidgetId = intent.getIntExtra(WIDGET_ID_EXTRA, 0);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bilangan_acak_widget);
            views.setTextViewText(R.id.appwidget_text, lastUpdate);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, int appWidgetID, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        intent.putExtra(WIDGET_ID_EXTRA, appWidgetID);
        return PendingIntent.getBroadcast(context, appWidgetID, intent, 0);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

