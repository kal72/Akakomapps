package community.rasckspira.akakomapps;

/**
 * Created by kristiawan on 09/12/15.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dedevalen on 14/09/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {


    List<Data> mItems;
    Context mContext;

    public RecyclerAdapter(Context context, List<Data> mItems) {

        this.mItems = mItems;
        this.mContext = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view, null);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Data item = mItems.get(i);

        myViewHolder.judul.setText(item.getJudul());


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView judul;
        public CardView cv;


        public MyViewHolder(View itemView) {
            super(itemView);

            //robotobold = Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Bold.ttf");
            //robotoregular = Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Regular.ttf");


            //this.img = (ImageView)itemView.findViewById(R.id.img);
            this.judul = (TextView) itemView.findViewById(R.id.person_name);
            this.cv = (CardView) itemView.findViewById(R.id.cv);
            // this.desWisata = (TextView)itemView.findViewById(R.id.des_wisata);
            // judulWisata.setTypeface(robotobold);
            // desWisata.setTypeface(robotoregular);
            //itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            //Context Mcontext = itemView.getContext();

        }
    }
}