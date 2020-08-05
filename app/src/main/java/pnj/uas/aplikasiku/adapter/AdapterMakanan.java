package pnj.uas.aplikasiku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import pnj.uas.aplikasiku.R;
import pnj.uas.aplikasiku.model.ModelMakanan;

public class AdapterMakanan extends ArrayAdapter<ModelMakanan> {
    Context context;
    int resource;

    public AdapterMakanan(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    class Holder {
        TextView txtNama, txtHarga;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder;

        if(convertView==null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            holder.txtNama = convertView.findViewById(R.id.txtNama);
            holder.txtHarga = convertView.findViewById(R.id.txtHarga);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.txtNama.setText("Makanan: "+ getItem(position).getNama());
        holder.txtHarga.setText("Harga: "+ getItem(position).getHarga());

        return convertView;
    }


}
