package com.example.androidlpreviewtest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;

public class MainActivity extends Activity implements OnClickListener {

	View btn1;
	View btn2;
	View btn3;
	//	Button btn4;
	//	Button btn5;

	View view1;
	//	View view2;
	//	View view3;
	//	View view4;
	//	View view5;

	boolean isShownView1;
	//	boolean isShownView4;
	//	boolean isShownView5;

	boolean animating;

	private static final String KEY_ID = "ViewTransitionValues:id";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn1 = findViewById(R.id.btn1);
		btn2 = findViewById(R.id.btn2);
		btn3 = findViewById(R.id.btn3);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		//		findViewById(R.id.btn4).setOnClickListener(this);
		//		findViewById(R.id.btn5).setOnClickListener(this);

		view1 = findViewById(R.id.view1);
		//		view2 = findViewById(R.id.view2);
		//		view3 = findViewById(R.id.view3);
		//		view4 = findViewById(R.id.view4);
		//		view5 = findViewById(R.id.view5);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
			case R.id.btn1:
				onClick1();
				break;
			case R.id.btn2:
				onClick2();
				break;
			case R.id.btn3:
				onClick3();
				break;
		//			case R.id.btn4:
		//				onClick4();
		//				break;
		//			case R.id.btn5:
		//				onClick5();
		//				break;
		}
	}

	void onClick1() {
		if (animating) {
			return;
		}

		animating = true;

		isShownView1 = !isShownView1;
		if (isShownView1) {
			view1.setVisibility(View.VISIBLE);

			// get the center for the clipping circle
			int centerX = view1.getWidth() / 2;
			int centerY = view1.getHeight() / 2;

			// get the final radius for the clipping circle			
			int finalRadius = (int)Math.sqrt(Math.pow(centerX, 2) + Math.pow(centerY, 2));

			// create and start the animator for this view
			// (the start radius is zero)
			ValueAnimator anim = ViewAnimationUtils.createCircularReveal(view1, centerX, centerY, 0, finalRadius);

			anim.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					super.onAnimationEnd(animation);
					animating = false;
				}
			});

			anim.setDuration(300);
			anim.start();
		} else {
			// get the center for the clipping circle
			int centerX = view1.getWidth() / 2;//(view1.getLeft() + view1.getRight()) / 2;
			int centerY = view1.getHeight() / 2;//(view1.getTop() + view1.getBottom()) / 2;

			// get the initial radius for the clipping circle
			int initialRadius = (int)Math.sqrt(Math.pow(centerX, 2) + Math.pow(centerY, 2));

			// create the animation (the final radius is zero)
			ValueAnimator anim = ViewAnimationUtils.createCircularReveal(view1, centerX, centerY, initialRadius, 0);

			// make the view invisible when the animation is done
			anim.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					super.onAnimationEnd(animation);
					view1.setVisibility(View.INVISIBLE);
					animating = false;
				}
			});

			// start the animation
			anim.setDuration(300);
			anim.start();
		}
	}

	void onClick2() {

	}

	void onClick3() {
		Intent intent = new Intent(this, Test1Activity.class);

		//		((ViewGroup)btn3.getParent()).setTransitionGroup(false);

		// create the transition animation - the images in the layouts
		// of both activities are defined with android:viewName="test"
		ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, btn3, "test");
		// start the new activity
		startActivity(intent, options.toBundle());
	}
	//
	//	void onClick4() {
	//		isShownView4 = !isShownView4;
	//		if (isShownView4) {
	//
	//		} else {
	//
	//		}
	//	}
	//
	//	void onClick5() {
	//		isShownView5 = !isShownView5;
	//		if (isShownView5) {
	//
	//		} else {
	//
	//		}
	//	}
}
