package community.rasckspira.akakomapps.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import community.rasckspira.akakomapps.helper.AppController;
import community.rasckspira.akakomapps.R;
import community.rasckspira.akakomapps.helper.Config;

import static android.support.design.widget.Snackbar.LENGTH_INDEFINITE;
import static community.rasckspira.akakomapps.helper.AppController.TAG;


public class VisiMisiFragment extends Fragment {

    private TextView txtVisi;
    private TextView txtMisi;
    private String urlVisiMisi;
    public LinearLayout ll;
    private ProgressBar loading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_visi_misi, container, false);
        initView(v);
       getDataJson(v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View v){
        ll = (LinearLayout) v.findViewById(R.id.ll);
        ll.setVisibility(View.VISIBLE);
        txtMisi = (TextView) v.findViewById(R.id.misi);
        txtVisi = (TextView) v.findViewById(R.id.visi);
        loading = (ProgressBar) v.findViewById(R.id.loading);
        urlVisiMisi = Config.URL_VISI_MISI;
    }

    public void getDataJson(final View view) {
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
                        }
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                        final Snackbar snackbar = Snackbar.make(view.getRootView(), "Koneksi bermasalah...", LENGTH_INDEFINITE);
                        snackbar.setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                                getDataJson(view);
                                loading.setVisibility(View.VISIBLE);
                            }
                        });
                        View sbView = snackbar.getView();
                        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.WHITE);
                        snackbar.show();
                        loading.setVisibility(View.GONE);
                        Log.i(TAG, "onErrorResponse: "+urlVisiMisi);

                    }
                }
        );
        request.setRetryPolicy(new DefaultRetryPolicy(10 * 100, 1, 1.0f));
        AppController.getInstance().addToRequestQueue(request);
    }


}
