package com.thatgame.langcards;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class LangDialog extends DialogFragment {

    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();

        View dialogView = layoutInflater.inflate(R.layout.dialog_new_lang, null);

        final EditText editText4 = (EditText) dialogView.findViewById(R.id.editTextText4);

        Button btnCancel = dialogView.findViewById(R.id.cancel_button1);
        Button btnOk = dialogView.findViewById(R.id.ok_button1);

        btnCancel.setOnClickListener(v -> dismiss());
        btnOk.setOnClickListener(v -> {
            LangChooserActivity callingActivity = (LangChooserActivity) getActivity();
            String str1 = editText4.getText().toString();
            if (!(str1.isEmpty())) {
                callingActivity.db.createNewTableDict(str1 + "_" + callingActivity.login);
                callingActivity.init();
                dismiss();
            } else {
                Toast.makeText(callingActivity, "Какой язык хотите добавить?", Toast.LENGTH_LONG).show();
            }
        });

        builder.setView(dialogView).setMessage("Какой язык хотите добавить?");



        return builder.create();
    }
}
