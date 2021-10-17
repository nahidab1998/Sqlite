package adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.phonebook.R;
import java.util.List;
import model.UserModel;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Myviewholder>{

    private Activity context;
    private List<UserModel> models;

    public HomeAdapter(Activity context, List<UserModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.my_row , parent ,false);
        return new Myviewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, int position) {

        UserModel model = models.get(position);

        //control view
        holder.txtname.setText(model.getName());
        holder.txtmobile.setText(model.getMobile());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder {


//        creat views

        TextView txtname , txtmobile;


        public Myviewholder(@NonNull View itemView) {

            super(itemView);

            txtname=itemView.findViewById(R.id.txtname);
            txtmobile=itemView.findViewById(R.id.txtnumber);

        }
    }
}

