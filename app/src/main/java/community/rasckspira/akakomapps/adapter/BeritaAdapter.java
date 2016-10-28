package community.rasckspira.akakomapps.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import community.rasckspira.akakomapps.model.Data;
import community.rasckspira.akakomapps.DetailBeritaActivity;
import community.rasckspira.akakomapps.R;

/**
 * Created by kristiawan on 12/12/15.
 */
public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.MyViewHolder> {


    List<Data> mItems;
    Context mContext;

    public BeritaAdapter(Context context, List<Data> mItems) {

        this.mItems = mItems;
        this.mContext = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_berita_view, null);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Data item = mItems.get(i);

        myViewHolder.judul.setText(item.getJudul().toString());
        myViewHolder.tgl.setText(item.getWaktu().toString());
        myViewHolder.deskripsi.setText(item.getDetail());
        myViewHolder.urlPhoto = item.getFoto();

        if(item.getFoto() != ""){
            Glide.with(mContext).load(item.getFoto()).asBitmap().placeholder(R.drawable.placeholder).into(myViewHolder.gambar);
        }else{
            myViewHolder.gambar.setVisibility(View.GONE);
        }



    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView judul;
        public TextView tgl, deskripsi;
        ImageView gambar;
        public CardView cv;
        public String urlPhoto;


        public MyViewHolder(View itemView) {
            super(itemView);

            //robotobold = Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Bold.ttf");
            //robotoregular = Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Regular.ttf");


            this.judul = (TextView) itemView.findViewById(R.id.nama_berita);
            this.tgl = (TextView) itemView.findViewById(R.id.tgl_berita);
            this.deskripsi = (TextView) itemView.findViewById(R.id.deskripsi_berita);
            this.gambar = (ImageView) itemView.findViewById(R.id.foto_berita);
            this.cv = (CardView) itemView.findViewById(R.id.cv_berita);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            Context Mcontext = itemView.getContext();
            Intent intent = new Intent(Mcontext, DetailBeritaActivity.class);
            intent.putExtra(DetailBeritaActivity.KEY_JUDUL, judul.getText());
            intent.putExtra(DetailBeritaActivity.KEY_TANGGAL, tgl.getText());
            intent.putExtra(DetailBeritaActivity.KEY_URL_FOTO, urlPhoto);
            intent.putExtra(DetailBeritaActivity.KEY_DESKRIPSI, deskripsi.getText());
            intent.putExtra("title","Berita");
            Mcontext.startActivity(intent);

        }
    }
}
