package com.example.adithyaiyer.planner;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditTask extends AppCompatActivity {

    private EditText taskname;
    private EditText date;
    private EditText time;
    private EditText location;
    private EditText person1;
    private EditText person2;
    private EditText person3;
    private EditText names;
    private task kya;
    private List<customer> customerListForAdddition2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kya=new task("dance","2018-12-12","12:12:12","sakinaka");
        setContentView(R.layout.activity_edit_task);

        Bundle data=getIntent().getExtras();
        int taskId=data.getInt("pkvalue");

        taskname= (EditText)findViewById(R.id.addTaskNameForAddition);
        date = (EditText)findViewById(R.id.addTaskDateforAddition);
        time= (EditText)findViewById(R.id.addTaskTimeforAddition);
        location=(EditText)findViewById(R.id.addTaskLocationforAddition);
        person1 = (EditText)findViewById(R.id.customerName1);
        person2 = (EditText)findViewById(R.id.customerName2);
        person3=(EditText) findViewById(R.id.customerName3);

      //  getTaskNow(taskId);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServiceCustomer.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiServiceCustomer api= retrofit.create(ApiServiceCustomer.class);
        Call<task> call=api.getTaskData(taskId);
        call.enqueue(new Callback<task>() {
            @Override
            public void onResponse(Call<task> call, Response<task> response) {
                kya=response.body();
                taskname.setText(kya.getTaskName().toString());
                date.setText(kya.getTaskDate().toString());
                time.setText(kya.getTaskTime().toString());
                location.setText(kya.getLocation().toString());
                Toast.makeText(EditTask.this,
                        " "+kya.getTaskName().toString()+" "+kya.getLocation().toString(), Toast.LENGTH_SHORT).show();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ApiServiceCustomer.ROOT_URL)
                        .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                        .build();
                ApiServiceCustomer api1= retrofit.create(ApiServiceCustomer.class);
                Call<List<customer>> call2=api1.getMyJSONCustomer();

                call2.enqueue(new Callback<List<customer>>() {
                    @Override
                    public void onResponse(Call<List<customer>> call, Response<List<customer>> response) {
                        customerListForAdddition2=response.body();
                        ArrayList<String> listNamesInvolved=customerInvolvedNames(kya.getCustomersInvolved());
                        person1 = (EditText)findViewById(R.id.customerName1);
                        person2 = (EditText)findViewById(R.id.customerName2);
                        person3=(EditText) findViewById(R.id.customerName3);

                        if(listNamesInvolved.size()==2){
                            person1.setText(listNamesInvolved.get(1));
                        }
                        if(listNamesInvolved.size()==3){
                            person1.setText(listNamesInvolved.get(1));
                            person2.setText(listNamesInvolved.get(2));
                        }
                        if(listNamesInvolved.size()>=4){
                            person1.setText(listNamesInvolved.get(1));
                            person2.setText(listNamesInvolved.get(2));
                            person2.setText(listNamesInvolved.get(3));
                        }




                    }

                    @Override
                    public void onFailure(Call<List<customer>> call, Throwable t) {

                    }
                });


            }

            @Override
            public void onFailure(Call<task> call2, Throwable t) {

            }
        });
     /*   getCustomerList();
        List<Integer> listy=kya.getCustomersInvolved();
        String t= customerInvolvedNames(listy);
        names.setText(t);*/

    }

    private void getTaskNow(int i){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServiceCustomer.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiServiceCustomer api= retrofit.create(ApiServiceCustomer.class);
        Call<task> call=api.getTaskData(i);
        call.enqueue(new Callback<task>() {
            @Override
            public void onResponse(Call<task> call, Response<task> response) {
                kya=response.body();

            }

            @Override
            public void onFailure(Call<task> call, Throwable t) {

            }
        });


    }

    private List<customer> getCustomerList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServiceCustomer.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        ApiServiceCustomer api1= retrofit.create(ApiServiceCustomer.class);
        Call<List<customer>> call=api1.getMyJSONCustomer();

        call.enqueue(new Callback<List<customer>>() {
            @Override
            public void onResponse(Call<List<customer>> call, Response<List<customer>> response) {
                customerListForAdddition2=response.body();

            }

            @Override
            public void onFailure(Call<List<customer>> call, Throwable t) {

            }
        });
return customerListForAdddition2;
    }

    private ArrayList<String> customerInvolvedNames(List<Integer> list){
        ArrayList<String> stringList=new ArrayList<String>() ;

        int size=list.size();
        int ll=0;
        for(int i=0;i<size;i++){
            for(int j=0;j<customerListForAdddition2.size();j++){
                if(customerListForAdddition2.get(j).getId()==list.get(i)){
                    if(customerListForAdddition2.get(j).getUsername().toString().equals("paul")==false){
                  stringList.add(ll,customerListForAdddition2.get(j).getUsername().toString());  ll++;}
                    break;
                }
            }


        }

        return stringList;
    }

public void goEdit(View view){
    taskname= (EditText)findViewById(R.id.addTaskNameForAddition);
    date = (EditText)findViewById(R.id.addTaskDateforAddition);
    time= (EditText)findViewById(R.id.addTaskTimeforAddition);
    location=(EditText)findViewById(R.id.addTaskLocationforAddition);
    person1 = (EditText)findViewById(R.id.customerName1);
    person2 = (EditText)findViewById(R.id.customerName2);
    person3=(EditText) findViewById(R.id.customerName3);
    task t=new task(taskname.getText().toString(),date.getText().toString(),time.getText().toString(),location.getText().toString());

    Bundle data=getIntent().getExtras();
    final int Id=data.getInt("pkvalue");
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiServiceCustomer.ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
            .build();
    ApiServiceCustomer api= retrofit.create(ApiServiceCustomer.class);
    Call<task> call=api.getTaskData(Id);
    call.enqueue(new Callback<task>() {
        @Override
        public void onResponse(Call<task> call, Response<task> response) {
            kya=response.body();

            kya.setTaskName(taskname.getText().toString());
            kya.setTaskDate(date.getText().toString());
            kya.setTaskTime(time.getText().toString());
            kya.setLocation(location.getText().toString());

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiServiceCustomer.ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                    .build();
            ApiServiceCustomer api= retrofit.create(ApiServiceCustomer.class);
            Call<task> call2=api.updateTask(Id,kya);
            call2.enqueue(new Callback<task>() {
                @Override
                public void onResponse(Call<task> call, Response<task> response) {
                    Toast.makeText(EditTask.this,
                            "updating task successful", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<task> call, Throwable t) {

                }
            });




        }

        @Override
        public void onFailure(Call<task> call, Throwable t) {

        }
    });
    data=getIntent().getExtras();
    int taskId=data.getInt("pkvalue");
    Intent go=new Intent(EditTask.this,Main_task.class);
    // Bundle data=getIntent().getExtras();

  //  go.putExtra("pkvalue",);
   // startActivity(go);


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
