package ro.mta.serverstatemonitor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Custom adapter class for devices ListView
  */
public class DevsListViewAdapter extends BaseAdapter {

    private Context ctx;
    private List<Device> devices;

    public DevsListViewAdapter(Context ctx, List<Device> devices) {
        this.ctx = ctx;
        this.devices = devices;
    }

    @Override
    public int getCount() {
        return this.devices.size();
    }

    @Override
    public Object getItem(int i) { return this.devices.get(i); }

    @Override
    public long getItemId(int i) { return 0; }

    // show devices data in ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        @SuppressLint("ViewHolder") View v = inflater.inflate(R.layout.item_device_layout, parent, false);
        ImageView imageView = v.findViewById(R.id.device_imageview);
        TextView textView1 = v.findViewById(R.id.device_name_textview); // device name identifier
        TextView textView2 = v.findViewById(R.id.device_brand_textview); // Lenovo/Cisco/HP,etc
        TextView textView3 = v.findViewById(R.id.device_ip_textview); // IPv4 address
        TextView textView4 = v.findViewById(R.id.device_location_textview); // city, street, number
        TextView textView5 = v.findViewById(R.id.device_type_textview); // PC / Server / Datacenter
        // get device to be displayed
        Device device = (Device) getItem(position);
        imageView.setImageResource(device.getImage());
        textView1.setText(device.getName());
        textView2.setText(device.getBrand());
        textView3.setText(device.getIp());
        textView4.setText(device.getLocation());
        textView5.setText(device.getType());
        return v;
    }

}
