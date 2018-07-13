package com.example.adithyaiyer.planner;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Main_task extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private List<task> tester;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private Context contextMain;
    private taskAdapter mAdapter;
    private TaskFragment taskFraggy;
    private ArrayList<task> listForToday;
    private ArrayList<task> listForTomorrow;
    private ArrayList<task> listForUpcoming;


    //   private CalendarView mCalendarView;
    private Calendar c;
    // This is the date picker used to select the date for our notification
  //  private DatePicker picker;
 //   private ScheduleClient scheduleClient;
    // This is the date picker used to select the date for our notification
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_task);
        this.contextMain=getApplicationContext();

        //Fetching the pk value from the signInActivity***********************

        Bundle data=getIntent().getExtras();
        int pkval=data.getInt("pkvalue");



        //***************************************************************************

        //making the layout for the main task and setting the viewpager **********************************

        taskFraggy=TaskFragment.newInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());






        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //**************************************************************************************************
/*


        });*/

        /////////////////////////////////////FETCHING CUSTOMER DATA STARTS HERE*****************************************************

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServiceCustomer.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        ApiServiceCustomer api= retrofit.create(ApiServiceCustomer.class);

        Call<List<task>> call=api.getCustomerData(pkval);
        call.enqueue(new Callback<List<task>>() {
            @Override
            public void onResponse(Call<List<task>> call, Response<List<task>> response) {
                tester= response.body();
                listForToday=giveListForToday(tester);
                listForTomorrow=giveListForTomorrow(tester);
                listForUpcoming=giveListForUpcoming(tester);
                recyclerView=(RecyclerView)findViewById(R.id.cycle);

                mAdapter = new taskAdapter(listForToday,Main_task.this);
                RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(eLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);

                recyclerView2=(RecyclerView)findViewById(R.id.tomCycle);
                mAdapter = new taskAdapter(listForTomorrow,Main_task.this);
                RecyclerView.LayoutManager eLayoutManager2 = new LinearLayoutManager(getApplicationContext());
                recyclerView2.setLayoutManager(eLayoutManager2);
                recyclerView2.setItemAnimator(new DefaultItemAnimator());
                recyclerView2.setAdapter(mAdapter);



                recyclerView3=(RecyclerView)findViewById(R.id.Upcycle);
                mAdapter = new taskAdapter(listForUpcoming,Main_task.this);
                RecyclerView.LayoutManager eLayoutManager3 = new LinearLayoutManager(getApplicationContext());
                recyclerView3.setLayoutManager(eLayoutManager3);
                recyclerView3.setItemAnimator(new DefaultItemAnimator());
                recyclerView3.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<List<task>> call, Throwable t) {
                Toast.makeText(Main_task.this,
                        "please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            refresh2();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position==0){
                return taskFraggy;
                }
            else{
                return CalendarJavaClass.newInstance();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }

    public void goToTask(View view){
        Intent go=new Intent(getApplicationContext(),AddTask.class);
        Bundle data=getIntent().getExtras();
        int pkval=data.getInt("pkvalue");
        go.putExtra("pkvals",pkval);
      //  go.putExtra("pkvalue",pkval);
        startActivity(go);
    }

    public void refresh(View view){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }


    public void refresh2(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public List<task> getListOfTasks(){


        Bundle data=getIntent().getExtras();
        int pkval=data.getInt("pkvalue");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServiceCustomer.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        ApiServiceCustomer api= retrofit.create(ApiServiceCustomer.class);

        Call<List<task>> call=api.getCustomerData(pkval);
        call.enqueue(new Callback<List<task>>() {
            @Override
            public void onResponse(Call<List<task>> call, Response<List<task>> response) {
                tester=response.body();
            }

            @Override
            public void onFailure(Call<List<task>> call, Throwable t) {

            }
        });

return tester;


    }
    public int gimmeCustomerId() {

        Bundle data = getIntent().getExtras();
        int pkvalue = data.getInt("pkvalue");
        return pkvalue;
    }

    public ArrayList<task> giveListForToday(List<task> listTask)  {
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

    //    calendar.add(Calendar.DAY_OF_YEAR, 1);
     //   Date tomorrow = calendar.getTime();
        ArrayList<task> listToday=new ArrayList<task>();
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(today);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for(int i=0;i<listTask.size();i++){
            Date tr=new Date();
            try {
                 tr=sdf.parse( listTask.get(i).getTaskDate().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(listTask.get(i).getTaskDate().equals(date) || tr.before(today)==true){
                listToday.add(listTask.get(i));
            }
        }
        return listToday;
    }


    public ArrayList<task> giveListForTomorrow(List<task> listTask){
        Calendar calendar = Calendar.getInstance();
      //  Date today = calendar.getTime();

            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date tomorrow = calendar.getTime();
        ArrayList<task> listTom=new ArrayList<task>();
        String date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(tomorrow);
        for(int i=0;i<listTask.size();i++){
            if(listTask.get(i).getTaskDate().equals(date1)){
                listTom.add(listTask.get(i));
            }
        }
        return listTom;
    }

    public ArrayList<task> giveListForUpcoming(List<task> listTask){
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        ArrayList<task> listUp=new ArrayList<task>();
        String date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(tomorrow);
        String date2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(today);

        for(int i=0;i<listTask.size();i++){
            if(listTask.get(i).getTaskDate().equals(date1)==false && listTask.get(i).getTaskDate().equals(date1)==false){
                listUp.add(listTask.get(i));}

        }
        return listUp;
    }
public Context giveContext(){
        return this.contextMain;
}



}
