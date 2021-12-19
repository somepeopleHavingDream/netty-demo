package org.yangxin.netty.ch10;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * @author yangxin
 * 2021/11/5 下午9:50
 */
@SuppressWarnings({"AlibabaAvoidManuallyCreateThread", "FieldMayBeFinal", "CommentedOutCode"})
public class FastThreadLocalTest {

    private static FastThreadLocal<Object> threadLocal0 = new FastThreadLocal<Object>() {

        @Override
        protected Object initialValue() {
            return new Object();
        }

        @Override
        protected void onRemoval(Object value) {
            System.out.println("onRemoval");
        }
    };

    private static FastThreadLocal<Object> threadLocal1 = new FastThreadLocal<Object>() {

        @Override
        protected Object initialValue() {
            return new Object();
        }
    };

    public static void main(String[] args) {
        new Thread(() -> {
            Object object = threadLocal0.get();
            System.out.println(object);
//            threadLocal0.set(new Object());
        }).start();

        new Thread(() -> {
            Object object = threadLocal0.get();
            System.out.println(object);
//            while (true) {
//                System.out.println(threadLocal0.get() == object);
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }).start();
    }
}
