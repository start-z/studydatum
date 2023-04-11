package com.zhou.supermapforjava.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("统一返回结果")
public class R<T> implements Serializable {
    private static final long serialVersionUID = 5990701580664511688L;
    @ApiModelProperty("是否成果")
    private boolean success = true;
    @ApiModelProperty("结果状态码")
    private Integer ackCode = 200;
    @ApiModelProperty("错误提示")
    private String message;
    @ApiModelProperty("扩展对象")
    private Object extension;
    @JsonInclude(Include.NON_NULL)
    @ApiModelProperty("返回对象")
    private T data;

    private R() {
        this.success = true;
    }

    private R(T data) {
        this.success = true;
        this.data = data;
    }

    public static<T> R<T> data(T data) {
        R<T> result = new R();
        result.setData(data);
        return result;
    }

    public static <T> R<String> success() {
        R<String> result = new R();
        result.setSuccess(true);
        return result;
    }

    public static <T> R<T> success(T data) {
        R<T> result = new R();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> R<T> success(T data, Object extension) {
        R<T> result = new R();
        result.setSuccess(true);
        result.setData(data);
        result.setExtension(extension);
        return result;
    }

    public static <T> R<T> fail(String msg) {
        R<T> result = new R();
        result.setSuccess(false);
        result.setMessage(msg);
        return result;
    }

    public static <T> R<T> fail(Integer errCode) {
        R<T> result = new R();
        result.setSuccess(false);
        result.setAckCode(errCode);
        return result;
    }

    public static <T> R<T> fail(Integer errCode, String msg) {
        R<T> result = new R();
        result.setSuccess(false);
        result.setAckCode(errCode);
        result.setMessage(msg);
        return result;
    }

    public static <T> R<T> error(Integer errCode, String errMsg) {
        R<T> result = new R();
        result.setSuccess(false);
        result.setAckCode(errCode);
        result.setMessage(errMsg);
        return result;
    }

    public static <T> R<T> error(Integer errCode, String errMsg, T data) {
        R<T> result = new R();
        result.setSuccess(false);
        result.setAckCode(errCode);
        result.setMessage(errMsg);
        result.setData(data);
        return result;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Integer getAckCode() {
        return this.ackCode;
    }

    public String getMessage() {
        return this.message;
    }

    public Object getExtension() {
        return this.extension;
    }

    public T getData() {
        return this.data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setAckCode(Integer ackCode) {
        this.ackCode = ackCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setExtension(Object extension) {
        this.extension = extension;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof R)) {
            return false;
        } else {
            R<?> other = (R)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isSuccess() != other.isSuccess()) {
                return false;
            } else {
                label61: {
                    Object this$ackCode = this.getAckCode();
                    Object other$ackCode = other.getAckCode();
                    if (this$ackCode == null) {
                        if (other$ackCode == null) {
                            break label61;
                        }
                    } else if (this$ackCode.equals(other$ackCode)) {
                        break label61;
                    }

                    return false;
                }

                label54: {
                    Object this$message = this.getMessage();
                    Object other$message = other.getMessage();
                    if (this$message == null) {
                        if (other$message == null) {
                            break label54;
                        }
                    } else if (this$message.equals(other$message)) {
                        break label54;
                    }

                    return false;
                }

                Object this$extension = this.getExtension();
                Object other$extension = other.getExtension();
                if (this$extension == null) {
                    if (other$extension != null) {
                        return false;
                    }
                } else if (!this$extension.equals(other$extension)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof R;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + (this.isSuccess() ? 79 : 97);
        Object $ackCode = this.getAckCode();
        result = result * 59 + ($ackCode == null ? 43 : $ackCode.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $extension = this.getExtension();
        result = result * 59 + ($extension == null ? 43 : $extension.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    public String toString() {
        return "R(success=" + this.isSuccess() + ", ackCode=" + this.getAckCode() + ", message=" + this.getMessage() + ", extension=" + this.getExtension() + ", data=" + this.getData() + ")";
    }
}
