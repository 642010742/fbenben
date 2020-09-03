package library.widget.banner;

import androidx.viewpager.widget.ViewPager.PageTransformer;

import library.widget.banner.transformer.AccordionTransformer;
import library.widget.banner.transformer.BackgroundToForegroundTransformer;
import library.widget.banner.transformer.CubeInTransformer;
import library.widget.banner.transformer.CubeOutTransformer;
import library.widget.banner.transformer.DefaultTransformer;
import library.widget.banner.transformer.DepthPageTransformer;
import library.widget.banner.transformer.FlipHorizontalTransformer;
import library.widget.banner.transformer.FlipVerticalTransformer;
import library.widget.banner.transformer.ForegroundToBackgroundTransformer;
import library.widget.banner.transformer.RotateDownTransformer;
import library.widget.banner.transformer.RotateUpTransformer;
import library.widget.banner.transformer.ScaleInOutTransformer;
import library.widget.banner.transformer.StackTransformer;
import library.widget.banner.transformer.TabletTransformer;
import library.widget.banner.transformer.ZoomInTransformer;
import library.widget.banner.transformer.ZoomOutSlideTransformer;
import library.widget.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
