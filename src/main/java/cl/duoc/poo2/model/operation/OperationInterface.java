package cl.duoc.poo2.model.operation;

import cl.duoc.poo2.exception.ModelLayerException;

public interface OperationInterface<I, O> {

    public O execute(I input) throws ModelLayerException;

}
