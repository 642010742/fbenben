package library.BaseAdapter.dadapter.helper;

/**
 * Created by Administrator on 2018/2/5.
 */

public  abstract class MultiItemView implements ItemViewDelegate {

    private int layoutId;
    private int dataBingId;
    public MultiItemView(int LayoutId,int DataBingId) {
        this.layoutId = LayoutId;
        this.dataBingId = DataBingId;
    }

    @Override
    public int getItemViewLayoutId() {
        return layoutId;
    }


    @Override
    public int getItemDataBingId() {
        return dataBingId;
    }


}
