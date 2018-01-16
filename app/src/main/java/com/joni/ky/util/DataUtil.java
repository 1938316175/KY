package com.joni.ky.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class DataUtil {

    private List<String> x_line = new ArrayList<>();
    private List<String> y_line = new ArrayList<>();
    private List<String> y_pillar = new ArrayList<>();

    public List<String> getX_line() {
        return x_line;
    }

    public void setX_line(List<String> x_line) {
        this.x_line = x_line;
    }

    public List<String> getY_line() {
        return y_line;
    }

    public void setY_line(List<String> y_line) {
        this.y_line = y_line;
    }

    public List<String> getY_pillar() {
        return y_pillar;
    }

    public void setY_pillar(List<String> y_pillar) {
        this.y_pillar = y_pillar;
    }

    public boolean saveY_line(){

        return false;
    }
}
