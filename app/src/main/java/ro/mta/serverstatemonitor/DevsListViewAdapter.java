package ro.mta.serverstatemonitor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// Custom adapter for devices list
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(R.layout.device_layout, parent, false);
        ImageView imageView = v.findViewById(R.id.device_imageview);
        TextView textView1 = v.findViewById(R.id.device_name_textview);
        TextView textView2 = v.findViewById(R.id.device_brand_textview);
        TextView textView3 = v.findViewById(R.id.device_ip_textview);
        TextView textView4 = v.findViewById(R.id.device_location_textview);
        TextView textView5 = v.findViewById(R.id.device_type_textview);
        Device device = (Device) getItem(position);
        imageView.setImageResource(device.getImage());
        textView1.setText(device.getName());
        textView2.setText(device.getBrand());
        textView3.setText(device.getIp());
        // TODO: check device connectivity and display state (up/down) accordingly
        // TODO: search for ways to display device uptime after checking connectivity
        textView4.setText(device.getLocation());
        textView5.setText(device.getType());
        return v;
    }
}
