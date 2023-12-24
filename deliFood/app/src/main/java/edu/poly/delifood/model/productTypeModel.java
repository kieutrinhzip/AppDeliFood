package edu.poly.delifood.model;

import java.util.List;

public class productTypeModel {
    boolean success;
    String message;
    List<productType> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<productType> getResult() {
        return result;
    }

    public void setResult(List<productType> result) {
        this.result = result;
    }
}
