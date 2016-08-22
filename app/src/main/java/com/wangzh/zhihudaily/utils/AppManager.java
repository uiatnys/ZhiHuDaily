package com.wangzh.zhihudaily.utils;

import java.util.Iterator;
import java.util.Stack;

import android.app.Activity;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class AppManager {
	
	private static Stack<Activity> activityStack;
	private static AppManager instance;
	
	private AppManager(){}
	/**
	 * 单一实例
	 */
	public static AppManager getAppManager(){
		if(instance==null){
			instance=new AppManager();
		}
		return instance;
	}
	/**
	 * 根据activity的class 从堆栈中 获取 对应的 对象
	 * @param clz
	 * @return
	 */
	public static Activity getActivityFromStack(Class clz){
		if(null == activityStack){
			return null;
		}
		for(Activity activity : activityStack){
			if(activity.getClass().equals(clz)){
				return activity;
			}
		}
		return null;
	}
	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity){
		if(activityStack==null){
			activityStack=new Stack<Activity>();
		}
		activityStack.add(activity);
	}
	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 */
	public Activity currentActivity(){
		if(null == activityStack || activityStack.size() == 0)
			return null;
		Activity activity=activityStack.lastElement();
		return activity;
	}
	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity(){
		Activity activity=activityStack.lastElement();
		finishActivity(activity);
	}
	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity){
		if(activity!=null){
			activityStack.remove(activity);
			activity.finish();
			activity=null;
		}
	}
	/**
	 * 结束指定类名的Activity
	 */
	public void finishActivity(Class<?> cls){
		Iterator<Activity> iterator = activityStack.iterator();
		while (iterator.hasNext()) {
			Activity activity = (Activity) iterator.next();
			if(activity!=null && activity.getClass().equals(cls)){
				iterator.remove();
				activity.finish();
				activity=null;
			}
		}
	}
	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity(){
		for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i)){
            	activityStack.get(i).finish();
            }
	    }
		activityStack.clear();
	}
	/**
	 * 退出应用程序
	 */
	public void AppExit() {
		try {
			finishAllActivity();
			System.exit(0);
		} catch (Exception e) {	}
	}
}