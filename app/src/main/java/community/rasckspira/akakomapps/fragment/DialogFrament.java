package community.rasckspira.akakomapps.fragment;


import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import community.rasckspira.akakomapps.R;
import community.rasckspira.akakomapps.WebViewActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFrament extends DialogFragment {


    private Button urls;
    private TextView email;
    private Button btn;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_dialog_frament, null);
        urls = (Button) view.findViewById(R.id.url);
        urls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String url = "http://www.fb.com/groups/RackSpira";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);*/
                Intent i = new Intent(getActivity(), WebViewActivity.class);
                i.putExtra(WebViewActivity.KEY_URL, "http://www.fb.com/groups/RackSpira");
                startActivity(i);
            }
        });


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.fragment_dialog_frament, null))
                // Add action buttons
                .setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        DialogFrament.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }


}
