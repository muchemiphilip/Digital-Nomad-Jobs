package io.github.alistairholmes.digitalnomadjobs.ui;

import android.content.Intent;
import android.net.Uri;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import io.github.alistairholmes.digitalnomadjobs.R;

public class DetailActivity extends AppCompatActivity {

    public TextView tv_JobTitle;
    public TextView tv_CompanyName;
    public TextView tv_JobDescription;
    public int jobID;
    public ImageView iv_CompanyLogo;
    public final String REMOTEOK_URL = "https://remoteok.io/l/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_JobTitle = findViewById(R.id.textView_jobtitle);
        tv_CompanyName = findViewById(R.id.textView_companyname);
        iv_CompanyLogo = findViewById(R.id.imageView_Logo);
        tv_JobDescription = findViewById(R.id.textView_description);


        //get the intent in the target activity
        Intent intent = getIntent();
        //get the attached bundle from the intent
        Bundle extras = intent.getExtras();
        //Extracting the stored data from the bundle
        String job_title = extras.getString("JOB_TITLE");
        String company_name = extras.getString("COMPANY_NAME");
        String company_logo = extras.getString("COMPANY_LOGO");
        String job_description = extras.getString("JOB_DESCRIPTION");
        jobID = extras.getInt("JOB_ID");

        tv_JobTitle.setText(job_title);
        tv_CompanyName.setText(company_name);
        tv_JobDescription.setText(job_description);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_foreground)
                .circleCrop();
        Glide.with(this)
                .load(company_logo)
                .apply(requestOptions)
                .into(iv_CompanyLogo);

        final String jobURL = REMOTEOK_URL + jobID;

        // Set a click listener for when the apply for job button is pressed
        findViewById(R.id.btn_applyforjob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Use a CustomTabsIntent.Builder to configure CustomTabsIntent.
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                // set toolbar color and set custom actions before invoking build()
                builder.setToolbarColor(ContextCompat.getColor(DetailActivity.this, R.color.colorAccent));
                // Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent
                CustomTabsIntent customTabsIntent = builder.build();
                // and launch the desired Url with CustomTabsIntent.launchUrl()
                customTabsIntent.launchUrl(DetailActivity.this, Uri.parse(jobURL));
            }
        });

    }
}