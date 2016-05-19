package bj.codesolution.rxbeauty.model;

import java.io.Serializable;

/**
 * Created by Rylynn on 2016/5/18 0018.
 */
public class BaseData implements Serializable {
    public int code;
    public String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
