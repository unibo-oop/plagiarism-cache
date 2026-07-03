package controller.charts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.chart.XYChart.Series;
import model.ModelImpl;
import model.interfaces.Model;

public abstract class GeneralChart implements Chart{

    protected final static int STARTING_YEAR = 2016;
    protected String title;
    protected final List<String> months = new ArrayList<>(Arrays.asList(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
            );
    protected Model model = new ModelImpl();

    @Override
    public String getTitle() {
            return this.title;
    }

    @Override
    public List<Integer> getAvailablePeriods(){

        List<Integer> l = new LinkedList<>();

        for (int i = LocalDate.now().getYear(); i >= STARTING_YEAR; i--){
            l.add(i);
        }

        return l;
    }

    @Override
    public abstract <X, Y> Series<X, Y> getSeries(int year);


}
