package community.rasckspira.akakomapps.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import community.rasckspira.akakomapps.AppController;
import community.rasckspira.akakomapps.R;

/**
 * Created by Admin on 04-06-2015.
 */
public class ContentFragment extends Fragment {
    private TextView judulProfil;
    private TextView isiProfil;
    private String urls = "http://service.rackspira.community/rest/rjson/profil.json";
    public LinearLayout ll;


    @Nullable
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content, container, false);

        ll = (LinearLayout) v.findViewById(R.id.ll);
        ll.setVisibility(View.VISIBLE);


        judulProfil = (TextView) v.findViewById(R.id.judul_profil);
        isiProfil = (TextView) v.findViewById(R.id.isi_profil);


        getDataJson(v);


        return v;

    }

    public void getDataJson(final View view) {

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
                            //snackbars.dismiss();
                        }


                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());


                        final Snackbar snackbar = Snackbar.make(view.getRootView(), "koneksi bermasalah...", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getDataJson(view);
                            }
                        });
                        View sbView = snackbar.getView();
                        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.WHITE);
                        snackbar.show();
                    }
                }
        );
        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        AppController.getInstance().addToRequestQueue(request);
    }
}