package com.melon.tbook.common;
/**
 * @Project：TBOOK
 * @Author: Liwei Wang
 * @Date: 3/1/2024
 */
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MoneyTextWatcher implements TextWatcher {

    private EditText editText;

    public MoneyTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
        // Not used in this example
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        // Not used in this example
    }

    @Override
    public void afterTextChanged(Editable editable) {
//        editText.removeTextChangedListener(this);
//
//        String originalString = editable.toString();
//
//        String[] parts = originalString.split("\\.");
//        String integerPart = parts[0];
//        String decimalPart = parts.length > 1 ? parts[1] : "";
//
//        StringBuilder formattedString = new StringBuilder();
//        int len = integerPart.length();
//        for (int i = 0; i < len; i++) {
//            formattedString.append(integerPart.charAt(i));
//            if ((len - i - 1) % 3 == 0 && i < len - 1) {
//                formattedString.append(",");
//            }
//        }
//
//        if (!decimalPart.isEmpty()) {
//            formattedString.append(".").append(decimalPart.substring(0, Math.min(2, decimalPart.length())));
//        }
//
//        editText.setText(formattedString.toString());
//        editText.setSelection(formattedString.length());
//
//        editText.addTextChangedListener(this);
    }
}
