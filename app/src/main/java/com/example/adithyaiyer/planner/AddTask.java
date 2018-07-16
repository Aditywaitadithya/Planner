package com.example.adithyaiyer.planner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class AddTask extends AppCompatActivity {
    private Button dateBut,timebut;
    private EditText taskname;
    private EditText date;
    private EditText time;
    private EditText location;
    private EditText person;
    private EditText person2;
    private EditText person3;
    private CheckBox ch;
    private int pkvalue;
    private joiningTask jk;
    private List<customer> customerListForAdddition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Bundle data=getIntent().getExtras();
        dateBut=(Button)findViewById(R.id.buttonDate);
        timebut=(Button)findViewById(R.id.buttonTime);
        String currentDate=data.getString("dateOfTask");
        date = (EditText)findViewById(R.id.addTaskDate);
        date.setText(currentDate);
        time= (EditText)findViewById(R.id.addTaskTime);



        //    timebut.setOnClickListener(this);


    }




    public void addTaskNow(View view){

        taskname= (EditText)findViewById(R.id.addTaskName);
        date = (EditText)findViewById(R.id.addTaskDate);
        time= (EditText)findViewById(R.id.addTaskTime);
        location=(EditText)findViewById(R.id.addTaskLocation);
        person = (EditText)findViewById(R.id.addTaskNo);
        person2 = (EditText)findViewById(R.id.addTaskNo2);
        person3 = (EditText)findViewById(R.id.addTaskNo3);



        Bundle data=getIntent().getExtras();
        task t=new task(taskname.getText().toString(),date.getText().toString(),time.getText().toString(),location.getText().toString());
        addTaskToDatabase(t);




        pkvalue=data.getInt("pkvals");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServiceCustomer.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        getCustomerList();

        ApiServiceCustomer api= retrofit.create(ApiServiceCustomer.class);
        api.getLatest(pkvalue).enqueue(new Callback<task>() {
            @Override
            public void onResponse(Call<task> call, Response<task> response) {
                task k=response.body();


                joiningTask jk=new joiningTask(1,1,"2018-12-12");
                jk.setCustomer(pkvalue);
                jk.setDateOfJoining(date.getText().toString());
                jk.setTaskDetails(k.getId());
                addRelation(jk);

                joiningTask jk2=new joiningTask(1,1,"2018-12-12");
                int idFromUsername =  getIdFromUsername(customerListForAdddition,person.getText().toString());
                if(idFromUsername!=0){
                    jk2.setCustomer(idFromUsername);
                    jk2.setTaskDetails(k.getId());
                    jk2.setDateOfJoining(date.getText().toString());
                    addRelation(jk2);
                    Toast.makeText(AddTask.this,
                            "username"+person.getText().toString()+" does not exist", Toast.LENGTH_SHORT).show();

                }


            joiningTask jk3=new joiningTask(1,1,"2018-12-12");
                int idFromUsername3 =  getIdFromUsername(customerListForAdddition,person2.getText().toString());
                if(idFromUsername3!=0){
                    jk3.setCustomer(idFromUsername3);
                    jk3.setTaskDetails(k.getId());
                    jk3.setDateOfJoining(date.getText().toString());
                    Toast.makeText(AddTask.this,
                            "username"+person2.getText().toString()+" does not exist", Toast.LENGTH_SHORT).show();
                }


                joiningTask jk4=new joiningTask(1,1,"2018-12-12");
                int idFromUsername4 =  getIdFromUsername(customerListForAdddition,person3.getText().toString());
                if(idFromUsername4!=0){
                    jk4.setCustomer(idFromUsername4);
                    jk4.setTaskDetails(k.getId());
                    jk4.setDateOfJoining(date.getText().toString());
                    Toast.makeText(AddTask.this,
                            "username "+person3.getText().toString()+" does not exist", Toast.LENGTH_SHORT).show();
                }






            }

            @Override
            public void onFailure(Call<task> call, Throwable t) {
                Toast.makeText(AddTask.this,
                        "finding last task failed", Toast.LENGTH_SHORT).show();
            }
        });


        Intent go=new Intent(AddTask.this,Main_task.class);
       // Bundle data=getIntent().getExtras();

        go.putExtra("pkvalue",pkvalue);
        startActivity(go);



    }

    private void addRelation(joiningTask jk){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServiceCustomer.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiServiceCustomer api1= retrofit.create(ApiServiceCustomer.class);
        api1.saveRelation(jk).enqueue(new Callback<joiningTask>() {
            @Override
            public void onResponse(Call<joiningTask> call, Response<joiningTask> response) {
                Toast.makeText(AddTask.this,"joining task no pain",Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<joiningTask> call, Throwable t) {
                Toast.makeText(AddTask.this,"joining task yes pain",Toast.LENGTH_SHORT);

            }
        });


    }

    private void addTaskToDatabase(task t){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServiceCustomer.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiServiceCustomer api= retrofit.create(ApiServiceCustomer.class);
        api.saveTask(t).enqueue(new Callback<task>() {
            @Override
            public void onResponse(Call<task> call, Response<task> response) {
                Toast.makeText(AddTask.this,
                        "adding task successful", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<task> call, Throwable t) {

                Toast.makeText(AddTask.this,
                        "adding task failed", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getCustomerList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServiceCustomer.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        ApiServiceCustomer api= retrofit.create(ApiServiceCustomer.class);
        Call<List<customer>> call=api.getMyJSONCustomer();

        call.enqueue(new Callback<List<customer>>() {
            @Override
            public void onResponse(Call<List<customer>> call, Response<List<customer>> response) {
                customerListForAdddition=response.body();

            }

            @Override
            public void onFailure(Call<List<customer>> call, Throwable t) {
                Toast.makeText(AddTask.this,
                        "internet fail", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public int getIdFromUsername(List<customer> customerList,String username){

        for(int i=0;i<customerList.size();i++){
            if(customerList.get(i).getUsername().toString().equals(username)){
                return customerList.get(i).getId();
            }

        }
        return 0;

    }
    public void datePicked(View view){
        Calendar c=Calendar.getInstance();

        DatePickerDialog d=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int ill=i1+1;
                    date.setText(String.valueOf(i)+"-"+String.valueOf(ill)+"-"+String.valueOf(i2)
                    );
            }
        },2018,12,12);
        d.show();

    }
public void timeSet(View view){
    TimePickerDialog p=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {

            time.setText(String.valueOf(i)+":"+String.valueOf(i1)+":"+String.valueOf(0)+String.valueOf(0));
        }
    },12,12,false);
    p.show();
}



    }



