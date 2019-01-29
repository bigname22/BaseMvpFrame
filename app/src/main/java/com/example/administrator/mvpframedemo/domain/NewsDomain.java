package com.example.administrator.mvpframedemo.domain;

import com.example.administrator.mvpframedemo.base.BaseDomain;

public class NewsDomain extends BaseDomain {

    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public NewsDomain() {

    }

    @Override
    public String toString() {
        return "NewsDomain{" +
                "reason='" + reason + '\'' +
                '}';
    }

    public NewsDomain(String reason) {

        this.reason = reason;
    }
}
