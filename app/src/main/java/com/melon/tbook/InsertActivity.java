package com.melon.tbook;
/**
 * @Project：TBOOK
 * @Author: Liwei Wang
 * @Date: 3/1/2024
 */
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import com.melon.tbook.common.MoneyTextWatcher;

import java.util.Calendar;

public class InsertActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_incomeid;
    private EditText et_money;
    private TextView et_date;
    private EditText et_type;
    private EditText et_note;
    private Button btn_sureincome;
    //static String type="";

    private Calendar calendar;

    private DatePickerDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        initView();
    }

    private void initView() {
        et_incomeid= (EditText) findViewById(R.id.et_incomeid);
        et_money= (EditText) findViewById(R.id.et_money);
        et_money.addTextChangedListener(new MoneyTextWatcher(et_money));

        et_date= (TextView) findViewById(R.id.et_date);
        et_date.setOnClickListener(this);
        et_type= (EditText) findViewById(R.id.et_type);
        et_note= (EditText) findViewById(R.id.et_note);
        btn_sureincome= (Button) findViewById(R.id.btn_sureincome);
        btn_sureincome.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.btn_sureincome:
                insert(v);
                break;
            case R.id.et_date:
                selectDate(v);
                break;
            default:
                Toast.makeText(this, "Nothing to show", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void selectDate(View v) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(InsertActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                        et_date.setText(date);
                    }
                }, currentYear, currentMonth, currentDay);

        Calendar minDateCalendar = Calendar.getInstance();
        minDateCalendar.add(Calendar.YEAR, -100);
        datePickerDialog.getDatePicker().setMinDate(minDateCalendar.getTimeInMillis());
        Calendar maxDateCalendar = Calendar.getInstance();
        maxDateCalendar.add(Calendar.YEAR, 100);
        datePickerDialog.getDatePicker().setMaxDate(maxDateCalendar.getTimeInMillis());

        datePickerDialog.getWindow().setGravity(Gravity.BOTTOM);

        datePickerDialog.setButton(DatePickerDialog.BUTTON_NEUTRAL, "今天", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Calendar todayCalendar = Calendar.getInstance();
                int todayYear = todayCalendar.get(Calendar.YEAR);
                int todayMonth = todayCalendar.get(Calendar.MONTH);
                int todayDay = todayCalendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog.updateDate(todayYear, todayMonth, todayDay);
                String date = todayYear + "-" + (todayMonth + 1) + "-" + todayDay;
                et_date.setText(date);
            }
        });

        datePickerDialog.show();
    }


    private void insert(View v) {
        //当单击“添加”按钮时，获取信息
        String id=et_incomeid.getText().toString().trim();
        String money = et_money.getText().toString().trim();
        String date = et_date.getText().toString().trim();
        String type = et_type.getText().toString().trim();
        String note = et_note.getText().toString();
        //添加
        Money o =new Money();
        o.id=id;
        o.money=money;
        o.date = date;
        o.type = type;
        o.note = note;
        //创建数据库访问对象
        MoneyDAO dao = new MoneyDAO(getApplicationContext());
        //打开数据库
        dao.open();
        //执行数据库访问方法
        long result = dao.addIncome(o);
        if (result > 0) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        }
        //关闭数据库
        dao.close();
        //关闭活动
        finish();
    }






   /* private void init(){
        mSpinner=(Spinner) findViewById(R.id.spinner);
        mStringArray=getResources().getStringArray(R.array.test_string_array);

        //使用自定义的ArrayAdapter
        mAdapter = new TestArrayAdapter(InsertActivity.this,mStringArray);

        mSpinner.setAdapter(mAdapter);
        //监听Item选中事件
        mSpinner.setOnItemSelectedListener(new ItemSelectedListenerImpl());
    }
    static class ItemSelectedListenerImpl implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3) {
            type =mStringArray[position];
            //  System.out.println("选中了:"+mStringArray[position]);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }*/
}
