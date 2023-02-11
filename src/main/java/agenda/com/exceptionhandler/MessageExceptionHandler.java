package agenda.com.exceptionhandler;

import java.util.Date;

public class MessageExceptionHandler {
    private Date timestemp;
    private Integer status;
    private String msg;

    public MessageExceptionHandler(Date timestemp,Integer status, String msg){
        this.timestemp = timestemp;
        this.status = status;
        this.msg = msg;
    }

    public Date getTimestemp() {
        return timestemp;
    }

    public void setTimestemp(Date timestemp) {
        this.timestemp = timestemp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
