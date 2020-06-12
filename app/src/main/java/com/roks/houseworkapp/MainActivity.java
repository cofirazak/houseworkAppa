package com.roks.houseworkapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_work, R.id.navigation_score, R.id.navigation_history)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void workDoneClickEvent(View view) {
        AlertDialog.Builder workDoneDialog = new AlertDialog.Builder(this);
        workDoneDialog.setTitle("Завершение дела.");
// Setting Dialog Message
        workDoneDialog.setMessage("Вы хотите завершить дело?");
// Setting Icon to Dialog
        workDoneDialog.setIcon(R.drawable.ic_action_complite);
// Setting Positive "Yes" Btn
        workDoneDialog.setPositiveButton("Да",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        Toast.makeText(view.getContext(),
                                "You clicked on YES", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
// Setting Negative "NO" Btn
        workDoneDialog.setNegativeButton("Нет",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        Toast.makeText(view.getContext(),
                                "You clicked on NO", Toast.LENGTH_SHORT)
                                .show();
                        dialog.cancel();
                    }
                });
// Showing Alert Dialog
        workDoneDialog.show();
    }
}
