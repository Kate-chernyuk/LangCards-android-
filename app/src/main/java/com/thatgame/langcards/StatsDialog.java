package com.thatgame.langcards;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class StatsDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();

        View dialogView = layoutInflater.inflate(R.layout.stats, null);

        final TextView textView = (TextView) dialogView.findViewById(R.id.textView);

        Button btnOk = dialogView.findViewById(R.id.button);

        LangChooserActivity langChooserActivity = (LangChooserActivity) getActivity();
        if (langChooserActivity.flag == 1) {
            textView.setSingleLine(false);
            textView.setText("Вы просмотрели " + langChooserActivity.watched + "\nкарточек за эту ходку");
        } else {
            textView.setSingleLine(false);
            textView.setText("Статистика доступна только\nзарегестрированным пользователям");
        }

        btnOk.setOnClickListener(v -> dismiss());

        builder.setView(dialogView).setMessage("Статистика");

        return builder.create();
    }
}
