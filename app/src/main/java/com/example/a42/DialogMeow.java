package com.example.a42;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DialogMeow extends DialogFragment {

    TextView tv;
    EditText login,password;
    TimePicker a;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        login = new EditText(getContext());
        password = new EditText(getContext());
        LayoutInflater b = getActivity().getLayoutInflater();
       View view = b.inflate(R.layout.dialogmeow,null);
        builder.setTitle("Авторизация")
                .setMessage("Введите Логин и Пароль")
                .setView(view)
                .setPositiveButton("Войти", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Закрываем окно

                        dialog.cancel();
                    }
                })

                .setNegativeButton("Закрыть", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Закрываем окно
                        Toast.makeText(getContext(), "Необходимо Войти!",
                                Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });

        return builder.create();
    }
}