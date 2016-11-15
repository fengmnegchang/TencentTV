package com.open.tencenttv.andenginetask;//package com.example.andenginetask;
//
//import com.example.newsinfo.R;
//
//import android.app.ProgressDialog;
//import android.os.Bundle;
//
//public class MainActivity extends BaseActivity {
//	/** Called when the activity is first created. */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//
//		this.doProgressAsync(this, ProgressDialog.STYLE_HORIZONTAL, R.string.app_name, R.string.app_name, new CallEarliest<Void>() {
//
//			@Override
//			public void onCallEarliest() throws Exception {
//
//			}
//
//		}, new ProgressCallable<Void>() {
//
//			@Override
//			public Void call(IProgressListener pProgressListener) throws Exception {
//				// TODO Auto-generated method stub
//				for (int i = 0; i < 100; i++) {
//					Thread.sleep(200);
//					pProgressListener.onProgressChanged(i);
//				}
//				return null;
//			}
//
//		}, new Callback<Void>() {
//
//			@Override
//			public void onCallback(Void pCallbackValue) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//	}
//}