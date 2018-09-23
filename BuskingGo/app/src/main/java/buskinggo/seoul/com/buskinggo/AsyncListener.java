package buskinggo.seoul.com.buskinggo;

import java.util.ArrayList;

public interface AsyncListener {
    void  taskComplete(BuskerDTO buskerDTO);

    void taskComplete(ArrayList<BuskingDTO> buskingDTOS);
}
