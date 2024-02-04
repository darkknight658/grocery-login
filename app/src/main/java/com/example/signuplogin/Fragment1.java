package com.example.signuplogin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;




public class Fragment1 extends Fragment {
    private Button infoButton;
    private Button cartButton;
    private Button ordersButton;
    private TextView resultTextView;
    private InternetReceiver broadcastReceiver;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_1, container, false);
        Button infoButton = rootView.findViewById(R.id.infobutton);
        Button cartButton = rootView.findViewById(R.id.cartbutton);
        Button ordersButton = rootView.findViewById(R.id.ordersbutton);

        // Set click listeners for each button in the fragment
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment1,new infofragment()).commit();
            }
        });

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ordersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return rootView;
    }

    }

