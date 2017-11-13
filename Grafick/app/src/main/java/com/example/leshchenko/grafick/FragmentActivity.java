package com.example.leshchenko.grafick;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;
import java.util.Date;
import java.util.Random;


public class FragmentActivity extends Fragment {
    private final Handler mHandler = new Handler();
    private Runnable mTimer;
    //Объкты линий
    private LineGraphSeries<DataPoint> mSeries1;
    private LineGraphSeries<DataPoint> mSeries2;
    // тект над графиком
    private TextView textPM10;
    private  TextView textPM2 ;

    private Date timeALineA = null;
    private Date timeBLineA = null;
    private Date timeALineB = null;
    private Date timeBLineB = null;
    private boolean flagTimeLineA;
    private boolean flagTimeLineB;
    private String pm10;
    private String pm2;


    private OnFragmentInteractionListener mListener;


    public FragmentActivity() {

    }


    public static FragmentActivity newInstance() {
        FragmentActivity fragment = new FragmentActivity();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flagTimeLineA = true;
        flagTimeLineB = true;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment, container, false);
        GraphView graph = (GraphView) rootView.findViewById(R.id.graph);
        //Текст куда записываю рандомные числа
         textPM10 = (TextView) rootView.findViewById(R.id.text_view_pm10);
         textPM2 = (TextView) rootView.findViewById(R.id.text_view_pm2);

        //Создаю первую линию
        mSeries1 = new LineGraphSeries<>(generateData1());
        //Делаю этой лини настройки, цвет,включение точек, радиус точки,вывод на график последние показание точки, плюс тост при нажатие на точку.
        mSeries1.setColor(Color.CYAN);
        mSeries1.setDrawDataPoints(true);
        mSeries1.setDataPointsRadius(20);
        mSeries1.setThickness(7);
        mSeries1.setTitle("PM2 = " + pm2);
        mSeries1.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getActivity(), "PM5: "+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
        //Создаю вторую линию и настройки к ней
       mSeries2 = new LineGraphSeries<>(generateData2());

        mSeries2.setColor(Color.GREEN);
        mSeries2.setDrawDataPoints(true);
        mSeries2.setDataPointsRadius(20);
        mSeries2.setThickness(7);
        mSeries2.setTitle("PM10 = " + pm10);

        // Значение по вертикали и горизонтали, подписи.
        graph.getLegendRenderer().setVisible(true);
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter()
        {
            @Override
            public String formatLabel(double value,boolean isValueX){
                if(isValueX){
                    return "Min " + super.formatLabel(value,isValueX);
                }else {
                    return "Pm " + super.formatLabel(value, isValueX);
                }
            }
        });
        // Добавляю в график линию 1 и 2, а также настройки графика, сролл, мин и мак значения по х и y
        graph.addSeries(mSeries1);
        graph.addSeries(mSeries2);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);
        graph.setBackgroundColor(Color.GRAY);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().isXAxisBoundsManual();
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(3);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);
        return rootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // тут происходит магия постоянной работы
        mTimer = new Runnable() {
            @Override
            public void run() {
                mSeries1.resetData(generateData1());
                mSeries2.resetData(generateData2());
                mHandler.postDelayed(this, 15000);
            }
        };
        mHandler.postDelayed(mTimer, 30000);
    }

    @Override
    public void onPause() {
        mHandler.removeCallbacks(mTimer);
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
        // В этом методе создаю массив точек
    private DataPoint[] generateData1(){
        int count = 50;
        DataPoint[] values = new DataPoint[count];
        for (int i = 0; i < count; i++) {
            double x = i;

//            if(flagTimeLineA){
//                timeALineA = new Date();
//            }
//            if(!flagTimeLineA){
//                timeBLineA = new Date();
//                flagTimeLineB = true;
//            }
//            if(flagTimeLineA && timeALineA != null){
//                flagTimeLineA = false;
//            }
//            if(timeALineA != null && timeBLineA != null){
//                x =  getTime(timeALineA,timeBLineA);
//            }else{
//                x = i;
//            }

            double f = mRand.nextDouble()*45+0.3;
            double y = Math.sin(i*f+2) + mRand.nextDouble()*45;
            DataPoint v = new DataPoint(x, y);
            textPM2.setText("PM2  " + x + " == " + y);
            pm2 = String.valueOf(y);
            values[i] = v;
        }
        return values;
    }

    private DataPoint[] generateData2(){
        int count = 30;
        DataPoint[] values = new DataPoint[count];
        for (int i = 0; i < count; i++) {
            double x = i;
//
//            if(flagTimeLineB && timeALineB == null){
//                timeALineB = new Date();
//            }
//            if(!flagTimeLineB && timeBLineB == null){
//                timeBLineB = new Date();
//                flagTimeLineB = true;
//            }
//
//            if(flagTimeLineB && timeBLineB == null){
//                flagTimeLineB = false;
//            }
//
//            if(timeALineB != null && timeBLineB != null){
//                x =  getTime(timeALineB,timeBLineB);
//            }else{
//                x = i;
//            }

            double f = mRand.nextDouble()*20+0.3;
            double y = Math.sin(i*f+2) + mRand.nextDouble()*20;
            DataPoint v = new DataPoint(x, y);
            textPM10.setText("PM10  " + x + " ==  " + y);
            values[i] = v;
            pm10 = String.valueOf(y);

        }
        return values;
    }



    private double getTime(Date dateA,Date dateB){
       double res = (double) (dateB.getTime() - dateA.getTime()) / 1000;
        if(timeALineA != null && timeBLineA != null){
            timeALineA = null;
            timeBLineA = null;
        }
        if(timeALineB != null && timeBLineB != null){
            timeALineB = null;
            timeBLineB = null;
        }
        return res;
    }
    Random mRand = new Random();
}
