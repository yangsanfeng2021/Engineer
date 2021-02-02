package cn.ysf.ratingbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;

import cn.ysf.ratingbar.drawable.StarDrawable;

public class AndRatingBar extends androidx.appcompat.widget.AppCompatRatingBar {

    private ColorStateList mStarColor;
    private ColorStateList mSubStarColor;
    private ColorStateList mBgColor;
    private int mStarDrawable;
    private int mBgDrawable;
    private boolean mKeepOriginColor;
    private float scaleFactor;
    private float starSpacing;
    private boolean right2Left;
    private StarDrawable mDrawable;

    private OnRatingChangeListener mOnRatingChangeListener;

    private float mTempRating;

    public AndRatingBar(Context context) {
        this(context, null);
    }

    public AndRatingBar(Context context, AttributeSet attrs) {
        // notice:can't use this(context, attrs, 0); because ratingbar has it's own defStyleAttr
        super(context, attrs);
        init(context, attrs, 0);
    }

    public AndRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AndRatingBar, defStyleAttr, 0);
        right2Left = typedArray.getBoolean(R.styleable.AndRatingBar_right2Left, false);

        if (typedArray.hasValue(R.styleable.AndRatingBar_starColor)) {
            if (right2Left) {
                mBgColor = typedArray.getColorStateList(
                        R.styleable.AndRatingBar_starColor);
            } else {
                mStarColor = typedArray.getColorStateList(
                        R.styleable.AndRatingBar_starColor);
            }
        }

        if (typedArray.hasValue(R.styleable.AndRatingBar_subStarColor)) {
            if (!right2Left) {
                mSubStarColor = typedArray.getColorStateList(
                        R.styleable.AndRatingBar_subStarColor);
            }
        }

        if (typedArray.hasValue(R.styleable.AndRatingBar_bgColor)) {
            if (right2Left) {
                mStarColor = typedArray.getColorStateList(
                        R.styleable.AndRatingBar_bgColor);
            } else {
                mBgColor = typedArray.getColorStateList(
                        R.styleable.AndRatingBar_bgColor);
            }
        }

        mKeepOriginColor = typedArray.getBoolean(R.styleable.AndRatingBar_keepOriginColor, false);
        scaleFactor = typedArray.getFloat(R.styleable.AndRatingBar_scaleFactor, 1);
        starSpacing = typedArray.getDimension(R.styleable.AndRatingBar_starSpacing, 0);

        // get customize drawable
        mStarDrawable = typedArray.getResourceId(R.styleable.AndRatingBar_starDrawable, R.drawable.ic_rating_star_solid);
        if (typedArray.hasValue(R.styleable.AndRatingBar_bgDrawable)) {
            mBgDrawable = typedArray.getResourceId(R.styleable.AndRatingBar_bgDrawable, R.drawable.ic_rating_star_solid);
        } else {
            mBgDrawable = mStarDrawable;
        }

        typedArray.recycle();

        mDrawable = new StarDrawable(context, mStarDrawable, mBgDrawable, mKeepOriginColor);
        mDrawable.setStarCount(getNumStars());
        setProgressDrawable(mDrawable);

        if (right2Left) {
            setRating(getNumStars() - getRating());
        }
    }

    @Override
    public void setNumStars(int numStars) {
        super.setNumStars(numStars);
        if (mDrawable != null) {
            mDrawable.setStarCount(numStars);
        }
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = getMeasuredHeight();
        int width = Math.round(height * mDrawable.getTileRatio() * getNumStars() * scaleFactor) + (int) ((getNumStars() - 1) * starSpacing);
        setMeasuredDimension(View.resolveSizeAndState(width, widthMeasureSpec, 0), height);
    }

    @Override
    public void setProgressDrawable(Drawable d) {
        super.setProgressDrawable(d);
        applyProgressTints();
    }

    private void applyProgressTints() {
        if (getProgressDrawable() == null) {
            return;
        }
        applyPrimaryProgressTint();
        applyProgressBackgroundTint();
        applySecondaryProgressTint();
    }

    private void applyPrimaryProgressTint() {
        if (mStarColor != null) {
            Drawable target = getTintTargetFromProgressDrawable(android.R.id.progress, true);
            if (target != null) {
                applyTintForDrawable(target, mStarColor);
            }
        }
    }

    private void applySecondaryProgressTint() {
        if (mSubStarColor != null) {
            Drawable target = getTintTargetFromProgressDrawable(android.R.id.secondaryProgress,
                    false);
            if (target != null) {
                applyTintForDrawable(target, mSubStarColor);
            }
        }
    }

    private void applyProgressBackgroundTint() {
        if (mBgColor != null) {
            Drawable target = getTintTargetFromProgressDrawable(android.R.id.background, false);
            if (target != null) {
                applyTintForDrawable(target, mBgColor);
            }
        }
    }

    private Drawable getTintTargetFromProgressDrawable(int layerId, boolean shouldFallback) {
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable == null) {
            return null;
        }
        progressDrawable.mutate();
        Drawable layerDrawable = null;
        if (progressDrawable instanceof LayerDrawable) {
            layerDrawable = ((LayerDrawable) progressDrawable).findDrawableByLayerId(layerId);
        }
        if (layerDrawable == null && shouldFallback) {
            layerDrawable = progressDrawable;
        }
        return layerDrawable;
    }

    private void applyTintForDrawable(Drawable drawable, ColorStateList tintList) {
        if (tintList != null) {
            drawable.setTintList(tintList);
            if (drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
        }
    }

    public OnRatingChangeListener getOnRatingChangeListener() {
        return mOnRatingChangeListener;
    }

    public void setOnRatingChangeListener(OnRatingChangeListener listener) {
        mOnRatingChangeListener = listener;
        if (right2Left) {
            mOnRatingChangeListener.onRatingChanged(this, getNumStars() - getRating());
        } else {
            mOnRatingChangeListener.onRatingChanged(this, getRating());
        }
    }

    @Override
    public synchronized void setSecondaryProgress(int secondaryProgress) {
        super.setSecondaryProgress(secondaryProgress);

        float rating = getRating();
        if (mOnRatingChangeListener != null && rating != mTempRating) {
            if (right2Left) {
                mOnRatingChangeListener.onRatingChanged(this, getNumStars() - rating);
            } else {
                mOnRatingChangeListener.onRatingChanged(this, rating);
            }
        }
        mTempRating = rating;
    }

    public interface OnRatingChangeListener {
        void onRatingChanged(AndRatingBar ratingBar, float rating);
    }

    public void setScaleFactor(float scaleFactor) {
        this.scaleFactor = scaleFactor;
        requestLayout();
    }

    public void setStarSpacing(float starSpacing) {
        this.starSpacing = starSpacing;
        requestLayout();
    }
}
