package com.daarululuumlido.mywidget.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.daarululuumlido.mywidget.R;

public class UpdatingWidgetService extends JobService {


    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.bilangan_acak_widget);
        ComponentName theWidget = new ComponentName(this, BilanganAcakWidget.class);

        String lastupdate = "Random " + NumberGenerator.Generate(100);
        Toast.makeText(this, "Job Service Running", Toast.LENGTH_LONG).show();

        view.setTextViewText(R.id.appwidget_text, lastupdate);
        manager.updateAppWidget(theWidget, view);
        jobFinished(jobParameters, false);

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


}
