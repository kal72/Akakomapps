package community.rasckspira.akakomapps;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

import static android.support.design.widget.Snackbar.LENGTH_INDEFINITE;
import static community.rasckspira.akakomapps.helper.AppController.TAG;

public class VisiMisiActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView txtVisi;
    private TextView txtMisi;
    private String urlVisiMisi;
    public LinearLayout ll, noInternet;
    private ProgressBar loading;
    private RelativeLayout activity;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visi_misi);

        toolbar = (Toolbar) findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Visi dan Misi");

        initView();
        getDataJson();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView(){
        activity = (RelativeLayout) findViewById(R.id.activity_visi_misi);
        ll = (LinearLayout) findViewById(R.id.ll);
        ll.setVisibility(View.VISIBLE);
        title = (TextView) findViewById(R.id.toolbar_title);
        title.setText("Visi dan Misi");
        noInternet = (LinearLayout) findViewById(R.id.ll_nointernet);
        txtMisi = (TextView) findViewById(R.id.misi);
        txtVisi = (TextView) findViewById(R.id.visi);
        loading = (ProgressBar) findViewById(R.id.loading);
        urlVisiMisi = Config.URL_VISI_MISI;
    }
    public void getDataJson() {
        ll.setVisibility(View.VISIBLE);
        noInternet.setVisibility(View.GONE);
        final JsonArrayRequest request = new JsonArrayRequest(urlVisiMisi,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                txtVisi.setText(jsonObject.getString("visi").toString());
                                txtMisi.setText(jsonObject.getString("misi").toString());
                            }

                            ll.setVisibility(View.GONE);

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
