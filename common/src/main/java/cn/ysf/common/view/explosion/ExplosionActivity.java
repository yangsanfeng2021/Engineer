package cn.ysf.common.view.explosion;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import cn.ysf.common.R;
import cn.ysf.common.R2;
import cn.ysf.common.arouter.RoutePath;
import cn.ysf.common.base.BaseActivity;
import cn.ysf.common.view.explosion.factory.BooleanFactory;
import cn.ysf.common.view.explosion.factory.ExplodeParticleFactory;
import cn.ysf.common.view.explosion.factory.FallingParticleFactory;
import cn.ysf.common.view.explosion.factory.FlyawayFactory;
import cn.ysf.common.view.explosion.factory.InnerFallingParticleFactory;
import cn.ysf.common.view.explosion.factory.VerticalAscentFactory;

/**
 * 粒子爆炸界面
 */
@Route(path = RoutePath.COMMON_ACTIVITY_EXPLOSION)
public class ExplosionActivity extends BaseActivity {

    @BindView(R2.id.googleIv)
    ImageView googleIv;

    @BindView(R2.id.qqIv)
    ImageView qqIv;

    @BindView(R2.id.qqMusicIv)
    ImageView qqMusicIv;

    @BindView(R2.id.qzoneIv)
    ImageView qzoneIv;

    @BindView(R2.id.vxIv)
    ImageView vxIv;

    @BindView(R2.id.wbIv)
    ImageView wbIv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_explosion;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    private void initListener() {
        new ExplosionField(this, new BooleanFactory()).addListener(googleIv);
        new ExplosionField(this, new ExplodeParticleFactory()).addListener(qqIv);
        new ExplosionField(this, new FallingParticleFactory()).addListener(qqMusicIv);
        new ExplosionField(this, new FlyawayFactory()).addListener(qzoneIv);
        new ExplosionField(this, new InnerFallingParticleFactory()).addListener(vxIv);
        new ExplosionField(this, new VerticalAscentFactory()).addListener(wbIv);
    }
}
