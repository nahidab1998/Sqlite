package com.example.phonebook.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonebook.Contact;
import com.example.phonebook.Database.SqliteDB;
import com.example.phonebook.MainActivity;
import com.example.phonebook.R;

import java.util.ArrayList;
import java.util.List;
//import com.example.phonebook.model.UserModel;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Myviewholder>{

//    private Activity context;
//    private List<UserModel> models;
    ArrayList<Contact> arrayList;

    int picture;
    Context context;
    MainActivity mainActivity;
    SqliteDB db;
    Dialog dialog;
    EditText editTextname;
    EditText editTextphone;
    ImageButton save;
    ImageButton cancel;





    public HomeAdapter(ArrayList<Contact> arrayList, int picture, Context context,MainActivity mainActivity,SqliteDB db) {
        this.arrayList = arrayList;
        this.context=context;
        this.picture = picture;
        this.mainActivity = mainActivity;
        this.db=db;

    }


    @Override
    public Myviewholder onCreateViewHolder( ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View v= layoutInflater.inflate(R.layout.my_row , parent ,false);
        Myviewholder myviewholder=new Myviewholder(v);
        return myviewholder;

    }

    @Override
    public void onBindViewHolder(Myviewholder holder, @SuppressLint("RecyclerView") int position) {

//        UserModel model = models.get(position);
        Contact contact =arrayList.get(position);
        //control view
        holder.txtname.setText(contact.getTit());
        holder.txtmobile.setText(contact.getPh());
        holder.pic.setImageResource(picture);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.deleteCourse(contact.getId());
                arrayList.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
                Toast.makeText(context, "deleted...", Toast.LENGTH_SHORT).show();
            }
        });



        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);
                dialog.show();
                editTextname=dialog.findViewById(R.id.add_edit_name);
                editTextphone= dialog.findViewById(R.id.add_edit_number);
//                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                editTextname.setText(contact.getTit());
                editTextphone.setText(contact.getPh());
                save=dialog.findViewById(R.id.checkedd);

                cancel=dialog.findViewById(R.id.close);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = editTextname.getText().toString();
                        String phone= editTextphone.getText().toString();
                        db.update_data(name ,phone , contact.getId());
                        arrayList.get(position).setTit(name);
                        arrayList.get(position).setPh(phone);
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

//                inputLayout_Name = dialog.findViewById(R.id.add_edit_name);

//                Toast.makeText(context, "edite", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder {

//        creat views
        TextView txtname , txtmobile;
        CircleImageView pic;
        ImageButton delete;
        ImageButton edit;

        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            txtname=itemView.findViewById(R.id.txtname);
            txtmobile=itemView.findViewById(R.id.txtnumber);
            pic=itemView.findViewById(R.id.imageContact);
            delete=itemView.findViewById(R.id.delete_btn);
            edit=itemView.findViewById(R.id.edit_btn);

        }
    }

}

