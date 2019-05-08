package library.commonModel;

import java.util.List;

import library.utils.DialogModelFactory;


public class DialogModel extends BaseModel{

    // 适合用于 有title  有内容  取消  确定按钮

    private String dialongTitle;
    private String dialogContent;
    private String dialogSure;
    private String dialogCancel;
    private int[]  colors;




    public DialogModel() {
    }


    public DialogModel(List<String> list){

        if (list != null) {
            dialongTitle = list.get(0);
            dialogContent = list.get(1);
            dialogCancel = list.get(2);
            dialogSure = list.get(3);
        }
    }

    public String getDialongTitle() {
        return dialongTitle;
    }

    public void setDialongTitle(String dialongTitle) {
        this.dialongTitle = dialongTitle;
    }

    public String getDialogContent() {
        return dialogContent;
    }

    public void setDialogContent(String dialogContent) {
        this.dialogContent = dialogContent;
    }


    public String getDialogSure() {
        return dialogSure;
    }

    public void setDialogSure(String dialogSure) {
        this.dialogSure = dialogSure;
    }

    public String getDialogCancel() {
        return dialogCancel;
    }

    public void setDialogCancel(String dialogCancel) {
        this.dialogCancel = dialogCancel;
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }



}
