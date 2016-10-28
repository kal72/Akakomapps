package community.rasckspira.akakomapps;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import community.rasckspira.akakomapps.helper.AppController;
import community.rasckspira.akakomapps.helper.Config;

public class ProfileActivity extends AppCompatActivity {
    private TextView judulProfil;
    private TextView isiProfil;
    private String urls = Config.URL_PROFILE;
    public LinearLayout ll, noInternet;
    private RelativeLayout activity;
    private Toolbar toolbar;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
        initView();
        getDataJson();
    }
    private void initView(){
        activity = (RelativeLayout) findViewById(R.id.activity_profile);
        ll = (LinearLayout) findViewById(R.id.ll);
        noInternet = (LinearLayout) findViewById(R.id.ll_nointernet);
        title = (TextView) findViewById(R.id.toolbar_title);
        title.setText("Profil");
        ll.setVisibility(View.VISIBLE);
        judulProfil = (TextView) findViewById(R.id.judul_profil);
        isiProfil = (TextView) findViewById(R.id.isi_profil);
        noInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataJson();
            }
        });
    }

    private void getDataJson() {
        ll.setVisibility(View.VISIBLE);
        noInternet.setVisibility(View.GONE);
        final JsonArrayRequest request = new JsonArrayRequest(urls,
                new Response.Listener<JSONArray>() {


                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                judulProfil.setText(jsonObject.getString("judul").toString());
                                isiProfil.setText(jsonObject.getString("isi").toString());
                            }
                            ll.setVisibility(View.GONE);
                            //snackbars.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ll.setVisibility(View.GONE);
                            noInternet.setVisibility(View.VISIBLE);
                        }
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //                        System.out.println(error.toString());
                        Log.i("TAG", "onErrorResponse: "+error.toString());
                        /*final Snackbar snackbar = Snackbar.make(view.getRootView(), "Koneksi bermasalah...", LENGTH_INDEFINITE);
                        snackbar.setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                                getDataJson(view);
                            }
                        });
                        View sbView = snackbar.getView();
                        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.WHITE);
                        snackbar.show();*/
                        ll.setVisibility(View.GONE);
                        noInternet.setVisibility(View.VISIBLE);
                    }
                }
        );
        request.setRetryPolicy(new DefaultRetryPolicy(10 * 100, 1, 1.0f));
        AppController.getInstance().addToRequestQueue(request);
    }
}
