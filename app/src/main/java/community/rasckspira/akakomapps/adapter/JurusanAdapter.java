package community.rasckspira.akakomapps.adapter;

/**
 * Created by kristiawan on 09/12/15.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import community.rasckspira.akakomapps.JurusanDetailActivity;
import community.rasckspira.akakomapps.R;
import community.rasckspira.akakomapps.model.Data;

public class JurusanAdapter extends RecyclerView.Adapter<JurusanAdapter.MyViewHolder> {


    List<Data> mItems;
    Context mContext;

    public JurusanAdapter(Context context, List<Data> mItems) {

        this.mItems = mItems;
        this.mContext = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_jurusan, null);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Data item = mItems.get(i);

        myViewHolder.namaJurusan.setText(item.getNama().toString());
        myViewHolder.desJurusan.setText(item.getDetail().toString());
        myViewHolder.aLink = item.getLink();



    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView namaJurusan;
        public TextView desJurusan;
        public String aLink;
        public CardView cv;


        public MyViewHolder(View itemView) {
            super(itemView);

            //robotobold = Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Bold.ttf");
            //robotoregular = Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Regular.ttf");


            //this.img = (ImageView)itemView.findViewById(R.id.img);
            this.namaJurusan = (TextView) itemView.findViewById(R.id.nama_jurusan);
            this.desJurusan = (TextView) itemView.findViewById(R.id.deskripsi_jurusan);
            this.cv = (CardView) itemView.findViewById(R.id.cv);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            Context Mcontext = itemView.getContext();
            Intent intent = new Intent(Mcontext, JurusanDetailActivity.class);
            intent.putExtra(JurusanDetailActivity.KEY_NAMA, namaJurusan.getText());
            intent.putExtra(JurusanDetailActivity.KEY_DESKRIPSI, desJurusan.getText());
            intent.putExtra(JurusanDetailActivity.KEY_LINK, aLink);
            Mcontext.startActivity(intent);

        }
    }
}