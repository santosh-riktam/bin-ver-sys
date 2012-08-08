package com.san.binversys.ui;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import com.san.binversys.R;
import com.san.binversys.dao.InstalledApp;
import com.san.binversys.uitil.Utils;

public class InstalledAppsActivity extends Activity {
	private ListView listView;
	private List<InstalledApp> mInstalledApps;
	private AppsAdapter adapter;
	private EditText filterEditText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		setContentView(R.layout.activity_inst_apps);

		filterEditText = (EditText) findViewById(R.id.editText1);
		filterEditText.addTextChangedListener(textWatcher);

		mInstalledApps = new ArrayList<InstalledApp>();

		adapter = new AppsAdapter();

		listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);

	}

	@Override
	@Deprecated
	public Object onRetainNonConfigurationInstance() {
		super.onRetainNonConfigurationInstance();
		return mInstalledApps;
	}

	@TargetApi(11)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
			return false;
		filterEditText.setVisibility(View.GONE);
		getMenuInflater().inflate(R.menu.activity_install_apps, menu);
		SearchView searchView = (SearchView) menu.findItem(R.id.menu_search)
				.getActionView();
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query) {

				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				adapter.setItems(filterList(newText));
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

	private TextWatcher textWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {

		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {

		}

		@Override
		public void afterTextChanged(Editable arg0) {
			adapter.setItems(filterList(filterEditText.getText().toString()));
		}
	};

	private ArrayList<InstalledApp> filterList(String text) {
		text = text.toLowerCase().trim();
		ArrayList<InstalledApp> installedApps = new ArrayList<InstalledApp>();
		if (text == null || text.equals(""))
			installedApps.addAll(mInstalledApps);
		else
			for (InstalledApp app : mInstalledApps)
				if (app.name.toLowerCase().contains(text)
						|| app.packageName.contains(text))
					installedApps.add(app);
		setProgressBarIndeterminateVisibility(Boolean.FALSE);
		return installedApps;
	}

	@Override
	protected void onResume() {
		super.onResume();
		setProgressBarIndeterminateVisibility(true);
		if(getLastNonConfigurationInstance()!=null)
			{
				mInstalledApps=(List<InstalledApp>) getLastNonConfigurationInstance();
				adapter.setItems(filterList(""));
				return ;
			}
		new Thread(new Runnable() {

			@Override
			public void run() {

				mInstalledApps = Utils
						.getInstalledApps(InstalledAppsActivity.this);
				listView.post(new Runnable() {

					@Override
					public void run() {
						adapter.setItems(filterList(filterEditText.getText()
								.toString()));

					}
				});
			}
		}).start();
	}

	class AppsAdapter extends BaseAdapter {
		public ArrayList<InstalledApp> items;

		public AppsAdapter() {
			items = new ArrayList<InstalledApp>();
		}

		public void setItems(ArrayList<InstalledApp> applications) {
			this.items = applications;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return items.size();
		}

		@Override
		public Object getItem(int arg0) {
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			if (arg1 == null)
				arg1 = getLayoutInflater().inflate(R.layout.list_row_home,
						arg2, false);
			TextView packagenameTextView = (TextView) arg1
					.findViewById(R.id.textView2);
			String packageName = items.get(arg0).packageName;
			packagenameTextView.setText(packageName);

			TextView appnameTextView = (TextView) arg1
					.findViewById(R.id.textView1);
			appnameTextView.setText(items.get(arg0).name);

			ImageView imageView = (ImageView) arg1
					.findViewById(R.id.imageView1);
			imageView.setImageDrawable(items.get(arg0).iconDrawable);

			return arg1;
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("position", listView.getFirstVisiblePosition());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		listView.setSelection(savedInstanceState.getInt("position"));
	}

}
