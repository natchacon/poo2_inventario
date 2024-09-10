package cl.duoc.poo2.controller;

public interface Controller<I, O> {

    ControllerDTO<O> execute(I viewObject);

}
