package rezende.israel.minhaempresa.datePicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    DatePickerDialog.OnDateSetListener listener;

    public DatePickerFragment(DatePickerDialog.OnDateSetListener teste) {
        listener = teste;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(requireContext(), listener, ano, mes, dia);
    }
}
