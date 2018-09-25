package buskinggo.seoul.com.buskinggo;

import java.util.ArrayList;

import buskinggo.seoul.com.buskinggo.BuskerDTO;
import buskinggo.seoul.com.buskinggo.BuskingDTO;

public interface AsyncListener {
    void buskerComplete(BuskerDTO buskerDTO);

    void buskerComplete(ArrayList<BuskerDTO> buskerDTOS);

    void buskingComplete(ArrayList<BuskingDTO> buskingDTOS);

    void buskingComplete(BuskingDTO buskingDTOS);
}
