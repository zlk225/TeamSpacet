package cn.moling.spacet.utils;

import cn.moling.spacet.common.enums.ResultEnum;

import java.util.Date;

/**
 * @Auther: zhanglk
 * @Date: 2019/5/29 09:11
 * @Description:
 */
public class CommandResult<T> {
    /**
     * 成功
     */
    public static final int CODEOK = 200 ;
    /**
     * 失败
     */
    public static final int CODEEOR = 400 ;
    /**
     * 异常
     */
    public static final int EXCEPTIONERROR = 504 ;

    // 响应代码
    private final int _CODE ;
    // 响应消息
    private final String _MSG ;
    // 响应数据
    private final Object _DATA ;
    //时间
    private Date timestamp;



    public CommandResult( int code, String msg ){
        this._CODE = code ;
        this._MSG = msg ;
        this._DATA = null ;
        this.timestamp = new Date();
    }

    public CommandResult( int code, String msg, Object data ){
        this._CODE = code ;
        this._MSG = msg ;
        this._DATA = data ;
        this.timestamp = new Date();
    }

    public CommandResult(ResultEnum result) {
        this._CODE = result.getCode() ;
        this._MSG = result.getMessage() ;
        this._DATA = null ;
        this.timestamp = new Date();
    }

    public CommandResult(ResultEnum result,T t) {
        this._CODE = result.getCode() ;
        this._MSG = result.getMessage() ;
        this._DATA = t ;
        this.timestamp = new Date();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder() ;
        sb.append( "{\"code\":\"" ).append( this._CODE ).append( "\"" ) ;
        sb.append( ",\"msg\":\"" ).append( this._MSG ).append( "\"" ) ;
        sb.append( ",\"data\":\"" ).append( this._DATA ).append( "\"" ) ;
        sb.append( ",\"timestamp\":\"" ).append( new Date() ).append( "\"}" ) ;
        return sb.toString() ;
    }
    /**
     * 获取响应代码
     * @return
     */
    public int getCode(){
        return this._CODE ;
    }

    /**
     * 获取响应消息
     * @return
     */
    public String getMsg(){
        return this._MSG ;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    /**
     * 获取响应数据
     * @return
     */
    @SuppressWarnings( "unchecked" )
    public <T> T getData(){
        return (T)this._DATA ;
    }
}
