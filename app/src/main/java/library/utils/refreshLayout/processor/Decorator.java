package library.utils.refreshLayout.processor;


import library.utils.refreshLayout.listener.XRefreshLayout;

/**
 * @author dongweizhou
 * @createTime 2019/4/8
 * @describe
 * @DWZ
 */
public abstract class Decorator implements IDecorator{
    protected IDecorator decorator;
    protected XRefreshLayout.CoContext cp;

    public Decorator(XRefreshLayout.CoContext processor, IDecorator decorator1) {
        cp = processor;
        decorator = decorator1;
    }
}
