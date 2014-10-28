package org.vkedco.android.multitouchdemo;

/*
 * capture motion events and logcat the details of each
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;

public class MotionEventSequenceCaptureAct extends Activity
	implements OnTouchListener
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        RelativeLayout layout =
        	(RelativeLayout) findViewById(R.id.my_rel_layout);
        layout.setOnTouchListener(this);
    }

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent ev) {
		String myTag = v.getTag().toString();
		Log.v(myTag, "----------------------");
		Log.v(myTag, "Current view in onTouch() is " + myTag);
		Log.v(myTag, describeEvent(ev));
		return true;
	}
	
	protected static String describeEvent(MotionEvent mot_ev) {
		StringBuilder result = new StringBuilder(500);
		int action_type = mot_ev.getAction();
		String action_name = null;
		switch ( action_type ) {
		case MotionEvent.ACTION_DOWN: action_name = "ACTION_DOWN"; break;
		case MotionEvent.ACTION_UP: action_name = "ACTION_UP"; break;
		case MotionEvent.ACTION_MOVE: action_name = "ACTION_MOVE"; break;
		default: action_name = "ACTION_UKNOWN"; break;
		}
		result.append("Action: ").append(mot_ev.getAction()).append("\n");
		result.append("Action Name: ").append(action_name).append("\n");
		int numPointers = mot_ev.getPointerCount();
		result.append("Number of event's pointers: ")
			.append(numPointers).append("\n");
		int currPointer = 0;
		while ( currPointer < numPointers ) {
			result.append(" Location: ").append(mot_ev.getX(currPointer)).append(" ");
			result.append(" x ").append(mot_ev.getY(currPointer)).append("\n");
			result.append(" Pressure: ").append(mot_ev.getPressure(currPointer));
			result.append(" Size: ").append(mot_ev.getSize(currPointer)).append("\n");
			currPointer++;
		}
		
		result.append("Downtime: ").append(mot_ev.getDownTime()).append("ms\n");
		result.append("Event time: ").append(mot_ev.getEventTime()).append("ms");
		result.append(" Elapsed: ").append(mot_ev.getEventTime()-
				mot_ev.getDownTime());
		result.append(" ms\n");
		return result.toString();
	}
}
