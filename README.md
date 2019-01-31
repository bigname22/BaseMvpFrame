# BaseMvpFrame
--

###update1: BaseMvpFrame  作为以后项目的框架（并会在实践中不断完善该框架，达到以后能复用进行快速开发）
csdn https://blog.csdn.net/bigname22/article/details/86606808
2019-1-18 15:00


###update2: ViewPager + Fragment + BottomNavigationView  确认App UI轮廓
2019-1-24 23:22


###update3： 引入网络框架，实现联动UI效果
2019-1-29 18:22

###update4： 自定义View，做一个炫酷的LoadingView（未完成封装优化）
2019-1-31 18:22

计划 update5： LoadingView封装优化，增加更多动画效果



--------------------------------------------------
mvp框架基础构建
这几天都在研究如何搭建一个实用稳固的MVP架构作为快速开发的基底。
也纠结了很久Presenter层该如何复用，在网上查阅了很多资料之后仍然没能找到一个适用的办法，有的写法单纯是为了presenter的复用而写，却给其他模块增负担；有的实现的手法过于僵硬，不符合写代码的原则。
在看完各种奇奇怪怪的实现思路之后，自己内心也有了一个实现presenter复用的一套方法，不过还不知道可不可行，到时撸完了可行再贴出来。



这篇文章先撸一遍MVP的基本框架搭建，看完这篇文章你能学会：

 - 一个还不错的Mvp框架结构是怎样的
 - Mvp框架如何避免内存泄漏
 - Presenter层如何复用？*这一个以后确定可行再撸*

顺着我的思路来一遍，先构造基类：
首相是对View层的基类下手，
**IBaseView**

```
package com.example.administrator.mvpframedemo.base;

public interface IBaseView {

}
```
实不相瞒，这个我一个方法都没定义。看到网上有很多人会把showToast()等这样的方法定义在这里喔我就不同意了，因为这些太重复固定的我觉得放在基类让每个子类去实现实在麻烦，所以我怎么做呢？我把showToast这样重复的实现放在BaseActivity；下面一起看一下
**BaseActivity**

```
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initBeforeCreate();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init(savedInstanceState);
        initData();
        initView();
        logic();
    }

    protected abstract void initBeforeCreate();

    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void logic();

    protected void showToast (String toastStr) {
        Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();
    };
}
```
这是在没啥好说，这里释放了更BaseActivity有关的，而不是跟Mvp有关的。至于你了解的BaseActivity中要处理presenter绑定，解绑这样的操作我会另外建一个BaseMvpActivity来做专门针对Mvp的处理的。保持这样结构的整洁还是感觉神清气爽的；当然BaseMvpActivity还是要继承BaseActivity的；万一哪天天收的说这个模块我要用MVC之类的时候也好处理一点。
接下来就是
**BasePresenter**

```
public class BasePresenter<V extends IBaseView>  {

    private WeakReference<V> mViewRef;
    public V mView;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
        mView = mViewRef.get();
    }

    public void detachView() {
        mViewRef.clear();
        mView = null;
    }
}
```
这里定义了attachView()以及detachView()两个接口；这是为啥？还有为什么要有mViewRef?
首先说为什么要attachView()？这个其实只要你敲过一些简单的mvp的代码就会知道，每一次都要写这样的代码：

```
MainActivity{
	// 这一句代码做了两个事情，①View层创建自己适合的Presenter，②然后把自己传给Presenter完成两者的绑定
	Presenter presenter = new Presenter(MainActivity.this);
}
```
那么attachView()就是完成②的事情，至于①是留给View层自己去实现的，后面会说到。
所以attachView()诞生的原因就了解了，那么detachView()设计的原因呢？
detachView()还有mViewRef的出现都是为了解决内存泄漏而存在的。那么是怎么解决内存泄漏的存在呢？
当一个Activity在显示的时候退出了，GC在感觉内存紧张的时候会想把这个Activity给回收掉，但是此时presenter对象是持有Activity对象的，所以GC就没办法回收了，这样就存在泄漏的隐患了。这种持有activity对象而引起内存泄漏是非常常见的原因。
所以我们使用deacttach()以及mViewRef(弱引用)来解除两者的绑定，让GC随心所欲。那什么时候解绑呢？在Activity的onDestroy()生命周期的时候合适。
那么接下来就是：
**BaseMvpActivity**

```
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements IBaseView {

    protected T mPresenter;

    @Override
    protected void init(Bundle savedInstanceState) {
        mPresenter = bindPresenter();
        mPresenter.attachView(this);
    }

    protected abstract T bindPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
```
这个BaseMvpActivity里面做了针对Mvp的事情，包括定义bindPresenter()创建自己合适的presenter，然后执行presenter.attachView(this),将两者进行绑定；最后在onDestroy()方法中接触两者的绑定。

--------------------------------------人工分割线----------------------------------------------------------------------
到这里，关于Mvp的基类设计好像就差不多了。
然后来模拟看看实际上要进行的业务：
**登录页面要请求登录**
第一步：在constract（合约层，维护P层与V层的关系）
建立一个LoginConstract

```
public interface LoginContract {
    abstract class LoginPresenter extends BasePresenter<LoginView>{
        public abstract void login(String name, String password);
    }
    interface LoginView extends IBaseView {
        void showTips(String str);
    }
}
```
在合约里面定义LoginPresenter以及LoginView的接口；实现交给其他地方。
在这里开发者就要明白登录的逻辑，例如：首先View利用presetner发起登录（login）请求，请求完成之后View要给用户显示结果（showTips）；所以在上面定义的接口也是这么来的。
实现：
**LoginPresenter：**

```
public  class LoginPresenter extends LoginContract.LoginPresenter {
    ILoginModel loginModel;
    @Override
    public void login(String name, String password) {
        loginModel = new LoginModel();
        loginModel.login(name, password, new LoginCallBack());
    }

    private class LoginCallBack implements ICallBack<LoginDomain, Exception> {

        @Override
        public void onSuccess(LoginDomain result) {
            mView.showTips("登录成功");
        }

        @Override
        public void onFail(Exception error) {
            mView.showTips("登录失败");
        }
    }
}
```
简简单单，login()方法需要用到model层去帮忙获取数据。所以实例化合适的model，然后调用它取数据的接口。
我一般会把model层分为interface以及impl两层，一个定义接口，一个实现。
不过关于Presenter与Model层的交互问题需要说明一下：因为Model层取数据大多未异步操作，所以通常使用接口回调的方式。所以在调用Model的login（）取数据的时候传递一个回调对象来实现异步。
**LoginModel：**

```
public class LoginModel implements ILoginModel {

    @Override
    public void login(String username, String password, ICallBack<LoginDomain, Exception> callBack) {
        // 模拟一部网络获取
        try {
            Thread.sleep(2000);
            callBack.onSuccess(new LoginDomain("周润发","123"));
        } catch (InterruptedException e) {
            e.printStackTrace();
            callBack.onFail(new Exception());
        }
    }
}
```
到了这里其实只有一些细节的问题了，关于presenter与model层的ICallBack回调如何统一规范等等都是小事。
最重要的是**presenter如何复用**？敬请期待我之后的博文。


最后是我的项目的目录结构：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190123111118538.png)
