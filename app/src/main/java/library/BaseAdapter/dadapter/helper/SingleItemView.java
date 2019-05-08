package library.BaseAdapter.dadapter.helper;


import library.commonModel.BaseModel;

/**
 * Created by Administrator on 2018/2/11.
 */

public class SingleItemView implements ItemViewDelegate {
    private int layoutId;
    private int dataBingId;
    public SingleItemView(int LayoutId, int DataBingId) {
        this.layoutId = LayoutId;
        this.dataBingId = DataBingId;
    }

    @Override
    public int getItemViewLayoutId() {
        return layoutId;
    }

    @Override
    public boolean isForViewType(BaseModel item, int position) {
        return true;
    }

    @Override
    public int getItemDataBingId() {
        return dataBingId;
    }

}