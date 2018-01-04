package com.app.keyalive.crudjava;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BuatDataActivity extends AppCompatActivity {
    protected Cursor cursor;
    private TextView textView1;
    private EditText edtNomor;
    private TextView textView2;
    private EditText edtNama;
    private TextView textView3;
    private EditText edtTgllahir;
    private TextView textView4;
     RadioGroup rbkelamin;
    private TextView textView5;
    private EditText edtAlamat;
    private Button btnSimpan;
    private Button btnBack;

    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd", Locale.ENGLISH);

    DatePickerDialog datePickerDialog;
    Calendar dateCalendar;
    DataHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_data);

         dbhelper = new DataHelper(this);

        textView1 = findViewById(R.id.textView1);
        edtNomor = findViewById(R.id.edt_nomor);
        textView2 = findViewById(R.id.textView2);
        edtNama = findViewById(R.id.edt_nama);
        textView3 = findViewById(R.id.textView3);
        edtTgllahir = findViewById(R.id.edt_tgllahir);
        textView4 = findViewById(R.id.textView4);
        rbkelamin = findViewById(R.id.rb_kelamin);
        textView5 = findViewById(R.id.textView5);
        edtAlamat = findViewById(R.id.edt_alamat);
        btnSimpan = findViewById(R.id.btn_simpan);
        btnBack = findViewById(R.id.btn_back);

edtTgllahir.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Calendar newCalendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(BuatDataActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                edtTgllahir.setText(" "+dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
});
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();

                int gender=rbkelamin.getCheckedRadioButtonId();
                RadioButton jk=(RadioButton)findViewById(gender);
                String inputjk= String.valueOf(jk.getText().toString());

                db.execSQL("insert into biodata (nomor, nama, tgl_lahir, jenkel, alamat) values('"+
                        edtNomor.getText().toString()+"','"+
                        edtNama.getText().toString()+"','"+
                        edtTgllahir.getText().toString()+"','"+
                        inputjk+"','"+
                        edtAlamat.getText().toString()+"')");
                        Toast.makeText(getApplicationContext(),"Data telah disimpan",Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
