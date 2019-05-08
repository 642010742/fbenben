package library.common.eventbus.model;

import java.io.Serializable;

/**
 * eventBus 发送消息载体
 */
public class EventModel implements Serializable {


    private int what;
    private String msg;
    private Object data;

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
