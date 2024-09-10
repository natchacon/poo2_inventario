package cl.duoc.poo2.controller;

import cl.duoc.poo2.exception.ControllerException;
import cl.duoc.poo2.exception.ModelLayerException;
import cl.duoc.poo2.model.operation.OperationEnum;
import cl.duoc.poo2.model.operation.OperationFactory;
import cl.duoc.poo2.model.operation.OperationInterface;

public abstract class ControllerBase<I, O> implements Controller<I, O> {

    private OperationInterface modelService;

    public ControllerBase(OperationEnum modelServiceEnum) {
        modelService = OperationFactory.getInstance().getModelService(modelServiceEnum);
    }

    @Override
    public ControllerDTO<O> execute(I viewObject) {
        try {
            Object response = modelService.execute(viewObject);
            return new ControllerDTO(response);
        } catch (ModelLayerException e) {
            return new ControllerDTO(new ControllerException(e));
        }
    }
}
