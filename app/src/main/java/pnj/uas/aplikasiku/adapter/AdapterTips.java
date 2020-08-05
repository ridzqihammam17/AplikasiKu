package pnj.uas.aplikasiku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import pnj.uas.aplikasiku.R;
import pnj.uas.aplikasiku.model.ModelTips;
public class AdapterTips extends ArrayAdapter<ModelTips> {
    Context context;
    int resource;

    public AdapterTips(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Holder holder;

        if (convertView==null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            holder.imageTips = convertView.findViewById(R.id.Imagetips);
            holder.txtJudultips = convertView.findViewById(R.id.txtJudultips);
            holder.txtDeskripsitips = convertView.findViewById(R.id.txtDeskripsitips);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.txtJudultips.setText(getItem(position).getJudultips());
        holder.txtDeskripsitips.setText(getItem(position).getDeskripsitips());
        Picasso.get().load(""+getItem(position).getImagetips()).into(holder.imageTips);

        return convertView;
    }

    class Holder {
        ImageView imageTips;
        TextView txtJudultips, txtDeskripsitips;
    }
}
