package alessandro.draganddrop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import alessandro.draganddrop.R;
import alessandro.draganddrop.model.Item;

/**
 * Created by Alessandro on 12/01/2016.
 */
public class MyAdapterRecyclerView extends RecyclerView.Adapter<MyAdapterRecyclerView.MyViewHolder>{

    private List<Item> mList;

    public MyAdapterRecyclerView(List<Item> mList) {
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item item = mList.get(position); // Object Item
        holder.setName(item.getName()); // Name
        holder.setDescription(item.getDescription()); // Description
        holder.setImage(item.getIdImage()); // Image
    }

    @Override
    public int getItemCount() {
        if (mList == null){
            return 0;
        }else {
            return mList.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView ivUser;
        TextView tvName, tvDescription;
        public MyViewHolder(View itemView) {
            super(itemView);
            ivUser          = (ImageView)itemView.findViewById(R.id.ivUser);
            tvName          = (TextView)itemView.findViewById(R.id.tvName);
            tvDescription   = (TextView)itemView.findViewById(R.id.tvDescription);
        }

        public void setName(String name){
            tvName.setText(name);
        }

        public void setDescription(String description){
            tvDescription.setText(description);
        }

        public void setImage(int idImage){
            Picasso.with(ivUser.getContext()).
                    load(idImage).
                    centerCrop().
                    resize(60,60).
                    transform(new CircleTransform()).
                    into(ivUser);
        }

    }

}
