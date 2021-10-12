package com.example.a42;

public class DepNote {

    private String address;
    private Integer status;
    private String WorkStart;
    private  String WorkClose;

    public DepNote(String address, Integer status, String workStart, String workClose) {
        this.address = address;
        this.status = status;
        WorkStart = workStart;
        WorkClose = workClose;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setWorkStart(String workStart) {
        WorkStart = workStart;
    }

    public String getWorkStart() {
        return WorkStart;
    }

    public void setWorkClose(String workClose) {
        WorkClose = workClose;
    }

    public String getWorkClose() {
        return WorkClose;
    }
}
