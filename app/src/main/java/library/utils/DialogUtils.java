package library.utils;


public class DialogUtils {

//    /**
//     *  通用弹框
//     * @param context
//     */
//    public static void showCommonDialog(final Activity context, DialogModel shareModel, final CommonDialogListener commonDialogListener) {
//        final Dialog dialog = new Dialog(context, R.style.MyDialog);
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.dialog_item,null);
//        DialogItemBinding dialogShareChooseBinding = DataBindingUtil.bind(view);
//        dialogShareChooseBinding.setModel(shareModel);
//        dialog.setContentView(dialogShareChooseBinding.getRoot());
//        Window dialogWindow = dialog.getWindow();
//        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
//        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//        p.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.65
//        p.height = p.WRAP_CONTENT;
//        dialogWindow.setGravity(Gravity.CENTER);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.setCancelable(true);
//        PopWindowHelper.backgroundAlpha(context,0.6f);
//        dialogShareChooseBinding.txCancelChangeName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                PopWindowHelper.backgroundAlpha(context,1f);
//                commonDialogListener.cancel("");
//            }
//        });
//
//        dialogShareChooseBinding.txSureChangeName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                PopWindowHelper.backgroundAlpha(context,1f);
//                commonDialogListener.sure("");
//            }
//        });
//        dialog.show();
//        dialogWindow.setAttributes(p);
//    }
//
//
//    /**
//     * 查看图片的
//     * @param context
//     * @param urls  图片集合
//     * @param position  当前点击的图片
//     */
//
//    public static void showImage(Context context, final List<ImageModel> urls,int position){
//        final Dialog dialog = new Dialog(context, R.style.MyDialog);
//        dialog.setContentView(R.layout.dialog_image_viewpager);
//        //获取当前Activity所在的窗体
//        Window dialogWindow = dialog.getWindow();
//        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        //设置宽
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        //设置高
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        dialogWindow.setAttributes(lp);
//
//        final TextView tvSize = dialog.findViewById(R.id.tvImageSzie);
//        LinearLayout linearLayout = dialog.findViewById(R.id.llDialog);
//        ViewPager viewPager = dialog.findViewById(R.id.dialogViewPager);
//        tvSize.setText((position+1)+"/"+urls.size());
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                tvSize.setText((position+1)+"/"+urls.size());
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        DialogImagePageAdapter dialogImagePageAdapter = new DialogImagePageAdapter(context,R.layout.dialog_only_image,urls);
//        viewPager.setAdapter(dialogImagePageAdapter);
//        dialogImagePageAdapter.setBtnDoneListener(new BtnDoneListener() {
//            @Override
//            public void done(String content) {
//                dialog.dismiss();
//            }
//        });
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.show();
//    }
//
//
//    /**
//     * 可设置最大,最小时间
//     *
//     * @param activity
//     * @param maxYear
//     * @param maxMonth
//     * @param maxDay
//     * @param minYear
//     * @param minMonth
//     * @param minDay
//     * @param onClickSureChooseListener
//     */
//
//    public static void ChooseData(final Activity activity, int maxYear, int maxMonth, int maxDay, int minYear, int minMonth, int minDay, final OnClickSureChooseListener onClickSureChooseListener) {
////        List<Integer> date = DateUtil.getDateForString("1990-01-01");
//        List<Integer> date = DateUtil.getDateForString(DateUtil.formatDate(System.currentTimeMillis(), DateUtil.ymd));
//        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(activity);
//        builder.setOnDateSelectedListener(new library.utils.timepicker.DatePickerDialog.OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(int[] dates) {
//                int date1 = dates[0];
//                String s = String.valueOf(date1);
//                //String substring = s.substring(2, s.length());
//                //textView.setText( substring+ "/" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])));
////                textView.setText( substring+ "/" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "/"
////                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));
//                String s1 = s + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
//                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2]));
//                onClickSureChooseListener.SureChoose(s1);
//            }
//
//            @Override
//            public void onCancel() {
//                onClickSureChooseListener.Cancel();
//            }
//        }).setSelectYear(date.get(0) - 1)
//                .setSelectMonth(date.get(1) - 1)
//                .setSelectDay(date.get(2) - 1);
//
////        builder.setMaxYear(DateUtil.getYear());
////        builder.setMaxMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
////        builder.setMaxDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
//
//        if (maxYear != -1) {
//            builder.setMaxYear(maxYear);
//            builder.setMaxMonth(maxMonth);
//            builder.setMaxDay(maxDay);
//        }
//
//        if (minYear != -1) {
//            builder.setMinYear(minYear);
//            builder.setMinMonth(minMonth);
//            builder.setMinDay(minDay);
//        }
//        Dialog dateDialog = builder.create();
//        PopWindowHelper.backgroundAlpha(activity, 0.6f);
//        dateDialog.show();
//        dateDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialogInterface) {
//                PopWindowHelper.backgroundAlpha(activity, 1.0f);
//            }
//        });
//    }
//
//    private static ImageView getView(List<ImageModel> urls,Context context) {
//
//        ImageView imgView = new ImageView(context);
//        imgView.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
//        GlideUtils.LoadImage(context,urls.get(0).getUrl(),imgView);
//        imgView.setId(0);
//        return imgView;
//    }



}
