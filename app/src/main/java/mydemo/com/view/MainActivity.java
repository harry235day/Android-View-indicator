package mydemo.com.view;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private ViewPager mvp;
    private List<String> imageList;
    private MyIndicator mIndicator;
    private List<View> imagViews;

    //        http://f.hiphotos.baidu.com/image/pic/item/3ac79f3df8dcd1008742b1cc788b4710b8122f04.jpg
//        http://img0.imgtn.bdimg.com/it/u=2302918630,1086443006&fm=27&gp=0.jpg
//        http://img5.imgtn.bdimg.com/it/u=1894464170,2955189889&fm=27&gp=0.jpg
//        http://img4.imgtn.bdimg.com/it/u=227953490,3054069314&fm=27&gp=0.jpg
//        http://img2.imgtn.bdimg.com/it/u=2548577336,1835070124&fm=27&gp=0.jpg
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIndicator = findViewById(R.id.mIndicator);
        mvp = findViewById(R.id.mvp);
        init();
    }

    private void init() {

        imageList = new ArrayList<>();
//        imageList.add("http://img2.imgtn.bdimg.com/it/u=2548577336,1835070124&fm=27&gp=0.jpg");
//        imageList.add("http://img4.imgtn.bdimg.com/it/u=227953490,3054069314&fm=27&gp=0.jpg");
        imageList.add("http://img5.imgtn.bdimg.com/it/u=1894464170,2955189889&fm=27&gp=0.jpg");
        imageList.add("http://img0.imgtn.bdimg.com/it/u=2302918630,1086443006&fm=27&gp=0.jpg");
        imageList.add("http://img5.imgtn.bdimg.com/it/u=1894464170,2955189889&fm=27&gp=0.jpg");
        imageList.add("http://img0.imgtn.bdimg.com/it/u=2302918630,1086443006&fm=27&gp=0.jpg");

        imagViews = new ArrayList<>();
        for (int i = 0; i < imageList.size(); i++) {
            ImageView iv  = new ImageView(MainActivity.this);
            imagViews.add(iv);
        }
        mvp.setAdapter(new myPagerAdapter());
        mvp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("zz",position%imageList.size()+"");
                mIndicator.setNumberAndOffSet(imageList.size(),position%=imageList.size(),positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }




    public  class myPagerAdapter extends PagerAdapter{



        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= imageList.size();

            RequestOptions options =  new RequestOptions();
            options.centerCrop().error(R.drawable.ic_launcher_background).placeholder(R.drawable.ic_launcher_background);
            ImageView iv = (ImageView) imagViews.get(position);
            ViewGroup parent = (ViewGroup) iv.getParent();
            Glide.with(MainActivity.this)
                    .load(imageList.get(position))
                    .apply(options)
                    .into(iv);
            if (parent != null) {
                parent.removeAllViews();
            }
            container.addView((ImageView) imagViews.get(position));
            return (ImageView) imagViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            position%=imageList.size();
//            container.removeView((ImageView) imagViews.get(position));
        }

        @Override
        public int getCount() {
            return 100000;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
}
