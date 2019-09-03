package ci.dcg.visionzero.support;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class AjaxResponseBody<T> implements Serializable {

    private String msg;
    private Double valeur;
    private T object;
    private List<T> result;

    //getters and setters

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
}
