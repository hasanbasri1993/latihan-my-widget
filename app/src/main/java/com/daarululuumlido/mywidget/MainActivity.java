package com.daarululuumlido.mywidget;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.daarululuumlido.mywidget.service.UpdatingWidgetService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_start:
                startJob();
                break;

            case R.id.btn_stop:
                cancelJob();
                break;
        }
    }
    private static int jobId = 100;
    private static int PERIODE_SCHEDULER = 86000;

    private void startJob() {
        ComponentName mServiceComponent = new ComponentName(this, UpdatingWidgetService.class);
        JobInfo.Builder builder = new JobInfo.Builder(jobId, mServiceComponent);

        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPeriodic(PERIODE_SCHEDULER);

        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(builder.build());

        Toast.makeText(this, "Job Service Started", Toast.LENGTH_SHORT).show();

    }

    private void cancelJob() {

        JobScheduler  tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.cancel(jobId);
        Toast.makeText(this, "Job Service canceled", Toast.LENGTH_SHORT).show();

    }
}
