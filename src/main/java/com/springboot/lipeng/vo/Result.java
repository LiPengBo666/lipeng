package com.springboot.lipeng.vo;

import java.io.Serializable;

/**
* @Description:    http请求统一返回的对象
* @Author:         LiPeng
* @CreateDate:     2019/5/23 15:11
* @UpdateUser:     LiPeng
* @UpdateDate:     2019/5/23 15:11
* @UpdateRemark:   暂无
* @Version:        1.0
*/
public class Result<T> implements Serializable {

	private String status;// 响应状态编码
	private String message;// 响应信息
	private T data;// 返回成功信息

	private static final long serialVersionUID = 1L;

	private Result() {
		// 单例
	}
	private static volatile  Result result;
	public static synchronized  Result getInstance() {
		return new Result();
	}
	/**
	 * 响应status和message
	 * @param status
	 * @param message
	 */
	public Result(String status, String message) {
		this.status = status;
		this.message = message;
	}
	/**
	 * 响应status、message和result
	 * @param status
	 * @param message
	 * @param data
	 */
	public Result(String status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public synchronized void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public synchronized void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public synchronized void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [status=" + status + ", message=" + message + ", data="
				+ data + "]";
	}

}