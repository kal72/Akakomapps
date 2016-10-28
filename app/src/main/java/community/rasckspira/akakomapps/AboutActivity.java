package community.rasckspira.akakomapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {
    private Button fb;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        toolbar = (Toolbar) findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fb = (Button) findViewById(R.id.url);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 /*String url = "http://www.fb.com/groups/RackSpira";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);*/
                Intent i = new Intent(AboutActivity.this, WebViewActivity.class);
                i.putExtra(WebViewActivity.KEY_HEADER, "Facebook Fanspage RackSpira");
                i.putExtra(WebViewActivity.KEY_URL, "http://www.fb.com/groups/RackSpira");
                startActivity(i);
            }
        });
    }
}
