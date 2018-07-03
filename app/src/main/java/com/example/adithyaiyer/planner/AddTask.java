package com.example.adithyaiyer.planner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddTask extends AppCompatActivity {

    private EditText taskname;
    private EditText date;
    private EditText time;
    private EditText location;
    private EditText person;
    private int pkvalue;
    private joiningTask jk;
    private List<customer> customerListForAdddition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

    }


    public void addTaskNow(View view){

        taskname= (EditText)findViewById(R.id.addTaskName);
        date = (EditText)findViewById(R.id.addTaskDate);
        time= (EditText)findViewById(R.id.addTaskTime);
        location=(EditText)findViewById(R.id.addTaskLocation);
        person = (EditText)findViewById(R.id.addTaskNo);

        task t=new task(taskname.getText().toString(),date.getText().toString(),time.getText().toString(),location.getText().toString());
        addTaskToDatabase(t);


        Bundle data=getIntent().getExtras();
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
                jk.setDateOfJoining("2018-12-12");
                jk.setTaskDetails(k.getId());
                joiningTask jk2=new joiningTask(1,1,"2018-12-12");




                int idFromUsername =  getIdFromUsername(customerListForAdddition,person.getText().toString());
                if(idFromUsername==0){
                    idFromUsername++;
                    Toast.makeText(AddTask.this,
                            "username does not exist", Toast.LENGTH_SHORT).show();
                }
                jk2.setCustomer(idFromUsername);
                jk2.setTaskDetails(k.getId());
                jk2.setDateOfJoining("2018-12-12");

                addRelation(jk);
                addRelation(jk2);

            }

            @Override
            public void onFailure(Call<task> call, Throwable t) {
                Toast.makeText(AddTask.this,
                        "finding last task failed", Toast.LENGTH_SHORT).show();
            }
        });






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

            }

            @Override
            public void onFailure(Call<joiningTask> call, Throwable t) {

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
}
