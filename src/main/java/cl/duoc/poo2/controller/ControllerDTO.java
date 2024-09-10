package cl.duoc.poo2.controller;

import cl.duoc.poo2.exception.ControllerException;

public class ControllerDTO<O> {

    private O object;

    private Boolean successfully;

    private ControllerException e;

    public ControllerDTO(O object) {
        this.successfully = Boolean.TRUE;
        this.object = object;
    }

    public ControllerDTO(ControllerException exception) {
        this.successfully = Boolean.FALSE;
        this.e = exception;
    }

    public O getObject() {
        return object;
    }

    public void setObject(O object) {
        this.object = object;
    }

    public Boolean getSuccessfully() {
        return successfully;
    }

    public void setSuccessfully(Boolean successfully) {
        this.successfully = successfully;
    }

    public ControllerException getE() {
        return e;
    }

    public void setE(ControllerException e) {
        this.e = e;
    }
}
