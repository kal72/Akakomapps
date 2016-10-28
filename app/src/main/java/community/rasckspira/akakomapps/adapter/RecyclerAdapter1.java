package community.rasckspira.akakomapps.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import community.rasckspira.akakomapps.model.Data;
import community.rasckspira.akakomapps.R;

/**
 * Created by dedevalen on 14/09/15.
 */
public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.MyViewHolder> {


    List<Data> mItems;
    Context mContext;

    public RecyclerAdapter1(Context context, List<Data> mItems) {

        this.mItems = mItems;
        this.mContext = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_jabatan_view, null);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Data item = mItems.get(position);

        holder.jNama.setText(item.getNama().toString());
        holder.jPosisi.setText(item.getPosisi().toString());
        holder.jEmail.setText(item.getEmail().toString());

    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView jNama;
        public TextView jPosisi;
        public TextView jEmail;
        public CardView cvJabatan;


        public MyViewHolder(View itemView) {
            super(itemView);

            //robotobold = Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Bold.ttf");
            //robotoregular = Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Regular.ttf");


            jNama = (TextView) itemView.findViewById(R.id.nama_jabatan);
            jPosisi = (TextView) itemView.findViewById(R.id.posisi_jabatan);
            this.jEmail = (TextView) itemView.findViewById(R.id.email_jabatan);
            cvJabatan = (CardView) itemView.findViewById(R.id.cvJabatan);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            ClipboardManager clipboard = (ClipboardManager) view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("email ", jEmail.getText());
            clipboard.setPrimaryClip(clip);
            final Snackbar snackbar = Snackbar.make(view.getRootView(), "Email Copied", Snackbar.LENGTH_SHORT);
            snackbar.setAction("OKE", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();

        }
    }
}