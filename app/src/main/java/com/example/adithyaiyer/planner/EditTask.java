package com.example.adithyaiyer.planner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
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
    int Id=data.getInt("pkvalue");
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiServiceCustomer.ROOT_URL)
            .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
            .build();
    ApiServiceCustomer api= retrofit.create(ApiServiceCustomer.class);
  //  Call<task> call=api.changeTask(Id,taskname.getText().toString(),date.getText().toString(),time.getText().toString(),location.getText().toString());
   /* api.changeTask(Id, taskname.getText().toString(), date.getText().toString(), time.getText().toString(), location.getText().toString()).enqueue(new Callback<task>() {
        @Override
        public void onResponse(Call<task> call, Response<task> response) {
            Toast.makeText(EditTask.this,
                    "edit task successful", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<task> call, Throwable t) {
            Toast.makeText(EditTask.this,
                    "adding task fail", Toast.LENGTH_SHORT).show();
        }
    });*/


}

    public int getIdFromUsername(List<customer> customerList,String username){

        for(int i=0;i<customerList.size();i++){
            if(customerList.get(i).getUsername().toString().equals(username)){
                return customerList.get(i).getId();
            }

        }
        return 0;

    }



}
