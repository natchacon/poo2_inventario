package cl.duoc.poo2.exception;

public class ModelLayerException extends Exception {
    private ModelExceptionEnum causeEnum;

    public ModelLayerException(ModelExceptionEnum causeEnum) {
        this.causeEnum = causeEnum;
    }

    public ModelExceptionEnum getCauseEnum() {
        return causeEnum;
    }
}
