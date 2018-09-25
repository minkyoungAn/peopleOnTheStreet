package buskinggo.seoul.com.buskinggo;

import java.util.ArrayList;

import buskinggo.seoul.com.buskinggo.BuskerDTO;
import buskinggo.seoul.com.buskinggo.BuskingDTO;

public interface AsyncListener {
    void  taskComplete(BuskerDTO buskerDTO);

    void taskComplete(ArrayList<BuskingDTO> buskingDTOS);
}
