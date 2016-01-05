package net.meluo.propertyplatform.app;

public class DDEvent {

	public static final int E_APP_HIDE = 0;
	public static final int E_APP_LOGOUT = 1;
	public static final int E_LOGOUT = 2;
	public static final int WX_LOADING = 3;


	private int mEvent;
	private Object mData;

	public DDEvent(int event) {
		// TODO Auto-generated constructor stub
		mEvent = event;
	}

	public DDEvent(int event, Object o) {
		// TODO Auto-generated constructor stub
		mEvent = event;
		mData = o;
	}

	public int getEvent() {
		return mEvent;
	}

	public Object getData() {
		return mData;
	}
}