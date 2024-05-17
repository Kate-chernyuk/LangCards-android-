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

public class WordsDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();

        View dialogView = layoutInflater.inflate(R.layout.dialog_new_word, null);

        final EditText editText2 = (EditText) dialogView.findViewById(R.id.editTextText2);
        final EditText editText3 = (EditText) dialogView.findViewById(R.id.editTextText3);

        Button btnCancel = dialogView.findViewById(R.id.cancel_button);
        Button btnOk = dialogView.findViewById(R.id.ok_button);

        btnCancel.setOnClickListener(v -> dismiss());
        btnOk.setOnClickListener(v -> {
            FlashCardsActivity callingActivity = (FlashCardsActivity) getActivity();
            String str1 = editText2.getText().toString(), str2 = editText3.getText().toString();
            if (!(str1.isEmpty() || str2.isEmpty())) {
                callingActivity.db.updateDict(callingActivity.lang + "_" + callingActivity.login, editText2.getText().toString(), editText3.getText().toString());
                callingActivity.initAdapter();
                                dismiss();
            } else {
                Toast.makeText(callingActivity, "Введите и слово, и его перевод", Toast.LENGTH_LONG).show();
            }
        });

        builder.setView(dialogView).setMessage("Введите новое слово, которое хотите добавить");

        return builder.create();
    }
}
